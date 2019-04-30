package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaTypeId;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.repositories.GeoAreaTypeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class GeoAreaTypeService {
    @Autowired
    private GeoAreaTypeRepository geoAreaTypeRepository;


    public List<GeographicalAreaType> getListOfGeoAreaTypes() {
        Iterable<GeographicalAreaType> geoAreaTypeIterables = this.geoAreaTypeRepository.findAll();
        List<GeographicalAreaType> geographicalAreaTypeList = new ArrayList<>();
        geoAreaTypeIterables.forEach(geographicalAreaTypeList::add);
        return geographicalAreaTypeList;
    }

    /**
     * method that get the list of geo area types
     *
     * @return a list of geo area types.
     */
    public List<String> getListOfGeoAreaTypesToString() {
        List<String> listOfGeoAreaTypes = new ArrayList<>();
        for (GeographicalAreaType object : this.geoAreaTypeRepository.findAll()) {
            listOfGeoAreaTypes.add(object.getStringOfTypeOfGeoArea());
        }
        return listOfGeoAreaTypes;
    }

    public boolean createGeoAreaType(String geoAreaTypeId) {
        GeoAreaTypeId newIdGeoAreaType = new GeoAreaTypeId(geoAreaTypeId);
        if (!geoAreaTypeRepository.existsById(geoAreaTypeId)) {
            geoAreaTypeRepository.save(new GeographicalAreaType(newIdGeoAreaType));
            return true;
        }
        return false;
    }
}
