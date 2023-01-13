package model;
import java.util.*;

public class Node {
    public State state;
    public List<Node> children = new ArrayList<>();
    public Node parent;
    public Book book;

    public Node(State state) {
        this.state = state;
    }

    public Node addChild(Node node) {
        children.add(node);
        node.parent = this;
    }

    class Book {
        public String action;
        public int depth;
        public int cost;
        public boolean expanded;

        public Book(action, depth, cost, expanded) {
            this.action = action;
            this.depth = depth;
            this.cost = cost;
            this.expanded = expanded;
        }
    }

    public static void main (String[] args) {
    
    }
}