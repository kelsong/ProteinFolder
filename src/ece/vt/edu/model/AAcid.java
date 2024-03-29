package ece.vt.edu.model;

public class AAcid {
	public static enum AcidType {POLAR, NONPOLAR};
	public static enum AcidName {ALANINE, ARGININE, ASPARAGINE, ASPARTIC_ACID, CYSTEINE, GLUTAMIC_ACID, 
								 GLUTAMINE, GLYCINE, HISTIDINE, ISOLEUCINE, LEUCINE, LYSINE, METHIONINE,
								 PHENYLALANINE, PROLINE, SERINE, THREONINE, TRYPTOPHAN, TYROSINE, VALINE, HYDROPHOBIC, POLAR};
	
	AcidName acidName;
	AcidType acidType;
	
	static int counter=0;
	public int myID;
	
	public AAcid(){
		acidName = null;
		acidType = null;
		
		myID=counter++;
	}
	
	public AAcid(AcidName init){
		acidName = init;
		
		if(isAcidHydrophobic())
		{
			acidType=AcidType.NONPOLAR;
		}
		else
		{
			acidType=AcidType.POLAR;
		}
		
		myID=counter++;
	}
	
	public AAcid(AcidType init)
	{
		if(init==AcidType.POLAR)
		{
			acidName=AcidName.POLAR;
		}
		else if(init==AcidType.NONPOLAR)
		{
			acidName=AcidName.HYDROPHOBIC;
		}
		
		acidType=init;
		
		myID=counter++;
	}
	
	public boolean isAcidHydrophobic()
	{
		if(getAcidType()==AcidType.NONPOLAR)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public int getAcidity()
	{
		//hydrophobic -> non-polar
		//hydrophalic -> polar
		
		switch(acidName){
		//listing of amino acids based up Hydropahty index on wikipedia 
		case ARGININE:
			return 0;
		case LYSINE:
			return 1;
		case GLUTAMINE:
			return 2;
		case GLUTAMIC_ACID:
			return 3;
		case ASPARAGINE:
			return 4;
		case ASPARTIC_ACID:
			return 5;
		case HISTIDINE:
			return 6;
		case PROLINE:
			return 7;
		case TYROSINE:
			return 8;
		case TRYPTOPHAN:
			return 9;
		case SERINE:
			return 10;
		case THREONINE:
			return 11;
		case GLYCINE:
			return 12;
		case ALANINE:
			return 13;
		case METHIONINE:
			return 14;
		case CYSTEINE:
			return 15;
		case PHENYLALANINE:
			return 16;
		case LEUCINE:
			return 17;
		case VALINE:
			return 18;
		case ISOLEUCINE:
			return 19;
		default:
			return 0;
		}
	}
	
	public AcidType getAcidType(){
		switch(acidName){
			//NON-POLAR AMINO ACIDS 
			case ALANINE:
			case GLYCINE:
			case ISOLEUCINE:
			case LEUCINE:
			case METHIONINE:
			case PHENYLALANINE:
			case PROLINE:
			case TRYPTOPHAN:
			case TYROSINE:
			case VALINE:
			case HYDROPHOBIC:
				return AcidType.NONPOLAR;
			
			case ARGININE:
			case ASPARAGINE:
			case ASPARTIC_ACID:
			case CYSTEINE:
			case GLUTAMIC_ACID:
			case GLUTAMINE:
			case HISTIDINE:
			case LYSINE:
			case SERINE:
			case THREONINE:
			case POLAR:
				return AcidType.POLAR;
				
			default:
				return null;
		}
	}
	
	public String toString()
	{
		switch(acidName){
		//NON-POLAR AMINO ACIDS 
		case ALANINE:
			return "Alanine";
		case GLYCINE:
			return "Glycine";
		case ISOLEUCINE:
			return "Isoleucine";
		case LEUCINE:
			return "Leucine";
		case METHIONINE:
			return "Methionine";
		case PHENYLALANINE:
			return "Phenylalanine";
		case PROLINE:
			return "Proline";
		case TRYPTOPHAN:
			return "Tryptophan";
		case TYROSINE:
			return "Tyrosine";
		case VALINE:
			return "Valine";
		case HYDROPHOBIC:
			return "Hydrophobic";
		case ARGININE:
			return "Arginine";
		case ASPARAGINE:
			return "Asparagine";
		case ASPARTIC_ACID:
			return "Aspartic Acid";
		case CYSTEINE:
			return "Cysteine";
		case GLUTAMIC_ACID:
			return "Glutamic Acid";
		case GLUTAMINE:
			return "Glutamine";
		case HISTIDINE:
			return "Histidine";
		case LYSINE:
			return "Lysine";
		case SERINE:
			return "Serine";
		case THREONINE:
			return "Threonine";
		case POLAR:
			return "Polar";
		default:
			return "Unknown";
		}
	}
}
