package LeetCodeSolution;

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


	

	public static void main (String[] arg) {

		LC_top60List classIns = new LC_top60List();
		
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
