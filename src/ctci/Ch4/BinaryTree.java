package ctci.Ch4;

public class BinaryTree {
    private int[] data;
    private int size;

    public BinaryTree() {
        this.data = new int[10000];
        this.size = 0;
    }

    public BinaryTree(int capacity) {
        this.data = new int[capacity];
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getNodeVal(int node) {
        return this.data[node];
    }

    public void setNodeVal(int node, int val) {
        this.data[node] = val;
    }

    public int getLeftNode(int node) {
        return node*2 + 1;
    }

    public int getRightNode(int node) {
        return node*2 + 2;
    }

    public int getParentNode(int node) {
        return (node-1)/2;
    }

    public int getSiblingNode(int node) {
        if (node % 2 == 1)
            return node+1;
        else
            return node-1;
    }

    public void insert(int val) {
        this.setNodeVal(this.size, val);
        this.size++;
    }
}
