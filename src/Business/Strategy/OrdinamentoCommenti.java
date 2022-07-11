package Business.Strategy;

import Model.Commento;

import java.util.List;

public class OrdinamentoCommenti {

    private List<Commento> listaCommenti;
    private IOrdinamentoCommentoStrategy ordinamentoCommentoStrategy;

    public OrdinamentoCommenti(List<Commento> listaCommenti) {
        this.listaCommenti = listaCommenti;
    }

    public void setOrdinamentoCommentoStrategy(IOrdinamentoCommentoStrategy ordinamentoCommentoStrategy) {
        this.ordinamentoCommentoStrategy = ordinamentoCommentoStrategy;
    }

    public void ordina() {
        ordinamentoCommentoStrategy.ordina(listaCommenti);
    }
}
