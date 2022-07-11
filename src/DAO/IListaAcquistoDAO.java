package DAO;

import Model.IUtente;
import Model.ListaAcquisto;

import java.util.ArrayList;

public interface IListaAcquistoDAO {
    ArrayList<ListaAcquisto> findByUser(IUtente utente);
    ArrayList<ListaAcquisto> findAll();
    ListaAcquisto findById(int id);
    int add(ListaAcquisto listaAcquisto);
    int removeById(int id);
    int update(ListaAcquisto listaAcquisto);
}
