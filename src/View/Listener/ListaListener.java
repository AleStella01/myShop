package View.Listener;

import DAO.*;
import Business.SessionManager;
import Model.*;
import View.CatalogoPanel;
import View.ViewModel.RigaCatalogo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

public class ListaListener implements ActionListener {
    private List<ListaAcquisto> liste=new ArrayList<>();
    private ListaAcquisto lista;
    private List<RigaCatalogo> righeEffettive=new ArrayList<>();


    public ListaListener() {


    }

    public List<RigaCatalogo> getRigheEffettive() {
        return righeEffettive;
    }

    public void setRigheEffettive(List<RigaCatalogo> righeEffettive) {
        this.righeEffettive = righeEffettive;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        ListaAcquistoDAO listaAcquistoDAO = ListaAcquistoDAO.getInstance();

        Utente u = (Utente) SessionManager.getSession().get(SessionManager.LOGGED_USER);

        liste=listaAcquistoDAO.findByUser(u);

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

        int i=1;

        ListIterator<ListaAcquisto> listIterator=liste.listIterator();
        while (listIterator.hasNext()){
            ListaAcquisto lista = listIterator.next();
            centro.add(new JLabel(lista.getCliente().getUsername()));
            //List<RigaCatalogo> righePerListener=new ArrayList<>();
            //righePerListener=righeEffettive;
            //System.out.println("Dimensione catalòoghiiii: " + lista.getRigheCatalogo().size());

            Iterator<Map.Entry<IProdotto,Integer>> lprodotti = lista.getProdotti().entrySet().iterator();
           // List<IArticolo> prodotti=;
           // ListIterator<IProdotto> lprodotti=prodotti.listIterator();
            while (lprodotti.hasNext()){
                Map.Entry<IProdotto,Integer> entry=lprodotti.next();
                IProdotto prodotto= entry.getKey();
               centro.add(new JLabel("Nome prodotto: " + prodotto.getNome()));
               centro.add(new JLabel("Prezzo: " + prodotto.getPrezzo()));
            }
                centro.add(new JLabel("Servizi: "));
                if(lista.getServizi().size()==0){
                    centro.add(new JLabel(" Nessun servizio"));
                }
                ListIterator<IArticolo> servizioListIterator1=lista.getServizi().listIterator();
                while (servizioListIterator1.hasNext()){
                    Servizio servizioEffettivo= (Servizio) servizioListIterator1.next();
                    centro.add(new JLabel("  Nome servizio:  "+servizioEffettivo.getNome()));
                    centro.add(new JLabel("  Prezzo servizio: " +servizioEffettivo.getPrezzo()));
                }


            centro.add(new JLabel("  Stato lista: " + lista.isPagato()));
            if(!lista.isPagato()){
                JButton acquista = new JButton("Acquista");
                acquista.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        IPuntoVendita puntoVendita = CatalogoPanel.getInstance().getPuntoVendita();
                        Iterator<Map.Entry<IProdotto, Integer>> righeEffettive = lista.getProdotti().entrySet().iterator();
                        while (righeEffettive.hasNext()){
                            boolean presente;
                            Map.Entry<IProdotto, Integer> ri2 = righeEffettive.next();
                            IArticolo articolo = prodottoDAO.findByName(ri2.getKey().getNome());
                            IProdotto prodotto = (IProdotto) articolo;
                            CatalogoPanel catalogoPanel=CatalogoPanel.getInstance();
                            presente = catalogoPanel.getPuntoVendita().getProdottiVenduti().containsKey(prodotto);
                            if(!presente){
                                JOptionPane.showMessageDialog(null,"Il prodotto " + ri2.getKey().getNome() + " non è più presente nel catalogo");
                                return;
                            }
                           if(ri2.getValue() > puntoVendita.getProdottiVenduti().get(prodotto)){
                               JOptionPane.showMessageDialog(null,"Errore la quuantità del prodotto " + ri2.getKey().getNome() + " è superiore della disponibilità del prodotto in magazzino" );
                               return;
                           }
                           else{
                               int quantita=catalogoPanel.getPuntoVendita().getProdottiVenduti().get(prodotto);
                               puntoVenditaDAO.removeProdottoVenduto(catalogoPanel.getPuntoVendita(), prodotto);
                               puntoVenditaDAO.addProdottoVenduto(catalogoPanel.getPuntoVendita(), prodotto,catalogoPanel.getPuntoVendita().getProdottiVenduti().get(prodotto));
                           }
                        }


                        ServizioDAO servizioDAO=ServizioDAO.getInstance();
                        List<IArticolo> servizi=servizioDAO.findAll();

                        JFrame frame=new JFrame();
                        JPanel west=new JPanel();
                        west.setLayout(new GridLayout(10,1));
                        JPanel north=new JPanel();
                        north.setLayout(new GridLayout(10,1));
                        Container container=frame.getContentPane();
                        container.setLayout(new BorderLayout());
                        container.add(west,BorderLayout.CENTER);
                        container.add(north,BorderLayout.WEST);

                        JButton bottoneServizi=new JButton("Inserisci servizi");
                        west.add(bottoneServizi);
                        frame.setTitle("Vuoi aggiungere dei servizi altuo ordine?");
                        frame.setBounds(100,100,400,200);
                        frame.setVisible(true);
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame.revalidate();
                        frame.repaint();

                        ListIterator<IArticolo> servizioListIterator=servizi.listIterator();
                        while (servizioListIterator.hasNext()){
                            Servizio servizio= (Servizio) servizioListIterator.next();
                            JRadioButton jRadioButton1 = new JRadioButton(servizio.getNome()+" Prezzo: " + servizio.getPrezzo());
                            north.add(jRadioButton1);

                            bottoneServizi.addActionListener(new ActionListener() {

                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(jRadioButton1.isSelected()){

                                        lista.addServizio(servizio);
                                        //Elimino eventuali duplicati
                                        List<IArticolo> serviziEffettivi = lista.getServizi().stream().distinct().collect(Collectors.toList());
                                        for(int i = 0; i < serviziEffettivi.size(); i++){
                                            lista.addServizio(serviziEffettivi.get(i));
                                        }
                                        listaAcquistoDAO.addServizio(lista,servizio, u.getIndirizzo());


                                        //listaAcquisto2.setCostoTotale(listaAcquisto2.getCostoTotale()+costoTotale);
                                        JOptionPane.showMessageDialog(null,"Servizio: " + servizio.getNome()+ " inserito correttamente");

                                    }

                                }
                            });

                        }

                        JButton avanti=new JButton("Avanti");
                        avanti.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                Float costoTotale = 0F;
                                Iterator<Map.Entry<IProdotto, Integer>> entryIterator = lista.getProdotti().entrySet().iterator();
                                while (entryIterator.hasNext()){
                                    Map.Entry<IProdotto, Integer> entry = entryIterator.next();
                                    costoTotale += entry.getKey().getPrezzo() * entry.getValue();
                                }

                                ListIterator<IArticolo> serviziIterator = lista.getServizi().listIterator();
                                while (serviziIterator.hasNext()){
                                    IArticolo a = serviziIterator.next();
                                    costoTotale += a.getPrezzo();
                                }

                                frame.dispose();

                                JOptionPane.showMessageDialog(null,"Il costo totale è: " + costoTotale);
                                JOptionPane.showMessageDialog(null,"Puoi dirigerti alla cassa per pagare");
                                lista.setPagato(true);
                                lista.setPuntoVendita(CatalogoPanel.getInstance().getPuntoVendita());
                                listaAcquistoDAO.update(lista);
                                finestra.revalidate();
                                finestra.repaint();

                            }
                        });
                        west.add(avanti);
                    }
                });
                centro.add(acquista);
            }
            else {
                JButton acquista=new JButton("Acquista di nuovo");
                acquista.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Iterator<Map.Entry<IProdotto, Integer>> righeEffIterator = lista.getProdotti().entrySet().iterator();
                        while (righeEffIterator.hasNext()){
                            boolean presente;
                            Map.Entry<IProdotto, Integer> entry = righeEffIterator.next();

                            IArticolo articolo=prodottoDAO.findByName(entry.getKey().getNome());
                            IProdotto prodotto=(IProdotto) articolo;
                            IPuntoVendita puntoVendita = CatalogoPanel.getInstance().getPuntoVendita();
                            presente = puntoVendita.getProdottiVenduti().containsKey(prodotto);

                            if(!presente){
                                JOptionPane.showMessageDialog(null,"Il prodotto " + entry.getKey().getNome() + " non è più presente nel catalogo");
                                return;
                            }
                            if(entry.getValue() > puntoVendita.getProdottiVenduti().get(entry.getKey())){
                                JOptionPane.showMessageDialog(null,"Errore la quuantità del prodotto " + entry.getKey().getNome() + " è superiore della disponibilità del prodotto in magazzino" );
                                return;
                            }
                            else{
                                int quantita= puntoVendita.getProdottiVenduti().get(prodotto);
                                puntoVenditaDAO.removeProdottoVenduto(puntoVendita, prodotto);
                                puntoVenditaDAO.addProdottoVenduto(puntoVendita, prodotto, puntoVendita.getProdottiVenduti().get(prodotto));
                            }
                        }

                        Float costoTotale = 0F;
                        Iterator<Map.Entry<IProdotto, Integer>> entryIterator = lista.getProdotti().entrySet().iterator();
                        while (entryIterator.hasNext()){
                            Map.Entry<IProdotto, Integer> entry = entryIterator.next();
                            costoTotale += entry.getKey().getPrezzo() * entry.getValue();
                        }

                        ListIterator<IArticolo> serviziIterator = lista.getServizi().listIterator();
                        while (serviziIterator.hasNext()){
                            IArticolo a = serviziIterator.next();
                            costoTotale += a.getPrezzo();
                        }
                        JOptionPane.showMessageDialog(null,"Il costo totale è: " + costoTotale);
                        JOptionPane.showMessageDialog(null,"Puoi dirigerti alla cassa per pagare");
                    }
                });
                JButton commenta= new JButton("Commenta prodotti");


                CommentoListener commentoListener=new CommentoListener(lista);
                commenta.addActionListener(commentoListener);
                centro.add(acquista);
                centro.add(commenta);
            }

            JButton rimuovi=new JButton("Rimuovi lista");
            rimuovi.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    liste.remove(lista);
                    listaAcquistoDAO.removeById(lista.getId());
                    //centro.removeAll();
                    finestra.revalidate();
                    finestra.repaint();
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
