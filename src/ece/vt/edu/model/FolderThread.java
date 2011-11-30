package ece.vt.edu.model;

import java.util.ArrayList;
import java.util.List;

//This setup is fairly clever. It allows us to program algorithms that both do and do not take state.
//without exposing the state storage to anyone except the orchestrator. 
// We can do this by using implicit information in the lattice class, though I may make this information explicit since it will just be easier.

public class FolderThread implements Runnable {

	// this is conveniently thread safe because each object is it's own thread.
	// Each thread can be considered a self contained "worker" thread that
	// shares no information except with the orchestrator.

	State ref;
	FoldingAlgorithm folder;
	Protein protein;
	EnergyRule rules;
	Lattice local;
	
	public FolderThread(State init) {
		ref = init;

		if (ref != null) {
			// apply state to lattice
			ref.restoreState(local);
		}
	}

	public void run() {
		if (ref == null) {
			ref = new State();
		}

		folder.fold(protein, rules, local);

		ref.recordState(local, rules.scoreLattice(local));
	}

	public State returnState() {
		return ref;
	}
}

class State {
	ArrayList<GridLocation> bead_loc = new ArrayList<GridLocation>();
	//need a way to store the AAcids and figure out where to start in the amino acid chain.
	
	int fitness;

	public void recordState(Lattice lattice, int score) {
		bead_loc.clear();

		fitness = score;
		List<LatticeBead> temp = lattice.getListofBeads();

		for (int i = 0; i < temp.size(); i++) {
			bead_loc.add(temp.get(i).getLocation().getGridLocation());
		}
	}

	public void restoreState(Lattice lattice) {
		for (int i = 0; i < bead_loc.size(); i++) {
			//need to figure this out in order to restore the Amino Acids. 
		}
	}
}