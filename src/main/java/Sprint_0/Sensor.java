package Sprint_0;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Semaphore;


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

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Sensor)) {
            return false;
        }
        Sensor c = (Sensor) o;
        if (this.getmNomeSensor().equals(c.getmNomeSensor())) {
            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return 1;
    }

    public double distanciaLinearEntreDoisSensores (Sensor sensor1, Sensor sensor2){

        return sensor1.mLocalizacao.distanciaDuasLocalizacoes(sensor1.mLocalizacao, sensor2.mLocalizacao);
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
