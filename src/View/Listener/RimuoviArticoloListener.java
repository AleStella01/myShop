package View.Listener;

import DAO.*;
import Model.IArticolo;
import Model.Prodotto;
import Model.Servizio;

import View.CatalogoPanel;
import View.SchiedaArticoloTableModel;
import View.ViewModel.RigaCatalogo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class RimuoviArticoloListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

        ProduttoreDAO produttoreDAO=ProduttoreDAO.getInstance();
        CategoriaDAO categoriaDAO=CategoriaDAO.getInstance();
        CollocazioneDAO collocazioneDAO=CollocazioneDAO.getInstance();

        String[] optionsToChoose = {"Prodotto","Prodotto Composito", "Servizio"};

        JFrame jFrame = new JFrame();

        JComboBox<String> jComboBox = new JComboBox<>(optionsToChoose);
        jComboBox.setBounds(80, 50, 140, 20);

        JButton jButton = new JButton("Done");
        jButton.setBounds(100, 100, 90, 20);

        JLabel jLabel = new JLabel();
        jLabel.setBounds(90, 100, 400, 100);

        jFrame.add(jButton);
        jFrame.add(jComboBox);
        jFrame.add(jLabel);

        jFrame.setTitle("Seleziona l'articolo che vuoi eliminare");

        jFrame.setLayout(null);
        jFrame.setSize(350, 250);
        jFrame.setVisible(true);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String scelta= jComboBox.getItemAt(jComboBox.getSelectedIndex());
                if("prodotto".equalsIgnoreCase(scelta)){
                    ProdottoDAO prodottoDAO=ProdottoDAO.getInstance();

                    int id=0;
                    try {
                        String idS= JOptionPane.showInputDialog(null,"Inserisci l'ID del prodotto che vuoi rimuovere");
                        id=Integer.parseInt(idS);
                    }catch (Exception exception){
                        JOptionPane.showMessageDialog(null,"L'ID deve essere un numero intero");
                        return;
                    }
                    boolean trovato=false;
                    List<IArticolo> prodotti=prodottoDAO.findAll();
                    ListIterator<IArticolo> prodottiIterator=prodotti.listIterator();




                    while (prodottiIterator.hasNext()){
                        Prodotto prodotto=(Prodotto) prodottiIterator.next();
                        if(prodotto.getId() == id ){
                            trovato=true;
                            List<RigaCatalogo> righeCercate= new ArrayList<>();

                            RigaCatalogo riga=new RigaCatalogo();
                            riga.setIdProdotto(prodotto.getId());
                            riga.setNomeProdotto(prodotto.getNome());
                            riga.setNomeProduttore(prodotto.getProduttore().getNomeProduttore());
                            riga.setNomeCategoria(prodotto.getCategoria().getNome());
                            riga.setPrezzo(prodotto.getPrezzo());

                            righeCercate.add(riga);



                            JFrame finestra=creaFinestra();
                            finestra.setTitle("Riepilogo eliminazione articolo");
                            finestra.add(new JLabel("Schieda articolo"), BorderLayout.NORTH);
                            SchiedaArticoloTableModel tableModel = new SchiedaArticoloTableModel(righeCercate);
                            JTable tabella = new JTable(tableModel);

                            tabella.setRowHeight(100);

                            JScrollPane scrollPane = new JScrollPane(tabella);
                            finestra.add(scrollPane, BorderLayout.CENTER);
                            JButton ok=new JButton("Rimuovi articolo");
                            ok.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    prodottoDAO.removeById(prodotto.getId());
                                    JOptionPane.showMessageDialog(null,"Prodotto rimosso dal catalogo con successo");
                                    CatalogoPanel.getInstance().revalidate();
                                    CatalogoPanel.getInstance().repaint();

                                }
                            });
                            finestra.add(ok,BorderLayout.SOUTH);
                            finestra.setVisible(true);
                            finestra.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            break;


                        }

                    }
                    if(!trovato){
                        JOptionPane.showMessageDialog(null,"L'ID inserito non esiste");

                    }


                }else if("servizio".equalsIgnoreCase(scelta)){

                    ServizioDAO servizioDAO=ServizioDAO.getInstance();

                    int id=0;
                    try {
                        String idS= JOptionPane.showInputDialog(null,"Inserisci l'ID del servizio che vuoi rimuovere");
                        id=Integer.parseInt(idS);



                    }catch (Exception exception){
                        JOptionPane.showMessageDialog(null,"L'ID deve essere un numero intero");
                        return;
                    }
                    boolean trovato=false;
                    List<IArticolo> servizi=servizioDAO.findAll();
                    ListIterator<IArticolo> serviziIterator=servizi.listIterator();




                    while (serviziIterator.hasNext()){
                        Servizio servizio=(Servizio) serviziIterator.next();
                        if(servizio.getId() == id ){
                            trovato=true;



                            JFrame finestra=creaFinestra();
                            finestra.setTitle("Riepilogo eliminazione articolo");
                            finestra.add(new JLabel("Schieda servizio: " + servizio.getNome()+ " Prezzo: " + servizio.getPrezzo()),BorderLayout.NORTH);

                            JButton ok=new JButton("Rimuovi articolo");
                            ok.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    servizioDAO.removeById(servizio.getId());
                                    JOptionPane.showMessageDialog(null,"Sevizio rimosso con successo");
                                    CatalogoPanel.getInstance().revalidate();
                                    CatalogoPanel.getInstance().repaint();

                                }
                            });
                            finestra.add(ok,BorderLayout.SOUTH);
                            finestra.setVisible(true);
                            finestra.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            break;


                        }

                    }
                    if(!trovato){
                        JOptionPane.showMessageDialog(null,"L'ID inserito non esiste");

                    }



                }/*else if ("Prodotto Composito".equals(scelta)){
                    ProdottoCompositoDAO prodottoCompositoDAO= ProdottoCompositoDAO.getInstance();

                    int id=0;
                    try {
                        String idS= JOptionPane.showInputDialog(null,"Inserisci l'ID del prodotto che vuoi rimuovere");
                        id=Integer.parseInt(idS);
                    }catch (Exception exception){
                        JOptionPane.showMessageDialog(null,"L'ID deve essere un numero intero");
                        return;
                    }
                    boolean trovato=false;
                    List<IArticolo> prodotti=prodottoCompositoDAO.findAll();
                    ListIterator<IArticolo> prodottiIterator=prodotti.listIterator();




                    while (prodottiIterator.hasNext()){
                        ProdottoComposito prodottoComposito=(ProdottoComposito) prodottiIterator.next();
                        if(prodottoComposito.getId() == id ){
                            trovato=true;
                            List<RigaCatalogo> righeCercate= new ArrayList<>();
                            String visualizzaProdotti="";
                            ListIterator<IProdotto> prodottiInclusi=prodottoComposito.getSottoprodotti().listIterator();
                            while (prodottiInclusi.hasNext()){
                                Prodotto prodotto=(Prodotto) prodottiInclusi.next();
                                visualizzaProdotti=prodotto.getNome()+", ";
                            }

                            RigaCatalogo riga=new RigaCatalogo();
                            riga.setIdProdotto(prodottoComposito.getId());
                            riga.setNomeProdotto(prodottoComposito.getNome());
                            riga.setNomeProduttore(prodottoComposito.getProduttore().getNomeProduttore());
                            riga.setNomeCategoria(prodottoComposito.getCategoria().getNome());
                            riga.setPrezzo(prodottoComposito.getPrezzo());
                            riga.setProdottoComposito("No");
                            riga.setProdotti(visualizzaProdotti);

                            righeCercate.add(riga);



                            JFrame finestra=creaFinestra();
                            finestra.setTitle("Riepilogo eliminazione articolo");
                            finestra.add(new JLabel("Schieda articolo"), BorderLayout.NORTH);
                            SchiedaArticoloTableModel tableModel = new SchiedaArticoloTableModel(righeCercate);
                            JTable tabella = new JTable(tableModel);

                            tabella.setRowHeight(100);

                            JScrollPane scrollPane = new JScrollPane(tabella);
                            finestra.add(scrollPane, BorderLayout.CENTER);
                            JButton ok=new JButton("Rimuovi articolo");
                            ok.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    prodottoCompositoDAO.removeById(prodottoComposito.getId());
                                    JOptionPane.showMessageDialog(null,"Prodotto rimosso dal catalogo con successo");
                                    CatalogoPanel.getInstance().revalidate();
                                    CatalogoPanel.getInstance().repaint();

                                }
                            });
                            finestra.add(ok,BorderLayout.SOUTH);
                            finestra.setVisible(true);
                            finestra.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            break;


                        }

                    }
                    if(!trovato){
                        JOptionPane.showMessageDialog(null,"L'ID inserito non esiste");

                    }



                }
                */


            }
        });




    }


    public JFrame creaFinestra(){
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
        return finestra;

    }






}
