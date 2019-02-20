package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.*;

public class AddSensorToGeoAreaController {
    private SensorTypeList mSensorTypeList;
    private GeographicalAreaList mListaAreaGeografica;
    private GeographicalArea mGeographicalArea;
    private Location mLocation;
    private SensorType mSensorType;

    public AddSensorToGeoAreaController(SensorTypeList sensorTypeList, GeographicalAreaList geographicalAreaList) {
        this.mSensorTypeList = sensorTypeList;
        this.mListaAreaGeografica = geographicalAreaList;
    }

    public void getTipoSensorPorPosicao (int posicao) {
        mSensorType = mSensorTypeList.getSensorTypeByPosition(posicao);
    }

    public String getNomeAreaGeograficaPorIndice (int posicao) {
        return mListaAreaGeografica.getGeographicalAreaNameByPosition(posicao);
    }

    public void getAreaGeograficaNaListaPorPosicao(int posicao) {
        mGeographicalArea = mListaAreaGeografica.getGeoAreaList().get(posicao);
    }

    public int numeroElementosDaListaAreaGeografica () {
        return mListaAreaGeografica.getGeoAreaList().size();
    }

    public int numeroElementosDaListaTipoDeSensor () {
        return mSensorTypeList.getListOfSensorTypes().size();
    }

    public String getNomeTipoSensorPorIndice (int posicao) {
        return mSensorTypeList.getSensorTypeByPosition(posicao).getType();
    }

    public boolean adicionarSensorAAreaGeografica(Sensor sensor) {
        if ((!(this.mListaAreaGeografica.getGeographicalArea(this.mGeographicalArea).getSensorListInTheGeographicArea().getListOfSensors().contains(sensor)))) {
            mListaAreaGeografica.getGeographicalArea(this.mGeographicalArea).getSensorListInTheGeographicArea().addSensor(sensor);
            return true;
        }
        return false;
    }

    public void criarNovaLocalizacao(double mAltitude, double mLatitude, double mLongitude) {
        mLocation = this.mListaAreaGeografica.getGeographicalArea(this.mGeographicalArea).newLocation(mAltitude, mLatitude, mLongitude);
    }

    public Sensor criarNovoSensor(String nome) {
        return this.mListaAreaGeografica.getGeographicalArea(this.mGeographicalArea).newSensor(nome, this.mSensorType, this.mLocation);
    }
}
