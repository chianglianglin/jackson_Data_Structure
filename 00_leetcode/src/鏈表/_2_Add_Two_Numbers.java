package 鏈表;

public class _2_Add_Two_Numbers {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		if (l1 == null) return l2;
		if (l2 == null) return l1;
		ListNode dummyHead = new ListNode(0);
		ListNode cur = dummyHead;
		int carry = 0;
		while (l1 != null || l2 != null) {
			int v1 = 0;
			if (l1 != null){
				v1 = l1.val;
				l1 = l1.next;
			}
			int v2 = 0;
			if (l2 != null){
				v2 = l2.val;
				l2 = l2.next;
			}
			int sum = v1 + v2 + carry;
			carry = sum / 10;
			cur.next = new ListNode(sum % 10);
			cur = cur.next;
		}
		//檢查最後的進位
		if (carry > 0){
			cur.next = new ListNode(1);
		}
		return dummyHead.next;

	}
}
