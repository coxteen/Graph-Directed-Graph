import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window extends JPanel implements MouseListener, MouseMotionListener, KeyListener {

    private final RadioButton button = new RadioButton();

    private final Graph graph = new Graph();

    private boolean dragging = false;
    private Node draggedNode = null;
    private int dragOffsetX, dragOffsetY;
    private int initialPositionX, initialPositionY;

    public Window() {
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
    }

    public static void draw_window() {
        JFrame frame = new JFrame("Graph Builder");
        Window window = new Window();
        frame.add(window);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Draw.draw(g2d, graph, button);
    }

    private void left_click_action(MouseEvent e){
        if (button.isClicked(e.getX(), e.getY())) {
            button.switchGraphType();
            return;
        }
        for (Node node : graph.nodes) {
            if (node.isClicked(e.getX(), e.getY())) {
                if (graph.selectedNode == null) {
                    graph.selectedNode = node;
                } else {
                    graph.addEdge(graph.selectedNode, node);
                    graph.selectedNode = null;
                    File.writeInFiles(graph.edges, graph.nodes.size());
                }
                return;
            }
        }
        graph.addNode(e.getX(), e.getY());
        graph.selectedNode = null;
        File.writeInFiles(graph.edges, graph.nodes.size());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            left_click_action(e);
        }
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (Node node : graph.nodes) {
            if (node.isClicked(e.getX(), e.getY())) {
                dragging = true;
                draggedNode = node;
                dragOffsetX = e.getX() - node.x;
                dragOffsetY = e.getY() - node.y;

                initialPositionX = node.x;
                initialPositionY = node.y;
                return;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (dragging && draggedNode != null && graph.isOverlapping(draggedNode)) {
            draggedNode.x = initialPositionX;
            draggedNode.y = initialPositionY;
        }
        dragging = false;
        draggedNode = null;
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (dragging && draggedNode != null) {
            draggedNode.x = e.getX() - dragOffsetX;
            draggedNode.y = e.getY() - dragOffsetY;
            repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key_code = e.getKeyCode();
        if (key_code == KeyEvent.VK_ENTER) {
            Graph.isOriented = !Graph.isOriented;
            File.writeInFiles(graph.edges, graph.nodes.size());
        }
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}
}
