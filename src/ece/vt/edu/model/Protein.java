package ece.vt.edu.model; //THis whole class may be redundant and unnecessary.... let me think about it.
 
import java.util.LinkedList; 
import java.io.*;
import java.util.Scanner;

import ece.vt.edu.model.AAcid.AcidName;
import ece.vt.edu.model.AAcid.AcidType;

public class Protein {
	LinkedList<AAcid> acids;
	
	public Protein()
	{
		acids = new LinkedList<AAcid>();
	}
	
	public AAcid getAcid(int idx){
		if(acids != null && acids.size() > idx){
			return acids.get(idx);
		}	else {
			return null; 
		}
	}
	
	public int getLength()
	{
		return acids.size();
	}
	
	public String toString()
	{
		String s="";
		for(AAcid acid : acids)
		{
			s+=acid+",";
		}
		s+="\n";
		
		return s;
	}
	
	//need this function because I can't get readFile to work
	public void parseString(String proteinStr)
	{
	
		/* 
		 * Use three letter amino acid code from 
		 * http://en.wikipedia.org/wiki/Amino_acid#Table_of_standard_amino_acid_abbreviations_and_properties
		 */
		
		Scanner scan = new Scanner(proteinStr);
		scan.useDelimiter(",");
		while (scan.hasNext()) {
			String argument = scan.next();
			argument = argument.trim();
			argument = argument.toUpperCase();
			//System.out.println(argument);
			if (argument.equals("ALA")) 
			{ // Alanine
				acids.add(new AAcid(AcidName.ALANINE));
			} 
			else if (argument.equals("ARG")) { // Arginine
				acids.add(new AAcid(AcidName.ARGININE));
			} else if (argument.equals("ASN")) { // Asparagine
				acids.add(new AAcid(AcidName.ASPARAGINE));
			} else if (argument.equals("ASP")) { // Aspartic Acid
				acids.add(new AAcid(AcidName.ASPARTIC_ACID));
			} else if (argument.equals("CYS")) { // Cysteine
				acids.add(new AAcid(AcidName.CYSTEINE));
			} else if (argument.equals("GLY")) { // Glycine
				acids.add(new AAcid(AcidName.GLYCINE));
			} else if (argument.equals("GLU")) { // Glutamic Acid
				acids.add(new AAcid(AcidName.GLUTAMIC_ACID));
			} else if (argument.equals("GLN")) { // Glutamine
				acids.add(new AAcid(AcidName.GLUTAMINE));
			} else if (argument.equals("HIS")) { // Histidine
				acids.add(new AAcid(AcidName.HISTIDINE));
			} else if (argument.equals("ILE")) { // Isoleucine
				acids.add(new AAcid(AcidName.ISOLEUCINE));
			} else if (argument.equals("LEU")) { // Leucine
				acids.add(new AAcid(AcidName.LEUCINE));
			} else if (argument.equals("LYS")) { // Lysine
				acids.add(new AAcid(AcidName.LYSINE));
			} else if (argument.equals("MET")) { // Methionine
				acids.add(new AAcid(AcidName.METHIONINE));
			} else if (argument.equals("PHE")) { // Phenylalanine
				acids.add(new AAcid(AcidName.PHENYLALANINE));
			} else if (argument.equals("PRO")) { // Proline
				acids.add(new AAcid(AcidName.PROLINE));
			} else if (argument.equals("TRP")) { // Tryptophan
				acids.add(new AAcid(AcidName.TRYPTOPHAN));
			} else if (argument.equals("TYR")) { // Tyrosine
				acids.add(new AAcid(AcidName.TYROSINE));
			} else if (argument.equals("SER")) { // Serine
				acids.add(new AAcid(AcidName.SERINE));
			} else if (argument.equals("THR")) { // Threonine
				acids.add(new AAcid(AcidName.THREONINE));
			} else if (argument.equals("VAL")) { // Valine
				acids.add(new AAcid(AcidName.VALINE));
			}
			else if(argument.equals("H")) //simple cases of using globular molecules
			{
				acids.add(new AAcid(AcidType.NONPOLAR));
			}
			else if(argument.equals("P"))//simple cases of using globular molecules
			{
				acids.add(new AAcid(AcidType.POLAR));
			} else {
				System.out.println("No Match");
			}
		}
	}
	
