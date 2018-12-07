package sprint0;

import java.text.SimpleDateFormat;
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
        return this.mNomeSensor.equals(sensor.mNomeSensor) && this.mDataFuncionamento.equals(sensor.mDataFuncionamento) && this.mTipoSensor.equals(sensor.mTipoSensor) && this.mLocalizacao.equals(sensor.mLocalizacao);
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

    public boolean temRegistosEntreDatas(Date dataInicial, Date dataFinal) {
        List<Double> registosEntreDatas = getValorRegistosEntreDatas(dataInicial, dataFinal);

        return !(registosEntreDatas.isEmpty());
    }

    public double getMenorRegistoDoMes(Date diaDoMes) {

        Date primeiroDiaMes = getPrimeiroDiaDoMes(diaDoMes);
        Date ultimoDiaMes = getUltimoDiaDoMes(diaDoMes);

        List<Double> registosEntreDatas = getValorRegistosEntreDatas(primeiroDiaMes, ultimoDiaMes);

        if(registosEntreDatas.isEmpty()){
            return Double.NaN;
        }
        double menorRegisto = registosEntreDatas.get(0);

        for (int i = 0; i < registosEntreDatas.size(); i++) {
            if (menorRegisto > registosEntreDatas.get(i)) {
                menorRegisto = registosEntreDatas.get(i);
            }
        }
        return menorRegisto;
    }

    public double getMaiorRegistoDoMes(Date diaDoMes) {

        Date primeiroDiaMes = getPrimeiroDiaDoMes(diaDoMes);
        Date ultimoDiaMes = getUltimoDiaDoMes(diaDoMes);

        List<Double> registosEntreDatas = getValorRegistosEntreDatas(primeiroDiaMes, ultimoDiaMes);

        if(registosEntreDatas.isEmpty()){
            return Double.NaN;
        }
        double maiorRegisto = registosEntreDatas.get(0);

        for (int i = 0; i < registosEntreDatas.size(); i++) {
            if (maiorRegisto < registosEntreDatas.get(i)) {
                maiorRegisto = registosEntreDatas.get(i);
            }
        }
        return maiorRegisto;
    }


    public double getRegistoMediaMes(Date diaDoMes) {

        Date primeiroDiaMes = getPrimeiroDiaDoMes(diaDoMes);
        Date ultimoDiaMes = getUltimoDiaDoMes(diaDoMes);

        List<Double> registosEntreDatas = getValorRegistosEntreDatas(primeiroDiaMes, ultimoDiaMes);

        int numeroDeRegistos = registosEntreDatas.size();
        double somaRegistos = 0;

        if (numeroDeRegistos == 0) {
            return Double.NaN;
        }

        for (Double registo : registosEntreDatas) {

            somaRegistos += registo;
        }
        return somaRegistos / numeroDeRegistos;
    }

    public Date getPrimeiroDiaDoMes(Date data) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    public Date getUltimoDiaDoMes(Date data) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, cal.getActualMaximum(Calendar.HOUR_OF_DAY));

        return cal.getTime();
    }

    public void adicionarMedicaoALista(Medicao medicao) {
        mRegistos.add(medicao);
    }

    public boolean listaDeRegistosEVazia() {
        return mRegistos.isEmpty();
    }

    public Medicao getUltimoRegisto() {
        for (int i = (mRegistos.size() - 1); i >= 0; i--) {
            if (!(Double.isNaN(mRegistos.get(i).getmValor()))) {
                return mRegistos.get(i);
            }
        }
        return null;
    }

    public boolean umTipoDeSensorEIgualAOutro(TipoSensor tipo) {
        String tipoDoSensorPedido = tipo.getmTipo();
        return (this.getmTipoSensor().getmTipo().equals(tipoDoSensorPedido));
    }

    public List<Medicao> getRegistosDoDia(Date data) {
        //passar data para calendario
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);

        List<Medicao>registosDoDia = new ArrayList<>();
        for (Medicao registo : mRegistos) {
            //passar Data do registo para Calendario
            Calendar ca2 = Calendar.getInstance();
            ca2.setTime(registo.getmDataHora());

            if (verificaDiasIguais(ca2, cal) && registo.getmValor() != Double.NaN) {
                registosDoDia.add(registo);
            }
        }
        return registosDoDia;

    }

    public boolean verificaDiasIguais (Calendar cal1, Calendar cal2){
        return (cal1.get(Calendar.YEAR)==cal2.get(Calendar.YEAR) && cal1.get(Calendar.MONTH)==cal2.get(Calendar.MONTH)
                && cal1.get(Calendar.DAY_OF_MONTH)==cal2.get(Calendar.DAY_OF_MONTH));
    }
}