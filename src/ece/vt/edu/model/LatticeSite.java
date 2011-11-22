package ece.vt.edu.model;

import java.util.ArrayList;

public class LatticeSite {
	GridLocation loc;
	
	boolean site_filled; 
	LatticeBead slot;
	
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
		slot = null;
		site_filled = false;
	}
	
	public void addBead(LatticeBead new_bead){
		slot = new_bead;
		site_filled = true;
	}
	
	public LatticeBead getBead()
	{
		return slot;
	}
}
