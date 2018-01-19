package ctci.Ch4;

import java.util.*;
import ctci.Ch4.Graph.GraphNode;

import javax.swing.tree.TreeNode;

public class ProblemsCh4 {
    public static void main (String[] args) {
        testBST();
        testMinHeap();
        testGraph();

        /** Problem 4.2 */
        p4_2_test();

        /** Problem 4.3 */
        p4_3_test();

        /** Problem 4.4 */
        p4_4_test();

        /** Problem 4.5 */
        p4_5_test();

        /** Problem 4.7 */
        p4_7_test();

        /** Problem 4.8 */
        p4_8_test();

        /** Problem 4.9 */
        p4_9_test();

        /** Problem 4.10 */
        p4_10_test();

        /** Problem 4.12 */
        p4_12_test();
   }

    public static void testBST() {
        System.out.println("Testing BST:");

        int[] list = {1, 3, 2, 7, 5, 0, 3};
        BST bst = new BST();

        for (int i = 0; i < list.length; i++) {
            bst.insert(list[i]);
        }

        System.out.println("Contains 5: " + bst.contains(5));
        System.out.println("Contains 12: " + bst.contains(12));

        System.out.print("inOrder: ");
        bst.printInOrder();
        System.out.println();

        System.out.print("preOrder: ");
        bst.printPreOrder();
        System.out.println();

        System.out.print("postOrder: ");
        bst.printPostOrder();
        System.out.println();

        bst.remove(1);
        bst.printInOrder();
        System.out.println();

        bst.remove(3);
        bst.printInOrder();
        System.out.println();

        bst.remove(7);
        bst.printInOrder();
        System.out.println();

        bst.remove(0);
        bst.printInOrder();
        System.out.println();

        System.out.println();
    }

    public static void testMinHeap() {
        System.out.println("Testing MinHeap:");

        MinHeap mh = new MinHeap();
        int[] list = {55, 50, 90, 4, 7, 87};

        for (int i = 0; i < list.length; i++) {
            mh.insert(list[i]);
        }

        while (mh.getSize() > 2) {
            System.out.print(mh.extract() + " ");
        }
        mh.insert(88);
        mh.insert(-20);
        System.out.println();
        while (!mh.isEmpty()) {
            System.out.print(mh.extract() + " ");
        }

        System.out.println();
        System.out.println();
    }

    public static void testGraph() {
        System.out.println("Testing Graph:");

        Graph graph = new Graph();
        for (int i = 0; i < 6; i++) {
            graph.addNode(i);
        }

        graph.getNode(0).addAdjacent(graph.getNode(1));
        graph.getNode(0).addAdjacent(graph.getNode(4));
        graph.getNode(0).addAdjacent(graph.getNode(5));
        graph.getNode(1).addAdjacent(graph.getNode(3));
        graph.getNode(1).addAdjacent(graph.getNode(4));
        graph.getNode(2).addAdjacent(graph.getNode(1));
        graph.getNode(3).addAdjacent(graph.getNode(2));
        graph.getNode(3).addAdjacent(graph.getNode(4));

        System.out.println(graph.dfs(200));
        System.out.println(graph.dfs(3));
        System.out.println(graph.bfs(200));
        System.out.println(graph.bfs(3));
        System.out.println();
    }

    /**
     * P4_1: Find out if a path exists between two nodes:
     *
     * Implement DFS or BFS
     *
     */


    /** P4_2: create BST from sorted array */
    public static BST p4_2(int[] array) {
        BSTNode root = createMinBST(array, 0, array.length-1);

        BST bst = new BST();
        bst.setRoot(root);
        return bst;
    }

    public static BSTNode createMinBST(int[] array, int start, int end) {
        if (end < start)
            return null;

        int mid = (start + end)/2;
        BSTNode node = new BSTNode(array[mid]);
        node.setLeft(createMinBST(array, start, mid-1));
        node.setRight(createMinBST(array, mid+1, end));
        return node;
    }

