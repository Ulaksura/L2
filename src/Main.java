import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // пишу оконное приложение для второй Лабы
        Formula frame = new Formula();



        /*JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("Справка");
        menuBar.add(fileMenu);
        JMenu nextMenu = new JMenu("О программе");
        fileMenu.add(nextMenu);*/




        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}