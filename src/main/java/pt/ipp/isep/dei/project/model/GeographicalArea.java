package pt.ipp.isep.dei.project.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GeographicalArea {
    private String mNomeAreaGeo;
    private GeoAreaType mGeoAreaType;
    private GeographicalArea mAreaInseridaEm;
    private Location mLocation;
    private RectangleArea mRectangleArea;
    private SensorList mSensorList = new SensorList();

    public GeographicalArea(String mNomeAreaGeo, GeoAreaType mGeoAreaType, Location mLocation, RectangleArea mRectangleArea) {
        this.mNomeAreaGeo = mNomeAreaGeo;
        this.mGeoAreaType = mGeoAreaType;
        this.mLocation = mLocation;
        this.mRectangleArea = mRectangleArea;
    }

    public SensorList getmSensorListInTheGeographicArea() {
        return mSensorList;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GeographicalArea)) {
            return false;
        }
        GeographicalArea ag = (GeographicalArea) obj;
        return this.mNomeAreaGeo.equals(ag.mNomeAreaGeo) && this.mGeoAreaType.equals(ag.mGeoAreaType) && this.mLocation.equals(ag.mLocation) && this.mRectangleArea.equals(ag.mRectangleArea);

    }


    public String getmNomeAreaGeo() {
        return mNomeAreaGeo;
    }

    public GeoAreaType getmGeoAreaType() {
        return mGeoAreaType;
    }

    public Location getmLocation() {
        return this.mLocation;
    }

    public GeographicalArea getmAreaInseridaEm() {
        return mAreaInseridaEm;
    }

    public void setmAreaInseridaEm(GeographicalArea mAreaInseridaEm) {
        this.mAreaInseridaEm = mAreaInseridaEm;
    }

    public double distanciaLinearDuasAreas(GeographicalArea novoAg) {
        return this.mLocation.distanciaDuasLocalizacoes(novoAg.getmLocation());
    }

