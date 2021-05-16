/**
 * 
 */
package ha07;

/**
 * @author Param
 *
 */
public class PrimAlgo {

	public static void main(String[] args) {
		int[][] adjazenzmatrix = { { 0, 3, 0, 2, 0, 0 }, { 3, 0, 2, 0, 3, 0 }, { 0, 2, 0, 1, 6, 0 },
				{ 2, 0, 1, 0, 0, 0 }, { 0, 3, 6, 0, 0, 5 }, { 0, 0, 0, 0, 5, 0 } };
		System.out.println("resultierende kosten: " + getMST(adjazenzmatrix));
	}

	/**
	 * Calculates and returns the total cost of the minimal spanning tree for the
	 * given Graph (in the form of an adjacency matrix).
	 * 
	 * @param edges the adjacency matrix
	 * @return the total cost of the minimal spanning tree
	 */
	public static int getMST(int[][] edges) {
		int cost = 0;
		boolean[] check = new boolean[edges.length];
		int start[] = {3};
		System.out.println("Waehle 2 als Wurzel");
		check[start[0] - 1] = true;
		int minCostElem = getMinCostElem(edges, check, start);
		while (minCostElem != -1) {
			cost += edges[start[0] - 1][minCostElem - 1];
			System.out.println(String.format("Kante hinzugefuegt von %d zu %d", start[0], minCostElem));
			check[minCostElem - 1] = true;
			minCostElem = getMinCostElem(edges, check, start);
		}
		return cost;
	}

	// Helping method that returns the edge which takes the least cost
	// to travel from fromNode. This edge should also have not been used
	// for the minimal spanning tree before.
	private static int getMinCostElem(int[][] edges, boolean[] check, int[] start) {
		int minCostElem = -1;
		int minCost = 0;
		int fromNode = 0;
		for (int j = 0; j < check.length; j++) {
			if (check[j]) {
				fromNode = j+1;
				for (int i = 0; i < edges.length; i++) {
					if (!check[i]) {
						if (minCost == 0 && edges[fromNode - 1][i]!=0) {
							minCost = edges[fromNode - 1][i];
							minCostElem = i + 1;
							start[0] = fromNode;
						} else if (edges[fromNode - 1][i] != 0 && edges[fromNode - 1][i] < minCost) {
							minCost = edges[fromNode - 1][i];
							minCostElem = i + 1;
							start[0] = fromNode;
						}
					}
				}
			}
		}
		return minCostElem;
	}
}
