package View;

import Business.Bridge.Documento;
import Business.Bridge.DocumentoListaAcquisto;
import Business.Bridge.PdfBoxAPI;
import Model.ListaAcquisto;
import Model.composite.Prodotto;

public class MainClass {

    public static void main(String args[]) {

        /*
        JFrame finestra = new JFrame("Prima finestra");

        finestra.setSize(800, 600);
        finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Buona lezione");

        Container c = finestra.getContentPane();
        c.add(label);

        finestra.setVisible(true);*/

        //new PrimaFinestra();
        //new EsempioFlowLayout();
        //new EsempioGridLayout();


        EsempioGerarchiaLayout finestra=new EsempioGerarchiaLayout();
        finestra.mostraPannelloUtenteNonLoggato();
        ListaAcquisto lista = new ListaAcquisto();

        Prodotto p1 = new Prodotto();
        p1.setNome("prodotto 1");
        Prodotto p2 = new Prodotto();
        p2.setNome("prodotto 2");

        lista.getProdotti().add(p1);
        lista.getProdotti().add(p2);

        Documento listaAcquisto = new DocumentoListaAcquisto(lista, new PdfBoxAPI());

        //prendere l'utente loggato dalla sessione e ottenere la mail dall'oggetto cliente
        listaAcquisto.invia("niccolo.depascali1@unisalento.it");

    }
}
