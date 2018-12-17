package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
import java.util.List;

public class ListaTiposAG {
    private List<TipoAreaGeo> mListaTAG = new ArrayList<>();

    public ListaTiposAG(List<TipoAreaGeo> mListaTAG) {
        this.mListaTAG = mListaTAG;
    }

    public ListaTiposAG() {
    }

    public List<TipoAreaGeo> getmListaTAG() {
        return mListaTAG;
    }

    public boolean adicionarElementoALista(TipoAreaGeo tipoAG) {
        if (!(mListaTAG.contains(tipoAG))) {
            mListaTAG.add(tipoAG);
            return true;
        }
        return false;
    }

    public TipoAreaGeo novoTipoAG(String novoTipo){
        return new TipoAreaGeo(novoTipo);
    }

    public List<String> getListaDosTiposDeAG () {
        List<String> listaFinal = new ArrayList<>();
        for (TipoAreaGeo objecto : mListaTAG) {
            listaFinal.add(objecto.getNomeDoTipoAreaGeo());
        }
        return listaFinal;
    }

}

