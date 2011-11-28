package ece.vt.edu.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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
	//LatticeBead head;
	//LatticeBead tail;
	
	LinkedList<LatticeBead> listofBeads;

	public Lattice() {
		lattice = new LatticeSite[lattice_size * lattice_size];
		lattice_type = 1; // square
		listofBeads = new LinkedList<LatticeBead>();
	}
	
	public Lattice(boolean two_d, int size, boolean square) {
		listofBeads = new LinkedList<LatticeBead>();
		
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
	
	private LatticeSite getLatticeSite(int x, int y, int z)
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
	
	private LatticeSite getLatticeSite(GridLocation loc){
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
	
	public ArrayList<LatticeSite> getAdjacentSites(LatticeBead bead)
	{
		ArrayList<LatticeSite> adjacent_sites = new ArrayList<LatticeSite>();
		
		LatticeSite loc = bead.getLocation();
		ArrayList<GridLocation> adj_loc = loc.getAdjacentSites(true, lattice_size);
		
		for(int i = 0; i < adj_loc.size(); i++){
			LatticeSite adj_temp = getLatticeSite(adj_loc.get(i));
			adjacent_sites.add(adj_temp);
		}
		
		return adjacent_sites;
		
	}
	
	public List<LatticeSite> getAdjacentSites(LatticeSite site)
	{
		ArrayList<LatticeSite> adjacent_sites = new ArrayList<LatticeSite>();
		
		ArrayList<GridLocation> adj_loc = site.getAdjacentSites(true, lattice_size);
		
		if(adj_loc==null)
		{
			System.out.println("No adjacent site found! This cannot happen!");
		}
		
		for(int i = 0; i < adj_loc.size(); i++){
			LatticeSite adj_temp = getLatticeSite(adj_loc.get(i));
			adjacent_sites.add(adj_temp);
		}
		
		return adjacent_sites;
	}
	
	public LatticeSite getRandomSite()
	{
		int x=new Random().nextInt(lattice_size);
		int y=new Random().nextInt(lattice_size);
		int z=new Random().nextInt(lattice_size);
		
		if(isLattice2D())
		{
			return getLatticeSite(x, y, 0);
		}
		else
		{
			return getLatticeSite(x, y, z);
		}
	}
	
	public boolean removeLastBead()
	{
		if(listofBeads.size()==0)
		{
			return false;
		}
		else
		{
			LatticeBead temp = listofBeads.removeLast(); //remove from list
			temp.deleteBead(); //remove site refernce
			temp = null; //remove temp reference, should be available for garbage collection
			return true;
		}
	}
	
	public LatticeBead getHead()
	{
		//return head;
		return listofBeads.get(0);
	}
	
	public void initializeBeadChain(Protein init) {
	}
	
	public void initializeLattice(){
	}
	
	// still making decisions on this API
	public void reconfigureWalk() {
	}
	
	private int getDimensions(){
		return lattice_size;
	}
	
	public LatticeBead placeAcid(AAcid acid, LatticeSite site)
	{
		//create new bead
		LatticeBead newBead=new LatticeBead(acid);
		
		//add site to bead
		newBead.setLocation(site);
		
		//add bead to site
		site.addBead(newBead);
		
		//add bead to list of beads
		listofBeads.add(newBead);
		
		return newBead;
	}
	
	public List<LatticeBead> getListofBeads()
	{
		return listofBeads;
	}
	
//	private LatticeBead placeAcid(AAcid acid, GridLocation loc)
//	{
//		return placeAcid(acid, loc.getX(), loc.getY(), loc.getZ());
//	}
//	
//	private LatticeBead placeAcid(AAcid acid, int x, int y, int z)
//	{
//		LatticeSite site=getLatticeSite(x, y, z);
//		
//		if(site.isFilled())
//			return null;
//		
//		LatticeBead bead=new LatticeBead(acid);
//		site.addBead(bead);
//		
//		listofBeads.add(bead);
//		
//		return bead;
//	}
}
