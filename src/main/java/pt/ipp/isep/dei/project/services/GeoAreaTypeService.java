package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.GeoAreaTypeRepository;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaTypeId;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;

import java.util.ArrayList;
import java.util.List;

@Service
public class GeoAreaTypeService {
    @Autowired
    GeoAreaTypeRepository geoAreaTypeRepository;

    public GeoAreaTypeService() {
    }

    public boolean createGeoAreaType(GeoAreaTypeId geoAreaTypeId){
        if (!geoAreaTypeRepository.existsById(geoAreaTypeId)){
            geoAreaTypeRepository.save(new GeographicalAreaType(geoAreaTypeId));
            return true;
        }
        return false;
    }

    public List<GeographicalAreaType> getListOfGeoAreaTypes(){
        List<GeographicalAreaType> geographicalAreaTypeList = new ArrayList<>();
        geographicalAreaTypeList.addAll(geoAreaTypeRepository.getAll());
        return geographicalAreaTypeList;
    }
}
