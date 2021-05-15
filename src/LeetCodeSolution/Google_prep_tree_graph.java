package LeetCodeSolution;

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
