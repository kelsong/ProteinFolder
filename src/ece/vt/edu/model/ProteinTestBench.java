package ece.vt.edu.model;

public class ProteinTestBench {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
	    	String testSequence1="h,p,h,p,p,h,h,p,h,p,p,h,p,h,h,p,p,h,p,h"; //optimal energy 9
		String testSequence2="h,h,p,p,h,p,p,h,p,p,h,p,p,h,p,p,h,p,p,h,p,p,h,h"; //optimal energy 9
		
		System.out.println("Random Walk Bench...");
		String testString="H,H,P,H,P,P,H";
		Protein protein=new Protein();
		protein.parseString(testSequence1);
		
		//protein.readFile("protein_test.txt");
		
		Lattice twoD=new Lattice(true,100,true);
		
		HHRule rule=new HHRule();
		
		//RandomWalk alg=new RandomWalk();
		BestMoveFirst alg=new BestMoveFirst();
		
		ThreadManager manage = new RandomThreadManager(alg, protein, rule);
		
		manage.startManager();
		//alg.fold(protein, rule, twoD);
	}

}