    public static void p4_2_test() {
        System.out.print("p4_2: ");
        int[] array = {0, 2, 4, 6, 8, 10, 12, 14, 16};
        BST bst = p4_2(array);

        bst.printInOrder();
        System.out.println();
        System.out.println();
    }


    public static ArrayList<LinkedList<BSTNode>> p4_3_1(BST bst) {
        ArrayList<LinkedList<BSTNode>> lists = new ArrayList();
        createdLevelLinkedList(bst.getRoot(), lists, 0);

        for (LinkedList<BSTNode> linkedList: lists) {
            for (BSTNode node: linkedList) {
                System.out.print(node.getData() + " ");
            }
            System.out.println();
        }

        return lists;
    }

    public static void createdLevelLinkedList(BSTNode node, ArrayList<LinkedList<BSTNode>> lists, int level) {
        if (node == null)
            return;

        LinkedList<BSTNode> list;
        if (lists.size() == level) {
            // levels are traversed in order, so if level==size, this is the first time we've visited this level
            list = new LinkedList();
            lists.add(list);
        }
        else {
            list = lists.get(level);
        }

        // preOrder traversal
        list.add(node);
        createdLevelLinkedList(node.getLeft(), lists, level+1);
        createdLevelLinkedList(node.getRight(), lists, level+1);
    }

    public static ArrayList<LinkedList<BSTNode>> p4_3_2(BST bst) {
        ArrayList<LinkedList<BSTNode>> result = new ArrayList();
        LinkedList<BSTNode> current = new LinkedList();

        if (bst.getRoot() != null)
            current.add(bst.getRoot());

        while(!current.isEmpty()) {
            result.add(current);
            LinkedList<BSTNode> parents = current;
            current = new LinkedList<BSTNode>();

            for (BSTNode parent: parents) {
                if (parent.getLeft() != null)
                    current.add(parent.getLeft());
                if (parent.getRight() != null)
                    current.add(parent.getRight());
            }
        }

        for (LinkedList<BSTNode> linkedList: result) {
            for (BSTNode node: linkedList) {
                System.out.print(node.getData() + " ");
            }
            System.out.println();
        }

        return result;
    }

    public static void p4_3_test() {
        int[] list = {1, 3, 2, 7, 5, 0, 3};
        BST bst = new BST();

        for (int i = 0; i < list.length; i++) {
            bst.insert(list[i]);
        }

        System.out.println("4_3_1:");
        p4_3_1(bst);
        System.out.println("4_3_2:");
        p4_3_2(bst);

        System.out.println();
    }


    /** check tree is balanced */
    public static boolean p4_4(BST bst) {
        return getHeight(bst.getRoot()) != -1;
    }

    public static int getHeight(BSTNode node) {
        if (node == null)
            return 0;

        int leftHeight = getHeight(node.getLeft());
        if (leftHeight == -1)
            return -1;

        int rightHeight = getHeight(node.getRight());
        if (rightHeight == -1)
            return -1;

        if (Math.abs(leftHeight-rightHeight) > 1)
            return -1;

        int height = 1 + Math.max(leftHeight, rightHeight);
        return height;
    }

    public static void p4_4_test() {
        BST bst = new BST();

        int[] list1 = {1, 3, 2, 7, 5, 0, 3};
        for (int i = 0; i < list1.length; i++) {
            bst.insert(list1[i]);
        }
        System.out.println("4_4:" + p4_4(bst));

        bst.insert(-1);
        System.out.println("4_4:" + p4_4(bst));

        bst = new BST();
        System.out.println("4_4:" + p4_4(bst));

        int[] list2 = {0, -5, 5, -3, 2};
        for (int i = 0; i < list2.length; i++) {
            bst.insert(list2[i]);
        }
        System.out.println("4_4:" + p4_4(bst));

        bst.insert(-10);
        System.out.println("4_4:" + p4_4(bst));
        bst.insert(-11);
        System.out.println("4_4:" + p4_4(bst));
        bst.insert(-12);
        System.out.println("4_4:" + p4_4(bst));

        System.out.println();
    }

