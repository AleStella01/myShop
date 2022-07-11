package View.Listener;

import Model.ListaAcquisto;
import Model.composite.IProdotto;
import Model.composite.Prodotto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ListaListener implements ActionListener {
    private static ListaListener instance = new ListaListener();
    private List<ListaAcquisto> liste=new ArrayList<>();
    private ListaAcquisto lista = new ListaAcquisto();


    private ListaListener() {

    }

    public static ListaListener getInstance() {
        return instance;
    }

    public void setLista(ListaAcquisto lista) {
        this.lista = lista;
        liste.add(lista);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

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
        finestra.setTitle("Liste D'Acuqisto");
        Container c=finestra.getContentPane();
        c.setLayout(new BorderLayout());
        c.add(nord,BorderLayout.NORTH);
        c.add(sud,BorderLayout.SOUTH);
        c.add(east,BorderLayout.EAST);
        c.add(west,BorderLayout.WEST);
        c.add(centro,BorderLayout.CENTER);
/*
        List<List<RigaCatalogo>> liste=new ArrayList<>();
        liste.add(righeSelezionate);

        ListIterator<List<RigaCatalogo>> iterator=liste.listIterator();
        while (iterator.hasNext()){
           List<RigaCatalogo> lista=iterator.next();
            ListIterator<RigaCatalogo> iterator2= lista.listIterator();
            while (iterator.hasNext()){
                RigaCatalogo riga=iterator2.next();
                centro.add(new JLabel("Nome articolo: " + riga.getNomeProdotto() + " ID: " + riga.getIdProdotto() + " Prezzo: " + riga.getPrezzo() + "$" + "\n"));
            }
        }

 */


        System.out.println(liste.size());
        int i=1;

        ListIterator<ListaAcquisto> listIterator=liste.listIterator();
        while (listIterator.hasNext()){
            ListaAcquisto lista=listIterator.next();
            lista.setNome("LISTA " + i +":");
            centro.add(new JLabel( lista.getNome()));


            List<IProdotto> prodotti=lista.getProdotti();
            ListIterator<IProdotto> lprodotti=prodotti.listIterator();
            while (lprodotti.hasNext()){
                Prodotto prodotto=(Prodotto) lprodotti.next();
               centro.add(new JLabel("Nome prodotto: " + prodotto.getNome()));
               centro.add(new JLabel("Prezzo: " + prodotto.getPrezzo()));
            }
            centro.add(new JLabel("Stato lista: " + lista.getStatoLista()));
            if(lista.getStatoLista() == ListaAcquisto.StatoLista.DA_PAGARE){
                JButton acquista=new JButton("Acquista");
                centro.add(acquista);
            }
            else {
                JButton acquista=new JButton("Acquista di nuovo");
                acquista.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(null,"Puoi dirigerti alla cassa per pagare");
                    }
                });
                centro.add(acquista);
            }

            JButton rimuovi=new JButton("Rimuovi lista");
            rimuovi.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    liste.remove(lista);
                    centro.removeAll();
                    finestra.revalidate();
                    finestra.revalidate();
                }
            });

            centro.add(rimuovi);
            i++;
        }




        JButton rimuoviTutto = new JButton("Rimuovi tutte le liste");
        rimuoviTutto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                liste.clear();
                centro.removeAll();
                finestra.repaint();
                finestra.revalidate();
            }
        });

        sud.add(rimuoviTutto);





        finestra.setVisible(true);
        finestra.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

}
