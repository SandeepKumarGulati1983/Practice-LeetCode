package LeetCodeSolution;

import java.util.LinkedList;
import java.util.Queue;

import myUtils.TreeNode;

public class Tree_Leetcode {

	// 100. Same Tree
	public boolean isSameTree(TreeNode p, TreeNode q) {

		// need to check the height - for checking that both has same structure

		// need to check one by one each value

		if (p == null  && q == null ) return true;

		if (p == null || q == null ) return false ;

		if ( p.val ==q.val ) {

			//return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
			return isSameTree(p.left, q.right) && isSameTree(p.right, q.left); // open for problem 101 and close above line.
		}

		return false;

	}

	// 101. Symmetric Tree
	// recursive way , need to write an iterative way also.
	public boolean isSymmetric(TreeNode root) {

		if (root == null ) return true;

		return isSameTree(root.left, root.right);
	}

	//104. Maximum Depth of Binary Tree
	public int maxDepth(TreeNode root) {

		if (root == null) return 0;

		int leftDepth = maxDepth(root.left) + 1 ;
		int rightDepth = maxDepth(root.right) + 1  ;

		return Math.max(leftDepth,rightDepth);

	}

	// 108. Convert Sorted Array to Binary Search Tree  -- iterative approch 
	// not looks possible 
	//	    public TreeNode sortedArrayToBST(int[] nums) {
	//	    	
	//	    	int low = 0 ;
	//	    	int high = nums.length-1;
	//	    	
	//	    	TreeNode head = new TreeNode();
	//	    	int mid = high-low/2 ;
	//	    	head.val = nums[mid];
	//	    	//for left part
	//	    	while (low <mid) {
	//	    		head.left = new TreeNode(nums[mid-low/2]);
	//	    		head.right = new TreeNode (nums[high-mid/2]);
	//	    	}
	//	        
	//	    }

	public TreeNode sortedArrayToBST(int[] nums) {

		return arryToBST(nums, 0 , nums.length-1);
	}

	TreeNode arryToBST(int[] nums , int low , int high) {

		if (low > high) return null ;

		int mid = (high-low)/2 + low;

		TreeNode head = new TreeNode(nums[mid]);
		head.left = arryToBST(nums ,low, mid-1 );
		head.right = arryToBST(nums ,mid+1, high );

		return head;

	}
	//110. Balanced Binary Tree
	/*
	 * a binary tree in which the left and right subtrees of 
	 * every node differ in height by no more than 1.
	 */

	// o (n^2) solution ---------
	//	    public boolean isBalanced(TreeNode root) {
	//	     
	//	    	if (root == null) return true;
	//	    	
	//	    	if (Math.abs(heightOfTree(root.left) -heightOfTree(root.right)) >1) return false ;
	//	    	return isBalanced(root.left) && isBalanced(root.right);
	//	    }
	//	    
	//	    int heightOfTree(TreeNode node ) {   	
	//	    	if (node == null) return 0;   	
	//	    	int leftHeight = heightOfTree(node.left) +1 ;
	//	    	int rightHeight = heightOfTree(node.right) +1 ;    	
	//
	//	    	return Math.max(rightHeight,leftHeight );
	//	    }

	// o(n) solution -----
	boolean isBalanced = true;
	public boolean isBalanced(TreeNode root) {

		if (root == null) return true;
		//	    	
		//	    	if (Math.abs(heightOfTree(root.left) -heightOfTree(root.right)) >1) return false ;
		//	    	return isBalanced(root.left) && isBalanced(root.right);
		heightOfTree(root);
		return isBalanced;
	}

	int heightOfTree(TreeNode node ) {   	
		if (node == null) return 0;   	
		int leftHeight = heightOfTree(node.left) +1 ;
		int rightHeight = heightOfTree(node.right) +1 ;    	

		if (Math.abs(leftHeight-rightHeight) >1) isBalanced = false;

		return Math.max(rightHeight,leftHeight );
	}


	//111. Minimum Depth of Binary Tree
	public int minDepth2(TreeNode root) {

		if (root == null) {
			return 0 ;
		}


		int leftHeight = minDepth(root.left) + 1 ;
		int rightHeight = minDepth(root.right) + 1;

		//return Math.min(leftHeight, rightHeight);
		/*
		 * dont understand the reason for if part , but my return (above one ) is not workin g
		 * if tree has only right node or left node 
		 * [2,null,3,null,4,null,5,null,6]  expected value is 5 , which is actually not min , thats max .. i don't know why 
		 */
		return (leftHeight == 0 || rightHeight == 0 ? leftHeight+rightHeight + 1 :Math.min(leftHeight, rightHeight)+1);


	}

	// above solution is o(N^2) solution and space is also high because recursion take the stack 
	// for fast  solution always design a DFS queue to keep track of parent 

	public int minDepth1(TreeNode root) { 
		// this function is not giving the min depth , instead giving the max 
		// because we are adding all value in queue, so traversing till last 
		// we have to traverse to a complete level and if any node will be having 
		// both left and right node as null then we have to return 
		Queue<TreeNode> queue = new LinkedList<>();
		int level = 1 ;
		if (root!= null ) {
			queue.add(root );
		}else return 0;

		while (!queue.isEmpty()) {

			TreeNode node = queue.poll();

			if (node.left == null && node.right == null ) {
				return level ;
			}

			if (node.left != null) {
				queue.add (node.left);
			}else 

				if (node.right != null ) {
					queue.add(node.right);
				}
			System.out.println(queue.size());
			level++;

		}
		return level ;

	}

