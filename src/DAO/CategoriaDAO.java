package DAO;

import DbInterface.*;
import Model.CategoriaAbstractFactory;
import Model.CategoriaFactoryProvider;
import Model.CollocazioneFactory;
import Model.ICategoria;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoriaDAO implements ICategoriaDAO{
    private static CategoriaDAO instance = new CategoriaDAO();
    private ICategoria categoria;
    private static IDbConnection  connection;
    private static ResultSet rs;

    private CategoriaDAO(){
        categoria = null;
        connection = null;
        rs = null;
    }

    public static CategoriaDAO getInstance(){
        return instance;
    }


    @Override
    public ICategoria findByName(String nome) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idcategoria, nome, tipo FROM myShop.categoria WHERE nome = '" + nome + "';";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1) {
                CategoriaFactoryProvider.CategoriaFactoryType type = (CategoriaFactoryProvider.CategoriaFactoryType) rs.getObject("tipo");
                CategoriaAbstractFactory factory = CategoriaFactoryProvider.getFactory(type);
                categoria = factory.crea();

                categoria.setId(rs.getInt("idcategoria"));
                categoria.setNome(rs.getString("nome"));

                sql = "SELECT categoria_idcategoria1 FROM categoria_has_categoria WHERE categoria_idcategoria = '" + categoria.getId() + "';";
                readOp = new ReadOperation(sql);
                rs = executor.executeOperation(readOp).getResultSet();
                while (rs.next()){
                    categoria.add(findById(rs.getInt("categoria_idcategoria1")));
                }
            }
            return categoria;
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
    public ArrayList<ICategoria> findAll() {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idcategoria, nome, tipo FROM myShop.categoria;";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        ArrayList<ICategoria> categorie = new ArrayList<>();
        try {
            while (rs.next()){
                CategoriaFactoryProvider.CategoriaFactoryType type = (CategoriaFactoryProvider.CategoriaFactoryType) rs.getObject("tipo");
                CategoriaAbstractFactory factory = CategoriaFactoryProvider.getFactory(type);
                categoria = factory.crea();

                categoria.setId(rs.getInt("idcategoria"));
                categoria.setNome(rs.getString("nome"));

                sql = "SELECT categoria_idcategoria1 FROM categoria_has_categoria WHERE categoria_idcategoria = '" + categoria.getId() + "';";
                readOp = new ReadOperation(sql);
                rs = executor.executeOperation(readOp).getResultSet();
                while (rs.next()){
                    categoria.add(findById(rs.getInt("categoria_idcategoria1")));
                }
                categorie.add(categoria);
            }
            return categorie;
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
    public int add(ICategoria categoria) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "INSERT INTO myShop.categoria (nome, tipo) VALUES ('" + categoria.getNome() + "', '" + categoria.getTipo() + "');";
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
    public int removeById(int id) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "DELETE FROM myShop.categoria_has_categoria WHERE categoria_idcategoria = '" + id + "', OR categoria_idcategoria1 = '" + id + "';";
        IDbOperation writeOp = new WriteOperation(sql);
        rs = executor.executeOperation(writeOp).getResultSet();

        sql = "DELETE FROM myShop.categoria WHERE idcategoria = '" + id + "';";
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
    public int update(ICategoria categoria) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "UPDATE myShop.categoria SET nome = '" + categoria.getNome() + "' WHERE idcategoria = '" + categoria.getId() + "';";
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
    public ICategoria findById(int id) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT idcategoria, nome, tipo FROM myShop.categoria WHERE idcategoria = '" + id + "';";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1) {
                CategoriaFactoryProvider.CategoriaFactoryType type = (CategoriaFactoryProvider.CategoriaFactoryType) rs.getObject("tipo");
                CategoriaAbstractFactory factory = CategoriaFactoryProvider.getFactory(type);
                categoria = factory.crea();

                categoria.setId(rs.getInt("idcategoria"));
                categoria.setNome(rs.getString("nome"));

                sql = "SELECT categoria_idcategoria1 FROM categoria_has_categoria WHERE categoria_idcategoria = '" + categoria.getId() + "';";
                readOp = new ReadOperation(sql);
                rs = executor.executeOperation(readOp).getResultSet();
                while (rs.next()){
                    categoria.add(findById(rs.getInt("categoria_idcategoria1")));
                }
            }
            return categoria;
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
    public int addSubCategoria(ICategoria categoria, ICategoria subcategoria) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "INSERT INTO myShop.categoria_has_categoria (categoria_idcategoria, categoria_idcategoria1) VALUES ('" + categoria.getId() + "', '" + subcategoria.getId() + "');";
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
    public int addSuperCategoria(ICategoria categoria, ICategoria superCategoria) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "INSERT INTO myShop.categoria_has_categoria (categoria_idcategoria, categoria_idcategoria1) VALUES ('" + superCategoria.getId() + "', '" + categoria.getId() + "');";
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
    public int removeSubCategoria(ICategoria categoria, ICategoria subcategoria) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "DELETE FROM myShop.categoria_has_categoria WHERE categoria_idcategoria = '" + categoria.getId() + "' AND categoria_idcategoria1 = '" + subcategoria.getId() + "';";
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
    public int removeSuperCategoria(ICategoria categoria, ICategoria superCategoria) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "DELETE FROM myShop.categoria_has_categoria WHERE categoria_idcategoria = '" + superCategoria.getId() + "' AND categoria_idcategoria1 = '" + categoria.getId() + "';";
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

}
