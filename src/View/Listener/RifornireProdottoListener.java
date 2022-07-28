package View.Listener;

import DAO.PuntoVenditaDAO;

import Model.IProdotto;
import Model.IPuntoVendita;
import Model.Prodotto;
import View.CatalogoPanel;
import View.SchiedaArticoloTableModel;
import View.ViewModel.RigaCatalogo;

import javax.swing.*;
import javax.swing.text.html.parser.Entity;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class RifornireProdottoListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        PuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        int id=0;
        try {
            String idS= JOptionPane.showInputDialog(null,"Inserisci l'ID del prodotto che vuoi rifornire");
            id=Integer.parseInt(idS);
        }catch (Exception exception){
            JOptionPane.showMessageDialog(null,"L'ID deve essere un numero intero");
        }
        CatalogoPanel panel = CatalogoPanel.getInstance();
        Map<IProdotto, Integer> prodotti = panel.getPuntoVendita().getProdottiVenduti();
        Iterator<Map.Entry<IProdotto, Integer>> iterator = prodotti.entrySet().iterator();

        boolean trovato = false;

        while (iterator.hasNext()){
            Map.Entry<IProdotto, Integer> entry = iterator.next();
            if(id == entry.getKey().getId()){
                trovato=true;

                RigaCatalogo riga = new RigaCatalogo();
                riga.setIdProdotto(entry.getKey().getId());
                riga.setNomeProdotto(entry.getKey().getNome());
                riga.setNomeProduttore(entry.getKey().getProduttore().getNomeProduttore());
                riga.setNomeCategoria(entry.getKey().getCategoria().getNome());
                riga.setPrezzo(entry.getKey().getPrezzo());
                riga.setDisponibilita(entry.getValue());
                riga.setProdottoComposito("No");
                riga.setProdotti("//");

                List<RigaCatalogo> righeCercate= new ArrayList<>();
                righeCercate.add(riga);


                JFrame finestra=creaFinestra();
                finestra.setTitle("Riepilogo modifica articolo");
                finestra.add(new JLabel("Schieda articolo"),BorderLayout.NORTH);
                SchiedaArticoloTableModel tableModel = new SchiedaArticoloTableModel(righeCercate);
                JTable tabella = new JTable(tableModel);

                tabella.setRowHeight(100);



                JScrollPane scrollPane = new JScrollPane(tabella);
                finestra.add(scrollPane, BorderLayout.CENTER);

                finestra.setVisible(true);
                finestra.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JButton rifornisci= new JButton("Rifornisci prodotto");
                finestra.add(rifornisci,BorderLayout.SOUTH);
                rifornisci.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            String qS= JOptionPane.showInputDialog(null,"Inserisci la quantità che vuoi rifornire");
                            int q =Integer.parseInt(qS);
                            riga.setDisponibilita(q + riga.getDisponibilita());

                            int quantita = panel.getPuntoVendita().getProdottiVenduti().get(entry.getKey());
                            puntoVenditaDAO.removeProdottoVenduto(panel.getPuntoVendita(), entry.getKey());
                            puntoVenditaDAO.addProdottoVenduto(panel.getPuntoVendita(), entry.getKey(), riga.getDisponibilita());

                        }catch (Exception exception){
                            JOptionPane.showMessageDialog(null,"La quantità deve essere un numero intero");
                            return;
                        }
                        JOptionPane.showMessageDialog(null,"Prodotto rifornito con successo");
                    }
                });
                break;
            }
        }
        if(!trovato){
            JOptionPane.showMessageDialog(null,"L'ID inserito non esiste");
            return;
        }
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
