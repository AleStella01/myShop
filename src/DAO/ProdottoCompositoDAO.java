package DAO;

import DbInterface.*;
import Model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.concurrent.RecursiveTask;

public class ProdottoCompositoDAO implements IArticoloDAO{
    private static ProdottoCompositoDAO instance = new ProdottoCompositoDAO();
    private ProdottoComposito prodottoComposito;
    private static DbConnection connection;
    private static ResultSet rs;

    public ProdottoCompositoDAO() {
        prodottoComposito = null;
        connection = null;
        rs = null;
    }

    public static ProdottoCompositoDAO getInstance(){
        return instance;
    }

    @Override
    public IArticolo findByName(String nome) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT * FROM myShop.prodotto AS p1 INNER JOIN myShop.prodotto_has_prodotto AS pc INNER JOIN myShop.prodotto AS p2 ON p1.idprodotto = pc.prodotto_idprodotto AND p2.idprodotto = pc.prodotto_idprodotto1 WHERE pc.nomeProdottoComposito = '" + nome + "';";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();
        try {
            ArticoloFactoryProvider.ArticoloType type = ArticoloFactoryProvider.ArticoloType.PRODOTTO_COMPOSITO;
            ArticoloAbstractFactory factory = ArticoloFactoryProvider.getFactory(type);
            prodottoComposito = (ProdottoComposito) factory.crea();
            while (rs.next()){
                IArticoloDAO prodottoDAO = ProdottoDAO.getInstance();
                IProdotto prodotto = (IProdotto) prodottoDAO.findById(rs.getInt("prodotto_idprodotto1"));
                prodottoComposito.add(prodotto);
                prodottoComposito.setNome(rs.getString("nomeProdottoComposito"));
            }
            return prodottoComposito;
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
        String sql = "SELECT idprodotto, nome, costo, descrizione, categoria_idcategoria, collocazione_idcollocazione, produttore_idproduttore FROM myShop.prodotto WHERE main_composito = 1;";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<IArticolo> prodottiCompositi = new ArrayList<>();
        try {
            while (rs.next()) {
                ArticoloFactoryProvider.ArticoloType type = ArticoloFactoryProvider.ArticoloType.PRODOTTO_COMPOSITO;
                ArticoloAbstractFactory factory = ArticoloFactoryProvider.getFactory(type);
                prodottoComposito = (ProdottoComposito) factory.crea();

                prodottoComposito.setId(rs.getInt("idprodotto"));
                prodottoComposito.setNome(rs.getString("nome"));
                prodottoComposito.setPrezzo(rs.getFloat("costo"));
                prodottoComposito.setDescrizione(rs.getString("descrizione"));
                prodottoComposito.setCategoria(CategoriaDAO.getInstance().findById(rs.getInt("categoria_idcategoria")));
                prodottoComposito.setProduttore(ProduttoreDAO.getInstance().findById(rs.getInt("produttore_idproduttore")));

                sql = "SELECT p.idprodotto FROM myShop.prodotto_has_prodotto AS pc INNER JOIN myShop.prodotto AS p ON pc.prodotto_idprodotto1 = p.idprodotto WHERE pc.prodotto_idprodotto = '" + prodottoComposito.getId() + "';";
                readOp = new ReadOperation(sql);
                ResultSet rs1 = executor.executeOperation(readOp).getResultSet();
                while (rs1.next()){
                    prodottoComposito.add((IProdotto) ProdottoDAO.getInstance().findById(rs1.getInt("idprodotto")));
                }
                prodottiCompositi.add(prodottoComposito);
            }
            return prodottiCompositi;
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
    public int add(IArticolo articolo) {
        ProdottoComposito prodotto1 = (ProdottoComposito) articolo;
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "INSERT INTO myShop.prodotto (nome, costo, descrizione, main_composito, categoria_idcategoria, collocazione_idcollocazione, produttore_idproduttore) VALUES ('" + articolo.getNome() + "', '" + articolo.getPrezzo() + "', '" + articolo.getDescrizione() + "', 1, '" + articolo.getCategoria().getId() + "', '" + prodotto1.getCollocazione().getId() + "', '" + prodotto1.getProduttore().getId() + "');";
        IDbOperation writeOp = new WriteOperation(sql);
        rs = executor.executeOperation(writeOp).getResultSet();

        ListIterator<IProdotto> iterator = prodotto1.getSottoprodotti().listIterator();
        while (iterator.hasNext()){
            sql = "INSERT INTO myShop.prodotto_has_prodotto (prodotto_idprodotto, prodotto_idprodotto1) VALUES ('" + prodottoComposito.getId() + "', '" + prodotto1.getId() + "');";
            writeOp = new WriteOperation(sql);
            rs = executor.executeOperation(writeOp).getResultSet();
        }

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
        String sql = "DELETE FROM myShop.prodotto_has_prodotto WHERE prodotto_idprodotto = '" + id + "';";
        IDbOperation writeOp = new WriteOperation(sql);
        rs = executor.executeOperation(writeOp).getResultSet();

        sql = "DELETE FROM myShop.prodotto WHERE idprodotto = '" + id + "';";
        writeOp = new WriteOperation(sql);
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
        ProdottoComposito prodotto1 = (ProdottoComposito) articolo;
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "UPDATE myShop.prodotto SET nome = '" + articolo.getNome() + "', costo = '" + articolo.getPrezzo() + "', descrizione = '" + articolo.getDescrizione() + "', categoria_idcategoria = '" + articolo.getCategoria().getId() + "', collocazione_idcollocazione = '" + prodotto1.getCollocazione().getId() + "', produttore_idproduttore = '" + prodotto1.getProduttore().getId() +"' WHERE idprodotto = '" + articolo.getId() + "';";
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
        String sql = "SELECT * FROM myShop.prodotto AS p1 INNER JOIN myShop.prodotto_has_prodotto AS pc INNER JOIN myShop.prodotto AS p2 ON p1.idprodotto = pc.prodotto_idprodotto AND p2.idprodotto = pc.prodotto_idprodotto1 WHERE p1.idprodotto = '" + id + "';";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();
        try {
            ArticoloFactoryProvider.ArticoloType type = ArticoloFactoryProvider.ArticoloType.PRODOTTO_COMPOSITO;
            ArticoloAbstractFactory factory = ArticoloFactoryProvider.getFactory(type);
            prodottoComposito = (ProdottoComposito) factory.crea();
            while (rs.next()){
                IArticoloDAO prodottoDAO = ProdottoDAO.getInstance();
                IProdotto prodotto = (IProdotto) prodottoDAO.findById(rs.getInt("prodotto_idprodotto1"));
                prodottoComposito.add(prodotto);
                prodottoComposito.setNome(rs.getString("nomeProdottoComposito"));
            }
            return prodottoComposito;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            System.out.println("ResultSet: " + e.getMessage());
        } finally {
            connection.close();
        }
        return null;    }
}
