package Test;

import Model.*;
import org.junit.Assert;
import org.junit.Test;

public class ListaAcquistoFactoryTest {
    @Test
    public void getListaAcquistoClienteFactoryTest(){
        ListaAcquistoFactory.ListaAcquistoType type = ListaAcquistoFactory.ListaAcquistoType.CLIENTE;
        ListaAcquistoFactory factory = new ListaAcquistoFactory();
        ListaAcquisto listaAcquisto = factory.getListaAcquistoFactory(type);

        Assert.assertNotNull(listaAcquisto);
        Assert.assertTrue(listaAcquisto instanceof ListaAcquistoCliente);
    }
}
