package View.Listener;

import Model.Utente;
import View.CatalogoPanel;
import View.SchiedaIniziale;
import View.ViewModel.RigaCatalogo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ListIterator;

public class LogoutListener implements ActionListener {

    private SchiedaIniziale frame;
    private JPanel nord;
    private JPanel west;
    private JPanel centro;
    private JPanel sud;
    private JPanel utenteLoggato;
    private Utente u;

    public LogoutListener(SchiedaIniziale frame, JPanel nord, JPanel west, JPanel centro, JPanel sud, JPanel utenteLoggato, Utente u) {
        this.frame = frame;
        this.nord=nord;
        this.west=west;
        this.centro=centro;
        this.sud=sud;
        this.utenteLoggato=utenteLoggato;
        this.u=u;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CatalogoPanel panel=CatalogoPanel.getInstance();
        List<RigaCatalogo> righeSelezionate=panel.getRigheSelezionate();
        ListIterator<RigaCatalogo> iterator= righeSelezionate.listIterator();
        while (iterator.hasNext()){
            RigaCatalogo rigaCatalogo=iterator.next();
            rigaCatalogo.setDisponibilita(rigaCatalogo.getDisponibilita() + 1);
        }
        righeSelezionate.clear();

        nord.removeAll();
        west.removeAll();
        centro.removeAll();
        sud.removeAll();
        utenteLoggato.removeAll();
        frame.remove(utenteLoggato);
        frame.mostraPannelloUtenteNonLoggato();
    }


}




