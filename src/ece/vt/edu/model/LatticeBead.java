package ece.vt.edu.model;
// serves as the chain of bonds 

public class LatticeBead {
	LatticeBead pred; 
	LatticeBead succ;
	
	AAcid acid;
	
	LatticeSite location;
	
	public LatticeBead(){
		acid = null;
		pred = null;
		succ = null;
	}
	
	public LatticeBead(AAcid init){
		acid = init;
		pred = null;
		succ = null;
	}
	
	public boolean isHead(){
		return pred == null; 
	}
	
	public boolean isTail(){
		return succ == null;
	}
	
	public void setPredBond(LatticeBead bond){
		pred = bond;
	}
	
	public void setSuccBond(LatticeBead bond){
		succ = bond; 
	}
	
	public LatticeBead getNext(){
		return succ;
	}
	
	public LatticeBead getPrev(){
		return succ;
	}
	
	public void setLocation(LatticeSite new_site){
		location.removeBead();
		new_site.addBead(this);
		location = new_site;
	}
	
	public LatticeSite getLocation(){
		return location;
	}
}
