package ece.vt.edu.model;

import java.util.ArrayList;

public class JasonTestBench {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		String global9a="h,p,h,p,p,h,h,p,h,p,p,h,p,h,h,p,p,h,p,h"; //optimal energy 9
		String global9b="h,h,p,p,h,p,p,h,p,p,h,p,p,h,p,p,h,p,p,h,p,p,h,h"; //optimal energy 9
		String global4="h,h,h,h,p";
		String global8="p,p,h,p,p,h,h,p,p,p,p,h,h,p,p,p,p,h,h,p,p,p,p,h,h";
		String global14="P,P,P,H,H,P,P,H,H,P,P,P,P,P,H,H,H,H,H,H,H,P,P,H,H,P,P,P,P,H,H,P,P,H,P,P";
		String global23="P,P,H,P,P,H,H,P,P,H,H,P,P,P,P,P,H,H,H,H,H,H,H,H,H,H,P,P,P,P,P,P,H,H,P,P,H,H,P,P,H,H,P,P,H,P,P,H,H,H,H,H";
		
//		BestMoveFirst alg=new BestMoveFirst();
//		//RandomWalk alg = new RandomWalk();
//		
//		Protein protein=new Protein();
//		protein.parseString(global42);
//		
//		HHRule rule=new HHRule();
		
		/*AntColonyManager antManager = new AntColonyManager(alg, protein, rule);
		
		antManager.startManager();*/
		
		Protein protein=new Protein();
		protein.parseString(global23);

		int numTrials=100;
		System.out.println("Beginning trials...");
		for(int trial=0;trial<numTrials;trial++)
		{
			Lattice twoD=new Lattice(true,500,true);

			HHRule rule=new HHRule();

			BestMoveFirst alg=new BestMoveFirst();
			//RandomWalk alg=new RandomWalk();
			
			alg.fold(protein, rule, twoD,false);
		}
	}

}
