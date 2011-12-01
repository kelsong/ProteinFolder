package ece.vt.edu.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*This algorithm initially places the protein at a random site in the lattice
* and then makes the next best move using the energy rule given
*/

public class BestMoveFirst extends FoldingAlgorithm {

	int finalScore=-1;
	
	@Override
	boolean fold(Protein protein, EnergyRule energy, Lattice lattice, boolean restoredState) 
	{
		//System.out.println("Hello world! Attempting to fold protein\n"+protein+"with Energy Rule: "+energy);
		int proteinLength=protein.getLength();
	
		LatticeSite currentSite=null;
		int startingIndex=-1;
		//we're starting from a restored state
		//go to the end of the chain
		if(restoredState) 
		{
			currentSite=lattice.getLastSite();
			startingIndex=0;
		}
		else
		{
			//get a random site in the lattice and place a bead there
			currentSite=lattice.getRandomSite();
			LatticeBead bead=lattice.placeAcid(protein.getAcid(0), currentSite);
			startingIndex=1;
			
			//System.out.println(bead.getAcid()+" "+currentSite+" "+"initial");
		}
		
		//System.out.println("Placing initial acid at "+currentSite);
		
		//walk through the chain, selecting the best possible placement 
		//based upon only the next bead
		for(int acidIndex=startingIndex;acidIndex<proteinLength;acidIndex++)
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
			
			//System.out.println("Current site: "+currentSite);
			
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
					acidPlaced|=false;
					//System.out.println("Couldn't place acid, continuing...");
					continue;
				}
				else
				{
					//System.out.println("Potential site: "+potentialSite);
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
				System.out.println("BestMoveFirst Failure!");
				//System.out.println("Lattice at time of failure: ");
				//lattice.printBeads();
				return false;
			}
			
			int numScores=scoresIndex.size();
			int indexOfBestScore=scoresIndex.get(new Random().nextInt(numScores));
				
			//out of all the potential neighbors, choose the one that had the best score
			//and get the bead associated with that site
			
				
			LatticeBead bead=lattice.placeAcid(acid, sites.get(indexOfBestScore));
			//System.out.println("Insert "+bead.getAcid()+" "+sites.get(indexOfBestScore)+" "+bestScore);
			
			if(bead==null)
			{
				System.out.println("Something really terrible happened...");
			}
			
			currentSite=sites.get(indexOfBestScore);
			
		}
		
		finalScore=energy.scoreLattice(lattice);
		
		//System.out.println("////Simulation Solution////");
		System.out.println("Final Score: "+finalScore);
		//System.out.println("Lattice Structure: ");
		//lattice.printBeads();
		
		return true;
	}
}
