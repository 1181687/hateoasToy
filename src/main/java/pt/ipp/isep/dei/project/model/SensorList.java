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

    public Sensor createNewSensor(String name, SensorType sensorType, Location location) {
        return new Sensor(name, sensorType, location);
    }

    public void setmSensorList(List<Sensor> mSensorList) {
        this.mSensorList = mSensorList;
    }

    public List<Measurement> getListaDeUltimosRegistosPorTipoDeSensor(SensorType tipo) {
        List<Measurement> listaDeUltimosRegistos = new ArrayList<>();
        for (Sensor sensor : mSensorList) {
            if (sensor.listaDeRegistosEVazia()) {
                break;
            }
            if (sensor.umTipoDeSensorEIgualAOutro(tipo) && (!(Double.isNaN(sensor.getUltimoRegisto().getmValue())))) {
                listaDeUltimosRegistos.add(sensor.getUltimoRegisto());
            }
        }
        return listaDeUltimosRegistos;
    }

    public double getUltimoRegistoDeUmTipoDeSensor(SensorType tipo) {
        List<Measurement> listaDeUltimosRegisto = getListaDeUltimosRegistosPorTipoDeSensor(tipo);
        if (getListaDeUltimosRegistosPorTipoDeSensor(tipo).isEmpty()) {
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
}


