package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.*;

public class US6Controller {
    private SensorTypeList mSensorTypeList;
    private GeoAreaList mListaAreaGeografica;
    private GeographicalArea mGeographicalArea;
    private Location mLocation;
    private SensorType mSensorType;

    public US6Controller(SensorTypeList sensorTypeList, GeoAreaList geoAreaList) {
        this.mSensorTypeList = sensorTypeList;
        this.mListaAreaGeografica = geoAreaList;
    }


    public void getTipoSensorPorPosicao (int posicao) {
        mSensorType = mSensorTypeList.getTipoSensorPorPosicao(posicao);
    }

    public String getNomeAreaGeograficaPorIndice (int posicao) {
        return mListaAreaGeografica.getGeographicalAreaNameByPosition(posicao);
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
        return mSensorTypeList.getTipoSensorPorPosicao(posicao).getmType();
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
