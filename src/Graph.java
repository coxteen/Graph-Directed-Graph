import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Graph {

    protected ArrayList<Node> nodes = new ArrayList<>();
    protected ArrayList<Edge> edges = new ArrayList<>();

    protected Node selected_node = null;

    protected static boolean is_oriented = false;

    // TODO: Matrice de adiacenta
    // TODO: Lista de adiacenta

    public Boolean is_overlapping(Node node) {
        for (Node list_node : nodes) {
            if(node != list_node && Math.abs(list_node.x - node.x) <= Node.radius && Math.abs(list_node.y - node.y) <= Node.radius) {
                return true;
            }
        }
        return false;
    }

    protected void add_node(int x, int y) {
        Node node = new Node(x, y, nodes.size() + 1);
        if(!is_overlapping(node)) {
            nodes.add(node);
        }
    }

    protected void add_edge(Node start, Node end) {
        if(start == end) {
            return;
        }
        for(Edge edge : edges) {
            if(edge.node1 == start && edge.node2 == end) {
                return;
            }
        }
        edges.add(new Edge(start, end));
    }
}
