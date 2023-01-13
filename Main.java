public class Main {
    public static void main (String[] args) {
        if (args[0] == null || args[1] == null) {
            System.out.println("Choose difficulty and search method:\n[method] [easy/med/hard]");
        }

        String method = args[0];
        String difficulty = args[1];

        if (difficulty.equals("easy"))
            int[][] conf = [[1,3,4],[8,6,2],[7,0,5]];
        else if (difficulty.equals("med"))
            int[][] conf = [[2,8,1],[0,4,3],[7,6,5]];
        else if (difficulty.equals("hard"))
            int[][] conf = [[5,6,7],[4,0,8],[3,2,1]];
        else {
            System.out.println("Difficulty \"" + difficulty + "\" not valid");
            return;

        if (method.equals("DFS")) {
            DFS dfs = new DFS(conf);
            return dfs.run();
        } 
    }
}