    public static boolean p4_5(BST bst) {
        return checkBST(bst.getRoot(), null, null);
    }

    public static boolean checkBST(BSTNode node, Integer min, Integer max) {
        if (node == null)
            return true;

        if ((min != null && node.getData() <= min) || (max != null && node.getData() > max))
            return false;

        if (!checkBST(node.getLeft(), min, node.getData()) || !checkBST(node.getRight(), node.getData(), max))
            return false;

        return true;
    }

    public static void p4_5_test() {
        BST bst = new BST();
        int[] list1 = {1, 3, 2, 7, 5, 0, 3};
        for (int i = 0; i < list1.length; i++) {
            bst.insert(list1[i]);
        }

        System.out.println("p4_5: " + p4_5(bst));
        System.out.println();
    }

    /**
     * P4_6: Successor (node needs access to parent)
     * TODO
     */


    /** get project build order given project dependencies */
    public static boolean p4_7_1(ArrayList<Integer> projects, ArrayList<int[]> dependencies) {
        Graph graph = new Graph();
        for (int project : projects) {
            graph.addNode(project);
        }
        for (int[] dependency : dependencies) {
            graph.getNode(dependency[1]).addAdjacent(graph.getNode(dependency[0]));
        }

        ArrayList<Integer> completed =  new ArrayList();

        while (!graph.isEmpty()) {
            ArrayList<GraphNode> removed = new ArrayList<>();
            for (GraphNode node: graph.getNodes()) {
                if (node.getAdjacentNodes().isEmpty()) {
                    removed.add(node);
                }
            }

            if (removed.isEmpty())
                return false;

            for (GraphNode removedNode: removed) {
                graph.removeNode(removedNode);
                completed.add(removedNode.getData());
                for (GraphNode node: graph.getNodes()) {
                    node.removeAdjacent(removedNode);
                }
            }
        }

        System.out.println(completed);
        return true;
    }

    /** use Hashmap instead of graph */
    public static boolean p4_7_2(ArrayList<Integer> projects, ArrayList<int[]> dependencies) {
        // projects: # dependencies
        HashMap<Integer, Integer> outstandingProjects = new HashMap();
        for (int project: projects) {
            outstandingProjects.put(project, 0);
        }

        // dependency: projects
        HashMap<Integer, HashSet<Integer>> dependencyMap = new HashMap();
        for (int[] dependency: dependencies) {
            int project = dependency[1];
            int projectDependency = dependency[0];

            HashSet<Integer> listProjects;
            if (dependencyMap.containsKey(projectDependency)) {
                listProjects = dependencyMap.get(projectDependency);
            }
            else {
                listProjects = new HashSet();
                dependencyMap.put(projectDependency, listProjects);
            }
            listProjects.add(project);
            outstandingProjects.put(project, outstandingProjects.get(project)+1);
        }

        ArrayList<Integer> completed =  new ArrayList();
        while (!outstandingProjects.isEmpty()) {
            ArrayList<Integer> removed = new ArrayList<>();

            for (int project: outstandingProjects.keySet()) {
                if (outstandingProjects.get(project) == 0)
                    removed.add(project);
            }

            if (removed.isEmpty())
                return false;

            for (int removedProject: removed) {
                completed.add(removedProject);
                outstandingProjects.remove(removedProject);
                if (!dependencyMap.containsKey(removedProject))
                    continue;

                HashSet<Integer> removedProjectDependencies = dependencyMap.get(removedProject);
                for (int project: removedProjectDependencies) {
                    outstandingProjects.put(project, outstandingProjects.get(project)-1);
                }
            }
        }


        System.out.println(completed);
        return true;
    }

