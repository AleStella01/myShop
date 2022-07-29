package View.Listener;

import Business.DocumentoListaAcquisto;
import Business.PdfBoxAPI;
import Business.SessionManager;
import DAO.ListaAcquistoDAO;
import DAO.ProdottoDAO;
import DAO.PuntoVenditaDAO;
import DAO.ServizioDAO;
import Model.*;

import View.CarrelloTableModel;
import View.CatalogoPanel;
import View.ViewModel.RigaCatalogo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.stream.Collectors;

public class CarrelloListener implements ActionListener {

    private List<RigaCatalogo> righeSelezionate;
    private ListaAcquisto listaAcquisto;
    private float somma=0;
    private boolean procedi=false;

    public CarrelloListener(List<RigaCatalogo> righeSelezionate) {
        this.righeSelezionate=righeSelezionate;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(righeSelezionate.size() == 0){
            JOptionPane.showMessageDialog(null,"Il carrello è vuoto: Seleziona uno o più prodotti e inseriscili nel carrello");
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

           // Metodo per rimuovere i duplicati da un ArrayList
            List<RigaCatalogo> righeEffettive = righeSelezionate.stream().distinct().collect(Collectors.toList());
            System.out.println("Dimensione " + righeEffettive.size());
            ListIterator<RigaCatalogo> righeEffIterator= righeEffettive.listIterator();
            while (righeEffIterator.hasNext()){
                RigaCatalogo ri2=righeEffIterator.next();
                System.out.println(ri2.getNomeProdotto() + " Prezzo: " +  ri2.getPrezzo());
            }


            Utente u = (Utente) SessionManager.getSession().get(SessionManager.LOGGED_USER);

            CarrelloTableModel tableModel = new CarrelloTableModel(righeSelezionate);
            JTable tabella = new JTable(tableModel);

            tabella.setRowHeight(100);



            JScrollPane scrollPane = new JScrollPane(tabella);
            finestra.add(scrollPane, BorderLayout.CENTER);


            ProdottoDAO prodottoDAO=ProdottoDAO.getInstance();
            ListaAcquistoDAO listaAcquistoDAO=ListaAcquistoDAO.getInstance();
            PuntoVenditaDAO puntoVenditaDAO=PuntoVenditaDAO.getInstance();

            ServizioDAO servizioDAO=ServizioDAO.getInstance();
            List<IArticolo> servizi=servizioDAO.findAll();




            //JButton acquista=new JButton("Procedi all'acquisto");
            JButton svuota=new JButton("Svuota carrello");
            JButton lista=new JButton("Genera lista d'acquisto");
            svuota.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ListIterator<RigaCatalogo> iterator= righeSelezionate.listIterator();
                    while (iterator.hasNext()){
                        RigaCatalogo rigaCatalogo=iterator.next();
                        rigaCatalogo.setDisponibilita(rigaCatalogo.getDisponibilita() + 1);
                    }
                    righeSelezionate.clear();

                    somma=0;

                    CatalogoPanel.getInstance().repaint();
                    CatalogoPanel.getInstance().revalidate();


                    centro.removeAll();
                    finestra.revalidate();
                    finestra.repaint();
                }
            });



            lista.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(righeSelezionate.size()>0){
                        ListaAcquistoFactory.ListaAcquistoType type = ListaAcquistoFactory.ListaAcquistoType.CLIENTE;
                        ListaAcquistoFactory factory = new ListaAcquistoFactory();
                        ListaAcquisto listaAcquisto2 = factory.getListaAcquistoFactory(type);

                        listaAcquisto2.setCliente(u);

                        ListIterator<RigaCatalogo> righeEffIterator= righeEffettive.listIterator();
                        while (righeEffIterator.hasNext()){
                            RigaCatalogo ri2=righeEffIterator.next();
                            IArticolo articolo=prodottoDAO.findByName(ri2.getNomeProdotto());
                            IProdotto prodotto=(IProdotto) articolo;
                            listaAcquisto2.addProdotto(prodotto,ri2.getQuantitaInserita());
//                            listaAcquistoDAO.addProdotto(listaAcquisto2,articolo,ri2.getQuantitaInserita());
                        }

                        listaAcquisto2.setPagato(false);

                        float somma2 = 0;
                        ListIterator<RigaCatalogo> listIterator = righeSelezionate.listIterator();
                        while (listIterator.hasNext()){
                            RigaCatalogo rigaCatalogo = listIterator.next();
                            somma2 += rigaCatalogo.getPrezzo();
                        }

                        listaAcquisto2.setCliente(u);
                        listaAcquisto2.setPuntoVendita(CatalogoPanel.getInstance().getPuntoVendita());
                        //System.out.println(listaAcquisto2);
                        listaAcquistoDAO.add(listaAcquisto2);
                        List<ListaAcquisto> liste = listaAcquistoDAO.findAll();
                        listaAcquisto2.setId(liste.get(liste.size() - 1).getId());
                        Iterator<Map.Entry<IProdotto, Integer>> iterator = listaAcquisto2.getProdotti().entrySet().iterator();
                        while (iterator.hasNext()){
                            Map.Entry<IProdotto, Integer> entry = iterator.next();
                            listaAcquistoDAO.addProdotto(listaAcquisto2, (IArticolo) entry.getKey(), entry.getValue());
                        }
                        ListIterator<IArticolo> listIterator1 = listaAcquisto2.getServizi().listIterator();
                        while (listIterator1.hasNext()){
                            IArticolo servizio = listIterator1.next();
                            listaAcquistoDAO.addServizio(listaAcquisto2, servizio, listaAcquisto2.getIndirizzoSpedizione());
                        }
                        listaAcquisto=listaAcquisto2;
                        DocumentoListaAcquisto documentoListaAcquisto=new DocumentoListaAcquisto(new PdfBoxAPI(), listaAcquisto2);
                        documentoListaAcquisto.invia(u.getEmail());
                        JOptionPane.showMessageDialog(null,"Lista generata correttamente");
                    }else {
                        JOptionPane.showMessageDialog(null,"Lista non generata");
                    }
                }
            });

