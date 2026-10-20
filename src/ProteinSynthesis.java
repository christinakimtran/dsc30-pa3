/*
    Name: Christina Kim Tran
    PID:  A18864701 / TSN: 200349533
 */

import java.util.NoSuchElementException;

/**
 * @author Christina Kim Tran
 * @since 10/18/26
 */
class ProteinSynthesis {
    private static final int CODON_LENGTH = 3;
    private static final String START = "AUG";
    // Creates an RNA by replacing each occurrence of 'T' with 'U'
    // Saves the result in a queue and returns it
    // @throws IllegalArgumentException if the length of the input is not divisible by 3
    public CharQueue transcribeDNA(String dna) {
        if (dna.length() % CODON_LENGTH != 0) {
            throw new IllegalArgumentException();
        }
        CharQueue rna = new CharQueue();
        for (int i = 0; i < dna.length(); i++) {
            char nucleotide = dna.charAt(i);
            rna.enqueue(nucleotide == 'T' ? 'U' : nucleotide);
        }
        return rna;
    }

    public CharQueue translateRNA(CharQueue rna) {
        CharQueue protein = new CharQueue();
        boolean start = false;
        try {
            while (!rna.isEmpty()) {
                String codon = "" + rna.dequeue() + rna.dequeue() + rna.dequeue();
                if (!start) {
                    if (codon.equals(START)) {
                        start = true;
                        protein.enqueue('M');
                    }
                    continue;
                }
                Character aminoAcid = CodonMap.getAminoAcid(codon);
                if (aminoAcid == null) {
                    break;
                }
                protein.enqueue(aminoAcid);
                if (aminoAcid == '*') {
                    break;
                }
            }
        } catch (NoSuchElementException e) {
            if (start) {
                protein.enqueue('-');
            }
        }
        return protein;
    }

}
