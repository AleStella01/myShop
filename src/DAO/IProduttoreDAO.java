package DAO;

import Model.IProduttore;

import java.util.ArrayList;

public interface IProduttoreDAO {
    IProduttore findByName(String name);
    ArrayList<IProduttore> findAll ();
    int add(IProduttore produttore);
    int removeByName(String name);
    int update(IProduttore produttore);
}
