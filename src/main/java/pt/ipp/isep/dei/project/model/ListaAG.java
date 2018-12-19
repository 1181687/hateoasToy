package pt.ipp.isep.dei.project.model;

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

    public AreaGeografica getAreaGeografica(AreaGeografica areaGeografica) {
        for (AreaGeografica area : mListaAG) {
            if (area.equals(areaGeografica)) {
                return area;
            }
        }
        return null;
    }

    public String getNomeAreaGeograficaPorIndice(int posicao) {
        return this.mListaAG.get(posicao).getmNomeAreaGeo();
    }

    public List<String> getListaAGPorTipo(String tipo) {
        List<String> listaAGMesmoTipo = new ArrayList<>();
        for (AreaGeografica areaGeo : mListaAG) {
            if (areaGeo.getmTipoAreaGeo().umTipoAreaGeoEIgualAOutra(tipo)) {
                listaAGMesmoTipo.add(areaGeo.getmNomeAreaGeo());
            }
        }
        return listaAGMesmoTipo;
    }

    public AreaGeografica getAreaGeograficaNaListaApresentada(int opcaoSelecionada) {
        return mListaAG.get(opcaoSelecionada);
    }

    public boolean verificarSeAGNaoTemAreaInserida(AreaGeografica area) {
        return area.getmAreaInseridaEm() == null;
    }

    public boolean removerAreaGeoALista(AreaGeografica AG) {
        return mListaAG.remove(AG);
    }

    public void adicionarAreaGeoAListaNumaPosicaoEspecifica(int posicao, AreaGeografica AG) {
        mListaAG.add(posicao, AG);
    }

    public boolean verificarSeAGEstaContidaNoutra(int opcaoSelecionada1, int opcaoSelecionada2) {
        AreaGeografica primeiraAG = mListaAG.get(opcaoSelecionada1);
        AreaGeografica segundaAG = mListaAG.get(opcaoSelecionada2);
        while (primeiraAG.getmAreaInseridaEm() != null) {
            if (!primeiraAG.getmAreaInseridaEm().equals(segundaAG)) {
                primeiraAG = primeiraAG.getmAreaInseridaEm();
            } else {
                return true;
            }
        }
        return false;
    }


    public String conteudoLista(boolean usarCriterio) {
        StringBuilder conteudo = new StringBuilder();
        for (int i = 1; i <= mListaAG.size(); i++) {
            conteudo.append(i + " - Nome: " + mListaAG.get(i - 1).getmNomeAreaGeo());
            conteudo.append(", Tipo: " + mListaAG.get(i - 1).getmTipoAreaGeo().getNomeDoTipoAreaGeo());
            conteudo.append(", Latitude: " + mListaAG.get(i - 1).getmLocalizacao().getmLatitude());
            conteudo.append(", Longitude: " + mListaAG.get(i - 1).getmLocalizacao().getmLongitude());
            if (usarCriterio && !verificarSeAGNaoTemAreaInserida(mListaAG.get(i - 1))) {
                conteudo.append(", Inserido Em: " + mListaAG.get(i - 1).getmAreaInseridaEm().getmTipoAreaGeo().getNomeDoTipoAreaGeo());
                conteudo.append(" " + mListaAG.get(i - 1).getmAreaInseridaEm().getmNomeAreaGeo());
            }
            conteudo.append("\n");
        }
        return conteudo.toString();
    }

}