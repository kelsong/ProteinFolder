package ece.vt.edu.model;

import java.util.ArrayList;
public class RandomThreadManager extends ThreadManager{

	public static int NUM_THREADS = 100;
	public static final int NUM_ITER = 1; 

	ArrayList<State> best_states = new ArrayList<State>();
	//build the ability to launch many threads from this manager. 
	//Not entirely necessary just nice to have it contained.

	public RandomThreadManager(){

	}

	public RandomThreadManager(FoldingAlgorithm fold, Protein prot, EnergyRule rules){
		folder = fold;
		foldee = prot;
		fitness = rules;
	}

	public void startManager(){

		System.out.println("Starting RandomThreadManager with "+NUM_THREADS+" threads");

		FolderThread[] runnables= new FolderThread[NUM_THREADS];
		Thread[] threads = new Thread[NUM_THREADS];
		for(int i = 0; i<NUM_ITER; i++){
			for(int j = 0; j < NUM_THREADS; j++)
			{
				runnables[j] = new FolderThread(null, folder, foldee, fitness);
				threads[j] = new Thread(runnables[j]);
			}

			for(int j = 0; j < NUM_THREADS; j++){
				threads[j].start();
			}

			for(int j = 0; j < NUM_THREADS; j++){ // be awesome
				try{
					threads[j].join();
				} catch (InterruptedException e){
					e.printStackTrace();
				}
			}
		}

		for(int i=0; i<NUM_THREADS; i++){
			State st = runnables[i].returnState();
			if(st == null){
				System.out.println("Something is wrong");
			}
			System.out.println("Final Score: " + st.getFitness());
		}
	}

	@Override
	public void setGlobalScore(int score) {
		globalOptimal=score;

	}

	@Override
	public void setNumThreads(int numT) {
		NUM_THREADS=numT;

	}
}
