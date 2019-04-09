package pt.ipp.isep.dei.project.model.sensor;

import java.util.ArrayList;
import java.util.List;

public class SensorDTOList {
    private List<GeoAreaSensorDTO> list;

    /**
     * Constructor.
     */
    public SensorDTOList() {
        this.list = new ArrayList<>();
    }

    /**
     * Method that returns the list.
     *
     * @return List with SensorDTOs.
     */
    public List<GeoAreaSensorDTO> getList() {
        return list;
    }

    /**
     * Method that adds a GeoAreaSensorDTO to the list.
     *
     * @param sensorDTO GeoAreaSensorDTO to be used.
     */
    public void addSensorDTO(GeoAreaSensorDTO sensorDTO) {
        this.list.add(sensorDTO);
    }
}
