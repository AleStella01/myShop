package View;

import javax.swing.*;
import java.awt.*;

public class EsempioFlowLayout extends JFrame {

    public EsempioFlowLayout() {
        super("Esempio FlowLayout");

        this.setSize(1000,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = this.getContentPane();
        c.setLayout(new FlowLayout());
/*
        c.add(new JButton("Pulsante 1"));
        c.add(new JButton("Pulsante 2"));
        c.add(new JButton("Pulsante 3"));
        c.add(new JButton("Pulsante 4"));
        c.add(new JButton("Pulsante 5"));
*/

        JTextField username = new JTextField(20);
        JPasswordField password = new JPasswordField(20);
        JButton login = new JButton("Login");

        c.add(username);
        c.add(password);
        c.add(login);

        this.setVisible(true);
    }
}
