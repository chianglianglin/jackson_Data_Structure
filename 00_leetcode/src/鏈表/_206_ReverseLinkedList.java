package 鏈表;

/**
 * https://leetcode.com/problems/reverse-linked-list/
 */
public class _206_ReverseLinkedList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 頭節點
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        //if(head == null) return null just like return head
        //head的下一個節點為空 則只有一個節點返回節點就好
        //if(head.next == null) return head
        //整合為下面第一行
        //5->4->3->2->1
        if (head == null || head.next == null) return head;
        ListNode newHead = reverseList(head.next);
        //1->2->3->4->null
        head.next.next = head;
        //1->2->3->4->5
        head.next = null;
        //1->2->3->4->5->null
        return newHead;
    }

    public ListNode reverseList2(ListNode head) {
        //if(head == null) return null just like return head
        //head的下一個節點為空 則只有一個節點返回節點就好
        //if(head.next == null) return head
        //整合為下面第一行
        //5->4->3->2->1
        if (head == null || head.next == null) return head;

        ListNode newHead = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = newHead;
            newHead = head;
            head = temp;
        }

        return newHead;
    }
}
