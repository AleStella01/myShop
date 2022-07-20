package DAO;

import DbInterface.*;
import Model.IUtente;
import Model.Notifica;
import Model.NotificationFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MessaggioDAO implements IMessaggioDAO{
    private static MessaggioDAO instance = new MessaggioDAO();
    private Notifica notifica;
    private static IDbConnection connection;
    private static ResultSet rs;

    private MessaggioDAO() {
        notifica = null;
        connection = null;
        rs = null;
    }

    public  MessaggioDAO getInstance(){
        return instance;
    }

    @Override
    public Notifica findById(int id) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idmessaggio, testo, cliente_idcliente, manager_idmanager FROM myShop.messaggio WHERE idmessaggio = '" + id + "';";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1) {
                NotificationFactory.TipoNotifica type = NotificationFactory.TipoNotifica.EMAIL;
                NotificationFactory factory = new NotificationFactory();
                notifica = factory.getCanaleNotifica(type);

                notifica.setId(rs.getInt("idmessaggio"));
                notifica.setMsg(rs.getString("testo"));
                notifica.setCliente(UtenteDAO.getInstance().findById(rs.getInt("cliente_idcliente")));
                notifica.setManager(UtenteDAO.getInstance().findById(rs.getInt("manager_idmanager")));

                return notifica;
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
    public ArrayList<Notifica> findAll() {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idmessaggio, testo, cliente_idcliente, manager_idmanager FROM myShop.messaggio;";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<Notifica> notifiche = new ArrayList<>();
        try {
            while (rs.next()){
                NotificationFactory.TipoNotifica type = NotificationFactory.TipoNotifica.EMAIL;
                NotificationFactory factory = new NotificationFactory();
                notifica = factory.getCanaleNotifica(type);

                notifica.setId(rs.getInt("idmessaggio"));
                notifica.setMsg(rs.getString("testo"));
                notifica.setCliente(UtenteDAO.getInstance().findById(rs.getInt("cliente_idcliente")));
                notifica.setManager(UtenteDAO.getInstance().findById(rs.getInt("manager_idmanager")));

                notifiche.add(notifica);
            }
            return notifiche;
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
    public ArrayList<Notifica> findByCliente(IUtente utente) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idmessaggio, testo, cliente_idcliente, manager_idmanager FROM myShop.messaggio WHERE cliente_idcliente = '" + utente .getId() + ";";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<Notifica> notifiche = new ArrayList<>();
        try {
            while (rs.next()){
                NotificationFactory.TipoNotifica type = NotificationFactory.TipoNotifica.EMAIL;
                NotificationFactory factory = new NotificationFactory();
                notifica = factory.getCanaleNotifica(type);

                notifica.setId(rs.getInt("idmessaggio"));
                notifica.setMsg(rs.getString("testo"));
                notifica.setCliente(UtenteDAO.getInstance().findById(rs.getInt("cliente_idcliente")));
                notifica.setManager(UtenteDAO.getInstance().findById(rs.getInt("manager_idmanager")));

                notifiche.add(notifica);
            }
            return notifiche;
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
    public ArrayList<Notifica> findByManager(IUtente utente) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idmessaggio, testo, cliente_idcliente, manager_idmanager FROM myShop.messaggio WHERE manager_idmanager = '" + utente .getId() + ";";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<Notifica> notifiche = new ArrayList<>();
        try {
            while (rs.next()){
                NotificationFactory.TipoNotifica type = NotificationFactory.TipoNotifica.EMAIL;
                NotificationFactory factory = new NotificationFactory();
                notifica = factory.getCanaleNotifica(type);

                notifica.setId(rs.getInt("idmessaggio"));
                notifica.setMsg(rs.getString("testo"));
                notifica.setCliente(UtenteDAO.getInstance().findById(rs.getInt("cliente_idcliente")));
                notifica.setManager(UtenteDAO.getInstance().findById(rs.getInt("manager_idmanager")));

                notifiche.add(notifica);
            }
            return notifiche;
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
    public int add(Notifica notifica) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "INSERT INTO myShop.messaggio (testo, cliente_idcliente, manager_idmanager) VALUES ('" + notifica.getMsg() + "', '" + notifica.getCliente().getId() + "', '" + notifica.getManager().getId() + "');";
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
        } finally {
            connection.close();
        }

        return 0;
    }

    @Override
    public int removeById(int id) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "DELETE FROM myShop.messaggio WHERE idmessaggio = '" + id + "';";
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
            connection.close();
        }
        return 0;
    }

    @Override
    public int update(Notifica notifica) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "UPDATE myShop.messaggio SET testo = '" + notifica.getMsg() + "', cliente_idcliente = '" + notifica.getCliente().getId() + "', manager_idmanager = '" + notifica.getManager().getId() + "' WHERE idmessaggio = '" + notifica.getId() + "';";
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
            connection.close();
        }
        return 0;
    }
}
