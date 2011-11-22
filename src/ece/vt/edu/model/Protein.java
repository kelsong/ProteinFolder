package ece.vt.edu.model; //THis whole class may be redundant and unnecessary.... let me think about it.
 
import java.util.LinkedList; 

public class Protein {
	LinkedList<AAcid> acids;
	
	public Protein(){
		
	}
	
	public AAcid getAcid(int idx){
		if(acids != null && acids.size() > idx){
			return acids.get(idx);
		}	else {
			return null; 
		}
	}
	
	public void readFile(){
		//need to figure out the file format for primary structures
	}
	
}
