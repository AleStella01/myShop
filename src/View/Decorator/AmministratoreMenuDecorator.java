package View.Decorator;

import DAO.ProdottoCompositoDAO;
import DAO.ProdottoDAO;
import DAO.PuntoVenditaDAO;
import Model.*;
import View.CatalogoPanel;
import View.Listener.*;
import View.PuntiVenditaModel;
import View.SchiedaArticoloTableModel;
import View.ViewModel.RigaCatalogo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class AmministratoreMenuDecorator extends CustomMenuDecorator {

    private ClienteMenuDecorator menuDecorator;

    public AmministratoreMenuDecorator(Menu menu,ClienteMenuDecorator menuDecorator) {
        this.menu = menu;
        this.menuDecorator=menuDecorator;
    }

    @Override
    public List<JButton> getPulsanti() {

        // ha tutte le funzioni del guest
        pulsanti.addAll(this.menu.getPulsanti());
        pulsanti.addAll(this.menuDecorator.getPulsanti());

        CatalogoPanel panel=CatalogoPanel.getInstance();
        List<RigaCatalogo> righeSelezionate=panel.getRigheSelezionate();


        JButton aggiungi = new JButton("Aggiungi prodotto");
        AggiungiArticoloListener listener=new AggiungiArticoloListener();
        aggiungi.setActionCommand(AggiungiArticoloListener.AGGIUNGI);
        aggiungi.addActionListener(listener);
        JButton rimuovi = new JButton("Rimuovi prodotto");
        rimuovi.setActionCommand(AggiungiArticoloListener.RIMUOVI);
        rimuovi.addActionListener(new RimuoviArticoloListener());
        JButton modifica = new JButton("Modifica prodotto");
        modifica.setActionCommand(AggiungiArticoloListener.MODIFICA);
        modifica.addActionListener(new ModificaArticoloListener());

        JButton crea=new JButton("Crea nuovo punto vendita");
        crea.addActionListener(new PuntoVenditaListener());

        JButton prodotti=new JButton("Mostra tutti i prodotti");
        prodotti.addActionListener(new ActionListener() {
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
                finestra.setSize(1000,700);
                Container c=finestra.getContentPane();
                c.setLayout(new BorderLayout());
                c.add(nord,BorderLayout.NORTH);
                c.add(sud,BorderLayout.SOUTH);
                c.add(east,BorderLayout.EAST);
                c.add(west,BorderLayout.WEST);
                c.add(centro,BorderLayout.CENTER);

                List<RigaCatalogo> righe=new ArrayList<>();

                List<IArticolo> prodottiList = ProdottoDAO.getInstance().findAll();
                prodottiList.addAll(ProdottoCompositoDAO.getInstance().findAll());
                ListIterator<IArticolo> prodottiIterator= prodottiList.listIterator();
                while (prodottiIterator.hasNext()){
                    IArticolo prodotto=prodottiIterator.next();
                    RigaCatalogo riga1 = new RigaCatalogo();
                    riga1.setIdProdotto(prodotto.getId());
                    riga1.setNomeProdotto(prodotto.getNome());
                    riga1.setNomeCategoria(prodotto.getCategoria().getNome());
                    riga1.setPrezzo(prodotto.getPrezzo());
                    if(prodotto instanceof Prodotto){
                        riga1.setNomeProduttore(((Prodotto) prodotto).getProduttore().getNomeProduttore());
                        riga1.setProdottoComposito("NO");
                        riga1.setProdotti("//");
                        riga1.setCorsia(((Prodotto) prodotto).getCollocazione().getCorsia());
                        riga1.setScaffale(((Prodotto) prodotto).getCollocazione().getScaffale());
                    }else if(prodotto instanceof ProdottoComposito){
                        riga1.setNomeProduttore("MyShop");
                        riga1.setProdottoComposito("SI");
                        riga1.setCorsia("//");
                        riga1.setScaffale(0);
                        String visualizzaProdotti="";
                        ListIterator<IProdotto> prodottiInclusi=ProdottoCompositoDAO.getInstance().getProdotti(((ProdottoComposito) prodotto)).listIterator();
                        while (prodottiInclusi.hasNext()){
                            IProdotto prodotto1= prodottiInclusi.next();
                            visualizzaProdotti=visualizzaProdotti + prodotto1.getNome()+",\n";
                        }
                        riga1.setProdotti(visualizzaProdotti);

                    }

                    righe.add(riga1);


                }

 /*
                InputStream stream = getClass().getResourceAsStream(image);
                try {
                    ImageIcon icon = new ImageIcon(ImageIO.read(stream));
                    riga1.setImage(icon);
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(null,"Errore l'immagine non è inserita nella cartella references o il titolo è sbagliato");
                    exception.printStackTrace();
                    return;

                }
/*
                JFileChooser chooser = new JFileChooser(System.getProperty("user.home") );
                String image2=null;

                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                     image2 = chooser.getSelectedFile().toString();
                    InputStream stream2 = getClass().getResourceAsStream(image2);
                    try {
                        ImageIcon icon = new ImageIcon(ImageIO.read(stream2));
                        riga1.setImage(icon);
                    } catch (IOException exception) {
                        //throw new RuntimeException(e);
                        JOptionPane.showMessageDialog(null,"Errore l'immagine non è inserita nella cartella references o il titolo è sbagliato");
                        exception.printStackTrace();
                        return;

                    }
                }

 */
                SchiedaArticoloTableModel tableModel=new SchiedaArticoloTableModel(righe);
                JTable table=new JTable(tableModel);
                JScrollPane scrollPane=new JScrollPane(table);
                c.add(scrollPane,BorderLayout.CENTER);

                finestra.setVisible(true);
                finestra.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


            }
        });

        JButton mostraPunti =new JButton("Mostra punti vendita");
        mostraPunti.addActionListener(new ActionListener() {
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
                finestra.setSize(1000,700);
                Container c=finestra.getContentPane();
                c.setLayout(new BorderLayout());
                c.add(nord,BorderLayout.NORTH);
                c.add(sud,BorderLayout.SOUTH);
                c.add(east,BorderLayout.EAST);
                c.add(west,BorderLayout.WEST);
                c.add(centro,BorderLayout.CENTER);

                List<IPuntoVendita> puntiVendita= PuntoVenditaDAO.getInstance().findAll();
                System.out.println(puntiVendita.size());
                PuntiVenditaModel tableModel=new PuntiVenditaModel(puntiVendita);
                JTable table=new JTable(tableModel);
                JScrollPane scrollPane=new JScrollPane(table);
                c.add(scrollPane,BorderLayout.CENTER);

                finestra.setVisible(true);
                finestra.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            }
        });


        pulsanti.add(aggiungi);
        pulsanti.add(rimuovi);
        pulsanti.add(modifica);
        pulsanti.add(crea);
        pulsanti.add(prodotti);
        pulsanti.add(mostraPunti);


        return pulsanti;
    }
}
