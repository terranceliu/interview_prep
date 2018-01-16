package ctci.Ch3;

/** Implement a queue with two stacks */
public class QueueTL {
    private QueueNode first;
    private QueueNode last;

    public QueueTL() {
        first = null;
        last = null;
    }

    public QueueTL(int val) {
        QueueNode node = new QueueNode(val);
        first = node;
        last = node;
    }

    public void add(int val) {
        QueueNode node = new QueueNode(val);
        if (last != null)
            last.setNext(node);
        last = node;

        if (first == null) {
            first = last;
        }
    }

    public int remove() {
        int val = first.getData();
        first = first.getNext();
        if (first == null)
            last = first;
        return val;
    }

    public int peek() {
        return first.getData();
    }

    public boolean isEmpty() {
        return first == null;
    }


    private class QueueNode {
        private QueueNode next = null;
        private int data;

        private QueueNode() {
            this.data = 0;
        }

        private QueueNode(int val) {
            this.data = val;
        }

        private QueueNode getNext() {
            return this.next;
        }

        private void setNext(QueueNode node) {
            this.next = node;
        }

        private int getData() {
            return this.data;
        }
    }
}
