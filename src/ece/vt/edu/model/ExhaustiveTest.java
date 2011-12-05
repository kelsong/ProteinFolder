package ece.vt.edu.model;

public class ExhaustiveTest {
    public static void main(String[] args) {
	String testSequence1 = "h,p,h,p,p,h,h,p,h,p,p,h,p,h,h,p,p,h,p,h"; // optimal
									  // energy
									  // 9
	System.out.println("Exhaustive Search Bench...");
	String testString = "H,H,P,H,P,P,H";
	String testString2 = "H,H,H,H,H,H,H,H,H,H,H,H,H,H,H";
	
	Protein protein = new Protein();
	protein.parseString(testSequence1);

	System.out.println("Protein Length: "  + protein.getLength());
	// protein.readFile("protein_test.txt");

	Lattice twoD = new Lattice(true, 100, true);

	HHRule rule = new HHRule();
	
	ExhaustiveSearch search = new ExhaustiveSearch(protein, rule);
	
	int best_score = search.search();
	
	System.out.println("Best score: " + best_score);
	search.getBestState().printState();
    }
}
