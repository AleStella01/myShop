package View;

import View.ViewModel.RigaCatalogo;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CatalogoTableModel extends AbstractTableModel {

   // private static CatalogoTableModel instance = new CatalogoTableModel();
    private List<RigaCatalogo> righe = new ArrayList<RigaCatalogo>();

    public List<RigaCatalogo> getRighe() {
        return righe;
    }

    public void setRighe(List<RigaCatalogo> righe) {
        this.righe = righe;
    }

    public CatalogoTableModel(List<RigaCatalogo> righe) {
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
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        RigaCatalogo riga = righe.get(rowIndex);

        switch(columnIndex) {
            case 0: return riga.getIdProdotto();
            case 1: return riga.getNomeProdotto();
            case 2: return riga.getNomeProduttore();
            case 3: return riga.getNomeCategoria();
            case 4: return riga.getPrezzo();
            case 5: return riga.getSelezionato();
            case 6: return riga.getDisponibilita();
            case 7:
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

        }

        return null;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        RigaCatalogo riga = righe.get(rowIndex);

        switch(columnIndex) {
            case 0: riga.setIdProdotto(Integer.parseInt(value.toString()));
            case 1: riga.setNomeProdotto(value.toString());
            case 2: riga.setNomeProduttore(value.toString());
            case 3: riga.setNomeCategoria(value.toString());
            case 4: riga.setPrezzo(Float.parseFloat(value.toString()));
            case 5: riga.setSelezionato(Boolean.parseBoolean(value.toString()));
            //case 6: riga.setDisponibilita(Integer.parseInt(value.toString()));

        }

        System.out.println('.');

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex >= 4;
    }

    @Override
    public String getColumnName(int columnIndex) {

        switch(columnIndex) {
            case 0: return "ID prodotto";
            case 1: return "Nome";
            case 2: return "Produttore";
            case 3: return "Categoria";
            case 4: return "Prezzo (€)";
            case 5: return "Seleziona";
            case 6: return "Numero prodotti disponibili";
            case 7: return "Immagine";

        }

        return null;
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        if(columnIndex == 5) return Boolean.class;
        if(columnIndex == 7) return ImageIcon.class;
        return Object.class;

    }
}
