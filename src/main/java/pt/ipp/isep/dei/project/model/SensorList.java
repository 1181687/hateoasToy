package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
import java.util.List;

public class SensorList {
    private List<Sensor> mSensorList = new ArrayList<>();

    public SensorList() {
    }

    public List<Sensor> getmSensorList() {
        return mSensorList;
    }

    public boolean addSensorToTheListOfSensors(Sensor sensor) {
        if (!(mSensorList.contains(sensor))) {
            mSensorList.add(sensor);
            return true;
        }
        return false;
    }

    public Sensor createNewSensor (String name, TipoSensor sensorType, Location location) {
        return new Sensor(name, sensorType, location);
    }

}
