package View;

import javax.swing.*;
import java.awt.*;

public class EsempioBorderLayout extends JFrame {

    private JButton nord = new JButton("Nord");
    private JButton centro = new JButton("Centro");
    private JButton sud = new JButton("Sud");
    private JButton ovest = new JButton("Ovest");
    private JButton est = new JButton("Est");

    public EsempioBorderLayout() {
        super("Esempio BorderLayout");

        this.setSize(600,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = this.getContentPane();
        c.setLayout(new BorderLayout());

        c.add(nord, BorderLayout.NORTH);
        c.add(centro, BorderLayout.CENTER);
        //c.add(sud, BorderLayout.SOUTH);
        c.add(ovest, BorderLayout.WEST);
        //c.add(est, BorderLayout.EAST);

        this.setVisible(true);
    }
}
