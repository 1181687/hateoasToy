package pt.ipp.isep.dei.project.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GeographicalArea {
    private String mNomeAreaGeo;
    private GeoAreaType mGeoAreaType;
    private GeographicalArea mInsertedIn;
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

    public GeographicalArea getmInsertedIn() {
        return mInsertedIn;
    }

    public void setmInsertedIn(GeographicalArea mInsertedIn) {
        this.mInsertedIn = mInsertedIn;
    }

    public double distanciaLinearDuasAreas(GeographicalArea novoAg) {
        return this.mLocation.distanciaDuasLocalizacoes(novoAg.getmLocation());
    }

    public boolean verificarSeSensorEstaContidoNaAG(Sensor sensor) {

        return mRectangleArea.verificaSeLocalizacaoEstaContidaNumaArea(sensor.getmLocation());

    }

    public List<Sensor> listarSensoresContidosNaAGPorTipo(SensorType sensorType, List<Sensor> listaDeSensores) {

        List<Sensor> listaDeSensoresInseridos = new ArrayList<>();

        for (Sensor sensor : listaDeSensores) {

            if (verificarSeSensorEstaContidoNaAG(sensor) && sensor.getmSensorType().equals(sensorType)) {
                listaDeSensoresInseridos.add(sensor);
            }
        }
        return listaDeSensoresInseridos;
    }

    public List<Sensor> listarSensoresDeUmTipoNaAGNumPeriodo(SensorType tipo, List<Sensor> listaDeSensores, Date dataInicial, Date dataFinal) {

        List<Sensor> listaSensoresContidosNaAGPorTipo = listarSensoresContidosNaAGPorTipo(tipo, listaDeSensores);
        List<Sensor> listaSensoresDeTipoNumPeriodo = new ArrayList<>();

        for (Sensor sensor : listaSensoresContidosNaAGPorTipo) {
            if (sensor.temRegistosEntreDatas(dataInicial, dataFinal)) {
                listaSensoresDeTipoNumPeriodo.add(sensor);
            }
        }
        return listaSensoresDeTipoNumPeriodo;
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

    /**
     * @param type
     * @return
     */
    public SensorList getTheSensorListInTheFirstAreaWithSensorOfAGivenType(SensorType type) {
        GeographicalArea areaToBeUsed = new GeographicalArea(mNomeAreaGeo, mGeoAreaType, mLocation, mRectangleArea);
        areaToBeUsed.setmInsertedIn(mInsertedIn);
        areaToBeUsed.getmSensorListInTheGeographicArea().setmSensorList(mSensorList.getmSensorList());

        SensorList sensorList = new SensorList();
        sensorList.setmSensorList(listarSensoresContidosNaAGPorTipo(type, areaToBeUsed.getmSensorListInTheGeographicArea().getmSensorList()));
        while (sensorList.getmSensorList().isEmpty()) {
            if (areaToBeUsed.getmInsertedIn() != null) {
                areaToBeUsed.getmSensorListInTheGeographicArea().setmSensorList(areaToBeUsed.getmInsertedIn().getmSensorListInTheGeographicArea().getmSensorList());
                areaToBeUsed.setmInsertedIn(areaToBeUsed.getmInsertedIn().getmInsertedIn());
                sensorList.setmSensorList(areaToBeUsed.getmSensorListInTheGeographicArea().getmSensorList());
            } else {
                return sensorList;
            }
        }
        return sensorList;
    }

    /**
     * Method that returns the last measurement of a given type in the area.
     *
     * @param location Location to be used.
     * @param type     Sensor type.
     * @return Last measurement.
     */
    public double getTheLastMeasurementInTheArea(Location location, SensorType type) {
        SensorList sensorList = getTheSensorListInTheFirstAreaWithSensorOfAGivenType(type);
        if (!sensorList.getmSensorList().isEmpty()) {
            if (getNearestSensorOfALocation(sensorList, location).getUltimoRegisto() == null) {
                return Double.NaN;
            }
            return getNearestSensorOfALocation(sensorList, location).getUltimoRegisto().getmValue();
        }
        return Double.NaN;
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
        List<Double> dailyAverage = new ArrayList<>();
        List<Sensor> sensorListWithRightTypeDuringPeriod = listarSensoresDeUmTipoNaAGNumPeriodo(sensorType, this.mSensorList.getmSensorList(), startDate, endDate);

        for (Sensor sensor : sensorListWithRightTypeDuringPeriod) {

            for (LocalDate dateIterator = startDate1; dateIterator.isBefore(endDate1); dateIterator = dateIterator.plusDays(1)) {
                Date currentDate = Date.from(dateIterator.atStartOfDay(ZoneId.systemDefault()).toInstant());
                if (!(sensor.getDailyMeasurement(currentDate).isEmpty())) {
                    dailyAverage.add(sensor.getDailyAverage(currentDate));
                }
            }
        }
        return dailyAverage;

    }

    /*public double getTotalDailyMeasurementInTheArea(SensorType sensorType, Date day) {
        LocalDate day1 = day.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        List<Sensor> listOfSensorsInTheArea
        double sumOfDailyMeasurements = 0;


    }*/
}

