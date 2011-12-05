package ece.vt.edu.model;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.plaf.SliderUI;

public class AntColonyManager extends ThreadManager {

	private ArrayList<State> statePool = new ArrayList<State>();
	private ArrayList<FolderThread> runnables=new ArrayList<FolderThread>();
	private ArrayList<Thread> threads=new ArrayList<Thread>();

	FoldingAlgorithm algorithm;
	Protein protein;
	EnergyRule rule;
	
	State bestState=null;

	public AntColonyManager(FoldingAlgorithm alg, Protein protein_, EnergyRule rule_)
	{
		algorithm=alg;
		protein=protein_;
		rule=rule_;
	}

	@Override
	public void startManager() 
	{
		int NUM_THREADS=4;

		System.out.println("Starting AntColony with "+NUM_THREADS+" threads");
		
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
						state=new State();
						
						//pick a random state
						int randomIndex=new Random().nextInt(statePool.size());
						statePool.get(randomIndex).CopyInto(state);
						
						//only restore the nth beads from that state
						state.setNumRestore(index);
						
						//pop off the head of the protein, we've already placed it
						protein.popFront();
					}
					
					//FoldingAlgorithm newAlg = new BestMoveFirst();
					FoldingAlgorithm newAlg=null;
					try {
						newAlg = algorithm.getClass().newInstance();
					} catch (InstantiationException e) {
					
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						
						e.printStackTrace();
					}
					
					//EnergyRule newHHrule =  new HHRule();
					EnergyRule newRule=null;
					try {
						newRule = rule.getClass().newInstance();
					} catch (InstantiationException e) {
						
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						
						e.printStackTrace();
					}
					
					Protein newProtein=new Protein();
					testChain.copyInto(newProtein);

					FolderThread fThread=new FolderThread(state, newAlg, newProtein, newRule);
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

				//add the states that are greater than the average
				//to the pool of potential next states
				//for(State s: statePool)
				for(int i=0;i<statePool.size();i++)
				{
					State s = statePool.get(i);

					//update the bestscore
					if(s.getFitness()>bestScore)
					{
						bestScore=s.getFitness();
						bestState=s;
					}
					
					if(s.getFitness()>=globalOptimal)
					{
						globalFound=true;
					}
					
					//if score is less than average fitness
					if(s.getFitness()<(globalOptimal/5))
					{
						statePool.remove(s);
					}
					
					System.out.println("Score: "+s.fitness);
				}
			}

		}
		long endTime=System.currentTimeMillis();
		
		if(globalFound)
		{
			System.out.println("Global optimal found in "+(endTime-startTime)+" ms");
		}
		else
		{
			System.out.println("Global optimal not found. Best score: "+bestScore);
		}
		bestState.printState();
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
	}

	@Override
	public void setGlobalScore(int score) 
	{
		globalOptimal=score;

	}

}
