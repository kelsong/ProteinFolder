package ece.vt.edu.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*This algorithm initially places the protein at a random site in the lattice
* and then makes the next best move using the energy rule given
*/

public class BestMoveFirst extends FoldingAlgorithm {

	
	@Override
	boolean fold(Protein protein, EnergyRule energy, Lattice lattice) 
	{
		System.out.println("Hello world! Attempting to fold protein\n"+protein+"with Energy Rule: "+energy);
		int proteinLength=protein.getLength();
		
		//get a random site in the lattice and place a bead there
		LatticeSite currentSite=lattice.getRandomSite();
		lattice.placeAcid(protein.getAcid(0), currentSite);
		System.out.println("Placing initial acid at "+currentSite);
		
		//walk through the chain, selecting the best possible placement 
		//based upon only the next bead
		for(int acidIndex=1;acidIndex<proteinLength;acidIndex++)
		{
			if(currentSite==null)
			{
				System.out.println("Couldn't place bead, something bad happened...");
			}
			
			//get next bead/acid in the chain
			AAcid acid=protein.getAcid(acidIndex);
			
			//get list of neighbors from last placed bead
			List<LatticeSite> sites=lattice.getAdjacentSites(currentSite);
			
			/*System.out.println("Neighbors of "+site+" are: ");
			for(LatticeSite s : sites)
			{
				System.out.println(s);
			}*/
			
			//rate each bead placement and choose the one that's best
			ArrayList<Integer> scoresIndex=new ArrayList<Integer>();
			
			int bestScore=0;
			boolean acidPlaced=false;
			for(int neighborIndex=0;neighborIndex<sites.size();neighborIndex++)
			{
				//get a neighbor
				LatticeSite potentialSite=sites.get(neighborIndex);
				
				//place in lattice
				LatticeBead bead=lattice.placeAcid(acid, potentialSite);
				if(bead==null)
				{
					acidPlaced=false;
					//System.out.println("Couldn't place acid, continuing...");
					continue;
				}
				else
				{
					acidPlaced=true;
				}
				
				//compare score against bestScore
				int score=energy.scoreLattice(lattice);
				
				if(score>bestScore)
				{
					scoresIndex=new ArrayList<Integer>();
					scoresIndex.add(neighborIndex);
					
					bestScore=score;
				}
				else if(score==bestScore)
				{
					scoresIndex.add(neighborIndex);
				}
				
				//remove latest bead
				if(!lattice.removeLastBead())
				{
					System.out.println("Couldn't remove bead! Something bad happened...");
					return false;
				}
				
			}
			
			if(!acidPlaced)
			{
				System.out.println("Algorithm Failure!!! Could not place acid!\nShutting down.");
				System.out.println("Lattice at time of failure...");
				lattice.printBeads();
				return false;
			}
			
			int numScores=scoresIndex.size();
			int indexOfBestScore=scoresIndex.get(new Random().nextInt(numScores));
				
			//out of all the potential neighbors, choose the one that had the best score
			//and get the bead associated with that site
			System.out.println("Best Site Found at "+sites.get(indexOfBestScore)+" with score "+bestScore);
				
			lattice.placeAcid(acid, sites.get(indexOfBestScore));
			
			currentSite=sites.get(indexOfBestScore);
			
		}
		
		//print final protein
		lattice.printBeads();
		
		return true;
	}
	

}
