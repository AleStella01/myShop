package View;

import View.ViewModel.RigaCatalogo;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CarrelloTableModel extends AbstractTableModel {

   // private static CatalogoTableModel instance = new CatalogoTableModel();
    private List<RigaCatalogo> righe = new ArrayList<RigaCatalogo>();

    public List<RigaCatalogo> getRighe() {
        return righe;
    }

    public void setRighe(List<RigaCatalogo> righe) {
        this.righe = righe;
    }

    public CarrelloTableModel(List<RigaCatalogo> righe) {
        this.righe = righe;
    }


/*
    private CatalogoTableModel(){

    }

    public static CatalogoTableModel getInstance(){
        return instance;
    }



 */

    @Override
    public int getRowCount() {
        return righe.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        RigaCatalogo riga = righe.get(rowIndex);
        JButton button=new JButton("Rimuovi prodotto");

        switch(columnIndex) {
            case 0: return riga.getIdProdotto();
            case 1: return riga.getNomeProdotto();
            case 2: return riga.getNomeCategoria();
            case 3: return riga.getPrezzo();
            case 4:
                //URL url = getClass().getResource("/download.jpeg");
                InputStream stream = getClass().getResourceAsStream("/download.jpeg");
                try {
                    ImageIcon icon = new ImageIcon(ImageIO.read(stream));
                    return icon;
                } catch (IOException e) {
                    //throw new RuntimeException(e);
                    e.printStackTrace();
                    return new ImageIcon();
                }
            case 5: return button;

        }

        return null;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        RigaCatalogo riga = righe.get(rowIndex);
        JButton button=new JButton("Rimuovi prodotto");

        switch(columnIndex) {
            case 0: riga.setIdProdotto(Integer.parseInt(value.toString()));
            case 1: riga.setNomeProdotto(value.toString());
            case 2: riga.setNomeCategoria(value.toString());
            case 3: riga.setPrezzo(Float.parseFloat(value.toString()));
            case 4:
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JOptionPane.showMessageDialog(null,"Pippo");
                        }
                    });




        }

        System.out.println('.');

    }


    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex >= 5;
    }


    @Override
    public String getColumnName(int columnIndex) {

        switch(columnIndex) {
            case 0: return "ID prodotto";
            case 1: return "Nome";
            case 2: return "Categoria";
            case 3: return "Prezzo (€)";
            case 4: return "Immagine";
            case 5: return "Rimuovi prodotto";

        }

        return null;
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        if(columnIndex == 4) return ImageIcon.class;
        if(columnIndex == 5) return JButton.class;
        return Object.class;

    }
}
