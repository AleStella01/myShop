package Model;

public class ListaAcquistoFactory {
    public enum ListaAcquistoType{CLIENTE}

    public ListaAcquisto getListaAcquistoFactory(ListaAcquistoType type){
        if (type == null){
            return null;
        }

        switch (type){
            case CLIENTE:
                return new ListaAcquistoCliente();
            default:
                return null;
        }
    }
}
