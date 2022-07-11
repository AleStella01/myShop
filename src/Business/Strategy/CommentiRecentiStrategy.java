package Business.Strategy;

import Model.Commento;

import java.util.Comparator;
import java.util.List;

public class CommentiRecentiStrategy implements IOrdinamentoCommentoStrategy {

    @Override
    public void ordina(List<Commento> listaCommenti) {

        listaCommenti.sort(new Comparator<Commento>() {
            @Override
            public int compare(Commento o1, Commento o2) {

                if(o2 == null || o1 == null) return 0;

                return o2.getData().compareTo(o1.getData());
            }
        });

    }
}
