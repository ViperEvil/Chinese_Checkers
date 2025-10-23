package chinese.checkers.JTable;

import javax.swing.*;

public class Game_Window extends JFrame {
    private JPanel panelMain;
    private JButton button1;

    public Game_Window() {
        this.setTitle("Китайские шашки");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();


    }
}
