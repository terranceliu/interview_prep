package ctci.Ch4;

import java.util.*;

public class Graph {
    private ArrayList<GraphNode> nodes;
    private HashSet<GraphNode> visited;

    public Graph() {
        this.nodes = new ArrayList<>();
        this.visited = new HashSet();
    }

    public void addNode(int val) {
        this.nodes.add(new GraphNode(val));
    }

    public GraphNode getNode(int i) {
        return this.nodes.get(i);
    }

    public void removeNode(GraphNode node) {
        this.nodes.remove(node);
    }

    public ArrayList<GraphNode> getNodes() {
        return nodes;
    }

    public boolean isEmpty() {
        return this.nodes.isEmpty();
    }

    /** recursive depth first search */
    public GraphNode dfs(int val) {
        visited.clear();
        System.out.print("DFS:");
        GraphNode result = dfs(this.getNode(0), val);
        System.out.println();
        return result;
    }

    private GraphNode dfs(GraphNode node, int val) {
        System.out.print(" " + node.getData());
        visited.add(node);

        if (node.getData() == val)
            return node;

        for (GraphNode adjacent: node.getAdjacentNodes()) {
            if (!visited.contains(adjacent)) {
                GraphNode found = dfs(adjacent, val);
                if (found != null)
                    return found;
            }
        }
        return null;
    }

    public GraphNode bfs(int val) {
        System.out.print("BFS:");
        GraphNode root = this.getNode(0);
        if (root.getData() == val) {
            System.out.println();
            return root;
        }

        visited.clear();
        Queue<GraphNode> toVisit = new LinkedList();
        toVisit.add(root);

        while (!toVisit.isEmpty()) {
            GraphNode nextNode = toVisit.remove();
            if (!visited.contains(nextNode)) {
                visited.add(nextNode);
                System.out.print(" " + nextNode.getData());
                if (nextNode.getData() == val) {
                    System.out.println();
                    return nextNode;
                }

                for (GraphNode adjacent: nextNode.getAdjacentNodes()) {
                    toVisit.add(adjacent);
                }
            }
        }

        System.out.println();
        return null;
    }

    public class GraphNode {
        private int data;
        private ArrayList<GraphNode> adjacentNodes;

        public GraphNode(int val) {
            this.data = val;
            this.adjacentNodes = new ArrayList<>();
        }

        public void addAdjacent(GraphNode node) {
            this.adjacentNodes.add(node);
        }

        public void removeAdjacent(GraphNode node) {
            this.adjacentNodes.remove(node);
        }

        public int getData() {
            return this.data;
        }

        public ArrayList<GraphNode> getAdjacentNodes() {
            return this.adjacentNodes;
        }

        @Override
        public String toString() {
            return Integer.toString(data);
        }
    }

}
