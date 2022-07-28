package View.Decorator;

import View.Listener.FinestraCommentListener;
import View.Listener.GestireAcquirentiListener;
import View.Listener.RifornireProdottoListener;

import javax.swing.*;
import java.util.List;

public class ManagerMenuDecorator extends CustomMenuDecorator {

    private ClienteMenuDecorator menuDecorator;

    public ManagerMenuDecorator(Menu menu,ClienteMenuDecorator menuDecorator) {
        this.menu = menu;
        this.menuDecorator=menuDecorator;
    }

    @Override
    public List<JButton> getPulsanti() {

        // ha tutte le funzioni del guest
        pulsanti.addAll(this.menu.getPulsanti());
        pulsanti.addAll(this.menuDecorator.getPulsanti());

        JButton gestione = new JButton("Gestisci Acquirenti");
        GestireAcquirentiListener gestioneListener=new GestireAcquirentiListener();
        gestione.addActionListener(gestioneListener);
        JButton rifornire = new JButton("Rifornire Prodotto");
        RifornireProdottoListener listener=new RifornireProdottoListener();
        rifornire.addActionListener(listener);
        //action command, listener...
        pulsanti.add(gestione);
        pulsanti.add(rifornire);

        return pulsanti;

    }
}
