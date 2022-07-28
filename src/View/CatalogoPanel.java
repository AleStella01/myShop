package View;

import DAO.PuntoVenditaDAO;
import Model.IProdotto;
import Model.IPuntoVendita;
import Model.Prodotto;
import Model.ProdottoComposito;
import View.ViewModel.RigaCatalogo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.*;

public class CatalogoPanel extends JPanel {

    private static CatalogoPanel instance = new CatalogoPanel();
    private List<RigaCatalogo> righeSelezionate = new ArrayList<>();
    private List<RigaCatalogo> righe=new ArrayList<>();
    private IPuntoVendita puntoVendita;

    private CatalogoPanel() {
        setLayout(new BorderLayout());

        PuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();

        puntoVendita = puntoVenditaDAO.findByName("cavour");
        System.out.println(puntoVendita.getClass());
        System.out.println(puntoVendita.getId());
        System.out.println(puntoVendita.getNome());

          Iterator<Map.Entry<IProdotto,Integer>> prodottiIterator = puntoVendita.getProdottiVenduti().entrySet().iterator();
          while (prodottiIterator.hasNext()){
              RigaCatalogo riga = new RigaCatalogo();
              Map.Entry<IProdotto,Integer> entry = prodottiIterator.next();
              IProdotto prodotto=  entry.getKey();
              int quantita = entry.getValue();

              System.out.println("PRODOTTO: " + prodotto.getNome() + "Quantità: " + quantita);

              //puntoVenditaDAO.addProdottoVenduto(puntoVendita,prodotto,quantita);
              riga.setIdProdotto(prodotto.getId());
              riga.setNomeProdotto(prodotto.getNome());
              riga.setNomeProduttore(prodotto.getProduttore().getNomeProduttore());
              riga.setNomeCategoria(prodotto.getCategoria().getNome());
              if(prodotto instanceof Prodotto){
                  riga.setProdottoComposito("No");
                  riga.setProdotti("//");
              }else if(prodotto instanceof ProdottoComposito){
                  riga.setProdottoComposito("Si");
                  ProdottoComposito prodottoComposito=(ProdottoComposito) prodotto;
                  String visualizzaProdotti="";
                  ListIterator<IProdotto> prodottiInclusi=prodottoComposito.getSottoprodotti().listIterator();
                  while (prodottiInclusi.hasNext()){
                      Prodotto prodottoIncluso=(Prodotto) prodottiInclusi.next();
                      visualizzaProdotti=prodottoIncluso.getNome()+", ";
                  }
                  riga.setProdotti(visualizzaProdotti);
              }

              riga.setCorsia(prodotto.getCollocazione().getCorsia());
              riga.setScaffale(prodotto.getCollocazione().getScaffale());
              riga.setPrezzo(prodotto.getPrezzo());
              riga.setDisponibilita(quantita);
              InputStream stream = getClass().getResourceAsStream("/"+prodotto.getNome()+"jpg");
//              try {
//                  ImageIcon icon = new ImageIcon(ImageIO.read(stream));
//                  riga.setImage(icon);
//              } catch (IOException e) {
//                  e.printStackTrace();
//
//              }
              righe.add(riga);


          }

        CatalogoTableModel tableModel = new CatalogoTableModel(righe);

        System.out.println(righe.size());
        JTable tabella = new JTable(tableModel);

        tabella.setRowHeight(200);

        JScrollPane scrollPane = new JScrollPane(tabella);
        add(scrollPane, BorderLayout.CENTER);

        JPanel pulsantiAzioneTabella = new JPanel();
        pulsantiAzioneTabella.setLayout(new FlowLayout());
        JButton mettiNelCarrello = new JButton("Metti nel carrello");


        mettiNelCarrello.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
            repaint();
            revalidate();

            }
        });

        System.out.println("vedi " + righe.size());

        pulsantiAzioneTabella.add(mettiNelCarrello);

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

    public IPuntoVendita getPuntoVendita() {
        return puntoVendita;
    }

    public void setPuntoVendita(IPuntoVendita puntoVendita) {
        this.puntoVendita = puntoVendita;
    }
}
