package sprint0.Controllers;

import sprint0.Model.*;

public class US3Controller {
    private ListaAG mListaAG;
    private ListaTiposAG mListaTAG;

    public US3Controller(ListaAG listaAG, ListaTiposAG listaTAG) {
        this.mListaAG = listaAG;
        this.mListaTAG = listaTAG;
    }

    public boolean adicionarNovaAG(AreaGeografica novaAG) {
        return mListaAG.adicionarAreaGeoALista(novaAG);
    }

    public ListaAG getListaAG() {
        return mListaAG;
    }

    public int obterTamanhoLista() {
        return mListaTAG.getTamanhoLista();
    }

    public String getNomeTipoAGNaLista (int posicao) {
        return mListaTAG.getListaDosTiposDeAG().get(posicao);
    }

}
