package ece.vt.edu.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

//There are some bugs here. For some reason the lattice gets corrupted and then the state save and restore sometimes fails.
public class ChainGrowth extends FoldingAlgorithm {

	@Override
	boolean fold(Protein protein, EnergyRule energy, Lattice lattice,
			boolean restoredState) {

		int middle = protein.getLength() / 2;
		
		//start in the middle of the protein
		if(!restoredState){
			//find middle of protein chain
			middle = protein.getLength() / 2; 
			
			lattice.placeAcid(protein.getAcid(middle), lattice.getRandomSite());
			return true;
			
		} else {
			//what are the cases for potential growth. 
			//only 2, case where there are an even number of placed acids
			// case where there are an odd number of placed acids.
			//if number of placed acids are even, then it is done, else there is more to attempt to grow.
			
			int num_placed = lattice.getNumberBeads();
			//System.out.println("num_placed total: " + num_placed);
			if(num_placed %2 == 1){ // case one
				//number of places from middle to start
				int placed_each_side = (num_placed - 1)/2;
				//System.out.println("Num placed each side: " + placed_each_side);
				
				boolean placed_successful = true;
				if((middle + placed_each_side) < protein.getLength()){
					//place the next acid from the top half
					 placed_successful = attemptAcidPlacement(protein.getAcid(middle+placed_each_side), lattice, placed_each_side, true);
				} 
				if((middle - placed_each_side) >= 0){
					//place next acid from the bottom half
					placed_successful &= attemptAcidPlacement(protein.getAcid(middle-placed_each_side), lattice, placed_each_side, false);
				}
				
				return placed_successful;
			} else {
			    	System.out.println("breaking out");
				return true; // if placed even number you have placed all the acids in the protein, by nature of the growth.
			}
			
		}
		//return false;
	}

	private boolean attemptAcidPlacement(AAcid acid, Lattice lat, int placed_each_side, boolean top_half){
		List<LatticeSite> adjacent_sites;
		//System.out.println("ADDING ACID");
		//System.out.println(placed_each_side);
		if(placed_each_side != 0){
		    if(top_half){
			adjacent_sites = lat.getAdjacentSites(lat.getListofBeads().get(1+2*(placed_each_side-1)).getLocation());
		    } else {
			adjacent_sites = lat.getAdjacentSites(lat.getListofBeads().get(2+2*(placed_each_side-1)).getLocation());
		    }
		} else {
		    adjacent_sites = lat.getAdjacentSites(lat.getListofBeads().get(0).getLocation());
		}
		
		List<LatticeSite> good_sites = new ArrayList<LatticeSite>();
		for(int i = 0; i < adjacent_sites.size(); i++){
			if(!adjacent_sites.get(i).isFilled()){
				good_sites.add(adjacent_sites.get(i));
			}
		}
		
		if(good_sites.size() == 0) return false;
		
		Random gen = new Random();
		
		//choose site
		
		if(lat.placeAcid(acid, good_sites.get(gen.nextInt(good_sites.size()))) != null){
			return true;
		} else {
			return false;
		}
	}
}
