package DAO;

import DbInterface.*;
import Model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;

public class ListaAcquistoDAO implements IListaAcquistoDAO{
    private static ListaAcquistoDAO instance;
    private ListaAcquisto listaAcquisto;
    private static IDbConnection connection;
    private static ResultSet rs;

    private ListaAcquistoDAO() {
        listaAcquisto = null;
        connection = null;
        rs = null;
    }

    public static ListaAcquistoDAO getInstance(){
        return instance;
    }

    @Override
    public ArrayList<ListaAcquisto> findByUser(IUtente utente) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idlistaAcquisto, utente_idutente, puntoVendita_idpuntoVendita FROM myShop.listaAcquisto WHERE utente_idutente = '" + utente.getId() + "';";
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

                sql = "SELECT prodotto_idprodotto, quantitaProdotto, FROM listaAcquisto_has_prodotto WHERE main_composito = 0 AND listaAcquisto_idlistaAcquisto = '" + listaAcquisto.getId() + "';";
                readOp = new ReadOperation(sql);
                rs = executor.executeOperation(readOp).getResultSet();
                while (rs.next()){
                    listaAcquisto.addProdotto((IProdotto) ProdottoDAO.getInstance().findById(rs.getInt("prodotto_idprodotto")), rs.getInt("quantitaProdotto"));
                }

                sql = "SELECT prodotto_idprodotto, quantitaProdotto, indirizzoSpedizione FROM listaAcquisto_has_prodotto WHERE main_composito = 1 AND listaAcquisto_idlistaAcquisto = '" + listaAcquisto.getId() + "';";
                readOp = new ReadOperation(sql);
                rs = executor.executeOperation(readOp).getResultSet();
                while (rs.next()){
                    listaAcquisto.addProdotto((IProdotto) ProdottoCompositoDAO.getInstance().findById(rs.getInt("prodotto_idprodotto")), rs.getInt("quantitaProdotto"));
                }
                listaAcquisto.addSpedizione(rs.getString("indirizzoSpedizione"));

