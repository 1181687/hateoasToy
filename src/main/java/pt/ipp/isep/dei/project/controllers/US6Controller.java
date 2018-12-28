package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.*;

public class US6Controller {
    private ListaTiposSensores mListaTiposSensores;
    private ListaAG mListaAreaGeografica;
    private GeographicalArea mGeographicalArea;
    private Location mLocation;
    private TipoSensor mSensorType;
    private Sensor mSensor;

    public US6Controller(ListaTiposSensores listaTiposSensores, ListaAG listaAG) {
        this.mListaTiposSensores = listaTiposSensores;
        this.mListaAreaGeografica = listaAG;
    }


    public void getTipoSensorPorPosicao (int posicao) {
        mSensorType = mListaTiposSensores.getTipoSensorPorPosicao(posicao);
    }

    public String getNomeAreaGeograficaPorIndice (int posicao) {
        return mListaAreaGeografica.getNomeAreaGeograficaPorIndice(posicao);
    }

    public void getAreaGeograficaNaListaPorPosicao(int posicao) {
        mGeographicalArea = mListaAreaGeografica.getmListaAG().get(posicao);
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

    public boolean adicionarSensorAAreaGeografica(Sensor sensor) {
        if ((!(this.mListaAreaGeografica.getAreaGeografica(this.mGeographicalArea).getmSensorListInTheGeographicArea().getmSensorList().contains(sensor)))) {
            mListaAreaGeografica.getAreaGeografica(this.mGeographicalArea).getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(sensor);
            return true;
        }
        return false;
    }

    public void criarNovaLocalizacao(double mAltitude, double mLatitude, double mLongitude) {
        mLocation = this.mListaAreaGeografica.getAreaGeografica(this.mGeographicalArea).novaLocalizacao(mAltitude, mLatitude, mLongitude);
    }

    public Sensor criarNovoSensor(String nome) {
        return this.mListaAreaGeografica.getAreaGeografica(this.mGeographicalArea).novoSensor(nome, this.mSensorType, this.mLocation);
    }
}
