/**
 * 
 */
package ha05;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Param
 *
 */
public class Heap {

	private ArrayList<Integer> array;
	private int elems;

	public static void main(String[] args) {
		Heap hp = new Heap();
		hp.add(1);
		System.out.println(hp);
		hp.add(6);
		System.out.println(hp);
		hp.add(8);
		System.out.println(hp);
		hp.add(18);
		System.out.println(hp);
		hp.add(23);
		System.out.println(hp);
		hp.add(5);
		System.out.println(hp);
		hp.add(17);
		System.out.println(hp);
		hp.add(20);
		System.out.println(hp);
		hp.add(26);
		System.out.println(hp);
		hp.add(21);
		System.out.println(hp);
		hp.add(9);
		System.out.println(hp);
		System.out.println();
		while (!hp.isEmpty()) {
			System.out.println(hp.getMax());
			System.out.println(hp);
		}
	}

	public Heap() {
		array = new ArrayList<>();
		array.add(0, null);
		elems = 0;
	}

	public boolean isEmpty() {
		return elems == 0;
	}

	public void add(int i) {
		array.add(elems + 1, i);
		elems++;
		upheap();
	}

	public int getMax() {

		if (isEmpty())
			throw new ArithmeticException("Removal error : Heap is empty");

		int a = array.get(1).intValue();
		Collections.swap(array, elems, 1);
		array.remove(elems);
		elems--;
		downheap();

		return a;
	}

	@Override
	public String toString() {
		return array.subList(1, elems + 1).toString();
	}

	private void upheap() {
		int n = elems;
		while (n > 1 && array.get(n / 2) < array.get(n)) {
			Collections.swap(array, n / 2, n);
			n /= 2;
		}
	}

	private void downheap() {
		if (elems == 0 || elems == 1)
			return;

		if (elems == 2) {
			if (array.get(1) < array.get(2)) {
				Collections.swap(array, 1, 2);
				return;
			}
		}

		int n = 1;
		int left;
		int right;

		while (2 * n <= elems) {
			left = array.get(2 * n);
			// If node has element on both sides
			if (2 * n + 1 <= elems) {
				right = array.get(2 * n + 1);
				if (left > right) {
					if (array.get(n) < left) {
						Collections.swap(array, n, 2 * n);
						n = 2 * n;
					} else
						break;
				} else {
					if (array.get(n) < right) {
						Collections.swap(array, n, 2 * n + 1);
						n = 2 * n + 1;
					} else
						break;
				}
			}
			// If node only has element on the left side
			else {
				if (array.get(n) < left) {
					Collections.swap(array, n, 2 * n);
					n = 2 * n;
				} else
					break;
			}
		}

	}
}
