package Sprint_0;

import java.util.ArrayList;
import java.util.List;

public class AreaGeografica {
    private String mNomeAreaGeo;
    private TipoAreaGeo mTipoAreaGeo;
    private AreaGeografica mAreaInserida;
    private Localizacao mLocalizacao;
    private RetanguloArea mRetanguloArea;
    private List<Sensor> mListaSensor= new ArrayList<>();

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
        if (this.mNomeAreaGeo.equals(ag.mNomeAreaGeo) && this.mTipoAreaGeo.equals(ag.mTipoAreaGeo) && this.mLocalizacao.equals(ag.mLocalizacao) && this.mRetanguloArea.equals(ag.mRetanguloArea)) {
            return true;
        }
        return false;
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
        String tipoDeSensorPedido = tipo.getmTipo();
        for (Sensor sensor : mListaSensor) {
            if ((tipoDeSensorPedido).equals(sensor.getmTipoSensor().getmTipo())) {
                listaDeUltimosRegistos.add(sensor.getUltimoRegisto());
            }
        }
        if(listaDeUltimosRegistos.isEmpty()){
            return null;
        }
        return listaDeUltimosRegistos;
    }

    public double getUltimoRegistoDeUmTipoDeSensor(TipoSensor tipo){
        List<Medicao> listaDeUltimosRegisto=getListaDeUltimosRegistosPorTipoDeSensor(tipo);
        Medicao medicaoComUltimoRegisto = listaDeUltimosRegisto.get(0);
        for (Medicao registo:listaDeUltimosRegisto){
            if(registo.getmDataHora().after(medicaoComUltimoRegisto.getmDataHora())){
                medicaoComUltimoRegisto=registo;
            }
        }
        return medicaoComUltimoRegisto.getmValor();

        }



}


