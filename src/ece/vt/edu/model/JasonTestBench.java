package ece.vt.edu.model;

public class JasonTestBench {

	public static void main(String[] args) 
	{
		String global3="h,h,h,h,h,h,h,h"; //optimal energy 3
		String global9a="h,p,h,p,p,h,h,p,h,p,p,h,p,h,h,p,p,h,p,h"; //optimal energy 9
		String global9b="h,h,p,p,h,p,p,h,p,p,h,p,p,h,p,p,h,p,p,h,p,p,h,h"; //optimal energy 9
		String global8="p,p,h,p,p,h,h,p,p,p,p,h,h,p,p,p,p,h,h,p,p,p,p,h,h"; //optimal energy 8
		String global14="P,P,P,H,H,P,P,H,H,P,P,P,P,P,H,H,H,H,H,H,H,P,P,H,H,P,P,P,P,H,H,P,P,H,P,P"; //optimal energy 14
		String global23="P,P,H,P,P,H,H,P,P,H,H,P,P,P,P,P,H,H,H,H,H,H,H,H,H,H,P,P,P,P,P,P,H,H,P,P,H,H,P,P,H,H,P,P,H,P,P,H,H,H,H,H"; //optimal energy 23
		
		Protein protein=new Protein();
		protein.parseString(global9a);
		int globalScore=9;
		
		BestMoveFirst alg = new BestMoveFirst();
		HHRule rule=new HHRule();
		
		AntColonyManager ant = new AntColonyManager(alg, protein, rule);
		ant.setGlobalScore(globalScore);
		ant.startManager();

		/*int bestScore=-1;
		boolean globalReached=false;
		System.out.println("Beginning trials...");
		
		long startTime=System.currentTimeMillis();
		long TIME_LIMIT = 120000; 
		for(int trial=0;(System.currentTimeMillis()-startTime)<=TIME_LIMIT&&!globalReached;trial++)
		{	
			Lattice twoD=new Lattice(true,500,true);

			HHRule rule=new HHRule();

			BestMoveFirst alg=new BestMoveFirst();
			//RandomWalk alg=new RandomWalk();
			
			boolean success=alg.fold(protein, rule, twoD,false);
			
			if(success)
			{
				int finalScore=alg.finalScore;
				if(finalScore>bestScore)
				{
					bestScore=finalScore;
				}
				
				if(finalScore==globalScore)
				{
					globalReached=true;
				}
			}
		}
		
		long endTime=System.currentTimeMillis();
		long totalTime=endTime-startTime;
		
		System.out.println("Trial complete");
		if(globalReached)
		{
			System.out.println("Global Reach in "+totalTime+"ms");
		}
		else
		{
			System.out.println("Global not reached not in "+TIME_LIMIT+"ms");
			System.out.println("Best Score: "+bestScore);
		}
*/	}

}
