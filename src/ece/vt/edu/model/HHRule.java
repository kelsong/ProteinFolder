package ece.vt.edu.model;

import java.util.ArrayList;
import java.util.HashSet;

public class HHRule extends AbstractEnergyRule{
	
	private class HHBond
	{
		LatticeBead a;
		LatticeBead b;
		
		@SuppressWarnings("unused")
		public HHBond(LatticeBead _a, LatticeBead _b)
		{
			a=_a;
			b=_b;
		}
		
		public boolean equals(HHBond that)
		{
			if(this.a==that.a && this.b==that.b)
			{
				return true;
			}
			else if(this.a==that.b && this.b==that.a)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	}

	@Override
	int scoreLattice(Lattice lat) {
		//assuming only square lattices, need to figure out if 2D or 3D
		boolean is2D=lat.isLattice2D();
		
		int score=0;
		
		if(is2D)
		{
			score=score2DLattice(lat);
		}
		else
		{
			score=score3DLattice(lat);
		}


		return score;
	}
	
	private int score2DLattice(Lattice lat)
	{
		//start at (0,0) and walk through all locations, noting each bond
		int runningScore=0;
		int latDimension=lat.getDimensions();
		
		
		HashSet<HHBond> bondMap=new HashSet<HHBond>();
		
		for(int x=0;x<latDimension;x++)
		{
			for(int y=0;y<latDimension;y++)
			{
				//get lattice site
				LatticeSite latticeSite=lat.getLatticeSite(x, y, 0);
				
				//if site contains an H, check for neighboring H's
				if(latticeSite.isFilled())
				{
					LatticeBead bead=latticeSite.getBead();
					
					if(bead.getAcid().isAcidHydrophobic())
					{
						//count the number of neighboring H's
						 ArrayList<GridLocation> neighbors=latticeSite.getAdjacentSites(true, 1);
						 
						 int numHNeighbors=0;
						 for(GridLocation loc : neighbors) //for each neighbor
						 {
							 //get the lattice bead at this location
							 LatticeBead nBead=lat.getLatticeSite(loc.getX(), loc.getY(), 0).getBead();
							 
							 //if neighbor is an acid and this bond hasn't already been score
							 if(nBead.getAcid().isAcidHydrophobic()) 
							 {
								 HHBond newBond=new HHBond(bead,nBead);
								 if(!bondMap.contains(newBond))
								 {
									 //add bond to hash set
									 bondMap.add(newBond);
									 
									 //increment running score
									 numHNeighbors++;
 
								 }
							 }
						 }
						 
						 runningScore+=numHNeighbors*-1;
					}
				}
			}
		}
		return runningScore;
	}
	
	private int score3DLattice(Lattice lat)
	{
		return 0;
	}

}
