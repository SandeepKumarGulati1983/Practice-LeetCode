package LeetCodeSolution;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class CCI_GraphAndTree {


	class Node{

		char lable;
		int data;
		boolean visited;

		Node(char c){
			this.lable = c;
			this.visited = false;
		}
		Node(int d){
			this.data = d;
			this.visited = false;
		}



	}

	class Graph{

		Node[] vertexArray;
		int totalVertex =0;
		int[][] adjencymatrix;

		Graph(){
			vertexArray = new Node[20];
			adjencymatrix = new int[20][20];

		}

		void addVertix(Node vertex) {
			vertexArray[totalVertex++] = vertex;

		}

		void addEdge(int start , int end ) { 
			adjencymatrix[start][end] = 1 ;// directed 
		}

		Node getAdjacentNode(Node n) {	

			for (int i = 0 ; i <totalVertex ; i++) {
				if (adjencymatrix[n.data][i] == 1 && !vertexArray[i].visited) {
					return vertexArray[i];
				}

			}
			return null;
		}

	}

	Graph getGraph() {
		return new Graph();
	}

	Node createNode(int data) {
		return new Node(data);
	}

	//==============================================

	//4.1  -- route between nodes   -- 

	boolean routeBetweenNodes(Graph g , Node startNode, Node endNode) {
		//we have to run DFS

		if (startNode == endNode) return true ;

		Queue<Node> queue = new LinkedList<>();

		startNode.visited =true;
		queue.add(startNode);
		while(!queue.isEmpty()) {

			Node adjacentNode =g.getAdjacentNode(queue.remove());
			if (adjacentNode != null) {
				if (adjacentNode == endNode) return true;
				queue.add(adjacentNode);
			}else return false ;
		}

		return false ;

	}

	// Minimal tree 
	/*
	 * too dificult without recursive .
	 */

	class TreeNode{
		TreeNode left;
		TreeNode right;
		int value;


		TreeNode(int v){
			this.value = v;
		}
		TreeNode (int v , TreeNode l, TreeNode r){
			this.value = v;
			this.left = l;
			this.right = r;
		}

	}

	TreeNode getTreeNode (int v) {
		return new TreeNode(v);
	}
	// 4.2  CCI 
	//Not working 
	TreeNode createBST(int[] array, int start , int end) {

		if (array ==null || start <end) return null;
		int mid = (end+start) /2 ;
		TreeNode n = new TreeNode(array[mid]);
		n.left = createBST(array, start, mid-1);
		n.right = createBST(array, mid+1 , end);

		return n;
	}


	// 4.3 -- list of depth

	LinkedList<TreeNode>[] listofDepth(TreeNode n){

		if(n == null) return null;
		LinkedList<TreeNode>[] listArray = null ;
		LinkedList<TreeNode> list = null;

		Queue<TreeNode> queue = new LinkedList<>();
		int ListArrayCount=0;
		queue.add(n);
		list= new LinkedList<>();
		list.add(n);
		listArray[ListArrayCount] = list;

		while (!queue.isEmpty()) {

			list= new LinkedList<>();
			int qSize = queue.size();
			for (int i = 0 ; i < qSize ; i++) {
				TreeNode currentNode = queue.remove();
				queue.add(currentNode.left);
				queue.add(currentNode.right);
				list.add(currentNode);
			}
			listArray[++ListArrayCount] = list;

		}


		return listArray;
	}

	//4.4 balanced tree  -- recursive time complexity here is O(nLogN)

	boolean isBalanced(TreeNode n) {

		if (n == null ) return false ;

		return Math.abs(height(n.left)-height(n.right))>1 ?false :true;

	}

	int height(TreeNode n) {
		if (n == null) return 0;

		return Math.abs(height(n.left)-height(n.right));
	}

	// 4.5 -- validate a BST

	boolean isBST(TreeNode n) {


		return (minMax(n.left) > n.value && n.value < minMax(n.right) ) ?true : false ;

	}

	int minMax (TreeNode n) {

		if (n ==null) return 0 ;

		int min = Math.min(n.value,minMax(n.left));
		int max = Math.max(n.value,minMax(n.right));

		return Math.min (min,max);

	}

	// 4.8 common anccesstor of 2 node in a BST --  not a binary tree 

	// 4.9 ... BST sequence ..

	// 4.10 ... t2 is a subtree of T1 
	// 4.14 --- path sum  -- + and - both node , can be anywhere in between root and leaf 

	boolean isSubTree(TreeNode t1 , TreeNode t2) {


		if (t2 == null) return false ;

		return (t1 ==t2 )? isSubTree(t1.left, t2.left) && isSubTree(t1.right, t2.right) : isSubTree(t1.left, t2) && isSubTree(t1.right, t2) ;

	}

	//310. Minimum Height Trees
	public List<Integer> findMinHeightTrees_notWorking(int n, int[][] edges) {

		List<Integer> list = new LinkedList<>();

		int node = 0;

		int minHeight =n;
		while (node <n) {


			// consider a node as a root 
			int root = node ;


			// do a dfs 
			Stack<Integer> s = new Stack<>();
			s.add(root);
			while (!s.isEmpty()) {
				int cNode = s.pop();
				int cHeight = 0;
				for (int i = 0 ; i < n;i++) {
					if (edges[cNode][i] ==1 ) {
						s.push(i);
						cHeight++;
					}
				}
				// if dfs is < min height  -- update min height create a list and add in list 
				if (cHeight<minHeight ) {
					minHeight = cHeight;
					list = new LinkedList<>();
					list.add(cNode);
				}else
					// if dfs result is == min heihgt -- add in created list 

					if (cHeight==minHeight ) {
						list.add(cNode);
					}
				// if dfs is > then move to next node 


			}

			node++;
		}


		return list;

	}

	public List<Integer> findMinHeightTrees(int n, int[][] edges) {

		/*
		 * solution is based on the fact that a root which is connect with all the nodes has the min height tree 
		 * 
		 * and total no of edges in a connected tree are n-1
		 */


		int minHeight = n-1 ; // if all are in a straight line then they are connected with n-n edge 
		int currentNode = 0;
		while (currentNode<n) {
			int currentHeight = 0;
			for (int i = 0 ; i <n ; i++) {
				if (edges[currentNode][i] ==1 ) {
					currentHeight++;

				}
			}


			currentNode++;
		}

		return null ;

	}

	// 98. Validate Binary Search Tree


	public boolean isValidBST_wrong(TreeNode root) {

		if (root ==null) return true;
		if (root.left ==null && root.right ==null) return true;
		boolean isvali = false ;
		if (root.left== null ) {
			isvali = (root.value < root.right.value) ?true :false;
		}else if (root.right== null ) {
			isvali =  (root.left.value < root.value)?true:false ;
		}else isvali =  (root.left.value < root.value && root.value < root.right.value) ?true :false;
		return isValidBST(root.left)&& isValidBST(root.right) && isvali ;

		/*
		 * fail for  this use case 
		 * // another use case - [5,4,6,null,null,3,7]
		 */
	}

	public boolean isValidBST(TreeNode root) {
		return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
	}

	public boolean isValidBST(TreeNode root, long minVal, long maxVal) {
		if (root == null) return true;
		if (root.value >= maxVal || root.value <= minVal) return false;
		return isValidBST(root.left, minVal, root.value) && isValidBST(root.right, root.value, maxVal);
	}

	//207. Course Schedule
	public boolean canFinish(int numCourses, int[][] prerequisites) {

		//HashSet<Integer> dependencies = new HashSet<Integer>();
		HashSet<Integer> courses =new HashSet<Integer>();;

		for (int[] pre: prerequisites) {
			if (!courses.contains(pre[0])) courses.add(pre[0]);
			if (courses.contains(pre[1])) {
				return false ;
			}
		}
		return true;

		/*
		 * use case  1, 0  -- 2,0 --  0, 1,2 - false  --- 0, 3 true 
		 */


	}

	//236. Lowest Common Ancestor of a Binary Tree

//	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//
//		// start from bottom 
//		// do BFS/dfs on each node and check in complete BFS tree.
//		 	// check p, q in all nodes including root 
//		
//		
//		
//	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CCI_GraphAndTree cci = new CCI_GraphAndTree();
		//int[][] prerequisites= {{1,0}};
		int[][] prerequisites= {{1,0},{0,1}};
		int numCourse = 2;
		System.out.println(cci.canFinish(numCourse, prerequisites));

		//		TreeNode root = cci.getTreeNode(5);
		//		root.left = cci.getTreeNode(1);
		//		root.right = cci.getTreeNode(4);
		//		root.right.left = cci.getTreeNode(3);
		//		root.right.right = cci.getTreeNode(6);

		//use case when tree only have one side , either left or right 
		//		TreeNode root = cci.getTreeNode(1);
		//		root.right = cci.getTreeNode(1);

		// another use case - [5,4,6,null,null,3,7]
		//		TreeNode root = cci.getTreeNode(5);
		//		root.left = cci.getTreeNode(4);
		//		root.right = cci.getTreeNode(6);
		//		root.right.left = cci.getTreeNode(3);
		//		root.right.right = cci.getTreeNode(7);
		//
		//		System.out.println(cci.isValidBST(root));


		//310. Minimum Height Trees
		//		int n =4;
		//		int[][] edges  = new int[n][n];
		//		edges[1][0] = 1 ;
		//		edges[1][2] = 1 ;
		//		edges[1][3] = 1 ;
		//
		//		System.out.println(cci.findMinHeightTrees(n, edges));



		//4.2
		//		int[] a = {1,2,3,4,5,6};
		//		TreeNode n = cci.createBST(a, 0, a.length-1);
		//
		//		// non recursive way of traversing in a tree
		//
		//		Stack<TreeNode> s = new Stack<>();
		//		s.push(n);
		//		while (!s.isEmpty()) {
		//
		//			TreeNode next = s.pop();
		//			if (next != null ) {System.out.println(next.value);
		//			s.push(next.left);
		//			s.push(next.right);
		//			}
		//
		//
		//		}



		//4.1 
		//		Graph g = cci.getGraph();
		//
		//		Node a = cci.createNode(1);
		//		Node b = cci.createNode(5);
		//
		//		g.addVertix(a); 
		//		g.addVertix(cci.createNode(2)); 
		//		g.addVertix(cci.createNode(3)); 
		//		g.addVertix(cci.createNode(4)); 
		//
		//		g.addEdge(1,2);
		//		//g.addEdge(2,3);
		//		g.addEdge(3,4);
		//		g.addEdge(4,1);
		//
		//		System.out.println(cci.routeBetweenNodes(g,a,b));

	}

}
