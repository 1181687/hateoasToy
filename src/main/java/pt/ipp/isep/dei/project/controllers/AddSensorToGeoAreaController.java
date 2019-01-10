package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.*;

public class AddSensorToGeoAreaController {
    private SensorTypeList mSensorTypeList;
    private GeoAreaList mListaAreaGeografica;
    private GeographicalArea mGeographicalArea;
    private Location mLocation;
    private SensorType mSensorType;

    public AddSensorToGeoAreaController(SensorTypeList sensorTypeList, GeoAreaList geoAreaList) {
        this.mSensorTypeList = sensorTypeList;
        this.mListaAreaGeografica = geoAreaList;
    }


    public void getTipoSensorPorPosicao (int posicao) {
        mSensorType = mSensorTypeList.getSensorTypeByPosition(posicao);
    }

    public String getNomeAreaGeograficaPorIndice (int posicao) {
        return mListaAreaGeografica.getGeographicalAreaNameByPosition(posicao);
    }

    public void getAreaGeograficaNaListaPorPosicao(int posicao) {
        mGeographicalArea = mListaAreaGeografica.getmGeoAreaList().get(posicao);
    }

    public int numeroElementosDaListaAreaGeografica () {
        return mListaAreaGeografica.getmGeoAreaList().size();
    }

    public int numeroElementosDaListaTipoDeSensor () {
        return mSensorTypeList.getSensorTypeList().size();
    }

    public String getNomeTipoSensorPorIndice (int posicao) {
        return mSensorTypeList.getSensorTypeByPosition(posicao).getmType();
    }

    public boolean adicionarSensorAAreaGeografica(Sensor sensor) {
        if ((!(this.mListaAreaGeografica.getGeographicalArea(this.mGeographicalArea).getmSensorListInTheGeographicArea().getmSensorList().contains(sensor)))) {
            mListaAreaGeografica.getGeographicalArea(this.mGeographicalArea).getmSensorListInTheGeographicArea().addSensorToTheListOfSensors(sensor);
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
