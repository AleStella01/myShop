package Test.Model;

import Model.*;
import org.junit.Assert;
import org.junit.Test;

public class ArticoloFactoryTest {
    @Test
    public void getProdottoFactoryTest(){
        ArticoloFactoryProvider.ArticoloType type = ArticoloFactoryProvider.ArticoloType.PRODOTTO;
        ArticoloAbstractFactory factory = ArticoloFactoryProvider.getFactory(type);
        Assert.assertTrue(factory instanceof ProdottoFactory);

        IArticolo articolo = factory.crea();
        Assert.assertTrue(articolo instanceof Prodotto);

        ICategoria categoria = factory.creaCategoria();
        Assert.assertTrue(categoria instanceof CategoriaProdotto);
    }

    @Test
    public void getProdottoCompositoFactoryTest(){
        ArticoloFactoryProvider.ArticoloType type = ArticoloFactoryProvider.ArticoloType.PRODOTTO_COMPOSITO;
        ArticoloAbstractFactory factory = ArticoloFactoryProvider.getFactory(type);
        Assert.assertTrue(factory instanceof ProdottoCompositoFactory);

        IArticolo articolo = factory.crea();
        Assert.assertTrue(articolo instanceof ProdottoComposito);

        ICategoria categoria = factory.creaCategoria();
        Assert.assertTrue(categoria instanceof CategoriaProdotto);
    }

    @Test
    public void getServizioFactoryTest(){
        ArticoloFactoryProvider.ArticoloType type = ArticoloFactoryProvider.ArticoloType.SERVIZIO;
        ArticoloAbstractFactory factory = ArticoloFactoryProvider.getFactory(type);
        Assert.assertTrue(factory instanceof ServizioFactory);

        IArticolo articolo = factory.crea();
        Assert.assertTrue(articolo instanceof Servizio);

        ICategoria categoria = factory.creaCategoria();
        Assert.assertTrue(categoria instanceof CategoriaServizio);
    }
}
