import java.util.*;

public class UCS {
    private Node goal;
    private Node initNode;
    private Node result;
    private PriorityQueue<Node> heap;

    public UCS(Node goal, Node initNode) {
        this.goal = goal;
        this.initNode = initNode;
        this.result = null;

        this.heap = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {

                return Integer.compare(o1.book.getDepth(), o2.book.getDepth());
            }
        });

        run();
    }

    public void run() {
        this.heap.addAll(this.initNode.expand());
        Node chosenOne = this.initNode;

        while(heap.size() != 0) {
            if (chosenOne.equalsTo(this.goal)) {
                this.result = chosenOne;
                break;
            }
            else {
                chosenOne = heap.poll();
                heap.addAll(chosenOne.expand());
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