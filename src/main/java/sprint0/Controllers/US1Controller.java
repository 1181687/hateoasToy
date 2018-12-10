package sprint0.Controllers;

import sprint0.Model.ListaTiposAG;
import sprint0.Model.TipoAreaGeo;


public class US1Controller {
    ListaTiposAG listaTAG;

    public US1Controller(ListaTiposAG listaTAG) {
        this.listaTAG = listaTAG;
    }

    public boolean novoTAG(String tipoTAG){
        TipoAreaGeo novoTAG = listaTAG.novoTipoAG(tipoTAG);
        return listaTAG.adicionarElementoALista(novoTAG);
    }
}
