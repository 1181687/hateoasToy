package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.*;

public class US6Controller {
    private ListaTiposSensores mListaTiposSensores;
    private ListaAG mListaAreaGeografica;

    public US6Controller(ListaTiposSensores listaTiposSensores, ListaAG listaAG) {
        this.mListaTiposSensores = listaTiposSensores;
        this.mListaAreaGeografica = listaAG;
    }


    public TipoSensor getTipoSensorPorPosicao (int posicao) {
        return mListaTiposSensores.getTipoSensorPorPosicao(posicao);
    }

    public String getNomeAreaGeograficaPorIndice (int posicao) {
        return mListaAreaGeografica.getNomeAreaGeograficaPorIndice(posicao);
    }

    public GeographicArea getAreaGeograficaNaListaPorPosicao(int posicao) {
        return mListaAreaGeografica.getmListaAG().get(posicao);
    }

    public int numeroElementosDaListaAreaGeografica () {
        return mListaAreaGeografica.getmListaAG().size();
    }

    public int numeroElementosDaListaTipoDeSensor () {
        return mListaTiposSensores.getmListaTiposSensores().size();
    }

    public String getNomeTipoSensorPorIndice (int posicao) {
        return mListaTiposSensores.getTipoSensorPorPosicao(posicao).getmTipo();
    }

    public boolean adicionarSensorAAreaGeografica(Sensor sensor, GeographicArea geographicArea) {
        if ((!(this.mListaAreaGeografica.getAreaGeografica(geographicArea).getmSensorListInTheGeographicArea().getmSensorList().contains(sensor)))) {
            mListaAreaGeografica.getAreaGeografica(geographicArea).getmSensorListInTheGeographicArea().addSensorToTheListOfSensorsInTheGeographicalArea(sensor);
            return true;
        }
        return false;
    }

    public Location criarNovaLocalizacao(double mAltitude, double mLatitude, double mLongitude, GeographicArea geographicArea) {
        return this.mListaAreaGeografica.getAreaGeografica(geographicArea).novaLocalizacao(mAltitude, mLatitude, mLongitude);
    }

    public Sensor criarNovoSensor(String nome, TipoSensor novoTipoSensor, Location novaLocation, GeographicArea geographicArea) {
        return this.mListaAreaGeografica.getAreaGeografica(geographicArea).novoSensor(nome, novoTipoSensor, novaLocation);
    }
}
