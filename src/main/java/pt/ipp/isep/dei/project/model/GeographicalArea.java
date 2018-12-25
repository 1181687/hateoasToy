package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GeographicalArea {
    private String mNomeAreaGeo;
    private TipoAreaGeo mTipoAreaGeo;
    private GeographicalArea mAreaInseridaEm;
    private Location mLocation;
    private RectangleArea mRectangleArea;
    private SensorList mSensorList = new SensorList();

    public GeographicalArea(String mNomeAreaGeo, TipoAreaGeo mTipoAreaGeo, Location mLocation, RectangleArea mRectangleArea) {
        this.mNomeAreaGeo = mNomeAreaGeo;
        this.mTipoAreaGeo = mTipoAreaGeo;
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
        return this.mNomeAreaGeo.equals(ag.mNomeAreaGeo) && this.mTipoAreaGeo.equals(ag.mTipoAreaGeo) && this.mLocation.equals(ag.mLocation) && this.mRectangleArea.equals(ag.mRectangleArea);

    }


    public String getmNomeAreaGeo() {
        return mNomeAreaGeo;
    }

    public TipoAreaGeo getmTipoAreaGeo() {
        return mTipoAreaGeo;
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


    public List<Medicao> getListaDeUltimosRegistosPorTipoDeSensor(TipoSensor tipo) {
        List<Medicao> listaDeUltimosRegistos = new ArrayList<>();
        for (Sensor sensor : mSensorList.getmSensorList()) {
            if (sensor.listaDeRegistosEVazia()) {
                break;
            }
            if (sensor.umTipoDeSensorEIgualAOutro(tipo) && (!(Double.isNaN(sensor.getUltimoRegisto().getmValor())))) {
                listaDeUltimosRegistos.add(sensor.getUltimoRegisto());
            }
        }
        return listaDeUltimosRegistos;
    }

    public double getUltimoRegistoDeUmTipoDeSensor(TipoSensor tipo) {
        List<Medicao> listaDeUltimosRegisto = getListaDeUltimosRegistosPorTipoDeSensor(tipo);
        if (getListaDeUltimosRegistosPorTipoDeSensor(tipo).isEmpty()) {
            return Double.NaN;
        }
        Medicao medicaoComUltimoRegisto = listaDeUltimosRegisto.get(0);
        for (Medicao registo : listaDeUltimosRegisto) {
            if (registo.getmDataHora().after(medicaoComUltimoRegisto.getmDataHora())) {
                medicaoComUltimoRegisto = registo;
            }
        }
        return medicaoComUltimoRegisto.getmValor();
    }

    public boolean verificarSeSensorEstaContidoNaAG(Sensor sensor) {

        return mRectangleArea.verificaSeLocalizacaoEstaContidaNumaArea(sensor.getmLocation());

    }

    public List<Sensor> listarSensoresContidosNaAGPorTipo(TipoSensor tipoSensor, List<Sensor> listaDeSensores) {

        List<Sensor> listaDeSensoresInseridos = new ArrayList<>();

        for (Sensor sensor : listaDeSensores) {

            if (verificarSeSensorEstaContidoNaAG(sensor) && sensor.getmTipoSensor().equals(tipoSensor)) {
                listaDeSensoresInseridos.add(sensor);
            }
        }
        return listaDeSensoresInseridos;
    }

    public List<Sensor> listarSensoresDeUmTipoNaAGNumPeriodo(TipoSensor tipo, List<Sensor> listaDeSensores, Date dataInicial, Date dataFinal) {

        List<Sensor> listaSensoresContidosNaAGPorTipo = listarSensoresContidosNaAGPorTipo(tipo, listaDeSensores);
        List<Sensor> listaSensoresDeTipoNumPeriodo = new ArrayList<>();

        for (Sensor sensor : listaSensoresContidosNaAGPorTipo) {
            if (sensor.temRegistosEntreDatas(dataInicial, dataFinal)) {
                listaSensoresDeTipoNumPeriodo.add(sensor);
            }
        }
        return listaSensoresDeTipoNumPeriodo;
    }

    public Sensor novoSensor(String nome, TipoSensor novoTipoSensor, Location novaLocalizacao) {
        return new Sensor(nome, novoTipoSensor, novaLocalizacao);
    }

    public Location novaLocalizacao(double mLatitude, double mLongitude, double mAltitude) {
        return new Location(mLatitude, mLongitude, mAltitude);
    }

    public double getLastTemperatureInTheArea(Location location) {
        TipoSensor temperature = new TipoSensor("Temperature");

        GeographicalArea areaToBeUsed = new GeographicalArea(mNomeAreaGeo, mTipoAreaGeo, mLocation, mRectangleArea);
        areaToBeUsed.setmAreaInseridaEm(mAreaInseridaEm);
        areaToBeUsed.getmSensorListInTheGeographicArea().setmSensorList(mSensorList.getmSensorList());

        SensorList sensorList = new SensorList();
        sensorList.setmSensorList(listarSensoresContidosNaAGPorTipo(temperature, areaToBeUsed.getmSensorListInTheGeographicArea().getmSensorList()));
        while (sensorList.getmSensorList().isEmpty()) {
            if (areaToBeUsed.getmAreaInseridaEm() != null) {
                areaToBeUsed.getmSensorListInTheGeographicArea().setmSensorList(areaToBeUsed.getmAreaInseridaEm().getmSensorListInTheGeographicArea().getmSensorList());
                areaToBeUsed.setmAreaInseridaEm(areaToBeUsed.getmAreaInseridaEm().getmAreaInseridaEm());
                sensorList.setmSensorList(areaToBeUsed.getmSensorListInTheGeographicArea().getmSensorList());
            } else {
                return Double.NaN;
            }
        }
        Sensor nearestSensor = sensorList.getmSensorList().get(0);
        double shortestDistance = nearestSensor.distanceBetweenASensorAndALocation(location);
        for (Sensor sensor : sensorList.getmSensorList()) {
            if (shortestDistance > sensor.distanceBetweenASensorAndALocation(location)) {
                shortestDistance = sensor.distanceBetweenASensorAndALocation(location);
                nearestSensor = sensor;
            }
        }
        if (nearestSensor.getUltimoRegisto() == null) {
            return Double.NaN;
        }
        return nearestSensor.getUltimoRegisto().getmValor();
    }
}