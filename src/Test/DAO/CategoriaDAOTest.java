package Test.DAO;

import DAO.CategoriaDAO;
import Model.CategoriaProdotto;
import Model.ICategoria;
import org.junit.Assert;
import org.junit.Test;

public class CategoriaDAOTest {
    @Test
    public void findByNameTest(){
        ICategoria categoria = CategoriaDAO.getInstance().findByName("INTERNI");
        System.out.println(categoria);
        Assert.assertTrue(categoria instanceof CategoriaProdotto);
    }
}
