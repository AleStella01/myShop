package View.Listener;

import View.CatalogoPanel;
import View.ViewModel.RigaCatalogo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ListIterator;

public class RifornireProdottoListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        int id=0;
        try {
            String idS= JOptionPane.showInputDialog(null,"Inserisci l'ID del prodotto che vuoi rifornire");
            id=Integer.parseInt(idS);
        }catch (Exception exception){
            JOptionPane.showMessageDialog(null,"L'ID deve essere un numero intero");
        }
        CatalogoPanel panel=CatalogoPanel.getInstance();
        List<RigaCatalogo> righe=panel.getRighe();
        ListIterator<RigaCatalogo> iterator = righe.listIterator();
        boolean trovato=false;

        while (iterator.hasNext()){
            RigaCatalogo riga=iterator.next();
            if(id == riga.getIdProdotto() ){
                try {
                    String qS= JOptionPane.showInputDialog(null,"Inserisci la quantità che vuoi rifornire");
                    int q =Integer.parseInt(qS);
                    riga.setDisponibilita(q+riga.getDisponibilita());
                }catch (Exception exception){
                    JOptionPane.showMessageDialog(null,"La quantità deve essere un numero intero");
                }
                JOptionPane.showMessageDialog(null,"Prodotto rifornito con successo");
                trovato=true;
            }else {
                trovato=false;
            }
        }
        if(!trovato){
            JOptionPane.showMessageDialog(null,"L'ID inserito non esiste");
        }
    }
}