    /**
     * TODO: p4_7_3, topological sort. Check book solutions, figure out run time
     */
    public static boolean p4_7_3(ArrayList<Integer> projects, ArrayList<int[]> dependencies) {
        Graph graph = new Graph();
        for (int project : projects) {
            graph.addNode(project);
        }
        for (int[] dependency : dependencies) {
            graph.getNode(dependency[0]).addAdjacent(graph.getNode(dependency[1]));
        }

        HashSet<GraphNode> visited = new HashSet();
        Stack<GraphNode> completed = new Stack();

        for (GraphNode node: graph.getNodes()) {
            // if we visit a node that we are currently visiting, there is a loop
            HashSet<GraphNode> visiting = new HashSet();
            if (visited.contains(node))
                continue;

            visiting.add(node);
            visited.add(node);
            if (!dfs(node, completed, visited, visiting))
                return false;
        }

        while (!completed.isEmpty()) {
            System.out.print(completed.pop() + " ");
        }
        System.out.println();

        return true;
    }

    public static boolean dfs(GraphNode node, Stack<GraphNode> completed, HashSet<GraphNode> visited, HashSet<GraphNode> visiting) {
        for (GraphNode adjacentNode: node.getAdjacentNodes()) {
            if (visiting.contains(adjacentNode))
                return false;
            if (visited.contains(adjacentNode))
                continue;

            visiting.add(adjacentNode);
            visited.add(adjacentNode);

            if (!dfs(adjacentNode, completed, visited, visiting))
                return false;
        }

        completed.add(node);
        return true;
    }

    public static void p4_7_test() {
        ArrayList<Integer> projects = new ArrayList();
        for (int i = 0; i < 6; i ++) {
            projects.add(i);
        }

        ArrayList<int[]> dependencies = new ArrayList();
        int[] dependency1 = {0, 1};
        dependencies.add(dependency1);
        int[] dependency2 = {5, 1};
        dependencies.add(dependency2);
        int[] dependency3 = {1, 3};
        dependencies.add(dependency3);
        int[] dependency4 = {5, 0};
        dependencies.add(dependency4);
        int[] dependency5 = {3, 2};
        dependencies.add(dependency5);

        System.out.println("p4_7_1: " + p4_7_1(projects, dependencies));
        System.out.println("p4_7_2: " + p4_7_2(projects, dependencies));
        System.out.println("p4_7_3: " + p4_7_3(projects, dependencies));

        int[] dependency6 = {2, 3};
        dependencies.add(dependency6);
        System.out.println("p4_7_1: " + p4_7_1(projects, dependencies));
        System.out.println("p4_7_2: " + p4_7_2(projects, dependencies));
        System.out.println("p4_7_3: " + p4_7_3(projects, dependencies));

        System.out.println();
    }

    /**
     * find common ancestor
     *
     * Has access to parent (array implementation of tree)
     */
    public static int p4_8_1(BinaryTree tree, int node1, int node2) {
        int depth1 = getDepth(tree, node1);
        int depth2 = getDepth(tree, node2);

        while (depth1 > depth2) {
            node1 = tree.getParentNode(node1);
            depth1--;
        }
        while (depth2 > depth1) {
            node2 = tree.getParentNode(node2);
            depth2--;
        }

        while (node1 > 0 && node2 > 0 && node1 != node2) {
            node1 = tree.getParentNode(node1);
            node2 = tree.getParentNode(node2);
        }

        return node1;
    }

    public static int getDepth(BinaryTree tree, int node) {
        int depth = 0;
        while (node > 0) {
            node = tree.getParentNode(node);
            depth++;
        }
        return depth;
    }

    /** instead of checking the same subtree more than once, we can check the parents, sibling */
    public static int p4_8_2(BinaryTree tree, int node1, int node2) {
        // check if either node is already the common ancestor
        if (covers(tree, node1, node2))
            return node1;
        if (covers(tree, node2, node1))
            return node2;

        int sibling = tree.getSiblingNode(node1);
        int parent = tree.getParentNode(node1);
        while (!covers(tree, sibling, node2)) {
            sibling = tree.getSiblingNode(parent);
            parent = tree.getParentNode(parent);
        }

        return parent;
    }

