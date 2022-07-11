package View.Listener;

import Model.ListaAcquisto;
import Model.Produttore;
import Model.composite.IProdotto;
import Model.composite.Prodotto;
import View.CarrelloTableModel;
import View.ViewModel.RigaCatalogo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

public class CarrelloListener implements ActionListener {

    private List<RigaCatalogo> righeSelezionate;
    private ListaAcquisto listaAcquisto=new ListaAcquisto();

    public CarrelloListener(List<RigaCatalogo> righeSelezionate) {
        this.righeSelezionate=righeSelezionate;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(righeSelezionate.size() == 0){
            System.out.println("Seleziona uno o più prodotti e inseriscili nel carrello");
        }
        else {
            JPanel nord=new JPanel();
            JPanel sud=new JPanel();
            JPanel east=new JPanel();
            JPanel west=new JPanel();
            JPanel centro=new JPanel();

            nord.setLayout(new FlowLayout());
            sud.setLayout(new FlowLayout());
            east.setLayout(new FlowLayout());
            west.setLayout(new FlowLayout());
            centro.setLayout(new FlowLayout());

            JFrame finestra=new JFrame();
            finestra.setSize(600,700);
            finestra.setTitle("Carrello");
            Container c=finestra.getContentPane();
            c.setLayout(new BorderLayout());
            c.add(nord,BorderLayout.NORTH);
            c.add(sud,BorderLayout.SOUTH);
            c.add(east,BorderLayout.EAST);
            c.add(west,BorderLayout.WEST);
            c.add(centro,BorderLayout.CENTER);

           nord.add(new JLabel("Ripilogo articoli selezionati"));

            /*
            ListIterator<RigaCatalogo> iterator= righeSelezionate.listIterator();
            while (iterator.hasNext()){
                RigaCatalogo riga=iterator.next();
               // if(riga.getSelezionato() != null){
                   // if (riga.getSelezionato()){

                       centro.add(new JLabel("Nome articolo: " + riga.getNomeProdotto() + " ID: " + riga.getIdProdotto() + " Prezzo: " + riga.getPrezzo() + "$" + "\n"));

                   // }
               // }
            }

             */



            CarrelloTableModel tableModel = new CarrelloTableModel(righeSelezionate);
            JTable tabella = new JTable(tableModel);

            tabella.setRowHeight(100);

            JScrollPane scrollPane = new JScrollPane(tabella);
            finestra.add(scrollPane, BorderLayout.CENTER);






            JButton acquista=new JButton("Procedi all'acquisto");
            JButton svuota=new JButton("Svuota carrello");
            JButton lista=new JButton("Genera lista d'acquisto");
            svuota.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ListIterator<RigaCatalogo> iterator= righeSelezionate.listIterator();
                    while (iterator.hasNext()){
                        RigaCatalogo rigaCatalogo=iterator.next();
                        rigaCatalogo.setDisponibilita(rigaCatalogo.getDisponibilita()+rigaCatalogo.getQuantitaInserita());
                    }
                    righeSelezionate.clear();

                    centro.removeAll();
                    finestra.revalidate();
                    finestra.repaint();
                }
            });



            lista.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    /*
                    ListIterator<RigaCatalogo> iterator= righeSelezionate.listIterator();
                    while (iterator.hasNext()){
                        RigaCatalogo riga=iterator.next();
                        //System.out.println(riga.getPrezzo());
                    }

                     */
                    if(righeSelezionate.size()>0){
                        List<IProdotto> prodottoList=new ArrayList<>();
                        for (int i=0;i<righeSelezionate.size();i++){
                            RigaCatalogo riga=righeSelezionate.get(i);
                            Prodotto prodotto=new Prodotto();
                            prodotto.setNome(riga.getNomeProdotto());
                            prodotto.setPrezzo(riga.getPrezzo());
                            prodotto.setCategoria(riga.getNomeCategoria());
                            Produttore produttore=new Produttore();
                            produttore.setNome(riga.getNomeProduttore());
                            prodotto.setProduttore(produttore);
                            prodottoList.add(prodotto);
                        }

                        //ListaAcquisto listaAcquisto=new ListaAcquisto();
                        ListaAcquisto listaAcquisto2=new ListaAcquisto();
                        listaAcquisto2.setProdotti(prodottoList);
                        listaAcquisto2.setDataCreazione(Date.from(Instant.now()));
                        listaAcquisto2.setStatoLista(ListaAcquisto.StatoLista.DA_PAGARE);
                        ListaListener listaListener=ListaListener.getInstance();
                        listaListener.setLista(listaAcquisto2);
                        listaAcquisto=listaAcquisto2;
                        JOptionPane.showMessageDialog(null,"Lista generata correttamente");
                    }else {
                        JOptionPane.showMessageDialog(null,"Lista non generata");

                    }


                }
            });

            acquista.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(righeSelezionate.size()>0){
                        List<IProdotto> prodottoList=new ArrayList<>();
                        for (int i=0;i<righeSelezionate.size();i++){
                            RigaCatalogo riga=righeSelezionate.get(i);
                            Prodotto prodotto=new Prodotto();
                            prodotto.setNome(riga.getNomeProdotto());
                            prodotto.setPrezzo(riga.getPrezzo());
                            prodotto.setCategoria(riga.getNomeCategoria());
                            Produttore produttore=new Produttore();
                            produttore.setNome(riga.getNomeProduttore());
                            prodotto.setProduttore(produttore);
                            prodottoList.add(prodotto);
                        }

                        //ListaAcquisto listaAcquisto=new ListaAcquisto();
                        ListaAcquisto listaAcquisto2=new ListaAcquisto();
                        listaAcquisto2.setProdotti(prodottoList);
                        listaAcquisto2.setDataCreazione(Date.from(Instant.now()));
                        listaAcquisto2.setStatoLista(ListaAcquisto.StatoLista.PAGATA);
                        ListaListener listaListener=ListaListener.getInstance();
                        listaListener.setLista(listaAcquisto2);
                        listaAcquisto=listaAcquisto2;
                        JOptionPane.showMessageDialog(null,"Lista generata correttamente");
                        JOptionPane.showMessageDialog(null,"Dirigiti alla cassa per effettuare il pagamento");
                    }else {
                        JOptionPane.showMessageDialog(null,"Lista non generata");

                    }


                    listaAcquisto.setStatoLista(ListaAcquisto.StatoLista.PAGATA);

                }
            });

            sud.add(lista);
            sud.add(acquista);
            sud.add(svuota);







            finestra.setVisible(true);
            finestra.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }

    }
}
