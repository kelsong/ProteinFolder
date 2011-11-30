package ece.vt.edu.model;

public abstract class ThreadManager {
    	FoldingAlgorithm folder;
	Protein foldee;
	EnergyRule fitness;
	
	public abstract void startManager();
}
