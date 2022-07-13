package Model;

public abstract class Collocazione {
    protected String corsia;
    protected int scaffale;

    public Collocazione() {
        this.corsia = null;
        this.scaffale = 0;
    }

    public Collocazione(String corsia, int scaffale) {
        this.corsia = corsia;
        this.scaffale = scaffale;
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
}
