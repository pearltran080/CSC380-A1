import java.util.*;

public class DFS implements SearchMethod{
    private Node goal;
    private Node initNode;
    private Node result;
    private int time;
    private int space;
    private int length;

    private Stack<Node> stack;
    private List<Node> visited;

    public DFS(Node goal, Node initNode) {
        this.goal = goal;
        this.initNode = initNode;
        this.result = null;
        this.time = 0;
        this.space = 0;
        this.length = 0;

        this.stack = new Stack<Node>();
        this.visited = new ArrayList<>();

        run();
    }

    /*
     *   - Run DFS
     */
    @Override
    public void run() {
        this.stack.push(this.initNode);
        this.visited.add(this.initNode);

        Node chosenOne = this.initNode;

        while (!stack.empty()) {
            if (chosenOne.equalsTo(this.goal)) {
                // save goal node to backtrack/reverse path later using its saved parent
                this.result = chosenOne;
                break;
            }

            else {
                // pop node off stack to add to visited list
                chosenOne = stack.pop();
                this.visited.add(chosenOne);

                // expand popped node and filter visited children
                List<Node> adjacentList = chosenOne.expand();

                for (Node n : adjacentList) {
                    if (!contains(this.visited, n)) {
                        this.stack.push(n);
                    }
                }

                this.time++;
                if (stack.size() > space) space = stack.size();
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

    /*
     *   - Use goal/result state to reverse solution path back up to root/initial state
     */
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