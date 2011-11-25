package ece.vt.edu.model;

public class AAcid {
	public static enum AcidType {POLAR, NONPOLAR};
	public static enum AcidName {ALANINE, ARGININE, ASPARAGINE, ASPARTIC_ACID, CYSTEINE, GLUTAMIC_ACID, 
								 GLUTAMINE, GLYCINE, HISTIDINE, ISOLEUCINE, LEUCINE, LYSINE, METHIONINE,
								 PHENYLALANINE, PROLINE, SERINE, THREONINE, TRYPTOPHAN, TYROSINE, VALINE};
	
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
		acidName=null;
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
				return AcidType.POLAR;
				
			default:
				return null;
		}
	}
}
