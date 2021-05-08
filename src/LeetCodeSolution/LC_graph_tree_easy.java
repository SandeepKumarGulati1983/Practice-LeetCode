package LeetCodeSolution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class LC_graph_tree_easy {



	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode() {}
		TreeNode(int val) { this.val = val; }
		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

	TreeNode getTreeNode(int v) {
		return new TreeNode(v);
	}

	//226. Invert Binary Tree
	TreeNode invertTree(TreeNode root) {
		if (root == null )return null;
		if (root.left == null && root.right == null) return root;

		TreeNode temp = root.left;
		root.left  =  invertTree(root.right);
		root.right = invertTree(temp);

		return root;
	}

	// 988. Smallest String Starting From Leaf
	public String smallestFromLeaf(TreeNode root) {

		// smallest height  - via DFS
		if (root == null) return null;
		int minheight = 0 ;
		int minValue = 0;
		Stack<TreeNode> stack = new Stack<>();
		stack.add(root);
		while (!stack.isEmpty()) {
			TreeNode currentNode = stack.pop();
			if  (currentNode.right!=null) {
				stack.add(currentNode.right);
			}

			if  (currentNode.left!=null) {
				stack.add(currentNode.left);
			}
		}
		return null;


		// smallest leaf 
	}

	// DFS - iterative 

	void dfs (TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();
		stack.add(root);
		int lheight =0;
		int rheight =0;
		while (!stack.isEmpty()) {
			TreeNode currentNode = stack.pop();
			System.out.println(currentNode.val);
			if  (currentNode.right!=null) {
				stack.add(currentNode.right);
				rheight++;
			}else {
				System.out.println("right tree height " +rheight);
				System.out.println("end of right tree");
				rheight =0;
			}

			if  (currentNode.left!=null) {
				stack.add(currentNode.left);
				lheight++;
			}else {
				System.out.println("left tree height " +lheight);
				System.out.println("end of left tree");
				lheight =0;
			}

		}
	}
	/*
	 * height of a tree  --  must use level traversal  -- BFS 
	 * then what for min height ???
	 */

	void bfs(TreeNode node) {

		int heihgtOfTree =0;
		Queue<TreeNode> queue = new LinkedList<>();

		queue.add(node);
		int level = 0;
		int minLevel = Integer.MAX_VALUE ; // when we encounter a leaf 
		while (!queue.isEmpty()) {
			level++;

			int levelSize = queue.size();

			for (int i = 0 ; i < levelSize ; i ++) {
				TreeNode currentNode = queue.remove();
				if (currentNode.left !=null) {
					queue.add(currentNode.left);
				}else {
					if (level < minLevel)minLevel = level;
				}
				if (currentNode.right !=null) {
					queue.add(currentNode.right);
				}else {
					if (level < minLevel)minLevel = level;
				}

			}


		}
		System.out.println("height of the tree = level = " + (level-1));
		System.out.println("min height  = " + (minLevel-1));

	}


	//235. Lowest Common Ancestor of a Binary Search Tree
	TreeNode ans ;
	public TreeNode lowestCommonAncestor_NotWorkingForOneuseCase(TreeNode root, TreeNode p, TreeNode q) {

		// do the BFS on each node and check 
		// if both noode exist then save 

		// move to iits left and right 
		// if does not have , then return the saved one 

		// else replace saved one with one having both and do 
		if (root == null) return null ;

		// Wrong 
		if ( bsfForAncestor(root,p,q)) {
			ans = root;
			bsfForAncestor(root.left,p,q);
			bsfForAncestor(root.right,p,q);
		}

		// wrong 
		//		if ( bsfForAncestor(root,p,q)) {
		//			if (!bsfForAncestor(root.left,p,q)) {
		//				if (!bsfForAncestor(root.right,p,q)) {
		//					ans = root;
		//				}else ans = root.right;
		//
		//			}else ans = root.left;
		//		}
		return ans;
		//		if (p.val<root.val && q.val <root.val) {
		//			lowestCommonAncestor(root.left, p,q);
		//		}else if (p.val>root.val && q.val >root.val) {
		//			lowestCommonAncestor(root.right, p,q);
		//		}else {
		//			return root;
		//		}


	}
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		while ((root.val - p.val) * (root.val - q.val) > 0)
			root = p.val < root.val ? root.left : root.right;
		return root;
	}


	boolean bsfForAncestor(TreeNode n,TreeNode p, TreeNode q ) {
		if (n ==null) return false;
		boolean pExist = false;
		boolean qExist = false ;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(n);
		while (!queue.isEmpty()) {
			TreeNode currentNode = queue.remove();
			if (currentNode == p) pExist = true;
			if (currentNode == q) qExist = true;
			if (pExist && qExist) return true;
			if (currentNode.left !=null) queue.add(currentNode.left);
			if (currentNode.right !=null) queue.add(currentNode.right);
		}
		return false ;
	}

	/*
	 * 								6
	 * 				2								8
	 * 		0				4				7				9
	 * null		null	3		5
	 */

	//257. Binary Tree Paths
	public List<String> binaryTreePaths_notworking(TreeNode root) {
		StringBuilder  path =new StringBuilder();
		StringBuilder  rootPath =new StringBuilder();
		if (root == null)return null;

		List<String> list = new LinkedList<>();
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);

		while (!stack.isEmpty()) {

			TreeNode currentNode = stack.pop();
			path.append(currentNode.val);

			if (currentNode.left ==null && currentNode.right ==null) {

				list.add(path.toString());
				path = new StringBuilder(); 
				path.append(root.val+"->");

			}else {
				path.append("->");
			}
			if(currentNode.left!=null) {
				stack.push(currentNode.left);
			}
			if(currentNode.right !=null) {
				stack.push(currentNode.right);
			}
		}
		return list;
	}

	public List<String> binaryTreePaths(TreeNode root) {
		List<String> list=new ArrayList<String>();
		Stack<TreeNode> sNode=new Stack<TreeNode>();
		Stack<String> sStr=new Stack<String>();

		if(root==null) return list;
		sNode.push(root);
		sStr.push("");
		while(!sNode.isEmpty()) {
			TreeNode curNode=sNode.pop();
			String curStr=sStr.pop();

			if(curNode.left==null && curNode.right==null) list.add(curStr+curNode.val);
			if(curNode.left!=null) {
				sNode.push(curNode.left);
				sStr.push(curStr+curNode.val+"->");
			}
			if(curNode.right!=null) {
				sNode.push(curNode.right);
				sStr.push(curStr+curNode.val+"->");
			}
		}
		return list;
	}
	//501. Find Mode in Binary Search Tree