//            acquista.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    if(righeSelezionate.size()>0){
//                        ListaAcquistoFactory.ListaAcquistoType type = ListaAcquistoFactory.ListaAcquistoType.CLIENTE;
//                        ListaAcquistoFactory factory = new ListaAcquistoFactory();
//                        ListaAcquisto listaAcquisto2 = factory.getListaAcquistoFactory(type);
//
//                        JFrame frame=new JFrame();
//                        JPanel west=new JPanel();
//                        west.setLayout(new GridLayout(10,1));
//                        JPanel north=new JPanel();
//                        north.setLayout(new GridLayout(10,1));
//                        Container container=frame.getContentPane();
//                        container.setLayout(new BorderLayout());
//                        container.add(west,BorderLayout.CENTER);
//                        container.add(north,BorderLayout.WEST);
//
//                        JButton bottoneServizi=new JButton("Inserisci servizi");
//                        west.add(bottoneServizi);
//                        frame.setTitle("Vuoi aggiungere dei servizi altuo ordine?");
//                        frame.setBounds(100,100,400,200);
//                        frame.setVisible(true);
//                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//                        frame.revalidate();
//                        frame.repaint();
//
//
//                        ListIterator<IArticolo> servizioListIterator=servizi.listIterator();
//                        while (servizioListIterator.hasNext()){
//                            Servizio servizio=(Servizio) servizioListIterator.next();
//                            JRadioButton jRadioButton1=new JRadioButton(servizio.getNome()+" Prezzo: " + servizio.getPrezzo());
//                            north.add(jRadioButton1);
//
//                            bottoneServizi.addActionListener(new ActionListener() {
//
//                                @Override
//                                public void actionPerformed(ActionEvent e) {
//                                    if(jRadioButton1.isSelected()){
//                                        //Elimino eventuali duplicati
//                                        List<IArticolo> serviziEffettivi=listaAcquisto2.getServizi().stream().distinct().collect(Collectors.toList());
//                                        for(int i = 0; i < serviziEffettivi.size(); i++){
//                                            listaAcquisto2.addServizio(serviziEffettivi.get(i));
//                                        }
//                                        listaAcquistoDAO.addServizio(listaAcquisto2,servizio, u.getIndirizzo());
//                                        JOptionPane.showMessageDialog(null,"Servizio: " + servizio.getNome()+ " inserito correttamente");
//                                    }
//                                }
//                            });
//                        }
//
//                        JButton avanti=new JButton("Avanti");
//                        avanti.addActionListener(new ActionListener() {
//                            @Override
//                            public void actionPerformed(ActionEvent e) {
//                                frame.dispose();
//
//                                listaAcquisto2.setCliente(u);
//
//                                ListIterator<RigaCatalogo> righeEffIterator= righeEffettive.listIterator();
//                                while (righeEffIterator.hasNext()){
//                                    RigaCatalogo ri2=righeEffIterator.next();
//                                    IArticolo articolo=prodottoDAO.findByName(ri2.getNomeProdotto());
//                                    IProdotto prodotto=(IProdotto) articolo;
//                                    listaAcquisto2.addProdotto(prodotto,ri2.getQuantitaInserita());
//                                    CatalogoPanel catalogoPanel=CatalogoPanel.getInstance();
//                                    int quantita=catalogoPanel.getPuntoVendita().getProdottiVenduti().get(prodotto);
//                                    catalogoPanel.getPuntoVendita().getProdottiVenduti().replace(prodotto,quantita-ri2.getQuantitaInserita());
//                                    puntoVenditaDAO.removeProdottoVenduto(catalogoPanel.getPuntoVendita(), prodotto);
//                                    puntoVenditaDAO.addProdottoVenduto(catalogoPanel.getPuntoVendita(), prodotto,catalogoPanel.getPuntoVendita().getProdottiVenduti().get(prodotto));
//                                    listaAcquistoDAO.addProdotto(listaAcquisto2,articolo,ri2.getQuantitaInserita());
//
//                                }
//
//                                listaAcquisto2.setPagato(true);
//
//                                float somma2=0;
//                                ListIterator<RigaCatalogo> listIterator = righeSelezionate.listIterator();
//                                while (listIterator.hasNext()){
//                                    RigaCatalogo rigaCatalogo=listIterator.next();
//                                    somma2 = somma2 + rigaCatalogo.getPrezzo();
//                                }
//
//                                listaAcquisto2.setCliente(u);
//
//                                listaAcquisto=listaAcquisto2;
//
//                                listaAcquistoDAO.update(listaAcquisto2);
//                                DocumentoListaAcquisto documentoListaAcquisto=new DocumentoListaAcquisto(new PdfBoxAPI(), listaAcquisto2);
//                                documentoListaAcquisto.invia(u.getEmail());
//                                //MailHelper mailHelper=new MailHelper();
//                                //mailHelper.send("nicdepascali@gmail.com", "MyShop","Prova");
//
//                                Float costoTotale = 0F;
//                                Iterator<Map.Entry<IProdotto, Integer>> entryIterator = listaAcquisto2.getProdotti().entrySet().iterator();
//                                while (entryIterator.hasNext()){
//                                    Map.Entry<IProdotto, Integer> entry = entryIterator.next();
//                                    costoTotale += entry.getKey().getPrezzo() * entry.getValue();
//                                }
//
//                                ListIterator<IArticolo> serviziIterator = listaAcquisto2.getServizi().listIterator();
//                                while (serviziIterator.hasNext()){
//                                    IArticolo a = serviziIterator.next();
//                                    costoTotale += a.getPrezzo();
//                                }
//
//                                JOptionPane.showMessageDialog(null,"Lista generata correttamente");
//                                JOptionPane.showMessageDialog(null,"Il costo è di : " + costoTotale + " Dirigiti alla cassa per effettuare il pagamento");
//
//
//                                somma=0;
//                                righeSelezionate.clear();
//
//
//
//                                centro.removeAll();
//                                finestra.revalidate();
//                                finestra.repaint();
//
//                            }
//                        });
//                        west.add(avanti);
//
//
//
//
//
//
//
//
//                    }else {
//                        JOptionPane.showMessageDialog(null,"Lista non generata");
//
//                    }
//
//                }
//            });


            ListIterator<RigaCatalogo> listIterator = righeSelezionate.listIterator();
            while (listIterator.hasNext()){
                RigaCatalogo rigaCatalogo=listIterator.next();
                somma=somma+rigaCatalogo.getPrezzo();
            }

            sud.add(new JLabel("Costo totale: " + somma +"$"));



            sud.add(lista);
            //sud.add(acquista);
            sud.add(svuota);

            finestra.revalidate();
            finestra.repaint();





            somma=0;

            finestra.setVisible(true);
            finestra.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }

    }



}
