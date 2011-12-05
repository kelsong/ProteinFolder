package ece.vt.edu.model;

public class DataSet 
{
	String ProteinSequence="";
	int globalOptimum=-1;
	Protein protein;
	
	public DataSet(String sProtein, int score)
	{
		ProteinSequence=sProtein;
		
		protein=new Protein();
		protein.parseString(ProteinSequence);
		
		globalOptimum=score;
	}
}
