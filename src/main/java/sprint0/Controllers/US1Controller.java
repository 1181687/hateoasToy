package sprint0.Controllers;

import sprint0.Model.ListaTiposAG;
import sprint0.Model.TipoAreaGeo;


public class US1Controller {
    private ListaTiposAG mListaTAG;

    public US1Controller(ListaTiposAG listaTAG) {
        this.mListaTAG = listaTAG;
    }

    public boolean novoTAG(String tipoTAG){
        TipoAreaGeo novoTAG = mListaTAG.novoTipoAG(tipoTAG);
        return mListaTAG.adicionarElementoALista(novoTAG);
    }

    public ListaTiposAG getListaTAG() {
        return mListaTAG;
    }
}

