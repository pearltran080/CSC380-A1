
import java.util.*;

public class UCS implements SearchMethod{
    private Node goal;
    private Node initNode;
    private Node result;
    private int time;
    private int space;
    private int length;

    private PriorityQueue<Node> heap;
    private List<Node> visited;

    public UCS(Node goal, Node initNode) {
        this.goal = goal;
        this.initNode = initNode;
        this.result = null;
        this.time = 0;
        this.space = 0;
        this.length = 0;

        this.heap = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {  // min heap; sorted by lowest depth
                return Integer.compare(o1.getBook().getDepth(), o2.getBook().getDepth());
            }
        });
        this.visited = new ArrayList<>();

        run();
    }

    /*
     *   - Run UCS
     */
    @Override
    public void run() {
        this.heap.addAll(this.initNode.expand());
        this.visited.add(this.initNode);
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

                for (Node n : chosenOne.expand()) {
                    if (!contains(this.visited, n)) {
                        this.heap.add(n);
                    }
                }
                
                this.time++;
                if (heap.size() > space) space = heap.size();
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