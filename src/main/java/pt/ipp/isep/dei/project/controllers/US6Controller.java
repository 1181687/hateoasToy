package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.*;

public class US6Controller {
    private SensorTypeList mSensorTypeList;
    private GeoAreaList mListaAreaGeografica;
    private GeographicalArea mGeographicalArea;
    private Location mLocation;
    private SensorType mSensorType;
    private Sensor mSensor;

    public US6Controller(SensorTypeList sensorTypeList, GeoAreaList geoAreaList) {
        this.mSensorTypeList = sensorTypeList;
        this.mListaAreaGeografica = geoAreaList;
    }


    public void getTipoSensorPorPosicao (int posicao) {
        mSensorType = mSensorTypeList.getTipoSensorPorPosicao(posicao);
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
        return mSensorTypeList.getmListaTiposSensores().size();
    }

    public String getNomeTipoSensorPorIndice (int posicao) {
        return mSensorTypeList.getTipoSensorPorPosicao(posicao).getmTipo();
    }

    public boolean adicionarSensorAAreaGeografica() {
        if ((!(this.mListaAreaGeografica.getAreaGeografica(this.mGeographicalArea).getmSensorListInTheGeographicArea().getmSensorList().contains(this.mSensor)))) {
            mListaAreaGeografica.getAreaGeografica(this.mGeographicalArea).getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(this.mSensor);
            return true;
        }
        return false;
    }

    public void criarNovaLocalizacao(double mAltitude, double mLatitude, double mLongitude) {
        mLocation = this.mListaAreaGeografica.getAreaGeografica(this.mGeographicalArea).novaLocalizacao(mAltitude, mLatitude, mLongitude);
    }

    public void criarNovoSensor(String nome) {
        mSensor = this.mListaAreaGeografica.getAreaGeografica(this.mGeographicalArea).novoSensor(nome, this.mSensorType, this.mLocation);
    }
}
