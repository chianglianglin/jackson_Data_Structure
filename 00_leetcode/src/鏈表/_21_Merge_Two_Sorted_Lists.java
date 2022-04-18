package 鏈表;

import java.util.List;

public class _21_Merge_Two_Sorted_Lists {
	public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
		if (list1 == null) return list2;
		if (list2 == null) return list1;

		ListNode newHead = new ListNode(0);
		ListNode cur = newHead;
		while (list1 != null && list2 != null){
			if (list1.val < list2.val){
				cur.next = list1;
				cur = cur.next;
				list1 = list1.next;
			}else{
				cur.next = list2;
				cur = cur.next;
				list2 = list2.next;
			}
		}
		if (list2 == null){
			cur.next = list1;
		}else if (list1 == null){
			cur.next= list2;
		}
		return newHead.next;
	}
}
