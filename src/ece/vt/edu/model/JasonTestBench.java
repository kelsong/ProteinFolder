package ece.vt.edu.model;

public class JasonTestBench {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		String testString="H,H,H,H";
		Protein protein=new Protein();
		protein.parseString(testString);
		
		//protein.readFile("protein_test.txt");
		
		Lattice twoD=new Lattice(true,100,true);
		
		HHRule rule=new HHRule();
		
		BestMoveFirst alg=new BestMoveFirst();
		
		alg.fold(protein, rule, twoD);
	}

}
