
import java.util.*;

public class AStar implements SearchMethod{
    private Node goal;
    private Node initNode;
    private Node result;
    private int time;
    private int space;
    private int length;

    private PriorityQueue<Node> heap;
    private List<Node> visited;

    public AStar(Node goal, Node initNode) {
        this.goal = goal;
        this.initNode = initNode;
        this.result = null;
        this.time = 0;
        this.space = 0;
        this.length = 0;

        this.heap = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {  // min heap; sorted by lowest result from chosen heuristic
                return Integer.compare(o1.getH(), o2.getH());
            }
        });
        this.visited = new ArrayList<>();

        run();
    }

    /*
     *   - Run A*
     */
    @Override
    public void run() {
        List<Node> children = this.initNode.expand();
        visited.add(this.initNode);

        for (Node child : children) {
            child.setH(geth2(child));   // change geth1, geth2, geth3 accordingly
        }
        this.heap.addAll(children);

        this.space = heap.size();

        Node chosenOne = this.initNode;

        while(heap.size() != 0) {
            if (chosenOne.equalsTo(this.goal)) {
                this.result = chosenOne;
                break;
            }

            else {
                chosenOne = heap.poll();
                visited.add(chosenOne);
                
                children = chosenOne.expand();

                for (Node child : children) {
                    if (!contains(this.visited, child)) {
                        child.setH(geth2(child));   // change geth1, geth2, geth3 accordingly
                        this.heap.add(child);
                    }
                }
                
                this.time++;
                if (heap.size() > space) space = heap.size();
            }
        }
    }

    /*
     *   - h1 = number of tiles that are not in correct position
     */
    private int geth1(Node node) {
        int misplacedTiles = 0;
        
        for(int i=0; i < node.getState().length; i++) {
            for(int j=0; j <node.getState()[i].length; j++) {
                if (this.goal.getState()[i][j] != node.getState()[i][j]) {
                    misplacedTiles++;
                }
            }
        }
        return node.getBook().getDepth() + misplacedTiles;
    }

    /*
     *   - h2 = sum of Manhattan distances between all tiles and their correct positions
     */
    private int geth2(Node node) {
        int manhattanSum = 0;

        for(int i=0; i < node.getState().length; i++) {
            for(int j=0; j < node.getState()[i].length; j++) {
                int[] goalPos = getGoalPos(node.getState()[i][j]);
                manhattanSum += Math.abs(i - goalPos[0]) + Math.abs(j - goalPos[1]);
            }
        }
        return node.getBook().getDepth() + manhattanSum;
    }

    /*
     *   - h3 = sum for each tile: tile value × Manhattan distance to correct position
     */
    private int geth3(Node node) {
        int sumOfTiles = 0;

        for(int i=0; i < node.getState().length; i++) {
            for(int j=0; j <node.getState()[i].length; j++) {
                int[] goalPos = getGoalPos(node.getState()[i][j]);
                sumOfTiles += node.getState()[i][j] * (Math.abs(i - goalPos[0]) + Math.abs(j - goalPos[1]));
            }
        }
        return node.getBook().getDepth() + sumOfTiles;
    }

    /*
     *   - Get desired goal position for a number
     */
    private int[] getGoalPos(int num) {
        for(int i=0; i < this.goal.getState().length; i++) {
            for(int j=0; j < this.goal.getState()[i].length; j++) {
                if (this.goal.getState()[i][j] == num) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[2];
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