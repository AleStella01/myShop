package DAO;

import Model.Commento;
import Model.IArticolo;
import Model.IUtente;

import java.util.ArrayList;

public interface ICommentoDAO {
    ArrayList<Commento> findByUser(IUtente utente);
    ArrayList<Commento> findByProduct(IArticolo articolo);
    ArrayList<Commento> findForProcuctRatio(IArticolo articolo);
    ArrayList<Commento> findByRatio(IArticolo articolo, int valutazione);
    ArrayList<Commento> findAll();
    int add(Commento commento);
    int removeById(int id);
    int update(Commento commento);
}