	public void readFile(String filename) {
		// need to figure out the file format for primary structures
		File protein_file = new File(filename);
		
		System.out.println("Load: "+protein_file.getAbsolutePath());
		
		/*TODO
		* This file read doesn't seem to work. The file is in the proper path but 
		* still get "File Not Found" exception
		*/
		
		FileReader reader;
		try {
			reader = new FileReader(protein_file);
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found: Aborting");
			return;
		}
		
		/* 
		 * Use three letter amino acid code from 
		 * http://en.wikipedia.org/wiki/Amino_acid#Table_of_standard_amino_acid_abbreviations_and_properties
		 */
		
		Scanner scan = new Scanner(reader);
		scan.useDelimiter(",");
		while (scan.hasNext()) {
			String argument = scan.next();
			argument = argument.trim();
			argument = argument.toUpperCase();
			//System.out.println(argument);
			if (argument.equals("ALA")) 
			{ // Alanine
				acids.add(new AAcid(AcidName.ALANINE));
			} 
			else if (argument.equals("ARG")) { // Arginine
				acids.add(new AAcid(AcidName.ARGININE));
			} else if (argument.equals("ASN")) { // Asparagine
				acids.add(new AAcid(AcidName.ASPARAGINE));
			} else if (argument.equals("ASP")) { // Aspartic Acid
				acids.add(new AAcid(AcidName.ASPARTIC_ACID));
			} else if (argument.equals("CYS")) { // Cysteine
				acids.add(new AAcid(AcidName.CYSTEINE));
			} else if (argument.equals("GLY")) { // Glycine
				acids.add(new AAcid(AcidName.GLYCINE));
			} else if (argument.equals("GLU")) { // Glutamic Acid
				acids.add(new AAcid(AcidName.GLUTAMIC_ACID));
			} else if (argument.equals("GLN")) { // Glutamine
				acids.add(new AAcid(AcidName.GLUTAMINE));
			} else if (argument.equals("HIS")) { // Histidine
				acids.add(new AAcid(AcidName.HISTIDINE));
			} else if (argument.equals("ILE")) { // Isoleucine
				acids.add(new AAcid(AcidName.ISOLEUCINE));
			} else if (argument.equals("LEU")) { // Leucine
				acids.add(new AAcid(AcidName.LEUCINE));
			} else if (argument.equals("LYS")) { // Lysine
				acids.add(new AAcid(AcidName.LYSINE));
			} else if (argument.equals("MET")) { // Methionine
				acids.add(new AAcid(AcidName.METHIONINE));
			} else if (argument.equals("PHE")) { // Phenylalanine
				acids.add(new AAcid(AcidName.PHENYLALANINE));
			} else if (argument.equals("PRO")) { // Proline
				acids.add(new AAcid(AcidName.PROLINE));
			} else if (argument.equals("TRP")) { // Tryptophan
				acids.add(new AAcid(AcidName.TRYPTOPHAN));
			} else if (argument.equals("TYR")) { // Tyrosine
				acids.add(new AAcid(AcidName.TYROSINE));
			} else if (argument.equals("SER")) { // Serine
				acids.add(new AAcid(AcidName.SERINE));
			} else if (argument.equals("THR")) { // Threonine
				acids.add(new AAcid(AcidName.THREONINE));
			} else if (argument.equals("VAL")) { // Valine
				acids.add(new AAcid(AcidName.VALINE));
			}
			else if(argument.equals("H")) //simple cases of using globular molecules
			{
				acids.add(new AAcid(AcidType.NONPOLAR));
			}
			else if(argument.equals("P"))//simple cases of using globular molecules
			{
				acids.add(new AAcid(AcidType.POLAR));
			} else {
				System.out.println("No Match");
			}
		}
	}
}
