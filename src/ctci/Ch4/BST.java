package ctci.Ch4;

public class BST {
    private BSTNode root;

    public BST() {
        this.root = null;
    }

    public BST(int val) {
        this.root = new BSTNode(val);
    }

    public void setRoot(BSTNode node) {
        this.root = node;
    }

    public BSTNode getRoot() {
        return this.root;
    }

    public void insert(int val) {
        if (this.root == null)
            this.root = new BSTNode(val);
        else
            insert(this.root, val);
    }

    private void insert(BSTNode node, int val) {
        if (val > node.getData()) {
            if (node.getRight() == null)
                node.setRight(new BSTNode(val));
            else
                insert(node.getRight(), val);
        }
        else {
            if (node.getLeft() == null)
                node.setLeft(new BSTNode(val));
            else
                insert(node.getLeft(), val);
        }
    }

    public boolean contains(int val) {
        return contains(this.root, val);
    }

    public boolean contains(BSTNode node, int val) {
        if (node != null) {
            if (val == node.getData())
                return true;
            else if (val < node.getData())
                return contains(node.getLeft(), val);
            else
                return contains(node.getRight(), val);
        }
        return false;
    }

    public boolean remove(int val) {
        if (this.root == null)
            return false;

        if (this.root.getData() == val) {
            System.out.println("Case: root");
            BSTNode tempParent = new BSTNode();
            tempParent.setLeft(this.root);
            this.root.remove(val, tempParent);
            this.root = tempParent.getLeft();
            return true;
        }

        // don't need to worry about parent==null because it assumes that the root.data != val
        return this.root.remove(val, null);
    }

    public void printInOrder() {
        printInOrder(this.root);
    }

    private void printInOrder(BSTNode node) {
        if (node != null) {
            printInOrder(node.getLeft());
            System.out.print(node.getData() + " ");
            printInOrder(node.getRight());
        }
    }

    public void printPreOrder() {
        printPreOrder(this.root);
    }

    private void printPreOrder(BSTNode node) {
        if (node != null) {
            System.out.print(node.getData() + " ");
            printPreOrder(node.getLeft());
            printPreOrder(node.getRight());
        }
    }

    public void printPostOrder() {
        printPostOrder(this.root);
    }

    private void printPostOrder(BSTNode node) {
        if (node != null) {
            printPostOrder(node.getLeft());
            printPostOrder(node.getRight());
            System.out.print(node.getData() + " ");
        }
    }
}
