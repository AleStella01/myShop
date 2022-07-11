package DAO;

import Model.IArticolo;

import java.util.ArrayList;

public interface IArticoloDAO {
    IArticolo findByName(String nome);
    ArrayList<IArticolo> findAll();
    int add(IArticolo articolo);
    int removeById(int id);
    int update(IArticolo articolo);
    public IArticolo findById(int id);
}
