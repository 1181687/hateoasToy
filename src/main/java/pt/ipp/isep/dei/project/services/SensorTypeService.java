package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.SensorTypeRepository;
import pt.ipp.isep.dei.project.model.sensor.SensorType;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;

@Service
public class SensorTypeService {
    @Autowired
    SensorTypeRepository sensorTypeRepository;

    //public SensorTypeService() {
    //}

    public boolean createAndAddSensorType(String sensorTypeId) {
        if (!sensorTypeRepository.existsById(new SensorTypeId(sensorTypeId))) {
            this.sensorTypeRepository.save(new SensorType(new SensorTypeId(sensorTypeId)));
            return true;
        }
        return false;
    }


    /**
     * Get method
     *
     * @return sensor Type List
     */
    /*public List<SensorType> getListOfSensorTypes() {
        return listOfSensorTypes;
    }

    /**
     * Method that retrieves the sensor type in a specific position of the sensor Type List
     *
     * @param posicao position the sensor Type in the sensor Type List
     * @return the sensor Type of that position
     */
    /*public SensorType getSensorTypeByPosition(int posicao) {
        return this.listOfSensorTypes.get(posicao);
    }

    /**
     * Method that shows the content of the sensor type in the list.
     * @return
     */
    /*public String getSensorTypeListToString() {
        StringBuilder content = new StringBuilder();
        for (int i = 1; i <= this.listOfSensorTypes.size(); i++) {
            content.append(i + " - sensor Type: " + this.listOfSensorTypes.get(i - 1).getSensorType());
            content.append("\n");
        }
        return content.toString();
    }

    /**
     * Boolean method that checks if the sensor Type List is empty
     * @return empty sensor Type List
     */
    /*public boolean isEmpty() {
        return this.listOfSensorTypes.isEmpty();
    }*/
}
