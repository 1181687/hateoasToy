package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
import java.util.List;

public class SensorDTOList {
    private List<SensorDTO> list;

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
    public List<SensorDTO> getList() {
        return list;
    }

    /**
     * Method that adds a SensorDTO to the list.
     *
     * @param sensorDTO SensorDTO to be used.
     */
    public void addSensorDTO(SensorDTO sensorDTO) {
        this.list.add(sensorDTO);
    }
}
