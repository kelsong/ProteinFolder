package ece.vt.edu.model;

import java.util.Random;
import java.util.ArrayList;

public class Lattice {
	int lattice_dim; // by default using 2D, this need to works first before
	                 // trying to make 3D happen.
	int lattice_size = 10; // size of the lattice.
	char lattice_type; // can be a triangle or a square lattice. I will try to
	                   // implement both.
	public static final int SQUARE_THETA = 90;
	public static final int TRIANGLE_THETA = 60;
	public static final int DISTANCE = 3; // filler number, please don't keep
	                                      // this.
	LatticeSite[] lattice;
	LatticeBead head;
	
	public Lattice() {
		lattice = new LatticeSite[lattice_size * lattice_size];
		lattice_type = 1; // square
	}
	
	public Lattice(boolean two_d, int size, boolean square) {
		if (two_d) {
			lattice_dim = 2;
			lattice_size = size;
			lattice = new LatticeSite[lattice_size * lattice_size]; // x*lattice_size
			                                                        // + y
			for (int i = 0; i < lattice_size; i++) {
				for (int j = 0; j < lattice_size; j++) {
					GridLocation temp = new GridLocation(i, j);
					lattice[i * lattice_size + j] = new LatticeSite(temp);
				}
			}
			if (square) {
				lattice_type = 1;
			} else {
				lattice_type = 2;
			}
		} else {
			lattice_dim = 3;
			lattice_size = size;
			lattice = new LatticeSite[lattice_size * lattice_size * lattice_size]; // x*lattice_size*lattice_size +
			                                                                       // y*lattice_size + z
			if (square) {
				lattice_type = 1;
			} else {
				lattice_type = 2;
			}
		}
	}
	
	public boolean isLattice2D()
	{
		return true;
	}
	
	public LatticeSite getLatticeSite(int x, int y, int z)
	{
		if(isLattice2D())
		{
			return lattice[x * lattice_size + y];
		}
		else
		{
			return lattice[x * lattice_size * lattice_size + y * lattice_size + z];
		}
	}
	
	public void initializeBeadChain(Protein init) {
	}
	
	//throw out most of this
	public void initializeLattice() {
		// generate a self-avoiding walk
		Random gen = new Random();
		int x,y,z;
		
		LatticeBead current = head;
		if (current != null) {
			// start walk
			if (lattice_dim == 2) {
				x = gen.nextInt(lattice_size);
				y = gen.nextInt(lattice_size);

				LatticeSite temp = lattice[x * lattice_size + y];
				temp.addBead(current);
				current.setLocation(temp);
			} else {
				x = gen.nextInt(lattice_size);
				y = gen.nextInt(lattice_size);
				z = gen.nextInt(lattice_size);
				
				LatticeSite temp = lattice[x * lattice_size * lattice_size + y * lattice_size + z];
				temp.addBead(current);
				current.setLocation(temp);
			}

		} else {
			//throw an exception here eventually.
			return;
		}

		// should have some backtrack mechanism
		while (current.getNext() != null) {
			// attempt to walk
			ArrayList<GridLocation> adjacent = current.getLocation().getAdjacentSites(true, lattice_size);
			
			current = current.getNext();
			
			int choice = gen.nextInt(adjacent.size());
			
			//get LatticeSite of our choice
			if (lattice_dim == 2) {
				LatticeSite walk_point = lattice[adjacent.get(choice).getX()*lattice_size + adjacent.get(choice).getY()];
				if(walk_point.isFilled()){
					//backtrack, remove the point and try again. 
					// keep track of # of backtracks
				}
				else{
					//should check if this path is blocked, but if the system doesn't work I'll worry about it then
					
				}
			}
		}
	}
	
	// still making decisions on this API
	public void reconfigureWalk() {
	}
	
	public int calculateFreeEnergy() {
		return 0;
	}
	
	public int getDimensions(){
		return lattice_dim;
	}
}
