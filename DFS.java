import java.util.*;

public class DFS {
    private Node goal;
    private Node root;
    private List<Node> visited;
    private Node result;
    private boolean finished;

    public DFS(Node goal, Node root) {
        this.goal = goal;
        this.root = root;
        this.finished = false;
        this.visited = new ArrayList<Node>();
        this.result = null;
        run(this.root);
    }

    public void run(Node current) {
        if (this.finished == true) {
            return;
        }

        this.visited.add(current);
        List<Node> expansionList = current.expand();

        // filter already visited
        for (Node n : expansionList) {
            if (!contains(this.visited, n)) {
                current.addChild(n);
            }
        }

        List<Node> childrenList = current.getChildren();
        
        if (current.equalsTo(this.goal)) {
            this.finished = true;
            this.result = current;
            return;
        }
        else if (childrenList.size() == 0) {
            return;
        }
        else {
            for (Node n : childrenList) {
                run(n);
            }
        }
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
        Node n = this.result;
        List<Node> sortedRes = new ArrayList<>();
        while (n.getParent() != null) {
            sortedRes.add(n);
            n = n.getParent();
        }
        sortedRes.add(n);

        return sortedRes;
    }
}