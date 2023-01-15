import java.util.*;

public class BFS implements SearchMethod{
    private Node goal;
    private Node initNode;
    private Node result;
    private int time;
    private int space;
    private int length;

    private LinkedList<Node> queue;
    private List<Node> visited;

    public BFS(Node goal, Node initNode) {
        this.goal = goal;
        this.initNode = initNode;
        this.result = null;
        this.time = 0;
        this.space = 0;
        this.length = 0;

        this.queue = new LinkedList<Node>();
        this.visited = new ArrayList<>();

        run();
    }

    @Override
    public void run() {
        this.queue.add(this.initNode);

        Node chosenOne = this.initNode;

        while (queue.size() != 0) {
            if (chosenOne.equalsTo(this.goal)) {
                this.result = chosenOne;
                break;
            }

            else {
                chosenOne = queue.poll();
                this.visited.add(chosenOne);

                List<Node> adjacentList = chosenOne.expand();

                for (Node n : adjacentList) {
                    if (!contains(this.visited, n)) {
                        this.queue.add(n);
                    }
                }

                this.time++;
                if (queue.size() > space) space = queue.size();
            }
        }
    }

    @Override
    public boolean contains(List<Node> nodeLst, Node node) {
        for(int i=0; i < nodeLst.size(); i++) {
            if(node.equalsTo(nodeLst.get(i))) {
                return true;
            }
        }

        return false;
    }

    @Override
    public List<Node> getResult() {
        Node n = this.result;
        List<Node> sortedRes = new ArrayList<>();
        int cost = n.getBook().getDepth();

        while (n.getParent() != null) {
            this.length++;
            sortedRes.add(n);
            n = n.getParent();
        }
        sortedRes.add(n);

        System.out.println("Final Cost: " + cost + ", Final Length: " + length + ", Time: " + time + ", Space: " + space + "\n");

        return sortedRes;
    }
}