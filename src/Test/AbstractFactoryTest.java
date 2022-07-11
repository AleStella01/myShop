package Test;

import Business.AbstractFactory.*;
import Model.CategoriaProdotto;
import Model.composite.Prodotto;
import org.junit.Assert;
import org.junit.Test;

public class AbstractFactoryTest {

    @Test
    public void getFactoryTest(){

        FactoryProvider.FactoryType type = FactoryProvider.FactoryType.PRODOTTO;
        AbstractFactory factory = FactoryProvider.getFactory(type);
        Assert.assertTrue(factory instanceof ProdottoFactory);

        IArticolo art = factory.crea();
        ICategoria cat = factory.creaCategoria();

        Assert.assertTrue(art instanceof Prodotto);
        Assert.assertTrue(cat instanceof CategoriaProdotto);

    }
}
