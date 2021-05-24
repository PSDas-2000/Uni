/**
 * 
 */
package ha08;

import java.util.ArrayList;

/**
 * @author Param
 *
 */
public class DamenProblem {
	
	public static void main(String[] args) {
		damenProblem(4);
	}
	
	public static void damenProblem(int brettgroesse) {
		if(brettgroesse == 1)
			System.out.println("[1]");
		else if(brettgroesse < 4)
			System.out.println("Keine Loesung");
		else {
			ArrayList<Integer> pos = new ArrayList<>();
			damenProblem(pos, brettgroesse, 1);
		}
	}
	
	private static boolean damenProblem(ArrayList<Integer> pos, int brettgroesse,  int spalte) {
		if(pos.size() == brettgroesse) {
			System.out.println(displayCoordinates(pos));
			return true;
		}
		boolean foundValid = false;
		boolean subCheck = false;
		for(int i = 0; i < brettgroesse; i++) {
			if(isValidPosition(i, spalte - 1, pos)) {
				foundValid = true;
				pos.add(spalte - 1, i);
				subCheck = damenProblem(pos, brettgroesse, spalte + 1);
				pos.remove(spalte - 1);
			}
		}
		return foundValid && subCheck;
	}
	
	private static boolean isValidPosition(int zeile, int spalte, ArrayList<Integer> pos) {
		for(int i = 0; i < pos.size(); i++) {
			if(zeile == pos.get(i) || spalte == i || Math.abs(spalte - i) == Math.abs(zeile - pos.get(i)))
				return false;
		}
		return true;
	}
	
	private static String displayCoordinates(ArrayList<Integer> pos) {
		StringBuilder s = new StringBuilder("");
		s.append("[");
		for(int i = 0; i < pos.size(); i++) {
			s.append(String.format("%d, ", pos.get(i) + 1));
		}
		s.deleteCharAt(s.length() - 1);
		s.deleteCharAt(s.length() - 1);
		s.append("]");
		return s.toString();
	}
}
