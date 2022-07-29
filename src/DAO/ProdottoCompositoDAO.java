package DAO;

import DbInterface.*;
import Model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.RecursiveTask;

public class ProdottoCompositoDAO implements IArticoloDAO{
    private static ProdottoCompositoDAO instance = new ProdottoCompositoDAO();
    private ProdottoComposito prodottoComposito;
    //private static DbConnection connection;
    private static ResultSet rs;

    public ProdottoCompositoDAO() {
        prodottoComposito = null;
        //connection = null;
        rs = null;
    }

    public static ProdottoCompositoDAO getInstance(){
        return instance;
    }

    @Override
    public IArticolo findByName(String nome) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idprodotto, nome, costo, descrizione, collocazione_idcollocazione, categoria_idcategoria, produttore_idproduttore FROM myShop.prodotto WHERE nome = '" + nome + "' AND main_composito = 1;";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1){
                ArticoloFactoryProvider.ArticoloType type = ArticoloFactoryProvider.ArticoloType.PRODOTTO_COMPOSITO;
                ArticoloAbstractFactory factory = ArticoloFactoryProvider.getFactory(type);
                IArticolo articolo = factory.crea();
                IProdotto prodotto1 = (IProdotto) articolo;

                articolo.setNome(rs.getString("nome"));
                articolo.setPrezzo(rs.getFloat("costo"));
                articolo.setDescrizione(rs.getString("descrizione"));
                articolo.setId(rs.getInt("idprodotto"));
                prodotto1.setCollocazione(CollocazioneDAO.getInstance().findById(rs.getInt("collocazione_idcollocazione")));
                prodotto1.setCategoria(CategoriaDAO.getInstance().findById(rs.getInt("categoria_idcategoria")));
                prodotto1.setProduttore(ProduttoreDAO.getInstance().findById(rs.getInt("produttore_idproduttore")));

                return articolo;
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            System.out.println("ResultSet: " + e.getMessage());
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

                prodottoComposito.setNome(rs.getString("nome"));
                prodottoComposito.setId(rs.getInt("idprodotto"));
                prodottoComposito.setDescrizione(rs.getString("descrizione"));
                prodottoComposito.setCategoria(CategoriaDAO.getInstance().findById(rs.getInt("categoria_idcategoria")));
                prodottoComposito.setProduttore(ProduttoreDAO.getInstance().findById(5));

                sql = "SELECT p.idprodotto FROM myShop.prodotto_has_prodotto AS pc INNER JOIN myShop.prodotto AS p ON pc.prodotto_idprodotto1 = p.idprodotto WHERE pc.prodotto_idprodotto = " + prodottoComposito.getId() + ";";
                readOp = new ReadOperation(sql);
                ResultSet rs1 = executor.executeOperation(readOp).getResultSet();
                while (rs1.next()){
                    prodottoComposito.add((IProdotto) ProdottoDAO.getInstance().findById(rs1.getInt("idprodotto")));
                }
                prodottoComposito.setPrezzo(prodottoComposito.getPrezzo());
                prodottiCompositi.add(prodottoComposito);
            }
            return prodottiCompositi;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.getMessage();
        }

        return null;
    }

    @Override
    public int add(IArticolo articolo) {
        ProdottoComposito prodotto1 = (ProdottoComposito) articolo;
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "INSERT INTO myShop.prodotto (nome, costo, descrizione, main_composito, categoria_idcategoria, collocazione_idcollocazione, produttore_idproduttore) VALUES ('" + articolo.getNome() + "', " + articolo.getPrezzo() + ", '" + articolo.getDescrizione() + "', 1, " + articolo.getCategoria().getId() + ", 3, 5);";
        IDbOperation writeOp = new WriteOperation(sql);
        rs = executor.executeOperation(writeOp).getResultSet();
        try {
            int rowCount = 0;
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
    public int removeById(int id) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "DELETE FROM myShop.prodotto_has_prodotto WHERE prodotto_idprodotto = " + id + ";";
        IDbOperation writeOp = new WriteOperation(sql);
        rs = executor.executeOperation(writeOp).getResultSet();

        sql = "DELETE FROM myShop.prodotto WHERE idprodotto = " + id + ";";
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
    public int update(IArticolo articolo) {
        ProdottoComposito prodotto1 = (ProdottoComposito) articolo;
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "UPDATE myShop.prodotto SET nome = '" + articolo.getNome() + "', costo = " + articolo.getPrezzo() + ", descrizione = '" + articolo.getDescrizione() + "', categoria_idcategoria = " + articolo.getCategoria().getId() + ", collocazione_idcollocazione = 3, produttore_idproduttore = 5 WHERE idprodotto = " + articolo.getId() + ";";
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
    public IArticolo findById(int id) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idprodotto, nome, costo, descrizione, collocazione_idcollocazione, categoria_idcategoria, produttore_idproduttore FROM myShop.prodotto WHERE idprodotto = " + id + " AND main_composito = 1;";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1){
                ArticoloFactoryProvider.ArticoloType type = ArticoloFactoryProvider.ArticoloType.PRODOTTO_COMPOSITO;
                ArticoloAbstractFactory factory = ArticoloFactoryProvider.getFactory(type);
                IArticolo articolo = factory.crea();
                IProdotto prodotto1 = (IProdotto) articolo;

                articolo.setNome(rs.getString("nome"));
                articolo.setPrezzo(rs.getFloat("costo"));
                articolo.setDescrizione(rs.getString("descrizione"));
                articolo.setId(rs.getInt("idprodotto"));
                prodotto1.setCollocazione(CollocazioneDAO.getInstance().findById(rs.getInt("collocazione_idcollocazione")));
                prodotto1.setCategoria(CategoriaDAO.getInstance().findById(rs.getInt("categoria_idcategoria")));
                prodotto1.setProduttore(ProduttoreDAO.getInstance().findById(rs.getInt("produttore_idproduttore")));

                return articolo;
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            System.out.println("ResultSet: " + e.getMessage());
        }

        return null;
    }

    public int addProdotto(IArticolo articolo, IProdotto prodotto){
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "INSERT INTO myShop.prodotto_has_prodotto (prodotto_idprodotto, prodotto_idprodotto1) VALUES (" + articolo.getId() + ", " + prodotto.getId() + ");";
        IDbOperation writeOp = new WriteOperation(sql);
        rs = executor.executeOperation(writeOp).getResultSet();
        try {
            int rowCount = 0;
            rowCount = rs.getRow();
            return rowCount;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.getMessage();
        }
        return 0;
    }

    public int removeProdotto(IArticolo articolo, IProdotto prodotto){
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "DELETE FROM myShop.prodotto_has_prodotto WHERE prodotto_idprodotto = " + articolo.getId() + " AND prodotto_idprodotto1 = " + prodotto.getId() + ";";
        IDbOperation writeOp = new WriteOperation(sql);
        rs = executor.executeOperation(writeOp).getResultSet();
        try {
            int rowCount = 0;
            rowCount = rs.getRow();
            return rowCount;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.getMessage();
        }
        return 0;
    }

    public List<IProdotto> getProdotti(IProdotto prodottoComposito){
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT prodotto_idprodotto1 FROM prodotto_has_prodotto WHERE prodotto_idprodotto = " + prodottoComposito.getId() + ";";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        List<IProdotto> sottoprodotti = new ArrayList<>();
        try {
            while (rs.next()){
                sottoprodotti.add((IProdotto) ProdottoDAO.getInstance().findById(rs.getInt("prodotto_idprodotto1")));
            }
            return sottoprodotti;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            e.getMessage();
        }
        return null;
    }
}
