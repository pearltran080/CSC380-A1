import java.util.*;

public interface SearchMethod {
    void run();
    
    boolean contains(List<Node> nodeLst, Node node);

    List<Node> getResult();
}