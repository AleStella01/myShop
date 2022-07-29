package View.Listener;

import DAO.ProdottoDAO;
import DAO.PuntoVenditaDAO;
import DAO.UtenteDAO;
import Model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

public class PuntoVenditaListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        UtenteDAO utenteDAO=UtenteDAO.getInstance();
        ProdottoDAO prodottoDAO=ProdottoDAO.getInstance();
        PuntoVendita puntoVendita=new PuntoVendita();
        String nome=JOptionPane.showInputDialog(null,"Inserisci il nome del punto vendita");
        if(nome == null || "".equals(nome))
            return;
        puntoVendita.setNome(nome);
        String citta=JOptionPane.showInputDialog(null,"Inserisci la città del punto vendita");
        if(citta == null || "".equals(citta))
            return;
        puntoVendita.setCitta(citta);
        String indirizzo=JOptionPane.showInputDialog(null,"Inserisci l'indirizzo del punto vendita");
        if(indirizzo == null || "".equals(indirizzo))
            return;
        puntoVendita.setIndirizzo(indirizzo);

        UtenteFactoryProvider.UserType type= UtenteFactoryProvider.UserType.MANAGER;
        UtenteAbstractFactory factory=UtenteFactoryProvider.getFactory(type);
        Manager manager=(Manager) factory.creaUtente();


        List<IUtente> utenti=utenteDAO.findAll();

        String nomeM=JOptionPane.showInputDialog(null,"Inserisci nome manager");
        if(nomeM == null || "".equals(nomeM))
            return;
        manager.setNome(nomeM);

        String cognome= JOptionPane.showInputDialog(null,"Inserisci cognome");
        if(cognome == null || "".equals(cognome))
            return;
        manager.setCognome(cognome);
        String username= JOptionPane.showInputDialog(null,"Inserisci username");
        if(username == null || "".equals(username))
            return;
        manager.setUsername(username);
        ListIterator<IUtente> iterator1= utenti.listIterator();
        while (iterator1.hasNext()){
            Utente utente1=(Utente) iterator1.next();
            if(utente1.getUsername().equals(username)){
                JOptionPane.showMessageDialog(null,"Errore: Lo username inserito esiste già");
                JOptionPane.showMessageDialog(null,"Registrazione non completata");
                return;
            }
        }
        manager.setCognome(username);


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
        manager.setCodiceFiscale(codiceFiscale);
        try {
            String data=JOptionPane.showInputDialog(null,"Inserisci data di nascita");
            if(data == null || "".equals(data))
                return;
//            SimpleDateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
//            Date date = parser.parse(data);
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//            String formattedDate = formatter.format(date);
//            Date datan=parser.parse(formattedDate);
            manager.setDataNascita(data);
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
        manager.setEmail(email);

        String password=JOptionPane.showInputDialog(null,"Inserisci password");
        if(password == null || "".equals(password))
            return;
        manager.setPassword(password);
        String cittaM=JOptionPane.showInputDialog(null,"Inserisci la città del manager");
        if(cittaM == null || "".equals(cittaM))
            return;
        manager.setCitta(cittaM);
        String indirizzoM=JOptionPane.showInputDialog(null,"Inserisci l'indirizzo del manager");
        if(indirizzoM == null || "".equals(indirizzoM))
            return;
        manager.setIndirizzo(indirizzoM);
        utenteDAO.add(manager);
        ArrayList<IUtente> listaUtenti = UtenteDAO.getInstance().findAll();
        puntoVendita.setManager(UtenteDAO.getInstance().findById(listaUtenti.get(listaUtenti.size() - 1).getId()));

/*
        boolean fine=false;
        int i=0;
        while (!fine){
            try {
                String idS=JOptionPane.showInputDialog(null,"Inserisci id del prodotto da inserire nel catalogo o fine per fermare l'inserimento");
                if("fine".equalsIgnoreCase(idS)){
                    fine=true;
                }else {
                    int id=Integer.parseInt(idS);
                    Prodotto prodotto= (Prodotto) prodottoDAO.findById(id);
                    if(prodotto == null){
                        JOptionPane.showMessageDialog(null,"Il prodotto non esiste");
                    }else{
                        puntoVendita.getProdottiVenduti().put(prodotto,i);
                    }

                }

            }catch (Exception exception){
                JOptionPane.showMessageDialog(null,"Errore: l'id deve essere un numero intero ");
            }



        }

 */
        PuntoVenditaDAO.getInstance().add(puntoVendita);
        ArrayList<IPuntoVendita> list = PuntoVenditaDAO.getInstance().findAll();
        puntoVendita.setId(list.get(list.size() -1).getId());
        boolean fine=false;
        int i=0;
        while (!fine){
            try {
                String idS=JOptionPane.showInputDialog(null,"Inserisci id del prodotto da inserire nel catalogo o fine per fermare l'inserimento");
                if("fine".equalsIgnoreCase(idS)){
                    fine=true;
                }else {
                    int id=Integer.parseInt(idS);
                    IProdotto prodottoCercato = (IProdotto) prodottoDAO.findById(id);
                    System.out.println(id);
                    System.out.println(prodottoCercato);
                    if(prodottoCercato==null){
                        JOptionPane.showMessageDialog(null,"Il prodotto non esiste");
                    }else {
                        i=Integer.parseInt(JOptionPane.showInputDialog(null,"Inserisci quantità del prodotto"));
                        puntoVendita.getProdottiVenduti().put(prodottoCercato,i);
                        PuntoVenditaDAO.getInstance().addProdottoVenduto(puntoVendita, prodottoCercato, i);
                    }

                }

            }catch (Exception exception){
                JOptionPane.showMessageDialog(null,exception.getMessage());
            }

            JOptionPane.showMessageDialog(null,"Punto vendita creato correttamente");

        }





    }
}
