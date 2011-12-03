package ece.vt.edu.model;

import java.util.ArrayList;

public class ChainGrowthThreadManager extends ThreadManager {

	private static final int NUM_THREADS = 100;

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
		// TODO Auto-generated method stub

		for (int i = 0; i < protein.getLength() / 2; i++) {
			runnables.clear();
			threads.clear();

			if (good_states.size() == 0) {
				// initialize
			} else {
				//resume and add next 
			}
		}
	}

}
