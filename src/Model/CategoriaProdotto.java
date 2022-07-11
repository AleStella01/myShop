package Model;

import Business.AbstractFactory.ICategoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriaProdotto implements ICategoria {

    private String nome;

    private List<CategoriaProdotto> sottocategorie;

    public CategoriaProdotto() {
    }

    public CategoriaProdotto(String nome) {
        this.nome = nome;
        this.sottocategorie = new ArrayList<CategoriaProdotto>();
    }

    public List<CategoriaProdotto> getSottocategorie() {
        return sottocategorie;
    }

    public void aggiungiSottocategoria(CategoriaProdotto c) {
        sottocategorie.add(c);
    }

    public void aggiungiSottocategoria(List<CategoriaProdotto> c) {
        sottocategorie.addAll(c);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
