package 鏈表;

public class _234_Palindrome_Linked_List {
	public boolean isPalindrome(ListNode head) {
		if (head == null  || head.next == null )return true;
		if (head.next.next == null) return head.val == head.next.val;

		//找到中間節點
		ListNode mid = middleNode(head);
		//翻轉右半部分(中間節點的右邊部分)
		ListNode rHead = reverseList(mid.next);
		ListNode lHead = head;

		while( rHead != null){
			if (lHead.val != rHead.val)return false;
			rHead = rHead.next;
			lHead = lHead.next;
		}
		return true;
	}

	private ListNode reverseList(ListNode node){
		ListNode newHead = null;
		while (node != null){
			ListNode tmp = node.next;
			node.next = newHead;
			newHead = node;
			node = tmp;
		}
		return newHead;
	}

	private ListNode middleNode(ListNode node){
		ListNode slow = node;
		ListNode fast = node;
		while(fast.next != null && fast.next.next != null){
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	}
}
