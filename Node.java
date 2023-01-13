// package model;
import java.util.*;

public class Node {
    private int[][] state;
    private static List<Node> children;
    private Node parent;
    private Book book;

    public Node(int[][] state, Node parent, Book book) {
        this.state = state;
        this.parent = parent;
        this.book = book;
        this.children = new ArrayList<>();
    }

    public void addChild(Node child) {
        this.children.add(child);
    }

    public Node move(int[] blankPos, int[] nextMove, String action) {
        int[][] newState = new int[state.length][state[0].length];
        for(int i=0; i < state.length; i++) {
            for (int j=0; j < state[i].length; j++) {
                newState[i][j] = state[i][j];
            }
        }

        newState[blankPos[0]][blankPos[1]] = newState[nextMove[0]][nextMove[1]];
        newState[nextMove[0]][nextMove[1]] = 0;

        Book newBook = new Book(action, book.getDepth() + newState[blankPos[0]][blankPos[1]], newState[blankPos[0]][blankPos[1]], false);
        Node newChild = new Node(newState, this, newBook);
        return newChild;
        // System.out.println(newChild);
    }

    public List<Node> expand() {
        List<Node> ret = new ArrayList<>();
        book.setExpanded(true);
        int[] blankPos = getBlankPos();
        int i = blankPos[0];
        int j = blankPos[1];

        int[] nextMove = new int[2];

        // above
        if (i-1 != -1) {
            nextMove[0] = i-1;
            nextMove[1] = j;
            // System.out.println(state[i-1][j]);
            Node child = move(blankPos, nextMove, "down");
            ret.add(child);
        }
        // below
        if (i+1 != state.length) {
            nextMove[0] = i+1;
            nextMove[1] = j;
            // System.out.println(state[i+1][j]);
            Node child = move(blankPos, nextMove, "up");
            ret.add(child);
        }
        // left
        if (j-1 != -1) {
            nextMove[0] = i;
            nextMove[1] = j-1;
            // System.out.println(state[i][j-1]);
            Node child = move(blankPos, nextMove, "right");
            ret.add(child);
        }
        // right
        if (j+1 != state[0].length){
            nextMove[0] = i;
            nextMove[1] = j+1;
            // System.out.println(state[i][j+1]);
            Node child = move(blankPos, nextMove, "left");
            ret.add(child);
        }

        return ret;
    }

    public List<Node> getChildren() {
        List<Node> currentChildren = new ArrayList<>();
        for (Node n : this.children) {
            currentChildren.add(n);
        }
        return currentChildren;
    }

    public int[] getBlankPos() {
        int[] blankPos = new int[2];

        for(int i=0; i < state.length; i++) {
            for (int j=0; j < state[i].length; j++) {
                if (state[i][j] == 0) {
                    blankPos[0] = i;
                    blankPos[1] = j;
                    // System.out.println(i + ", " + j);
                }
            }
        }

        return blankPos;
    }

    public boolean equalsTo(Node node) {
        int[][] otherState = node.getState();
        for(int i=0; i < state.length; i++) {
            for (int j=0; j < state[i].length; j++) {
                if (state[i][j] != otherState[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    public String toString() {
        String result = "";
        for (int[] row : state) {
            for (int i : row) {
                result = result + i + " ";
            }
        }
        return result;
    }

    public int[][] getState() {
        return this.state;
    }
}