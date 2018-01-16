package ctci.Ch4;

import java.util.*;

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
        // set up
        Graph graph = new Graph();
        for (int project: projects) {
            graph.addNode(project);
        }
        for (int[] dependency: dependencies) {
            graph.getNode(dependency[1]).addAdjacent(graph.getNode(dependency[0]));
        }

        ArrayList<Integer> completed =  new ArrayList();

        while (!graph.isEmpty()) {
            ArrayList<Graph.GraphNode> removed = new ArrayList<>();
            for (Graph.GraphNode node: graph.getNodes()) {
                if (node.getAdjacentNodes().isEmpty()) {
                    removed.add(node);
                }
            }

            if (removed.isEmpty())
                return false;

            for (Graph.GraphNode removedNode: removed) {
                graph.removeNode(removedNode);
                completed.add(removedNode.getData());
                for (Graph.GraphNode node: graph.getNodes()) {
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
        HashMap<Integer, ArrayList<Integer>> dependencyMap = new HashMap();
        for (int[] dependency: dependencies) {
            int project = dependency[1];
            int projectDependency = dependency[0];

            ArrayList<Integer> listProjects;
            if (dependencyMap.containsKey(projectDependency)) {
                listProjects = dependencyMap.get(projectDependency);
            }
            else {
                listProjects = new ArrayList();
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

                ArrayList<Integer> removedProjectDependencies = dependencyMap.get(removedProject);
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
        System.out.println("p4_7_2 : " + p4_7_2(projects, dependencies));

        int[] dependency6 = {2, 3};
        dependencies.add(dependency6);
        System.out.println("p4_7_1: " + p4_7_1(projects, dependencies));
        System.out.println("p4_7_2: " + p4_7_2(projects, dependencies));

        System.out.println();
    }

}
