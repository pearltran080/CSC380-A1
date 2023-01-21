
import java.util.*;

public class Node {
    private int[][] state;
    private Node parent;
    private Book book;
    private int h;

    public Node(int[][] state, Node parent, Book book) {
        this.state = state;
        this.parent = parent;
        this.book = book;
        this.h = 0;
    }

    /*
     *   - Generates new node for next move state
     *   - Updates depth (using tile value as cost)
     *   - Updates cost
     */ 
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
    }

    /*
     *   - Find all existing adjacent tiles next to 0 (blank tile)
     *   - Returns children nodes for search method to use.
     */ 
    public List<Node> expand() {
        List<Node> ret = new ArrayList<>();
        book.setExpanded(true);

        int[] blankPos = getBlankPos();
        int i = blankPos[0];
        int j = blankPos[1];

        int[] nextMove = new int[2];

        // check above
        if (i-1 != -1) {
            nextMove[0] = i-1;
            nextMove[1] = j;
            Node child = move(blankPos, nextMove, "DOWN");
            if (!child.equalsTo(parent)) ret.add(child);
        }
        // check below
        if (i+1 != state.length) {
            nextMove[0] = i+1;
            nextMove[1] = j;
            Node child = move(blankPos, nextMove, "UP");
            if (!child.equalsTo(parent)) ret.add(child);
        }
        // check left
        if (j-1 != -1) {
            nextMove[0] = i;
            nextMove[1] = j-1;
            Node child = move(blankPos, nextMove, "RIGHT");
            if (!child.equalsTo(parent)) ret.add(child);
        }
        // check right
        if (j+1 != state[0].length){
            nextMove[0] = i;
            nextMove[1] = j+1;
            Node child = move(blankPos, nextMove, "LEFT");
            if (!child.equalsTo(parent)) ret.add(child);
        }

        return ret;
    }

    public int[] getBlankPos() {
        int[] blankPos = new int[2];

        for(int i=0; i < this.state.length; i++) {
            for (int j=0; j < this.state[i].length; j++) {
                if (state[i][j] == 0) {
                    blankPos[0] = i;
                    blankPos[1] = j;
                }
            }
        }

        return blankPos;
    }

    public boolean equalsTo(Node other) {
        if (other == null) return false;
        
        int[][] otherState = other.getState();
        for(int i=0; i < this.state.length; i++) {
            for (int j=0; j < this.state[i].length; j++) {
                if (this.state[i][j] != otherState[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    /*
     *   - Prints state with cost and depth to get there
     */ 

    public String toString() {
        String result = "";
        result += this.book.getAction() + ", ";
        result += "Cost: " + this.book.getCost() + ", ";
        result += "Total cost: " + this.book.getDepth() + "\n";
        
        for (int[] row : state) {
            for (int i : row) {
                result = result + i + " ";
            }
        }
        result += "\n";

        return result;
    }

    public int[][] getState() {
        return this.state;
    }

    public Node getParent() {
        return this.parent;
    }

    public Book getBook() {
        return this.book;
    }

    public void setH(int num) {
        this.h = num;
    }

    public int getH() {
        return this.h;
    }
}