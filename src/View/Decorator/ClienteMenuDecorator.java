package View.Decorator;

import View.CatalogoPanel;
import View.Listener.CarrelloListener;
import View.Listener.FinestraCommentListener;
import View.Listener.ListaListener;
import View.ViewModel.RigaCatalogo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ClienteMenuDecorator extends CustomMenuDecorator {

    private JPanel centro;




    public ClienteMenuDecorator(Menu menu,JPanel centro) {
         this.menu = menu;
         this.centro=centro;

    }

    @Override
    public List<JButton> getPulsanti() {


        // ha tutte le funzioni del guest
        pulsanti.addAll(this.menu.getPulsanti());


        CatalogoPanel panel=CatalogoPanel.getInstance();
        List<RigaCatalogo> righeSelezionate=panel.getRigheSelezionate();


        // + le funzioni del cliente

        JButton home=new JButton("Home");
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame finestra=((GuestMenu) menu).getFinestra();
                centro.removeAll();
                finestra.repaint();
                finestra.revalidate();
            }
        });

        JButton liste = new JButton("Le mie liste");
        ListaListener listaListener=new ListaListener();
        liste.addActionListener(listaListener);


        JButton carrello=new JButton("Vai al carrello");
        CarrelloListener carrelloListener=new CarrelloListener(righeSelezionate);
        carrello.addActionListener(carrelloListener);

        JButton mostraCommenti=new JButton("Mostra commenti");
        FinestraCommentListener commentListener=new FinestraCommentListener();
        mostraCommenti.addActionListener( commentListener);



        //action command, listener...
        pulsanti.add(home);
        pulsanti.add(liste);
        pulsanti.add(carrello);
        pulsanti.add(mostraCommenti);







        return pulsanti;
    }
}
