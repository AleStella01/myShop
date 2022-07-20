package DAO;

import DbInterface.*;
import Model.ArticoloAbstractFactory;
import Model.ArticoloFactoryProvider;
import Model.IArticolo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServizioDAO implements IArticoloDAO{
    private static ServizioDAO instance = new ServizioDAO();
    private IArticolo servizio;
    private static DbConnection connection;
    private static ResultSet rs;

    public ServizioDAO() {
        servizio = null;
        connection = null;
        rs = null;
    }

    public ServizioDAO getInstance() {
        return instance;
    }

    @Override
    public IArticolo findByName(String nome) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idservizio, nome, costo, descrizione FROM myShop.servizio WHERE nome = '" + nome + "';";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1){
                ArticoloFactoryProvider.ArticoloType type = ArticoloFactoryProvider.ArticoloType.SERVIZIO;
                ArticoloAbstractFactory factory = ArticoloFactoryProvider.getFactory(type);
                servizio = factory.crea();

                servizio.setNome(rs.getString("nome"));
                servizio.setPrezzo(rs.getFloat("costo"));
                servizio.setDescrizione(rs.getString("descrizione"));
                servizio.setId(rs.getInt("idprodotto"));

                return servizio;
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
        String sql = "SELECT idservizio, nome, costo, descrizione FROM myShop.prodotto";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<IArticolo> servizi = new ArrayList<>();
        try {
            while (rs.next()){
                ArticoloFactoryProvider.ArticoloType type = ArticoloFactoryProvider.ArticoloType.SERVIZIO;
                ArticoloAbstractFactory factory = ArticoloFactoryProvider.getFactory(type);
                servizio = factory.crea();

                servizio.setNome(rs.getString("nome"));
                servizio.setPrezzo(rs.getFloat("costo"));
                servizio.setDescrizione(rs.getString("descrizione"));
                servizio.setId(rs.getInt("idprodotto"));

                servizi.add(servizio);
            }
            return servizi;
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
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "INSERT INTO myShop.servizio (nome, costo, descrizione, categoria_idcategoria) VALUES ('" + articolo.getNome() + "', '" + articolo.getPrezzo() + "', '" + articolo.getDescrizione() + "', '" + articolo.getCategoria().getId() + "');";
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
    public int removeById(int id) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "DELETE FROM myShop.servizio WHERE idservizio = '" + id + "';";
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
    public int update(IArticolo articolo) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "UPDATE myShop.servizio SET nome = '" + articolo.getNome() + "', costo = '" + articolo.getPrezzo() + "', descrizione = '" + articolo.getDescrizione() + "', categoria_idcategoria = '" + articolo.getCategoria().getId() + "' WHERE idservizio = '" + articolo.getId() + "';";
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
    public IArticolo findById(int id) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idservizio, nome, costo, descrizione, categoria_idcategoria FROM myShop.servizio WHERE idservizio = '" + id + "';";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1){
                ArticoloFactoryProvider.ArticoloType type = ArticoloFactoryProvider.ArticoloType.SERVIZIO;
                ArticoloAbstractFactory factory = ArticoloFactoryProvider.getFactory(type);
                servizio = factory.crea();

                servizio.setId(id);
                servizio.setNome(rs.getString("nome"));
                servizio.setPrezzo(rs.getFloat("prezzo"));
                servizio.setDescrizione(rs.getString("descrizione"));

                return servizio;
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
