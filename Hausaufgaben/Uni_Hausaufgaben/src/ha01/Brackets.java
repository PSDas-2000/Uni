/**
 * 
 */
package ha01;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Param
 * Algorithmen
 * Hausaufgabe 01 - Sommersemester	
 */

public class Brackets {
	
	public static final String[] OPENING_BRACKETS = {"(","{","["};
	private static final Map<String, String> BRACKET_MAP = new HashMap<>();

	/**
	 * Ueberprueft, ob <code>s</code> ein valider String ist oder nicht.
	 * Ein String ist valid, wenn:
	 * <ul>
	 * <li>Jede sich oeffnende Klammer wird von einer Klammer gleicher Art wieder geschlossen</li>
	 * <li>Jede sich schliessende Klammer wurde von einer Klammer gleicher Art geoeffnet</li>
	 * <li>Es duerfen keine Ueberschneidungen in der Form [ ( ] ) stattfinden. Um valide zu bleiben, muss
	 * die runde Klammer innerhalb der eckigen Klammern wieder geschlossen werden [ ( ) ]</li>
	 * </ul>
	 *
	 * 
	 * @param s
	 * @return true; valider String
	 * <br>
	 * false; invalider String oder <code>null</code>
	 */
	public static boolean isValid(String s) {
		
		if(s==null)
			return false;
		
		ArrayDeque<String> deq = new ArrayDeque<>();
		
		if(BRACKET_MAP.isEmpty())
			fillBracketMap();
		
		String[] chars = s.split("");
		
		for(String m : chars) {
			if(isOpeningBracket(m))
				deq.add(m);
			else if(BRACKET_MAP.get(m)!=null) {
				if(deq.isEmpty())
					return false;
				
				if(BRACKET_MAP.get(m).equalsIgnoreCase(deq.peekLast()))
					deq.removeLast();
				else
					return false;
			}
		}
		
		return deq.isEmpty();
		
	}
	
	//Hilfsmethode zum Ausfüllen des HashMaps
	/**
	 * Diese Methode wird benutzt, um in dem HashMap die
	 * schliessenden Klammern (key) und die zugehoerigen oeffnenden Klammern (value)
	 * als Key-Value Paare, zu speichern
	 */
	private static final void fillBracketMap() {
		BRACKET_MAP.put("}", "{");
		BRACKET_MAP.put(")", "(");
		BRACKET_MAP.put("]", "[");
	}
	
	
	/**
	 * Ueberprueft, ob <code>s</code> eine oeffnende Klammer ist oder nicht
	 * 
	 * @param 	s
	 * @return 	true; oeffnende
	 * <br>		false; alle andere Zeichen
	 */
	public static final boolean isOpeningBracket(String s) {
		if(s.length()!=1)
			return false;
		
		for(String m: OPENING_BRACKETS) {
			if(m.equalsIgnoreCase(s))
				return true;
		}
		
		return false;
	}

}
