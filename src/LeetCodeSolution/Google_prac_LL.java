package LeetCodeSolution;

public class Google_prac_LL {

	
	 // Definition for singly-linked list.
	  class ListNode {
	      int val;
	      ListNode next;
	      ListNode(int x) {
	          val = x;
	          next = null;
	      }
	  }
	 
	// list has a cycle 
	  public boolean hasCycle(ListNode head) {
	       if (head == null || head.next == null) return false;
			ListNode slowPointer= head; 
			ListNode fastpointer = head;

			while (slowPointer != null && fastpointer != null && fastpointer.next != null ) {

				slowPointer = slowPointer.next;
				fastpointer = fastpointer.next.next;
				
				if (slowPointer == fastpointer) return true;

			}
	        return false;
	    }
	
	//intersection point between 2 list 
	  public ListNode GetIntersectionNode(ListNode headA, ListNode headB)
	    {
	        int sizeA = GetSize(headA);
	        int sizeB = GetSize(headB);

	        for (int i = 0; i < Math.Abs(sizeA - sizeB); i++)
	        {
	            if (sizeA > sizeB)
	            {
	                headA = headA.next;
	            }
	            else
	            {
	                headB = headB.next;
	            }
	        }

	        while (headA != null && headB != null)
	        {
	            if (headA == headB)
	            {
	                return headA;
	            }

	            headA = headA.next;
	            headB = headB.next;
	        }

	        return null;
	    }

	    private int GetSize(ListNode head)
	    {
	        ListNode ptr = head;
	        int count = 0;

	        while (ptr != null)
	        {
	            count++;
	            ptr = ptr.next;
	        }

	        return count;
	    }
	}
}
