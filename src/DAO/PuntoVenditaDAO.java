package DAO;

import DbInterface.*;
import Model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class PuntoVenditaDAO implements IPuntoVenditaDAO{
    private static PuntoVenditaDAO instance = new PuntoVenditaDAO();
    private IPuntoVendita puntoVendita;
    //private static IDbConnection connection;
    private static ResultSet rs;

    private PuntoVenditaDAO() {
        puntoVendita = null;
        //connection = null;
        rs = null;
    }

    public static PuntoVenditaDAO getInstance(){
        return instance;
    }

    @Override
    public IPuntoVendita findById(int id) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idpuntoVendita, nome, citta, indirizzo, utente_idutente FROM myShop.puntoVendita WHERE idpuntoVendita = " + id + ";";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1){
                PuntoVenditaFactoryProvider.PuntoVenditaType type = PuntoVenditaFactoryProvider.PuntoVenditaType.MOBILI;
                PuntoVenditaAbstractFactory factory = PuntoVenditaFactoryProvider.getFactory(type);
                puntoVendita = factory.crea();

                puntoVendita.setId(rs.getInt("idpuntovendita"));
                puntoVendita.setNome(rs.getString("nome"));
                puntoVendita.setCitta(rs.getString("citta"));
                puntoVendita.setIndirizzo(rs.getString("indirizzo"));
                puntoVendita.setManager(UtenteDAO.getInstance().findById(rs.getInt("utente_idutente")));

                sql = "SELECT prodotto_idprodotto, quantità FROM puntoVendita_has_prodotto AS pv INNER JOIN prodotto AS p ON p.idprodotto = pv.prodotto_idprodotto WHERE p.main_composito = 0 AND puntoVendita_idpuntoVendita = " + id + ";";
                readOp = new ReadOperation(sql);
                rs = executor.executeOperation(readOp).getResultSet();
                Map<IProdotto, Integer> prodottiVenduti = new HashMap<>();
                while (rs.next()){
                    prodottiVenduti.put((IProdotto) ProdottoDAO.getInstance().findById(rs.getInt("prodotto_idprodotto")), rs.getInt("quantita"));
                }

                sql = "SELECT prodotto_idprodotto, quantità FROM puntoVendita_has_prodotto AS pv INNER JOIN prodotto AS p ON p.idprodotto = pv.prodotto_idprodotto WHERE p.main_composito = 1 AND puntoVendita_idpuntoVendita = " + id + ";";
                readOp = new ReadOperation(sql);
                rs = executor.executeOperation(readOp).getResultSet();
                while (rs.next()){
                    prodottiVenduti.put((IProdotto) ProdottoCompositoDAO.getInstance().findById(rs.getInt("prodotto_idprodotto")), rs.getInt("quantita"));
                }

                return puntoVendita;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public IPuntoVendita findByName(String nome) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idpuntoVendita, nome, citta, indirizzo, utente_idutente FROM myShop.puntoVendita WHERE nome = '" + nome + "';";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1){
                PuntoVenditaFactoryProvider.PuntoVenditaType type = PuntoVenditaFactoryProvider.PuntoVenditaType.MOBILI;
                PuntoVenditaAbstractFactory factory = PuntoVenditaFactoryProvider.getFactory(type);
                puntoVendita = factory.crea();

                puntoVendita.setId(rs.getInt("idpuntovendita"));
                puntoVendita.setNome(rs.getString("nome"));
                puntoVendita.setCitta(rs.getString("citta"));
                puntoVendita.setIndirizzo(rs.getString("indirizzo"));
                puntoVendita.setManager(UtenteDAO.getInstance().findById(rs.getInt("utente_idutente")));

                sql = "SELECT prodotto_idprodotto, quantità FROM puntoVendita_has_prodotto AS pv INNER JOIN prodotto AS p ON p.idprodotto = pv.prodotto_idprodotto WHERE p.main_composito = 0 AND puntoVendita_idpuntoVendita = " + puntoVendita.getId() + ";";
                readOp = new ReadOperation(sql);
                rs = executor.executeOperation(readOp).getResultSet();
                Map<IProdotto, Integer> prodottiVenduti = new HashMap<>();
                while (rs.next()){
                    prodottiVenduti.put((IProdotto) ProdottoDAO.getInstance().findById(rs.getInt("prodotto_idprodotto")), rs.getInt("quantita"));
                }

                sql = "SELECT prodotto_idprodotto, quantità FROM puntoVendita_has_prodotto AS pv INNER JOIN prodotto AS p ON p.idprodotto = pv.prodotto_idprodotto WHERE p.main_composito = 1 AND puntoVendita_idpuntoVendita = " + puntoVendita.getId() + ";";
                readOp = new ReadOperation(sql);
                rs = executor.executeOperation(readOp).getResultSet();
                while (rs.next()){
                    prodottiVenduti.put((IProdotto) ProdottoCompositoDAO.getInstance().findById(rs.getInt("prodotto_idprodotto")), rs.getInt("quantita"));
                }

                return puntoVendita;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public IPuntoVendita findByManager(IUtente manager) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idpuntoVendita, nome, citta, indirizzo, utente_idutente FROM myShop.puntoVendita WHERE utente_idutente = " + manager.getId() + ";";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1){
                PuntoVenditaFactoryProvider.PuntoVenditaType type = PuntoVenditaFactoryProvider.PuntoVenditaType.MOBILI;
                PuntoVenditaAbstractFactory factory = PuntoVenditaFactoryProvider.getFactory(type);
                puntoVendita = factory.crea();

                puntoVendita.setId(rs.getInt("idpuntovendita"));
                puntoVendita.setNome(rs.getString("nome"));
                puntoVendita.setCitta(rs.getString("citta"));
                puntoVendita.setIndirizzo(rs.getString("indirizzo"));
                puntoVendita.setManager(UtenteDAO.getInstance().findById(rs.getInt("utente_idutente")));

                sql = "SELECT prodotto_idprodotto, quantità FROM puntoVendita_has_prodotto AS pv INNER JOIN prodotto AS p ON p.idprodotto = pv.prodotto_idprodotto WHERE p.main_composito = 0 AND puntoVendita_idpuntoVendita = " + puntoVendita.getId() + ";";
                readOp = new ReadOperation(sql);
                rs = executor.executeOperation(readOp).getResultSet();
                Map<IProdotto, Integer> prodottiVenduti = new HashMap<>();
                while (rs.next()){
                    prodottiVenduti.put((IProdotto) ProdottoDAO.getInstance().findById(rs.getInt("prodotto_idprodotto")), rs.getInt("quantita"));
                }

                sql = "SELECT prodotto_idprodotto, quantità FROM puntoVendita_has_prodotto AS pv INNER JOIN prodotto AS p ON p.idprodotto = pv.prodotto_idprodotto WHERE p.main_composito = 1 AND puntoVendita_idpuntoVendita = " + puntoVendita.getId() + ";";
                readOp = new ReadOperation(sql);
                rs = executor.executeOperation(readOp).getResultSet();
                while (rs.next()){
                    prodottiVenduti.put((IProdotto) ProdottoCompositoDAO.getInstance().findById(rs.getInt("prodotto_idprodotto")), rs.getInt("quantita"));
                }

                return puntoVendita;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public ArrayList<IPuntoVendita> findAll() {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idpuntoVendita, nome, citta, indirizzo, utente_idutente FROM myShop.puntoVendita;";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<IPuntoVendita> puntiVendita = new ArrayList<>();
        try {
            while (rs.next()){
                PuntoVenditaFactoryProvider.PuntoVenditaType type = PuntoVenditaFactoryProvider.PuntoVenditaType.MOBILI;
                PuntoVenditaAbstractFactory factory = PuntoVenditaFactoryProvider.getFactory(type);
                puntoVendita = factory.crea();

                puntoVendita.setId(rs.getInt("idpuntovendita"));
                puntoVendita.setNome(rs.getString("nome"));
                puntoVendita.setCitta(rs.getString("citta"));
                puntoVendita.setIndirizzo(rs.getString("indirizzo"));
                puntoVendita.setManager(UtenteDAO.getInstance().findById(rs.getInt("utente_idutente")));

                sql = "SELECT prodotto_idprodotto, quantità FROM puntoVendita_has_prodotto AS pv INNER JOIN prodotto AS p ON p.idprodotto = pv.prodotto_idprodotto WHERE p.main_composito = 0 AND puntoVendita_idpuntoVendita = " + puntoVendita.getId() + ";";
                readOp = new ReadOperation(sql);
                rs = executor.executeOperation(readOp).getResultSet();
                Map<IProdotto, Integer> prodottiVenduti = new HashMap<>();
                while (rs.next()){
                    prodottiVenduti.put((IProdotto) ProdottoDAO.getInstance().findById(rs.getInt("prodotto_idprodotto")), rs.getInt("quantita"));
                }

                sql = "SELECT prodotto_idprodotto, quantità FROM puntoVendita_has_prodotto AS pv INNER JOIN prodotto AS p ON p.idprodotto = pv.prodotto_idprodotto WHERE p.main_composito = 1 AND puntoVendita_idpuntoVendita = " + puntoVendita.getId() + ";";
                readOp = new ReadOperation(sql);
                rs = executor.executeOperation(readOp).getResultSet();
                while (rs.next()){
                    prodottiVenduti.put((IProdotto) ProdottoCompositoDAO.getInstance().findById(rs.getInt("prodotto_idprodotto")), rs.getInt("quantita"));
                }
                puntiVendita.add(puntoVendita);
            }
            return puntiVendita;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public int add(IPuntoVendita puntoVendita) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "INSERT INTO myShop.puntoVendita (nome, citta, indirizzo, utente_idutente) VALUES ('" + puntoVendita.getNome() + "', '" + puntoVendita.getCitta() + "', '" + puntoVendita.getIndirizzo() + "', " + puntoVendita.getManager().getId() + ");";
        IDbOperation writeOp = new WriteOperation(sql);
        rs = executor.executeOperation(writeOp).getResultSet();

        Iterator<Map.Entry<IProdotto, Integer>> iterator = puntoVendita.getProdottiVenduti().entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<IProdotto, Integer> entry = iterator.next();
            sql = "INSERT INTO myShop.puntoVendita_has_prodotto (puntoVendita_idpuntoVendita, prodotto_idprodotto, quantita) VALUES (" + puntoVendita.getId() + ", " + entry.getKey().getId() + ", '" + entry.getValue() + "');";
            writeOp = new WriteOperation(sql);
            rs = executor.executeOperation(writeOp).getResultSet();
        }
        try {
            int rowCount = rs.getRow();
            return rowCount;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int removeById(int id) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "DELETE FROM myShop.puntoVendita_has_prodotto WHERE puntoVendita_idpuntoVendita = " + id + ";";
        IDbOperation writeOp = new WriteOperation(sql);
        rs = executor.executeOperation(writeOp).getResultSet();

        sql = "DELETE FROM myShop.puntoVendita WHERE idpuntoVendita = " + id + ";";
        writeOp = new WriteOperation(sql);
        rs = executor.executeOperation(writeOp).getResultSet();

        int rowCount = 0;
        try {
            rowCount = rs.getRow();
            return rowCount;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.getMessage();
        }
        return 0;
    }

    @Override
    public int update(IPuntoVendita puntoVendita) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "UPDATE myShop.puntoVendita SET nome = '" + puntoVendita.getNome() + "', citta = '" + puntoVendita.getCitta() + "', indirizzo = '" + puntoVendita.getIndirizzo() + ", utente_idutente = " + puntoVendita.getManager().getId() + " WHERE idpuntoVendita = " + puntoVendita.getId() + ";";
        IDbOperation writeOp = new WriteOperation(sql);
        rs = executor.executeOperation(writeOp).getResultSet();

        int rowCount = 0;
        try {
            rowCount = rs.getRow();
            return rowCount;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.getMessage();
        }
        return 0;
    }

    @Override
    public int addProdottoVenduto(IPuntoVendita puntoVendita, IProdotto prodotto, int quantita) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "INSERT INTO myShop.puntoVendita_has_prodotto (puntoVendita_idpuntoVendita, prodotto_idprodotto, quantita) VALUES (" + puntoVendita.getId() + ", " + prodotto.getId() + ", " + quantita + ");";
        IDbOperation writeOp = new WriteOperation(sql);
        rs = executor.executeOperation(writeOp).getResultSet();

        int rowCount = 0;
        try {
            rowCount = rs.getRow();
            return rowCount;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.getMessage();
        }
        return 0;
    }

    @Override
    public int removeProdottoVenduto(IPuntoVendita puntoVendita, IProdotto prodotto) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "DELETE FROM myShop.puntoVendita_has_prodotto WHERE puntoVendita_idpuntoVendita = " + puntoVendita.getId() + " AND prodotto_idprodotto = " + prodotto.getId() + ";";
        IDbOperation writeOp = new WriteOperation(sql);
        rs = executor.executeOperation(writeOp).getResultSet();

        int rowCount = 0;
        try {
            rowCount = rs.getRow();
            return rowCount;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.getMessage();
        }
        return 0;
    }
}
