package Business.Strategy;

import Model.Commento;

import java.util.Comparator;
import java.util.List;

public class CommentiMiglioriStrategy implements IOrdinamentoCommentoStrategy{

    @Override
    public void ordina(List<Commento> listaCommenti) {

        listaCommenti.sort(new Comparator<Commento>() {
            @Override
            public int compare(Commento o1, Commento o2) {

                // restituisce 0 se o1.punteggio = o2.punteggio
                // restituisce n>0 se o1.punteggio < o2.punteggio
                // restituisce n<0 se o1.punteggio > o2.punteggio

                if(o1.getPunteggio() == o2.getPunteggio()) return 0;

                int punteggio1=0, punteggio2=0;

                switch(o1.getPunteggio()) {
                    case SCARSO: punteggio1 = 1; break;
                    case MEDIOCRE: punteggio1 = 2; break;
                    case BUONO: punteggio1 = 3; break;
                    case ECCELLENTE: punteggio1 = 4;
                }

                switch(o2.getPunteggio()) {
                    case SCARSO: punteggio2 = 1; break;
                    case MEDIOCRE: punteggio2 = 2; break;
                    case BUONO: punteggio2 = 3; break;
                    case ECCELLENTE: punteggio2 = 4;
                }

                return punteggio2 - punteggio1;

            }
        });
    }
}
