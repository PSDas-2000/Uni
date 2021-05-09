package ha06;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * Jedes Objekt dieser Klasse dient der Speicherung eines Graphen
 * mit Hilfe von Adjazenzlisten wie in der Vorlesung definiert.<br>
 * Loesung zu algo-h06 im SS 2021.
 */
public class MyGraph extends Graph {
	
	public MyGraph(int[] vertexList) {
		super(vertexList);
	}
	
	public MyGraph(int vertices, int edges) {
		super(vertices, edges);
	}
		
	public ArrayList<Integer> getEdgeList(){
		ArrayList<Integer> lst = new ArrayList<>();
		int v = this.getVertexCount();
		int e = this.getEdgeCount();
		lst.add(v);
		lst.add(e);
		for(int i = 1; i <= v; i++) {
			for(Integer j : this.getAdjacent(i)) {
				lst.add(i);
				lst.add(j);
			}
		}
		return lst;
	}
	
	public ArrayList<Integer> getVertexList(){
		ArrayList<Integer> lst = new ArrayList<>();
		int v = this.getVertexCount();
		int e = this.getEdgeCount();
		lst.add(v);
		lst.add(e);
		ArrayList<Integer> connectedNodes = null;
		for(int i = 1; i <= v; i++) {
			connectedNodes = this.getAdjacent(i);
			lst.add(this.ausgangsgrad(i));
			for(Integer j : connectedNodes) {
				lst.add(j);
			}
		}
		return lst;
	}
	
	public int[][] getAdjacencyMatrix(){
		int v = this.getVertexCount();
		int[][] adjacencyMatrix = new int[v][v];
		for(int i = 1; i <= v; i++) {
			for(Integer j : this.getAdjacent(i)) {
				adjacencyMatrix[i-1][j-1] = 1;
			}
		}
		return adjacencyMatrix;
	}
	
	public ArrayList<Integer> bfs(int start){
		checkStart(start);
		
		ArrayList<Integer> result = new ArrayList<>();
		Deque<Integer> q= new ArrayDeque<>();
		boolean check[] = new boolean[this.getVertexCount() + 1];
		
		q.add(start);
		check[start] = true;
		int	temp = 0;
		while(!q.isEmpty()) {
			temp = q.poll();
			result.add(temp);
			for(Integer x : this.getAdjacent(temp)) {
				if(!check[x]) {
					q.add(x);
					check[x] = true;
				}
			}
		}
		return result;
	}
	
	public ArrayList<Integer> dfs(int start){
		checkStart(start);
		
		ArrayList<Integer> result = new ArrayList<>();
		boolean[] check = new boolean[this.getVertexCount() + 1];
		Deque<Integer> stack = new ArrayDeque<>();
		
		result.add(start);
		stack.add(start);
		check[start] = true;
		boolean b = true;
		int temp = start;
		
		while(!stack.isEmpty()) {
			b = true;
			for(Integer x : this.getAdjacent(temp)) {
				if(!check[x]) {
					result.add(x);
					stack.add(x);
					check[x] = true;
					b = false;
					temp = x;
					break;
				}
			}
			if(b) {
				check[temp] = true;
				temp = stack.pop();
			}
		}
		
		return result;
	}
	
	public ArrayList<Integer> getUnreachableVertices(int start){
		checkStart(start);
		
		Deque<Integer> q= new ArrayDeque<>();
		boolean check[] = new boolean[this.getVertexCount() + 1];
		
		q.add(start);
		check[start] = true;
		int	temp = 0;
		while(!q.isEmpty()) {
			temp = q.poll();
			for(Integer x : this.getAdjacent(temp)) {
				if(!check[x]) {
					q.add(x);
					check[x] = true;
				}
			}
		}
		
		ArrayList<Integer> result = new ArrayList<>();
		for(int i = 1; i < check.length; i++) {
			if(!check[i])
				result.add(i);
		}
		
		return result;
	}
	
	private void checkStart(int start) {
		if(start < 0 || start > this.getVertexCount())
			throw new IllegalArgumentException(String.format("Start node %d could not be found", start));
	}
}

