/**
 * 
 */
package ha09;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Param
 *
 */
public class Balkenwage {

	public static final int[] g = { 1, 3, 8, 20 };

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String input = null;
		int n = 0;
		while (true) {
			System.out.print("Gewicht des Artikels: ");
			input = in.nextLine();
			try {
				n = Integer.parseInt(input);
				break;
			} catch (Exception e) {
				System.out.println("Bitte geben sie eine Nummer ein!");
			}
		}
		printCombinations(n);
		in.close();
	}

	public static void printCombinations(int n) {
		printCombinations(new ArrayList<>(), n, 0, 0);
	}

	private static void printCombinations(ArrayList<Integer> lst, int n, int sum, int gPos) {
		if (sum == n || sum == -1 * n) {
			printCombinations(lst);
			return;
		}

		if (gPos >= g.length)
			return;

		if (!(lst.contains(g[gPos]) || lst.contains(-1 * g[gPos]))) {

			// Linke Seite
			lst.add(g[gPos]);
			printCombinations(lst, n, sum + g[gPos], gPos + 1);
			lst.remove(lst.size() - 1);

			// Mitte
			printCombinations(lst, n, sum, gPos + 1);

			// Rechte Seite
			lst.add(-1 * g[gPos]);
			printCombinations(lst, n, sum - g[gPos], gPos + 1);
			lst.remove(lst.size() - 1);

		}
	}

	private static void printCombinations(ArrayList<Integer> lst) {
		StringBuilder s = new StringBuilder("(");
		for (int i : lst) {
			if (i != 0) {
				s.append(i);
				s.append(", ");
			}
		}
		if (s.length() >= 2) {
			s.deleteCharAt(s.length() - 1);
			s.deleteCharAt(s.length() - 1);
		}
		s.append(")");
		System.out.println(s);
	}
}
