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
        US1Controller ctrl1 = new US1Controller(listaTAG);
        ListaTiposAG obterListaTAG = ctrl1.getListaTAG();
        for (TipoAreaGeo objecto: obterListaTAG.getmListaTAG()){
                listaFinal.add(objecto.getmTipoAreaGeo());
        }
        return listaFinal;
    }
}
