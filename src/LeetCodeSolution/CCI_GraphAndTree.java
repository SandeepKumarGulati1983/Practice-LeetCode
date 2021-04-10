package LeetCodeSolution;

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

		Node[] vedrtexArray;
		int totalVertex =0;
		int[][] adjencymatrix;

		Graph(){

		}

		void addVertix(Node vertex) {
			vedrtexArray[totalVertex++] = vertex;		

		}

		void addEdge(int start , int end ) { 
			adjencymatrix[start][end] = 1 ;// directed 
		}
	}

	Graph getGraph() {
		return new Graph();
	}

	Node getNode(int data) {
		return new Node(data);
	}

	//==============================================

	//4.1  -- route between nodes   -- 

	boolean routeBetweenNodes(Graph g , Node startNode, Node endNode) {
		//we have to run DFS
		

	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

		CCI_GraphAndTree cci = new CCI_GraphAndTree();

		Graph g = cci.getGraph();

		Node a = cci.getNode(1);
		Node b = cci.getNode(3);

		g.addVertix(a); 
		g.addVertix(cci.getNode(2)); 
		g.addVertix(b); 
		g.addVertix(cci.getNode(4)); 

		g.addEdge(1,2);
		g.addEdge(2,3);
		g.addEdge(3,4);
		g.addEdge(4,1);

		cci.routeBetweenNodes(g,a,b);

	}

}
