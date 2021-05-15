package LeetCodeSolution;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import LeetCodeSolution.LC_graph_tree_easy.TreeNode;

public class Google_prep_tree_graph {

	//112 - Path Sum 
	public boolean hasPathSum(TreeNode root, int targetSum) {
		if (root == null) {
			return false;
		}
		Stack<TreeNode> s = new Stack<>();
		Stack<Integer> sum = new Stack<>();
		s.push(root);
		sum.push(root.val);
		while (!s.isEmpty()) {
			TreeNode curr = s.pop();
			int path = sum.pop();
			if (curr.right != null) {
				s.push(curr.right);
				sum.push(path + curr.right.val);
			}
			if (curr.left != null) {
				s.push(curr.left);
				sum.push(path + curr.left.val);
			}
			if (curr.left == null && curr.right == null && path == targetSum) {
				return true;
			}
		}
		return false;
	}


	// 111 minimum depth 
	public int minDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}

		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		TreeNode cur = root;
		int depth = 1;

		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				cur = queue.poll();

				if (cur.left == null && cur.right == null) {
					return depth;
				}

				if (cur.left != null) {
					queue.offer(cur.left);
				}

				if (cur.right != null) {
					queue.offer(cur.right);
				}
			}

			depth++;
		}
		return depth;
	}

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

	//104. Maximum Depth of Binary Tree
	public int maxDepth(TreeNode root) {
		if (root == null) return 0;

		int leftDepth = maxDepth(root.left) + 1 ;
		int rightDepth = maxDepth(root.right) + 1  ;

		return Math.max(leftDepth,rightDepth);  
	}

	//100. Same Tree
	public boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null  && q == null ) return true;

		if (p == null || q == null ) return false ;

		if ( p.val ==q.val ) {

			return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);

		}

		return false;
	}

	//101. Symmetric Tree
	public boolean isSymmetric(TreeNode root) {
		if (root == null ) return true;

		return isSameTree1(root.left, root.right);
	}

	public boolean isSameTree1(TreeNode p, TreeNode q) {

		if (p == null  && q == null ) return true;

		if (p == null || q == null ) return false ;

		if ( p.val ==q.val ) {

			return isSameTree(p.left, q.right) && isSameTree(p.right, q.left); 
		}

		return false;

	}

	//124. Binary Tree Maximum Path Sum

	 int ans = Integer.MIN_VALUE;
	    public int maxPathSum(TreeNode root) {
	        recurse(root);
	        return ans;
	    }
	    
	    public int recurse(TreeNode root) {
			//base case
	        if(root == null) return 0;
			//postorder
	        int left = recurse(root.left); 
	        int right = recurse(root.right);
	        
			//anything that is negative will never help maximize the path , so lets ignore it
	        if(left < 0) left = 0;
	        if(right < 0) right = 0;
			
			//worst case - root is only considered , if left and right are negatives
	        int sum = root.val + left + right;
			//update global answer
	        ans = Math.max(sum, ans);
			//from here send to parent what is max (it will be either curr+leftMax or curr+rightMax , as path in one directional)
	        return Math.max(left + root.val, right + root.val);
	    }

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
}
