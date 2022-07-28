package View.Listener;

import DAO.UtenteDAO;
import Model.Cliente;
import Model.IUtente;
import Model.Utente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class GestireAcquirentiListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        UtenteDAO uDao=UtenteDAO.getInstance();
        List<IUtente> utenti=new ArrayList<>();
        utenti=uDao.findAll();
        List<Utente> clienti=new ArrayList<>();
        boolean isCliente=false;
        ListIterator<IUtente> utenteListIterator= utenti.listIterator();
        while (utenteListIterator.hasNext()){
            Utente utente=(Utente) utenteListIterator.next();
            isCliente=false;
            if(utente instanceof Cliente){
                isCliente=true;
            }
            if(isCliente){
                clienti.add(utente);
            }
        }
        int id=0;
        try{
            String idS= JOptionPane.showInputDialog(null,"Inserisci l'id del cliente che vuoi rimuovere");
            id=Integer.parseInt(idS);

        }catch (Exception exception){
            JOptionPane.showMessageDialog(null,"Errore: L'id deve essere un numero intero");
            return;

        }
        ListIterator<Utente> clientiIterator= clienti.listIterator();
        boolean trovato=false;
        while (clientiIterator.hasNext()){
            Utente utente=clientiIterator.next();
            if(id == utente.getId()){
                trovato=true;
                clienti.remove(utente);
                uDao.removeById(utente.getUsername());
                JOptionPane.showMessageDialog(null,"Il cliente è stato eliminato con successo");
                break;
            }

        }
        if (!trovato){
            JOptionPane.showMessageDialog(null,"Errore: L'id inserito non esiste");
        }

    }
}
