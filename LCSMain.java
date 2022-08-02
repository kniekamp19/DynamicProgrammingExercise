/*
Kristen Niekamp
Lab 3 - Dynamic Programming, Longest Common Subsequence

This class contains the main driver of the program. It checks for errors,
reads the input sequences, and creates the matrix to be used in LCS
computation.
*/

import java.util.*;
import java.io.*;

class LCSMain {

/*
This method is the main driver of the program. It handles initial I/O,
variable creation, and ensures each pair of sequences is analyzed.
*/
  public static void main(String[] args) throws IOException {
    LCSMain lcsm = new LCSMain();
    LongestCommonSub lcs = new LongestCommonSub();

    File inputFile = null;
    Scanner scan = null;
    Scanner countLines = null;
    FileWriter outputStream = null;
    BufferedWriter output = null;
    String[] inputStrs;
    char[] A;
    char[] B;
    char[] longestSub;
    int[][] lcsMat;
    int comps;

    if (args.length != 2) { //error checking
      System.out.println("Usage: java LSCMain inputFile.txt outputFile.txt");
      System.exit(0);
    }

    try {
      inputFile = new File(args[0]);
      scan = new Scanner(inputFile);
      countLines = new Scanner(inputFile);
      outputStream = new FileWriter(args[1],true);
      output = new BufferedWriter(outputStream);
    } catch (IOException ioe) {
        System.out.println("There was an I/O Error: " + ioe.getMessage());
    }

    if (inputFile.length() == 0) { //check that input file is not empty
      System.out.println("The input file is empty. Please provide sequences for processing.");
      output.write("The input file is empty. Please provide sequences for processing.");
      scan.close();
      output.close();
      System.exit(0);
    }

    int numSeqs = lcsm.countLines(countLines);
    inputStrs = lcsm.readInput(scan, numSeqs);

/*
These for loops ensure that each pair of sequences is processed and there
are no duplicates (i.e. 1 and 4 are processed but not 4 and 1).
*/
    for (int i = 0; i < inputStrs.length-1; i++) {
      for (int j = i+1; j < inputStrs.length; j++) {
        A = lcs.stringToChar(inputStrs[i]);
        B = lcs.stringToChar(inputStrs[j]);
        lcsMat = lcsm.makeMatrix(A.length,B.length);
        lcsMat = lcs.getLCSMatrix(A,B,A.length,B.length,lcsMat);
        longestSub = lcs.readMatrix(A,B,lcsMat);
        comps = lcsMat[0][0];
        lcs.printLCS(inputStrs[i],inputStrs[j],longestSub,comps,output);
      }
    }

    output.close();
    scan.close();
  }//end main

  /*
  This method counts the number of lines in the file to create the string
  array containing the input strings
  */
  public int countLines (Scanner readFile) {
    int numLines = 0;
    while (readFile.hasNextLine()) {
      numLines++;
      readFile.nextLine();
    }
    return numLines;
  }

/*
This method reads in the input file and creates a String array
containing each sequence to be processed.
*/
  public String[] readInput(Scanner scan, int numSeqs) throws IOException {
    String[] inputStrs = new String[numSeqs];//string array of input sequences
    int m = 0;
    String currLine;

    while(scan.hasNext()) {
      currLine = scan.nextLine();
      currLine = currLine.replaceAll(" ", ""); //get rid of any spaces in the sequence
      if (!currLine.equals("")) {
        inputStrs[m] = currLine;
      }
      else {
        continue; //start next iteration of loop without increasing m if line is empty
      }
      m++; //increase index of input array
    }

    return inputStrs;
  }//end readInput

/*
This method creates the matrix and initializes all positions to 0.
*/
  public int[][] makeMatrix(int m, int n){
    int[][] lcsMat = new int[m+1][n+1];

    for (int i = 0; i <= m; i++) {//initialize positions to 0
      for (int j = 0; j <= n; j++) {
        lcsMat[i][j] = 0;
      }
    }
    return lcsMat;
  }

}//end class
