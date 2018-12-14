package sprint0.Controllers;

import sprint0.Model.ListaTiposAG;
import java.util.List;

public class US2Controller {

    private ListaTiposAG mListaTAG;


    public US2Controller(ListaTiposAG listaTAG) {
        this.mListaTAG = listaTAG;
    }


    public List<String> getListaTiposDeAG (){
        return mListaTAG.getListaDosTiposDeAG();
    }
}
