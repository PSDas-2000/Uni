/**
 * 
 */
package ha02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Param
 *
 */
public class MyHashSet<K> {

	private Map<Integer, List<K>> map;
	private int max_elem;
	private int current_elem;
	private double fgrad;

	public MyHashSet() {
		map = new HashMap<>();
		max_elem = 10;
		current_elem = 0;
		fgrad = 0;
	}

	/**
	 * Adds <code>element</code> to this instance of MyHashSet if the element is unique <b>and</b> not null.
	 * 
	 * @param element
	 * @throws IllegalArgumentException if <code>element</code> is <code>null</code>
	 * @return 	true; if <code>element</code> is added successfully <br>
	 * 			false; if <code>element</code> is already present
	 */
	public boolean add(K element) {
		if (element == null)
			throw new IllegalArgumentException();

		if (fgrad > 2) {
			doubleMaxAndUpdate();
		}

		int index = element.hashCode() % max_elem;
		List<K> subList = map.get(index);

		if (subList == null)
			subList = new ArrayList<>();

		if (subList.contains(element))
			return true;

		subList.add(element);
		map.put(index, subList);
		current_elem++;
		fgrad = current_elem / max_elem;
		return false;
	}

	//Secondary method
	private void doubleMaxAndUpdate() {
		int oldMax = max_elem;
		Map<Integer, List<K>> oldMap = map;

		map = new HashMap<>();
		max_elem *= 2;
		fgrad = current_elem / max_elem;

		List<K> subList = null;

		for (int i = 0; i < oldMax; i++) {
			subList = oldMap.get(i);
			if (subList != null) {
				for (K k : subList)
					this.add(k);
			}
		}
	}

	/**
	 * Removes <code>element</code> (if present) and returns <code>true</code>. Returns <code>false</code>, if <code>element</code> isn't found or is null.
	 * @param element
	 * @return 	true; element deleted <br>
	 * 			false; element not found or null
	 */
	public boolean delete(K element) {
		if (element == null)
			return false;

		int index = element.hashCode() % max_elem;
		List<K> lst = map.get(index);
		if (lst == null)
			return false;

		if (lst.contains(element)) {
			lst.remove(element);
			current_elem--;
			return true;
		} else
			return false;
	}

	/**
	 * Checks whether <code>element</code> is present in this instance of MyHashSet. Returns <code>true</code>, if <code>element</code> is present
	 * and <code>false</code>, if <code>element</code> is <code>null</code> <b>or</b> isn't present.
	 * 
	 * @param element
	 * @return 	true; <code>element</code> present <br>
	 * 			false; <code>element</code> absent or <code>null</code> 
	 */
	public boolean contains(K element) {
		if (element == null)
			return false;

		int index = element.hashCode() % max_elem;
		List<K> lst = map.get(index);
		if (lst == null)
			return false;

		return lst.contains(element);
	}

	/**
	 * Returns all elements that are stored in this instance of MyHashSet in the form of an
	 * ArrayList whose generic type is <code>K</code>.
	 * 
	 * @return all elements in form of an ArrayList
	 */
	public ArrayList<K> getElements() {
		ArrayList<K> list = new ArrayList<>();
		List<K> sublist = null;
		for (int i = 0; i < max_elem; i++) {
			sublist = map.get(i);
			if (sublist != null) {
				for (K k : sublist) {
					list.add(k);
				}
			}
		}
		return list;
	}
}
