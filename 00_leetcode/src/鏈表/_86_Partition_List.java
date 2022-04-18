package 鏈表;

import java.util.List;

public class _86_Partition_List {
	public ListNode partition(ListNode head, int x) {
		if (head == null)return null;
		ListNode lHead = new ListNode(0);
		ListNode lTail = lHead;
		ListNode rHead = new ListNode(0);
		ListNode rTail = rHead;
		while (head != null){
			if (head.val < x){
				lTail.next = head;
				lTail = head;
			}else {
				rTail.next = head;
				rTail = head;
			}
			head = head.next;
		}
		rTail.next = null;
		lTail.next = rHead.next;
		return lHead.next;
	}
}
