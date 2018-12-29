package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
import java.util.Date;
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

    public Sensor createNewSensor(String name, SensorType sensorType, Location location) {
        return new Sensor(name, sensorType, location);
    }

    public void setmSensorList(List<Sensor> mSensorList) {
        this.mSensorList = mSensorList;
    }

    public List<Measurement> getListOfLatestMeasurementsBySensorType(SensorType type) {
        List<Measurement> listOfLatestMeasurements = new ArrayList<>();
        for (Sensor sensor : mSensorList) {
            if (sensor.measurementListIsEmpty()) {
                break;
            }
            if (sensor.umTipoDeSensorEIgualAOutro(type) && (!(Double.isNaN(sensor.getUltimoRegisto().getmValue())))) {
                listOfLatestMeasurements.add(sensor.getUltimoRegisto());
            }
        }
        return listOfLatestMeasurements;
    }

    ////method to substitute the getUltimoRegistoDeUmTipoDeSensor if nobody uses it
    public Measurement getLatestMeasurementBySensorType(SensorType type) {
        List<Measurement> listOfLatestMeasurements = getListOfLatestMeasurementsBySensorType(type);
        if (getListOfLatestMeasurementsBySensorType(type).isEmpty()) {
            return null;
        }
        Measurement latestMeasurement = listOfLatestMeasurements.get(0);
        for (Measurement measurement : listOfLatestMeasurements) {
            if (measurement.getmDateTime().after(latestMeasurement.getmDateTime())) {
                latestMeasurement = measurement;
            }
        }
        return latestMeasurement;
    }

    public double getUltimoRegistoDeUmTipoDeSensor(SensorType type) {
        List<Measurement> listaDeUltimosRegisto = getListOfLatestMeasurementsBySensorType(type);
        if (getListOfLatestMeasurementsBySensorType(type).isEmpty()) {
            return Double.NaN;
        }
        Measurement measurementComUltimoRegisto = listaDeUltimosRegisto.get(0);
        for (Measurement registo : listaDeUltimosRegisto) {
            if (registo.getmDateTime().after(measurementComUltimoRegisto.getmDateTime())) {
                measurementComUltimoRegisto = registo;
            }
        }
        return measurementComUltimoRegisto.getmValue();
    }

    /**
     * @param type type of sensor (temperature)
     * @param date any given day
     * @return maximum value of the temperature sensor in a given day.
     */
    public double getMaximumMeasureOfATypeOfSensorInAGivenDay(SensorType type, Date date) {
        double maxValue = Double.NaN;
        for (Sensor sensor : mSensorList) {
            if (sensor.getmSensorType().equals(type) && (!(sensor.getDailyMeasurement(date).isEmpty()))) {
                if (sensor.getMaximumValueOfDay(date) > maxValue) {
                    maxValue = sensor.getMaximumValueOfDay(date);

                }

            }
        }
        return maxValue;
    }
}


