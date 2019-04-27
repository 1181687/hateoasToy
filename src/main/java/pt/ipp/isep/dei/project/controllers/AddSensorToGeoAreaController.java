package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;
import pt.ipp.isep.dei.project.model.sensor.SensorType;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeList;
import pt.ipp.isep.dei.project.services.GeographicalAreaService;

public class AddSensorToGeoAreaController {
    private SensorTypeList sensorTypeList;
    private GeographicalAreaService geographicalAreaService;
    private GeographicalArea geographicalArea;
    private Location location;
    private SensorType sensorType;

    public AddSensorToGeoAreaController(SensorTypeList sensorTypeList, GeographicalAreaService geographicalAreaService) {
        this.sensorTypeList = sensorTypeList;
        this.geographicalAreaService = geographicalAreaService;
    }

    public void getTipoSensorPorPosicao(int posicao) {
        sensorType = sensorTypeList.getSensorTypeByPosition(posicao);
    }

    public String getNomeAreaGeograficaPorIndice(int posicao) {
        return geographicalAreaService.getGeographicalAreaNameByPosition(posicao);
    }

    public void getAreaGeograficaNaListaPorPosicao(int posicao) {
        geographicalArea = geographicalAreaService.getGeoAreaList().get(posicao);
    }

    public int numeroElementosDaListaAreaGeografica() {
        return geographicalAreaService.getGeoAreaList().size();
    }

    public int numeroElementosDaListaTipoDeSensor() {
        return sensorTypeList.getListOfSensorTypes().size();
    }

    public String getNomeTipoSensorPorIndice(int posicao) {
        return sensorTypeList.getSensorTypeByPosition(posicao).getType();
    }

    public boolean adicionarSensorAAreaGeografica(GeoAreaSensor sensor) {
        if ((!(this.geographicalAreaService.getGeographicalArea(this.geographicalArea).getSensorListInTheGeographicArea().getListOfSensors().contains(sensor)))) {
            geographicalAreaService.getGeographicalArea(this.geographicalArea).getSensorListInTheGeographicArea().addSensor(sensor);
            return true;
        }
        return false;
    }

    public void criarNovaLocalizacao(double mAltitude, double mLatitude, double mLongitude) {
        location = this.geographicalAreaService.getGeographicalArea(this.geographicalArea).newLocation(mAltitude, mLatitude, mLongitude);
    }

    public GeoAreaSensor criarNovoSensor(String id, String nome, String units) {
        return this.geographicalArea.newSensor(id, nome, this.sensorType, this.location, units);
    }
}
