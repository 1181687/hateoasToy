package sprint0.Model;

import java.util.ArrayList;
import java.util.List;

public class ListaTiposAG {
    private List<TipoAreaGeo> mListaTAG = new ArrayList<>();

    public ListaTiposAG(List<TipoAreaGeo> mListaTAG) {
        this.mListaTAG = mListaTAG;
    }

    public ListaTiposAG() {
    }

    public List<TipoAreaGeo> getmListaTAG() {
        return mListaTAG;
    }

    public boolean adicionarElementoALista(TipoAreaGeo tipoAG) {
        if (!(mListaTAG.contains(tipoAG))) {
            mListaTAG.add(tipoAG);
            return true;
        }
        return false;
    }

    public TipoAreaGeo novoTipoAG(String novoTipo){
        return new TipoAreaGeo(novoTipo);
    }
}
