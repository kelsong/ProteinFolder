package ece.vt.edu.model;

public class ProteinTestBench {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		String testString="H,H,P,H,P,P,H";
		Protein protein=new Protein();
		protein.parseString(testString);
		
		//protein.readFile("protein_test.txt");
		
		Lattice twoD=new Lattice(true,100,true);
		
		HHRule rule=new HHRule();
		
		BestMoveFirst alg=new BestMoveFirst();
		
		alg.fold(protein, rule, twoD);
	}

}
