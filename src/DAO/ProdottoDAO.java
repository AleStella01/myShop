package DAO;

import DbInterface.DbOperationExecutor;
import DbInterface.IDbConnection;
import DbInterface.IDbOperation;
import DbInterface.ReadOperation;
import Model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdottoDAO implements IArticoloDAO{
    private static ProdottoDAO instance = new ProdottoDAO();
    private IArticolo prodotto;
    private static IDbConnection connection;
    private static ResultSet rs;

    public ProdottoDAO() {
        prodotto = null;
        connection = null;
        rs = null;
    }

    public ProdottoDAO getInstance() {
        return instance;
    }

    @Override
    public IArticolo findByName(String nome) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idprodotto, nome, costo, descrizione FROM myShop.prodotto WHERE nome = '" + nome + "';";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1){
                ArticoloFactoryProvider.ArticoloType type = ArticoloFactoryProvider.ArticoloType.PRODOTTO;
                ArticoloAbstractFactory factory = ArticoloFactoryProvider.getFactory(type);
                prodotto = factory.crea();

                prodotto.setNome(rs.getString("nome"));
                prodotto.setPrezzo(rs.getFloat("costo"));
                prodotto.setDescrizione(rs.getString("descrizione"));
                prodotto.setId(rs.getInt("idprodotto"));

                return prodotto;
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            System.out.println("ResultSet: " + e.getMessage());
        } finally {
            connection.close();
        }

        return null;
    }

    @Override
    public ArrayList<IArticolo> findAll() {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idprodotto, nome, costo, descrizione FROM myShop.prodotto";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<IArticolo> prodotti = new ArrayList<>();
        try {
            while (rs.next()){
                ArticoloFactoryProvider.ArticoloType type = ArticoloFactoryProvider.ArticoloType.PRODOTTO;
                ArticoloAbstractFactory factory = ArticoloFactoryProvider.getFactory(type);
                prodotto = factory.crea();

                prodotto.setNome(rs.getString("nome"));
                prodotto.setPrezzo(rs.getFloat("costo"));
                prodotto.setDescrizione(rs.getString("descrizione"));
                prodotto.setId(rs.getInt("idprodotto"));

                prodotti.add(prodotto);
            }
            return prodotti;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            System.out.println("Resultset: " + e.getMessage());
        } finally {
            connection.close();
        }

        return null;
    }

    @Override
    public int add(IArticolo articolo) {
        return 0;
    }

    @Override
    public int removeById(int id) {
        return 0;
    }

    @Override
    public int update(IArticolo articolo) {
        return 0;
    }

    @Override
    public IArticolo findById(int id){
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idprodotto, nome, costo, descrizione FROM myShop.prodotto WHERE idprodotto = '" + id + "';";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1){
                ArticoloFactoryProvider.ArticoloType type = ArticoloFactoryProvider.ArticoloType.PRODOTTO;
                ArticoloAbstractFactory factory = ArticoloFactoryProvider.getFactory(type);
                prodotto = factory.crea();

                prodotto.setNome(rs.getString("nome"));
                prodotto.setPrezzo(rs.getFloat("costo"));
                prodotto.setDescrizione(rs.getString("descrizione"));
                prodotto.setId(rs.getInt("idprodotto"));

                return prodotto;
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            System.out.println("ResultSet: " + e.getMessage());
        } finally {
            connection.close();
        }

        return null;
    }
}
