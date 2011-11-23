package ece.vt.edu.model;

public class AAcid {
	public static enum AcidType {POLAR, NONPOLAR};
	public static enum AcidName {ALANINE, ARGININE, ASPARAGINE, ASPARTIC_ACID, CYSTEINE, GLUTAMIC_ACID, 
								 GLUTAMINE, GLYCINE, HISTIDINE, ISOLEUCINE, LEUCINE, LYSINE, METHIONINE,
								 PHENYLALANINE, PROLINE, SERINE, THREONINE, TRYPTOPHAN, TYROSINE, VALINE};
	
	AcidName this_acid;
	
	public AAcid(){
		this_acid = null;
	}
	
	public AAcid(AcidName init){
		this_acid = init; 
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
		switch(this_acid){
		//NON-POLAR AMINO ACIDS 
		case ALANINE:
			return 0;
		case GLYCINE:
			return 1;
		case ISOLEUCINE:
			return 2;
		case LEUCINE:
			return 3;
		case METHIONINE:
			return 4;
		case PHENYLALANINE:
			return 5;
		case PROLINE:
			return 6;
		case TRYPTOPHAN:
			return 7;
		case TYROSINE:
			return 8;
		case VALINE:
			return 9;
		case ARGININE:
			return 10;
		case ASPARAGINE:
			return 11;
		case ASPARTIC_ACID:
			return 12;
		case CYSTEINE:
			return 13;
		case GLUTAMIC_ACID:
			return 14;
		case GLUTAMINE:
			return 15;
		case HISTIDINE:
			return 16;
		case LYSINE:
			return 17;
		case SERINE:
			return 18;
		case THREONINE:
			return 19;
		default:
			return 0;
		}
	}
	
	public AcidType getAcidType(){
		switch(this_acid){
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
