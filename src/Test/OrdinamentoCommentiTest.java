package Test;

import Business.Strategy.CommentiMiglioriStrategy;
import Business.Strategy.CommentiRecentiStrategy;
import Business.Strategy.IOrdinamentoCommentoStrategy;
import Business.Strategy.OrdinamentoCommenti;
import Model.Commento;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrdinamentoCommentiTest {

    List<Commento> commentiList;

    @Before
    public void setUp() throws Exception {

        //fixture
        commentiList = new ArrayList<Commento>();

        Commento c1=new Commento();
        c1.setId(1);
        c1.setPunteggio(Commento.Punteggio.MEDIOCRE);
        Date data1 = new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-20");
        c1.setData(data1);

        Commento c2=new Commento();
        c2.setId(2);
        c2.setPunteggio(Commento.Punteggio.SCARSO);
        Date data2 = new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-27");
        c2.setData(data2);

        Commento c3=new Commento();
        c3.setId(3);
        c3.setPunteggio(Commento.Punteggio.ECCELLENTE);
        Date data3 = new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-22");
        c3.setData(data3);

        commentiList.add(c1);
        commentiList.add(c2);
        commentiList.add(c3);

    }

    @After
    public void tearDown() {
        commentiList = null;
    }

    @Test
    public void ordinaTest() {

        OrdinamentoCommenti ordinamentoCommenti = new OrdinamentoCommenti(commentiList);
        IOrdinamentoCommentoStrategy strategy = new CommentiRecentiStrategy();
        ordinamentoCommenti.setOrdinamentoCommentoStrategy(strategy);
        ordinamentoCommenti.ordina();

        //verifichiamo che ha ordinato come ci aspettavamo
        Assert.assertEquals(commentiList.get(0).getId(), 2);
        Assert.assertEquals(commentiList.get(1).getId(), 3);
        Assert.assertEquals(commentiList.get(2).getId(), 1);

        strategy = new CommentiMiglioriStrategy();
        ordinamentoCommenti.setOrdinamentoCommentoStrategy(strategy);
        ordinamentoCommenti.ordina();
        Assert.assertEquals(commentiList.get(0).getId(), 3);
        Assert.assertEquals(commentiList.get(1).getId(), 1);
        Assert.assertEquals(commentiList.get(2).getId(), 2);

    }
}
