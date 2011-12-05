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
	
	int finalScore=-1;
	
	static int counter=0;
	int threadID;
	
	public FolderThread(State init, FoldingAlgorithm alg, Protein prot, EnergyRule rule) {
		ref = init;
		local = new Lattice(true, 100, true);
		
		if (ref != null) {
			// apply state to lattice
			ref.restoreState(local);
		} 
		
		folder = alg;
		protein = prot;
		rules = rule;
		
		threadID=counter++;
	}
	
	public int getThreadID()
	{
		return threadID;
	}
	
	public FolderThread(State init) {
		ref = init;

		if (ref != null) {
			// apply state to lattice
			ref.restoreState(local);
		}
	}

	public void run() {
		boolean restoredState=false;
		
		if (ref == null) 
		{
		    //System.out.println("Creating New State");
			ref = new State();
			restoredState=false;
		}
		else
		{
			restoredState=true;
		}
		

		boolean success = folder.fold(protein, rules, local,restoredState);

		if(success){
		    ref.recordState(local, rules.scoreLattice(local));
		} else {
		    ref.setFitness(0);
		}
	}

	public State returnState() {
	    	if(ref == null){
	    	    System.out.println("Something wrong here");
	    	}
		return ref;
	}
	
	public void setState(State newState)
	{
		ref=newState;
	}
}

class State {
	ArrayList<GridLocation> bead_loc = new ArrayList<GridLocation>();
	//need a way to store the AAcids and figure out where to start in the amino acid chain.
	ArrayList<AAcid> acids = new ArrayList<AAcid>();
	
	//ArrayList<LatticeBead> savedList = new ArrayList<LatticeBead>();
	
	int fitness = 0;

	public void recordState(Lattice lattice, int score) {
		bead_loc.clear();
		acids.clear();
		
		fitness = score;
		List<LatticeBead> temp = lattice.getListofBeads();

		for (int i = 0; i < temp.size(); i++) {
			bead_loc.add(temp.get(i).getLocation().getGridLocation());
			acids.add(temp.get(i).getAcid());
		}
		
		//System.out.println("Saving state:");
		//lattice.printBeads();
		
		/*System.out.println("Copy:");
		for(int i=0;i<acids.size();i++)
		{
			System.out.println(acids.get(i)+" "+bead_loc.get(i));
		}*/
		
		
	}

	public void restoreState(Lattice lattice) {
		lattice.clearLattice();
		
		for (int i = 0; i < bead_loc.size(); i++) {
			lattice.placeAcid(acids.get(i), lattice.getLatticeSite(bead_loc.get(i)));
		}
		
		//System.out.println("Lattice After Restore:");
		//lattice.printBeads();
	}
	
	public void setFitness(int score){
	    fitness = score;
	}
	
	public int getFitness(){
	    return fitness;
	}
	
	public void printState(){
	    for(int i = 0; i<bead_loc.size(); i++){
		System.out.println(acids.get(i).toString() + "@" + "(" 
				+ bead_loc.get(i).getX() + "," 
				+ bead_loc.get(i).getY() + ","
				+ bead_loc.get(i).getZ() + ")");
		
	    }
	}
}