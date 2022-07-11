package View.Decorator;

import View.EsempioGerarchiaLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuestMenu extends Menu {

    private EsempioGerarchiaLayout finestra;
    private JPanel catalogo;

    public GuestMenu(EsempioGerarchiaLayout finestra) {

        this.finestra = finestra;


        JButton browse = new JButton("Sfoglia catalogo");
        browse.setActionCommand("browse");
        // .... agganciate i listener
        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               finestra.mostraCatalogo();

            }

        });

        pulsanti.add(browse);

    }

    public JPanel getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(JPanel catalogo) {
        this.catalogo = catalogo;
    }
}
