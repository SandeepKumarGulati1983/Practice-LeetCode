package LeetCodeSolution;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Google_prep_graphs {

	//207. Course Schedule

	public boolean canFinish(int numCourses, int[][] prerequisites) {

		// course -> list of next courses
		HashMap<Integer, List<Integer>> courseDict = new HashMap<>();

		// build the graph first
		for (int[] relation : prerequisites) {
			// relation[0] depends on relation[1]
			if (courseDict.containsKey(relation[1])) {
				courseDict.get(relation[1]).add(relation[0]);
			} else {
				List<Integer> nextCourses = new LinkedList<>();
				nextCourses.add(relation[0]);
				courseDict.put(relation[1], nextCourses);
			}
		}

		boolean[] path = new boolean[numCourses];

		for (int currCourse = 0; currCourse < numCourses; ++currCourse) {
			if (this.isCyclic(currCourse, courseDict, path)) {
				return false;
			}
		}

		return true;
	}


	/*
	 * backtracking method to check that no cycle would be formed starting from currCourse
	 */
	protected boolean isCyclic(
			Integer currCourse,
			HashMap<Integer, List<Integer>> courseDict,
			boolean[] path) {

		if (path[currCourse]) {
			// come across a previously visited node, i.e. detect the cycle
			return true;
		}

		// no following courses, no loop.
		if (!courseDict.containsKey(currCourse))
			return false;

		// before backtracking, mark the node in the path
		path[currCourse] = true;

		// backtracking
		boolean ret = false;
		for (Integer nextCourse : courseDict.get(currCourse)) {
			ret = this.isCyclic(nextCourse, courseDict, path);
			if (ret)
				break;
		}
		// after backtracking, remove the node from the path
		path[currCourse] = false;
		return ret;
	}

	// 236. Lowest Common Ancestor of a Binary Tree
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if(root == null){
			return null;
		}

		if(root == p || root == q){
			return root;
		}

		TreeNode left = lowestCommonAncestor(root.left,p,q);  //5
		TreeNode right = lowestCommonAncestor(root.right,p,q);  //

		if(left!=null && right!=null){
			return root;
		} else if(left == null && right !=null){
			return right;
		} else if(right == null && left !=null){
			return left;
		}else{
			return null;
		}
	}

	//topological sort , user for finalising dependencies or prerequisites 
	// for DAG - directed acyclic graph 

	// detect cycle in a graph 

	// MST - minimum spanning tree  -- modified DFS 
	public void mst(int nVerts, Vertex[] vertexList) // minimum spanning tree (depth first)
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
		for(int j=0; j<nVerts; j++) vertexList[j].wasVisited= false;
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
			System.out.print( sortedArray[j] ); System.out.println(“”);
	} // end topo

	// utility functions and classes -----------------------------------

	int noSuccessors() {
		return 1 ;
	}
	// returns an unvisited vertex adj to v 
	public int getAdjUnvisitedVertex(int v )
	{
		// 1. by adjency matrix 
		// 2. by adjency list  -- array of list as define in robert lafore 
		// 3. it can be hash map having list  -- for faster traversal 
		HashMap<Integer, List<Vertex>> adjencyMap = new HashMap<>();


		//need to fill according to type of adjency list -- matrix or link list 
		return -1;
	}


	class Vertex
	{
		public char label; // label (e.g. ‘A’) 
		public boolean wasVisited;

		public Vertex(char lab) // constructor
		{
			label = lab; 
			wasVisited = false; }

	} 

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}

}
