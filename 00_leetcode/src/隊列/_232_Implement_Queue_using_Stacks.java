package 隊列;

import java.util.Stack;

public class _232_Implement_Queue_using_Stacks {

    private Stack<Integer> inStack;
    private Stack<Integer> outStack;
    public _232_Implement_Queue_using_Stacks() {
        inStack = new Stack<>();
        outStack = new Stack<>();
    }

    public void push(int x) {
        inStack.push(x);
    }

    public int pop() {
        checkOutStack();
        return outStack.pop();
    }

    public int peek() {
        checkOutStack();
        return outStack.peek();
    }

    public boolean empty() {
        return outStack.isEmpty() && inStack.isEmpty();
    }

    private void checkOutStack(){
        if (outStack.isEmpty()){
            while (!inStack.isEmpty()){
                outStack.push(inStack.pop());
            }
        }
    }
}