	public int minDepth(TreeNode root) { 

		Queue<TreeNode> queue = new LinkedList<>();
		int level = 1 ;
		if (root!= null ) {
			queue.add(root );
		}else return 0;

		while (!queue.isEmpty()) {

			for (int i = 0 ; i < queue.size() ; i++) { // this for loop is a modification of DFS for traversing the compelate level like BFS
				TreeNode node  = queue.poll();

				if (node.left == null && node.right == null ) {
					return level ;
				}

				if (node.left != null) {
					queue.add (node.left);
				}

				if (node.right != null ) {
					queue.add(node.right);
				}

			}
			level++;
		}
		return level ;

	}

	//112. Path Sum

	public boolean hasPathSum(TreeNode root, int targetSum) {

		if (root == null) return false ;
		
		if (root.val == targetSum && root.left == null && root.right == null) {
			return true;
		}
		
		return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tree_Leetcode solution = new Tree_Leetcode();

		//112. Path Sum


		//111
		//int[] a = {};
		//TreeNode root = solution.constructTree(, new TreeNode() , 0);


		//		int[] num = {-10,-3,0,5,9};
		//int[] arr = {1,2,2,3,null,null,3,4,-1,-1,4};
		////		int []arr = { 1, 2, 3, 4, 5, 6, 6, 6, 6 };
		//		
		//TreeNode node = solution.constructTree(arr, new TreeNode() , 0);

		//111. Minimum Depth of Binary Tree
		TreeNode testData = new TreeNode(1);
		TreeNode testData1 = new TreeNode(2);
		TreeNode testData2 = new TreeNode(3);
		TreeNode testData3 = new TreeNode(4);
		TreeNode testData4 = new TreeNode(5);
		testData.left = testData1;
		testData.right = testData2;
		testData1.left = testData3;
		testData1.right = testData4;

		// 112
		System.out.println(solution.hasPathSum(testData, 8));
		//		TreeNode testData = new TreeNode(1);
		//				testData.left = new TreeNode(2);
		//				testData.right = new TreeNode(2);
		//				testData.left.left = new TreeNode(3);
		//				testData.right.right = new TreeNode(3);
		//				testData.left.left.left = new TreeNode(4);
		//				testData.right.right.right = new TreeNode(4);
		//		---- 1
		//		  2----2
		//		 3-n  n-3
		//		4-n     n-4

		//System.out.println(solution.usingDFS(testData)); // [1,2,3,4,5] --> expected 2 

		//System.out.println(node.val +" "+ node.left.val +" "+ node.right.val+" "+ node.left.left.val +" "+ node.left.right.val);
		//solution.traverse(node);

		//		TreeNode testData = new TreeNode(3);
		////		TreeNode testData1 = new TreeNode(9);
		////		TreeNode testData2 = new TreeNode(20);
		////		TreeNode testData3 = new TreeNode(15);
		////		TreeNode testData4 = new TreeNode(7);
		//		TreeNode testData5 = new TreeNode(1);
		//		TreeNode testData6 = new TreeNode(2);
		//		TreeNode testData7 = new TreeNode(4);

		//TreeNode testData = new TreeNode();
		//		testData.left = testData1;
		//		testData.right = testData2;
		//		testData2.left = testData3;
		//		testData2.right = testData4;

		//		testData5.left = new TreeNode(2);
		//		testData5.right = new TreeNode(2);
		//		testData5.left.left = new TreeNode(3);
		//		testData5.left.right  = new TreeNode(3);
		//		
		//		testData5.left.left.left = new TreeNode(4);
		//		testData5.left.left.right  = new TreeNode(4);

		// [1,null,2,null,3]
		//		testData5.right = testData6;
		//		testData6.right = testData;

		//		--1
		//		n   2
		//		  n   3

		// [1,2,2,3,null,null,3,4,null,null,4]


		//110 
		//TreeNode p = new TreeNode(1,new TreeNode(2, null , new TreeNode(3)), new TreeNode(2, null , new TreeNode(3)));
		//System.out.println(solution.isBalanced(testData));

		// 108. Convert Sorted Array to Binary Search Tree  -- iterative approch
		//solution.traverse(solution.sortedArrayToBST(num));


		//104. Maximum Depth of Binary Tree
		//TreeNode p = new TreeNode(1,new TreeNode(2, null , new TreeNode(3)), new TreeNode(2, null , new TreeNode(3)));
		//		TreeNode p = new TreeNode(1,new TreeNode(2), new TreeNode(3));
		//		System.out.println(solution.maxDepth(p));

		// 101. Symmetric Tree
		//		TreeNode p = new TreeNode(1,new TreeNode(2, null , new TreeNode(3)), new TreeNode(2, null , new TreeNode(3)));
		//		System.out.println(solution.isSymmetric(p));

		//100. Same Tree
		//		TreeNode p = new TreeNode(1,new TreeNode(2), new TreeNode(3));
		//		TreeNode q = new TreeNode(1,new TreeNode(2), new TreeNode(3));
		//		System.out.println(solution.isSameTree(p,q));



	}

	// this is DFS in tree (it can be Inorder , preorder and postorder)
	void traverse(TreeNode node){

		if (node == null) {
			System.out.println("null");
			return;
		}
		traverse(node.left);
		System.out.println(node.val);
		traverse(node.right);



	}



	TreeNode constructTree(Integer[] arr , TreeNode root , int level) {

		if (level< arr.length) {
			if (arr[level] == null) {
				return null;
			}else {
				root = new TreeNode(arr[level]);
			}

			root.left = constructTree(arr, root , 2*level+1);
			root.right = constructTree(arr, root , 2*level+2);

		}

		return root ;

	}

}
