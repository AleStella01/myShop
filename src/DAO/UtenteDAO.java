package DAO;

import DbInterface.*;
import Model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UtenteDAO implements IUtenteDAO{
    private static UtenteDAO instance = new UtenteDAO();
    private IUtente utente;
    //private static IDbConnection connection;
    private static ResultSet rs;

    private UtenteDAO(){
        utente = null;
        //connection = null;
        rs = null;
    }

    public static UtenteDAO getInstance(){
        return instance;
    }

    @Override
    public IUtente findById(String username) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idutente, nome, cognome, codiceFiscale, dataNascita, citta, indirizzo, username, password, email, tipo FROM myShop.utente WHERE username = '" + username + "';";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1) {
                UtenteFactoryProvider.UserType type = selectorType(rs.getString("tipo"));
                UtenteAbstractFactory factory = UtenteFactoryProvider.getFactory(type);
                utente = factory.creaUtente();

                utente.setId(rs.getInt("idutente"));
                utente.setNome(rs.getString("nome"));
                utente.setCognome(rs.getString("cognome"));
                utente.setCodiceFiscale(rs.getString("codiceFiscale"));
                utente.setDataNascita(rs.getString("dataNascita"));
                utente.setCitta(rs.getString("citta"));
                utente.setIndirizzo(rs.getString("indirizzo"));
                utente.setEmail(rs.getString("email"));
                utente.setUsername(rs.getString("username"));
                utente.setPassword(rs.getString("password"));
                return utente;
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            System.out.println("ResultSet: " + e.getMessage());
        } finally {
            //connection.close();
        }

        return null;
    }
    public IUtente findById(int id) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idutente, nome, cognome, codiceFiscale, dataNascita, citta, indirizzo, username, password, email, tipo FROM myshop.utente WHERE idutente = " + id + ";";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1) {
//                UtenteFactoryProvider.UserType type = (UtenteFactoryProvider.UserType) rs.getObject("tipo");
                UtenteFactoryProvider.UserType type = selectorType(rs.getString("tipo"));
                UtenteAbstractFactory factory = UtenteFactoryProvider.getFactory(type);
                utente = factory.creaUtente();

                utente.setId(rs.getInt("idutente"));
                utente.setNome(rs.getString("nome"));
                utente.setCognome(rs.getString("cognome"));
                utente.setCodiceFiscale(rs.getString("codiceFiscale"));
                utente.setDataNascita(rs.getString("dataNascita"));
                utente.setCitta(rs.getString("citta"));
                utente.setIndirizzo(rs.getString("indirizzo"));
                utente.setEmail(rs.getString("email"));
                utente.setUsername(rs.getString("username"));
                utente.setPassword(rs.getString("password"));
                return utente;
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            System.out.println("ResultSet: " + e.getMessage());
        } finally {
            //connection.close();
        }

        return null;
    }

    @Override
    public ArrayList<IUtente> findAll() {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idutente, nome, cognome, codiceFiscale, dataNascita, citta, indirizzo, username, email, tipo FROM myShop.utente;";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<IUtente> utenti = new ArrayList<>();
        try{
            while (rs.next()){
                UtenteFactoryProvider.UserType type = selectorType(rs.getString("tipo"));
                UtenteAbstractFactory factory = UtenteFactoryProvider.getFactory(type);
                utente = factory.creaUtente();

                utente.setId(rs.getInt("idutente"));
                utente.setNome(rs.getString("nome"));
                utente.setCognome(rs.getString("cognome"));
                utente.setCodiceFiscale(rs.getString("codiceFiscale"));
                utente.setUsername(rs.getString("username"));
                utente.setEmail(rs.getString("email"));
                utente.setDataNascita(rs.getString("dataNascita"));
                utente.setCitta(rs.getString("citta"));
                utente.setIndirizzo(rs.getString("indirizzo"));

                utenti.add(utente);
            }
            return utenti;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e){
            System.out.println("Resultset: " + e.getMessage());
        } finally {
            //connection.close();
        }

        return null;
    }

    @Override
    public int add(IUtente utente) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "INSERT INTO `myshop`.`utente` (`nome`, `cognome`, `codiceFiscale`, `dataNascita`, `citta`, `indirizzo`, `username`, `password`, `email`, `tipo`) VALUES ('" + utente.getNome() + "','" + utente.getCognome() + "','" + utente.getCodiceFiscale() + "', '" + utente.getDataNascita() + "', '" + utente.getCitta() + "','" + utente.getIndirizzo() + "','" + utente.getUsername() + "','" + utente.getPassword() + "','" + utente.getEmail() + "','" + utente.getType() + "');";
        IDbOperation writeOp = new WriteOperation(sql);
        DbOperationResult result = executor.executeOperation(writeOp);
        try {
            if (result.getRowsAffected() == 0)
                throw new SQLException("SQL Error!");
            else
                return result.getRowsAffected();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e){
            System.out.println("Resultset: " + e.getMessage());
        } finally {
            //connection.close();
        }

        return 0;
    }

    @Override
    public int removeById(String username) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "DELETE FROM myShop.utente WHERE username = '" + username + "';";
        IDbOperation writeOp = new WriteOperation(sql);
        rs = executor.executeOperation(writeOp).getResultSet();
        try{
            int rowCount = rs.getRow();
            return rowCount;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            System.out.println("Resultset: " + e.getMessage());
        } finally {
            //connection.close();
        }

        return 0;
    }

    @Override
    public int update(IUtente utente) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "UPDATE myShop.utente SET nome = '" + utente.getNome() + "', cognome = '" + utente.getCognome() + "', email = '" + utente.getEmail() + "', codiceFiscale = '" + utente.getCodiceFiscale() + "', dataNascita = '" + utente.getDataNascita() + "', citta = '" + utente.getCitta() + "', indirizzo = '" + utente.getIndirizzo() + "' WHERE username = '" + utente.getUsername() + "';";
        IDbOperation writeOp = new WriteOperation(sql);
        rs = executor.executeOperation(writeOp).getResultSet();
        try {
            int rowCount = rs.getRow();
            return rowCount;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            System.out.println("Resultset: " + e.getMessage());
        } finally {
            //connection.close();
        }

        return 0;
    }

    public int resetPassword(IUtente utente, String password) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "UPDATE myShop.utente SET password = '" + password + "' WHERE username = '" + utente.getUsername() + "';";
        IDbOperation writeOp = new WriteOperation(sql);
        rs = executor.executeOperation(writeOp).getResultSet();
        try {
            int rowCount = rs.getRow();
            return rowCount;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            System.out.println("Resultset: " + e.getMessage());
        } finally {
            //connection.close();
        }

        return 0;
    }

    public UtenteFactoryProvider.UserType selectorType(String type) {
        switch (type.toUpperCase()){
            case "MANAGER":
                return UtenteFactoryProvider.UserType.MANAGER;
            case "ADMIN":
                return UtenteFactoryProvider.UserType.ADMIN;
            default:
                return UtenteFactoryProvider.UserType.CLIENTE;

        }
    }

    public boolean userExists(String username){
        String sql = "SELECT count(*) AS count from myshop.utente as U where U.username= '" + username.toLowerCase() + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1) {
                int count = rs.getInt("count");
                return count == 1;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkCredentials(String username, String password) {

        String sql = "SELECT count(*) AS count FROM myshop.utente AS U where U.username= '" + username + "' AND U.password= '" + password + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1) {
                int count = rs.getInt("count");
                return count == 1;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
