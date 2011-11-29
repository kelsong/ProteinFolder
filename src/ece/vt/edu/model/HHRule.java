package ece.vt.edu.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class HHRule extends EnergyRule{

	@Override
	public int scoreLattice(Lattice lattice)
	{

		//hash set to keep track of the bonds we've counted
		HashSet<AcidBond> bondSet=new HashSet<AcidBond>();

		//count the number of bond
		int bondCount=0;

		//walk through protein chain starting at the head
		List<LatticeBead> beadList=lattice.getListofBeads();

		for(LatticeBead bead : beadList)
		{
			if(bead.getAcid().isAcidHydrophobic())
			{
				//get a list of neighbors for this bead
				ArrayList<LatticeBead> neighborBead = lattice.getAdjacentBeads(bead);
				for(LatticeBead neighbor : neighborBead)
				{
					//is neighbor hydrophobic
					if(neighbor.getAcid().isAcidHydrophobic())
					{
						AcidBond newBond = new AcidBond(bead,neighbor);

						//check to see if bond had already been counter
						if(!bondSet.contains(newBond))
						{
							//add to set
							bondSet.add(newBond);

							//count this bond
							bondCount++;
						}
					}
				}
			}
		}

		return bondCount*1;
	}

	public String toString()
	{
		return "HH Rule";
	}

	//		while(bead.succ!=null)
	//		{
	//			//get a list of neighbors for this bead
	//			ArrayList<LatticeBead> neighborBead = lattice.getAdjacentBeads(bead);
	//			for(LatticeBead neighbor : neighborBead)
	//			{
	//				//is neighbor hydrophobic
	//				if(neighbor.getAcid().isAcidHydrophobic())
	//				{
	//					AcidBond newBond = new AcidBond(bead,neighbor);
	//					
	//					//check to see if bond had already been counter
	//					if(!bondSet.contains(newBond))
	//					{
	//						//add to set
	//						bondSet.add(newBond);
	//						
	//						//count this bond
	//						bondCount++;
	//					}
	//				}
	//			}
	//			
	//			bead=bead.succ;
	//		}

	//		return bondCount*-1;

	/*
		//start at (0,0) and walk through all locations, noting each bond
		int runningScore=0;
		int latDimension=lattice.getDimensions();


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

	}*/
}
