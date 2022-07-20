package Model;

import java.util.ArrayList;
import java.util.List;

public class CategoriaProdotto implements ICategoria{
    private String nome;
    private List<CategoriaProdotto> sottocategorie;
    private int id;

    public CategoriaProdotto() {
    }

    public CategoriaProdotto(String nome) {
        this.nome = nome;
        sottocategorie = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public List<CategoriaProdotto> getSottocategorie() {
        return sottocategorie;
    }

    public void add(CategoriaProdotto categoria){
        sottocategorie.add(categoria);
    }

    public void add(List<CategoriaProdotto> categorie){
        sottocategorie.addAll(categorie);
    }


    @Override
    public int getId() {
        return id;
    }
}
