package View;

import javax.swing.*;
import java.awt.*;

public class EsempioGridLayout extends JFrame {

    public EsempioGridLayout() {
        super("Esempio GridLayout");

        this.setSize(600,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = this.getContentPane();
        c.setLayout(new GridLayout(3,5));

        for(int i=0;i<25;i++)
            c.add(new JButton("Pulsante "+i));

        this.setVisible(true);
    }
}
