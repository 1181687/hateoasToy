package sprint0;

import java.util.ArrayList;
import java.util.List;

public class AreaGeografica {
    private String mNomeAreaGeo;
    private TipoAreaGeo mTipoAreaGeo;
    private AreaGeografica mAreaInserida;
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

    public Localizacao getmLocalizacao() {
        return this.mLocalizacao;
    }

    public double distanciaLinearDuasAreas(AreaGeografica novoAg) {
        return this.mLocalizacao.distanciaDuasLocalizacoes(novoAg.getmLocalizacao());
    }

    public void adicionarSensorAListaDeSensores(Sensor sensor) {
        mListaSensor.add(sensor);
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

}


