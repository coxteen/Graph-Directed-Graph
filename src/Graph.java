import java.util.ArrayList;

public class Graph {

    protected ArrayList<Node> nodes = new ArrayList<>();
    protected ArrayList<Edge> edges = new ArrayList<>();

    protected Node selectedNode = null;

    protected static boolean isOriented = false;

    public Boolean isOverlapping(Node node) {
        for (Node list_node : nodes) {
            if(node != list_node && Math.abs(list_node.x - node.x) <= Node.radius && Math.abs(list_node.y - node.y) <= Node.radius) {
                return true;
            }
        }
        return false;
    }

    protected void addNode(int x, int y) {
        Node node = new Node(x, y, nodes.size() + 1);
        if(!isOverlapping(node)) {
            nodes.add(node);
        }
    }

    protected void addEdge(Node start, Node end) {
        if(start == end) {
            return;
        }
        for(Edge edge : edges) {
            if ((edge.node1 == start && edge.node2 == end) ||
                    (!Graph.isOriented && edge.node1 == end && edge.node2 == start)) {
                return;
            }
        }
        edges.add(new Edge(start, end));
    }
}
