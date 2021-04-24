package LeetCodeSolution;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class TreeRevision_23April {

	// 100 - recursive 
	public boolean isSameTree(TreeNode p, TreeNode q) {
		if(p == null && q == null) return true;
		if(p == null || q == null) return false;
		if(p.val == q.val)
			return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
		return false;
	}

	// 100 - iterative  -- via using queue 
	public boolean isSameTree_iterative(TreeNode p, TreeNode q) {
		Queue<TreeNode> queue = new LinkedList<>();
		if (p == null && q == null)
			return true;
		else if (p == null || q == null)
			return false;
		if (p != null && q != null) {
			queue.offer(p);
			queue.offer(q);
		}
		while (!queue.isEmpty()) {
			TreeNode first = queue.poll();
			TreeNode second = queue.poll();
			if (first == null && second == null)
				continue;
			if (first == null || second == null)
				return false;
			if (first.val != second.val)
				return false;
			queue.offer(first.left);
			queue.offer(second.left);
			queue.offer(first.right);
			queue.offer(second.right);
		}
		return true;
	}

	// 101 - recursive 
	public boolean isSymmetric(TreeNode root) {
		return isMirror(root, root);
	}

	public boolean isMirror(TreeNode t1, TreeNode t2) {
		if (t1 == null && t2 == null) return true;
		if (t1 == null || t2 == null) return false;
		return (t1.val == t2.val)
				&& isMirror(t1.right, t2.left)
				&& isMirror(t1.left, t2.right);
	}

	// 101 iterative 
	public boolean isSymmetric_iterative(TreeNode root) {
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		q.add(root);
		while (!q.isEmpty()) {
			TreeNode t1 = q.poll();
			TreeNode t2 = q.poll();
			if (t1 == null && t2 == null) continue;
			if (t1 == null || t2 == null) return false;
			if (t1.val != t2.val) return false;
			q.add(t1.left);
			q.add(t2.right);
			q.add(t1.right);
			q.add(t2.left);
		}
		return true;
	}


	//104 -- recursive solution 
	public int maxDepth(TreeNode root) {
		if(root==null){
			return 0;
		}
		return 1+Math.max(maxDepth(root.left),maxDepth(root.right));
	}

	//104 - iterative one  -- BFS and DFS 

	//108 - recursive solution 
	public TreeNode sortedArrayToBST(int[] num) {
		if (num.length == 0) {
			return null;
		}
		TreeNode head = helper(num, 0, num.length - 1);
		return head;
	}

	public TreeNode helper(int[] num, int low, int high) {
		if (low > high) { // Done
			return null;
		}
		int mid = (low + high) / 2;
		TreeNode node = new TreeNode(num[mid]);
		node.left = helper(num, low, mid - 1);
		node.right = helper(num, mid + 1, high);
		return node;
	}


	//110 - recursive 
	boolean isBalanced = true;
	public boolean isBalanced(TreeNode root) {

		if (root == null) return true;
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

	//111 - recursive solution 
	public class Solution {
		public int minDepth(TreeNode root) {
			if(root == null) return 0;
			int left = minDepth(root.left);
			int right = minDepth(root.right);
			return (left == 0 || right == 0) ? left + right + 1: Math.min(left,right) + 1;

		}
	}

	// 111- iterative solution  - BFS returns 0 when incounter a null node 

	//112 - recursive -- good learning 
	public boolean hasPathSum(TreeNode root, int sum) {
		if(root == null) return false;

		if(root.left == null && root.right == null && sum - root.val == 0) return true;

		return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
	}

	// 112 - iterative solution via DFS 
	public boolean hasPathSum_dfs(TreeNode root, int sum) {
		Stack <TreeNode> stack = new Stack<> ();	    
		stack.push(root) ;	    
		while (!stack.isEmpty() && root != null){
			TreeNode cur = stack.pop() ;	
			if (cur.left == null && cur.right == null){	    		
				if (cur.val == sum ) return true ;
			}
			if (cur.right != null) {
				cur.right.val = cur.val + cur.right.val ;
				stack.push(cur.right) ;
			}
			if (cur.left != null) {
				cur.left.val = cur.val + cur.left.val;
				stack.push(cur.left);
			}
		}	    
		return false ;
	}

	// 226 - recursive solution 

	public TreeNode invertTree(TreeNode root) {
		if (root == null )return null;
		if (root.left == null && root.right == null) return root;

		TreeNode temp = root.left;
		root.left  =  invertTree(root.right);
		root.right = invertTree(temp);

		return root;
	}

	// best DFS solution 
	public TreeNode invertTree_DFS (TreeNode root) {
	    if (root == null) return null;
	    Queue<TreeNode> queue = new LinkedList<TreeNode>();
	    queue.add(root);
	    while (!queue.isEmpty()) {
	        TreeNode current = queue.poll();
	        TreeNode temp = current.left;
	        current.left = current.right;
	        current.right = temp;
	        if (current.left != null) queue.add(current.left);
	        if (current.right != null) queue.add(current.right);
	    }
	    return root;
	}
	// 235 - recursive approch 
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        // Value of current node or parent node.
        int parentVal = root.val;

        // Value of p
        int pVal = p.val;

        // Value of q;
        int qVal = q.val;

        if (pVal > parentVal && qVal > parentVal) {
            // If both p and q are greater than parent
            return lowestCommonAncestor(root.right, p, q);
        } else if (pVal < parentVal && qVal < parentVal) {
            // If both p and q are lesser than parent
            return lowestCommonAncestor(root.left, p, q);
        } else {
            // We have found the split point, i.e. the LCA node.
            return root;
        }
    }
	
	// 235 - iterative approch - use property of BSt and run a DFS /BFS
	
	
	//257 - recursive approch 
	public List<String> binaryTreePaths(TreeNode root) {
	    List<String> answer = new ArrayList<String>();
	    if (root != null) searchBT(root, "", answer);
	    return answer;
	}
	private void searchBT(TreeNode root, String path, List<String> answer) {
	    if (root.left == null && root.right == null) answer.add(path + root.val);
	    if (root.left != null) searchBT(root.left, path + root.val + "->", answer);
	    if (root.right != null) searchBT(root.right, path + root.val + "->", answer);
	}
	
	// 257 -- iterative solution - via another stack in DFS 
	
	//501  -- inorder and then a frequency array 
	
	//530 -- (If BST ) inorder traversal then substraction of adjacent value in an array . because it will be sorted 

	    int min = Integer.MAX_VALUE;
	    Integer prev = null;
	    
	    public int getMinimumDifference(TreeNode root) {
	        if (root == null) return min;
	        
	        getMinimumDifference(root.left);
	        
	        if (prev != null) {
	            min = Math.min(min, root.val - prev);
	        }
	        prev = root.val;
	        
	        getMinimumDifference(root.right);
	        
	        return min;
	    }
	    
	// 530 -- if not BST   -- For solution 2, if the tree is not BST, you can first traverse it in any order to get a list, and then sort it. It also takes O(NlgN) time, space O(N) without using the Java built-in TreeSet.

	    // 543  -- max left height + max right height 
	    
	        int max = 0;
	        
	        public int diameterOfBinaryTree(TreeNode root) {
	            maxDepth1(root);
	            return max;
	        }
	        
	        private int maxDepth1(TreeNode root) {
	            if (root == null) return 0;
	            
	            int left = maxDepth1(root.left);
	            int right = maxDepth1(root.right);
	            
	            max = Math.max(max, left + right);
	            
	            return Math.max(left, right) + 1;
	        }
	// 559 - BFS is a good solution , 
	        public int maxDepth(Node root) {
	            if(root == null) return 0;
	            
	            Queue<Node> queue = new LinkedList<>();
	            queue.offer(root);
	            
	            int depth = 0;
	            
	            while(!queue.isEmpty())
	            {
	                int size = queue.size();
	                
	                for(int i = 0; i < size; i++)
	                {
	                    Node current = queue.poll();
	                    for(Node child: current.children) queue.offer(child);
	                }
	                
	                depth++;
	            }
	            
	            return depth;
	        }
	    //563 -- post order DFS 
	        private int totalTilt = 0;

	        protected int valueSum(TreeNode node) {
	            if (node == null)
	                return 0;

	            int leftSum = this.valueSum(node.left);
	            int rightSum = this.valueSum(node.right);
	            int tilt = Math.abs(leftSum - rightSum);
	            this.totalTilt += tilt;

	            // return the sum of values starting from this node.
	            return node.val + leftSum + rightSum;
	        }

	        public int findTilt(TreeNode root) {
	            this.totalTilt = 0;
	            this.valueSum(root);
	            return this.totalTilt;
	        }
	        
	        //572 -- recursive solution 
	       
	            public boolean isSubtree(TreeNode s, TreeNode t) {
	                if (s == null) return false;
	                if (isSame(s, t)) return true;
	                return isSubtree(s.left, t) || isSubtree(s.right, t);
	            }
	            
	            private boolean isSame(TreeNode s, TreeNode t) {
	                if (s == null && t == null) return true;
	                if (s == null || t == null) return false;
	                
	                if (s.val != t.val) return false;
	                
	                return isSame(s.left, t.left) && isSame(s.right, t.right);
	            }
	  
	            
	            // 572  -- String solution  -- important 
	            
	            HashSet < String > trees = new HashSet < > ();
	            public boolean isSubtree1(TreeNode s, TreeNode t) {
	                String tree1 = preorder(s);
	                String tree2 = preorder(t);
	                return tree1.indexOf(tree2) >= 0;
	            }
	            public String preorder(TreeNode t) {
	                if (t == null) {
	                    return "null";
	                }
	                return "#"+t.val + " " +preorder(t.left)+" " +preorder(t.right);
	            }
	//-------------------Utility Classes ------------------------------------
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
	class Node {
	    public int val;
	    public List<Node> children;

	    public Node() {}

	    public Node(int _val) {
	        val = _val;
	    }

	    public Node(int _val, List<Node> _children) {
	        val = _val;
	        children = _children;
	    }
}
