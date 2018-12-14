package sprint0.Controllers;

import sprint0.Model.AreaGeografica;
import sprint0.Model.ListaAG;

public class US8Controller {
    private ListaAG mLista;

    public US8Controller(ListaAG listaAG) {
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

    public boolean verificarSeAGEstaContidaDiretaOuIndiretamenteNoutraAG(int opcaoSelecionada1, int opcaoSelecionada2){
        return mLista.verificarSeAGEstaContidaNoutra(opcaoSelecionada1,opcaoSelecionada2);
    }
}
