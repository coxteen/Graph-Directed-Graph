import java.awt.*;

public class Node {

    protected int x, y;
    protected static int radius = 60;
    protected Color node_color = Color.GREEN;
    protected Color selected_node_color = Color.DARK_GRAY;

    protected Integer value;

    protected int font_size = radius / 2;
    protected String font_name = "Serif";

    public Node(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    protected boolean is_clicked(int mouseX, int mouseY) {
        return Math.abs(x - mouseX) < radius / 2 && Math.abs(y - mouseY) < radius / 2;
    }
}
