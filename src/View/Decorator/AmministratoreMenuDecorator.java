package View.Decorator;

import View.CatalogoPanel;
import View.Listener.*;
import View.ViewModel.RigaCatalogo;

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

        CatalogoPanel panel=CatalogoPanel.getInstance();
        List<RigaCatalogo> righeSelezionate=panel.getRigheSelezionate();


        JButton aggiungi = new JButton("Aggiungi prodotto");
        AggiungiArticoloListener listener=new AggiungiArticoloListener();
        aggiungi.setActionCommand(AggiungiArticoloListener.AGGIUNGI);
        aggiungi.addActionListener(listener);
        JButton rimuovi = new JButton("Rimuovi prodotto");
        rimuovi.setActionCommand(AggiungiArticoloListener.RIMUOVI);
        rimuovi.addActionListener(new RimuoviArticoloListener());
        JButton modifica = new JButton("Modifica prodotto");
        modifica.setActionCommand(AggiungiArticoloListener.MODIFICA);
        modifica.addActionListener(new ModificaArticoloListener());

        JButton crea=new JButton("Crea nuovo punto vendita");
        crea.addActionListener(new PuntoVenditaListener());



        pulsanti.add(aggiungi);
        pulsanti.add(rimuovi);
        pulsanti.add(modifica);
        pulsanti.add(crea);


        return pulsanti;
    }
}
