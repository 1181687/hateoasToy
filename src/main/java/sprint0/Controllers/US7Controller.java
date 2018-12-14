package sprint0.Controllers;

import sprint0.Model.AreaGeografica;
import sprint0.Model.ListaAG;

public class US7Controller {
    private ListaAG mLista;

    public US7Controller(ListaAG listaAG) {
        this.mLista = listaAG;
    }

    public String getConteudoLista (boolean usarCriterio){
        return mLista.conteudoLista(usarCriterio);
    }

    public AreaGeografica getAGNaListaApresentada(int opcaoSelecionada){
        return mLista.getAreaGeograficaNaListaApresentada(opcaoSelecionada);
    }

    public boolean verSeAGTemAreaInseridaVazia(AreaGeografica area) {
        return mLista.verificarSeAGNaoTemAreaInserida (area);
    }

    public void adicionarAGLista (int posicao, AreaGeografica area){
        mLista.adicionarAreaGeoAListaNumaPosicaoEspecifica(posicao,area);
    }

    public void removerAGLista (AreaGeografica area){
        mLista.removerAreaGeoALista(area);
    }
}