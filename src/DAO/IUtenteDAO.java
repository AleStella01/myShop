package DAO;

import Model.Utente;

import java.util.ArrayList;

public interface IUtenteDAO {
    Utente findById(String email);
    ArrayList<Utente> findAll();
    int add(Utente utente);
    int removeById(String email);
    int update(Utente utente);
}