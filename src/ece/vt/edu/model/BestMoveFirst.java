package ece.vt.edu.model;

import java.util.Random;

/*This algorithm initially places the protein at a random site in the lattice
* and then makes the next best move using the energy rule given
*/

public class BestMoveFirst extends FoldingAlgorithm {

	
	@Override
	boolean fold(Protein protein, EnergyRule energy, Lattice lattice) 
	{
		int proteinLength=protein.getLength();
		
		AAcid head=protein.getAcid(0);
		
		//check to make sure the head is actually the first one in the series
		if(head.myID!=0) 
		{
			System.out.println("Something bad happened...");
		}
		
		//place head at random location in the lattice
		if(lattice.isLattice2D())
		{
			int x=new Random().nextInt(lattice.getDimensions());
			int y=new Random().nextInt(lattice.getDimensions());
			
			lattice.placeAcid(head, x, y, 0);
		}
		
		for(int i=0;i<proteinLength;i++)
		{
			
		}
		
		return true;
	}
	

}
