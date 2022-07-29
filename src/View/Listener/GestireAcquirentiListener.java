package View.Listener;

import DAO.PuntoVenditaDAO;
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
        IUtente cliente=null;

        int id=0;
        try{
            String idS= JOptionPane.showInputDialog(null,"Inserisci l'id del cliente che vuoi rimuovere");
            id=Integer.parseInt(idS);

        }catch (Exception exception){
            JOptionPane.showMessageDialog(null,"Errore: L'id deve essere un numero intero");
            return;

        }
        cliente=uDao.findById(id);
        uDao.removeById(cliente.getUsername());
        if (cliente == null){
            JOptionPane.showMessageDialog(null,"Id non esistente");
        }else {
            JOptionPane.showMessageDialog(null,"Cliente eliminato con successo");
        }
    }
}
