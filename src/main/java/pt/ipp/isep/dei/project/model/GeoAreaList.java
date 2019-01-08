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

    public boolean addGeoAreaToTheList(GeographicalArea AG) {
        if (!(mListaAG.contains(AG))) {
            mListaAG.add(AG);
            return true;
        }
        return false;
    }

    public GeographicalArea getGeographicalArea(GeographicalArea geographicalArea) {
        for (GeographicalArea area : mListaAG) {
            if (area.equals(geographicalArea)) {
                return area;
            }
        }
        return null;
    }

    public String getGeographicalAreaNameByPosition(int posicao) {
        return this.mListaAG.get(posicao).getNameOfGeoArea();
    }

    public List<String> getListOfGeographicalAreasByType(String tipo) {
        List<String> listaAGMesmoTipo = new ArrayList<>();
        for (GeographicalArea areaGeo : mListaAG) {
            if (areaGeo.getGeoAreaType().checkIfOneTypeOfGeoAreaIsEqualToAnotherType(tipo)) {
                listaAGMesmoTipo.add(areaGeo.getNameOfGeoArea());
            }
        }
        return listaAGMesmoTipo;
    }

    public GeographicalArea getGeographicalAreaInTheList(int opcaoSelecionada) {
        return mListaAG.get(opcaoSelecionada);
    }

    public boolean checkIfGeoAreaDoesntHaveAnInsertedArea(GeographicalArea area) {
        return area.getInsertedIn() == null;
    }

    public boolean removeGeoAreaFromTheList(GeographicalArea geoArea) {
        return mListaAG.remove(geoArea);
    }

    public void addGeoAreaToTheListInASpecificPosition(int posicao, GeographicalArea geoArea) {
        mListaAG.add(posicao, geoArea);
    }

    public boolean checkIfGeoAreaIsinsertedInAnother(int opcaoSelecionada1, int opcaoSelecionada2) {
        GeographicalArea primeiraAG = mListaAG.get(opcaoSelecionada1);
        GeographicalArea segundaAG = mListaAG.get(opcaoSelecionada2);
        while (primeiraAG.getInsertedIn() != null) {
            if (!primeiraAG.getInsertedIn().equals(segundaAG)) {
                primeiraAG = primeiraAG.getInsertedIn();
            } else {
                return true;
            }
        }
        return false;
    }


    public String listContent(boolean usarCriterio) {
        StringBuilder conteudo = new StringBuilder();
        for (int i = 1; i <= mListaAG.size(); i++) {
            conteudo.append(i + " - Nome: " + mListaAG.get(i - 1).getNameOfGeoArea());
            conteudo.append(", Tipo: " + mListaAG.get(i - 1).getGeoAreaType().getStringOfTypeOfGeoArea());
            conteudo.append(", Latitude: " + mListaAG.get(i - 1).getLocation().getmLatitude());
            conteudo.append(", Longitude: " + mListaAG.get(i - 1).getLocation().getmLongitude());
            if (usarCriterio && !checkIfGeoAreaDoesntHaveAnInsertedArea(mListaAG.get(i - 1))) {
                conteudo.append(", Inserido Em: " + mListaAG.get(i - 1).getInsertedIn().getGeoAreaType().getStringOfTypeOfGeoArea());
                conteudo.append(" " + mListaAG.get(i - 1).getInsertedIn().getNameOfGeoArea());
            }
            conteudo.append("\n");
        }
        return conteudo.toString();
    }

    public GeographicalArea newGeographicalArea(String nomeAG, String nomeTipoAG, double latitude, double longitude, double altitude, double altura, double comprimento) {
        GeoAreaType tipoAG = new GeoAreaType(nomeTipoAG);
        Location localizacao = new Location(latitude, longitude,altitude);
        AreaShape rectanguloArea = new AreaShape(altura, comprimento, localizacao);
        return new GeographicalArea(nomeAG, tipoAG, localizacao, rectanguloArea);
    }
}