    public static boolean covers(BinaryTree tree, int root, int findNode) {
        if (root >= tree.getSize())
            return false; // did not find node
        if (root == findNode)
            return true;
        return covers(tree, tree.getLeftNode(root), findNode) || covers(tree, tree.getRightNode(root), findNode);
    }

    /** no access to parent */
    public static int p4_8_3(BinaryTree tree, int node1, int node2) {
        return findAncestorRoot_3(tree, tree.getNodeVal(0), node1, node2);
    }

    public static int findAncestorRoot_3(BinaryTree tree, int root, int node1, int node2) {
        if (root == node1 || root == node2 || root >= tree.getSize())
            return root;

        boolean node1IsOnLeft = covers(tree, tree.getLeftNode(root), node1);
        boolean node2IsOnLeft = covers(tree, tree.getLeftNode(root), node2);

        // if this is the first time the nodes are on different sides, we have found the common ancestor
        if (node1IsOnLeft != node2IsOnLeft)
            return root;
        
        if (node1IsOnLeft)
            return findAncestorRoot_3(tree, tree.getLeftNode(root), node1, node2);
        else
            return findAncestorRoot_3(tree, tree.getRightNode(root), node1, node2);
    }

    public static int p4_8_4(BinaryTree tree, int node1, int node2) {
        if (node1==node2)
            return node1;
        return findAncestorRoot_4(tree, tree.getNodeVal(0), node1, node2);
    }


    /**
     * Returns p, if root's subtree includes p (and not q).
     * Returns q, if root 's subtree includes q (and not pl.
     * Returns null, if neither p nor q are in root's subtree.
     * Else, returns the common ancestor of p and q.
     */
    public static int findAncestorRoot_4(BinaryTree tree, int root, int node1, int node2) {
        // tree.getSize() means a null root
        if (root >= tree.getSize())
            return tree.getSize();

        int left = findAncestorRoot_4(tree, tree.getLeftNode(root), node1, node2);
        /* if we found the ancestor already we can stop here.
         * Having found an ancestor already means the following haven't happened:
         * left == null means that we didn't find node1/2 in the subtree
         * left == node1 or node2 means we haven't found an ancestor (that != node1/2)
            - it's possible that node1/2 is the common ancestor, but that is fine (see below)
         */
        if (left != tree.getSize() && left != node1 && left != node2)
            return left;

        int right = findAncestorRoot_4(tree, tree.getRightNode(root), node1, node2);
        if (right != tree.getSize() && right != node1 && right != node2)
            return right;

        /* if we haven't found an ancestor already but are in situation where the left and right subtrees each contain
         * node1/2 (i.e. != null), then the root is the common ancestor
         */
        if (left != tree.getSize() && right!= tree.getSize())
            return root;
        /* if we find node1/2, just keep passing it up
         * in the case where node1/2 is the common ancestor that contains the other node in its subtree, we are fine
         * because we'll keep getting node + null, so we'll eventually just return the node (see below)
         */
        else if (root == node1 || root == node2)
            return root;
        else
            return left != tree.getSize() ? left:right; //return the non-null value (both could be null)
    }

    public static void p4_8_test() {
        BinaryTree tree = new BinaryTree();
        for (int i = 0; i < 31; i++) {
            tree.insert(i);
        }

        // Answers; 8, 1, 3, 0, 7
        System.out.println("p4_8_1: " + p4_8_1(tree, 17, 18));
        System.out.println("p4_8_1: " + p4_8_1(tree, 17, 19));
        System.out.println("p4_8_1: " + p4_8_1(tree, 15, 8));
        System.out.println("p4_8_1: " + p4_8_1(tree, 15, 5));
        System.out.println("p4_8_1: " + p4_8_1(tree, 7, 15));

        System.out.println("p4_8_2: " + p4_8_2(tree, 17, 18));
        System.out.println("p4_8_2: " + p4_8_2(tree, 17, 19));
        System.out.println("p4_8_2: " + p4_8_2(tree, 15, 8));
        System.out.println("p4_8_2: " + p4_8_2(tree, 15, 5));
        System.out.println("p4_8_2: " + p4_8_2(tree, 7, 15));
        
        System.out.println("p4_8_3: " + p4_8_3(tree, 17, 18));
        System.out.println("p4_8_3: " + p4_8_3(tree, 17, 19));
        System.out.println("p4_8_3: " + p4_8_3(tree, 15, 8));
        System.out.println("p4_8_3: " + p4_8_3(tree, 15, 5));
        System.out.println("p4_8_3: " + p4_8_3(tree, 7, 15));
        
        System.out.println("p4_8_4: " + p4_8_4(tree, 17, 18));
        System.out.println("p4_8_4: " + p4_8_4(tree, 17, 19));
        System.out.println("p4_8_4: " + p4_8_4(tree, 15, 8));
        System.out.println("p4_8_4: " + p4_8_4(tree, 15, 5));
        System.out.println("p4_8_4: " + p4_8_4(tree, 7, 15));
        
        System.out.println();
    }

