package View.Listener;

import Business.SessionManager;
import DAO.CommentoDAO;
import DAO.ListaAcquistoDAO;
import Model.*;
import View.ViewModel.RigaCatalogo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.*;

public class FinestraCommentListener implements ActionListener {


    private List<Commento> commenti=new ArrayList<>();
    private Commento commento;
    private String tipologia;
    private String username;
    private String tipo;
    private Map<Integer,List<Commento>> commentiClienti =new HashMap<>();
    private List<RigaCatalogo> righeEffettive=new ArrayList<>();


    public FinestraCommentListener() {

    }
    @Override
    public void actionPerformed(ActionEvent e) {

        Utente u = (Utente) SessionManager.getSession().get(SessionManager.LOGGED_USER);
        CommentoDAO commentoDAO=CommentoDAO.getInstance();
        commenti=commentoDAO.findAll();

        if(u instanceof Cliente) {
            tipo="Cliente";
        }
        else if(u instanceof Manager){
            tipo="Manager";
        }
        else if(u instanceof Admin){

           tipo="Amministratore";
        }

        JPanel nord=new JPanel();
        JPanel sud=new JPanel();
        JPanel east=new JPanel();
        JPanel west=new JPanel();
        JPanel centro=new JPanel();

        nord.setLayout(new FlowLayout());
        sud.setLayout(new FlowLayout());
        east.setLayout(new FlowLayout());
        west.setLayout(new FlowLayout());
        centro.setLayout(new GridLayout(commenti.size()+1,6));

        JScrollPane scrollPane=new JScrollPane(centro);

        JFrame finestra=new JFrame();
        finestra.setSize(600,700);
        finestra.setTitle("Commenti");
        Container c=finestra.getContentPane();
        c.setLayout(new BorderLayout());
        c.add(nord,BorderLayout.NORTH);
        c.add(sud,BorderLayout.SOUTH);
        c.add(east,BorderLayout.EAST);
        c.add(west,BorderLayout.WEST);
        //c.add(centro,BorderLayout.CENTER);
        c.add(scrollPane,BorderLayout.CENTER);

        ListIterator<Commento> iterator=commenti.listIterator();
        centro.add(new JLabel("COMMENTO DI:  "));
        centro.add(new JLabel("PRODOTTI ACQUISTATI "));
        centro.add(new JLabel("SERVIZI ACQUISTATI  "));
        centro.add(new JLabel("NUMERO STELLE "));
        centro.add(new JLabel("TESTO COMMENTO "));
        centro.add(new JLabel("RISPOSTA"));

        while (iterator.hasNext()){
            String nomiProdotti="";
            String nomiServizi="";
            Commento commento1=iterator.next();
            centro.add(new JLabel( commento1.getCliente().getUsername()));

            Map<IProdotto, Integer> prodotti = ListaAcquistoDAO.getInstance().getProdotti(commento1.getListaAcquisto());
            Iterator<Map.Entry<IProdotto, Integer>> iterator1 = prodotti.entrySet().iterator();
            while (iterator1.hasNext()){
                Map.Entry<IProdotto, Integer> entry = iterator1.next();
                commento1.getListaAcquisto().addProdotto(entry.getKey(), entry.getValue());
            }

            List<IArticolo> servizi1 = ListaAcquistoDAO.getInstance().getServizi(commento1.getListaAcquisto());
            ListIterator<IArticolo> listIterator = servizi1.listIterator();
            while (listIterator.hasNext()){
                commento1.getListaAcquisto().addServizio(listIterator.next());
            }

            Iterator<Map.Entry<IProdotto, Integer>> prodottiIterator = commento1.getListaAcquisto().getProdotti().entrySet().iterator();
            while (prodottiIterator.hasNext()){
                Map.Entry<IProdotto,Integer> prodotto = prodottiIterator.next();
               nomiProdotti= nomiProdotti + "" + prodotto.getKey().getNome() + ",\n " ;
            }
            centro.add(new JLabel(nomiProdotti));
            if(commento1.getListaAcquisto().getServizi().size()==0){
               nomiServizi="Nessun Servizio";
            }else {
                ListIterator<IArticolo> servizi = commento1.getListaAcquisto().getServizi().listIterator();
                while (servizi.hasNext()){
                    IArticolo servizio = servizi.next();
                  nomiServizi = nomiServizi + "" + servizio.getNome() + ", \n";
                }
            }
            centro.add(new JLabel(nomiServizi));

            centro.add(new JLabel(String.valueOf(commento1.getNumeroStelle())));
            centro.add(new JLabel ( commento1.getTesto()) );
            //centro.add(new JLabel( commento1.getTesto()) );
            String stato;
            if(commento1.isRisposto()){
                stato="Risposto";
            }else {
                stato="Non risposto";
            }
            //centro.add(new JLabel("  Stato risposta: " + stato));
            if(commento1.isRisposto()){
                centro.add(new JLabel( commento1.getTestoRisposta()));
            }else {
                if(u instanceof Manager){
                    JButton rispondi=new JButton("Rispondi");
                    rispondi.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String risposta=JOptionPane.showInputDialog(null,"Inserisci risposta");
                            commento1.setTestoRisposta(risposta);
                            commento1.setRisposto(true);
                            commentoDAO.update(commento1);
                            finestra.revalidate();
                            finestra.repaint();
                        }
                    });
                    centro.add(rispondi);
                    JMenuBar menuBar = new JMenuBar();
                    finestra.setJMenuBar(menuBar);

                    JMenu menuFile = new JMenu("Visualizza commenti");
                    menuBar.add(menuFile);

                    VisualizzaCommentiListener visualizzaCommentiListener=new VisualizzaCommentiListener(finestra,commenti,centro);

                    JMenuItem tuttiCommenti = new JMenuItem("Tutti i commenti");
                    tuttiCommenti.setActionCommand(VisualizzaCommentiListener.TUTTI);
                    tuttiCommenti.addActionListener(visualizzaCommentiListener);
                    menuFile.add(tuttiCommenti);

                    JMenuItem commentiNoRisposta = new JMenuItem("Commenti senza risposta");
                    commentiNoRisposta.setActionCommand(VisualizzaCommentiListener.NORISPOSTA);
                    commentiNoRisposta.addActionListener(visualizzaCommentiListener);
                    menuFile.add(commentiNoRisposta);

                    JMenuItem commentiConRisposta=new JMenuItem("Commenti con risposta");
                    commentiConRisposta.setActionCommand(VisualizzaCommentiListener.RISPOSTA);
                    commentiConRisposta.addActionListener(visualizzaCommentiListener);
                    menuFile.add(commentiConRisposta);

                    JMenuItem commentiPositivi=new JMenuItem("Commenti positivi(numero stelle >= 3)");
                    commentiPositivi.setActionCommand(VisualizzaCommentiListener.POSITIVI);
                    commentiPositivi.addActionListener(visualizzaCommentiListener);
                    menuFile.add(commentiPositivi);

                    JMenuItem commentiNegativi= new JMenuItem("Commenti negativi(numero stelle < 3");
                    commentiNegativi.setActionCommand(VisualizzaCommentiListener.NEGATIVI);
                    commentiNegativi.addActionListener(visualizzaCommentiListener);
                    menuFile.add(commentiNegativi);


                }

            }
        }




        finestra.setVisible(true);
        finestra.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);






    }


    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<RigaCatalogo> getRigheEffettive() {
        return righeEffettive;
    }

    public void setRigheEffettive(List<RigaCatalogo> righeEffettive) {
        this.righeEffettive = righeEffettive;
    }
}
