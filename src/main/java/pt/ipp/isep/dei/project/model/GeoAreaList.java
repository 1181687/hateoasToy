package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
import java.util.List;

public class GeoAreaList {

    private List<GeographicalArea> mListaAG;

    public GeoAreaList() {
        this.mListaAG = new ArrayList<>();
    }

    public List<GeographicalArea> getmListaAG() {
        return mListaAG;
    }

    public boolean adicionarAreaGeoALista(GeographicalArea AG) {
        if (!(mListaAG.contains(AG))) {
            mListaAG.add(AG);
            return true;
        }
        return false;
    }

    public GeographicalArea getAreaGeografica(GeographicalArea geographicalArea) {
        for (GeographicalArea area : mListaAG) {
            if (area.equals(geographicalArea)) {
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
        for (GeographicalArea areaGeo : mListaAG) {
            if (areaGeo.getmGeoAreaType().umTipoAreaGeoEIgualAOutra(tipo)) {
                listaAGMesmoTipo.add(areaGeo.getmNomeAreaGeo());
            }
        }
        return listaAGMesmoTipo;
    }

    public GeographicalArea getAreaGeograficaNaListaApresentada(int opcaoSelecionada) {
        return mListaAG.get(opcaoSelecionada);
    }

    public boolean verificarSeAGNaoTemAreaInserida(GeographicalArea area) {
        return area.getmInsertedIn() == null;
    }

    public boolean removerAreaGeoALista(GeographicalArea geoArea) {
        return mListaAG.remove(geoArea);
    }

    public void adicionarAreaGeoAListaNumaPosicaoEspecifica(int posicao, GeographicalArea geoArea) {
        mListaAG.add(posicao, geoArea);
    }

    public boolean verificarSeAGEstaContidaNoutra(int opcaoSelecionada1, int opcaoSelecionada2) {
        GeographicalArea primeiraAG = mListaAG.get(opcaoSelecionada1);
        GeographicalArea segundaAG = mListaAG.get(opcaoSelecionada2);
        while (primeiraAG.getmInsertedIn() != null) {
            if (!primeiraAG.getmInsertedIn().equals(segundaAG)) {
                primeiraAG = primeiraAG.getmInsertedIn();
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
            conteudo.append(", Tipo: " + mListaAG.get(i - 1).getmGeoAreaType().getNomeDoTipoAreaGeo());
            conteudo.append(", Latitude: " + mListaAG.get(i - 1).getmLocation().getmLatitude());
            conteudo.append(", Longitude: " + mListaAG.get(i - 1).getmLocation().getmLongitude());
            if (usarCriterio && !verificarSeAGNaoTemAreaInserida(mListaAG.get(i - 1))) {
                conteudo.append(", Inserido Em: " + mListaAG.get(i - 1).getmInsertedIn().getmGeoAreaType().getNomeDoTipoAreaGeo());
                conteudo.append(" " + mListaAG.get(i - 1).getmInsertedIn().getmNomeAreaGeo());
            }
            conteudo.append("\n");
        }
        return conteudo.toString();
    }

    public GeographicalArea novaAreaGeografica(String nomeAG, String nomeTipoAG, double latitude, double longitude, double altitude, double altura, double comprimento) {
        GeoAreaType tipoAG = new GeoAreaType(nomeTipoAG);
        Location localizacao = new Location(latitude, longitude,altitude);
        RectangleArea rectanguloArea = new RectangleArea(altura, comprimento, localizacao);
        return new GeographicalArea(nomeAG, tipoAG, localizacao, rectanguloArea);
    }
}