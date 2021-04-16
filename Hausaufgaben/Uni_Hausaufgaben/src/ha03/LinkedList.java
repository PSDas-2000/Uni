/**
 * 
 */
package ha03;


/**
 * @author Param
 */

public class LinkedList implements IList {

	public static void main(String[] args) {
		LinkedList ll = new LinkedList();
		for(int i = 0; i < 10; i++) {
			ll.insertAt(0, i);
		}
		System.out.println("Elements from 0 to 9 added!");
		System.out.println(ll);
		System.out.println("Count: " + ll.getCount());
		System.out.println(ll.toStringRev());
		
		ll.removeAt(0);
		
		System.out.println("First element removed");
		
		System.out.println(ll);
		
		System.out.println(ll.search(9));
		System.out.println(ll.search(8));
		ll.clear();
		System.out.println(ll);
		
	}

	private IntegerNode head;
	private int count;

	public LinkedList() {
		head = null;
		count = 0;
	}

	@Override
	public void insertAt(int pos, Integer element) {

		if (pos == 0) {
			IntegerNode temp = new IntegerNode(element);
			temp.next = head;
			head = temp;
			count++;
			return;
		}

		if (head == null) {
			throw new ArrayIndexOutOfBoundsException("Empty list. Please Insert element at position 0.");
		}

		IntegerNode temp = getNodeAt(pos);
		IntegerNode newNode = new IntegerNode(element);
		newNode.next = temp.next;
		temp.next = newNode.next;
		count++;
		return;
	}

	@Override
	public void removeAt(int pos) {
		if (head == null)
			throw new ArrayIndexOutOfBoundsException("There are no elements in this list.");

		if (pos > count - 1 || pos < (-1 * count)) {
			throw new ArrayIndexOutOfBoundsException("Index " + pos + " out of index " + count + " doesn't exist.");
		}

		if (count == 1) {
			head = null;
			count = 0;
			return;
		}

		if (pos == 0 || pos == -1 * count) {
			head = head.next;
			count--;
			return;
		}

		IntegerNode temp = getNodeAt(pos - 1);
		temp.next = temp.next.next;
		count--;

	}

	@Override
	public Integer getAt(int pos) {
		IntegerNode temp = getNodeAt(pos);
		return temp.value;
	}

	@Override
	public int search(Integer element) {
		int pos = -1;
		int counter = 0;
		IntegerNode temp = head;
		while (temp != null) {
			if (temp.value == element) {
				pos = counter;
				break;
			}
			counter++;
			temp = temp.next;
		}

		return pos;
	}

	@Override
	public void clear() {
		head = null;
		count = 0;

	}

	@Override
	public int getCount() {
		return count;
	}

	private IntegerNode getNodeAt(int pos) {
		if (head == null)
			throw new ArrayIndexOutOfBoundsException("There are no elements in this list.");

		if (pos > count - 1 || pos < (-1 * count)) {
			throw new ArrayIndexOutOfBoundsException("Index " + pos + " out of index " + count + " doesn't exist.");
		}

		if (pos < 0)
			pos = pos + count;

		IntegerNode temp = head;
		for (int i = 1; i <= pos; i++) {
			temp = temp.next;
		}

		return temp;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		sb.append("[");
		for(int i = 0; i < count; i++) {
			if(i!=0)
				sb.append(", ");
			sb.append(getAt(i).toString());
		}
		sb.append("]");
		return sb.toString();
	}
	
	public String toStringRev() {
		StringBuilder sb = new StringBuilder("");
		sb.append("[");
		for(int i = -1; i >= -1 * count; i--) {
			if(i!=-1)
				sb.append(", ");
			sb.append(getAt(i).toString());
		}
		sb.append("]");
		return sb.toString();
	}
}
