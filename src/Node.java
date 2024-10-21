import java.awt.*;

public class Node {

    protected int x, y;
    protected static int radius = 60;
    protected Color nodeColor = Color.GREEN;
    protected Color selectedNodeColor = Color.LIGHT_GRAY;

    protected Integer value;

    protected int fontSize = radius / 2;
    protected static String font_name = "Arial";

    public Node(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    protected boolean isClicked(int mouseX, int mouseY) {
        return Math.abs(x - mouseX) < radius / 2 && Math.abs(y - mouseY) < radius / 2;
    }
}
