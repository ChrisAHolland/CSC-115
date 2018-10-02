/*
Chris Holland
CSC 115, Assignment 4
BinarySearchTree.java
*/

import java.util.*;

//
// An implementation of a binary search tree.
//
// This tree stores both keys and values associated with those keys.
//
// More information about binary search trees can be found here:
//
// http://en.wikipedia.org/wiki/Binary_search_tree
//
// Note: Wikipedia is using a different definition of
//       depth and height than we are using.  Be sure
//       to read the comments in this file for the
//	 	 height function.
//
class BinarySearchTree <K extends Comparable<K>, V>  {

	public static final int BST_PREORDER  = 1;
	public static final int BST_POSTORDER = 2;
	public static final int BST_INORDER   = 3;

	// These are package friendly for the TreeView class
	BSTNode<K,V>	root;
	int		count;

	int		findLoops;
	int		insertLoops;

	public BinarySearchTree () {
		root = null;
		count = 0;
		resetFindLoops();
		resetInsertLoops();
	}

	public int getFindLoopCount() {
		return findLoops;
		
	}

	public int getInsertLoopCount() {
		return insertLoops;
	}

	public void resetFindLoops() {
		findLoops = 0;
	}
	public void resetInsertLoops() {
		insertLoops = 0;
	}

	//
	// Purpose:
	//
	// Insert a new Key:Value Entry into the tree.  If the Key
	// already exists in the tree, update the value stored at
	// that node with the new value.
	//
	// Pre-Conditions:
	// 	the tree is a valid binary search tree
	//
	public void insert (K k, V v) {
		if (root == null) {
			root = myInsert(root, k, v);
		} 
		else {
			myInsert(root, k, v);
		}
	}
	
	private BSTNode<K, V> myInsert(BSTNode<K, V> node, K k, V v) {
		insertLoops++;
		if (node == null) {
			count++;
			return new BSTNode<K,V>(k,v);
		}
		
		if (k.compareTo(node.key) == 0) {
			node.value = v;
			return node;
		}
		
		if (k.compareTo(node.key) < 0) {
			node.left = myInsert(node.left, k, v);
			return node;
		}
		
		else {
			node.right = myInsert(node.right, k, v);
			return node;
		}
	}

	//
	// Purpose:
	//
	// Return the value stored at key.  Throw a KeyNotFoundException
	// if the key isn't in the tree.
	//
	// Pre-conditions:
	//	the tree is a valid binary search tree
	//
	// Returns:
	//	the value stored at key
	//
	// Throws:
	//	KeyNotFoundException if key isn't in the tree
	//
	public V find (K key) throws KeyNotFoundException {
		return findKey(root, key);
	}
	
	private V findKey(BSTNode<K,V> root, K key) throws KeyNotFoundException {
		findLoops++;
		if (root == null) {
			throw new KeyNotFoundException();
		}
		if (key.equals(root.key)) {
			return root.value;
		}
	    if (key.compareTo(root.key) < 0) {
			return findKey(root.left, key);
		}
		else {
			return findKey(root.right, key);
		}
		
	}

	//
	// Purpose:
	//
	// Return the number of nodes in the tree.
	//
	// Returns:
	//	the number of nodes in the tree.
	public int size() {
		return count;
	}

	//
	// Purpose:
	//	Remove all nodes from the tree.
	//
	public void clear() {
		root = null;
		count = 0;
	}

	//
	// Purpose:
	//
	// Return the height of the tree.  We define height
	// as being the number of nodes on the path from the root
	// to the deepest node.
	//
	// This means that a tree with one node has height 1.
	//
	// Examples:
	//	See the assignment PDF and the test program for
	//	examples of height.
	//
	public int height() {
		if (root == null) {
			return 0;
		} else {
			BSTNode<K,V> temp = root;
			return getHeight(temp);
		}
	}
	
	private int getHeight(BSTNode<K,V> n) {
		int leftHeight = 0;
		int rightHeight = 0;
		
		if (n.left != null) {
			leftHeight = getHeight(n.left);
		}
		
		if (n.right != null) {
			rightHeight = getHeight(n.right);
		}
		
		if (leftHeight > rightHeight) {
			return leftHeight + 1;
		}
		else {
			return rightHeight + 1;
		}
	}

