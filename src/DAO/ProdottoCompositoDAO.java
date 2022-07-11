package DAO;

import DbInterface.DbConnection;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbOperation;
import DbInterface.ReadOperation;
import Model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
                IArticoloDAO prodottoDAO = new ProdottoDAO();
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
        String sql = "SELECT * FROM myShop.prodotto AS p1 INNER JOIN myShop.prodotto_has_prodotto AS pc INNER JOIN myShop.prodotto AS p2 ON p1.idprodotto = pc.prodotto_idprodotto AND p2.idprodotto = pc.prodotto_idprodotto1 ORDER BY p1.idprodotto;";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<IArticolo> prodottiCompositi = new ArrayList<>();
        try {
            while (rs.next()) {
                ArticoloFactoryProvider.ArticoloType type = ArticoloFactoryProvider.ArticoloType.PRODOTTO_COMPOSITO;
                ArticoloAbstractFactory factory = ArticoloFactoryProvider.getFactory(type);
                prodottoComposito = (ProdottoComposito) factory.crea();

            }
            return prodottiCompositi;
        } catch (SQLException e) {

        } catch (NullPointerException e) {

        } finally {
            connection.close();
        }

        return null;
    }

    @Override
    public int add(IArticolo articolo) {
        return 0;
    }

    @Override
    public int removeById(int id) {
        return 0;
    }

    @Override
    public int update(IArticolo articolo) {
        return 0;
    }

    @Override
    public IArticolo findById(int id) {
        return null;
    }
}
