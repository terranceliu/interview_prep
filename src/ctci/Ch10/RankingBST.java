package ctci.Ch10;

public class RankingBST {
    RankingBSTNode root;

    public RankingBST() {
        this.root = null;
    }

    public RankingBST(int val) {
        this.root = new RankingBSTNode(val, 1);
    }

    public void insert(int val) {
        if (root == null)
            root = new RankingBSTNode(val, 1);
        else
            insert(root, val);
    }

    private void insert(RankingBSTNode node, int val) {
        // error: root hasn't been set
        if (node == null)
            return;

        if (val <= node.val) {
            node.numLeftChildren += 1;
            if (node.left == null)
                node.left = new RankingBSTNode(val, 1);
            else
                insert(node.left, val);
        }
        else {
            if (node.right == null)
                node.right = new RankingBSTNode(val, 1);
            else
                insert(node.right, val);
        }
    }

    public int getRank(int val) {
        return getRank(root, val, 0);
    }

    private int getRank(RankingBSTNode node, int val, int rank) {
        if (node == null)
            return rank;

        if (val <= node.val)
            return getRank(node.left, val, rank);
        else
            return getRank(node.right, val, rank + node.numLeftChildren);
    }

    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(RankingBSTNode node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.val + " ");
            printInOrder(node.right);
        }
    }

    public class RankingBSTNode {
        int val;
        int numLeftChildren;
        RankingBSTNode left, right;

        public RankingBSTNode(int val, int numLeft) {
            this.val = val;
            this.numLeftChildren = numLeft;
            this.left = null;
            this.right = null;
        }
    }
}
