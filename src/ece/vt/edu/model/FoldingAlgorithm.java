package ece.vt.edu.model;

//TODO: chat about how to change this to be able to stage the algorithm.
public abstract class FoldingAlgorithm {
	
	abstract boolean fold(Protein protein, EnergyRule energy, Lattice lattice, boolean restoredState);

}
