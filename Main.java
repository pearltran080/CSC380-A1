import java.util.*;

public class Main {
    public static void main (String[] args) {
        if (args[0] == null || args[1] == null) {
            System.out.println("Choose difficulty and search method:\n[method] [easy/med/hard]");
        }

        String method = args[0];
        String difficulty = args[1];

        // List<Node> path = new ArrayList<>();

        int[][] conf;
        // int[][] easy = {{4,0,3},{5,2,1}};
        // int[][] goal = {{5,4,3},{0,2,1}};

        int[][] goal = {{1,2,3},{8,0,4},{7,6,5}};
        int[][] easy = {{1,3,4},{8,6,2},{7,0,5}};
        int[][] med = {{2,8,1},{0,4,3},{7,6,5}};
        int[][] hard = {{5,6,7},{4,0,8},{3,2,1}};

        if (difficulty.equals("easy")) {
            conf = easy;
        }
        else if (difficulty.equals("med")) {
            conf = med;
        }
        else if (difficulty.equals("hard")) {
            conf = hard;
        }
        else {
            System.out.println("Difficulty \"" + difficulty + "\" not valid");
            conf = null;
            return;
        }

        Book book = new Book(null, 0, 0, false);
        Node initNode = new Node(conf, null, book);
        Node goalNode = new Node(goal, null, book);

        if (method.equals("DFS")) {
            DFS dfs = new DFS(goalNode, initNode);
            List<Node> finalPath = dfs.getResult();
            for (int i=finalPath.size()-1; i >= 0; i--) {
                System.out.println(finalPath.get(i));
            }
        }
        else if (method.equals("UCS")) {
            UCS ucs = new UCS(goalNode, initNode);
            List<Node> finalPath = ucs.getResult();
            for (int i=finalPath.size()-1; i >= 0; i--) {
                System.out.println(finalPath.get(i));
            }
        }
        else {
            System.out.println("Search method \"" + method + "\" not valid");
            return;
        }
    }
}