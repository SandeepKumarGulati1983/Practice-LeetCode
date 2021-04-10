package LeetCodeSolution;


import myUtils.ListNode;

public class ListQuestions {

	//21. Merge Two Sorted Lists

	public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
		if(l1 == null) return l2;
		if(l2 == null) return l1;
		if(l1.val < l2.val){
			l1.next = mergeTwoLists1(l1.next, l2);
			return l1;
		} else{
			l2.next = mergeTwoLists1(l1, l2.next);
			return l2;
		}
	}

	// without recursion  - iterative approach with a temp node 
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

		// by using a dummy list 

		ListNode dummyList = new ListNode(0);  // dummy node for giving same reference
		ListNode parentOfDummyList = dummyList;


		while (true) {

			if (l1 == null) {
				dummyList.next = l2 ;
				break;
			}

			if (l2 == null ) {
				dummyList.next = l1 ; 
				break;
			}

			if (l1.val < l2.val ) {

				dummyList.next = l1;
				l1 = l1.next;
			} else {
				dummyList.next = l2 ;
				l2 = l2.next;
			}

			dummyList = dummyList.next;

		}
		return parentOfDummyList.next;

	}

	public ListNode deleteDuplicates(ListNode head) {



		ListNode parent = head ;

		while(true) {
			if (head == null || head.next == null) return parent;
			if (head.val == head.next.val) {
				head.next = head.next.next;
			}else head = head.next;

		}

	}


	// 141. Linked List Cycle  -- its Floyd's cycle detection methord ... fast and slow pointer approch 
	public boolean hasCycle1(ListNode head) {


		if (head == null || head.next == null) return false;
		ListNode slowPointer= head; 
		ListNode fastpointer = head;

		while (true) {

			if (slowPointer == null || fastpointer == null || fastpointer.next == null  ) {
				return false;
			}
			slowPointer = slowPointer.next;
			fastpointer = fastpointer.next.next;



			if (slowPointer.val == fastpointer.val) return true;

		}



	}

	/*
	 * above code is giving null pointer error in line 94  , which can be resolved via checking null pointer again before val 
	 * but that will be a repeat of code , better is to check once and then compare the node instead of values .
	 */

	public boolean hasCycle(ListNode head) {
		if (head == null || head.next == null) return false;
		ListNode slowPointer= head; 
		ListNode fastpointer = head;

		while (slowPointer != null && fastpointer != null && fastpointer.next != null ) {

			slowPointer = slowPointer.next;
			fastpointer = fastpointer.next.next;

			if (slowPointer == fastpointer) return true;  // comparing nodes and not its value 

		}
		return false;
	}


	//160. Intersection of Two Linked Lists

	public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {

		// searching one list elemnt in another list  -- oN2 approch 

		ListNode tempHead ;
		while  (headA != null ) {

			tempHead = headB;
			while (tempHead != null) {
				if (headA == tempHead) {
					return tempHead;
				}else tempHead = tempHead.next;
			}
			headA = headA.next;

		}
		return headA;
	}

	// O(n) approach is -- difference of 2 node approach 

	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

		ListNode headATail = headA;
		ListNode headBTail = headB;

		int listASize , listbSize, diff;
		listASize =0;
		listbSize = 0 ;
		diff =0 ;

		if (headA == null) return null;
		if (headB == null) return null;

		while (headATail != null) {
			headATail = headATail.next;
			listASize++;
		}



		while (headBTail != null) {
			headBTail = headBTail.next;
			listbSize++;
		}


		if (listASize >listbSize) {
			diff = listASize - listbSize;

			for (int i = 0 ; i < diff ; i++) {
				headA = headA.next;
			}

		}else if (listbSize > listASize) {
			diff = listbSize -listASize;

			for (int i = 0 ; i < diff ; i++) {
				headB = headB.next;
			}
		}



		while (headA !=null ) {

			if (headA == headB) return headB;

			headA = headA.next;
			headB = headB.next;
		}

		return null;

	}

	// 203. Remove Linked List Elements
	public ListNode removeElements(ListNode head, int val) {

		ListNode firstNode  = head;
		ListNode parent = head;
		if (firstNode == null) return head;

		while (head != null) {

			if (head.val == val) {
				if (head == firstNode ) {
					firstNode = firstNode.next;
				}
				parent.next = head.next;

			}else {
				parent = head ;
			}

			head = head.next;

		}
		return firstNode;

	}

	// 206. Reverse Linked List

	public ListNode reverseList(ListNode head) {

		ListNode parent = head ;
		ListNode temp = head ;

		if (head == null) return head ;

		head = head.next;
		parent.next = null ;
		while (head != null ) {

			temp = parent ; // 1 null 
			parent = head ; // 2
			head = head.next;
			parent.next  = temp; // 2 1 null 


		}

		return parent ;
	}

	//234. Palindrome Linked List
	public boolean isPalindrome1(ListNode head) {

		// reach to mid 
		// then after that reverse the link list 
		// then start comapring the item with first half 

		ListNode iforCounting = head ;
		ListNode tail = head;

		if (head.next == null ) return true ;

		int listSize = 0;
		while (iforCounting != null) {
			listSize++;
			iforCounting = iforCounting.next;
		}

		if (listSize%2 !=0) {
			listSize++ ;
		}


		// move head to mid of the list 
		for (int i = 0 ;i < listSize/2 ; i ++) {
			head = head.next;
		}

		// now reverse the linklist till head 
		ListNode reverseHead = reverseList(head) ;

		while (reverseHead != null ) {
			if (tail.val != reverseHead.val) return false ;
			tail = tail.next;
			reverseHead = reverseHead.next ;
		}

		return true;


	}

	// now in above solution , for reaching to end , we are traversing to compleate list for count
	// but actually we just need to traverse till middle 
	// and for that we can use slow and fast pointer , 

	public boolean isPalindrome2(ListNode head) {

		ListNode slow  = head , fast = head ;

		if (head.next == null ) return true ;

		while(fast != null && fast.next != null ) {
			slow = slow.next;
			fast = fast.next.next;
		}

		// for odd size 
		if (fast != null ) slow = slow.next;


		// now reverse the linklist till head 
		ListNode reversedHead = reverseList(slow) ;

		while (reversedHead != null ) {
			if (head.val != reversedHead.val) return false ;
			head = head.next;
			reversedHead = reversedHead.next ;
		}

		return true;

	}

	// 237. Delete Node in a Linked List

	public void deleteNode(ListNode node) {


		node = node.next;

	}


	// 876. Middle of the Linked List
	public ListNode middleNode(ListNode head) {

		ListNode slow = head;
		ListNode fast = head ;

		while (fast != null && fast.next != null) {
			slow = slow.next ;
			fast = fast.next.next ;
		}


		//if (fast!= null )
		return slow;
		
		//return slow = slow.next;
	}

	//1290. Convert Binary Number in a Linked List to Integer
	 public int getDecimalValue(ListNode head) {
	        
		 ListNode tail = head ;
		 int count = 0 ;
		 // count 
		 while (tail!= null) {
			 tail = tail.next;
			 count++;
		 }
		 
		 int numberVal = 0;
		 for(int i = count ; i > 0 ; i--) {
			 numberVal += head.val* Math.pow(2,i-1);
			 head = head.next;
			 
		 }
			 return numberVal;
		 
	    }

