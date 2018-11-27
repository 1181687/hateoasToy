package Sprint_0;

import java.util.ArrayList;
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
        if (this.mNomeSensor.equals(sensor.mNomeSensor) && this.mTipoSensor.equals(sensor.mTipoSensor) && this.mLocalizacao.equals(sensor.mLocalizacao)) {
            return true;
        } else {
            return false;
        }
    }

    public int hashCode(){
        return 1;
    }

// determinar temperatura/pluviosidade/humidade/vento/visibilidade média mínima mensal num dispositivo/sensor;

    /*
    public double getMediaMinimaMes(Date data) {

    }

    public double getMediaMaximaMes(Date data) {

    }
*/
    public double getMenorRegistoDia(Date dia) {

        List<Double> registosDoDia = new ArrayList<>();

        for (Medicao registo : mRegistos) {
            if (registo.getmDataHora().equals(dia)) {
                registosDoDia.add(registo.getmValor());
            }
        }
        double menorRegistoDia = registosDoDia.get(0);
        for (int i = 1; i < registosDoDia.size(); i++) {
            if (menorRegistoDia > registosDoDia.get(i)) {
                menorRegistoDia = registosDoDia.get(i);
            }
        }
        return menorRegistoDia;
    }

/*
    public double getMaiorTemperaturaDia(Date dia) {


    }
*/


    public void adicionarMedicaoALista(Medicao medicao) {
        mRegistos.add(medicao);
    }

    public List<Medicao> getmRegistos() {
        return mRegistos;
    }

    public Medicao getUltimoResultado(){
        if(mRegistos.isEmpty()){
            return null;
        }
        return mRegistos.get(mRegistos.size()-1);
    }

    }
}