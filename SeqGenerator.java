/*
Kristen Niekamp
Lab 3 - Dynamic Programming, Longest Common Subsequence

This class generates random sequences of A/G/C/T.
User inputs the number of sequences and is prompted for the
desired length of each one.
*/

import java.io.*;
import java.util.*;

class SeqGenerator {
  public static void main(String[] args) throws IOException {
    if (args.length != 2) {
      System.out.println("Usage: java SeqGenerator outputFile.txt [int]numbersOfSequences");
    }

    FileWriter outputStream = new FileWriter(args[0],true);
    BufferedWriter output = new BufferedWriter(outputStream);
    Scanner scan = new Scanner(System.in);

    int numOfSeqs = Integer.parseInt(args[1]);
    int j = 0;
    int length;
    int min = 1;
    int max = 4;
    int range = max - min + 1;
    double val;
    String input;

    while (j < numOfSeqs){
      System.out.println("Length of sequence " + (j+1) + ": ");
      while(!scan.hasNextInt()) {//input must be integer
        System.out.println("Input must be an integer.");
      }
      length = (int)scan.nextInt();//user inputs length of each sequence
      for (int i = 0; i < length; i++) {
        val = (int)(Math.random() * range + min);//calculate int in range 1-4
        if (val == 1) {//value of random int determines character in sequence
          output.write("A");
        }
        else if (val == 2) {
          output.write("T");
        }
        else if (val == 3) {
          output.write("G");
        }
        else {
          output.write("C");
        }
      }
      output.newLine();
      j++;
    }
    output.close();
  }//end main
}//end class
