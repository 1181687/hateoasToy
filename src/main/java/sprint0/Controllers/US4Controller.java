package sprint0.Controllers;


import sprint0.Model.AreaGeografica;
import sprint0.Model.ListaAG;
import java.util.ArrayList;

public class US4Controller {

    private ListaAG mListaAG;

    public US4Controller(ListaAG listaAG) {
        this.mListaAG = listaAG;
    }

    public ArrayList<String> getListaAGPorTipo(String tipo) {
        ArrayList<String> listaAGMesmoTipo = new ArrayList<>();
        for (AreaGeografica areaGeo : mListaAG.getmListaAG()) {
            if (areaGeo.getmTipoAreaGeo().umTipoAreaGeoEIgualAOutra(tipo)) {
                listaAGMesmoTipo.add(areaGeo.getmNomeAreaGeo());
            }
        }
        return listaAGMesmoTipo;
    }
}






