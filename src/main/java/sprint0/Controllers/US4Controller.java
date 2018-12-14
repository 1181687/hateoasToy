package sprint0.Controllers;


import sprint0.Model.ListaAG;
import sprint0.Model.ListaTiposAG;

import java.util.ArrayList;
import java.util.List;

public class US4Controller {

    private ListaAG mListaAG;
    private ListaTiposAG mListaTAG;

    public US4Controller(ListaAG listaAG, ListaTiposAG listaTAG) {
        this.mListaAG = listaAG;
        this.mListaTAG = listaTAG;
    }

    public List<String> getListaAGPorTipo(String tipo) {
        return this.mListaAG.getListaAGPorTipo(tipo);
    }

    public List<String> getListaDosTiposDeAG(){
        return mListaTAG.getListaDosTiposDeAG();
    }



}