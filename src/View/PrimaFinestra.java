package View;

import javax.swing.*;
import java.awt.*;

public class PrimaFinestra extends JFrame {

    public PrimaFinestra() {

        super("Prima finestra");

        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Buona lezione");
        JButton button = new JButton("Click me");

        Container c = this.getContentPane();
        c.add(button);
        c.add(label);

        this.setVisible(true);
    }
}
