import java.util.*;

public class Main {
    public static void main (String[] args) {
        String method = args[0];
        String difficulty = args[1];

        int[][] initState;
        int[][] goal = {{1,2,3},{8,0,4},{7,6,5}};

        int[][] easy = {{1,3,4},{8,6,2},{7,0,5}};
        int[][] med = {{2,8,1},{0,4,3},{7,6,5}};
        int[][] hard = {{5,6,7},{4,0,8},{3,2,1}};

        // set difficulty configuration
        if (difficulty.equals("easy")) {
            initState = easy;
        }
        else if (difficulty.equals("med")) {
            initState = med;
        }
        else if (difficulty.equals("hard")) {
            initState = hard;
        }
        else {
            System.out.println("Difficulty \"" + difficulty + "\" not valid");
            initState = null;
            return;
        }

        // creating starting node to begin chosen search method
        Book book = new Book(null, 0, 0, false);
        Node initNode = new Node(initState, null, book);
        Node goalNode = new Node(goal, null, book);

        SearchMethod search;

        if (method.equals("DFS")) {
            search = (SearchMethod) new DFS(goalNode, initNode);
        }
        else if (method.equals("UCS")) {
            search = (SearchMethod) new UCS(goalNode, initNode);
        }
        else if (method.equals("BFS")) {
            search = (SearchMethod) new BFS(goalNode, initNode);
        }
        else if (method.equals("GBF")) {
            search = (SearchMethod) new GBF(goalNode, initNode);
        }
        else if (method.equals("AStar")) {
            search = (SearchMethod) new AStar(goalNode, initNode);
        }
        else {
            System.out.println("Search method \"" + method + "\" not valid");
            return;
        }

        // print solution
        List<Node> finalPath = search.getResult();
        for (int i=finalPath.size()-1; i >= 0; i--) {
            System.out.println(finalPath.get(i));
        }
    }
}