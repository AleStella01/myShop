package DAO;

import Model.IArticolo;
import Model.IProdotto;
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
    int addProdotto(ListaAcquisto listaAcquisto, IArticolo prodotto, int quantita);
    int addServizio(ListaAcquisto listaAcquisto, IArticolo servizio, String spedizione);
    int removeProdotto(ListaAcquisto listaAcquisto, IArticolo prodotto);
    int removeServizio(ListaAcquisto listaAcquisto, IArticolo servizio);
}
