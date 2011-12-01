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
		int NUM_THREADS=100;

		for(int iter=0;iter<protein.getLength();iter++)
		{	
			System.out.println("\nIteration: "+(iter+1));
			
			runnables.clear();
			threads.clear();
			
			//test chain to be used in this iteration
			Protein testChain = new Protein();
			
			//pull acids from the original chain
			testChain.addAcid(protein.getAcid(iter));
			
			/*for(int i=0;i<iter+1;i++)
			{
				testChain.addAcid(protein.getAcid(i));
			}*/

			//stage and create all threads
			for(int i=0;i<NUM_THREADS;i++)
			{
				State state=null;
				
				if(statePool.size()>0)
				{
					int randomIndex=new Random().nextInt(statePool.size());
					state=statePool.get(randomIndex);
				}
				
				FolderThread fThread=new FolderThread(state, algorithm, testChain, rule);
				Thread thread=new Thread(fThread);

				runnables.add(fThread);
				threads.add(thread);
			}


			//start all the threads
			for(Thread t: threads)
				t.start();

			//wait for all the threads
			waitForThreads();

			//Calculate average of all the trials
			statePool.clear();
			double average=0;
			for(FolderThread trial: runnables)
			{
				State s=trial.returnState();
				int score=s.getFitness();

				average+=score;
				statePool.add(s);
			}
			average/=runnables.size();

			//add the states that are greater than the average
			//to the pool of potential next states
			//for(State s: statePool)
			for(int i=0;i<statePool.size();i++)
			{
				State s = statePool.get(i);
				
				//if score is less than average fitness
				if(s.getFitness()<average)
				{
					statePool.remove(s);
				}
			}
		}
		
		//print out final scores
		for(FolderThread thread : runnables)
		{
			State state=thread.returnState();
			int score=state.getFitness();
			
			System.out.println("Thread: "+thread.getThreadID()+" Final Score: "+score);
			//thread.local.printBeads();
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

}
