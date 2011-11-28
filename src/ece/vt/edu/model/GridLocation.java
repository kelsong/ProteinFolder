package ece.vt.edu.model;

import java.util.ArrayList;

public class GridLocation {
	boolean two_d; //true by default
	int x, y, z;
	
	
	public GridLocation(){
		two_d = true;
		x = 0;
		y = 0;
		z = 0;
	}
	
	public GridLocation(int x_init, int y_init){
		two_d = true;
		x = x_init;
		y = y_init;
	}
	
	public GridLocation(int x_init, int y_init, int z_init){
		two_d = false;
	}
	
	public void set3DLattice(){
		two_d = true;
	}
	
	public void set2DLattice(){
		two_d = false;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getZ(){
		return z;
	}
	
	//return a list of grid locations adjacent to this location
	public ArrayList<GridLocation> getAdjacent(boolean square, int edge_bound){
		ArrayList<GridLocation> adjacentLocs = new ArrayList<GridLocation>();
		if(square){ //do adjacency for squares
			if(two_d){
				if(x > 0){
					GridLocation temp = new GridLocation(x-1, y);
					adjacentLocs.add(temp);
					if(x < edge_bound-1){
						temp = new GridLocation(x+1, y);
						adjacentLocs.add(temp);
					}
				}
				if(y > 0){
					GridLocation temp = new GridLocation(x, y-1);
					adjacentLocs.add(temp);
					if(y < edge_bound-1){
						temp = new GridLocation(x, y-1);
						adjacentLocs.add(temp);
					}
				}
			} else { //generate 3D adjacent locations
				if(x > 0){
					GridLocation temp = new GridLocation(x-1, y, z);
					adjacentLocs.add(temp);
					if(x < edge_bound-1){
						temp = new GridLocation(x+1, y, z);
						adjacentLocs.add(temp);
					}
				}
				if(y > 0){
					GridLocation temp = new GridLocation(x, y-1, z);
					adjacentLocs.add(temp);
					if(y < edge_bound-1){
						temp = new GridLocation(x, y+1, z);
						adjacentLocs.add(temp);
					}
				}
				if(z > 0){
					GridLocation temp = new GridLocation(x, y, z-1);
					adjacentLocs.add(temp);
					if(z < edge_bound-1){
						temp = new GridLocation(x, y, z+1);
						adjacentLocs.add(temp);
					}
				}
			}
			return adjacentLocs;
		}
		else{ //TODO: adjacency for triangles
			return null;
		}
	}
	
	public boolean equals(GridLocation test){
		if(two_d != test.two_d){
			return false;
		}
		if(two_d){
			if(x == test.getX() && y == test.getY()){
				return true;
			} else {
				return false;
			}
		} else {
			if(x == test.getX() && y == test.getY() && z == test.getZ()){
				return true;
			} else {
				return false;
			}
		}
	}
}
