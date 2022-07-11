package Business.Bridge;

import Model.ListaAcquisto;
import Model.composite.IProdotto;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class DocumentoListaAcquisto extends Documento {

    private ListaAcquisto lista;

    public DocumentoListaAcquisto(ListaAcquisto lista, PdfAPI pdfAPI) {
        super(pdfAPI);
        this.lista = lista;
    }

    @Override
    public void invia(String indirizzo) {

        List<IProdotto> prodotti = lista.getProdotti();
        String text = "";

        Iterator<IProdotto> i = prodotti.iterator();
        while(i.hasNext()) {
            IProdotto p = i.next();
            text += p.getNome()+", ";
        }

        try {
            File tempFile = File.createTempFile("myshop", ".pdf");
            System.out.println(tempFile);
            pdfAPI.creaPdf(text, tempFile.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
