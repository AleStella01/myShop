package View.ViewModel;

import javax.swing.*;

public class RigaCatalogo {

    private int idProdotto;
    private String nomeProdotto;
    private String nomeProduttore;
    private float prezzo;
    private String nomeCategoria;
    private int disponibilita;
    private Boolean selezionato;
    private int quantitaInserita;
    private ImageIcon image;
    private String corsia;
    private int scaffale;
    private String prodottoComposito;
    private String prodotti;


    public Boolean getSelezionato() {
        return selezionato;
    }

    public void setSelezionato(Boolean selezionato) {
        this.selezionato = selezionato;
    }

    public int getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(int idProdotto) {
        this.idProdotto = idProdotto;
    }

    public String getNomeProdotto() {
        return nomeProdotto;
    }

    public void setNomeProdotto(String nomeProdotto) {
        this.nomeProdotto = nomeProdotto;
    }

    public String getNomeProduttore() {
        return nomeProduttore;
    }

    public void setNomeProduttore(String nomeProduttore) {
        this.nomeProduttore = nomeProduttore;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public int getDisponibilita() {
        return disponibilita;
    }

    public void setDisponibilita(int disponibilita) {
        this.disponibilita = disponibilita;
    }

    public int getQuantitaInserita() {
        return quantitaInserita;
    }

    public void setQuantitaInserita(int quantitaInserita) {
        this.quantitaInserita = quantitaInserita;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public String getCorsia() {
        return corsia;
    }

    public void setCorsia(String corsia) {
        this.corsia = corsia;
    }

    public int getScaffale() {
        return scaffale;
    }

    public void setScaffale(int scaffale) {
        this.scaffale = scaffale;
    }

    public String getProdottoComposito() {
        return prodottoComposito;
    }

    public void setProdottoComposito(String prodottoComposito) {
        this.prodottoComposito = prodottoComposito;
    }

    public String getProdotti() {
        return prodotti;
    }

    public void setProdotti(String prodotti) {
        this.prodotti = prodotti;
    }
}
