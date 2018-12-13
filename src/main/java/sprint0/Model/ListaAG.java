package sprint0.Model;

import java.awt.geom.Area;
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
        return mListaAG.get(opcaoSelecionada);
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

    public boolean verificarSeAGEstaContidaNoutra(int opcaoSelecionada1, int opcaoSelecionada2){
        AreaGeografica primeiraAG = mListaAG.get(opcaoSelecionada1);
        AreaGeografica segundaAG = mListaAG.get(opcaoSelecionada2);
        while(!primeiraAG.getmAreaInseridaEm().equals(null)){
            if(!primeiraAG.getmAreaInseridaEm().equals(segundaAG)){
                primeiraAG = primeiraAG.getmAreaInseridaEm();
            }
            else{
                return true;
            }
        }
        return false;
    }
}