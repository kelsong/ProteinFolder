package ece.vt.edu.model;

public class ExhaustiveSearch {
    
    Lattice lattice;
    Protein protein;
    EnergyRule rule; 
    
    State best_found;
    
    public ExhaustiveSearch(Protein prot){
	protein = prot;
	
	lattice = new Lattice(true, 100, true); 
    }
    
    public int search(){
	//place initial bead somewhere with lots of room to fold
	GridLocation loc = new GridLocation(50,50);
	
	LatticeSite site = lattice.getLatticeSite(loc);
	
	lattice.placeAcid(protein.getAcid(0), site);
	
	return recursiveSearch(1, site);
    }
    
    public int recursiveSearch(int index, LatticeSite prev){
	AAcid amino = protein.getAcid(index);
	//Lattice get
	//placeAcid()
	return 0;
    }
}
