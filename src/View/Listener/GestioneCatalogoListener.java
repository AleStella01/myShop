package View.Listener;

import Model.Produttore;
import Model.composite.Prodotto;
import View.CatalogoPanel;
import View.ViewModel.RigaCatalogo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ListIterator;

public class GestioneCatalogoListener implements ActionListener {

    public final static String AGGIUNGI = "Aggiungi prodotto";
    public final static String RIMUOVI = "Rimuovi prodotto";
    public final static String MODIFICA= "Modifica prodotto";


    //private CatalogoTableModel catalogoTableModel;
   // private RigaCatalogo riga1=new RigaCatalogo();
/*
    public AggiuntaProdottoListener(List<RigaCatalogo> righe) {
        //this.catalogoTableModel = catalogoTableModel;
        this.righe=righe;
    }

 */



    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        int disponibilita=0;
        if(AGGIUNGI.equals(action)){
            Prodotto prodotto=new Prodotto();
            String nome=JOptionPane.showInputDialog(null,"Inserisci nome prodotto");
            prodotto.setNome(nome);
            String nomeProduttore=JOptionPane.showInputDialog(null,"Inserisci nome produttore");
            Produttore produttore=new Produttore();
            produttore.setNome(nomeProduttore);
            prodotto.setProduttore(produttore);
            String nomeCategoria=JOptionPane.showInputDialog(null,"Inserisci nome categoria");
            prodotto.setCategoria(nomeCategoria);
            try {
                String prezzoS=JOptionPane.showInputDialog(null,"Inserisci prezzo");
                Float prezzo=Float.parseFloat(prezzoS);
                prodotto.setPrezzo(prezzo);
            }
            catch (Exception exception){
                JOptionPane.showMessageDialog(null,"Errore, prodotto non creato: Il prezzo deve essere un numero");
            }
            try {
                String idS=JOptionPane.showInputDialog(null,"Inserisci ID prodotto");
                int id=Integer.parseInt(idS);
                boolean trovato= false;
                List<RigaCatalogo> righeCatalogo=CatalogoPanel.getInstance().getRighe();
                ListIterator<RigaCatalogo> listIterator=righeCatalogo.listIterator();
                while (listIterator.hasNext()){
                    RigaCatalogo rigaCatalogo=listIterator.next();
                    if (id == rigaCatalogo.getIdProdotto()){
                        trovato=true;
                    }
                }
                if(!trovato){
                    prodotto.setId(id);
                }
                else{
                    JOptionPane.showMessageDialog(null,"L'ID inserito esiste già");
                    return;
                }

            }
            catch (Exception exception){
                JOptionPane.showMessageDialog(null,"Errore, prodotto non creato: L'ID del prodotto deve essere un numero intero");
            }
            try {
                String disponibilitaS=JOptionPane.showInputDialog(null,"Inserisci numero di prdotti disponibili");
                 disponibilita=Integer.parseInt(disponibilitaS);

            }
            catch (Exception exception){
                JOptionPane.showMessageDialog(null,"Errore, prodotto non creato: non hai inserito il numero di prodotti disponibili");
            }
            JOptionPane.showMessageDialog(null,"Prodotto creato e inserito nel catalogo correttamente");

            RigaCatalogo riga1 = new RigaCatalogo();
            riga1.setIdProdotto(prodotto.getId());
            riga1.setNomeProdotto(prodotto.getNome());
            riga1.setNomeProduttore(prodotto.getProduttore().getNome());
            riga1.setNomeCategoria(prodotto.getCategoria());
            riga1.setPrezzo(prodotto.getPrezzo());
            riga1.setDisponibilita(disponibilita);


            CatalogoPanel panel=CatalogoPanel.getInstance();
            List<RigaCatalogo> righe=panel.getRighe();
            righe.add(riga1);
            System.out.println("Dimensione: " +righe.size());
            panel.setRighe(righe);

        }else if(RIMUOVI.equals(action)){
            int id=0;
            try {
                String idS= JOptionPane.showInputDialog(null,"Inserisci l'ID del prodotto che vuoi rimuovere");
                 id=Integer.parseInt(idS);
            }catch (Exception exception){
                JOptionPane.showMessageDialog(null,"L'ID deve essere un numero intero");
            }
            CatalogoPanel panel=CatalogoPanel.getInstance();
            List<RigaCatalogo> righe=panel.getRighe();
            ListIterator<RigaCatalogo> iterator = righe.listIterator();
            boolean trovato=false;

            while (iterator.hasNext()){
                RigaCatalogo riga=iterator.next();
                if(id == riga.getIdProdotto() ){
                    righe.remove(riga);
                    trovato=true;
                    CatalogoPanel.getInstance().setRighe(righe);
                    JOptionPane.showMessageDialog(null,"Prodotto rimosso dal catalogo con successo");
                }else {
                    trovato=false;
                }
            }
            if(!trovato){
                JOptionPane.showMessageDialog(null,"L'ID inserito non esiste");
            }

        }else if (MODIFICA.equals(action)){
            int id=0;
            try {
                String idS= JOptionPane.showInputDialog(null,"Inserisci l'ID del prodotto che vuoi modificare");
                id=Integer.parseInt(idS);
            }catch (Exception exception){
                JOptionPane.showMessageDialog(null,"L'ID deve essere un numero intero");
            }
            CatalogoPanel panel=CatalogoPanel.getInstance();
            List<RigaCatalogo> righe=panel.getRighe();
            ListIterator<RigaCatalogo> iterator = righe.listIterator();
            boolean trovato=false;

            while (iterator.hasNext()){
                RigaCatalogo riga=iterator.next();
                if(id == riga.getIdProdotto() ){
                    trovato=true;
                    String campo1=JOptionPane.showInputDialog(null,"Inserisci campo da modificare tra: ID, Nome, Produttore, Categoria, Prezzo");
                    String campo=campo1.toLowerCase();
                    if("id".equals(campo)){
                        try {
                            String idS= JOptionPane.showInputDialog(null,"Inserisci l'ID del prodotto che vuoi modificare");
                            id=Integer.parseInt(idS);
                            riga.setIdProdotto(id);
                            JOptionPane.showMessageDialog(null,"Prodotto modificato con successo");
                        }catch (Exception exception){
                            JOptionPane.showMessageDialog(null,"L'ID deve essere un numero intero");
                        }
                    }
                    else if ("nome".equals(campo)){
                        String nome= JOptionPane.showInputDialog(null,"Inserisci il nome del prodotto che vuoi modificare");
                        riga.setNomeProdotto(nome);
                        JOptionPane.showMessageDialog(null,"Prodotto modificato con successo");
                    }
                    else if("produttore".equals(campo)){
                        String nomeP= JOptionPane.showInputDialog(null,"Inserisci il produttore del prodotto che vuoi modificare");
                        riga.setNomeProduttore(nomeP);
                        JOptionPane.showMessageDialog(null,"Prodotto modificato con successo");
                    }
                    else if("categoria".equals(campo)){
                        String nomeC= JOptionPane.showInputDialog(null,"Inserisci la categoria del prodotto che vuoi modificare");
                        riga.setNomeCategoria(nomeC);
                        JOptionPane.showMessageDialog(null,"Prodotto modificato con successo");
                    }
                    else if ("prezzo".equals(campo)){
                        try {
                            String pS= JOptionPane.showInputDialog(null,"Inserisci il prezzo del prodotto che vuoi modificare");
                            float prezzo=Float.parseFloat(pS);
                            riga.setPrezzo(prezzo);
                            JOptionPane.showMessageDialog(null,"Prodotto modificato con successo");
                        }catch (Exception exception){
                            JOptionPane.showMessageDialog(null,"Il prezzo deve essere un numero");
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Il campo inserito non è valido");
                    }

                    //CatalogoPanel.getInstance().setRighe(righe);
                    //JOptionPane.showMessageDialog(null,"Prodotto rimosso dal catalogo con successo");

                }else {
                    trovato=false;
                }
            }
            if(!trovato){
                JOptionPane.showMessageDialog(null,"L'ID inserito non esiste");
            }
        }






    }


/*
    public RigaCatalogo getRiga() {
        return riga1;
    }

 */

}
