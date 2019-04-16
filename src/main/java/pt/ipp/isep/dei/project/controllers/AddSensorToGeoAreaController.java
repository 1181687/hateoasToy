package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.services.GeoAreaSensorService;

public class AddSensorToGeoAreaController {

    GeoAreaSensorService geoAreaSensorService;






    /*
    public AddSensorToGeoAreaController(GeoAreaSensorService geoAreaSensorService) {
        this.geoAreaSensorService = geoAreaSensorService;
    }

    public void getTipoSensorPorPosicao (int posicao) {
        sensorType = sensorTypeList.getSensorTypeByPosition(posicao);
    }

    public String getNomeAreaGeograficaPorIndice (int posicao) {
        return geographicalAreaService.getGeographicalAreaNameByPosition(posicao);
    }

    public void getAreaGeograficaNaListaPorPosicao(int posicao) {
        geographicalArea = geographicalAreaService.getGeoAreaList().get(posicao);
    }

    public int numeroElementosDaListaAreaGeografica () {
        return geographicalAreaService.getGeoAreaList().size();
    }

    public int numeroElementosDaListaTipoDeSensor () {
        return sensorTypeList.getListOfSensorTypes().size();
    }

    public String getNomeTipoSensorPorIndice (int posicao) {
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
    */

}
