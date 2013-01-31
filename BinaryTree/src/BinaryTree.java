
public class BinaryTree {

	private class BNode {
		private int key;
		private BNode left;
		private BNode right;
		
		private BNode(int n) {
			key = n;
			left = null;
			right = null;
		}
		
		private boolean add(int n) {
	
			if (n == this.key) {
				return false;
			}
			
			if (n < this.key) {
				
				if (this.left == null) {
					this.left = new BNode(n);
				} else {
					return this.left.add(n);
				}
			}	
			else
			if (n > this.key) {
				
				if (this.right == null) {
					this.right = new BNode(n);
				} else {
					return this.right.add(n);
				}
				
			}	
			return true;
		}
		
		private void print() {
			if (left != null) {
				left.print();
			}
			System.out.println(this.key);
			if (right != null) {
				right.print();
			}
			
		}
	}
	
	private BNode root;
	
	public BinaryTree () {
		root = null; 
	}
	
	public boolean add(int n) {
		if (root == null) {
			root = new BNode(n);
			return true;
		}
		return root.add(n);
	}
	
	public void print() {
		root.print();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BinaryTree btree = new BinaryTree();
		btree.add(10);
		btree.add(15);
		btree.add(5);
		btree.add(4);
		btree.add(16);
		btree.add(51);
		btree.add(2);
		btree.add(9);
		btree.print();

		BinaryTree btree2 = new BinaryTree();
		btree2.add(10);
		btree2.add(12);
		btree2.add(5);
		btree2.add(6);
		btree2.print();
		
	}

}
