package Business;

import Model.IProdotto;
import Model.ListaAcquisto;
import Model.Prodotto;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DocumentoListaAcquisto extends Documento{
    private ListaAcquisto listaAcquisto;

    public DocumentoListaAcquisto(PdfAPI pdfAPI, ListaAcquisto listaAcquisto) {
        super(pdfAPI);
        this.listaAcquisto = listaAcquisto;
    }



    @Override
    public void invia(String indirizzo) {
        Map<Prodotto, Integer> prodotti = listaAcquisto.getProdotti();
        String text = "";

        Iterator<Prodotto> i = prodotti.keySet().iterator();
        while (i.hasNext()){
            IProdotto p = i.next();
            text += p.getNome() + ", ";
        }

        try {
            File tempFile = File.createTempFile("myShop", ".pdf");
            System.out.println(tempFile);
            pdfAPI.creaPdf(text, tempFile.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
