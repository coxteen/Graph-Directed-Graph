public class RadioButton {

    int x = 30;
    int y = 30;
    int radius = 30;
    int strokeWidth = 3;

    boolean selected = false;

    public boolean isClicked(int mouseX, int mouseY) {
        return Math.abs(x - mouseX) < radius / 2 && Math.abs(y - mouseY) < radius / 2;
    }

    public void switchGraphType() {
        Graph.isOriented = !Graph.isOriented;
        selected = !selected;
    }
}
