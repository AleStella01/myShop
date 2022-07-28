package Test.DAO;

import DAO.*;
import Model.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class ListaAcquistoDAOTest {
    @Test
    public void findByUserTest(){
        IUtente u = UtenteDAO.getInstance().findById(1);
        List<ListaAcquisto> liste = ListaAcquistoDAO.getInstance().findByUser(u);

        Assert.assertTrue(liste.size() >= 1);

        for (ListaAcquisto l : liste)
            System.out.println(l);
    }

    @Test
    public void findAllTest(){
        List<ListaAcquisto> liste = ListaAcquistoDAO.getInstance().findAll();
        Assert.assertTrue(liste.size() >= 1);
        for (ListaAcquisto l : liste)
            System.out.println(l);
    }

    @Test
    public void findByIdTest(){
        ListaAcquisto lista = ListaAcquistoDAO.getInstance().findById(1);
        System.out.println(lista);
        Assert.assertTrue(lista instanceof ListaAcquistoCliente);
    }

    @Test
    public void getProdottiTest(){
        ListaAcquisto lista = ListaAcquistoDAO.getInstance().findById(1);
        Map<IProdotto, Integer> prodotti = new HashMap<>();
        prodotti = ListaAcquistoDAO.getInstance().getProdotti(lista);
        Assert.assertTrue(prodotti.size() >= 1);
        Iterator<Map.Entry<IProdotto, Integer>> iterator = prodotti.entrySet().iterator();

        while (iterator.hasNext()){
            Map.Entry<IProdotto, Integer> entry = iterator.next();
            System.out.println(entry.getKey());
            System.out.println("Quantità: " + entry.getValue());
            System.out.println("-----");
        }
    }

    @Test
    public void getServiziTest(){
        ListaAcquisto lista = ListaAcquistoDAO.getInstance().findById(1);
        List<IArticolo> servizi = ListaAcquistoDAO.getInstance().getServizi(lista);
        Assert.assertTrue(servizi.size() >= 1);
        for (IArticolo s : servizi){
            System.out.println(s);
        }
        System.out.println(lista.getIndirizzoSpedizione());
    }

    @Test
    public void addProdottoTest(){
        IArticolo prodotto = ProdottoDAO.getInstance().findById(1);
        ListaAcquisto lista = ListaAcquistoDAO.getInstance().findById(6);

        System.out.println(lista);

        System.out.println("Lista: " + lista.getId() + ", prodotto: " + prodotto.getId());

        ListaAcquistoDAO.getInstance().addProdotto(lista, prodotto, 3);
    }

    @Test
    public void addServizioTest(){
        ListaAcquisto lista = ListaAcquistoDAO.getInstance().findById(6);
        IArticolo servizio = ServizioDAO.getInstance().findById(2);
        String indirizzo = "ciao";
        ListaAcquistoDAO.getInstance().addServizio(lista, servizio, indirizzo);
    }

    @Test
    public void removeProdottoTest(){
        IArticolo prodotto = ProdottoDAO.getInstance().findById(4);
        ListaAcquisto lista = ListaAcquistoDAO.getInstance().findById(2);

        ListaAcquistoDAO.getInstance().removeProdotto(lista, prodotto);
    }

    @Test
    public void removeServizioTest(){
        ListaAcquisto lista = ListaAcquistoDAO.getInstance().findById(2);
        IArticolo servizio = ServizioDAO.getInstance().findById(1);

        ListaAcquistoDAO.getInstance().removeServizio(lista, servizio);
    }

    @Test
    public void updateTest(){
        ListaAcquisto lista = ListaAcquistoDAO.getInstance().findById(2);
        lista.setCliente(UtenteDAO.getInstance().findById(1));
        ListaAcquistoDAO.getInstance().update(lista);
    }

    @Test
    public void removeTest(){
        ListaAcquistoDAO.getInstance().removeById(2);
    }

    @Test
    public void addTest(){
        ListaAcquistoFactory.ListaAcquistoType type = ListaAcquistoFactory.ListaAcquistoType.CLIENTE;
        ListaAcquistoFactory factory = new ListaAcquistoFactory();
        ListaAcquisto lista = factory.getListaAcquistoFactory(type);

        lista.setCliente(UtenteDAO.getInstance().findById("astella"));
        lista.setPuntoVendita(PuntoVenditaDAO.getInstance().findById(6));

        ListaAcquistoDAO.getInstance().add(lista);
    }
}
