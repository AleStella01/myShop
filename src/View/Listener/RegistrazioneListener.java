package View.Listener;

import DAO.UtenteDAO;
import Model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

public class RegistrazioneListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        UtenteFactoryProvider.UserType type= UtenteFactoryProvider.UserType.CLIENTE;
        UtenteAbstractFactory factory=UtenteFactoryProvider.getFactory(type);
        Cliente utente=(Cliente) factory.creaUtente();

        List<IUtente> utenti= UtenteDAO.getInstance().findAll();
        String nome= JOptionPane.showInputDialog(null,"Inserisci nome");
        if(nome == null || "".equals(nome))
            return;
        utente.setNome(nome);
        String cognome= JOptionPane.showInputDialog(null,"Inserisci cognome");
        if(cognome == null || "".equals(cognome))
            return;
        utente.setCognome(cognome);
        String username= JOptionPane.showInputDialog(null,"Inserisci username");
        if(username == null || "".equals(username))
            return;
        ListIterator<IUtente> iterator1= utenti.listIterator();
        while (iterator1.hasNext()){
            Utente utente1=(Utente) iterator1.next();
            if(utente1.getUsername().equals(username)){
                JOptionPane.showMessageDialog(null,"Errore: Lo username inserito esiste già");
                JOptionPane.showMessageDialog(null,"Registrazione non completata");
                return;
            }
        }
        utente.setUsername(username);
        String codiceFiscale=JOptionPane.showInputDialog(null,"Inserisci codice fiscale");
        if(codiceFiscale == null || "".equals(codiceFiscale))
            return;
        ListIterator<IUtente> iterator4= utenti.listIterator();
        while (iterator4.hasNext()){
            Utente utente1=(Utente) iterator4.next();
            if(utente1.getCodiceFiscale().equals(codiceFiscale)){
                JOptionPane.showMessageDialog(null,"Errore: Il codice fiscale inserito esiste già");
                JOptionPane.showMessageDialog(null,"Registrazione non completata");
                return;
            }
        }
        utente.setCodiceFiscale(codiceFiscale);
        try {
            String data=JOptionPane.showInputDialog(null,"Inserisci data di nascita");
            if(data == null || "".equals(data))
                return;

            utente.setDataNascita(data);
        }catch (Exception exception){
            JOptionPane.showMessageDialog(null,"Data non valida");
            return;
        }



        String email=JOptionPane.showInputDialog(null,"Inserisci email");
        if(email == null || "".equals(email))
            return;
        ListIterator<IUtente> iterator2= utenti.listIterator();
        while (iterator2.hasNext()){
            Utente utente1=(Utente) iterator2.next();
            if(utente1.getEmail().equals(email)){
                JOptionPane.showMessageDialog(null,"Errore: L'email inserita esiste già");
                JOptionPane.showMessageDialog(null,"Registrazione non completata");
                return;
            }
        }
        utente.setEmail(email);

        String password=JOptionPane.showInputDialog(null,"Inserisci password");
        if(password == null || "".equals(password))
            return;
        utente.setPassword(password);

        String citta=JOptionPane.showInputDialog(null,"Inserisci la città di residenza");
        if(citta == null || "".equals(citta))
            return;
        utente.setCitta(citta);
        String indirizzo=JOptionPane.showInputDialog(null,"Inserisci l'indirizzo di residenza");
        if(indirizzo == null || "".equals(indirizzo))
            return;
        utente.setIndirizzo(indirizzo);

        UtenteDAO.getInstance().add(utente);
        JOptionPane.showMessageDialog(null,"Registrazione avvenuta con successo");
    }
}
