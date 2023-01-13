// package search;
import java.util.*;

public class DFS {
    private Node goal;
    private Node root;
    private List<Node> visited;
    private List<Node> result;

    public DFS(Node goal, Node root) {
        this.goal = goal;
        this.root = root;
        this.visited = new ArrayList<Node>();
        this.result = new ArrayList<Node>();
        List<Node> tempPath = new ArrayList<Node>();
        tempPath.add(this.root);
        run(this.root, tempPath);
    }

    public void run(Node current, List<Node> path) {
        this.visited.add(current);
        List<Node> expansionList = current.expand();

        // filter already visited
        for (Node n : expansionList) {
            if (!contains(this.visited, n)) {
                current.addChild(n);
            }
            // System.out.println(n);
        }

        List<Node> childrenList = current.getChildren();
        System.out.println(current);
        System.out.println(childrenList + "\n");
        
        if (current.equalsTo(this.goal)) {
            for (Node t : path) {
                this.result.add(t);
            }
            return;
        }
        else if (childrenList.size() == 0) {
            return;
        }
        else {
            for (Node n : childrenList) {
                List<Node> newPath = new ArrayList<>();
                for (Node t : path) {
                    newPath.add(t);
                }

                newPath.add(n);
                run(n, newPath);
            }
        }

        // int[][] array1 = {{1,3,4},{8,6,2},{7,0,5}};
        // int[][] array2 = {{1,3,4},{8,2,2},{7,0,5}};
        // int[][] array3 = {{1,0,4},{8,2,4},{7,0,1}};
        // Book book1 = new Book(null, 0, 0, false);
        // Node n1 = new Node(array1, null, book1);

        // Book book2 = new Book(null, 0, 0, false);
        // Node n2 = new Node(array2, null, book2);

        // Book book3 = new Book(null, 0, 0, false);
        // Node n3 = new Node(array3, null, book3);

        // List<Node> lst = new ArrayList<Node>();
        // lst.add(n1);
        // lst.add(n2);

        // System.out.println(contains(lst, n3));

        // System.out.println(n2.equalsTo(n3));

    }

    public boolean contains(List<Node> nodeLst, Node node) {
        for(int i=0; i < nodeLst.size(); i++) {
            if(node.equalsTo(nodeLst.get(i))) {
                return true;
            }
        }

        return false;
    }

    public List<Node> getResult() {
        return this.result;
    }
}