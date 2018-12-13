package sprint0.Model;

import java.util.ArrayList;
import java.util.List;

public class ListaAG {

    private List<AreaGeografica> mListaAG;

    public ListaAG() {
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

    public AreaGeografica getAreaGeografica (AreaGeografica areaGeografica) {
        for (AreaGeografica area : mListaAG) {
            if (area.equals(areaGeografica)) {
                return area;
            }
        }
        return null;
    }

    public String getNomeAreaGeograficaPorIndice (int posicao) {
        return this.mListaAG.get(posicao).getmNomeAreaGeo();
    }

    public ArrayList<String> getListaAGPorTipo(String tipo) {
        ArrayList<String> listaAGMesmoTipo = new ArrayList<>();
        for (AreaGeografica areaGeo : this.getmListaAG()) {
            if (areaGeo.getmTipoAreaGeo().umTipoAreaGeoEIgualAOutra(tipo)) {
                listaAGMesmoTipo.add(areaGeo.getmNomeAreaGeo());
            }
        }
        return listaAGMesmoTipo;
    }

    public AreaGeografica getAreaGeograficaNaListaApresentada(int opcaoSelecionada){
        return mListaAG.get(opcaoSelecionada-1);
    }

    public boolean verificarSeAGNaoTemAreaInserida(AreaGeografica area) {
        return area.getmAreaInseridaEm() == null;
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

    public String getNomeAreaInseridaEmAG (int posicao){
        return mListaAG.get(posicao).getmAreaInseridaEm().getmNomeAreaGeo();
    }

    public String getTipoAreaInseridaEmAG (int posicao){
        return mListaAG.get(posicao).getmAreaInseridaEm().getmTipoAreaGeo().getmTipoAreaGeo();
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