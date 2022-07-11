package View.Decorator;

import View.Listener.GestioneCatalogoListener;

import javax.swing.*;
import java.util.List;

public class AmministratoreMenuDecorator extends CustomMenuDecorator {

    private ClienteMenuDecorator menuDecorator;

    public AmministratoreMenuDecorator(Menu menu,ClienteMenuDecorator menuDecorator) {
        this.menu = menu;
        this.menuDecorator=menuDecorator;
    }

    @Override
    public List<JButton> getPulsanti() {

        // ha tutte le funzioni del guest
        pulsanti.addAll(this.menu.getPulsanti());
        pulsanti.addAll(this.menuDecorator.getPulsanti());

        JButton aggiungi = new JButton("Aggiungi prodotto");
        GestioneCatalogoListener listener=new GestioneCatalogoListener();
        aggiungi.setActionCommand(GestioneCatalogoListener.AGGIUNGI);
        aggiungi.addActionListener(listener);
        JButton rimuovi = new JButton("Rimuovi prodotto");
        rimuovi.setActionCommand(GestioneCatalogoListener.RIMUOVI);
        rimuovi.addActionListener(listener);
        JButton modifica = new JButton("Modifica prodotto");
        modifica.setActionCommand(GestioneCatalogoListener.MODIFICA);
        modifica.addActionListener(listener);


        //action command, listener...
        pulsanti.add(aggiungi);
        pulsanti.add(rimuovi);
        pulsanti.add(modifica);

        return pulsanti;
    }
}
