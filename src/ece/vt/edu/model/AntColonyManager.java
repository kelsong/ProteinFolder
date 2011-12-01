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
		int NUM_THREADS=2;

		for(int iter=0;iter<protein.getLength();iter++)
		{
			//test chain to be used in this iteration
			Protein testChain = new Protein();
			
			//pull acids from the original chain
			for(int i=0;i<protein.getLength();i++)
			{
				testChain.addAcid(protein.getAcid(i));
			}

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

			//calulate average of all the trials
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
			for(State s: statePool)
			{
				//if score is less than average fitness
				if(s.getFitness()<average)
				{
					statePool.remove(s);
				}
			}
		}
	}

	private void waitForThreads()
	{
		//wait until all threads are dead
		boolean allThreadsDead = false;
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
		}
	}

}
