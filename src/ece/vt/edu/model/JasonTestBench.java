package ece.vt.edu.model;

public class JasonTestBench {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		String testSequence1="h,p,h,p,p,h,h,p,h,p,p,h,p,h,h,p,p,h,p,h"; //optimal energy 9
		String testSequence2="h,h,p,p,h,p,p,h,p,p,h,p,p,h,p,p,h,p,p,h,p,p,h,h"; //optimal energy 9
		
		Protein protein=new Protein();
		protein.parseString(testSequence2);

		int numTrials=50;
		System.out.println("Beginning trials...");
		for(int trial=0;trial<numTrials;trial++)
		{
			Lattice twoD=new Lattice(true,100,true);

			HHRule rule=new HHRule();

			BestMoveFirst alg=new BestMoveFirst();
			//RandomWalk alg=new RandomWalk();
			
			alg.fold(protein, rule, twoD);
		}
	}

}
