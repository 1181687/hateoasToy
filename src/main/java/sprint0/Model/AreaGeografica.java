package sprint0.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AreaGeografica {
    private String mNomeAreaGeo;
    private TipoAreaGeo mTipoAreaGeo;
    private AreaGeografica mAreaInseridaEm;
    private Localizacao mLocalizacao;
    private RetanguloArea mRetanguloArea;
    private List<Sensor> mListaSensor = new ArrayList<>();

    public AreaGeografica(String mNomeAreaGeo, TipoAreaGeo mTipoAreaGeo, Localizacao mLocalizacao, RetanguloArea mRetanguloArea) {
        this.mNomeAreaGeo = mNomeAreaGeo;
        this.mTipoAreaGeo = mTipoAreaGeo;
        this.mLocalizacao = mLocalizacao;
        this.mRetanguloArea = mRetanguloArea;
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
        if (!(obj instanceof AreaGeografica)) {
            return false;
        }
        AreaGeografica ag = (AreaGeografica) obj;
        return this.mNomeAreaGeo.equals(ag.mNomeAreaGeo) && this.mTipoAreaGeo.equals(ag.mTipoAreaGeo) && this.mLocalizacao.equals(ag.mLocalizacao) && this.mRetanguloArea.equals(ag.mRetanguloArea);

    }

    public List<Sensor> getmListaSensor() {
        return mListaSensor;
    }

    public String getmNomeAreaGeo() {
        return mNomeAreaGeo;
    }

    public TipoAreaGeo getmTipoAreaGeo() {
        return mTipoAreaGeo;
    }

    public Localizacao getmLocalizacao() {
        return this.mLocalizacao;
    }

    public AreaGeografica getmAreaInseridaEm() {
        return mAreaInseridaEm;
    }

    public void setmAreaInseridaEm(AreaGeografica mAreaInseridaEm) {
        this.mAreaInseridaEm = mAreaInseridaEm;
    }

    public double distanciaLinearDuasAreas(AreaGeografica novoAg) {
        return this.mLocalizacao.distanciaDuasLocalizacoes(novoAg.getmLocalizacao());
    }

    public boolean adicionarSensorAListaDeSensores(Sensor sensor) {
        if (!(mListaSensor.contains(sensor))) {
            mListaSensor.add(sensor);
            return true;
        }
        return false;
    }

    public List<Medicao> getListaDeUltimosRegistosPorTipoDeSensor(TipoSensor tipo) {
        List<Medicao> listaDeUltimosRegistos = new ArrayList<>();
        for (Sensor sensor : mListaSensor) {
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

        return mRetanguloArea.verificaSeLocalizacaoEstaContidaNumaArea(sensor.getmLocalizacao());

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

    public List<Sensor> listarSensoresDeUmTipoNaAGNumPeriodo(TipoSensor tipo, List<Sensor> listaDeSensores, Date dataInicial, Date dataFinal){

        List <Sensor> listaSensoresContidosNaAGPorTipo= listarSensoresContidosNaAGPorTipo(tipo,listaDeSensores);
        List<Sensor> listaSensoresDeTipoNumPeriodo = new ArrayList<>();

        for (Sensor sensor : listaSensoresContidosNaAGPorTipo) {
            if (sensor.temRegistosEntreDatas(dataInicial,dataFinal)){
                listaSensoresDeTipoNumPeriodo.add(sensor);
            }
        }
        return listaSensoresDeTipoNumPeriodo;
    }
}


