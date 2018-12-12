package sprint0.Controllers;

import sprint0.Model.ListaTiposAG;
import sprint0.Model.TipoAreaGeo;

import java.util.ArrayList;
import java.util.List;

public class US2Controller {

    private ListaTiposAG listaTAG;


    public US2Controller(ListaTiposAG listaTAG) {
        this.listaTAG = listaTAG;
    }


    public List<String> getListaDosTiposDeAG (){
        List<String> listaFinal = new ArrayList<>();
       
        for (TipoAreaGeo objecto: listaTAG.getmListaTAG()){
                listaFinal.add(objecto.getmTipoAreaGeo());
        }
        return listaFinal;
    }
}
