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

    public double distanciaLinearEntreDoisSensores (Sensor sensor1){

        return this.mLocalizacao.distanciaDuasLocalizacoes(sensor1.mLocalizacao);
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


    public double getMaiorRegistoDia(Date dia) {

        List<Double> registosDoDia = new ArrayList<>();

        for (Medicao registo : this.mRegistos) {
            if (eMesmoDia(registo.getmDataHora(),dia)) {
                registosDoDia.add(registo.getmValor());
            }
        }
        double maiorRegistoDia = registosDoDia.get(0);

        for (int i = 1; i < registosDoDia.size(); i++) {
            if (maiorRegistoDia < registosDoDia.get(i)) {
                maiorRegistoDia = registosDoDia.get(i);
            }
        }
        return maiorRegistoDia;
    }

    public boolean eMesmoDia(Date data1, Date data2){

        Calendar calendario1 = Calendar.getInstance();
        calendario1.setTime(data1);
        int dia1 = calendario1.get(Calendar.DAY_OF_MONTH);
        int mes1 = calendario1.get(Calendar.MONTH);
        int ano1 = calendario1.get(Calendar.YEAR);

        Calendar calendario2 = Calendar.getInstance();
        calendario2.setTime(data2);
        int dia2 = calendario2.get(Calendar.DAY_OF_MONTH);
        int mes2 = calendario2.get(Calendar.MONTH);
        int ano2 = calendario2.get(Calendar.YEAR);

        if (dia1 == dia2 && mes1 == mes2 && ano1 == ano2) {
            return true;
        }
        return false;
    }

    public boolean eMesmoMes(Date data1, Date data2){

        Calendar calendario1 = Calendar.getInstance();
        calendario1.setTime(data1);
        int mes1 = calendario1.get(Calendar.MONTH);
        int ano1 = calendario1.get(Calendar.YEAR);

        Calendar calendario2 = Calendar.getInstance();
        calendario2.setTime(data2);
        int mes2 = calendario2.get(Calendar.MONTH);
        int ano2 = calendario2.get(Calendar.YEAR);

        if (mes1 == mes2 && ano1 == ano2) {
            return true;
        }
        return false;
    }


    public void adicionarMedicaoALista(Medicao medicao) {
        mRegistos.add(medicao);
    }


    public Medicao getUltimoRegisto(){
        if(mRegistos.isEmpty()){
            return null;
        }
        return mRegistos.get(mRegistos.size()-1);
    }


}
