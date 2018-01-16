package ctci.Ch3;

public class StackTL {
    private StackNode top;

    public StackTL() {
        top = null;
    }

    public StackTL(int val) {
        top = new StackNode(val);
    }

    public int pop(){
        int data = top.getData();
        top = top.getNext();
        return data;
    }

    public void push(int val) {
        StackNode newNode = new StackNode(val);
        newNode.setNext(top);
        top = newNode;
    }

    public int peek() {
        return top.getData();
    }

    public boolean isEmpty() {
        return top == null;
    }

    private class StackNode {
        private StackNode next = null;
        private int data;

        private StackNode() {
            this.data = 0;
        }

        private StackNode(int val) {
            this.data = val;
        }

        private StackNode getNext() {
            return this.next;
        }

        private void setNext(StackNode node) {
            this.next = node;
        }

        private int getData() {
            return this.data;
        }
    }
}
