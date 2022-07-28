package View;

import View.ViewModel.RigaCatalogo;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class SchiedaArticoloTableModel extends AbstractTableModel {

    private List<RigaCatalogo> righe = new ArrayList<RigaCatalogo>();


    public SchiedaArticoloTableModel(List<RigaCatalogo> righe) {
        this.righe = righe;
    }



    @Override
    public int getRowCount() {
        return righe.size();
    }

    @Override
    public int getColumnCount() {
        return 10;
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
            case 5: return riga.getProdottoComposito();
            case 6: return riga.getProdotti();
            case 7: return riga.getCorsia();
            case 8: return riga.getScaffale();
            case 9: return riga.getImage();

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
            case 5: riga.setProdottoComposito(value.toString());
            case 6: riga.setProdotti(value.toString());
            case 7: riga.setCorsia(value.toString());
            case 8: riga.setScaffale(Integer.parseInt(value.toString()));
        }

        //System.out.println('.');

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex >= 0;
    }



    @Override
    public String getColumnName(int columnIndex) {

        switch(columnIndex) {
            case 0: return "ID prodotto";
            case 1: return "Nome";
            case 2: return "Produttore";
            case 3: return "Categoria";
            case 4: return "Prezzo (€)";
            case 5: return "Prodotto Composito";
            case 6: return "Prodotti";
            case 7: return "Corsia";
            case 8: return "Scaffale";
            case 9: return "Immagine";
        }

        return null;
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        if(columnIndex == 9) return ImageIcon.class;
        return Object.class;

    }










}
