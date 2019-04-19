/*Guangyang Chen 
1518931
gchen34@ucsc.edu
Programming Assignment 1 -- Recursion.java*/
public class Recursion {
	public static void main(String[] args) {
		int[] A = { -1, 2, 6, 12, 9, 2, -5, -2, 8, 5, 7 };
		int[] B = new int[A.length];
		int[] C = new int[A.length];
		int minIndex = minArrayIndex(A, 0, A.length - 1);
		int maxIndex = maxArrayIndex(A, 0, A.length - 1);
		for (int x : A)
			System.out.print(x + " ");
		System.out.println();
		System.out.println("minIndex = " + minIndex);
		System.out.println("maxIndex = " + maxIndex);
		reverseArray1(A, A.length, B);
		for (int x : B)
			System.out.print(x + " ");
		System.out.println();
		reverseArray2(A, A.length, C);
		for (int x : C)
			System.out.print(x + " ");
		System.out.println();
		reverseArray3(A, 0, A.length - 1);
		for (int x : A)
			System.out.print(x + " ");
		System.out.println();
	}

	static void reverseArray1(int[] X, int n, int[] Y) {
		if (n == 0)
			return;
		Y[X.length - n] = X[n - 1];
		reverseArray1(X, n - 1, Y);
	}

	static void reverseArray2(int[] X, int n, int[] Y) {
		if (n == 0)
			return;
		Y[n - 1] = X[X.length - n];
		reverseArray2(X, n - 1, Y);
	}

	static void reverseArray3(int[] X, int i, int j) {

		if (i >= j)
			return;
		else {
			int y = 0;
			y = X[i];
			X[i] = X[j];
			X[j] = y;
			reverseArray3(X, i + 1, j - 1);
		}
	}

	static int maxArrayIndex(int[] X, int p, int r) {
		int max = X[p];
		int maxIndex = 0;
		int i;
		for (i = p; i <= r; i++) {
			if (X[i] > max) {
				max = X[i];
				maxIndex = i;
			}
		}
		return maxIndex;
	}

	static int minArrayIndex(int[] X, int p, int r) {
		int min = X[p];
		int minIndex = 0;
		int i;
		for (i = p; i <= r; i++) {
			if (X[i] < min) {
				min = X[i];
				minIndex = i;
			}
		}
		return minIndex;
	}

}
