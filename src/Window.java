import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class Window extends JPanel implements MouseListener, MouseMotionListener, KeyListener {

    private RadioButton button = new RadioButton();

    private final Graph graph = new Graph();

    private boolean dragging = false;
    private Node dragged_node = null;
    private int dragOffsetX, dragOffsetY;
    private int initial_position_x, initial_position_y;

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
            button.switch_graph_type();
            return;
        }
        for (Node node : graph.nodes) {
            if (node.is_clicked(e.getX(), e.getY())) {
                if (graph.selected_node == null) {
                    graph.selected_node = node;
                } else {
                    graph.add_edge(graph.selected_node, node);
                    graph.selected_node = null;
                    File.write_in_files(graph.edges, graph.nodes.size());
                }
                return;
            }
        }
        graph.add_node(e.getX(), e.getY());
        graph.selected_node = null;
        File.write_in_files(graph.edges, graph.nodes.size());
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
            if (node.is_clicked(e.getX(), e.getY())) {
                dragging = true;
                dragged_node = node;
                dragOffsetX = e.getX() - node.x;
                dragOffsetY = e.getY() - node.y;

                initial_position_x = node.x;
                initial_position_y = node.y;
                return;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (dragging && dragged_node != null && graph.is_overlapping(dragged_node)) {
            dragged_node.x = initial_position_x;
            dragged_node.y = initial_position_y;
        }
        dragging = false;
        dragged_node = null;
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (dragging && dragged_node != null) {
            dragged_node.x = e.getX() - dragOffsetX;
            dragged_node.y = e.getY() - dragOffsetY;
            repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key_code = e.getKeyCode();
        if (key_code == KeyEvent.VK_ENTER) {
            Graph.is_oriented = !Graph.is_oriented;
            File.write_in_files(graph.edges, graph.nodes.size());
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
