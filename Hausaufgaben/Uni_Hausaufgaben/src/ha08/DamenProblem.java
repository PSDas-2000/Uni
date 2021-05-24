/**
 * 
 */
package ha08;

import java.util.ArrayList;

/**
 * @author Param
 * @author Hazem Falah
 * @author Leon Ikinger
 *
 */
public class DamenProblem {
	
	public static void main(String[] args) {
		damenProblem(14);
	}
	
	/**
	 * Diese methode gibt alle Loesungen des Damen-Problems zu einem 
	 * Schachbrett mit der uebergebenen Groesse auf dem Schirm aus.
	 * 
	 * @param brettgroesse
	 */
	public static void damenProblem(int brettgroesse) {
		if(brettgroesse == 1)
			System.out.println("[1]");
		else if(brettgroesse < 4)
			System.out.println("Keine Loesung");
		else {
			ArrayList<Integer> pos = new ArrayList<>();
			damenProblem(pos, brettgroesse);
		}
	}
	
	/**
	 * Die rekursive Hilfsmethode um die Loesung des Damen-Problems zu finden
	 * @param pos
	 * @param brettgroesse
	 */
	private static void damenProblem(ArrayList<Integer> pos, int brettgroesse) {
	// pos.size() entspricht die Spalte, wo wir gerade sind
		if(pos.size()== brettgroesse) {
			System.out.println(pos.toString());
			return;
		}
		for(int i = 1; i <= brettgroesse; i++) {
			if(isValidPosition(i, pos.size(), pos)) {
				pos.add(i);
				damenProblem(pos, brettgroesse);
				pos.remove(pos.size() - 1);
			}
		}
	}
	
	/**
	 * Ueberprueft, ob die Position (<code>zeile</code>, <code>spalte</code>) eine Position ist, wo
	 * eine Damen stehen darf, ohne von anderen Damen geschlagen zu werden.</br></br>
	 * 
	 * Die Positionen von anderen Damen werden durch <code>pos</code> definiert	
	 * 
	 * @param zeile
	 * @param spalte
	 * @param pos
	 * @return
	 */
	private static boolean isValidPosition(int zeile, int spalte, ArrayList<Integer> pos) {
		for(int i = 0; i < pos.size(); i++) {
			if(zeile == pos.get(i) || spalte == i || Math.abs(spalte - i) == Math.abs(zeile - pos.get(i)))
				return false;
		}
		return true;
	}
}
