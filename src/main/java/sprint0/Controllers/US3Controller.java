package sprint0.Controllers;

import sprint0.Model.*;

public class US3Controller {
    private ListaAG mListaAG;

    public US3Controller(ListaAG listaAG) {
        this.mListaAG = listaAG;
    }

    public boolean adicionarNovaAG(AreaGeografica novaAG) {
        return mListaAG.adicionarAreaGeoALista(novaAG);
    }

    public ListaAG getListaAG() {
        return mListaAG;
    }
}
