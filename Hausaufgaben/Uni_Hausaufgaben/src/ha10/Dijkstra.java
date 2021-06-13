/**
 * 
 */
package ha10;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Param
 *
 */
public class Dijkstra {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		printDijkstra(new int[] { 4, 1, 2, 2, 1, 4, 5, 2, 4, 1, 2, 3, 4, 3, 1, 1, 4, 3, 1 });
		System.out.println();
		printDijkstra(new int[] { 10, 1, 2, 30, 1, 3, 10, 2, 5, 15, 2, 8, 55, 3, 4, 5, 3, 9, 35, 4, 2, 10, 4, 5, 45, 4,
				6, 10, 5, 3, 20, 5, 7, 15, 5, 9, 25, 6, 7, 5, 7, 10, 20, 8, 10, 15, 9, 8, 10, 9, 10, 30 });
	}

	/**
	 * Die Methode gibt den kleinste Abstand aus dem Knoten 1 zu allen anderen
	 * Knoten mit dem Dijkstra-Algorithmus.
	 * 
	 * @param kanten Kantenliste, deren erstes Element die Anzahl an Knoten ist.
	 *               Darauf folgen für jede Kante jeweils 3 weitere Elemente in der
	 *               Reihenfolge „Ausgangsknoten,Zielknoten, Gewicht“
	 */
	public static void printDijkstra(int[] kanten) {
		// Producing the adjacent matrix
		int[][] adj = new int[kanten[0]][kanten[0]];
		for (int i = 1; i < kanten.length; i += 3) {
			adj[kanten[i] - 1][kanten[i + 1] - 1] = kanten[i + 2];
		}

		//
		int[] d = new int[kanten[0]];
		int[] p = new int[kanten[0]];

		int startKnoten = 1;
		boolean[] check = new boolean[kanten[0]];
		check[startKnoten - 1] = true;

		// Heading line (First line of the output)
		printDijkstra(startKnoten, 0, d, p, true);

		Deque<Integer> q = new ArrayDeque<Integer>();
		q.add(startKnoten);

		int smallestKnoten = 0;
		int tempKnoten = 0;
		int baseDistance = 0;

		while (!q.isEmpty()) {
			tempKnoten = q.poll();
			for (int i = 0; i < d.length; i++) {
				if (adj[tempKnoten - 1][i] > 0 && i != startKnoten - 1) {
					if (baseDistance + adj[tempKnoten - 1][i] < d[i] || d[i] == 0) {
						d[i] = baseDistance + adj[tempKnoten - 1][i];
						p[i] = tempKnoten;
					}
				}
			}
			printDijkstra(startKnoten, tempKnoten, d, p, false);
			smallestKnoten = getSmallestKnoten(d, check);
			if (smallestKnoten != -1) {
				check[smallestKnoten - 1] = true;
				q.add(smallestKnoten);
				baseDistance = d[smallestKnoten - 1];
			}
		}
	}

	/**
	 * Die methode gibt den Knoten zurueck, der den kleinsten Abstandswert hat und
	 * bisher noch nicht in die Hauptmethode (als lokaler Startknoten) benutzt
	 * wurde.
	 * 
	 * @param d
	 * @param check
	 * @return der Knoten mit kleinstem Abstand
	 */
	private static int getSmallestKnoten(int[] d, boolean[] check) {
		int smallestDistance = Integer.MAX_VALUE;
		int smallestKnoten = -1;
		for (int i = 0; i < d.length; i++) {
			if (!check[i] && smallestDistance > d[i] && d[i] != 0) {
				smallestDistance = d[i];
				smallestKnoten = i + 1;
			}
		}
		return smallestKnoten;
	}

	/**
	 * Die Methode gibt je nach dem Wert von <code>header</code> entweder den
	 * jetzigen Stand der Matrizen <code>d</code> und <code>p</code> aus oder die
	 * Headerzeile fuer die Ausgabe
	 * 
	 * @param startKnoten
	 * @param tempKnoten
	 * @param d
	 * @param p
	 * @param header
	 */
	private static void printDijkstra(int startKnoten, int tempKnoten, int[] d, int[] p, boolean header) {
		StringBuilder s = new StringBuilder();
		if (header) {
			s.append("Vi|");
			for (int i = 0; i < d.length; i++) {
				if (startKnoten - 1 != i) {
					if (i < 9)
						s.append("  " + (i + 1));
					else
						s.append(" " + (i + 1));
				}
			}
			s.append("|");
			for (int i = 0; i < p.length; i++) {
				if (startKnoten - 1 != i) {
					if (i < 9)
						s.append("  " + (i + 1));
					else
						s.append(" " + (i + 1));
				}
			}
			s.append("|");
			System.out.println(s);
			for (int i = 0; i < s.length(); i++) {
				System.out.print("-");
			}
			System.out.println();
		} else {
			if (tempKnoten < 10)
				s.append(" " + tempKnoten + "|");
			else
				s.append(tempKnoten + "|");
			for (int i = 0; i < d.length; i++) {
				if (startKnoten - 1 != i) {
					if (d[i] == 0)
						s.append(" --");
					else if (d[i] == -1)
						s.append("  0");
					else if (d[i] < 10)
						s.append("  " + d[i]);
					else
						s.append(" " + d[i]);
				}
			}
			s.append("|");
			for (int i = 0; i < p.length; i++) {
				if (startKnoten - 1 != i) {
					if (p[i] == 0)
						s.append(" --");
					else if (p[i] < 10)
						s.append("  " + p[i]);
					else
						s.append(" " + p[i]);
				}
			}
			s.append("|");
			System.out.println(s);
		}
	}

}
