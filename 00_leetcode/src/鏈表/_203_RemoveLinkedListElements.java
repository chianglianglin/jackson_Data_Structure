package 鏈表;

public class _203_RemoveLinkedListElements {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    public ListNode removeElements(ListNode head, int val) {
        //因為有可能刪除頭節點head 所以要設一個虛擬頭節點
        ListNode dumpNode = new ListNode(0);
        dumpNode.next = head;
//        用cur 表示当前节点。如果 cur 的下一个节点不为空且下一个节点的节点值等于给定的
//       val，则需要删除下一个节点。删除下一个节点可以通过以下做法实现：
//        ListNode cur = head;
        ListNode cur = dumpNode;
        while(cur.next != null ){
            if(cur.next.val == val){
                //刪除節點
                cur.next= cur.next.next;
            }else{
                cur = cur.next;
            }
        }
        return dumpNode.next;
    }
}
