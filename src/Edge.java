import java.awt.Color;

public class Edge {

    public Node node1, node2;
    public Color edgeColor = Color.BLACK;
    public int lineWidth = 3;
    public int arrowLength = 15;

    public Edge(Node node1, Node node2) {
        this.node1 = node1;
        this.node2 = node2;
    }
}
