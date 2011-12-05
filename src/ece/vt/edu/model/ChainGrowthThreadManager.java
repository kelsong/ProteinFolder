package ece.vt.edu.model;

import java.util.ArrayList;
import java.util.Random;

public class ChainGrowthThreadManager extends ThreadManager {

    private static int NUM_THREADS = 2;

    private ArrayList<State> good_states = new ArrayList<State>();
    private ArrayList<FolderThread> runnables = new ArrayList<FolderThread>();
    private ArrayList<Thread> threads = new ArrayList<Thread>();

    private Protein protein;
    private EnergyRule fitness;
    private FoldingAlgorithm alg = new ChainGrowth();

    public ChainGrowthThreadManager(Protein prot, EnergyRule rule) {
	protein = prot;
	fitness = rule;
    }

    @Override
    public void startManager() {
	
	System.out.println(NUM_THREADS);
	for (int i = 0; i < protein.getLength() / 2; i++) {
	    System.out.println("Iteration " + i + ": ");
	    runnables.clear();
	    threads.clear();

	    if (good_states.size() == 0) {
		// initialize
		for (int j = 0; j < NUM_THREADS; j++) {
		    runnables
			    .add(new FolderThread(null, alg, protein, fitness));
		}
		for (int j = 0; j < NUM_THREADS; j++) {
		    threads.add(new Thread(runnables.get(j)));
		}

		for (int j = 0; j < NUM_THREADS; j++) {
		    threads.get(j).start();
		}

		waitForThreads();

		// add all stored states to the state pool
		good_states.clear();
		for (int j = 0; j < NUM_THREADS; j++) {
		    good_states.add(runnables.get(j).returnState());
		}
		System.out.println("Ended initialization");
		System.out.println(good_states.size());
	    } else {
		// resume and add next
		int fitness_sum = 0;
		for (int j = 0; j < good_states.size(); j++) {
		    // add up the fitness scores to get a total fitness score
		    fitness_sum += good_states.get(j).getFitness();
		}

		Random chooser = new Random();

		//get starting states 
		for (int j = 0; j < NUM_THREADS; j++) {
		    if(fitness_sum != 0){
    		    	int choice = chooser.nextInt(fitness_sum);
    		    	int current_threshold = 0;
    		    	for (int k = 0; k < good_states.size(); k++) {
    		    	    current_threshold += good_states.get(k).getFitness();
    		    	    if (choice < current_threshold) {
    		    		//set this state to the thread starting state
    		    		runnables.add(new FolderThread(good_states.get(k), alg, protein, fitness));
    		    	    }
    		    	}
		    } else {
			int choice = chooser.nextInt(good_states.size());
			runnables.add(new FolderThread(good_states.get(choice), alg, protein, fitness));
		    }
		}
		
		for (int j = 0; j < NUM_THREADS; j++) {
		    threads.add(new Thread(runnables.get(j)));
		}

		for (int j = 0; j < NUM_THREADS; j++) {
		    threads.get(j).start();
		}

		waitForThreads();

		// add all stored states to the state pool

		for (int j = 0; j < NUM_THREADS; j++) {
		    good_states.add(runnables.get(j).returnState());
		    
		}
	    }
	}
	
	for (int j = 0; j < NUM_THREADS; j++) {
	    System.out.println("Score: " + good_states.get(j).getFitness());   
	}
    }

    private void waitForThreads() {
	// wait until all threads are dead

	for (Thread t : threads) {
	    try {
		t.join();
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
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
