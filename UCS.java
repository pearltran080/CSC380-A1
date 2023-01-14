import java.util.*;

public class UCS {
    private Node goal;
    private static Node initNode;
    private Node result;

    private List<Node> leafNodes;

    public UCS(Node goal, Node initNode) {
        this.goal = goal;
        this.initNode = initNode;
        this.result = null;
        this.leafNodes = new ArrayList<>();
        run();
    }

    public void run() {
        this.leafNodes.addAll(this.initNode.expand());
        Node chosenOne = this.initNode;

        while(leafNodes.size() != 0) {
            if (chosenOne.equalsTo(this.goal)) {
                this.result = chosenOne;
                break;
            }
            else {
                int lowest = Integer.MAX_VALUE;
                int parentInd = -1;
            
                for (int i=0; i < leafNodes.size(); i++) {
                    int currDepth = leafNodes.get(i).book.getDepth();
                    if (currDepth < lowest ) {
                        lowest = currDepth;
                        chosenOne = leafNodes.get(i);
                        parentInd = i;
                    }
                }
                
                this.leafNodes.addAll(chosenOne.expand());
                this.leafNodes.remove(parentInd);
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