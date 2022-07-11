package View.Listener;

import Model.Utente;
import View.EsempioGerarchiaLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogoutListener implements ActionListener {

    private EsempioGerarchiaLayout frame;
    private JPanel nord;
    private JPanel west;
    private JPanel centro;
    private JPanel sud;
    private JPanel utenteLoggato;
    private Utente u;

    public LogoutListener(EsempioGerarchiaLayout frame,JPanel nord,JPanel west,JPanel centro,JPanel sud,JPanel utenteLoggato,Utente u) {
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
        nord.removeAll();
        west.removeAll();
        centro.removeAll();
        sud.removeAll();
        utenteLoggato.removeAll();
        frame.remove(utenteLoggato);
        frame.mostraPannelloUtenteNonLoggato();
    }


}




