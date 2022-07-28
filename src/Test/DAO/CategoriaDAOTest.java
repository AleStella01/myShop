package Test.DAO;

import DAO.CategoriaDAO;
import Model.CategoriaAbstractFactory;
import Model.CategoriaFactoryProvider;
import Model.CategoriaProdotto;
import Model.ICategoria;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class CategoriaDAOTest {
    @Test
    public void findByNameTest(){
        ICategoria categoria = CategoriaDAO.getInstance().findByName("INTERNI");
        System.out.println(categoria);
        System.out.println("SOTTOCATEGORIE:");
        for (ICategoria c : categoria.getSottocategorie())
            System.out.println(c);
        Assert.assertTrue(categoria instanceof CategoriaProdotto);
    }

    @Test
    public void findByIdTest(){
        ICategoria categoria = CategoriaDAO.getInstance().findById(1);
        System.out.println(categoria);
        System.out.println("SOTTOCATEGORIE:");
        for (ICategoria c : categoria.getSottocategorie())
            System.out.println(c);
        Assert.assertTrue(categoria instanceof CategoriaProdotto);
    }

    @Test
    public void getSubCategorieTest(){
        ICategoria categoria = CategoriaDAO.getInstance().findById(1);
        ArrayList<ICategoria> sottocategorie = CategoriaDAO.getInstance().getSubCategorie(categoria);
        categoria.add(sottocategorie);
        System.out.println(categoria);
    }

    @Test
    public void findAllTest(){
        ArrayList<ICategoria> categorie = CategoriaDAO.getInstance().findAll();
        Assert.assertTrue(categorie.size() >= 1);
        for (ICategoria c : categorie)
            System.out.println(c);
    }

    @Test
    public void addTest(){
        CategoriaFactoryProvider.CategoriaFactoryType type = CategoriaFactoryProvider.CategoriaFactoryType.PRODOTTO;
        CategoriaAbstractFactory factory = CategoriaFactoryProvider.getFactory(type);
        ICategoria categoria = factory.crea();

        categoria.setNome("Arredamento");

        CategoriaDAO.getInstance().add(categoria);
    }

    @Test
    public void removeByIdTest(){
        CategoriaDAO.getInstance().removeById(7);
    }

    @Test
    public void updateTest(){
        CategoriaFactoryProvider.CategoriaFactoryType type = CategoriaFactoryProvider.CategoriaFactoryType.SERVIZIO;
        CategoriaAbstractFactory factory = CategoriaFactoryProvider.getFactory(type);
        ICategoria categoria = factory.crea();

        categoria.setNome("montaggio");
        categoria.setId(8);

        CategoriaDAO.getInstance().update(categoria);
    }
}
