package ece.vt.edu.model;

import java.util.ArrayList;

public class LatticeSite {
	GridLocation loc;
	
	boolean site_filled; 
	LatticeBead bead;
	
	public LatticeSite(){
		loc = new GridLocation();
	}
	
	public LatticeSite(GridLocation start){
		loc = start;
	}
	
	public GridLocation getGridLocation(){
		return loc;
	}
	
	public void setGridLocation(GridLocation new_loc){
		loc = new_loc;
	}
	
	//TODO: implement the adjacent site
	public ArrayList<GridLocation> getAdjacentSites(boolean square, int edge_bound){
		ArrayList<GridLocation> adjacentSites = loc.getAdjacent(square, edge_bound);
		return adjacentSites;
	}
	
	public boolean isFilled(){
		return site_filled;
	}
	
	public void removeBead(){ 
		bead = null;
		site_filled = false;
	}
	
	public void addBead(LatticeBead new_bead){
		bead = new_bead;
		site_filled = true;
	}
	
	public LatticeBead getBead()
	{
		return bead;
	}
}
