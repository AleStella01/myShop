package DAO;

import DbInterface.*;
import Model.Collocazione;
import Model.CollocazioneFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CollocazioneDAO implements ICollocazioneDAO{
    private static CollocazioneDAO instance = new CollocazioneDAO();
    private Collocazione collocazione;
    private static DbConnection connection;
    private static ResultSet rs;

    public CollocazioneDAO() {
        collocazione = null;
        connection = null;
        rs = null;
    }

    public static CollocazioneDAO getInstance(){
        return instance;
    }

    @Override
    public Collocazione findById(int id) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idcollocazione, corsia, numeroScaffale FROM myShop.collocazione WHERE idcollocazione = '" + id + "';";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1) {
                CollocazioneFactory.CollocazioneType type = CollocazioneFactory.CollocazioneType.MAGAZZINO;
                CollocazioneFactory factory = new CollocazioneFactory();
                collocazione = factory.getCollocazione(type);

                collocazione.setId(rs.getInt("idcollocazione"));
                collocazione.setCorsia(rs.getString("corsia"));
                collocazione.setScaffale(rs.getInt("numeroScaffale"));
            }
            return collocazione;
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
    public ArrayList<Collocazione> findAll() {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idcollocazione, corsia, numeroScaffale FROM myShop.collocazione";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<Collocazione> collocazioni = new ArrayList<>();
        try {
            while (rs.next()){
                CollocazioneFactory.CollocazioneType type = CollocazioneFactory.CollocazioneType.MAGAZZINO;
                CollocazioneFactory factory = new CollocazioneFactory();
                collocazione = factory.getCollocazione(type);

                collocazione.setId(rs.getInt("idcollocazione"));
                collocazione.setCorsia(rs.getString("corsia"));
                collocazione.setScaffale(rs.getInt("numeroScaffale"));

                collocazioni.add(collocazione);
            }
            return collocazioni;
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
    public int add(Collocazione collocazione) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "INSERT INTO myShop.collocazione (corsia, numeroScaffale) VALUES ('"+ collocazione.getCorsia() +"', " + collocazione.getScaffale() +"';)";
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
        String sql = "REMOVE FROM myShop.collocazione WHERE idcollocazione = '" + id + "';";
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
    public int update(Collocazione collocazione) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "UPDATE myShop.collocazione SET corsia = '" + collocazione.getCorsia() + "', numeroScaffale = '" + collocazione.getScaffale() + "' WHERE idcollocazione = '" + collocazione.getId() + ";";
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
    public Collocazione findByCorsiaEScaffale(String corsia, int scaffale) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idcollocazione, corsia, numeroScaffale FROM myShop.collocazione WHERE corsia = '" + corsia + "' AND numeroScaffale = '" + scaffale + "';";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if(rs.getRow() == 1) {
                CollocazioneFactory.CollocazioneType type = CollocazioneFactory.CollocazioneType.MAGAZZINO;
                CollocazioneFactory factory = new CollocazioneFactory();
                collocazione = factory.getCollocazione(type);

                collocazione.setId(rs.getInt("idcollocazione"));
                collocazione.setCorsia(rs.getString("corsia"));
                collocazione.setScaffale(rs.getInt("numeroScaffale"));
            }
            return collocazione;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }
}
