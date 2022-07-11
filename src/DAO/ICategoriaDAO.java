package DAO;

import Model.ICategoria;

import java.util.ArrayList;

public interface ICategoriaDAO {
    ICategoria findByName(String nome);
    ArrayList<ICategoria> findAll();
    int add(ICategoria categoria);
    int removeById(int id);
    int update(ICategoria categoria);
    ICategoria findById(int id);
    ArrayList<ICategoria> findSottoCategorie(ICategoria categoria);
    ArrayList<ICategoria> findSuperCategorie(ICategoria categoria);
}
