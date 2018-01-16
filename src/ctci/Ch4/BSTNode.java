package ctci.Ch4;

public class BSTNode {
    protected int data;
    private BSTNode left;
    private BSTNode right;

    public BSTNode() {
        this.left = null;
        this.right = null;
    }


    public BSTNode(int val) {
        this.data = val;
        this.left = null;
        this.right = null;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getData() {
        return this.data;
    }

    public void setLeft(BSTNode newNode) {
        this.left = newNode;
    }

    public BSTNode getLeft() {
        return this.left;
    }

    public void setRight(BSTNode newNode) {
        this.right = newNode;
    }

    public BSTNode getRight() {
        return this.right;
    }

    public boolean remove(int val, BSTNode parent) {
        // binary search
        if (val < this.data) {
            if (this.left != null)
                return this.left.remove(val, this);
            else
                return false;
        }
        else if (val > this.data) {
            if (this.right != null)
                return this.right.remove(val, this);
            else
                return false;
        }
        else {
            /* 2 children:
            * make the node = smallest element in the right subtree (last left child)
            * delete node with the smallest element in the subtree
            */
            if (this.left != null && this.right != null) {
                System.out.println("Case: 2 children");
                this.data = this.right.minValue();
                this.right.remove(this.data, this);
            }
            // has 0 or 1 children
            else if (parent.left == this) { // current node is parent's left node
                parent.left = (left != null) ? left:right;
            }
            else if (parent.right == this) { // current node is parent's right node
                parent.right = (left != null) ? left:right;
            }

            System.out.println("Case: 0 or 1 children");
            return true;
        }
    }

    public int minValue()
    {
        if (this.left == null)
            return this.data;
        else
            return this.left.minValue();
    }
}
