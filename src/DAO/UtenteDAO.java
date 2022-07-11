package DAO;

import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import DbInterface.command.DbOperationExecutor;
import DbInterface.command.IDbOperation;
import DbInterface.command.ReadOperation;
import Model.Amministratore;
import Model.Cliente;
import Model.Manager;
import Model.Utente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UtenteDAO implements IUtenteDAO {
    private static UtenteDAO instance = new UtenteDAO();
    private Utente utente;
    private static IDbConnection conn;
    private static ResultSet rs;

    private UtenteDAO() {
        utente = null;
        conn = null;
        rs = null;
    }

    public static UtenteDAO getInstance() {
        return instance;
    }

    @Override
    public Utente findById(String username) {

        //senza design pattern command
        //conn = DbConnection.getInstance();
        //rs = conn.executeQuery("SELECT Nome, Cognome, Username, email FROM Utente WHERE Username = '" + username + "';");

        //con metà design pattern command
        //String sql = "SELECT Nome, Cognome, Username, email FROM Utente WHERE Username = '" + username + "';";
        //IDbOperation readOp = new ReadOperation(sql);
        //rs = readOp.execute();

        //design pattern command completo
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT Nome, Cognome, Username, email FROM Utente WHERE Username = '" + username + "';";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow()==1) {
                utente = new Utente();
                utente.setName(rs.getString("Nome"));
                utente.setSurname(rs.getString("Cognome"));
                utente.setUsername(rs.getString("Username"));
                utente.setEmail(rs.getString("email"));
                return utente;
            }
        } catch (SQLException e) {
            // handle any errors
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // handle any errors
            System.out.println("Resultset: " + e.getMessage());
        } finally {
            conn.close();
        }
        return null;
    }

    @Override
    public ArrayList<Utente> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT Nome, Cognome, Username, email FROM Utente;");
        ArrayList<Utente> utenti = new ArrayList<>();
        try {
            while (rs.next()) {
                utente = new Utente();
                utente.setName(rs.getString("Nome"));
                utente.setSurname(rs.getString("Cognome"));
                utente.setUsername(rs.getString("Username"));
                utente.setEmail(rs.getString("email"));
                utenti.add(utente);
            }
            return utenti;
        } catch (SQLException e) {
            // Gestisce le differenti categorie d'errore
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // Gestisce le differenti categorie d'errore
            System.out.println("Resultset: " + e.getMessage());
        } finally {
            conn.close();
        }
        return null;
    }

    @Override
    public int add(Utente utente) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO Utente (Nome, Cognome, Username, Pwd, email, Tipo) VALUES ('"+ utente.getName() + "','" + utente.getSurname() + "','" + utente.getUsername() + "','" + utente.getPwd() + "','" + utente.getEmail()  + "');");
        conn.close();
        return rowCount;
    }

    @Override
    public int removeById(String username) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM Utente WHERE username = '"+ username + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public int update(Utente utente) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE Utente SET Nome = '" + utente.getName() + "', Cognome = '" + utente.getSurname() + "', Email = '" + utente.getEmail() + "' WHERE username = '" + utente.getUsername() + "';");
        conn.close();
        return rowCount;
    }

    public boolean userExists(String username) {

        String sql = "SELECT count(*) AS count from myshop1.utente as U where U.username='"+username+"';";

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

        String sql = "SELECT count(*) AS count FROM myshop1.utente AS U where U.username='"+username+"' AND U.password='"+password+"';";

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

    public boolean isCliente(String username) {

        String sql = "SELECT count(*) AS count FROM myshop1.utente AS U INNER JOIN myshop1.cliente AS C ON U.idutente = C.utente_idutente WHERE U.username='"+username+"';";

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

    public boolean isManager(String username) {

        String sql = "SELECT count(*) AS count FROM myshop1.utente AS U INNER JOIN myshop1.manager AS M ON U.idutente = M.utente_idutente WHERE U.username='"+username+"';";

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

    public boolean isAmministratore(String username) {

        String sql = "SELECT count(*) AS count FROM myshop1.utente AS U INNER JOIN myshop1.amministratore AS A ON U.idutente = A.utente_idutente WHERE U.username='"+username+"';";

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

    public Cliente caricaCliente(String username) {

        Cliente c = new Cliente();

        String sql = "SELECT U.idUtente, U.nome, U.cognome, U.email, U.username, C.canale_preferito FROM myshop1.utente AS U INNER JOIN myshop1.cliente AS C ON U.idutente = C.utente_idutente WHERE U.username = '"+username+"';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1) {
                c.setId(rs.getInt("idUtente"));
                c.setName(rs.getString("nome"));
                c.setSurname(rs.getString("cognome"));
                c.setEmail(rs.getString("email"));
                c.setUsername(rs.getString("username"));
                //c.setCanalePreferito();
                return c;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    public Manager caricaManager(String username) {

        Manager m = new Manager();

        String sql = "SELECT U.idUtente, U.nome, U.cognome, U.email, U.username FROM myshop1.utente AS U  WHERE U.username = '"+username+"';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1) {
                m.setId(rs.getInt("idUtente"));
                m.setName(rs.getString("nome"));
                m.setSurname(rs.getString("cognome"));
                m.setEmail(rs.getString("email"));
                m.setUsername(rs.getString("username"));
                return m;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    public Amministratore caricaAmministratore(String username) {

        Amministratore a= new Amministratore();

        String sql = "SELECT U.idUtente, U.nome, U.cognome, U.email, U.username FROM myshop1.utente AS U  WHERE U.username = '"+username+"';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1) {
                a.setId(rs.getInt("idUtente"));
                a.setName(rs.getString("nome"));
                a.setSurname(rs.getString("cognome"));
                a.setEmail(rs.getString("email"));
                a.setUsername(rs.getString("username"));
                return a;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }


}