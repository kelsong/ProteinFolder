package ece.vt.edu.model;

abstract public class ThreadManager {
	
		FoldingAlgorithm folder;
		Protein foldee;
		EnergyRule fitness;
		
		//build the ability to launch many threads from this manager. 
		//Not entirely necessary just nice to have it contained.
		abstract public void startManager();
}
