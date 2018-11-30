package Sprint_0;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Sensor {
    private String mNomeSensor;
    private Date mDataFuncionamento;
    private List<Medicao> mRegistos = new ArrayList<>();
    private TipoSensor mTipoSensor;
    private Localizacao mLocalizacao;

    public Sensor(String mNomeSensor, Date mDataFuncionamento, TipoSensor mTipoSensor, Localizacao mLocalizacao) {
        this.mNomeSensor = mNomeSensor;
        this.mDataFuncionamento = mDataFuncionamento;
        this.mTipoSensor = mTipoSensor;
        this.mLocalizacao = mLocalizacao;
    }

    public String getmNomeSensor() {
        return mNomeSensor;
    }

    public Date getmDataFuncionamento() {
        return mDataFuncionamento;
    }

    public TipoSensor getmTipoSensor() {
        return mTipoSensor;
    }

    public Localizacao getmLocalizacao() {
        return mLocalizacao;
    }

    public boolean equals(Object objeto) {
        if (this == objeto) {
            return true;
        }
        if (!(objeto instanceof Sensor)) {
            return false;
        }
        Sensor sensor = (Sensor) objeto;
        if (this.mNomeSensor.equals(sensor.mNomeSensor) && this.mDataFuncionamento.equals(sensor.mDataFuncionamento) && this.mTipoSensor.equals(sensor.mTipoSensor) && this.mLocalizacao.equals(sensor.mLocalizacao)) {
            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return 1;
    }

    public double distanciaLinearEntreDoisSensores(Sensor sensor1) {

        return this.mLocalizacao.distanciaDuasLocalizacoes(sensor1.mLocalizacao);
    }


    public List<Double> getValorRegistosEntreDatas(Date dataInicial, Date dataFinal) {

        List<Double> registosEntreDatas = new ArrayList<>();

        for (Medicao registo : mRegistos) {
            if (registo.getmDataHora().after(dataInicial) && registo.getmDataHora().before(dataFinal)) {
                registosEntreDatas.add(registo.getmValor());
            }
        }
        return registosEntreDatas;
    }

    public double getMenorRegistoDoMes(Date primeiroDiaMes, Date ultimoDiaMes) {

        List<Double> registosEntreDatas = getValorRegistosEntreDatas(primeiroDiaMes, ultimoDiaMes);
        double menorRegisto = registosEntreDatas.get(0);

        for (int i = 1; i < registosEntreDatas.size(); i++) {
            if (menorRegisto > registosEntreDatas.get(i)) {
                menorRegisto = registosEntreDatas.get(i);
            }
        }
        return menorRegisto;
    }

    public double getMaiorRegistoDoMes(Date primeiroDiaMes, Date ultimoDiaMes) {

        List<Double> registosEntreDatas = getValorRegistosEntreDatas(primeiroDiaMes, ultimoDiaMes);
        double maiorRegisto = registosEntreDatas.get(0);

        for (int i = 1; i < registosEntreDatas.size(); i++) {
            if (maiorRegisto < registosEntreDatas.get(i)) {
                maiorRegisto = registosEntreDatas.get(i);
            }
        }
        return maiorRegisto;
    }

    public double getRegistoMediaMes(Date primeiroDiaMes, Date ultimoDiaMes) {

        List<Double> registosEntreDatas = getValorRegistosEntreDatas(primeiroDiaMes, ultimoDiaMes);

        double numeroDeRegistos = registosEntreDatas.size();
        double somaRegistos = 0;

        for (Double registo : registosEntreDatas) {

            somaRegistos += registo;
        }
        return somaRegistos / numeroDeRegistos;
    }


    public void adicionarMedicaoALista(Medicao medicao) {
        mRegistos.add(medicao);
    }


    public Medicao getUltimoRegisto() {
        if (mRegistos.isEmpty()) {
            return null;
        }
        return mRegistos.get(mRegistos.size() - 1);
    }

    public boolean umTipoDeSensorEIgualAOutro(TipoSensor tipo){
        String tipoDoSensorPedido = tipo.getmTipo();
        if (this.getmTipoSensor().getmTipo().equals(tipoDoSensorPedido)) {
            return true;
        }
        return false;
    }





}