	//
	// Purpose:
	//
	// Return a list of all the key/value Entrys stored in the tree
	// The list will be constructed by performing a level-order
	// traversal of the tree.
	//
	// Level order is most commonly implemented using a queue of nodes.
	//
	//  From wikipedia (they call it breadth-first), the algorithm for level order is:
	//
	//	levelorder()
	//		q = empty queue
	//		q.enqueue(root)
	//		while not q.empty do
	//			node := q.dequeue()
	//			visit(node)
	//			if node.left != null then
	//			      q.enqueue(node.left)
	//			if node.right != null then
	//			      q.enqueue(node.right)
	//
	// Note that we will use the Java LinkedList as a Queue by using
	// only the removeFirst() and addLast() methods.
	//
	
	public List<Entry<K,V>> entryList() {
		List<Entry<K, V>> 			l = new LinkedList<Entry<K,V> >();
		LinkedList<BSTNode<K,V>> myQueue = new LinkedList<BSTNode<K,V>>();
		BSTNode<K,V> temp = null;
		myQueue.addLast(root);
		
		while (!myQueue.isEmpty()) {
			temp = myQueue.remove();
			l.add(new Entry<K,V>(temp.key, temp.value));
			
			if (temp.left != null) {
				myQueue.add(temp.left);
			}
			if (temp.right != null) {
				myQueue.add(temp.right);
			}
		}
	return l;
	}
	
	// In order traversal *********************************************************************
	public void inOrder() {
		List<Entry<K, V>> 			l = new LinkedList<Entry<K,V> >();
		inOrder(root, l);
	}
	// in order helper method
	private void inOrder(BSTNode<K,V> node, List<Entry<K,V>> list) throws NullPointerException {
		if (root == null) {
			throw new NullPointerException();
		}
		if (root != null) {
			
			if (node.left != null) {
				inOrder(node.left, list);
			}
			
			list.add(new Entry<K,V>(node.key, node.value));
			
			if (node.right != null) {
				inOrder(node.right, list);	
			}
			}
	}
	
	//*****************************************************************************************
	// Pre Order traversal
	public void preOrder() {
		List<Entry<K, V>> 			l = new LinkedList<Entry<K,V> >();
		preOrder(root, l);
	}
	// pre order helper method
	private void preOrder(BSTNode<K,V> node, List<Entry<K,V>> list) throws NullPointerException {
		if (root == null) {
			throw new NullPointerException();
		}
		if (root != null) {
			list.add(new Entry<K,V>(node.key, node.value));
			
			if (node.left != null) {
				preOrder(node.left, list);
			}
			
			if (node.right != null) {
				preOrder(node.right, list);
			}
		}
	}
	
	//*****************************************************************************************
	// Post Order traversal
	public void postOrder() {
		List<Entry<K, V>> 			l = new LinkedList<Entry<K,V> >();
		postOrder(root, l);
	}
	// post order helper method
	private void postOrder(BSTNode<K,V> node, List<Entry<K,V>> list) throws NullPointerException {
		if (root == null) {
			throw new NullPointerException();
		}
		if (root != null) {
			
			if (node.left != null) {
				postOrder(node.left, list);
			}
			
			if (node.right != null) {
				postOrder(node.right, list);
			}
			
			list.add(new Entry<K,V>(node.key, node.value));
		}
	}
	
	//
	// Purpose:
	//
	// Return a list of all the key/value Entrys stored in the tree
	// The list will be constructed by performing a traversal 
	// specified by the parameter which.
	//
	// If which is:
	//	BST_PREORDER	perform a pre-order traversal
	//	BST_POSTORDER	perform a post-order traversal
	//	BST_INORDER	perform an in-order traversal
	//
	public List<Entry<K,V> > entryList (int which) {
		List<Entry<K,V> > l = new LinkedList<Entry<K,V> >();
		
		if (which == 1) {
			preOrder(root, l);
		}
		
		if (which == 2) {
			postOrder(root, l);
		}
		
		if (which == 3) {
			inOrder(root, l);
		}
		
		return l;
	}

	// Your instructor had the following private methods in his solution:
	// private void doInOrder (BSTNode<K,V> n, List <Entry<K,V> > l);
	// private void doPreOrder (BSTNode<K,V> n, List <Entry<K,V> > l);
	// private void doPostOrder (BSTNode<K,V> n, List <Entry<K,V> > l);
	// private int doHeight (BSTNode<K,V> t)
}
