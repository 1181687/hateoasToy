package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.geographicalarea.*;
import pt.ipp.isep.dei.project.services.GeoAreaService;

import java.util.ArrayList;
import java.util.List;

public class GetListOfTypeOfGeoAreaController {
    private GeoAreaService geoAreaService;

    public GetListOfTypeOfGeoAreaController(GeoAreaService geoAreaService) {
        this.geoAreaService = geoAreaService;
    }

    public List<GeographicalAreaTypeDTO> getListOfGeoAreaTypes(){
        List<GeographicalAreaTypeDTO> geographicalAreaTypeDTOS = new ArrayList<>();
        for (GeographicalAreaType geographicalAreaType : geoAreaService.getListOfGeoAreaTypes()) {
            geographicalAreaTypeDTOS.add(GeographicalAreaTypeMapper.mapToDTO(geographicalAreaType));
        }
        return geographicalAreaTypeDTOS;
    }

    public List<GeographicalAreaDTO> getListOfGeographicalAreasByType(String type) {
        List<GeographicalAreaDTO> geographicalAreaDTOS = new ArrayList<>();
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId(type);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType(geoAreaTypeId);
        for (GeographicalArea geographicalArea : geoAreaService.getGeoAreasByType(geographicalAreaType)) {
            geographicalAreaDTOS.add(GeographicalAreaMapper.mapToDTO(geographicalArea));
        }
        return geographicalAreaDTOS;
    }

}