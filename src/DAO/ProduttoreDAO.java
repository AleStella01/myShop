package DAO;

import DbInterface.*;
import Model.IProduttore;
import Model.ProduttoreAbstractFactory;
import Model.ProduttoreFactoryProvider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProduttoreDAO implements IProduttoreDAO{
    private static ProduttoreDAO instance = new ProduttoreDAO();
    private IProduttore produttore;
    private static IDbConnection connection;
    private static ResultSet rs;

    private ProduttoreDAO(){
        produttore = null;
        connection = null;
        rs = null;
    }

    public static ProduttoreDAO getInstance(){
        return instance;
    }

    @Override
    public IProduttore findByName(String name) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idproduttore, nome, sitoWeb, nazione, citta FROM myShop.produttore WHERE nome = '" + name + "';";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1){
                ProduttoreFactoryProvider.ProduttoreType type = ProduttoreFactoryProvider.ProduttoreType.PRODUTTORE_MOBILI;
                ProduttoreAbstractFactory factory = ProduttoreFactoryProvider.getFactory(type);
                produttore = factory.crea();

                produttore.setId(rs.getInt("idproduttore"));
                produttore.setNomeProduttore(rs.getString("nome"));
                produttore.setSitoWeb(rs.getString("sitoWeb"));
                produttore.setNazione(rs.getString("nazione"));
                produttore.setCitta(rs.getString("citta"));

                return produttore;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return null;
    }

    @Override
    public ArrayList<IProduttore> findAll() {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idproduttore, nome, sitoWeb, nazione, citta FROM myShop.produttore";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<IProduttore> produttori = new ArrayList<>();
        try {
            while (rs.next()){
                ProduttoreFactoryProvider.ProduttoreType type = ProduttoreFactoryProvider.ProduttoreType.PRODUTTORE_MOBILI;
                ProduttoreAbstractFactory factory = ProduttoreFactoryProvider.getFactory(type);
                produttore = factory.crea();

                produttore.setId(rs.getInt("idproduttore"));
                produttore.setNomeProduttore(rs.getString("nome"));
                produttore.setSitoWeb(rs.getString("sitoWeb"));
                produttore.setNazione(rs.getString("nazione"));
                produttore.setCitta(rs.getString("citta"));

                produttori.add(produttore);
            }
            return produttori;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int add(IProduttore produttore) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "INSERT INTO myShop.produttore (idproduttore, nome, sitoWeb, nazione, citta) VALUES ('" + produttore.getId() + "','" + produttore.getNomeProduttore() + "','" + produttore.getSitoWeb() + "','" + produttore.getNazione() + "','" + produttore.getCitta() + "');";
        IDbOperation writeOp = new WriteOperation(sql);
        rs = executor.executeOperation(writeOp).getResultSet();

        int rowCount = 0;
        try {
            rowCount = rs.getRow();
            return rowCount;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }

        return 0;
    }

    @Override
    public int removeByName(String name) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "DELETE FROM myShop.produttore WHERE nome = '" + name + "';";
        IDbOperation writeOp = new WriteOperation(sql);
        rs = executor.executeOperation(writeOp).getResultSet();
        try {
            int rowCount = rs.getRow();
            return rowCount;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }

        return 0;
    }

    @Override
    public int update(IProduttore produttore) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "UPDATE myShop.produttore SET nome = '" + produttore.getNomeProduttore() + "', sitoWeb = '" + produttore.getSitoWeb() + "', nazione = '" + produttore.getNazione() + "', citta = '" + produttore.getCitta() + "' WHERE idproduttore = '" + produttore.getId() + "';";
        IDbOperation writeOp = new WriteOperation(sql);
        rs = executor.executeOperation(writeOp).getResultSet();
        try {
            int rowCount = rs.getRow();
            return rowCount;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }

        return 0;
    }
}