    /** my naive solution */
    public static void p4_9_1(BST bst) {
        BSTNode root = bst.getRoot();
        ArrayList<BSTNode> nextNodes = new ArrayList();
        if (root.getLeft() != null)
            nextNodes.add(root.getLeft());
        if (root.getRight() != null)
            nextNodes.add(root.getRight());

        getSequences(bst.getRoot(), new StringBuilder(), nextNodes);
    }

    public static void getSequences(BSTNode node, StringBuilder str, ArrayList<BSTNode> roots) {
        str.append(node.getData()).append(" ");

        if (roots.isEmpty()) {
            System.out.println(str.toString());
            return;
        }

        for (BSTNode root: roots) {
            ArrayList<BSTNode> newList = new ArrayList(roots);
            newList.remove(root);

            if (root.getLeft() != null)
                newList.add(root.getLeft());
            if (root.getRight() != null)
                newList.add(root.getRight());
            getSequences(root, new StringBuilder(str), newList);
        }
    }

    public static void p4_9_2(BST bst) {
        ArrayList<LinkedList<Integer>> results = getSequences(bst.getRoot());
        for (LinkedList<Integer> result: results) {
            System.out.println(result);
        }
    }

    public static ArrayList<LinkedList<Integer>> getSequences(BSTNode node) {
        ArrayList<LinkedList<Integer>> results = new ArrayList();

        // if we get a null, then we stop, and return an empty linekd list
        if (node == null) {
            results.add(new LinkedList<Integer>());
            return results;
        }

        LinkedList<Integer> prefix = new LinkedList();
        // add node data to the prefix (since the parent node also gets printed first)
        prefix.add(node.getData());

        // recuse through left and right subtrees to get lists of sequences in each subtree
        ArrayList<LinkedList<Integer>> leftSequences = getSequences(node.getLeft());
        ArrayList<LinkedList<Integer>> rightSequences = getSequences(node.getRight());

        // weave
        for (LinkedList<Integer> leftSequence: leftSequences) {
            for (LinkedList<Integer> rightSequence: rightSequences) {
                ArrayList<LinkedList<Integer>> weaved = new ArrayList();
                weave(leftSequence, rightSequence, prefix, weaved);
                results.addAll(weaved);
            }
        }

        return results;
    }

    /**
     * keep elements in each linked list in relative order
     * stores each result in ArrayList results
     * use LinkedList since it's easy to add/remove from head/tail
     */
    public static void weave(LinkedList<Integer> first, LinkedList<Integer> second, LinkedList<Integer> prefix,
                             ArrayList<LinkedList<Integer>> results) {
        if (first.isEmpty() || second.isEmpty()) {
            LinkedList<Integer> result = (LinkedList<Integer>) prefix.clone();
            result.addAll(first);
            result.addAll(second);
            results.add(result);
            return;
        }

        int headFirst = first.removeFirst();
        prefix.addLast(headFirst);
        weave(first, second, prefix, results);
        // undoes changes so we can call weave again
        first.addFirst(headFirst);
        prefix.removeLast();

        int headSecond = second.removeFirst();
        prefix.addLast(headSecond);
        weave(first, second, prefix, results);
        second.add(headSecond);
        prefix.removeLast();
    }

