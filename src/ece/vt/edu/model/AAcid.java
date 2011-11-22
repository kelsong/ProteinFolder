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
