package View.Listener;

import Business.SessionManager;
import DAO.CommentoDAO;
import Model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class CommentoListener implements ActionListener {
    private ListaAcquisto listaAcquisto;

    public CommentoListener(ListaAcquisto listaAcquisto) {
        this.listaAcquisto = listaAcquisto;


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CommentoDAO commentoDAO=CommentoDAO.getInstance();
        List<IProdotto> prodottiEffettivi=new ArrayList<>();
        prodottiEffettivi.addAll(listaAcquisto.getProdotti().keySet());
        List<Servizio> serviziEfferrivi=new ArrayList<>();
        ListIterator<IArticolo> serviziIterator=listaAcquisto.getServizi().listIterator();
        while (serviziIterator.hasNext()){
            Servizio servizio=(Servizio) serviziIterator.next();
            serviziEfferrivi.add(servizio);
        }

        Utente u = (Utente) SessionManager.getSession().get(SessionManager.LOGGED_USER);

        CommentoFactory.CommentoType type= CommentoFactory.CommentoType.PRODOTTO;
        CommentoFactory factory=new CommentoFactory();
        Commento commento=factory.getCommentoFactory(type);


        String stelleS= JOptionPane.showInputDialog(null,"Inserisci numero di stelle");
        int stelle;
        try {
            stelle=Integer.parseInt(stelleS);
        }catch (Exception exception){
            JOptionPane.showMessageDialog(null,"Il numero di stelle deve essere un numero intero compreso tra 1-5");
            return;
        }

        if(stelle < 1 || stelle > 5){
            JOptionPane.showMessageDialog(null,"Errore: Il numero di stelle deve esssere compreso tra 1-5");
            return;
        }

        commento.setNumeroStelle(stelle);
        String testo=JOptionPane.showInputDialog(null,"Inserisci il testo del commento");

        commento.setTesto(testo);
        commento.setCliente(u);
        commento.setListaAcquisto(listaAcquisto);
        boolean risposto = false;
        commento.setRisposto(risposto);
//        commento.setProdotti(prodottiEffettivi);
//        commento.setServizi(serviziEfferrivi);
//        commento.setUsername(u.getUsername());
//        commento.setTipo(tipo);
        commentoDAO.add(commento);

    }

}
