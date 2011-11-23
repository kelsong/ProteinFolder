package ece.vt.edu.model; //THis whole class may be redundant and unnecessary.... let me think about it.
 
import java.util.LinkedList; 
import java.io.*;
import java.util.Scanner;

import ece.vt.edu.model.AAcid.AcidName;

public class Protein {
	LinkedList<AAcid> acids;
	
	public Protein(){
		
	}
	
	public AAcid getAcid(int idx){
		if(acids != null && acids.size() > idx){
			return acids.get(idx);
		}	else {
			return null; 
		}
	}
	
	public void readFile(String filename) {
		// need to figure out the file format for primary structures
		// just read each line lowercase it and compare it to the names of the proteins
		File protein_file = new File(filename);
		FileReader reader;
		try {
			reader = new FileReader(protein_file);
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found: Aborting");
			return;
		}
		Scanner scan = new Scanner(reader);
		scan.useDelimiter(",");
		while (scan.hasNext()) {
			String argument = scan.next();
			argument.toUpperCase();
			if (argument.equals("ALA")) { // Alanine
				acids.add(new AAcid(AcidName.ALANINE));
			} else if (argument.equals("ARG")) { // Arginine
				acids.add(new AAcid(AcidName.ARGININE));
			} else if (argument.equals("ASP")) { // Asparagine
				acids.add(new AAcid(AcidName.ASPARAGINE));
			} else if (argument.equals("ASA")) { // Aspartic Acid
				acids.add(new AAcid(AcidName.ASPARTIC_ACID));
			} else if (argument.equals("CYS")) { // Cysteine
				acids.add(new AAcid(AcidName.CYSTEINE));
			} else if (argument.equals("GLY")) { // Glycine
				acids.add(new AAcid(AcidName.GLYCINE));
			} else if (argument.equals("GLA")) { // Glutamic Acid
				acids.add(new AAcid(AcidName.GLUTAMIC_ACID));
			} else if (argument.equals("GLU")) { // Glutamine
				acids.add(new AAcid(AcidName.GLUTAMINE));
			} else if (argument.equals("HIS")) { // Histidine
				acids.add(new AAcid(AcidName.HISTIDINE));
			} else if (argument.equals("ISO")) { // Isoleucine
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
			} else if (argument.equals("TRY")) { // Tryptophan
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
		}
	}
}
