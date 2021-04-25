/**
 * 
 */
package ha04;

/**
 * @author Param
 * @author Hazem Falah
 * @author Leon Ikinger
 *
 */
public class BinTree {

	public static void main(String[] args) {
		BinTree tree = new BinTree();
		tree.insert(20);
		tree.insert(10);
		tree.insert(30);
		tree.insert(50);
		int[] testcases = { 30, 35, 50 };
		for (int testcase : testcases) {
			TreeNode node = tree.getNode(testcase);
			if (node == null) {
				System.out.println("Knoten " + testcase + " nicht gefunden.");
			} else {
				System.out.println("Knoten " + testcase + " gefunden: " + node.data);
			}
		}
		tree.remove(30);
		System.out.println("Knoten geloescht: 30");
		System.out.println("Elternknoten von 50: " + tree.getParentNode(50).data);// 20
	}

	/**
	 * <b>Class TreeNode</b>
	 * <br>
	 * Acts as the Node class to BinTree
	 * @author Param
	 *
	 */
	public class TreeNode {

		public int data;
		public TreeNode right;
		public TreeNode left;

		public TreeNode(int val) {
			this.data = val;
		}

	}

	private TreeNode root;

	/**
	 * Fetches the node that has fulfills the condition : <code>data = x</code>.
	 * <br>
	 * If no node fulfills this condition, null is returned
	 * @param x
	 * @return
	 */
	private TreeNode getNode(int x) {

		TreeNode res = root;
		while (res != null) {

			if (res.data == x)
				break;

			if (x < res.data)
				res = res.left;
			else
				res = res.right;
		}
		
		return res;
	}

	/**
	 * Fetches the node that has fulfills the condition : <code>data = x</code>.
	 * <br>
	 * If no node fulfills this condition or if the required node has no parent (is the root element itself), null is returned
	 * @param x
	 * @return	Parent Node of Node containing x or
	 * 			<br> null if, Node is root or no suitable Node is found
	 */
	private TreeNode getParentNode(int x) {
		TreeNode res = root;
		TreeNode par = null;
		boolean found = false;
		while (res != null) {

			if (res.data == x) {
				found = true;
				break;
			}

			par = res;
			if (x < res.data) {
				res = res.left;
			} else {
				res = res.right;
			}
		}

		if (!found)
			return null;

		return par;
	}

	/**
	 * Inserts a element x to the this instance of BinTree
	 * @param x
	 * @throws ArithmeticException if the element to be added already existed in this tree
	 */
	public void insert(int x) {
		if (root == null) {
			root = new TreeNode(x);
			return;
		}

		TreeNode res = getParentNode(x);

		if (res == null && root.data != x) {
			TreeNode newTN = new TreeNode(x);
			TreeNode temp = root;
			while (true) {
				if (x > temp.data) {
					if (temp.right == null) {
						temp.right = newTN;
						break;
					} else
						temp = temp.right;
				} else {
					if (temp.left == null) {
						temp.left = newTN;
						break;
					} else
						temp = temp.left;
				}
			}
		} else
			throw new ArithmeticException("Addition error : Element already exists in the tree.");

	}

	/**
	 * Resets this instance of BinTree
	 */
	public void clear() {
		root = null;
	}

	/**
	 * Method which finds the Node containing data x and removes it while preserving the
	 * structure of the tree
	 * @param x
	 * @throws ArithmeticException if x could not be found
	 */
	public void remove(int x) {
		
		TreeNode par = getParentNode(x);
		TreeNode elem = null;

		if (par == null) {
			if (root.data == x) {
				elem = root;
				findAndRemoveCandidate(null, elem, x);
				return;
			} else
				throw new ArithmeticException("Removal error : Element not found");
		} else {
			elem = (par.right!= null & par.right.data == x) ? par.right : par.left;
			findAndRemoveCandidate(par, elem, x);
		}

	}

	//Helping method to finish the removal of an element
	private void findAndRemoveCandidate(TreeNode parent, TreeNode elem, int x) {

		// BLATT
		if (elem.left == null & elem.right == null) {
			if (parent == null) {
				root = null;
			} else {
				if (x < parent.data)
					parent.left = null;
				else
					parent.right = null;
			}
			return;
		}

		// EIN SOHN
		if (elem.left == null | elem.right == null) {
			TreeNode subTree = (elem.left == null) ? elem.right : elem.left;
			if (parent == null) {
				root = subTree;
			} else {
				if (x < parent.data)
					parent.left = subTree;
				else
					parent.right = subTree;
			}
			return;
		}

		// ZWEI SOEHNE
		TreeNode candidate = elem.right;
		TreeNode candidatePar = elem;
		while (candidate.left != null) {
			candidatePar = candidate;
			candidate = candidate.left;
		}

		candidatePar.left = candidate.right;

		if (parent == null) {
			root.data = candidate.data;
		} else {
			elem.data = candidate.data;
		}

	}

}
