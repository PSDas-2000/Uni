package ha11;

/**
 * Ein binaerer Suchbaum mit ganzen Zahlen als Datensatz: Vorlage fuer die A1
 * von algo-pr05. Als Operationen stehen `contains' und `insert' zur Verfuegung
 */
public class BinarySearchTree {

	/** Die Knotenklasse als statische innere Klasse. */
	public static class TreeNode {
		private int value;
		private TreeNode left;
		private TreeNode right;
		private TreeNode parent;	// Um von unten nach oben durcharbeiten zu koennen
		private double avg;			// Speichert der Mittelwert des Unterbaums, der von diesem Knoten aus geht
		private int subNodeCount;	// Speichert die Anzahl an Knoten, die im Unterbaum sind, der von diesem Knoten aus geht

		public double getAvg() {
			return avg;
		}

		public void setAvg(double avg) {
			this.avg = avg;
		}

		public int getSubNodeCount() {
			return subNodeCount;
		}

		public void setSubNodeCount(int subNodeCount) {
			this.subNodeCount = subNodeCount;
		}

		public void setParent(TreeNode parent) {
			this.parent = parent;
		}

		public TreeNode(int value) {
			this.value = value;
			this.avg = value;
			this.subNodeCount = 1;
		}

		public TreeNode(int value, TreeNode parent) {
			// Damit die Elternelementen auch betrachtet werden koennen
			this.parent = parent;
			this.value = value;
			this.subNodeCount = 1;
			this.avg = value;
		}

		public String toString() {
			return this.value + " ";
		}

		public int getValue() {
			return this.value;
		}

		public TreeNode getLeft() {
			return this.left;
		}

		public TreeNode getRight() {
			return this.right;
		}

		public TreeNode getParent() {
			return this.parent;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public void setLeft(TreeNode node) {
			this.left = node;
		}

		public void setRight(TreeNode node) {
			this.right = node;
		}
	}

	/** Baumwurzel */
	protected TreeNode root;

	/**
	 * Herausfinden, ob ein gewisser Datensatz schon im binaeren Suchbaum enthalten
	 * ist.
	 *
	 * @param data zu suchender Datensatz
	 * @return true: Datensatz ist vorhanden; false: Datensatz ist nicht vorhanden.
	 */
	public boolean contains(int data) {
		TreeNode temp = root;
		while (temp != null) {
			if (temp.getValue() == data) {
				return true;
			}
			if (temp.getValue() > data) {
				temp = temp.getLeft();
			} else {
				temp = temp.getRight();
			}
		}
		return false;
	}

	/**
	 * Einen neuen Datensatz in den binaeren Suchbaum einfuegen.
	 *
	 * @param data einzufuegender Datensatz
	 * @return true: Datensatz wurde eingefuegt; false: Datensatz war schon
	 *         vorhanden.
	 */
	public boolean insert(int data) {
		if (root == null) {
			root = new TreeNode(data);
			return true;
		}

		TreeNode temp = root;
		while (temp.getValue() != data) {
			if (temp.getValue() > data) {
				if (temp.getLeft() == null) {
					temp.setLeft(new TreeNode(data, temp));
					setValues(temp.getLeft());
					return true;
				}
				temp = temp.getLeft();
			} else {
				if (temp.getRight() == null) {
					temp.setRight(new TreeNode(data, temp));
					setValues(temp.getRight());
					return true;
				}
				temp = temp.getRight();
			}
		}
		return false;
	}

	/**
	 * Die Methode faengt bei dem Eltern von <code>node</code> an und berechnet nach und nach
	 * die neue Mittelwerte fuer alle betroffene Elternknoten
	 * 
	 * @param node
	 */
	protected void setValues(TreeNode node) {
		TreeNode par = node.parent;
		while (par != null) {
			if (par.left != null && par.right != null) {
				par.subNodeCount = par.left.subNodeCount + par.right.subNodeCount + 1;
				par.avg = (((par.left.avg * par.left.subNodeCount) + (par.right.avg * par.right.subNodeCount) + par.value)
						/ (double) (par.subNodeCount));
			} else if (par.left == null) {
				par.subNodeCount = par.right.subNodeCount + 1;
				par.avg = ((par.right.avg * par.right.subNodeCount) + par.value) / (double) par.subNodeCount;
			} else {
				par.subNodeCount = par.left.subNodeCount + 1;
				par.avg = ((par.left.avg * par.left.subNodeCount) + par.value)/ (double) par.subNodeCount;
			}//Left and Right can never be both null, because we have a legitimate parent node
			par = par.getParent();
		}
	}

	public static void main(String[] args) {
		BinarySearchTree tree = new BinarySearchTree();
		for (int i = 0; i < 20; i++) {
			int x = (int) (Math.random() * 50);
			System.out.println(x);
			tree.insert(x);
		}
		for (int i = 0; i < 50; i++) {
			System.out.println(i + ": " + tree.contains(i));
		}
	}
}
