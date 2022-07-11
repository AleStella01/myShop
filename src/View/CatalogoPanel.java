package View;

import Business.SessionManager;
import Model.Utente;
import View.ViewModel.RigaCatalogo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class CatalogoPanel extends JPanel {

    private static CatalogoPanel instance=new CatalogoPanel();
    private List<RigaCatalogo> righeSelezionate=new ArrayList<>();
    private List<RigaCatalogo> righe=new ArrayList<>();

    private CatalogoPanel() {

        //JLabel benvenuto = new JLabel("Qui mostreremo la tabella dei prodotti");
        //add(benvenuto);

        setLayout(new BorderLayout());

        String[][] dati = new String[3][5];
        dati[0][0] = "a";
        dati[0][1] = "b";
        dati[0][2] = "c";
        dati[0][3] = "d";
        dati[0][4] = "e";
        dati[1][0] = "f";
        dati[1][1] = "g";
        dati[1][2] = "h";
        dati[1][3] = "i";
        dati[1][4] = "j";
        dati[2][0] = "k";
        dati[2][1] = "l";
        dati[2][2] = "m";
        dati[2][3] = "n";
        dati[2][4] = "o";
        String[] nomiColonne = new String[] {"Colonna 1", "Colonna 2", "Colonna 3", "Colonna 4", "Colonna 5"};

        //JTable tabella = new JTable(dati, nomiColonne);

        //java.util.List<RigaCatalogo> righe = new ArrayList<RigaCatalogo>();
        //popoliamo con dati fake (voi dovete popolare tramite DAO)
        RigaCatalogo riga1 = new RigaCatalogo();
        riga1.setIdProdotto(759);
        riga1.setNomeProdotto("Tavolo giardino");
        riga1.setNomeProduttore("Produttore X");
        riga1.setNomeCategoria("Esterni");
        riga1.setPrezzo(99.9F);
        riga1.setDisponibilita(5);


        RigaCatalogo riga2 = new RigaCatalogo();
        riga2.setIdProdotto(88);
        riga2.setNomeProdotto("Sedia");
        riga2.setNomeProduttore("Produttore Y");
        riga2.setNomeCategoria("Casa");
        riga2.setPrezzo(50.0F);
        riga2.setDisponibilita(1);


        RigaCatalogo riga3 = new RigaCatalogo();
        riga3.setIdProdotto(54);
        riga3.setNomeProdotto("Tosaerba");
        riga3.setNomeProduttore("Produttore Z");
        riga3.setNomeCategoria("Utensili");
        riga3.setPrezzo(350.0F);
        riga3.setDisponibilita(6);


        righe.add(riga1);
        righe.add(riga2);
        righe.add(riga3);

        CatalogoTableModel tableModel = new CatalogoTableModel(righe);
        //CatalogoTableModel tableModel =CatalogoTableModel.getInstance();
        //tableModel.setRighe(righe);
        System.out.println(righe.size());
        JTable tabella = new JTable(tableModel);

        tabella.setRowHeight(200);

        JScrollPane scrollPane = new JScrollPane(tabella);
        add(scrollPane, BorderLayout.CENTER);

        JPanel pulsantiAzioneTabella = new JPanel();
        pulsantiAzioneTabella.setLayout(new FlowLayout());
        JButton mettiNelCarrello = new JButton("Metti nel carrello");
        //java.util.List<RigaCatalogo> righeSelezionate = new ArrayList<RigaCatalogo>();

        mettiNelCarrello.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                int[] righeSelezionate = tabella.getSelectedRows();
                System.out.println(righeSelezionate[0]);
                CatalogoTableModel tModel = (CatalogoTableModel) tabella.getModel();
                for(int i=0;i<righeSelezionate.length;i++) {
                    RigaCatalogo rigaSelezionata = tModel.getRighe().get(righeSelezionate[i]);
                    System.out.println("ID prodotto selezionato: "+rigaSelezionata.getIdProdotto());
                }

                 */
               // java.util.List<RigaCatalogo> righeSelezionate2 = new ArrayList<RigaCatalogo>();
                ListIterator<RigaCatalogo> iterator= righe.listIterator();
                while (iterator.hasNext()){
                    RigaCatalogo riga=iterator.next();
                    int quantita=0;
                    if(riga.getSelezionato() != null){
                        if (riga.getSelezionato()){
                            try {
                                if(riga.getDisponibilita() == 0){
                                    JOptionPane.showMessageDialog(null,"Il prodotto " + riga.getNomeProdotto() + " non è al momento disponibile in magazzino");
                                    return;
                                }
                                String quantitaS=JOptionPane.showInputDialog(null,"Inserisci quantità de: " + riga.getNomeProdotto());
                                quantita=Integer.parseInt(quantitaS);
                                if(quantita > riga.getDisponibilita()){
                                    JOptionPane.showMessageDialog(null,"La quantità desiderata è maggiore della disponibilità del prodotto");
                                    return;
                                }else {
                                    riga.setDisponibilita(riga.getDisponibilita() - quantita);
                                    riga.setQuantitaInserita(quantita);
                                }
                            }catch (Exception exception){
                                JOptionPane.showMessageDialog(null,"La quantità deve essere un numero intero");
                            }

                            //righeSelezionate2.add(riga);
                            for (int i=0; i<quantita;i++){
                                righeSelezionate.add(riga);
                                System.out.println("Nome prodotto selezionato: " +riga.getNomeProdotto());
                            }



                        }
                    }
                }
/*
                ListIterator<RigaCatalogo> iterator2=righeSelezionate2.listIterator();
                while (iterator2.hasNext()){
                    RigaCatalogo rigaCatalogo=iterator2.next();
                    righeSelezionate.add(rigaCatalogo);
                }

 */




            }
        });


        Utente u=(Utente) SessionManager.getSession().get(SessionManager.LOGGED_USER);
        /*if(u instanceof Cliente){
            JButton carrello=new JButton("Vai al carrello");
            CarrelloListener carrelloListener=new CarrelloListener(righeSelezionate);
            carrello.addActionListener(carrelloListener);
            pulsantiAzioneTabella.add(carrello);
            //add(pulsantiAzioneTabella,BorderLayout.SOUTH);
        }
        else if (u instanceof Amministratore){
            JButton aggiungi=new JButton("Aggiungi prodotto");
            AggiuntaProdottoListener listener=new AggiuntaProdottoListener();
            righe.add(listener.getRiga());
            CatalogoTableModel tableModel1=new CatalogoTableModel(righe);
            //tableModel.setRighe(righe);
            System.out.println("Righe fuori " + righe.size());
            aggiungi.addActionListener(listener);
            pulsantiAzioneTabella.add(aggiungi);
        }

         */



        System.out.println("vedi " + righe.size());






        pulsantiAzioneTabella.add(mettiNelCarrello);
        //add(mettiNelCarrello, BorderLayout.SOUTH);
        add(pulsantiAzioneTabella,BorderLayout.SOUTH);

    }

    public static CatalogoPanel getInstance(){
        return instance;
    }

    public List<RigaCatalogo> getRigheSelezionate() {
        return righeSelezionate;
    }


    public List<RigaCatalogo> getRighe() {
        return righe;
    }

    public void setRighe(List<RigaCatalogo> righe) {
        this.righe = righe;
    }
}
