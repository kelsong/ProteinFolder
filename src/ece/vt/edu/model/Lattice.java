package ece.vt.edu.model;

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
	LatticeBead tail;
	
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
	
	public LatticeSite getLatticeSite(GridLocation loc){
		if(isLattice2D())
		{
			return lattice[loc.getX() * lattice_size + loc.getY()];
		}
		else
		{
			return lattice[loc.getX() * lattice_size * lattice_size + loc.getY() * lattice_size + loc.getZ()];
		}
	}
	
	public ArrayList<LatticeBead> getAdjacentBeads(LatticeBead bead){
		ArrayList<LatticeBead> adjacent_beads = new ArrayList<LatticeBead>();
		
		LatticeSite loc = bead.getLocation();
		ArrayList<GridLocation> adj_loc = loc.getAdjacentSites(true, lattice_size);
		
		for(int i = 0; i < adj_loc.size(); i++){
			LatticeSite adj_temp = getLatticeSite(adj_loc.get(i));
			if(adj_temp.isFilled()){
				adjacent_beads.add(adj_temp.getBead());
			}
		}
		
		return adjacent_beads; 
	}
	
	public LatticeBead getHead()
	{
		return head;
	}
	
	public void initializeBeadChain(Protein init) {
	}
	
	public void initializeLattice(){
	}
	
	// still making decisions on this API
	public void reconfigureWalk() {
	}
	
	public int getDimensions(){
		return lattice_dim;
	}
	
	public boolean placeAcid(AAcid acid, int x, int y, int z)
	{
		LatticeSite site=getLatticeSite(x, y, z);
		
		if(site.isFilled())
			return false;
		
		LatticeBead bead=new LatticeBead(acid);
		site.addBead(bead);
		
		return true;
	}
}
