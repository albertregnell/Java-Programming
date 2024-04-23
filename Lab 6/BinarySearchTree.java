package bst;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;


public class BinarySearchTree<E> {
  BinaryNode<E> root;  // Används också i BSTVisaulizer
  int size;            // Används också i BSTVisaulizer
  private Comparator<E> comparator;
    
	/**
	 * Constructs an empty binary search tree.
	 */
	public BinarySearchTree() {
		root = null;
		size = 0;
	}
	
	/**
	 * Constructs an empty binary search tree, sorted according to the specified comparator.
	 */
	public BinarySearchTree(Comparator<E> comparator) {
		root = null;
		size = 0;
		this.comparator = comparator;
	}
	
	public static void main(String[] args) {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		Random rand = new Random();
		for(int i=0; i<10;i++) {
			tree.add(rand.nextInt(20));	
		}
		BSTVisualizer visual = new BSTVisualizer("Tree" , 300,300);
		visual.drawTree(tree);
		tree.printTree();
		
		//tree.rebuild();
		//visual.drawTree(tree);
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		if(add(x, root)) {
			size++;
			return true;
		}else {
			return false;
		}
	}
	private boolean add(E x, BinaryNode<E> node) {
		if(node == null) {
			root = new BinaryNode<E>(x);
			return true;
		}
		int n = comparing(x,node);
		if(n<0) {
			if(node.left == null) {
				node.left = new BinaryNode<E>(x);
				return true;
			}else {
			return add(x, node.left);
			}
		}else if(n>0) {
			if(node.right == null) {
				node.right = new BinaryNode<E>(x);
				return true;
			}else {
			return add(x, node.right);
			}
		}
		return false;
	}
	private int comparing(E x, BinaryNode<E> node) {
		
		if(comparator == null) {
			return ((Comparable<E>) x).compareTo(node.element);
		}else {
			return comparator.compare(x, node.element);
		}
	}
	
	/**
	 * Computes the height of tree.
	 * @return the height of the tree
	 */
	public int height() {
		return height(root);
	}
	
	private int height(BinaryNode<E> node) {
		if(node == null) {
			return 0;
		}else {
			return 1 + Math.max(height(node.left), height(node.right));
		}
	}
	/**
	 * Returns the number of elements in this tree.
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Removes all of the elements from this list.
	 */
	public void clear() {
		root = null;
		size = 0;
	}
	
	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		printTree(root);

	}
	private void printTree(BinaryNode<E> node) {
		if(!( node == null)) {
			printTree(node.left);
			System.out.println(node.element.toString());
			printTree(node.right);
		}
	}

	/** 
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		if(root == null) {
			return;
		}
		ArrayList<E> sorted = new ArrayList<E>();
		toArray(root, sorted);
		root = buildTree(sorted, 0, sorted.size()-1);
	}
	
	/*
	 * Adds all elements from the tree rooted at n in inorder to the list sorted.
	 */
	private void toArray(BinaryNode<E> node, ArrayList<E> sorted) {
		if(!( node == null)) {
			toArray(node.left, sorted);
			sorted.add(node.element);
			toArray(node.right, sorted);
		}
	}
	
	/*
	 * Builds a complete tree from the elements from position first to 
	 * last in the list sorted.
	 * Elements in the list a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(ArrayList<E> sorted, int first, int last) {
		
		if(!(first > last)) {
			int mid = first + (last-first)/2;
			BinaryNode<E> node = new BinaryNode<>(sorted.get(mid));
			node.left = buildTree(sorted, first, mid-1);
			node.right = buildTree(sorted, mid +1 , last);
			return node;
		}else {
			return null;
		}
	}
	


	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}	
	}
	
}
