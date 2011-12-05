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
		//BondCounter bondSet = new BondCounter();

		//count the number of bond
		int bondCount=0;

		//walk through protein chain starting at the head
		List<LatticeBead> beadList=lattice.getListofBeads();
		
		//count number of adjacent H's, subtract from final score
		int numAdjacent=0;
		for(int i=0;i<beadList.size()-1;i++)
		{
			LatticeBead current=beadList.get(i);
			LatticeBead next=beadList.get(i+1);
			
			if(current.getAcid().isAcidHydrophobic()&&next.getAcid().isAcidHydrophobic())
			{
				numAdjacent++;
			}
		}

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
		
		//done counting, print out the bond
		return (bondCount-numAdjacent);
	}

	class BondCounter
	{
		List<AcidBond> bondList=new ArrayList<AcidBond>();
		
		boolean contains(AcidBond bond)
		{
			if(bondList.isEmpty())
			{
				return false;
			}
			
			for(AcidBond element : bondList)
			{
				if(element.equals(bond))
				{
					return true;
				}
			}
			
			return false;
		}
		
		boolean add(AcidBond bond)
		{
			if(contains(bond))
			{
				return false;
			}
			else
			{
				bondList.add(bond);
				return true;
			}
		}
	}

	
	public String toString()
	{
		return "HH Rule";
	}
}
