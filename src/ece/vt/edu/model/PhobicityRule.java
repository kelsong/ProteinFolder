package ece.vt.edu.model;

import java.util.ArrayList;
import java.util.HashSet;

public class PhobicityRule extends AbstractEnergyRule{

	@Override
	public int scoreLattice(Lattice lattice) {
		
		HashSet<AcidBond> bondSet=new HashSet<AcidBond>();
		LatticeBead bead=lattice.getHead();
		
		int runningTotal=0;
		
		while(bead.succ!=null)
		{
			//get a list of neighbors for this bead
			ArrayList<LatticeBead> neighborBead = lattice.getAdjacentBeads(bead);
			for(LatticeBead neighbor : neighborBead)
			{
				int myAcidiy=bead.getAcid().getAcidity();
				int neighborAcidity=neighbor.getAcid().getAcidity();
				
				if(myAcidiy>0 && neighborAcidity>0)
				{
					AcidBond newBond=new AcidBond(bead,neighbor);
					
					if(!bondSet.contains(newBond))
					{
						bondSet.add(newBond);
						
						runningTotal+=(myAcidiy + neighborAcidity);
					}
					
				}
			}
			
			bead=bead.succ;
		}
		
		return runningTotal;
		
	}

}
