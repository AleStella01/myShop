package DAO;

import DbInterface.*;
import Model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdottoDAO implements IArticoloDAO{
    private static ProdottoDAO instance = new ProdottoDAO();
    private IArticolo prodotto;
    //private static IDbConnection connection;
    private static ResultSet rs;

    private ProdottoDAO() {
        prodotto = null;
        //connection = null;
        rs = null;
    }

    public static ProdottoDAO getInstance() {
        return instance;
    }

    @Override
    public IArticolo findByName(String nome) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idprodotto, nome, costo, descrizione, collocazione_idcollocazione, categoria_idcategoria, produttore_idproduttore FROM myShop.prodotto WHERE nome = '" + nome + "' AND main_composito = 0;";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1){
                ArticoloFactoryProvider.ArticoloType type = ArticoloFactoryProvider.ArticoloType.PRODOTTO;
                ArticoloAbstractFactory factory = ArticoloFactoryProvider.getFactory(type);
                prodotto = factory.crea();
                IProdotto prodotto1 = (IProdotto) prodotto;

                prodotto.setNome(rs.getString("nome"));
                prodotto.setPrezzo(rs.getFloat("costo"));
                prodotto.setDescrizione(rs.getString("descrizione"));
                prodotto.setId(rs.getInt("idprodotto"));
                prodotto1.setCollocazione(CollocazioneDAO.getInstance().findById(rs.getInt("collocazione_idcollocazione")));
                prodotto1.setCategoria(CategoriaDAO.getInstance().findById(rs.getInt("categoria_idcategoria")));
                prodotto1.setProduttore(ProduttoreDAO.getInstance().findById(rs.getInt("produttore_idproduttore")));

                return prodotto;
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
        String sql = "SELECT idprodotto, nome, costo, descrizione, collocazione_idcollocazione, categoria_idcategoria, produttore_idproduttore FROM myShop.prodotto WHERE main_composito = 0";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<IArticolo> prodotti = new ArrayList<>();
        try {
            while (rs.next()){
                ArticoloFactoryProvider.ArticoloType type = ArticoloFactoryProvider.ArticoloType.PRODOTTO;
                ArticoloAbstractFactory factory = ArticoloFactoryProvider.getFactory(type);
                prodotto = factory.crea();
                IProdotto prodotto1 = (IProdotto) prodotto;

                prodotto.setNome(rs.getString("nome"));
                prodotto.setPrezzo(rs.getFloat("costo"));
                prodotto.setDescrizione(rs.getString("descrizione"));
                prodotto.setId(rs.getInt("idprodotto"));
                prodotto1.setCollocazione(CollocazioneDAO.getInstance().findById(rs.getInt("collocazione_idcollocazione")));
                prodotto1.setCategoria(CategoriaDAO.getInstance().findById(rs.getInt("categoria_idcategoria")));
                prodotto1.setProduttore(ProduttoreDAO.getInstance().findById(rs.getInt("produttore_idproduttore")));

                prodotti.add(prodotto);
            }
            return prodotti;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            System.out.println("Resultset: " + e.getMessage());
        }

        return null;
    }

    @Override
    public int add(IArticolo articolo) {
        IProdotto prodotto1 = (IProdotto) articolo;
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "INSERT INTO myShop.prodotto (nome, costo, descrizione, categoria_idcategoria, collocazione_idcollocazione, produttore_idproduttore, main_composito) VALUES ('" + articolo.getNome() + "', '" + articolo.getPrezzo() + "', '" + articolo.getDescrizione() + "', '" + articolo.getCategoria().getId() + "', '" + prodotto1.getCollocazione().getId() + "', '" + prodotto1.getProduttore().getId() + "', + " + false + ");";
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
    public int removeById(int id) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "DELETE FROM myShop.prodotto WHERE idprodotto = " + id + ";";
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
    public int update(IArticolo articolo) {
        IProdotto prodotto1 = (IProdotto) articolo;
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "UPDATE myShop.prodotto SET nome = '" + articolo.getNome() + "', costo = " + articolo.getPrezzo() + ", descrizione = '" + articolo.getDescrizione() + "', categoria_idcategoria = " + articolo.getCategoria().getId() + ", collocazione_idcollocazione = " + prodotto1.getCollocazione().getId() + ", produttore_idproduttore = " + prodotto1.getProduttore().getId() + " WHERE idprodotto = " + articolo.getId() + ";";
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
    public IArticolo findById(int id){
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idprodotto, nome, costo, descrizione, collocazione_idcollocazione, categoria_idcategoria, produttore_idproduttore FROM myShop.prodotto WHERE idprodotto = '" + id + "' AND main_composito = 0;";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1){
                ArticoloFactoryProvider.ArticoloType type = ArticoloFactoryProvider.ArticoloType.PRODOTTO;
                ArticoloAbstractFactory factory = ArticoloFactoryProvider.getFactory(type);
                prodotto = factory.crea();
                IProdotto prodotto1 = (IProdotto) prodotto;

                prodotto.setNome(rs.getString("nome"));
                prodotto.setPrezzo(rs.getFloat("costo"));
                prodotto.setDescrizione(rs.getString("descrizione"));
                prodotto.setId(rs.getInt("idprodotto"));
                prodotto1.setCollocazione(CollocazioneDAO.getInstance().findById(rs.getInt("collocazione_idcollocazione")));
                prodotto1.setCategoria(CategoriaDAO.getInstance().findById(rs.getInt("categoria_idcategoria")));
                prodotto1.setProduttore(ProduttoreDAO.getInstance().findById(rs.getInt("produttore_idproduttore")));

                return prodotto;
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
}
