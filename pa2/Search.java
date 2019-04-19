/*Guangyang Chen 
 * 1518931
 * gchen34@ucsc.edu
 * Programming Assignment 2 -- Search.java*/
import java.io.*;
import java.util.Scanner;

public class Search {
	public static void mergeSort(String[] word, int[] lineNumber, int p, int r) {
	//Sort a string array rather than a integer array, lineNumber here as well since we need to locate the line number
		int q;
		if (p < r) {
			q = (p + r) / 2;
			mergeSort(word, lineNumber, p, q);
			mergeSort(word, lineNumber, q + 1, r);
			merge(word, lineNumber, p, q, r);
		}
	}

	public static void merge(String[] word, int[] lineNumber, int p, int q, int r) {
	//Merge sorted string arrays
		int n1 = q - p + 1;
		int n2 = r - q;
		String[] L = new String[n1];
		String[] R = new String[n2];
		int[] L1 = new int[n1];
		int[] R1 = new int[n2];
		int i, j, k;
		
		//Word
		for (i = 0; i < n1; i++)
			L[i] = word[p + i];
		for (j = 0; j < n2; j++)
			R[j] = word[q + j + 1];
		
		//Line number
		for (i = 0; i < n1; i++)
			L1[i] = lineNumber[p + i];
		for (j = 0; j < n2; j++)
			R1[j] = lineNumber[q + j + 1];

		i = 0;
		j = 0;

		for (k = p; k <= r; k++) {
			if (i < n1 && j < n2) {
				if (L[i].compareTo(R[j]) < 0) {
					word[k] = L[i];
					lineNumber[k] = L1[i];
					i++;
				} else {
					word[k] = R[j];
					lineNumber[k] = R1[j];
					j++;
				}
			} else if (i < n1) {
				word[k] = L[i];
				lineNumber[k] = L1[i];
				i++;
			} else {
				word[k] = R[j];
				lineNumber[k] = R1[j];
				j++;
			}
		}

	}
public static int binarySearch(String[] word, int p, int r, String target) {
		//Search traget word in the string arrays
		int q;
		if (p > r) {
			return -1;
		} else {

			q = (p + r) / 2;

			if (target.compareTo(word[q]) == 0) {
				return q;

			} else if (target.compareTo(word[q]) < 0) {
				return binarySearch(word, p, q - 1, target);

			} else {
				return binarySearch(word, q + 1, r, target);
			}
		}
	}

	public static void main(String[] args) throws IOException {
	//Main method	
	Scanner in = null;
	if (args.length < 2) {
			System.err.println("Usage: Search file target1 [target2..]");
			System.exit(1);
		}

		//Read file
		in = new Scanner(new File(args[0]));
		in.useDelimiter("\\Z");// matches the end of file character
		String s = in.next();// read in whole file as a single String
		in.close();
		String[] word = s.split("\n"); // split s into individual lines
		int[] lineNumber = new int[word.length];
		// lineNumber[k] = words[k]
		for (int i = 0; i < word.length; i++) {
			lineNumber[i] = i + 1;//Count from line one
		}
		
		//Merge Sort
		mergeSort(word, lineNumber, 0, word.length - 1);
		
		//Binary Search
		for (int i = 1; i < args.length; i++) {
			if (binarySearch(word, 0, word.length - 1, args[i]) > -1) {
				System.out.println(
						args[i] + " found on line " + lineNumber[binarySearch(word, 0, word.length - 1, args[i])]);
			} else
				System.out.println(args[i] + " not found");
		}
	}
}
