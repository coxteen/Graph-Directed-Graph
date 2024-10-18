import java.awt.*;

public class Edge {

    protected Node node1, node2;
    protected Color edge_color = Color.BLACK;
    protected int line_width = 3;
    protected int arrowLength = 15;

    public Edge(Node node1, Node node2) {
        this.node1 = node1;
        this.node2 = node2;
    }
}
