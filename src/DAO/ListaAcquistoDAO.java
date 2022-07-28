package DAO;

import DbInterface.*;
import Model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ListaAcquistoDAO implements IListaAcquistoDAO{
    private static ListaAcquistoDAO instance = new ListaAcquistoDAO();
    private ListaAcquisto listaAcquisto;
    //private static IDbConnection connection;
    private static ResultSet rs;

    private ListaAcquistoDAO() {
        listaAcquisto = null;
        //connection = null;
        rs = null;
    }

    public static ListaAcquistoDAO getInstance(){
        return instance;
    }

    @Override
    public ArrayList<ListaAcquisto> findByUser(IUtente utente) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idlistaAcquisto, utente_idutente, puntoVendita_idpuntoVendita, pagato FROM myShop.listaAcquisto WHERE utente_idutente = '" + utente.getId() + "';";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<ListaAcquisto> liste = new ArrayList<>();
        try {
            while (rs.next()){
                ListaAcquistoFactory.ListaAcquistoType type = ListaAcquistoFactory.ListaAcquistoType.CLIENTE;
                ListaAcquistoFactory factory = new ListaAcquistoFactory();
                listaAcquisto = factory.getListaAcquistoFactory(type);

                listaAcquisto.setId(rs.getInt("idlistaAcquisto"));
                listaAcquisto.setCliente(UtenteDAO.getInstance().findById(rs.getInt("utente_idutente")));
                listaAcquisto.setPuntoVendita(PuntoVenditaDAO.getInstance().findById(rs.getInt("puntoVendita_idpuntoVendita")));
                listaAcquisto.setPagato(rs.getBoolean("pagato"));

                liste.add(listaAcquisto);
            }
            return (liste);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public ArrayList<ListaAcquisto> findAll() {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idlistaAcquisto, utente_idutente, puntoVendita_idpuntoVendita, pagato FROM myShop.listaAcquisto;";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<ListaAcquisto> liste = new ArrayList<>();
        try {
            while (rs.next()){
                ListaAcquistoFactory.ListaAcquistoType type = ListaAcquistoFactory.ListaAcquistoType.CLIENTE;
                ListaAcquistoFactory factory = new ListaAcquistoFactory();
                listaAcquisto = factory.getListaAcquistoFactory(type);

                listaAcquisto.setId(rs.getInt("idlistaAcquisto"));
                listaAcquisto.setCliente(UtenteDAO.getInstance().findById(rs.getInt("utente_idutente")));
                listaAcquisto.setPuntoVendita(PuntoVenditaDAO.getInstance().findById(rs.getInt("puntoVendita_idpuntoVendita")));
                listaAcquisto.setPagato(rs.getBoolean("pagato"));
                liste.add(listaAcquisto);
            }
            return liste;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public ListaAcquisto findById(int id) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idlistaAcquisto, utente_idutente, puntoVendita_idpuntoVendita, pagato FROM myShop.listaAcquisto WHERE idlistaAcquisto = " + id +  ";";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1){
                ListaAcquistoFactory.ListaAcquistoType type = ListaAcquistoFactory.ListaAcquistoType.CLIENTE;
                ListaAcquistoFactory factory = new ListaAcquistoFactory();
                listaAcquisto = factory.getListaAcquistoFactory(type);

                listaAcquisto.setId(rs.getInt("idlistaAcquisto"));
                listaAcquisto.setCliente(UtenteDAO.getInstance().findById(rs.getInt("utente_idutente")));
                listaAcquisto.setPuntoVendita(PuntoVenditaDAO.getInstance().findById(rs.getInt("puntoVendita_idpuntoVendita")));
                listaAcquisto.setPagato(rs.getBoolean("pagato"));

                return listaAcquisto;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public int add(ListaAcquisto listaAcquisto) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "INSERT INTO myShop.listaAcquisto (utente_idutente, puntoVendita_idpuntoVendita, pagato) VALUES ('" + listaAcquisto.getCliente().getId() + "', '" + listaAcquisto.getPuntoVendita().getId() + "', " + listaAcquisto.isPagato() + ");";
        IDbOperation writeOp = new WriteOperation(sql);
        rs = executor.executeOperation(writeOp).getResultSet();

        ArrayList<ListaAcquisto> liste = findAll();
        this.listaAcquisto = liste.get(liste.size() - 1);
        try {
            int rowCount = rs.getRow();
            return rowCount;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.getMessage();
        }
        return 0;
    }

    @Override
    public int removeById(int id) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "DELETE FROM myShop.listaAcquisto WHERE idlistaAcquisto = " + id + ";";
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
        }
        return 0;
    }

    @Override
    public int update(ListaAcquisto listaAcquisto) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "UPDATE myShop.listaAcquisto SET utente_idutente = " + listaAcquisto.getCliente().getId() + ", puntoVendita_idpuntoVendita = " + listaAcquisto.getPuntoVendita().getId() + ", pagato = " + listaAcquisto.isPagato() + " WHERE idlistaAcquisto = " + listaAcquisto.getId() + ";";
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
        }
        return 0;
    }

    @Override
    public int addProdotto(ListaAcquisto listaAcquisto, IArticolo prodotto, int quantita) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "INSERT INTO myShop.listaAcquisto_has_prodotto (listaAcquisto_idlistaAcquisto, prodotto_idprodotto, quantitaProdotto) VALUES (" + listaAcquisto.getId() + ", " + prodotto.getId() + ", " + quantita + ");";
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
        }
        return 0;
    }

    @Override
    public int addServizio(ListaAcquisto listaAcquisto, IArticolo servizio, String spedizione) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "INSERT INTO myShop.listaAcquisto_has_servizio (listaascquisto_idlistaacquisto, servizio_idservizio, note) VALUES (" + listaAcquisto.getId() + ", " + servizio.getId() + ", '" + spedizione + "');";
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
        }
        return 0;
    }

    @Override
    public int removeProdotto(ListaAcquisto listaAcquisto, IArticolo prodotto) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "DELETE FROM myShop.listaAcquisto_has_prodotto WHERE listaAcquisto_idlistaAcquisto = " + listaAcquisto.getId() + " AND prodotto_idprodotto = " + prodotto.getId() + ";";
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
        }
        return 0;
    }

    @Override
    public int removeServizio(ListaAcquisto listaAcquisto, IArticolo servizio) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "DELETE FROM myShop.listaAcquisto_has_servizio WHERE listaascquisto_idlistaacquisto = " + listaAcquisto.getId() + " AND servizio_idservizio = " + servizio.getId() + ";";
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
        }
        return 0;
    }

    public Map<IProdotto, Integer> getProdotti(ListaAcquisto listaAcquisto){
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT prodotto_idprodotto, quantitaProdotto FROM myShop.listaAcquisto_has_prodotto WHERE listaAcquisto_idlistaAcquisto = " + listaAcquisto.getId() + ";";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        Map<IProdotto, Integer> prodotti = new HashMap<>();
        try {
            while (rs.next()){
                prodotti.put((IProdotto) ProdottoDAO.getInstance().findById(rs.getInt("prodotto_idprodotto")), rs.getInt("quantitaProdotto"));
            }
            return prodotti;
        } catch (SQLException e){
            e.printStackTrace();
        } catch (NullPointerException e){
            e.getMessage();
        }
        return null;
    }

    public List<IArticolo> getServizi(ListaAcquisto listaAcquisto){
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT servizio_idservizio, note FROM myShop.listaAcquisto_has_servizio WHERE listaascquisto_idlistaacquisto = " + listaAcquisto.getId() + ";";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        List<IArticolo> servizi = new ArrayList<>();
        try{
            while (rs.next()){
                servizi.add(ServizioDAO.getInstance().findById(rs.getInt("servizio_idservizio")));
                if (rs.getString("note") != null) {
                    listaAcquisto.setIndirizzoSpedizione(rs.getString("note"));
                }
            }
            return servizi;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.getMessage();
        }
        return null;
    }

}
