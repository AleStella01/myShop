package Model;

public interface IProdotto {
    Float getPrezzo();
    String getNome();
    IProduttore getProduttore();
    CollocazioneProdotto getCollocazione();
    int getId();
}
