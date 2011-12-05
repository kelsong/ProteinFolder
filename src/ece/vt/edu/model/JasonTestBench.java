package ece.vt.edu.model;

import java.util.ArrayList;

public class JasonTestBench {
	

	static String global3="h,h,h,h,h,h,h,h"; //optimal energy 3
	static String global9a="h,p,h,p,p,h,h,p,h,p,p,h,p,h,h,p,p,h,p,h"; //optimal energy 9
	static String global9b="h,h,p,p,h,p,p,h,p,p,h,p,p,h,p,p,h,p,p,h,p,p,h,h"; //optimal energy 9
	static String global8="p,p,h,p,p,h,h,p,p,p,p,h,h,p,p,p,p,h,h,p,p,p,p,h,h"; //optimal energy 8
	static String global14="P,P,P,H,H,P,P,H,H,P,P,P,P,P,H,H,H,H,H,H,H,P,P,H,H,P,P,P,P,H,H,P,P,H,P,P"; //optimal energy 14
	static String global23="P,P,H,P,P,H,H,P,P,H,H,P,P,P,P,P,H,H,H,H,H,H,H,H,H,H,P,P,P,P,P,P,H,H,P,P,H,H,P,P,H,H,P,P,H,P,P,H,H,H,H,H"; //optimal energy 23
	
	static FoldingAlgorithm algorithm=null;
	static EnergyRule erule=null;
	static DataSet data=null;
	static ThreadManager manager=null;
	
	public static void main(String[] args) 
	{	
		
	
		
		boolean parseSuccess=parseArguments(args);
		
		int bestScore=-1;
		State bestState=null;
		long TIME_LIMIT=120000;
		
		if(manager==null) //single threaded application
		{
			System.out.println("Running Single Threaded Application for "+TIME_LIMIT +" ms");
			
			long startTime=System.currentTimeMillis();
			boolean globalReached=false;
			
			for(int trial=0;(System.currentTimeMillis()-startTime)<=TIME_LIMIT&&!globalReached;trial++)
			{	
				Lattice twoD=new Lattice(true,500,true);
				
				boolean success=algorithm.fold(data.protein, erule, twoD, false);
				
				if(success)
				{
					int finalScore=algorithm.finalScore;
					
					if(finalScore>bestScore)
					{
						bestScore=finalScore;
					}
					
					if(finalScore==data.globalOptimum)
					{
						globalReached=true;
					}
				}
			}
			long totalTime=System.currentTimeMillis()-startTime;
			
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
		}
		else //multithreaded application
		{
			
		}
		
		/*Protein protein=new Protein();
		protein.parseString(global9a);
		int globalScore=9;
		
		RandomThreadManager rand=new RandomThreadManager(new RandomWalk(), protein, new HHRule());
		rand.setGlobalScore(globalScore);
		rand.setNumThreads(10);
		rand.startManager();*/
		
		/*BestMoveFirst alg = new BestMoveFirst();
		HHRule rule=new HHRule();
		
		AntColonyManager ant = new AntColonyManager(alg, protein, rule);
		ant.setGlobalScore(globalScore);
		ant.startManager();*/

		/*int bestScore=-1;
		boolean globalReached=false;
		System.out.println("Beginning trials...");
		
		long startTime=System.currentTimeMillis();
		long TIME_LIMIT = 120000; 
		for(int trial=0;(System.currentTimeMillis()-startTime)<=TIME_LIMIT&&!globalReached;trial++)
		{	
			Lattice twoD=new Lattice(true,500,true);

			HHRule rule=new HHRule();

			BestMoveFirst alg=new BestMoveFirs0t();
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

	private static boolean parseArguments(String[] args) 
	{
		//args format
		//java proteintestbench <alg> <energy rule> <dataset> #<num threads>
		//Algorithms:
		//Single Threaded Algorithms
		//BestMove => BestMoveFirst
		//Random => RandomWalk
		//Exhaustive => Exhaustive Search
		//Multithreaded Algorithms
		//AntColony => BestMoveFirst AntColony
		//MultiRandom => Randomwalk AntColony
		
		//Energy Rules:
		//HHRule => H-H Bond rule
		
		//DataSet
		//global3="h,h,h,h,h,h,h,h"; //optimal energy 3
		//global9a="h,p,h,p,p,h,h,p,h,p,p,h,p,h,h,p,p,h,p,h"; //optimal energy 9
		//global9b="h,h,p,p,h,p,p,h,p,p,h,p,p,h,p,p,h,p,p,h,p,p,h,h"; //optimal energy 9
		//global8="p,p,h,p,p,h,h,p,p,p,p,h,h,p,p,p,p,h,h,p,p,p,p,h,h"; //optimal energy 8
		//global14="P,P,P,H,H,P,P,H,H,P,P,P,P,P,H,H,H,H,H,H,H,P,P,H,H,P,P,P,P,H,H,P,P,H,P,P"; //optimal energy 14
		//global23="P,P,H,P,P,H,H,P,P,H,H,P,P,P,P,P,H,H,H,H,H,H,H,H,H,H,P,P,P,P,P,P,H,H,P,P,H,H,P,P,H,H,P,P,H,P,P,H,H,H,H,H"; //optimal energy 23	
		
		if(args.length<3)
		{
			return false;
		}
		
		//parse protein/dataset
		String sDataSet=args[2];
		if(sDataSet.equals("global3"))
		{
			data=new DataSet(global3, 3);
		}
		else if(sDataSet.equals("global9a"))
		{
			data=new DataSet(global9a,9);
		}
		else if(sDataSet.equals("global9b"))
		{
			data=new DataSet(global9b,9);
		}
		else if(sDataSet.equals("global8"))
		{
			data=new DataSet(global8,8);
		}
		else if(sDataSet.equals("global14"))
		{
			data=new DataSet(global14,14);
		}
		else if(sDataSet.equals("global23"))
		{
			data=new DataSet(global23,23);
		}
		
		//parse rule
		String sRule=args[1];
		if(sRule.equals("HHRule"))
		{
			erule=new HHRule();
		}
		
		//parse number of threads
		int numThreads=1;
		if(args.length==4)
		{
			numThreads=Integer.parseInt(args[3]);
		}
		
		//attempt to parse algorithm
		String sAlgorithm=args[0];
		if(sAlgorithm.equals("BestMove"))
		{
			algorithm=new BestMoveFirst();
		}
		else if(sAlgorithm.equals("Random"))
		{
			algorithm=new RandomWalk();
		}
		else if(sAlgorithm.equals("Exhaustive"))
		{
			algorithm=new ExhaustiveSearch();
		}
		else if(sAlgorithm.equals("AntColony"))
		{
			algorithm=new BestMoveFirst();
			manager=new AntColonyManager(algorithm, data.protein, erule);
			manager.setGlobalScore(data.globalOptimum);
			manager.setNumThreads(numThreads);
		}
		else if(sAlgorithm.equals("MultiRandom"))
		{
			algorithm=new RandomWalk();
			manager=new RandomThreadManager(algorithm,data.protein,erule);
			manager.setGlobalScore(data.globalOptimum);
			manager.setNumThreads(numThreads);
		}
		
		
		return true;
	}
}
