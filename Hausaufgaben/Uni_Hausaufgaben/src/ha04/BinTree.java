/**
 * 
 */
package ha04;

/**
 * @author Param
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

	public class TreeNode {

		public int data;
		public TreeNode right;
		public TreeNode left;

		public TreeNode(int val) {
			this.data = val;
		}

	}

	private TreeNode root;

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

	public void clear() {
		root = null;
	}

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

	private void findAndRemoveCandidate(TreeNode parent, TreeNode elem, int x) {

		// BLATT
		if (elem.left == null & elem.right == null) {
			if (parent == null) {
				root = null;
				return;
			} else {
				if (x < parent.data)
					parent.left = null;
				else
					parent.right = null;
			}
		}

		// EIN SOHN
		if (elem.left == null | elem.right == null) {
			TreeNode subTree = (elem.left == null) ? elem.right : elem.left;
			if (parent == null) {
				root = subTree;
				return;
			} else {
				if (x < parent.data)
					parent.left = subTree;
				else
					parent.right = subTree;
			}
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
