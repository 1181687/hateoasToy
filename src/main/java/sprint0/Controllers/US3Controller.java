package sprint0.Controllers;

import sprint0.Model.*;

import java.util.List;

public class US3Controller {
    private ListaAG mListaAG;
    private ListaTiposAG mListaTAG;

    public US3Controller(ListaAG listaAG, ListaTiposAG listaTAG) {
        this.mListaAG = listaAG;
        this.mListaTAG = listaTAG;
    }

    public boolean adicionarNovaAG(AreaGeografica novaAG) {
        return mListaAG.adicionarAreaGeoALista(novaAG);
    }

    public ListaAG getListaAG() {
        return mListaAG;
    }

    public List<String> getListaTAG () {
        return mListaTAG.getListaDosTiposDeAG();
    }

}