                sql = "SELECT servizio_idservizio FROM listaAcquisto_has_prodotto WHERE listaAcquisto_idlistaAcquisto = '" + listaAcquisto.getId() + "';";
                readOp = new ReadOperation(sql);
                rs = executor.executeOperation(readOp).getResultSet();
                while (rs.next()){
                    if (rs.getInt("servizio_idservizio") != 0){
                        listaAcquisto.addServizio(ServizioDAO.getInstance().findById(rs.getInt("servizio_idservizio")));
                    }
                }
                liste.add(listaAcquisto);
            }
            return (liste);
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
    public ArrayList<ListaAcquisto> findAll() {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idlistaAcquisto, utente_idutente, puntoVendita_idpuntoVendita FROM myShop.listaAcquisto;";
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

                sql = "SELECT prodotto_idprodotto, quantitaProdotto, FROM listaAcquisto_has_prodotto WHERE main_composito = 0 AND listaAcquisto_idlistaAcquisto = '" + listaAcquisto.getId() + "';";
                readOp = new ReadOperation(sql);
                rs = executor.executeOperation(readOp).getResultSet();
                while (rs.next()){
                    listaAcquisto.addProdotto((IProdotto) ProdottoDAO.getInstance().findById(rs.getInt("prodotto_idprodotto")), rs.getInt("quantitaProdotto"));
                }

                sql = "SELECT prodotto_idprodotto, quantitaProdotto, indirizzoSpedizione FROM listaAcquisto_has_prodotto WHERE main_composito = 1 AND listaAcquisto_idlistaAcquisto = '" + listaAcquisto.getId() + "';";
                readOp = new ReadOperation(sql);
                rs = executor.executeOperation(readOp).getResultSet();
                while (rs.next()){
                    listaAcquisto.addProdotto((IProdotto) ProdottoCompositoDAO.getInstance().findById(rs.getInt("prodotto_idprodotto")), rs.getInt("quantitaProdotto"));
                }
                listaAcquisto.addSpedizione(rs.getString("indirizzoSpedizione"));

                sql = "SELECT servizio_idservizio FROM listaAcquisto_has_prodotto WHERE listaAcquisto_idlistaAcquisto = '" + listaAcquisto.getId() + "';";
                readOp = new ReadOperation(sql);
                rs = executor.executeOperation(readOp).getResultSet();
                while (rs.next()){
                    if (rs.getInt("servizio_idservizio") != 0){
                        listaAcquisto.addServizio(ServizioDAO.getInstance().findById(rs.getInt("servizio_idservizio")));
                    }
                }
                liste.add(listaAcquisto);
            }
            return liste;
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
    public ListaAcquisto findById(int id) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idlistaAcquisto, utente_idutente, puntoVendita_idpuntoVendita FROM myShop.listaAcquisto;";
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

                sql = "SELECT prodotto_idprodotto, quantitaProdotto, FROM listaAcquisto_has_prodotto WHERE main_composito = 0 AND listaAcquisto_idlistaAcquisto = '" + listaAcquisto.getId() + "';";
                readOp = new ReadOperation(sql);
                rs = executor.executeOperation(readOp).getResultSet();
                while (rs.next()){
                    listaAcquisto.addProdotto((IProdotto) ProdottoDAO.getInstance().findById(rs.getInt("prodotto_idprodotto")), rs.getInt("quantitaProdotto"));
                }

                sql = "SELECT prodotto_idprodotto, quantitaProdotto, indirizzoSpedizione FROM listaAcquisto_has_prodotto WHERE main_composito = 1 AND listaAcquisto_idlistaAcquisto = '" + listaAcquisto.getId() + "';";
                readOp = new ReadOperation(sql);
                rs = executor.executeOperation(readOp).getResultSet();
                while (rs.next()){
                    listaAcquisto.addProdotto((IProdotto) ProdottoCompositoDAO.getInstance().findById(rs.getInt("prodotto_idprodotto")), rs.getInt("quantitaProdotto"));
                }
                listaAcquisto.addSpedizione(rs.getString("indirizzoSpedizione"));

                sql = "SELECT servizio_idservizio FROM listaAcquisto_has_prodotto WHERE listaAcquisto_idlistaAcquisto = '" + listaAcquisto.getId() + "';";
                readOp = new ReadOperation(sql);
                rs = executor.executeOperation(readOp).getResultSet();
                while (rs.next()){
                    if (rs.getInt("servizio_idservizio") != 0){
                        listaAcquisto.addServizio(ServizioDAO.getInstance().findById(rs.getInt("servizio_idservizio")));
                    }
                }
                return listaAcquisto;
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
    public int add(ListaAcquisto listaAcquisto) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "INSERT INTO myShop.listaAcquisto (utente_idutente, puntoVendita_idpuntoVendita) VALUES ('" + listaAcquisto.getCliente().getId() + "', '" + listaAcquisto.getPuntoVendita().getId() + "');";
        IDbOperation writeOp = new WriteOperation(sql);
        rs = executor.executeOperation(writeOp).getResultSet();

        ArrayList<ListaAcquisto> liste = findAll();
        this.listaAcquisto = liste.get(liste.size() - 1);
        try {
            Iterator<Map.Entry<IProdotto, Integer>> prodottiIterator = listaAcquisto.getProdotti().entrySet().iterator();
            while (prodottiIterator.hasNext()){
                Map.Entry<IProdotto, Integer> prodottoEntry = prodottiIterator.next();
                sql = "INSERT INTO myShop.listaAcquisto_has_prodotto (listaAcquisto_idlistaAcquisto, prodotto_idprodotto, quantitaProdotto, indirizzoSpedizione) VALUES ('" + this.listaAcquisto.getId() + "', '" + prodottoEntry.getKey().getId() + "', '" + prodottoEntry.getValue() + "', '" + listaAcquisto.getIndirizzoSpedizione() + "');";
                writeOp = new WriteOperation(sql);

                rs = executor.executeOperation(writeOp).getResultSet();
            }

            ListIterator<IArticolo> servizioIterator = listaAcquisto.getServizi().listIterator();
            while (servizioIterator.hasNext()){
                IArticolo servizio = servizioIterator.next();
                sql = "INSERT INTO myShop.listaAcquisto_has_prodotto (listaAcquisto_idlistaAcquisto, servizio_idservizio) VALUES ('" + this.listaAcquisto.getId() + "', '" + servizio.getId() + "',);";
                writeOp = new WriteOperation(sql);
                rs = executor.executeOperation(writeOp).getResultSet();

                int rowCount = rs.getRow();
                return rowCount;
            }
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
        String sql = "DELETE FORM myShop.listaAcquisto_has_prodotto WHERE listaAcquisto_idlistaAcquisto = " + id + ";";
        IDbOperation writeOp = new WriteOperation(sql);
        rs = executor.executeOperation(writeOp).getResultSet();

        sql = "DELETE FROM myShop.listaAcquisto WHERE idlistaAcquisto = " + id + ";";
        writeOp = new WriteOperation(sql);
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
    public int update(ListaAcquisto listaAcquisto) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "UPDATE myShop.listaAcquisto SET utente_idutente = " + listaAcquisto.getCliente().getId() + ", puntoVendita_idpuntoVendita = " + listaAcquisto.getPuntoVendita().getId() + " WHERE idlistaAcquisto = " + listaAcquisto.getId() + ";";
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

    @Override
    public int addProdotto(ListaAcquisto listaAcquisto, IArticolo prodotto, int quantita) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "INSERT INTO myShop.listaAcquisto_has_prodotto (listaAcquisto_idlistaAcquisto, prodotto_idprodotto, quantitaProdotto) VALUES ('" + listaAcquisto.getId() + "', '" + prodotto.getId() + "', '" + quantita + "');";
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

    @Override
    public int addServizio(ListaAcquisto listaAcquisto, IArticolo servizio, String spedizione) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "INSERT INTO myShop.listaAcquisto_has_prodotto (listaAcquisto_idlistaAcquisto, servizio_idservizio, indirizzoSpedizione) VALUES ('" + listaAcquisto.getId() + "', '" + servizio.getId() + "', '" + spedizione + "');";
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

    @Override
    public int removeProdotto(ListaAcquisto listaAcquisto, IArticolo prodotto) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "DELETE FROM myShop.listaAcquisto_has_prodotto WHERE listaAcquisto_idlistaAcquisto = " + listaAcquisto.getId() + "prodotto_idprodotto = " + prodotto.getId() + ";";
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

    @Override
    public int removeServizio(ListaAcquisto listaAcquisto, IArticolo servizio) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "DELETE FROM myShop.listaAcquisto_has_prodotto WHERE listaAcquisto_idlistaAcquisto = " + listaAcquisto.getId() + "servizio_idservizio = " + servizio.getId() + ";";
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
