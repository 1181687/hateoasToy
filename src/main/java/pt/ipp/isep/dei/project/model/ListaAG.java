package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
import java.util.List;

public class ListaAG {

    private List<GeographicArea> mListaAG;

    public ListaAG() {
        this.mListaAG = new ArrayList<>();
    }

    public List<GeographicArea> getmListaAG() {
        return mListaAG;
    }

    public boolean adicionarAreaGeoALista(GeographicArea AG) {
        if (!(mListaAG.contains(AG))) {
            mListaAG.add(AG);
            return true;
        }
        return false;
    }

    public GeographicArea getAreaGeografica(GeographicArea geographicArea) {
        for (GeographicArea area : mListaAG) {
            if (area.equals(geographicArea)) {
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
        for (GeographicArea areaGeo : mListaAG) {
            if (areaGeo.getmTipoAreaGeo().umTipoAreaGeoEIgualAOutra(tipo)) {
                listaAGMesmoTipo.add(areaGeo.getmNomeAreaGeo());
            }
        }
        return listaAGMesmoTipo;
    }

    public GeographicArea getAreaGeograficaNaListaApresentada(int opcaoSelecionada) {
        return mListaAG.get(opcaoSelecionada);
    }

    public boolean verificarSeAGNaoTemAreaInserida(GeographicArea area) {
        return area.getmAreaInseridaEm() == null;
    }

    public boolean removerAreaGeoALista(GeographicArea AG) {
        return mListaAG.remove(AG);
    }

    public void adicionarAreaGeoAListaNumaPosicaoEspecifica(int posicao, GeographicArea AG) {
        mListaAG.add(posicao, AG);
    }

    public boolean verificarSeAGEstaContidaNoutra(int opcaoSelecionada1, int opcaoSelecionada2) {
        GeographicArea primeiraAG = mListaAG.get(opcaoSelecionada1);
        GeographicArea segundaAG = mListaAG.get(opcaoSelecionada2);
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
            conteudo.append(", Latitude: " + mListaAG.get(i - 1).getmLocation().getmLatitude());
            conteudo.append(", Longitude: " + mListaAG.get(i - 1).getmLocation().getmLongitude());
            if (usarCriterio && !verificarSeAGNaoTemAreaInserida(mListaAG.get(i - 1))) {
                conteudo.append(", Inserido Em: " + mListaAG.get(i - 1).getmAreaInseridaEm().getmTipoAreaGeo().getNomeDoTipoAreaGeo());
                conteudo.append(" " + mListaAG.get(i - 1).getmAreaInseridaEm().getmNomeAreaGeo());
            }
            conteudo.append("\n");
        }
        return conteudo.toString();
    }

    public GeographicArea novaAreaGeografica(String nomeAG, String nomeTipoAG, double latitude, double longitude, double altitude, double altura, double comprimento) {
        TipoAreaGeo tipoAG = new TipoAreaGeo(nomeTipoAG);
        Location localizacao = new Location(latitude, longitude,altitude);
        RectangleArea rectanguloArea = new RectangleArea(altura, comprimento, localizacao);
        return new GeographicArea(nomeAG, tipoAG, localizacao, rectanguloArea);
    }

}