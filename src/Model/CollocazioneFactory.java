package Model;

public class CollocazioneFactory {
    public enum CollocazioneType{MAGAZZINO}

    public Collocazione getCollocazione(CollocazioneType type) {
        if (type == null) {
            return null;
        }

        switch (type){
            case MAGAZZINO:
                return new CollocazioneProdotto();
            default:
                return null;
        }
    }
}
