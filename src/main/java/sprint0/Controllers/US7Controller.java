package sprint0.Controllers;

import sprint0.Model.AreaGeografica;
import sprint0.Model.ListaAG;

public class US7Controller {
    private ListaAG mLista;

    public US7Controller(ListaAG listaAG) {
        this.mLista = listaAG;
    }

    public int obterTamanhoLista() {
        return mLista.getTamanhoLista();
    }

    public String nomeAGNaLista(int posicao){
        return mLista.getNomeAGNaLista(posicao);
    }

    public String tipoAGNaLista(int posicao){
        return mLista.getTipoAGNaLista(posicao);
    }

    public double latitudeAGNaLista(int posicao){
        return mLista.getLatitudeAGNaLista(posicao);
    }

    public double longitudeAGNaLista(int posicao){
        return mLista.getLongitudeAGNaLista(posicao);
    }

    public String nomeAreaInseridaEmAGNaLista(int posicao){
        return mLista.getNomeAreaInseridaEmAG(posicao);
    }

    public String tipoAreaInseridaEmAGNaLista(int posicao){
        return mLista.getTipoAreaInseridaEmAG(posicao);
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