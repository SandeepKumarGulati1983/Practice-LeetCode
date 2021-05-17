package LeetCodeSolution;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;


public class Graph_RobertLaforay {
	private final int MAX_VERTS = 20;
	private Vertex vertexList[]; // list of vertices
	private int adjMat[][]; // adjacency matrix
	private int nVerts; // current number of vertices 
	private Stack theStack;
	public Graph_RobertLaforay() // constructor
	{
		vertexList = new Vertex[MAX_VERTS];
		// adjacency matrix adjMat = new int[MAX_VERTS][MAX_VERTS];
		nVerts = 0;
		for(int j=0; j<MAX_VERTS; j++)
			for(int k=0; k<MAX_VERTS; k++) adjMat[j][k] = 0;
		theStack = new Stack();
		// set adjacency // matrix to 0
	} // end constructor
	// -----------------------------------------------------------

	class Vertex
	{
		public char label; // label (e.g. ‘A’) 
		public boolean wasVisited;

		public Vertex(char lab) // constructor
		{
			label = lab; 
			wasVisited = false; }

	} 

	//   1. ----- very important function 
	// returns an unvisited vertex adjacent to v 
	public int getAdjUnvisitedVertex(int v)
	{
		for(int j=0; j<nVerts; j++)
			if(adjMat[v][j]==1 && vertexList[j].wasVisited==false) return j; // return first such vertex
		return -1; 
		// no such vertices 
	}


	void dfs () {

		Vertex v = vertexList[0];
		Stack<Vertex> stack =  new Stack<>();
		stack.push(v);
		while (!stack.isEmpty()) {
			Vertex curV = stack.peek();  //**** can't use pop as then , will not able to get more then one neighbour of a same node 

			Vertex v1 = getAdjUnvisitedVertex(curV);
			if (v1 != null ) { // not -1 as I am taking Vertex node and not Integer 
				stack.push(v1);
				v1.wasVisited = true;
			}else {
				stack.pop();
			}
		}

	}
	void bfs() {

		Queue<Vertex> queue = new LinkedList<>();
		queue.add(vertexList[0]);
		while (!queue.isEmpty()) {
			Vertex curV = queue.remove(); // because in queue peek will give the head means latest added one 
			Vertex v1 = getAdjUnvisitedVertex(curV);
			if (v1 != null) {
				queue.add(v1);
				v1.wasVisited = true;
			}else queue.remove();
		}
	}


	// MST - minimum spanning tree  -- modified DFS 
	public void mst() // minimum spanning tree (depth first)
	{
		Stack<Integer> theStack = new Stack<>();



		vertexList[0].wasVisited = true; 
		theStack.push(0);

		while( !theStack.isEmpty() ) {
			int currentVertex = theStack.peek();
			// get next unvisited neighbor
			int v = getAdjUnvisitedVertex(currentVertex); // this need adjency list 
			if(v == -1) theStack.pop();
			else {
				// if no more neighbors // pop it away
				// got a neighbor
				vertexList[v].wasVisited = true; // mark it 
				theStack.push(v); // push it // display edge

			}
		} // end while(stack not empty)
		// from currentV // to v
		// reset flags
		// stack is empty, so we’re 
		for(int j=0; j<nVerts; j++) 
			vertexList[j].wasVisited= false;
	} 


	// topological serach -- modified DFS 
	public void topo(int nVerts) // topological sort 
	{
		int orig_nVerts = nVerts; // remember how many verts
		while(nVerts > 0) // while vertices remain, 
		{
			// get a vertex with no successors, or -1
			int currentVertex = noSuccessors();
			if(currentVertex == -1) // must be a cycle
			{
				//System.out.println(“ERROR Graph has cycles”); 
				return;
			}
			// insert vertex label in sorted array (start at end) 
			sortedArray[nVerts-1] = vertexList[currentVertex].label;
			deleteVertex(currentVertex); // delete vertex 
		} // end while
		// vertices all gone; display sortedArray 
		//System.out.print(“Topologically sorted order: “); 
		for(int j=0; j<orig_nVerts; j++)
			System.out.print(sortedArray[j] ); 
		System.out.println(“”);
	} // end topo


	int noSuccessors() {
		return 1 ;
	}
	// returns an unvisited vertex adj to v 
	public int getAdjUnvisitedVertex1(int v )
	{
		// 1. by adjency matrix 
		// 2. by adjency list  -- array of list as define in robert lafore 
		// 3. it can be hash map having list  -- for faster traversal 
		HashMap<Integer, List<Vertex>> adjencyMap = new HashMap<>();


		//need to fill according to type of adjency list -- matrix or link list 
		return -1;
	}





}
