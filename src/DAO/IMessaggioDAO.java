package DAO;

import Model.IUtente;
import Model.Notifica;

import java.util.ArrayList;

public interface IMessaggioDAO {
    Notifica findById(int id);
    ArrayList<Notifica> findAll();
    ArrayList<Notifica> findByCliente(IUtente utente);
    ArrayList<Notifica> findByManager(IUtente utente);
    int add(Notifica notifica);
    int removeById(int id);
    int update(Notifica notifica);
}
