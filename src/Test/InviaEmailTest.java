package Test;

import Business.Documento;
import Business.DocumentoListaAcquisto;
import Business.PdfAPI;
import Business.PdfBoxAPI;
import DAO.ListaAcquistoDAO;
import org.junit.Test;

public class InviaEmailTest {
    @Test
    public void inviaTest(){
        PdfAPI api = new PdfBoxAPI();

        api.creaPdf("ciao", "lista");
        Documento documento = new DocumentoListaAcquisto(api, ListaAcquistoDAO.getInstance().findById(1));
        documento.invia("nicdepascali@gmail.com");
    }
}