public static void main(String[] args) {

	ListQuestions mainClass = new ListQuestions();

	//1290. Convert Binary Number in a Linked List to Integer
	ListNode list = new ListNode(1, new ListNode(0,new ListNode(1,null)));
	int ans = mainClass.getDecimalValue(list);
		System.out.println(ans);
	// 876. Middle of the Linked List
//	ListNode list = new ListNode(1, new ListNode(0,new ListNode(1,null)));
//	ListNode ans = mainClass.middleNode(list);
//		System.out.println(ans.val);


	//234. Palindrome Linked List
	//ListNode list = new ListNode(1, new ListNode(2,new ListNode(3,new ListNode(4, new ListNode(5,new ListNode(6,new ListNode(7,null)))))));
	//ListNode list = new ListNode(1, new ListNode(2,new ListNode(1,null)));
	//		boolean ans = mainClass.isPalindrome(list);
	//		System.out.println(ans);


	// 206. Reverse Linked List
	//		ListNode list = new ListNode(1, new ListNode(2,new ListNode(3,new ListNode(4, new ListNode(5,new ListNode(6,new ListNode(7,null)))))));
	//		ListNode ans = mainClass.reverseList(list);
	// 203. Remove Linked List Elements
	//		ListNode list = new ListNode(1, new ListNode(2,new ListNode(3,new ListNode(4, new ListNode(5,new ListNode(6,new ListNode(7,null)))))));

	//		ListNode list = new ListNode(1, new ListNode(2,new ListNode(3,null)));
	//		
	//		ListNode list = new ListNode(3, new ListNode(2,new ListNode(1,null)));

	//		ListNode list = new ListNode(1, new ListNode(3,new ListNode(3,null)));
	//		ListNode list = new ListNode(3, new ListNode(3,new ListNode(1,null)));
	// 13333
	// 33331 
	// 333
	// 131313

	//		ListNode ans = mainClass.removeElements(list, 3);

	//		while (ans != null) {
	//			System.out.println(ans.val);
	//			ans=ans.next;
	//		}
	//System.out.println(ans.val);

	//160. Intersection of Two Linked Lists
	//listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], 
	//		ListNode commonList = new ListNode(8, new ListNode(4,new ListNode(5,null)));
	//		ListNode a = new ListNode(4, new ListNode(1,commonList));
	//		ListNode b = new ListNode(5, new ListNode(6,new ListNode (1,commonList))); 
	//
	//		//		ListNode commonList = new ListNode(1, null);
	//		//		ListNode a = commonList;
	//		//		ListNode b = commonList; 
	//
	//		ListNode ans  = mainClass.getIntersectionNode(a,b);
	//		System.out.println(ans.val);

	// 141. Linked List Cycle
	//		ListNode n4 = new ListNode(-4,n2);s
	//		ListNode n3 = new ListNode(0,n4);
	//n2 = new ListNode(2,new ListNode(0,new ListNode(-4,n2)));
	//		ListNode n1 = new ListNode(3,new ListNode(2,new ListNode(0,new ListNode(-4,null))));
	//		n1.next.next.next = n1.next; // for making a cycle 
	//
	//		boolean ans = mainClass.hasCycle(n1);
	//		System.out.println(ans);


	//83. Remove Duplicates from Sorted List
	//		ListNode n1 = new ListNode(1, new ListNode(2, new ListNode(2,null)));
	//		ListNode n1 = new ListNode(2, new ListNode(2, new ListNode(2,new ListNode(2,null))));
	//		ListNode ans = mainClass.deleteDuplicates(n1);

	//21. Merge Two Sorted Lists

	//		ListNode n1 = new ListNode(1, new ListNode(2, new ListNode(4,null)));
	//		ListNode n2 = new ListNode(3, new ListNode(4, new ListNode(5,null)));
	//		ListNode ans = mainClass.mergeTwoLists(n1,n2);

	//		while (ans.next != null) {
	//			System.out.println(ans.val);
	//			ans=ans.next;
	//		}
	//		System.out.println(ans.val);


	// way to convert an Integer array to linked List 
	//		LinkedList<Integer> ll1 = new LinkedList<>();
	//		LinkedList<Integer> ll2 = new LinkedList<>();
	//
	//		int[] a = {1,2,3};
	//		int [] b = {1,3,4};
	//
	//		for (int i = 0; i <a.length ; i++ ) {
	//			ll1.add(a[i]);
	//		}
	//
	//		for (int j = 0 ; j < b.length ; j++) {
	//			ll2.add(b[j]);
	//		}



	// way to convert a String array to linked list 

	//		String[] s1 = {"a","b"};
	//		String[] s2 = {"c","d"};
	//
	//		LinkedList<String> sll1 = new LinkedList<>();
	//		Collections.addAll(sll1, s1);
	//
	//		List<String> list2 = Arrays.asList(s2);
	//		LinkedList<String> all2 = new LinkedList<>(list2);
	//
	//
	//
	//		System.out.println(ll1);

}





}
