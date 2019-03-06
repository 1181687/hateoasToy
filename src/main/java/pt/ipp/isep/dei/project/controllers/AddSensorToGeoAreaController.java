package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.*;

public class AddSensorToGeoAreaController {
    private SensorTypeList sensorTypeList;
    private GeographicalAreaList geographicalAreaList;
    private GeographicalArea geographicalArea;
    private Location location;
    private SensorType sensorType;

    public AddSensorToGeoAreaController(SensorTypeList sensorTypeList, GeographicalAreaList geographicalAreaList) {
        this.sensorTypeList = sensorTypeList;
        this.geographicalAreaList = geographicalAreaList;
    }

    public void getTipoSensorPorPosicao (int posicao) {
        sensorType = sensorTypeList.getSensorTypeByPosition(posicao);
    }

    public String getNomeAreaGeograficaPorIndice (int posicao) {
        return geographicalAreaList.getGeographicalAreaNameByPosition(posicao);
    }

    public void getAreaGeograficaNaListaPorPosicao(int posicao) {
        geographicalArea = geographicalAreaList.getGeoAreaList().get(posicao);
    }

    public int numeroElementosDaListaAreaGeografica () {
        return geographicalAreaList.getGeoAreaList().size();
    }

    public int numeroElementosDaListaTipoDeSensor () {
        return sensorTypeList.getListOfSensorTypes().size();
    }

    public String getNomeTipoSensorPorIndice (int posicao) {
        return sensorTypeList.getSensorTypeByPosition(posicao).getType();
    }

    public boolean adicionarSensorAAreaGeografica(Sensor sensor) {
        if ((!(this.geographicalAreaList.getGeographicalArea(this.geographicalArea).getSensorListInTheGeographicArea().getListOfSensors().contains(sensor)))) {
            geographicalAreaList.getGeographicalArea(this.geographicalArea).getSensorListInTheGeographicArea().addSensor(sensor);
            return true;
        }
        return false;
    }

    public void criarNovaLocalizacao(double mAltitude, double mLatitude, double mLongitude) {
        location = this.geographicalAreaList.getGeographicalArea(this.geographicalArea).newLocation(mAltitude, mLatitude, mLongitude);
    }

    public Sensor criarNovoSensor(String nome) {
        return this.geographicalAreaList.getGeographicalArea(this.geographicalArea).newSensor(nome, this.sensorType, this.location);
    }
}
