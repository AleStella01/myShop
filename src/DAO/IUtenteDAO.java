package DAO;

import Model.IUtente;

import java.util.ArrayList;

public interface IUtenteDAO {
    IUtente findById(String Username);
    ArrayList<IUtente> findAll();
    int add(IUtente utente);
    int removeById(String username);
    int update(IUtente utente);
}
