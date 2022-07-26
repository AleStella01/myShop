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
    int addSubCategoria(ICategoria categoria, ICategoria subcategoria);
    int addSuperCategoria(ICategoria categoria, ICategoria superCategoria);
    int removeSubCategoria(ICategoria categoria, ICategoria subcategoria);
    int removeSuperCategoria(ICategoria categoria, ICategoria superCategoria);
    ArrayList<ICategoria> getSubCategorie(ICategoria categoria);
}