    public static void p4_9_test() {
        BST bst = new BST();
        bst.insert(2);
        bst.insert(1);
        bst.insert(0);
        bst.insert(3);

        System.out.println("4_9_1:");
        p4_9_1(bst);
        System.out.println("4_9_2:");
        p4_9_2(bst);
        System.out.println();
    }


    /**
     * Check subtree. node 1 is the bigger tree
     */
    public static boolean p4_10_1(BSTNode node1, BSTNode node2) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        getPreOrderString(node1, sb1);
        getPreOrderString(node2, sb2);

        System.out.println("tree1: " + sb1.toString());
        System.out.println("tree2: " + sb2.toString());

        return sb1.indexOf(sb2.toString()) != -1;
    }

    public static void getPreOrderString(BSTNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("X ");
            return;
        }

        sb.append(node.data + " ");
        getPreOrderString(node.getLeft(), sb);
        getPreOrderString(node.getRight(), sb);
    }

    /**
     * just check every node whenever the data in the two tree roots match
     * better space complexity
     * */
    public static boolean p4_10_2(BSTNode node1, BSTNode node2) {
        if (node1 == null)
            return false;

        if (node1.getData() == node2.getData() || matchTree(node1, node2))
            return true;

        return p4_10_2(node1.getLeft(), node2) || p4_10_2(node1.getRight(), node2);
    }

    public static boolean matchTree(BSTNode node1, BSTNode node2) {
        // if we get to the bottom (null) at the same time for both, then the trees are equal
        if (node1 == null && node2 == null)
            return true;
        // if only one is null, then we reached the end of that tree, meaning they are not equal
        else if (node1 == null || node2 == null)
            return false;
        else if (node1.getData() != node2.getData())
            return false;
        else
            return matchTree(node1.getLeft(), node2.getLeft()) && matchTree(node1.getRight(), node2.getRight());
    }

    public static void p4_10_test() {
        BST bst = new BST();
        int[] list = {50, 20, 60, 10, 25, 70, 5, 15, 65, 80};
        for (int i = 0; i < list.length; i++ ) {
            bst.insert(list[i]);
        }

        BSTNode root = bst.getRoot();
        BSTNode node1 = root.getLeft();
        BSTNode node2 = root.getRight().getRight();

        System.out.println("4_10_1: " + p4_10_1(root, node1));
        System.out.println("4_10_1: " + p4_10_1(node1, node2));

        System.out.println("4_10_2: " + p4_10_2(root, node1));
        System.out.println("4_10_2: " + p4_10_2(node1, node2));
        
        System.out.println();
    }

    public static int p4_12_1(BST bst, int matchVal) {
        BSTNode root = bst.getRoot();
        ArrayList<Integer> matches = new ArrayList();

        ArrayList<Integer> sums = getSums(root, matchVal, matches);
        System.out.println(sums);

        return matches.size();
    }

    public static ArrayList<Integer> getSums(BSTNode node, int matchVal, ArrayList<Integer> matches) {
        ArrayList<Integer> result = new ArrayList();
        if (node==null) {
            return result;
        }

        result.addAll(getSums(node.getLeft(), matchVal, matches));
        result.addAll(getSums(node.getRight(), matchVal, matches));

        int nodeData = node.getData();
        if (nodeData == matchVal)
            matches.add(matchVal);

        for (int i = 0; i < result.size(); i++) {
            int newVal = result.get(i) + nodeData;
            if (newVal == matchVal)
                matches.add(matchVal);
            result.set(i, newVal);
        }
        result.add(nodeData);
        return result;
    }

    public static void p4_12_test() {
        BST bst = new BST();
        int[] list = {100, 3, 2, 5, 2, 3, 11, 1};
        for (int i = 0; i < list.length; i++) {
            bst.insert(list[i]);
        }

        System.out.println("p4_12_1:" + p4_12_1(bst, 8));
    }

}
