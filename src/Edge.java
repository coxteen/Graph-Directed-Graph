import java.awt.Color;

public class Edge {

    protected Node node1, node2;
    protected Color edgeColor = Color.BLACK;
    protected int lineWidth = 3;
    protected int arrowLength = 15;

    public Edge(Node node1, Node node2) {
        this.node1 = node1;
        this.node2 = node2;
    }
}
