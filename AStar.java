
import java.util.*;

public class AStar implements SearchMethod{
    private Node goal;
    private Node initNode;
    private Node result;
    private int time;
    private int space;
    private int length;

    private PriorityQueue<Node> heap;

    public AStar(Node goal, Node initNode) {
        this.goal = goal;
        this.initNode = initNode;
        this.result = null;
        this.time = 0;
        this.space = 0;
        this.length = 0;

        this.heap = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Integer.compare(o1.getH(), o2.getH());
            }
        });

        run();
    }

    @Override
    public void run() {
        List<Node> children = this.initNode.expand();
        for (Node child : children) {
            child.setH(geth1(child));
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
                
                children = chosenOne.expand();
                for (Node child : children) {
                    child.setH(geth1(child));
                }
                this.heap.addAll(children);
                
                this.time++;
                if (heap.size() > space) space = heap.size();
            }
        }
    }

    private int geth1(Node node) {
        int misplacedTiles = 0;
        for(int i=0; i < node.getState().length; i++) {
            for(int j=0; j <node.getState()[i].length; j++) {
                if (this.goal.getState()[i][j] != node.getState()[i][j]) {
                    misplacedTiles++;
                }
            }
        }
        return misplacedTiles;
    }

    // private int geth2(Node node) {

    // }

    // private int geth3(Node node) {

    // }

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