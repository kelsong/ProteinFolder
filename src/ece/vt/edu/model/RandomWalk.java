package ece.vt.edu.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class RandomWalk extends FoldingAlgorithm {

	//int finalScore=-1;
	// No state saving for now
	// start with a truly random walk
	@Override boolean fold(Protein protein, EnergyRule energy, Lattice lattice, boolean restoredState) {
		
		int protein_length = protein.getLength();
		Random gen = new Random();
		
		LatticeSite start = lattice.getRandomSite();
		lattice.placeAcid(protein.getAcid(0), start);
		LatticeSite curr = start;
		
		for (int i = 1; i < protein_length; i++) {
		    	
		    	if(curr == null){
		    	    //bad things happened so fail
		    	    return false; 
		    	}
			List<LatticeSite> adjacent_list = lattice.getAdjacentSites(curr);
			AAcid next = protein.getAcid(i);
			// check for stuck
			boolean stuck = true;
			//System.out.println("num adjacent spaces: " + adjacent_list.size());
			List<LatticeSite> good_neighbors = new ArrayList<LatticeSite>();
			
			for (int j = 0; j < adjacent_list.size(); j++) {
				if (!adjacent_list.get(j).isFilled()) {
					good_neighbors.add(adjacent_list.get(j));
				}
			}
			if (good_neighbors.size() == 0) {
				//System.out.println("Random Walk is Stuck...");
				return false;
			} 
			
			//grab a site
			int idx = gen.nextInt(good_neighbors.size());
			
			lattice.placeAcid(next, good_neighbors.get(idx));
			
			curr = adjacent_list.get(idx);
			
			//System.out.println("Iteration #" + i);
		    	//lattice.printBeads();
		}
		
		finalScore=energy.scoreLattice(lattice);
		//System.out.println("Final Score: "+energy.scoreLattice(lattice));
		
		return true;
	}
}
