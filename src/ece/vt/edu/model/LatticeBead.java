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
	
	public boolean setLocation(LatticeSite new_site){
		if(location==null)
		{
			location=new LatticeSite();
		}
		
		if(new_site.isFilled())
		{
			System.out.println("Site is filled...");
			return false;
		} else {
			location.removeBead();
			new_site.addBead(this);
			location = new_site;
			return true;
		}
	}
	
	public LatticeSite getLocation(){
		return location;
	}
	
	public void deleteBead(){
		if(location!=null){
			location.removeBead();
		}
	}
	
	public AAcid getAcid()
	{
		return acid;
	}
}
