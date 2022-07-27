package Test.DAO;

import DAO.*;
import Model.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ProdottoCompositoDAOTest {
    @Test
    public void findByNameTest(){
        IArticolo prodottoComposito = ProdottoCompositoDAO.getInstance().findByName("altro set di prova");
        System.out.println(prodottoComposito);
        Assert.assertTrue(prodottoComposito instanceof ProdottoComposito);
    }

    @Test
    public void findAllTest(){
        ArrayList<IArticolo> prodottiCompositi = ProdottoCompositoDAO.getInstance().findAll();
        Assert.assertTrue(prodottiCompositi.size() >= 1);
        for (IArticolo p : prodottiCompositi)
            System.out.println(p);
    }

    @Test
    public void findByIdTest(){
        IArticolo prodottoComposito = ProdottoCompositoDAO.getInstance().findById(13);
        System.out.println(prodottoComposito);
        Assert.assertTrue(prodottoComposito instanceof ProdottoComposito);
    }

    @Test
    public void addTest(){
        ArticoloFactoryProvider.ArticoloType type = ArticoloFactoryProvider.ArticoloType.PRODOTTO_COMPOSITO;
        ArticoloAbstractFactory factory = ArticoloFactoryProvider.getFactory(type);
        IArticolo articolo = factory.crea();
        ProdottoComposito prodottoComposito = (ProdottoComposito) articolo;

        articolo.setNome("altro set di prova");
        articolo.setDescrizione("proviamo se questo funziona");
        prodottoComposito.setProduttore(ProduttoreDAO.getInstance().findById(5));
        articolo.setCategoria(CategoriaDAO.getInstance().findById(1));
        prodottoComposito.setCollocazione(CollocazioneDAO.getInstance().findById(1));

        prodottoComposito.add((IProdotto) ProdottoDAO.getInstance().findById(1));
        prodottoComposito.add((IProdotto) ProdottoDAO.getInstance().findById(2));

        prodottoComposito.setPrezzo(prodottoComposito.getPrezzo());

        System.out.println(prodottoComposito);

        ProdottoCompositoDAO.getInstance().add(articolo);

    }

    @Test
    public void addProdottoTest(){
        ProdottoComposito prodottoComposito = (ProdottoComposito) ProdottoCompositoDAO.getInstance().findById(13);
        System.out.println(prodottoComposito);
        ProdottoCompositoDAO.getInstance().addProdotto(prodottoComposito, (IProdotto) ProdottoDAO.getInstance().findById(1));
    }

    @Test
    public void getProdottiTest(){
        ProdottoComposito prodottoComposito = (ProdottoComposito) ProdottoCompositoDAO.getInstance().findById(13);
        List<IProdotto> prodotti = ProdottoCompositoDAO.getInstance().getProdotti(prodottoComposito);
        Assert.assertTrue(prodotti.size() >= 1);
        prodottoComposito.add(prodotti);
        System.out.println(prodottoComposito);
    }

    @Test
    public void removeProdottoTest(){
        ProdottoCompositoDAO.getInstance().removeProdotto(ProdottoCompositoDAO.getInstance().findById(13), (IProdotto) ProdottoDAO.getInstance().findById(1));
    }

    @Test
    public void updateTest(){
        ProdottoComposito prodottoComposito = (ProdottoComposito) ProdottoCompositoDAO.getInstance().findById(13);
        prodottoComposito.setNome("alla fine funzionava");

        ProdottoCompositoDAO.getInstance().update(prodottoComposito);
    }

    @Test
    public void removeByIdTest(){
        ProdottoCompositoDAO.getInstance().removeById(13);
    }
}
