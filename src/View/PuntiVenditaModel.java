package View;

import Model.IPuntoVendita;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PuntiVenditaModel extends AbstractTableModel {

    private List<IPuntoVendita> puntiVendita;

    public PuntiVenditaModel(List<IPuntoVendita> puntiVendita) {
        this.puntiVendita = puntiVendita;
    }

    @Override
    public int getRowCount() {
        return puntiVendita.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        IPuntoVendita puntoVendita=puntiVendita.get(rowIndex);

        switch(columnIndex) {
            case 0: return puntoVendita.getId();
            case 1: return puntoVendita.getNome();
            case 2: return puntoVendita.getCitta();
            case 3: return puntoVendita.getIndirizzo();
            case 4: return puntoVendita.getManager().getNome() + " " + puntoVendita.getManager().getCognome();

        }
        return null;
    }

    @Override
    public String getColumnName(int columnIndex) {

        switch(columnIndex) {
            case 0: return "ID ";
            case 1: return "Nome";
            case 2: return "Città";
            case 3: return "Indirizzo";
            case 4: return "Nome Manager";

        }

        return null;
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return Object.class;

    }

}
