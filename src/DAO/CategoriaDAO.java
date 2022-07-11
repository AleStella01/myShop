package DAO;

import DbInterface.IDbConnection;
import Model.ICategoria;

import java.sql.ResultSet;
import java.util.ArrayList;

public class CategoriaDAO implements ICategoriaDAO{
    private static CategoriaDAO instance = new CategoriaDAO();
    private ICategoria categoria;
    private static IDbConnection  connection;
    private static ResultSet rs;

    private CategoriaDAO(){
        categoria = null;
        connection = null;
        rs = null;
    }

    public static CategoriaDAO getInstance(){
        return instance;
    }


    @Override
    public ICategoria findByName(String nome) {
        return null;
    }

    @Override
    public ArrayList<ICategoria> findAll() {
        return null;
    }

    @Override
    public int add(ICategoria categoria) {
        return 0;
    }

    @Override
    public int removeById(int id) {
        return 0;
    }

    @Override
    public int update(ICategoria categoria) {
        return 0;
    }

    @Override
    public ICategoria findById(int id) {
        return null;
    }

    @Override
    public ArrayList<ICategoria> findSottoCategorie(ICategoria categoria) {
        return null;
    }

    @Override
    public ArrayList<ICategoria> findSuperCategorie(ICategoria categoria) {
        return null;
    }
}
