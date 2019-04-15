package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaService;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaTypeDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaTypeList;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaTypeMapper;
import pt.ipp.isep.dei.project.services.GeoAreaTypeService;
import pt.ipp.isep.dei.project.services.SensorTypeService;

import java.util.ArrayList;
import java.util.List;

public class GetListOfTypeOfGeoAreaController {
    private GeoAreaTypeService geoAreaTypeService;

    public GetListOfTypeOfGeoAreaController(GeoAreaTypeService geoAreaTypeService) {
        this.geoAreaTypeService = geoAreaTypeService;
    }

    public List<GeographicalAreaTypeDTO> getListOfGeoAreaTypes(){
        List<GeographicalAreaTypeDTO> geographicalAreaTypeDTOS = new ArrayList<>();
        for (GeographicalAreaType geographicalAreaType : geoAreaTypeService.getListOfGeoAreaTypes()) {
            geographicalAreaTypeDTOS.add(GeographicalAreaTypeMapper.mapToDTO(geographicalAreaType));
        }
        return geographicalAreaTypeDTOS;
    }

    public List<String> getListaAGPorTipo(String tipo) {
        return this.geographicalAreaService.getListOfGeographicalAreasByType(tipo);
    }

    public List<String> getListaDosTiposDeAG(){
        return geographicalAreaTypeList.getListOfGeoAreaTypes();
    }



}