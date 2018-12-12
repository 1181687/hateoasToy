package sprint0.Model;

import java.util.ArrayList;
import java.util.List;

public class ListaAG {

    private List<AreaGeografica> mListaAG;

    public ListaAG() {
        this.mListaAG = new ArrayList<>();
    }

    public ListaAG(List<AreaGeografica> mListaAG) {
        this.mListaAG = new ArrayList<>();
    }

    public List<AreaGeografica> getmListaAG() {
        return mListaAG;
    }

    public boolean adicionarAreaGeoALista(AreaGeografica AG) {
        if (!(mListaAG.contains(AG))) {
            mListaAG.add(AG);
            return true;
        }
        return false;
    }

}