package ece.vt.edu.montecarlo;

import java.util.ArrayList;

public class AbstractLattice {
    
    ArrayList<MoveDir2D> chain_2d;
    ArrayList<MoveDir3D> chain_3d;
    
    boolean two_d = true; 
    
    public AbstractLattice(){
	two_d = true;
	chain_2d = new ArrayList<MoveDir2D>();
    }
    
    public AbstractLattice(boolean dim2){
	if(dim2){
	    two_d = true;
	    chain_2d = new ArrayList<MoveDir2D>();
	} else {
	    two_d = false;
	    chain_3d = new ArrayList<MoveDir3D>();
	}
    }
    
    public boolean validChain(){
	
    }
    
    public void attemptChange(int chain_item, MoveDir){
	
    }
}

abstract class MoveDir(){
    
}

class MoveDir2D extends MoveDir{
    
}

class MoveDir3D extends MoveDir{
    
}