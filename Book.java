class Book {
    private String action;
    private int depth;
    private int cost;
    private boolean expanded;

    public Book(String action, int depth, int cost, boolean expanded) {
        this.action = action;
        this.depth = depth;
        this.cost = cost;
        this.expanded = expanded;
    }

    public String getAction() {
        return this.action;
    }

    public int getDepth() {
        return this.depth;
    }

    public int getCost() {
        return this.cost;
    }

    public boolean getExpanded() {
        return this.expanded;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}