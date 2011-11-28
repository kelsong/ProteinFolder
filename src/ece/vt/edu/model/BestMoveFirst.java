package ece.vt.edu.model;

import java.util.ArrayList;
import java.util.Random;

/*This algorithm initially places the protein at a random site in the lattice
* and then makes the next best move using the energy rule given
*/

public class BestMoveFirst extends FoldingAlgorithm {

	
	@Override
	boolean fold(Protein protein, EnergyRule energy, Lattice lattice) 
	{
		int proteinLength=protein.getLength();


		//place initial acid at random location in lattice
		LatticeBead bead=lattice.placeAcid(protein.getAcid(0), lattice.getRandomSite());
		
		//walk through the chain, selecting the best possible placement 
		//based upon only the next bead
		for(int i=1;i<proteinLength;i++)
		{
			if(bead==null)
			{
				System.out.println("Couldn't place bead, something bad happened...");
			}
			
			//get next bead/acid in the chain
			AAcid acid=protein.getAcid(i);
			
			//get list of neighbors from last placed bead
			ArrayList<LatticeSite> sites=lattice.getAdjacentSites(bead);
			
			//rate each bead placement and choose the one that's best
			ArrayList<Integer> scores=new ArrayList<Integer>();
			
			int bestScore=0;
			int indexOfBestScore=-1;
			for(int j=0;j<sites.size();j++)
			{
				//get neighbor
				LatticeSite potentialSite=sites.get(j);
				
				//place in lattice
				lattice.placeAcid(acid, potentialSite);
				
				//compare score against bestScore
				int score=energy.scoreLattice(lattice);
				if(score>bestScore)
				{
					bestScore=score;
					indexOfBestScore=j;
				}
				
				//remove latest bead
				if(!lattice.removeLastBead())
				{
					System.out.println("Something bad happened...");
					return false;
				}
				
			}
			
			//out of all the potential neighbors, choose the one that had the best score
			//and get the bead associated with that site
			bead=lattice.placeAcid(acid, sites.get(indexOfBestScore));
			
		}
		
		return true;
	}
	

}
