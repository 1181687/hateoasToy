package pt.ipp.isep.dei.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaTypeDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaTypeMapper;
import pt.ipp.isep.dei.project.services.GeoAreaTypeService;

import java.util.ArrayList;
import java.util.List;

public class GetListGeoAreaTypesController {
    @Autowired
    private GeoAreaTypeService geoAreaTypeService;


    public GetListGeoAreaTypesController(GeoAreaTypeService geoAreaTypeService) {
        this.geoAreaTypeService = geoAreaTypeService;
    }


    public List<GeographicalAreaTypeDTO> getListOfGeoAreaTypes() {
        List<GeographicalAreaTypeDTO> geographicalAreaTypeDTOList = new ArrayList<>();
        for (GeographicalAreaType geographicalAreaType : geoAreaTypeService.getListOfGeoAreaTypes()) {
            geographicalAreaTypeDTOList.add(GeographicalAreaTypeMapper.mapToDTO(geographicalAreaType));
        }
        return geographicalAreaTypeDTOList;
    }
}