//	public int[] findMode(TreeNode root) {
//		int[] ansArray ;
//		if (root.left ==null && root.right==null) {
//			ansArray = new int[1];
//			ansArray[0] = root.val;
//			return ansArray;
//		}
//


		//int[] frequencyTable = new int[]; // not good as will take lots of space 
//		HashMap<Integer,Integer> map = new HashMap<>();
//		Queue<TreeNode> queue = new LinkedList<>();
//		queue.add(root);
//		while(!queue.isEmpty()) {
//			int queueSize = queue.size();
//			TreeNode currentNode = queue.remove();
//			for (int i = 0; i <queueSize ; i++ ) {
//
//				if (currentNode.left != null) {
//					queue.add(currentNode.left);
//					if (currentNode.val ==currentNode.left.val  ) {
//						map.put(currentNode.val, map.get(currentNode.val) + 1); // put will create the key if not exist and will update the value if key exist .
//					}
//				}
//				if (currentNode.right != null) {
//					queue.add(currentNode.right);
//					if (currentNode.val ==currentNode.right.val  ) {
//						map.put(currentNode.val, map.get(currentNode.val) + 1); // put will create the key if not exist and will update the value if key exist .
//					}
//				}
//
//			}
//		}
//		ansArray = new int[map.size()];
//		int i =0;
//		for (int val : map) {
//			ansArray[i++] = val ;
//		}
//
//
//
//		return ansArray;
//	}

	//530. Minimum Absolute Difference in BST
