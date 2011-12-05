package ece.vt.edu.model;

import java.util.ArrayList;
import java.util.Random;

public class AntColonyManager extends ThreadManager {

	private ArrayList<State> statePool = new ArrayList<State>();
	private ArrayList<FolderThread> runnables=new ArrayList<FolderThread>();
	private ArrayList<Thread> threads=new ArrayList<Thread>();

	FoldingAlgorithm algorithm;
	Protein protein;
	EnergyRule rule;

	public AntColonyManager(FoldingAlgorithm alg, Protein protein_, EnergyRule rule_)
	{
		algorithm=alg;
		protein=protein_;
		rule=rule_;
	}

	@Override
	public void startManager() 
	{
		int NUM_THREADS=1;

		long startTime=System.currentTimeMillis();
		long TIME_LIMIT=120000;
		boolean globalFound=false;
		int bestScore=-1;
		
		int proteinLength=protein.getLength();
		
		//make a deep copy of the protein so we can chop up the other one
		Protein globalCopy = new Protein();
		protein.copyInto(globalCopy);

		while(!globalFound && (System.currentTimeMillis()-startTime)<TIME_LIMIT)
		{
			globalCopy.copyInto(protein);
			
			//perform Ant Colony optimization
			for(int index=0;index<proteinLength && !globalFound ;index++)
			{	

				runnables.clear();
				threads.clear();

				//test chain to be used in this iteration
				//Protein testChain = new Protein();
				Protein testChain = protein;
				
				//pull acids from the original chain
				//testChain.addAcid(protein.getAcid(index));

				//stage and create all threads
				for(int i=0;i<NUM_THREADS;i++)
				{
					State state=null;

					if(statePool.size()>0 && index>0)
					{
						//pick a random state
						int randomIndex=new Random().nextInt(statePool.size());
						state=statePool.get(randomIndex);
						
						//only restore the nth beads from that state
						state.setNumRestore(index);
						
						//pop off the head of the protein, we've already placed it
						protein.popFront();
					}
					
					FoldingAlgorithm newAlg = new BestMoveFirst();
					EnergyRule newHHrule =  new HHRule();

					FolderThread fThread=new FolderThread(state, newAlg, testChain, newHHrule);
					Thread thread=new Thread(fThread);

					runnables.add(fThread);
					threads.add(thread);
				}


				//start all the threads
				for(Thread t: threads)
				{
					t.start();
				}
				
				//wait for all the threads
				waitForThreads();

				//Calculate average of all the trials
				statePool.clear();
				
				for(FolderThread trial : runnables)
				{
					State s = trial.returnState();
					statePool.add(s);
				}
				
				/*double average=0;
				for(FolderThread trial: runnables)
				{
					State s=trial.returnState();
					int score=s.getFitness();

					if(score>=globalOptimal)
					{
						globalFound=true;
					}
					
					if(score>=bestScore)
					{
						bestScore=score;
					}
					
					average+=score;
					statePool.add(s);
					
					System.out.println("Score: "+score);
				}
				average/=runnables.size();*/

				//add the states that are greater than the average
				//to the pool of potential next states
				//for(State s: statePool)
				for(int i=0;i<statePool.size();i++)
				{
					State s = statePool.get(i);

					//if score is less than average fitness
					if(s.getFitness()<(globalOptimal/2))
					{
						statePool.remove(s);
					}
					
					System.out.println("Score: "+s.fitness);
				}
			}

			//print out final scores
			int runningTotal=0;
			for(FolderThread thread : runnables)
			{
				State state=thread.returnState();
				int score=state.getFitness();

				runningTotal+=score;
				//System.out.println("Thread: "+thread.getThreadID()+" Final Score: "+score);
				//thread.local.printBeads();
			}
			
			//System.out.println("Ant Colony Avg: "+runningTotal/NUM_THREADS);

		}
		if(globalFound)
		{
			System.out.println("Global optimal found in "+(System.currentTimeMillis()-startTime)+" ms");
		}
		else
		{
			System.out.println("Global optimal not found. Best score: "+bestScore);
		}
	}

	private void waitForThreads()
	{
		//wait until all threads are dead

		for(Thread t : threads)
		{
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		/*boolean allThreadsDead = false;
		while (!allThreadsDead) {
			int counter = 0;
			for (int i = 0; i < threads.size(); i++) {
				if (!threads.get(i).isAlive()) {
					counter++;
				}
			}

			if (counter == threads.size()) {
				allThreadsDead = true;
			}
		}*/
	}

	@Override
	public void setGlobalScore(int score) 
	{
		globalOptimal=score;

	}

}
