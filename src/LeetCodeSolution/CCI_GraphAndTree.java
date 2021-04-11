package LeetCodeSolution;

import java.util.Arrays;
import java.util.LinkedList;
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

	TreeNode createBST(int[] array, int start , int end) {

		if (array ==null || start <end) return null;
		int mid = (end+start) /2 ;
		TreeNode n = new TreeNode(array[mid]);
		n.left = createBST(array, start, mid-1);
		n.right = createBST(array, mid+1 , end);

		return n;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		CCI_GraphAndTree cci = new CCI_GraphAndTree();

		int[] a = {1,2,3,4,5,6};
		TreeNode n = cci.createBST(a, 0, a.length-1);

		// non recursive way of traversing in a tree

		Stack<TreeNode> s = new Stack<>();
		s.push(n);
		while (!s.isEmpty()) {

			TreeNode next = s.pop();
			if (next != null ) {System.out.println(next.value);
			s.push(next.left);
			s.push(next.right);
			}


		}


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