//	int min = Integer.MAX_VALUE;
//	int prev = -1; 
//	public int getMinimumDifference(TreeNode root) {
//
//		if (root ==null) return -1;
//
//		getMinimumDifference(root.left);
//
//		if (prev !=-1 ) {
//			min = Math.min(min, root.val-prev);
//		}
//		prev = root.val;
//
//		getMinimumDifference(root.right);
//
//		return min;
//	}

	//543. Diameter of Binary Tree
	public int diameterOfBinaryTree(TreeNode root) {
		if (root == null ) return 0;

		int leftheight = heightOfTree(root.left);
		int rightheight = heightOfTree(root.right);


		return leftheight+rightheight;

	}

	int heightOfTree(TreeNode root) {

		if (root == null ) return 0;

		int lheight = heightOfTree(root.left);
		int rheight = heightOfTree(root.right);

		return Math.max(lheight,rheight) +1 ;
	}

	boolean left = false ; 
	boolean right = false ;
	public boolean isSubtree1(TreeNode s, TreeNode t) {

		if(s == null || t == null) return false ;


		if (t ==s ) {

			left = true;
			right = true;
			isSubtree(s.left, t.left);
			isSubtree(s.right, t.right);
		}else {

			left = false ;
			right =false;
			isSubtree(s.left, t);
			isSubtree(s.right, t);
		}
		return left && right ;

	}


	public boolean isSubtree(TreeNode s, TreeNode t) {

		// look for t's root in s 
		// if found ,
		//then run a BFS with each node of t 


		Queue<TreeNode> queue = new LinkedList<>();

		queue.add(s);
		TreeNode currentNode;
		while(!queue.isEmpty()) {

			for(int i =0 ; i<queue.size() ; i++) {
				currentNode = queue.remove();
				if (currentNode == t) {
					break;
				}


			}
		}

		queue.clear();
		queue.add(s);

		while(!queue.isEmpty()) {
			for(int i =0 ; i<queue.size() ; i++) {
				currentNode = queue.remove();
				if (currentNode == t) {
					break;
				}


			}
		}
		return left;

	}

	//589 - N array preorder traversal 

	class NArrayNode {
		public int val;
		public List<NArrayNode> children;

		public NArrayNode() {}

		public NArrayNode(int _val) {
			val = _val;
		}

		public NArrayNode(int _val, List<NArrayNode> _children) {
			val = _val;
			children = _children;
		}
	}

	NArrayNode getnArrayNode(int val,List<NArrayNode> _children ) {
		return new NArrayNode(val,_children);
	}

	List<Integer> gList= new ArrayList<>();

	public List<Integer> preorder(NArrayNode root) {

		if (root == null) return new ArrayList<>();
		gList.add(root.val);
		for (int i = 0 ; i < root.children.size() ; i++) {

			preorder(root.children.get(i));
		}
		return gList;
	}

	//606. Construct String from Binary Tree
	StringBuffer str = new StringBuffer();
	public String tree2str(TreeNode t) {

		if (t == null) return str.toString();

		str.append("("+t.val);
		tree2str(t.left);
		tree2str(t.right);
		str.append(")");
		return str.toString();
	}

	//617. Merge Two Binary Trees

	public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {


		if (root1 == null && root2 == null ) {
			return null;
		}
		if (root1 ==null) {
			return root2;
		}
		if (root2 == null) {
			return root1;
		}

		root1.left = mergeTrees(root1.left, root2.left);
		root1.right = mergeTrees(root1.right, root2.right);

		root1.val += root2.val;
		return root1;


	}

	//637. Average of Levels in Binary Tree

	public List<Double> averageOfLevels(TreeNode root) {

		List<Double> averageList = new ArrayList<>();
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);

		while(!queue.isEmpty()) {
			double totalnode = queue.size();
			double sum = 0;
			for (int i = 0 ; i < totalnode; i++) {
				TreeNode currentnode = queue.remove();
				if (currentnode.left != null)queue.add(currentnode.left);
				if (currentnode.right != null)queue.add(currentnode.right);

				sum += currentnode.val;

			}

			double average = sum/totalnode ;
			averageList.add(average);

		}

		return averageList ;

	}

	// 700 search in a binary tree 
	public TreeNode searchBST(TreeNode root, int val) {
		while(root != null && root.val != val){
			root = val<root.val? root.left:root.right;
		}
		return root;
	}

	// office work  -- pills logic for filter section 
	Stack pillsForFilterArea(String[] filterArray, int oneRowCharCapacity, int rows) {

		Stack<String> stackOfDisplaypills = new Stack<>();
		Stack<String> stackOfElipsisList = new Stack<>();
		int charCount =0;
		for (int i =0 ; i < filterArray.length ; i++) {
			charCount += filterArray[i].length();
			if(charCount < rows*oneRowCharCapacity) {
				stackOfDisplaypills.push(filterArray[i]); // use this of displaying pills 
			}else {
				stackOfElipsisList.push (filterArray[i]); // use this for elipsis list.
			}

		}
		return stackOfDisplaypills;
	}

	//1022. Sum of Root To Leaf Binary Numbers
	public int sumRootToLeaf(TreeNode root) {

		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		
		Stack<String> stringStack = new Stack<>();
		
		String string = ""; 
		stringStack.push(Integer.toString(root.val));
		int sum = 0;
		
 		while(!stack.isEmpty()) {
			TreeNode currentNode = stack.pop();
			String rootString = stringStack.pop(); 
			System.out.println("------"+rootString);
			
			
			if(currentNode.left == null && currentNode.right == null) {
				System.out.println(rootString);
				sum += Integer.parseInt(rootString.toString(),2);
				
			}
			
			if (currentNode.left !=null) {
				stack.push(currentNode.left);
				string = rootString+Integer.toString(currentNode.left.val);
				stringStack.push(string);
				
			}
			if(currentNode.right != null) {
				stack.push(currentNode.right);
				string = rootString+ Integer.toString(currentNode.right.val);
				stringStack.push(string);
			}
		}
		
 		return sum;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LC_graph_tree_easy ins = new LC_graph_tree_easy();
		
		
		// 1022
		//root = [1,0,1,0,1,0,1]
						TreeNode root = ins.getTreeNode(1);
						 root.left = ins.getTreeNode(0);
						root.right = ins.getTreeNode(1);
						root.left.left = ins.getTreeNode(0);
						root.left.right = ins.getTreeNode(1);
						root.right.left = ins.getTreeNode(0);
						root.right.right = ins.getTreeNode(1);
						System.out.println(ins.sumRootToLeaf(root));
		
		
 //office work - telstra Ui -- filter pills logic 
//		String[] filterlabels = {"billing ", "billing due ", "new bill arrieved ", "abcnnnnnnnndef", "abvvvvvvvcdefghijkl"};
//		Stack displayPills = ins.pillsForFilterArea(filterlabels , 50, 1);
////		while(!displayPills.isEmpty()) {
////			System.out.println(displayPills.pop());
////		}
//		System.out.println(displayPills);

		//637. Average of Levels in Binary Tree
		//root = [3,9,20,null,15,7]
		//		TreeNode root = ins.getTreeNode(3);
		//		 root.left = ins.getTreeNode(9);
		//		root.right = ins.getTreeNode(20);
		//		root.left.right = ins.getTreeNode(15);
		//		root.right.left = ins.getTreeNode(7);
		//		
		//		System.out.println(ins.averageOfLevels(root));
		//		

		//		//tree1
		//		TreeNode root1 = ins.getTreeNode(1);
		//		root1.left = ins.getTreeNode(2);
		//		root1.right = ins.getTreeNode(3);
		//		//tree2
		//		TreeNode root2 = ins.getTreeNode(4);
		//		root2.left = ins.getTreeNode(5);
		//		root2.right = ins.getTreeNode(6);
		//		TreeNode mergeTree = ins.mergeTrees(root1, root2);

		//		System.out.println(mergeTree.val +" "+ mergeTree.left.val +" " + mergeTree.right.val);


		//		TreeNode root = ins.getTreeNode(1);
		//		root.left = ins.getTreeNode(2);
		//		root.right = ins.getTreeNode(3);
		//		//root.left.left = ins.getTreeNode(2);
		//		root.left.right = ins.getTreeNode(4);
		//		String str  = ins.tree2str(root);
		//		System.out.println(str);

		//589
		//		NArrayNode root = ins.getnArrayNode(1,new ArrayList<NArrayNode>());
		//		NArrayNode node1 = ins.getnArrayNode(3,new ArrayList<NArrayNode>());
		//		NArrayNode node2 = ins.getnArrayNode(2,new ArrayList<NArrayNode>());
		//		NArrayNode node3 = ins.getnArrayNode(4,new ArrayList<NArrayNode>());
		//		NArrayNode node4 = ins.getnArrayNode(5,new ArrayList<NArrayNode>());
		//		NArrayNode node5 = ins.getnArrayNode(6,new ArrayList<NArrayNode>());
		//
		//		root.children.add(node1);
		//		root.children.add(node2);
		//		root.children.add(node3);
		//		node3.children.add(node4);
		//		node3.children.add(node5);
		//
		//		List<Integer> ansList = ins.preorder(root);
		//
		//		System.out.println(ansList);





		//LC_graph_tree_easy ins = new LC_graph_tree_easy();

		//543
		//		TreeNode root = ins.getTreeNode(1);
		//		root.left = ins.getTreeNode(2);
		//		root.right = ins.getTreeNode(3);
		//		root.left.left = ins.getTreeNode(2);
		//		root.left.right = ins.getTreeNode(2);
		//		int dia  = ins.diameterOfBinaryTree(root);
		//		System.out.println(dia);

		//501. Find Mode in Binary Search Tree
		//		TreeNode root = ins.getTreeNode(1);
		//		root.left = ins.getTreeNode(2);
		//		root.right = ins.getTreeNode(3);
		//		root.left.left = ins.getTreeNode(2);
		//		root.left.right = ins.getTreeNode(2);
		//		int[] array  = ins.findMode(root);
		//		for (int a : array)System.out.println(a);

		//257. Binary Tree Paths
		//[1,2,3,5,6]
		//		TreeNode root = ins.getTreeNode(1);
		//		root.left = ins.getTreeNode(2);
		//		root.right = ins.getTreeNode(3);
		//		root.left.left = ins.getTreeNode(5);
		//		root.left.right = ins.getTreeNode(6);
		//		List<String> list  = ins.binaryTreePaths(root);
		//		System.out.println(list);
		//[1,2,3,null,null,4]
		/*
		 * 			1
		 * 		2		3
		 * 	-		-|4		-
		 */
		//		TreeNode root = ins.getTreeNode(1);
		//		root.left = ins.getTreeNode(2);
		//		root.right = ins.getTreeNode(3);
		//		root.right.left = ins.getTreeNode(4);
		//		List<String> list  = ins.binaryTreePaths(root);
		//		System.out.println(list);
		//Input: root = [1,2,3,null,5]
		//		TreeNode root = ins.getTreeNode(1);
		//		root.left = ins.getTreeNode(2);
		//		root.right = ins.getTreeNode(3);
		//		root.left.right = ins.getTreeNode(5);
		//		List<String> list  = ins.binaryTreePaths(root);
		//		System.out.println(list);
		//[4,2,7,1,3,6,9]
		/*
		 * 					4
		 * 				2		7
		 * 			1		3|6		9
		 */
		//		TreeNode root = ins.getTreeNode(4);
		//		root.left = ins.getTreeNode(2);
		//		root.right = ins.getTreeNode(7);
		//		root.left.left = ins.getTreeNode(1);
		//		root.left.right = ins.getTreeNode(3);
		//		root.left.right.right = ins.getTreeNode(10);
		//		root.right.left = ins.getTreeNode(6);
		//		root.right.right = ins.getTreeNode(9);
		//		
		//		// 235
		//		TreeNode ans  = ins.lowestCommonAncestor(root,root.right.left,root.right.right);
		//		System.out.println(ans.val);

		// 235 failing for this use case - [6,2,8,0,4,7,9,null,null,3,5]

		/*
		 * 								6
		 * 				2								8
		 * 		0				4				7				9
		 * null		null	3		5
		 */

		//		TreeNode root = ins.getTreeNode(6);
		//		root.left = ins.getTreeNode(2);
		//		root.right = ins.getTreeNode(8);
		//		root.left.left = ins.getTreeNode(0);
		//		root.left.right = ins.getTreeNode(4);
		//		root.right.left = ins.getTreeNode(7);
		//		root.right.right = ins.getTreeNode(9);
		//		root.left.right.left = ins.getTreeNode(3);
		//		root.left.right.right = ins.getTreeNode(5);
		//		TreeNode ans  = ins.lowestCommonAncestor(root,root.left.right.left,root.left.right.right);
		//		System.out.println(ans.val);

		//		TreeNode root = ins.getTreeNode(2);
		//		root.left = ins.getTreeNode(1);
		//		root.right = ins.getTreeNode(3);

		//		TreeNode ans = ins.invertTree(root);
		//		System.out.println(ans.val +" "+ ans.left.val +" "+ ans.right.val);

		//ins.dfs(root);
		//ins.bfs(root);

	}

}
