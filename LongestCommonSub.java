/*
Kristen Niekamp
Lab 3 - Dynamic Programming, Longest Common Subsequence

This class performs all the processing work of the input sequences to find
the longest common subsequence of each pair of sequences
*/

import java.io.*;

class LongestCommonSub {
  /*
  This method converts the input strings into character arrays to
  be processed
  */
  public char[] stringToChar(String str) {
    char[] ch = new char[str.length()];

    for (int i = 0; i < str.length(); i++) {
      ch[i] = str.charAt(i);
    }
    return ch;
  }

  /*
  This method processes the char arrays of the input sequences.
  Each char of sequence A is compared to each char of sequence B.
  The matrix is filled out as each pair of characters is compared.
  */
  public int[][] getLCSMatrix(char[] A, char[] B, int m, int n, int[][] lcsMat) {
    int comps = 0;//count comparisons

    for (int i = 0; i <= m; i++) {
      for (int j = 0; j <= n; j++) {
        if (i == 0 || j == 0) {//0s in matrix indicate there are no more common characters
          lcsMat[i][j] = 0; //comparison not counted if (sub)string is empty
        }
        //if the pair of characters from the sequences match, length of lcs increases
        else if (A[i-1] == B[j-1]) {
          comps++;
          lcsMat[i][j] = lcsMat[i-1][j-1] + 1;
        }
        //if the pair of characters don't match, position is largest of left or
        //above positions
        else{
          comps++;
          lcsMat[i][j] = Math.max(lcsMat[i-1][j], lcsMat[i][j-1]);
        }//end if/else
      }//end for j
    }//end for i
    lcsMat[0][0] = comps; //number of comparisons stored to be printed
    return lcsMat;
  }//end getLCSMatrix

  /*
  Matrix is read beginning at matrix[m][n] and following the numbers, decreasing
  until 0 is reached. Characters that match are added to the LCS.
  */
  public char[] readMatrix(char[] A, char[] B, int[][] matrix) {
	  int m = A.length;
	  int n = B.length;
    char[] subseq = new char[matrix[m][n]];//matrix[m][n] value is the length of the LCS
    int ssInd = matrix[m][n]-1;//chars added to LCS backward as matrix is read

    while (m > 0 && n > 0){ //traverse matrix until matrix[m][n] == 0
      if (A[m-1] == B[n-1]) {
        subseq[ssInd] = A[m-1];
        m--;
        n--;
        ssInd--;
      }
      //if chars don't match, follow path in the direction of the larger value
      //left or up
      else if (matrix[m-1][n] > matrix[m][n-1]) {
        m--;
      }
      else {
        n--;
      }
    }//end while
    return subseq;
  }//end readMatrix

  /*
  This method prints the longest common subsequence as well as the input
  sequences and their lengths
  */
  public void printLCS(String A, String B, char[] lcs, int comps, BufferedWriter output) throws IOException{
    output.write("String 1: " + A + "\n");//print input seq A and its length
    output.write("Length of String 1: " + A.length() + "\n");
    output.write("String 2: " + B + "\n");//print input seq B and its length
    output.write("Length of String 2: " + B.length() + "\n\n");
    output.write("Longest Common Subsequence: ");

    if (lcs.length == 0) {//if there is no common subsequence
      output.write("There is no common subsequence for these sequences.");
    }
    for (int i = 0; i < lcs.length; i++) {
      output.write(lcs[i]);//write LCS from char array
    }
    output.write("\nNumber of comparisons: " + comps);
    output.newLine();
    output.newLine();
    output.newLine();
  }


}//end class
