package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
import java.util.List;

public class GeoAreaTypeList {
    private List<GeoAreaType> mListaTAG = new ArrayList<>();

    public GeoAreaTypeList(List<GeoAreaType> mListaTAG) {
        this.mListaTAG = mListaTAG;
    }

    public GeoAreaTypeList() {
    }

    public List<GeoAreaType> getmListaTAG() {
        return mListaTAG;
    }

    public boolean adicionarElementoALista(GeoAreaType tipoAG) {
        if (!(mListaTAG.contains(tipoAG))) {
            mListaTAG.add(tipoAG);
            return true;
        }
        return false;
    }

    public GeoAreaType novoTipoAG(String novoTipo) {
        return new GeoAreaType(novoTipo);
    }

    public List<String> getListaDosTiposDeAG () {
        List<String> listaFinal = new ArrayList<>();
        for (GeoAreaType objecto : mListaTAG) {
            listaFinal.add(objecto.getNomeDoTipoAreaGeo());
        }
        return listaFinal;
    }

}

