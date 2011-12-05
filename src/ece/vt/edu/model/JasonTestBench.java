package ece.vt.edu.model;

import java.util.ArrayList;

public class JasonTestBench {
	

	public static void main(String[] args) 
	{
		//args format
		//java proteintestbench <alg> <energy rule> <dataset> #<num threads>
		//Algorithms:
		//Single Threaded Algorithms
		//BestMove => BestMoveFirst
		//Random => RandomWalk
		//Exhaustive => Exhaustive Search
		//Multithreaded Algorithms
		//BestAntColony => BestMoveFirst AntColony
		//RandomAnyColony => Randomwalk AntColony
		
		//Energy Rules:
		//HHRule => H-H Bond rule
		
		//DataSet
		//global3="h,h,h,h,h,h,h,h"; //optimal energy 3
		//global9a="h,p,h,p,p,h,h,p,h,p,p,h,p,h,h,p,p,h,p,h"; //optimal energy 9
		//global9b="h,h,p,p,h,p,p,h,p,p,h,p,p,h,p,p,h,p,p,h,p,p,h,h"; //optimal energy 9
		//global8="p,p,h,p,p,h,h,p,p,p,p,h,h,p,p,p,p,h,h,p,p,p,p,h,h"; //optimal energy 8
		//global14="P,P,P,H,H,P,P,H,H,P,P,P,P,P,H,H,H,H,H,H,H,P,P,H,H,P,P,P,P,H,H,P,P,H,P,P"; //optimal energy 14
		//global23="P,P,H,P,P,H,H,P,P,H,H,P,P,P,P,P,H,H,H,H,H,H,H,H,H,H,P,P,P,P,P,P,H,H,P,P,H,H,P,P,H,H,P,P,H,P,P,H,H,H,H,H"; //optimal energy 23
		
		//parse commandline arugments
		if(args.length<3 || args.length>4)
		{
			System.out.println("Invalid command line arguments...\nExiting...");
			return;
		}
		
		//load up all the datasets. This really should be done from a file but
		//we don't have time for that
		
		String global3="h,h,h,h,h,h,h,h"; //optimal energy 3
		String global9a="h,p,h,p,p,h,h,p,h,p,p,h,p,h,h,p,p,h,p,h"; //optimal energy 9
		String global9b="h,h,p,p,h,p,p,h,p,p,h,p,p,h,p,p,h,p,p,h,p,p,h,h"; //optimal energy 9
		String global8="p,p,h,p,p,h,h,p,p,p,p,h,h,p,p,p,p,h,h,p,p,p,p,h,h"; //optimal energy 8
		String global14="P,P,P,H,H,P,P,H,H,P,P,P,P,P,H,H,H,H,H,H,H,P,P,H,H,P,P,P,P,H,H,P,P,H,P,P"; //optimal energy 14
		String global23="P,P,H,P,P,H,H,P,P,H,H,P,P,P,P,P,H,H,H,H,H,H,H,H,H,H,P,P,P,P,P,P,H,H,P,P,H,H,P,P,H,H,P,P,H,P,P,H,H,H,H,H"; //optimal energy 23
		
		ArrayList<DataSet> datasets = new ArrayList<DataSet>();
		datasets.add(new DataSet(global3,3));
		datasets.add(new DataSet(global9a,9));
		datasets.add(new DataSet(global9b,9));
		datasets.add(new DataSet(global8,8));
		datasets.add(new DataSet(global14,14));
		datasets.add(new DataSet(global23,23));
		
		FoldingAlgorithm algorithm=null;
		EnergyRule erule=null;
		DataSet data=null;

		algorithm=lookupAlgorithm(args[0]);
		erule=lookupEnergyRule(args[1]);
		data=lookupDataSet(args[2]);
		
		int numThreads=1;
		if(args.length==4)
		{
			numThreads=Integer.getInteger(args[3]);
		}
		
		
		Protein protein=new Protein();
		protein.parseString(global9b);
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

	private static DataSet lookupDataSet(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	private static EnergyRule lookupEnergyRule(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	private static FoldingAlgorithm lookupAlgorithm(String sAlgorithm) {
		if(sAlgorithm=="BestMove")
		{
			return new BestMoveFirst();
		}
		else if(sAlgorithm=="Random")
		{
			return new RandomWalk();
		}
		return null;
	}

}
