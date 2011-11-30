package ece.vt.edu.model;

import java.util.ArrayList;
public class RandomThreadManager extends ThreadManager{
	
    		public static final int NUM_THREADS = 1;
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
		    FolderThread[] runnables= new FolderThread[NUM_THREADS];
		    Thread[] threads = new Thread[NUM_THREADS];
		    for(int i = 0; i<NUM_ITER; i++){
			for(int j = 0; j < NUM_THREADS; j++){
			    runnables[i] = new FolderThread(null);
			    threads[i] = new Thread(runnables[i]);
			}
			
			for(int j = 0; j < NUM_THREADS; j++){
			    threads[i].start();
			}
		    }
		}
}
