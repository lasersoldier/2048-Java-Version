package ui;




public class Main {
    public static void main(String[] args) throws Exception {
        GUI gui = new GUI();
        Panel panel = new Panel(gui);
        gui.add(panel);
        gui.setVisible(true);
    }
}
