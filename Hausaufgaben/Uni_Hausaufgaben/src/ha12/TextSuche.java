/**
 * 
 */
package ha12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.PatternSyntaxException;

/**
 * @author Param
 *
 */
public class TextSuche {

	public static void main(String[] args) {
		System.out.println(textSearch("abcabcdababdc.", "ab")); // 0, 3, 7, 9
		System.out.println(textSearch("abcabcdababdc.", "c.")); // 2, 5, 12
		System.out.println(textSearch("abcabcdababdc.", "c\\.")); //12
		System.out.println(textSearch("abcabcdababdc.", "b[cd]")); //1,4,10
		System.out.println(textSearch("abcabcdababdc.", "a....c")); //0,7
		System.out.println(textSearch("a[aababa][ab]a", "a[ab]a")); //3,5
		System.out.println(textSearch("a[aababa][ab]a", "a.\\[a")); //7
	}

	public static ArrayList<Integer> textSearch(String text, String pattern) {
		HashMap<Integer, ArrayList<Character>> zKlassen = new HashMap<>();
		HashMap<Integer, Character> singularChars = new HashMap<>();
		int[] patternLength = { 0 };
		disintegratePattern(pattern, zKlassen, singularChars, patternLength);
		return compareText(text, zKlassen, singularChars, patternLength);
	}

	private static void disintegratePattern(String pattern, HashMap<Integer, ArrayList<Character>> zKlassen,
			HashMap<Integer, Character> singularChars, int[] patternLength) {
		boolean inZKlasse = false;
		int altIndex = 0;
		int openIndex = -1;
		for (int i = 0; i < pattern.length(); i++) {
			if (inZKlasse) {
				if (pattern.charAt(i) == ']') {
					inZKlasse = false;
					altIndex++;
				} else {
					zKlassen.get(altIndex).add(pattern.charAt(i));
				}
			} else {
				if (pattern.charAt(i) == '\\') {
					singularChars.put(altIndex, pattern.charAt(i + 1));
					i++;
					altIndex++;
				} else if (pattern.charAt(i) == '[') {
					inZKlasse = true;
					zKlassen.put(altIndex, new ArrayList<>());
				} else if (pattern.charAt(i) == '.') {
					zKlassen.put(altIndex, new ArrayList<>());
					altIndex++;
				} else {
					singularChars.put(altIndex, pattern.charAt(i));
					altIndex++;
				}
			}
		}

		if (inZKlasse)
			throw new PatternSyntaxException("Keine schliessende eckige Klammer gefunden", pattern, openIndex);
		else
			patternLength[0] = altIndex;
	}

	private static ArrayList<Integer> compareText(String text, HashMap<Integer, ArrayList<Character>> zKlassen,
			HashMap<Integer, Character> singularChars, int[] patternLength) {
		ArrayList<Integer> indices = new ArrayList<>();
		boolean test;
		for (int i = 0; i <= text.length() - patternLength[0]; i++) {
			test = true;
			for (int j = 0; j < patternLength[0]; j++) {
				if (singularChars.get(j) == null) {
					if (!(zKlassen.get(j).size() == 0 || zKlassen.get(j).contains(text.charAt(i + j)))) {
						test = false;
						break;
					}
				} else {
					if (!(singularChars.get(j) == text.charAt(i + j))) {
						test = false;
						break;
					}
				}
			}
			if (test) {
				indices.add(i);
			}
		}
		return indices;
	}

}