/*
    public List<Measurement> getListOfLatestMeasurementsBySensorType(SensorType tipo) {
        List<Measurement> listaDeUltimosRegistos = new ArrayList<>();
        for (Sensor sensor : mSensorList.getmSensorList()) {
            if (sensor.measurementListIsEmpty()) {
                break;
            }
            if (sensor.umTipoDeSensorEIgualAOutro(tipo) && (!(Double.isNaN(sensor.getUltimoRegisto().getmValue())))) {
                listaDeUltimosRegistos.add(sensor.getUltimoRegisto());
            }
        }
        return listaDeUltimosRegistos;
    }

    public double getUltimoRegistoDeUmTipoDeSensor(SensorType tipo) {
        List<Measurement> listaDeUltimosRegisto = getListOfLatestMeasurementsBySensorType(tipo);
        if (getListOfLatestMeasurementsBySensorType(tipo).isEmpty()) {
            return Double.NaN;
        }
        Measurement medicaoComUltimoRegisto = listaDeUltimosRegisto.get(0);
        for (Measurement registo : listaDeUltimosRegisto) {
            if (registo.getmDateTime().after(medicaoComUltimoRegisto.getmDateTime())) {
                medicaoComUltimoRegisto = registo;
            }
        }
        return medicaoComUltimoRegisto.getmValue();
    }
    */

    public boolean verificarSeSensorEstaContidoNaAG(Sensor sensor) {

        return mRectangleArea.verificaSeLocalizacaoEstaContidaNumaArea(sensor.getmLocation());

    }

    public List<Sensor> sortSensorsInAGeoAreaByType(SensorType sensorType, List<Sensor> listaDeSensores) {

        List<Sensor> listaDeSensoresInseridos = new ArrayList<>();

        for (Sensor sensor : listaDeSensores) {

            if (verificarSeSensorEstaContidoNaAG(sensor) && sensor.getmSensorType().equals(sensorType)) {
                listaDeSensoresInseridos.add(sensor);
            }
        }
        return listaDeSensoresInseridos;
    }

    public List<Sensor> listarSensoresDeUmTipoNaAGNumPeriodo(SensorType tipo, List<Sensor> listaDeSensores, Date dataInicial, Date dataFinal) {

        List<Sensor> listaSensoresContidosNaAGPorTipo = sortSensorsInAGeoAreaByType(tipo, listaDeSensores);
        List<Sensor> listaSensoresDeTipoNumPeriodo = new ArrayList<>();

        for (Sensor sensor : listaSensoresContidosNaAGPorTipo) {
            if (sensor.temRegistosEntreDatas(dataInicial, dataFinal)) {
                listaSensoresDeTipoNumPeriodo.add(sensor);
            }
        }
        return listaSensoresDeTipoNumPeriodo;
    }

    public List<Sensor> sortSensorTypesOfAGeoAreaInADay(SensorType type, List<Sensor> sensorList, Date day) {
        //Convert Date to LocalDate
        LocalDate dayLD = day.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDateTime beginningOfDayLDT = dayLD.atStartOfDay();
        LocalDateTime endOfDayLDT = dayLD.atTime(23, 59, 59);

        //Convert LocalDate to Date
        Date beginningOfDay = Date.from(beginningOfDayLDT.atZone(ZoneId.systemDefault()).toInstant());
        Date endOfDay = Date.from(endOfDayLDT.atZone(ZoneId.systemDefault()).toInstant());

        List<Sensor> SensorListByTypeInAGeoArea = sortSensorsInAGeoAreaByType(type, sensorList);
        List<Sensor> sensorListByTypeInADay = new ArrayList<>();

        for (Sensor sensor : SensorListByTypeInAGeoArea) {
            if (sensor.temRegistosEntreDatas(beginningOfDay, endOfDay)) {
                sensorListByTypeInADay.add(sensor);
            }
        }
        return sensorListByTypeInADay;
    }

    public Sensor novoSensor(String nome, SensorType novoSensorType, Location novaLocalizacao) {
        return new Sensor(nome, novoSensorType, novaLocalizacao);
    }

    public Location novaLocalizacao(double mLatitude, double mLongitude, double mAltitude) {
        return new Location(mLatitude, mLongitude, mAltitude);
    }

    public Sensor getNearestSensorOfALocation (SensorList sensorList, Location location){
        Sensor nearestSensor = sensorList.getmSensorList().get(0);
        double shortestDistance = nearestSensor.distanceBetweenASensorAndALocation(location);
        for (Sensor sensor : sensorList.getmSensorList()) {
            if (shortestDistance > sensor.distanceBetweenASensorAndALocation(location)) {
                shortestDistance = sensor.distanceBetweenASensorAndALocation(location);
                nearestSensor = sensor;
            }
        }
        return nearestSensor;
    }

    public double getLastTemperatureInTheArea(Location location) {
        SensorType temperature = new SensorType("Temperature");

        GeographicalArea areaToBeUsed = new GeographicalArea(mNomeAreaGeo, mGeoAreaType, mLocation, mRectangleArea);
        areaToBeUsed.setmAreaInseridaEm(mAreaInseridaEm);
        areaToBeUsed.getmSensorListInTheGeographicArea().setmSensorList(mSensorList.getmSensorList());

        SensorList sensorList = new SensorList();
        sensorList.setmSensorList(sortSensorsInAGeoAreaByType(temperature, areaToBeUsed.getmSensorListInTheGeographicArea().getmSensorList()));
        while (sensorList.getmSensorList().isEmpty()) {
            if (areaToBeUsed.getmAreaInseridaEm() != null) {
                areaToBeUsed.getmSensorListInTheGeographicArea().setmSensorList(areaToBeUsed.getmAreaInseridaEm().getmSensorListInTheGeographicArea().getmSensorList());
                areaToBeUsed.setmAreaInseridaEm(areaToBeUsed.getmAreaInseridaEm().getmAreaInseridaEm());
                sensorList.setmSensorList(areaToBeUsed.getmSensorListInTheGeographicArea().getmSensorList());
            } else {
                return Double.NaN;
            }
        }
        if (getNearestSensorOfALocation(sensorList, location).getUltimoRegisto() == null) {
            return Double.NaN;
        }
        return getNearestSensorOfALocation(sensorList, location).getUltimoRegisto().getmValue();
    }

    /**
     * Method that returns de daily average of the measurements of a list of sensors
     *
     * @param sensorList
     * @param date
     * @return
     */
    public double getDailyAverageOfAListOfSensors(List<Sensor> sensorList, Date date) {
        double dailyAverage = Double.NaN;
        for (Sensor sensor : sensorList) {
            if (!(sensor.getDailyMeasurement(date).isEmpty())) {
                dailyAverage = sensor.getDailyAverage(date);
            }
        }
        return dailyAverage;
    }

    /**
     * Method that returns an ArrayList with the daily averages between two dates.
     *
     * @param sensorType
     * @param startDate
     * @param endDate
     * @return
     */
    public List<Double> getDailyAverageMeasurementInTheArea(SensorType sensorType, Date startDate, Date endDate) {
        LocalDate startDate1 = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate1 = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        List<Double> listOfDailyAverages = new ArrayList<>();
        List<Sensor> sensorListWithRightTypeDuringPeriod = listarSensoresDeUmTipoNaAGNumPeriodo(sensorType, this.mSensorList.getmSensorList(), startDate, endDate);

        for (LocalDate dateIterator = startDate1; dateIterator.isBefore(endDate1); dateIterator = dateIterator.plusDays(1)) {
            Date currentDate = Date.from(dateIterator.atStartOfDay(ZoneId.systemDefault()).toInstant());
            double dailyAverage = getDailyAverageOfAListOfSensors(sensorListWithRightTypeDuringPeriod, currentDate);
            if (!Double.isNaN(dailyAverage)) {
                listOfDailyAverages.add(getDailyAverageOfAListOfSensors(sensorListWithRightTypeDuringPeriod, currentDate));
            }
        }
        return listOfDailyAverages;
    }


    /**
     * Method that returns the Total Daily Measurement of a Sensor Type in The Geographic Area. This method considers
     * the maximum value of the Sensor on that Area. In case there's no sensors in that Area, it returns Double NaN.
     *
     * @param sensorType
     * @param day
     * @return
     */
    public double getTotalDailyMeasurementInTheArea(SensorType sensorType, Date day) {
        double totalDailyMeasurement = Double.NaN;
        List<Sensor> sensorListWithSameTypeDuringADay = sortSensorTypesOfAGeoAreaInADay(sensorType, this.mSensorList.getmSensorList(), day);
        if(!sensorListWithSameTypeDuringADay.isEmpty()) {
            for (Sensor sensor : sensorListWithSameTypeDuringADay) {
                if (totalDailyMeasurement < sensor.getTotalDailyMeasurements(day)) {
                    totalDailyMeasurement = sensor.getTotalDailyMeasurements(day);
                }
            }
        }
        return totalDailyMeasurement;
    }
}

