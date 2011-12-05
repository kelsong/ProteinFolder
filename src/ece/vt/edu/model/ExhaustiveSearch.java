package ece.vt.edu.model;

import java.util.List;

public class ExhaustiveSearch extends FoldingAlgorithm{

	Lattice lattice;
	Protein protein;
	EnergyRule rule;

	State best_found;

	public ExhaustiveSearch()
	{
		lattice=null;
		protein=null;
		rule=null;
		
		best_found=null;
	}
	
	public ExhaustiveSearch(Protein prot, EnergyRule rules) {
		protein = prot;
		rule = rules;
		lattice = new Lattice(true, 100, true);
	}

	public int search() {
		// place initial bead somewhere with lots of room to fold
		GridLocation loc = new GridLocation(50, 50);

		LatticeSite site = lattice.getLatticeSite(loc);

		lattice.placeAcid(protein.getAcid(0), site);

		return recursiveSearch(1, site);
	}
	
	public String toString()
	{
		return "Exhaustive";
	}

	public int recursiveSearch(int index, LatticeSite prev) {
		AAcid amino;
		/*if (index <= 3) {
			System.out.println("Placing Acid #" + index + " ");
		}*/
		if (index < protein.getLength()) {
			amino = protein.getAcid(index);
		} else {
			// System.out.println("got here");
			State temp = new State();
			int fit = rule.scoreLattice(lattice);
			temp.recordState(lattice, fit);
			// System.out.println("got here");
			if (best_found == null) {
				best_found = temp;
			} else if (fit > best_found.getFitness()) {
				best_found = temp;
			}
			return fit;
		}

		List<LatticeSite> neighbors = lattice.getAdjacentSites(prev);

		boolean blocked = true;
		int recurse_best = 0;
		for (int i = 0; i < neighbors.size(); i++) {
			if (!neighbors.get(i).isFilled()) {
				// set blocked
				blocked = false;
				lattice.placeAcid(amino, neighbors.get(i));
				int temp_score = recursiveSearch(index + 1, neighbors.get(i));
				if (temp_score > recurse_best) {
					recurse_best = temp_score;
				}
				lattice.removeLastBead();
			}
		}
		if (blocked)
			return 0;
		else
			return recurse_best;
	}

	public State getBestState() {
		return best_found;
	}

	@Override
	boolean fold(Protein protein_, EnergyRule energy_, Lattice lattice_, boolean restoredState) 
	{
		if(restoredState)
		{
			System.out.println("Exhaustive Search cannot begin from a restored state, you messed up...");
			return false;
		}
		
		lattice=lattice_;
		protein=protein_;
		rule=energy_;
		
		int bestScore=search();
		finalScore=bestScore;
		
		return true;
	}
}