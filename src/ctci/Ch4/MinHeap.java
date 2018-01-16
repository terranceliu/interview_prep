package ctci.Ch4;

public class MinHeap {
    private int[] data;
    private int size;

    public MinHeap() {
        this.data = new int[10000];
        this.size = 0;
    }

    public MinHeap(int capacity) {
        this.data = new int[capacity];
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int getNodeVal(int node) {
        return this.data[node];
    }

    private void setNodeVal(int node, int val) {
        this.data[node] = val;
    }

    private int getLeftNode(int node) {
        return node*2 + 1;
    }

    private int getRightNode(int node) {
        return node*2 + 2;
    }

    private int getParentNode(int node) {
        return node/2;
    }

    /** O(log n) time, i.e. the height of the tree */
    public void insert(int val) {
        this.setNodeVal(this.size, val);
        this.bubbleUp(this.size);
        this.size++;
    }

    private void bubbleUp(int node) {
        if (node >= 0) {
            int nodeVal = this.getNodeVal(node);
            int parentNode = this.getParentNode(node);
            int parentNodeVal = this.getNodeVal(parentNode);

            if (nodeVal < parentNodeVal) {
                this.setNodeVal(parentNode, nodeVal);
                this.setNodeVal(node, parentNodeVal);
                this.bubbleUp(parentNode);
            }
        }
    }

    public int peek() {
        return this.data[0];
    }

    public int extract() {
        int minVal = getNodeVal(0);

        //move last element to the top
        size--;
        this.setNodeVal(0, this.getNodeVal(size));
        bubbleDown(0);

        return minVal;
    }

    private void bubbleDown(int node) {
        int nodeVal = this.getNodeVal(node);
        int leftNode = this.getLeftNode(node);
        int leftNodeVal = this.getNodeVal(leftNode);
        int rightNode = this.getRightNode(node);
        int rightNodeVal = this.getNodeVal(rightNode);

        // check if node has two children
        if (leftNode < size && rightNode < size) {
            if (leftNodeVal <= rightNodeVal) {
                if (nodeVal > leftNodeVal) {
                    this.setNodeVal(leftNode, nodeVal);
                    this.setNodeVal(node, leftNodeVal);
                    bubbleDown(leftNode);
                }
            }
            else {
                if (nodeVal > rightNodeVal) {
                    this.setNodeVal(rightNode, nodeVal);
                    this.setNodeVal(node, rightNodeVal);
                    bubbleDown(rightNode);
                }
            }
        }
        else if (leftNode < size) {
            if (nodeVal > leftNodeVal) {
                this.setNodeVal(leftNode, nodeVal);
                this.setNodeVal(node, leftNodeVal);
                bubbleDown(leftNode);
            }
        }
        else if (rightNode < size) {
            if (nodeVal > rightNodeVal) {
                this.setNodeVal(rightNode, nodeVal);
                this.setNodeVal(node, rightNodeVal);
                bubbleDown(rightNode);
            }
        }
    }
}
