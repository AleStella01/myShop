package View.Listener;

import Business.SessionManager;
import Model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class VisualizzaCommentiListener implements ActionListener {

    public final static String TUTTI = "Tutti i commenti";
    public final static String NORISPOSTA = "Commenti senza risposta";
    public final static String RISPOSTA = "Commenti con risposta";
    public final static String POSITIVI = "Tutti i commenti positivi";
    public final static String NEGATIVI = "Tutti i commenti negativi";

    private JFrame finestra;
    private List<Commento> commenti = new ArrayList<>();
    private JPanel centro;



    public VisualizzaCommentiListener(JFrame finestra, List<Commento> commenti,JPanel centro) {
        this.finestra = finestra;
        this.commenti = commenti;
        this.centro=centro;


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        Utente u = (Utente) SessionManager.getSession().get(SessionManager.LOGGED_USER);
        if (TUTTI.equals(action)) {
           centro.removeAll();
            ListIterator<Commento> iterator = commenti.listIterator();
            while (iterator.hasNext()) {
                Commento commento1 = iterator.next();
                centro.add(new JLabel("Commento di: "+ commento1.getCliente().getUsername()));
                centro.add(new JLabel("  Prodotti acquistati: "));
                Iterator<Map.Entry<IProdotto, Integer>> iterator1 = commento1.getListaAcquisto().getProdotti().entrySet().iterator();
                while (iterator1.hasNext()){
                    Map.Entry<IProdotto, Integer> entry = iterator1.next();
                    centro.add(new JLabel(entry.getKey().getNome()+", "));
                }
                centro.add(new JLabel("  Servizi acquistati: "));
                if(commento1.getListaAcquisto().getServizi().size()==0){
                    centro.add(new JLabel(" Nessun servizio"));
                }else {
                    ListIterator<IArticolo> servizi=commento1.getListaAcquisto().getServizi().listIterator();
                    while (servizi.hasNext()){
                        IArticolo servizio=servizi.next();
                        centro.add(new JLabel(servizio.getNome()+", "));
                    }
                }
                centro.add(new JLabel("    Numero di stelle: " + commento1.getNumeroStelle()));
                centro.add(new JLabel("   Testo del commento : " + commento1.getTesto()) );

                //centro.add(new JLabel(commento1.getTesto()));
                String stato;
                if (commento1.isRisposto()) {
                    stato = "Risposto";
                } else {
                    stato = "Non risposto";
                }
                centro.add(new JLabel("  Stato risposta: " + stato));
                if (commento1.isRisposto()) {
                    centro.add(new JLabel("  Risposta: " + commento1.getTestoRisposta()));
                } else {
                    if (u instanceof Manager) {
                        JButton rispondi = new JButton("Rispondi");
                        rispondi.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String risposta = JOptionPane.showInputDialog(null, "Inserisci risposta");
                                commento1.setTestoRisposta(u.getUsername() + "-Manager: " + risposta);
                                commento1.setRisposto(true);
                                finestra.revalidate();
                                finestra.repaint();
                            }
                        });

                        centro.add(rispondi);

                    }


                }
            }


            finestra.repaint();
            finestra.revalidate();

        } else if(NORISPOSTA.equals(action)){
            centro.removeAll();
            ListIterator<Commento> iterator = commenti.listIterator();
            while (iterator.hasNext()) {
                Commento commento1 = iterator.next();

                String stato;
                if (!commento1.isRisposto()) {
                    stato = "Non Risposto";
                    centro.add(new JLabel("Commento di: "+ commento1.getCliente().getUsername()));
                    centro.add(new JLabel("  Prodotti acquistati: "));
                    Iterator<Map.Entry<IProdotto, Integer>> iterator1 = commento1.getListaAcquisto().getProdotti().entrySet().iterator();
                    while (iterator1.hasNext()){
                        Map.Entry<IProdotto, Integer> entry = iterator1.next();
                        centro.add(new JLabel(entry.getKey().getNome()+", "));
                    }
                    centro.add(new JLabel("  Servizi acquistati: "));
                    if(commento1.getListaAcquisto().getServizi().size()==0){
                        centro.add(new JLabel(" Nessun servizio"));
                    }else {
                        ListIterator<IArticolo> servizi=commento1.getListaAcquisto().getServizi().listIterator();
                        while (servizi.hasNext()){
                            IArticolo servizio=servizi.next();
                            centro.add(new JLabel(servizio.getNome()+", "));
                        }
                    }
                    centro.add(new JLabel("    Numero di stelle: " + commento1.getNumeroStelle()));
                    centro.add(new JLabel("   Testo del commento : " + commento1.getTesto()) );
                    //centro.add(new JLabel(commento1.getTesto()));
                    centro.add(new JLabel("  Stato risposta: " + stato));

                        if (u instanceof Manager) {
                            JButton rispondi = new JButton("Rispondi");
                            rispondi.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String risposta = JOptionPane.showInputDialog(null, "Inserisci risposta");
                                    commento1.setTestoRisposta(u.getUsername() + "-Manager: " + risposta);
                                    commento1.setRisposto(true);
                                    finestra.revalidate();
                                    finestra.repaint();
                                }
                            });
                            centro.add(rispondi);

                        }

                }

            }

            finestra.repaint();
            finestra.revalidate();

        }else if (RISPOSTA.equals(action)){
           centro.removeAll();
            ListIterator<Commento> iterator = commenti.listIterator();
            while (iterator.hasNext()) {
                Commento commento1 = iterator.next();
                String stato;
                if (commento1.isRisposto()) {
                    centro.add(new JLabel("Commento di: "+ commento1.getCliente().getUsername()));
                    centro.add(new JLabel("  Prodotti acquistati: "));
                    Iterator<Map.Entry<IProdotto, Integer>> iterator1 = commento1.getListaAcquisto().getProdotti().entrySet().iterator();
                    while (iterator1.hasNext()){
                        Map.Entry<IProdotto, Integer> entry = iterator1.next();
                        centro.add(new JLabel(entry.getKey().getNome()+", "));
                    }
                    centro.add(new JLabel("  Servizi acquistati: "));
                    if(commento1.getListaAcquisto().getServizi().size()==0){
                        centro.add(new JLabel(" Nessun servizio"));
                    }else {
                        ListIterator<IArticolo> servizi=commento1.getListaAcquisto().getServizi().listIterator();
                        while (servizi.hasNext()){
                            IArticolo servizio=servizi.next();
                            centro.add(new JLabel(servizio.getNome()+", "));
                        }
                    }
                    centro.add(new JLabel("    Numero di stelle: " + commento1.getNumeroStelle()));
                    centro.add(new JLabel("   Testo del commento : " + commento1.getTesto()) );
                    //centro.add(new JLabel(commento1.getTesto()));
                    stato = " Risposto";
                    centro.add(new JLabel("  Stato risposta: " + stato));
                        centro.add(new JLabel("  Risposta: " + commento1.getTestoRisposta()));

                }
            }

            finestra.repaint();
            finestra.revalidate();

        }else if(POSITIVI.equals(action)){
            centro.removeAll();
            ListIterator<Commento> iterator = commenti.listIterator();
            while (iterator.hasNext()) {
                Commento commento1 = iterator.next();
                if(commento1.getNumeroStelle() >= 3){
                    centro.add(new JLabel("Commento di: "+ commento1.getCliente().getUsername()));
                    centro.add(new JLabel("  Prodotti acquistati: "));
                    Iterator<Map.Entry<IProdotto, Integer>> iterator1 = commento1.getListaAcquisto().getProdotti().entrySet().iterator();
                    while (iterator1.hasNext()){
                        Map.Entry<IProdotto, Integer> entry = iterator1.next();
                        centro.add(new JLabel(entry.getKey().getNome()+", "));
                    }
                    centro.add(new JLabel("  Servizi acquistati: "));
                    if(commento1.getListaAcquisto().getServizi().size()==0){
                        centro.add(new JLabel(" Nessun servizio"));
                    }else {
                        ListIterator<IArticolo> servizi=commento1.getListaAcquisto().getServizi().listIterator();
                        while (servizi.hasNext()){
                            IArticolo servizio=servizi.next();
                            centro.add(new JLabel(servizio.getNome()+", "));
                        }
                    }
                    centro.add(new JLabel("    Numero di stelle: " + commento1.getNumeroStelle()));
                    centro.add(new JLabel("   Testo del commento : " + commento1.getTesto()) );
                    //centro.add(new JLabel(commento1.getTesto()));
                    String stato;
                    if (commento1.isRisposto()) {
                        stato = "Risposto";
                    } else {
                        stato = "Non risposto";
                    }
                    centro.add(new JLabel("  Stato risposta: " + stato));
                    if (commento1.isRisposto()) {
                        centro.add(new JLabel("  Risposta: " + commento1.getTestoRisposta()));
                    } else {
                        if (u instanceof Manager) {
                            JButton rispondi = new JButton("Rispondi");
                            rispondi.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String risposta = JOptionPane.showInputDialog(null, "Inserisci risposta");
                                    commento1.setTestoRisposta(u.getUsername() + "-Manager: " + risposta);
                                    commento1.setRisposto(true);
                                    finestra.revalidate();
                                    finestra.repaint();
                                }
                            });
                            centro.add(rispondi);

                        }

                    }
                }

            }

            finestra.repaint();
            finestra.revalidate();

        }else if (NEGATIVI.equals(action)){
           centro.removeAll();
            ListIterator<Commento> iterator = commenti.listIterator();
            while (iterator.hasNext()) {
                Commento commento1 = iterator.next();
                if(commento1.getNumeroStelle() < 3){
                    centro.add(new JLabel("  Commento di: "+ commento1.getCliente().getUsername()));
                    centro.add(new JLabel("  Prodotti acquistati: "));
                    Iterator<Map.Entry<IProdotto, Integer>> iterator1 = commento1.getListaAcquisto().getProdotti().entrySet().iterator();
                    while (iterator1.hasNext()){
                        Map.Entry<IProdotto, Integer> entry = iterator1.next();
                        centro.add(new JLabel(entry.getKey().getNome()+", "));
                    }
                    centro.add(new JLabel("  Servizi acquistati: "));
                    if(commento1.getListaAcquisto().getServizi().size()==0){
                        centro.add(new JLabel(" Nessun servizio"));
                    }else {
                        ListIterator<IArticolo> servizi=commento1.getListaAcquisto().getServizi().listIterator();
                        while (servizi.hasNext()){
                            IArticolo servizio=servizi.next();
                            centro.add(new JLabel(servizio.getNome()+", "));
                        }
                    }
                    centro.add(new JLabel("    Numero di stelle: " + commento1.getNumeroStelle()));
                    centro.add(new JLabel("   Testo del commento : " + commento1.getTesto()) );
                    //centro.add(new JLabel(commento1.getTesto()));
                    String stato;
                    if (commento1.isRisposto()) {
                        stato = "Risposto";
                    } else {
                        stato = "Non risposto";
                    }
                    centro.add(new JLabel("  Stato risposta: " + stato));
                    if (commento1.isRisposto()) {
                        centro.add(new JLabel("  Risposta: " + commento1.getTestoRisposta()));
                    } else {
                        if (u instanceof Manager) {
                            JButton rispondi = new JButton("Rispondi");
                            rispondi.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String risposta = JOptionPane.showInputDialog(null, "Inserisci risposta");
                                    commento1.setTestoRisposta(u.getUsername() + "-Manager: " + risposta);
                                    commento1.setRisposto(true);
                                    finestra.revalidate();
                                    finestra.repaint();
                                }
                            });
                            centro.add(rispondi);

                        }

                    }
                }

            }

            finestra.repaint();
            finestra.revalidate();

        }
    }
}
