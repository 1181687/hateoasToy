package sprint0.Model;

import java.util.ArrayList;
import java.util.List;

public class ListaAG {

    private List<AreaGeografica> mListaAG;

    public ListaAG() {
        this.mListaAG = new ArrayList<>();
    }

    public ListaAG(List<AreaGeografica> mListaAG) {
        this.mListaAG = new ArrayList<>();
    }

    public List<AreaGeografica> getmListaAG() {
        return mListaAG;
    }

    public boolean adicionarAreaGeoALista(AreaGeografica AG) {
        if (!(mListaAG.contains(AG))) {
            mListaAG.add(AG);
            return true;
        }
        return false;
    }

    public AreaGeografica getAreaGeograficaNaListaApresentada(int opcaoSelecionada){
        return mListaAG.get(opcaoSelecionada-1);
    }

    public boolean verificarSeAGNaoTemAreaInserida(AreaGeografica area) {
        return area.getmAreaInserida() == null;
    }

    public String getNomeAGNaLista (int posicao){
        return mListaAG.get(posicao).getmNomeAreaGeo();
    }

    public String getTipoAGNaLista (int posicao){
        return mListaAG.get(posicao).getmTipoAreaGeo().getmTipoAreaGeo();
    }

    public double getLatitudeAGNaLista (int posicao){
        return mListaAG.get(posicao).getmLocalizacao().getmLatitude();
    }

    public double getLongitudeAGNaLista (int posicao){
        return mListaAG.get(posicao).getmLocalizacao().getmLongitude();
    }

    public int getTamanhoLista(){
        return mListaAG.size();
    }

    public boolean removerAreaGeoALista(AreaGeografica AG) {
        return mListaAG.remove(AG);
    }

    public void adicionarAreaGeoAListaNumaPosicaoEspecifica(int posicao, AreaGeografica AG) {
        mListaAG.add(posicao,AG);
    }
}