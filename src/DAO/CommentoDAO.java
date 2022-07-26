package DAO;

import DbInterface.*;
import Model.Commento;
import Model.CommentoFactory;
import Model.IArticolo;
import Model.IUtente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommentoDAO implements ICommentoDAO{
    private static CommentoDAO instance = new CommentoDAO();
    private Commento commento;
    //private static IDbConnection connection;
    private static ResultSet rs;

    public CommentoDAO() {
        commento = null;
        //connection = null;
        rs = null;
    }

    public static CommentoDAO getInstance() {
        return instance;
    }

    @Override
    public ArrayList<Commento> findByUser(IUtente utente) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idcommento, NumeroStelle, testo, risposto, testoRisposta, utente_idutente, listaAcquisto_idlistaAcquisto FROM myShop.commento WHERE utente_idutente = " + utente.getId() +";";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<Commento> commenti = new ArrayList<>();
        try {
            while (rs.next()){
                CommentoFactory.CommentoType type = CommentoFactory.CommentoType.PRODOTTO;
                CommentoFactory factory = new CommentoFactory();
                commento = factory.getCommentoFactory(type);

                commento.setId(rs.getInt("idcommento"));
                commento.setNumeroStelle(rs.getInt("NumeroStelle"));
                commento.setTesto(rs.getString("testo"));
                commento.setRisposto(rs.getBoolean("risposto"));
                commento.setTestoRisposta(rs.getString("testoRisposta"));
                commento.setCliente(UtenteDAO.getInstance().findById(rs.getInt("utente_idutente")));
                commento.setListaAcquisto(ListaAcquistoDAO.getInstance().findById(rs.getInt("listaAcquisto_idlistaAcquisto")));

                commenti.add(commento);
            }
            return commenti;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public ArrayList<Commento> findAll() {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idcommento, NumeroStelle, testo, risposto, testoRisposta, utente_idutente, listaAcquisto_idlistaAcquisto FROM myShop.commento;";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<Commento> commenti = new ArrayList<>();
        try {
            while (rs.next()){
                CommentoFactory.CommentoType type = CommentoFactory.CommentoType.PRODOTTO;
                CommentoFactory factory = new CommentoFactory();
                commento = factory.getCommentoFactory(type);

                commento.setId(rs.getInt("idcommento"));
                commento.setNumeroStelle(rs.getInt("NumeroStelle"));
                commento.setTesto(rs.getString("testo"));
                commento.setRisposto(rs.getBoolean("risposto"));
                commento.setTestoRisposta(rs.getString("testoRisposta"));
                commento.setCliente(UtenteDAO.getInstance().findById(rs.getInt("utente_idutente")));
                commento.setListaAcquisto(ListaAcquistoDAO.getInstance().findById(rs.getInt("listaAcquisto_idlistaAcquisto")));

                commenti.add(commento);
            }
            return commenti;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public int add(Commento commento) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "INSERT INTO myShop.commento (NumeroStelle, testo, risposto, testoRisposta, utente_idutente, listaAcquisto_idlistaAcquisto) VALUES (" + commento.getNumeroStelle() + ", '" + commento.getTesto() + "', " + commento.isRisposto() + ", '" + commento.getTestoRisposta() + "', " + commento.getCliente().getId() + ", " + commento.getListaAcquisto().getId() + ");";
        IDbOperation writeOp = new WriteOperation(sql);
        rs = executor.executeOperation(writeOp).getResultSet();
        int rowCount = 0;
        try {
            rowCount = rs.getRow();
            return rowCount;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e){
            System.out.println("Resultset: " + e.getMessage());
        }
        return 0;
    }

    @Override
    public int removeById(int id) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "DELETE FROM myShop.commento WHERE idcommento = " + id + ";";
        IDbOperation writeOp = new WriteOperation(sql);
        rs = executor.executeOperation(writeOp).getResultSet();
        int rowCount = 0;
        try {
            rowCount = rs.getRow();
            return rowCount;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e){
            System.out.println("Resultset: " + e.getMessage());
        }
        return 0;
    }

    @Override
    public int update(Commento commento) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "UPDATE myShop.commento SET NumeroStelle = " + commento.getNumeroStelle() + ", testo = '" + commento.getTesto() + "', risposto = " + commento.isRisposto() + ", testoRisposta = '" + commento.getTestoRisposta() + "', utente_idutente = " + commento.getCliente().getId() + ", listaAcquisto_idlistaAcquisto = " + commento.getListaAcquisto().getId() + " WHERE idcommento = " + commento.getId() + ";";
        IDbOperation writeOp = new WriteOperation(sql);
        rs = executor.executeOperation(writeOp).getResultSet();
        int rowCount = 0;
        try {
            rowCount = rs.getRow();
            return rowCount;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e){
            System.out.println("Resultset: " + e.getMessage());
        }
        return 0;
    }
}
