import javax.swing.*;
import java.awt.*;

public class Draw {

    private static void draw_arrows(Graphics2D g2d, Edge edge) {
        int deltaX = edge.node2.x - edge.node1.x;
        int deltaY = edge.node2.y - edge.node1.y;

        double angle = Math.atan2(deltaY, deltaX);

        double magnitude = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        double[] directionVector = {deltaX / magnitude, deltaY / magnitude};

        int xArrow1 = (int) (edge.node2.x - edge.arrowLength * Math.cos(angle - Math.PI / 6));
        int yArrow1 = (int) (edge.node2.y - edge.arrowLength * Math.sin(angle - Math.PI / 6));
        int xArrow2 = (int) (edge.node2.x - edge.arrowLength * Math.cos(angle + Math.PI / 6));
        int yArrow2 = (int) (edge.node2.y - edge.arrowLength * Math.sin(angle + Math.PI / 6));

        int differenceX = (int) (directionVector[0] * Node.radius / 2);
        int differenceY = (int) (directionVector[1] * Node.radius / 2);

        g2d.drawLine(edge.node2.x - differenceX, edge.node2.y - differenceY, xArrow1 - differenceX, yArrow1 - differenceY);
        g2d.drawLine(edge.node2.x - differenceX, edge.node2.y - differenceY, xArrow2 - differenceX, yArrow2 - differenceY);
    }


    private static void draw_edge(Graphics2D g2d, Edge edge) {
        g2d.setColor(edge.edge_color);
        g2d.setStroke(new BasicStroke(edge.line_width));
        g2d.drawLine(edge.node1.x, edge.node1.y, edge.node2.x, edge.node2.y);

        if (Graph.is_oriented) {
            draw_arrows(g2d, edge);
        }
    }

    private static void draw_node(Graphics2D g2d, Node node, Node selected_node) {
        if (node == selected_node) {
            g2d.setColor(node.selected_node_color);
        }
        else {
            g2d.setColor(node.node_color);
        }
        g2d.fillOval(node.x - Node.radius / 2, node.y - Node.radius / 2, Node.radius, Node.radius);
    }

    private static void draw_number(Graphics2D g2d, Node node) {
        Font font = new Font("Arial", Font.BOLD, node.font_size);
        g2d.setFont(font);
        g2d.setColor(Color.BLACK);
        if (node.value < 10) {
            g2d.drawString(String.valueOf(node.value), node.x - 8, node.y + 9);
        }
        else {
            g2d.drawString(String.valueOf(node.value), node.x - 16, node.y + 9);
        }
    }

    protected static void draw_button(Graphics2D g2d, RadioButton button) {
        g2d.setStroke(new BasicStroke(button.strokeWidth));
        g2d.drawOval(button.x - button.radius / 2, button.y - button.radius / 2, button.radius, button.radius);

        if (button.selected) {
            g2d.setColor(Color.BLACK);
            g2d.fillOval(button.x - button.radius / 4, button.y - button.radius / 4, button.radius / 2, button.radius / 2);
        }
    }

    protected static void draw(Graphics2D g2d, Graph graph, RadioButton button) {
        for (Edge edge : graph.edges) {
            draw_edge(g2d, edge);
        }
        for (Node node : graph.nodes) {
            draw_node(g2d, node, graph.selected_node);
            draw_number(g2d, node);
        }
        draw_button(g2d, button);
    }
}
