package 鏈表;


/**
 * https://leetcode.com/problems/delete-node-in-a-linked-list/
 */
public class _237_Delete_Node_in_a_Linked_List {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public void deleteNode(ListNode node) {
            //原本做法是找到當前節點的前一個節點讓他的下一個節點指向下下個節點
            //但這裡使用當前節點的值被後一個節點的值覆蓋
        //然後再讓當前節點指向下下個節點
       node.val = node.next.val;
        node.next = node.next.next;
    }
}
