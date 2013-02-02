import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;


	
public class BinaryTree<K extends Comparable<K>> {

	private class BNode {
		private K key;
		private BNode left;
		private BNode right;
		private int height;
		private int level;
		private int bal;
		
		private BNode(K n) {
			key = n;
			left = null;
			right = null;
			height = 0;
			level = 0;
			bal = 0;
		}
		
		private boolean add(K n) {
	
			int comparevalue = n.compareTo(this.key);
			if ( comparevalue == 0) {
				return false;
			} else if (comparevalue < 0) {
				System.out.println(n + " Left of " + this.key);
				if (this.left == null) {
					this.left = new BNode(n);
				} else {
					return this.left.add(n);
				}
			} else if (comparevalue > 0) {
				
				System.out.println(n +" Right of " + this.key);
				if (this.right == null) {
					this.right = new BNode(n);
				} else {
					return this.right.add(n);
				}
				
			}	
			return true;
		}
		
		private int size() {
			int sz = 1;
			if (left != null) {
				sz += left.size();
			}
			if (right != null) {
				sz += right.size();
			}
			return sz;
		}
		
		private void print() {
			if (left != null) {
				left.print();
			}
			
			System.out.println(this.key + " (" + level+ ")");

			if (right != null) {
				right.print();
			}
			
		}

		private void setLevel(int l) {
			level = l;
			if (left != null) {
				left.setLevel(level+1);
			}
			if (right != null) {
				right.setLevel(level+1);
			}
		}
		
		private int getHeight() {
			int h = 0;
			int lh = 0;
			int rh =0;
			
			if (left != null) {
				lh = left.getHeight();
			}
			
			if (right != null) {
				rh = right.getHeight();
			}
			
			h = ((lh>rh)?lh:rh) + 1;
			height = h;
			return height;
		}
	}
	
	private BNode root;
	
	public BinaryTree () {
		root = null; 
	}
	
	public boolean add(K n) {
		if (root == null) {
			root = new BNode(n);
			return true;
		}
		return root.add(n);
	}
	
	public void print() {
		root.print();
	}
	
	public int size() {
		return root.size();
	}
	
	public int getHeight() {
		return root.getHeight();
	}

	public void setLevel() {
		root.setLevel(0);
	}
	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BinaryTree<Integer> btree = new BinaryTree<Integer>();
		btree.add(15);
		btree.add(4);
		btree.add(20);
		btree.add(8);
		btree.add(7);
		System.out.println("Height: " + btree.getHeight());
		btree.setLevel();
		btree.print();
		System.out.println("Total: "+ btree.size());
	
		String s = "Jules Feiffer	 At sixteen I was stupid, " +
				"confused and indecisive. At twenty-five I was wise, " +
				"self-confident, prepossessing and assertive. At forty-five " +
				"I am stupid, c1";
		int k = s.indexOf('\t');
		
		String s1 = s.substring(0, k);
		String s2 = s.substring(k+1);
		Map<String, Integer> mapauthors = new HashMap<String, Integer>();
		Integer val;
		if ((val = mapauthors.get(s1)) == null) {
			mapauthors.put(s1, new Integer(1));
		} else {
			mapauthors.put(s1, new Integer(val.intValue() + 1));
		}
		
		//Iterator iter = mapauthors.entrySet().iterator();
		for (Map.Entry<String, Integer> me : mapauthors.entrySet()) {
			System.out.println(me.getKey() + " " + me.getValue());
		}
		//while(iter.hasNext()) {
			//me = (Map.Entry<String, Integer>) iter.next();
			
		System.out.println("["+s1+"]");
		System.out.println("["+s2+"]");
		StringTokenizer st = new StringTokenizer(s2);
		String tok = null;
		
		while(st.hasMoreTokens()) {
			tok = st.nextToken();
			// Remove some annoying punctuation
			tok = tok.replaceAll("'", ""); // remove single quotes (e.g., can't)
			tok = tok.replaceAll("[^a-zA-Z/-]", " "); // replace the rest with a space
			tok = tok.trim();
			if (tok.length() >=4) {
				System.out.print("["+ tok +"] ");
			}
		}
		/*
		BinaryTree<Car> btree2 = new BinaryTree<Car>();
		
		btree2.add(new Car(15));
		btree2.add(new Car(4));
		btree2.add(new Car(20));
		btree2.add(new Car(8));
		btree2.add(new Car(7));
		
		System.out.println("Height: " + btree2.getHeight());
		btree2.setLevel();
		btree2.print();
		*/
		
	}

}
