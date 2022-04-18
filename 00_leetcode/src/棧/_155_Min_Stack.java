package 棧;

import java.util.Stack;

public class _155_Min_Stack {

//	private Stack<Integer> stack1;
//	private Stack<Integer> stack2;
//	public _155_Min_Stack() {
//				stack1 = new Stack<>();
//				stack2 = new Stack<>();
//	}
//
//	public void push(int val) {
//			stack1.push(val);
//			if (stack2.isEmpty()){
//				stack2.push(val);
//			}else{
//				stack2.push(Math.min(val,stack2.peek()));
//			}
//	}
//
//	public void pop() {
//		stack1.pop();
//		stack2.pop();
//	}
//
//	public int top() {
//		return stack1.peek();
//	}
//
//	public int getMin() {
//		return stack2.peek();
//	}
	private Node head;

	public _155_Min_Stack() {
		head = new Node(0,Integer.MAX_VALUE,null);
	}

	public void push(int val) {
		head = new Node(val, Math.min(val,head.min),head);
	}

	public void pop() {
		head = head.nextNode;
	}

	public int top() {
		return head.val;
	}

	public int getMin() {
		return head.min;
	}

	private static class Node{
		public int val;
		public int min;
		public Node nextNode;

		public Node(int val, int min, Node nextNode) {
			this.val = val;
			this.min = min;
			this.nextNode = nextNode;
		}
	}
}

