package ctci.Ch3;

public class MyQueue {
    StackTL stackEnter;
    StackTL stackLeave;

    public MyQueue() {
        stackEnter = new StackTL();
        stackLeave = new StackTL();
    }
    
    public void add(int val) {
        stackEnter.push(val);
    }

    public int remove() {
        if (stackLeave.isEmpty()) {
            while(!stackEnter.isEmpty()) {
                stackLeave.push(stackEnter.pop());
            }
        }
        return stackLeave.pop();
    }

    public int peek() {
        if (stackLeave.isEmpty()) {
            while(!stackEnter.isEmpty()) {
                stackLeave.push(stackEnter.pop());
            }
        }
        return stackLeave.peek();
    }

    public boolean isEmpty() {
        return stackEnter.isEmpty() && stackLeave.isEmpty();
    }
}
