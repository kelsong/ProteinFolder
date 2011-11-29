package ece.vt.edu.model;

public class JasonTestBench {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		String E9_testSequence="h,p,h,p,p,h,h,p,h,p,p,h,p,h,h,p,p,h,p,h";
		
		Protein protein=new Protein();
		protein.parseString(E9_testSequence);

		int numTrials=50;
		System.out.println("Beginning trials...");
		for(int trial=0;trial<numTrials;trial++)
		{
			Lattice twoD=new Lattice(true,100,true);

			HHRule rule=new HHRule();

			BestMoveFirst alg=new BestMoveFirst();
			
			alg.fold(protein, rule, twoD);
		}
	}

}
