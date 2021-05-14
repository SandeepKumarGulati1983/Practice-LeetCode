package LeetCodeSolution;

import java.util.Comparator;
import java.util.PriorityQueue;

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

		for (int i = 0; i < Math.abs(sizeA - sizeB); i++)
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

	// merge k sorted list 
	//https://www.geeksforgeeks.org/merge-k-sorted-linked-lists-set-2-using-min-heap/ 
	class Node {
		int data;
		Node next;
		Node(int data)
		{
			this.data = data;
			next = null;
		}
	}
	public static Node mergeKSortedLists(
			Node arr[], int k)
	{
		Node head = null, last = null;

		PriorityQueue<Node> pq= new PriorityQueue<>(
				new Comparator<Node>() {
					public int compare(Node a, Node b)
					{
						return a.data - b.data;
					}
				});

		// push the head nodes of all
		// the k lists in 'pq'
		for (int i = 0; i < k; i++)
			if (arr[i] != null)
				pq.add(arr[i]);

		// loop till 'pq' is not empty
		while (!pq.isEmpty()) {
			// get the top element of 'pq'
			Node top = pq.peek();
			pq.remove();

			// check if there is a node
			// next to the 'top' node
			// in the list of which 'top'
			// node is a member
			if (top.next != null)
				// push the next node in 'pq'
				pq.add(top.next);

			// if final merged list is empty
			if (head == null) {
				head = top;
				// points to the last node so far of
				// the final merged list
				last = top;
			}
			else {
				// insert 'top' at the end
				// of the merged list so far
				last.next = top;

				// update the 'last' pointer
				last = top;
			}
		}
		// head node of the required merged list
		return head;
	}
	
	// remove kth element from the end  -- using 2 pointer 
	 public ListNode removeNthFromEnd(ListNode head, int n) {
		    ListNode parent  = head;
		    //dummy.next = head;
		    ListNode first = head;
		    ListNode second = head;
		    // Advances first pointer so that the gap between first and second is n nodes apart
		    for (int i = 1; i <= n + 1; i++) {
		        if (first.next != null)first = first.next;
		    }
		    // Move first to the end, maintaining the gap
		    while (first != null) {
		        first = first.next;
		        second = second.next;
		    }
		    second.next = second.next.next;
		    return parent;
		}
	
}

