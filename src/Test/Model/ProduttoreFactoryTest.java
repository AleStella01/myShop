package Test.Model;

import Model.*;
import org.junit.Assert;
import org.junit.Test;

public class ProduttoreFactoryTest {
    @Test
    public void getProduttoreMobiliFactoryTest(){
        ProduttoreFactoryProvider.ProduttoreType type = ProduttoreFactoryProvider.ProduttoreType.PRODUTTORE_MOBILI;
        ProduttoreAbstractFactory factory = ProduttoreFactoryProvider.getFactory(type);
        Assert.assertTrue(factory instanceof ProduttoreMobiliFactory);

        IProduttore produttore = factory.crea();
        Assert.assertTrue(produttore instanceof ProduttoreMobili);
    }
}
