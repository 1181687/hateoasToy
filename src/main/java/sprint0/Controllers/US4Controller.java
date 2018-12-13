package sprint0.Controllers;


import sprint0.Model.ListaAG;
import java.util.ArrayList;

public class US4Controller {

    private ListaAG mListaAG;

    public US4Controller(ListaAG listaAG) {
        this.mListaAG = listaAG;
    }

    public ArrayList<String> getListaAGPorTipo(String tipo) {
        return this.mListaAG.getListaAGPorTipo(tipo);
    }

}






