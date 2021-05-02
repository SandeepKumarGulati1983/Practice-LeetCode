package LeetCodeSolution;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class LC_top60List {


	// 82 
	public ListNode deleteDuplicates(ListNode head) {
		if(head==null) return null;
		ListNode FakeHead=new ListNode(0);
		FakeHead.next=head;
		ListNode pre=FakeHead;
		ListNode cur=head;
		while(cur!=null){
			while(cur.next!=null&&cur.val==cur.next.val){
				cur=cur.next;
			}
			if(pre.next==cur){
				pre=pre.next;
			}
			else{
				pre.next=cur.next;
			}
			cur=cur.next;
		}
		return FakeHead.next;
	}

	// 20

	public boolean isValid(String s) {

		if (s.length() ==0) return false ;
		Stack<Character> stack = new Stack<>();

		for (int i = 0 ; i <s.length() ; i++) {

			char c = s.charAt(i);



			if (c == ')' || c== '}' || c == ']') {
				if (stack.isEmpty()) {
					return false ;
				}else {
					char open = stack.pop();

					if (c == ')' && open != '(')return false ;
					if (c == '}' && open != '{')return false ;
					if (c == ']' && open != '[')return false ;

				}

			}else {
				stack.push(c);
			}

		}
		return stack.isEmpty();

	}

	// 206 - recursive approch 
	public ListNode reverseList(ListNode head) {
		if (head == null || head.next == null) return head;
		ListNode p = reverseList(head.next);
		head.next.next = head;
		head.next = null;
		return p;
	}

	// 206  -- BEST  -- in memory reversal + iterative with senital node 


	//560. Subarray Sum Equals K
	public int subarraySum(int[] nums, int k) {

		
		int p1 = 0 ;
		int p2 = 1 ;
		int count= 0;
		
		while (p2< nums.length) {
			if (p1 ==p2 && nums[p1]==k)count++;
			if (nums[p1]+nums[p2] < k) p2++;
			if (nums[p1]+nums[p2] == k) count++;
			if (nums[p1]+nums[p2] > k && p1<p2) {
				p1++;
			}else p2++;
			
		}
		
		return count;
		
	}

	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		Set<String> beginSet = new HashSet<String>(), endSet = new HashSet<String>();

	    if(!wordList.contains(endWord)) return 0;
		int len = 1;
		int strLen = beginWord.length();
		
		HashSet<String> visited = new HashSet<String>();
		
		beginSet.add(beginWord);
		endSet.add(endWord);
		while (!beginSet.isEmpty() && !endSet.isEmpty()) {
			if (beginSet.size() > endSet.size()) {
				Set<String> set = beginSet;
				beginSet = endSet;
				endSet = set;
			}

			Set<String> temp = new HashSet<String>();
			for (String word : beginSet) {
				char[] chs = word.toCharArray();

				for (int i = 0; i < chs.length; i++) {
					for (char c = 'a'; c <= 'z'; c++) {
						char old = chs[i];
						chs[i] = c;
						String target = String.valueOf(chs);

						if (endSet.contains(target)) {
							return len + 1;
						}

						if (!visited.contains(target) && wordList.contains(target)) {
							temp.add(target);
							visited.add(target);
						}
						chs[i] = old;
					}
				}
			}

			beginSet = temp;
			len++;
		}
		
		return 0;
	}
	//35. Search Insert Position
	public int searchInsert(int[] A, int target) {
        int low = 0, high = A.length-1;
        while(low<=high){
            int mid = (low+high)/2;
            if(A[mid] == target) return mid;
            else if(A[mid] > target) high = mid-1;
            else low = mid+1;
        }
        return low;
    }
	
	public static void main (String[] arg) {

		LC_top60List classIns = new LC_top60List();

		
		//Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
		
		List<String> wordList = new ArrayList<>();
		wordList.add("hot");
		wordList.add("dot");
		wordList.add("dog");
		wordList.add("lot");
		wordList.add("log");
		wordList.add("cog");
		
		System.out.println(classIns.ladderLength("hit", "cog", wordList));
		
		
		System.out.println(classIns.isValid("(]"));
		System.out.println(classIns.isValid("()"));
		System.out.println(classIns.isValid(""));
		System.out.println(classIns.isValid("((((()))))"));
		System.out.println(classIns.isValid("(()(()())()())"));


		//		ListNode  head = classIns.getNode(1);
		//		head.next = classIns.getNode(2);
		//		head.next.next = classIns.getNode(3);
		//		head.next.next.next = classIns.getNode(3);
		//		head.next.next.next.next = classIns.getNode(4);
		//		head.next.next.next.next.next = classIns.getNode(4);
		//		head.next.next.next.next.next.next = classIns.getNode(5);
		//
		//		ListNode ans = classIns.deleteDuplicates(head) ;
		//		while (ans!=null) {
		//			System.out.println(ans.val);
		//			ans = ans.next;
		//		}


	}


	//============utelity classes
	public class ListNode {
		int val;
		ListNode next;
		ListNode() {}
		ListNode(int val) { this.val = val; }
		ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}

	ListNode getNode(int v) {
		return new ListNode(v);
	}

}
