package sprint0.Model;

import javax.xml.crypto.Data;
import java.util.*;


public class Sensor {
    private String mNomeSensor;
    private Date mDataFuncionamento;
    private List<Medicao> mRegistos = new ArrayList<>();
    private TipoSensor mTipoSensor;
    private Localizacao mLocalizacao;

    public Sensor () {
    }

    public Sensor(String mNomeSensor, Date mDataFuncionamento, TipoSensor mTipoSensor, Localizacao mLocalizacao) {
        this.mNomeSensor = mNomeSensor;
        this.mDataFuncionamento = mDataFuncionamento;
        this.mTipoSensor = mTipoSensor;
        this.mLocalizacao = mLocalizacao;
    }

    public void setmNomeSensor (String nome) {
        this.mNomeSensor = nome;
    }

    public void setmDataFuncionamento (Date dataFuncionamento) {
        this.mDataFuncionamento = dataFuncionamento;
    }

    public void setmTipoSensor (TipoSensor tipoSensor) {
        this.mTipoSensor = tipoSensor;
    }

    public void setmLocalizacao (Localizacao localizacao) {
        this.mLocalizacao = localizacao;
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

        if (registosEntreDatas.isEmpty()) {
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

        if (registosEntreDatas.isEmpty()) {
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

        List<Medicao> registosDoDia = new ArrayList<>();
        for (Medicao registo : mRegistos) {
            //passar Data do registo para Calendario
            Calendar ca2 = Calendar.getInstance();
            ca2.setTime(registo.getmDataHora());

            if (verificaDiasIguais(ca2, cal) && (! Double.isNaN(registo.getmValor()))) {
                registosDoDia.add(registo);
            }
        }
        return registosDoDia;

    }

    public boolean verificaDiasIguais(Calendar cal1, Calendar cal2) {
        return (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)
                && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH));
    }

    public double getValorMinimoDoDia(Date data) {
        if (!getRegistosDoDia(data).isEmpty()) {
            double valorMinimoDoDia = getRegistosDoDia(data).get(0).getmValor();
            for (Medicao registo : getRegistosDoDia(data)) {
                if (valorMinimoDoDia > registo.getmValor()){
                    valorMinimoDoDia = registo.getmValor();
                }
            }
            return valorMinimoDoDia;
        }
        return Double.NaN;
    }

    public Date getPrimeiroDiaSemana (int ano, int semana){
        Calendar cal = new GregorianCalendar(Locale.US);
        cal.setWeekDate(ano, semana, cal.getFirstDayOfWeek());
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        return cal.getTime();
    }


    public List <Double> valoresMinimosSemana(int ano, int semana){
        List <Double> registosMinimosSemana = new ArrayList<>();
        Date primeiroDiaSemana = getPrimeiroDiaSemana(ano,semana);
        Calendar cal = new GregorianCalendar(Locale.US);
        cal.setTime(primeiroDiaSemana);
        int primeiroDiaDaSemana = cal.getFirstDayOfWeek();
        for (int diaDaSemana= primeiroDiaDaSemana; diaDaSemana<primeiroDiaDaSemana+7; diaDaSemana++) {
            double minimoDia = getValorMinimoDoDia(cal.getTime());
            if (!Double.isNaN(minimoDia)) {
                registosMinimosSemana.add(minimoDia);
            }
            cal.add(Calendar.DAY_OF_WEEK, 1);
        }
        return registosMinimosSemana;
    }

    public double getMediaRegitosMinSemanal (int ano, int semana){

        List <Double> registosMinSemana= this.valoresMinimosSemana(ano, semana);

        int contador=0;
        double somaRegistosMinSemana=0;
        if (registosMinSemana.size()==0){
            return Double.NaN;
        }
        while (contador<registosMinSemana.size()){
            somaRegistosMinSemana += registosMinSemana.get(contador);
            contador++;
        }
        return somaRegistosMinSemana/registosMinSemana.size();

    }

    public double getValorMaximoDoDia(Date data) {
        if (!getRegistosDoDia(data).isEmpty()) {
            double valorMaximoDoDia = getRegistosDoDia(data).get(0).getmValor();
            for (Medicao registo : getRegistosDoDia(data)) {
                if (valorMaximoDoDia < registo.getmValor()){
                    valorMaximoDoDia = registo.getmValor();
                }
            }
            return valorMaximoDoDia;
        }
        return Double.NaN;
    }

    public List<Double> valoresMaximosSemana(int ano, int semana) {
        List<Double> registosMaximosSemana = new ArrayList<>();
        Date primeiroDiaSemana = getPrimeiroDiaSemana(ano, semana);
        Calendar cal = new GregorianCalendar(Locale.US);
        cal.setTime(primeiroDiaSemana);
        int contador = 1;
        while (contador < 8) {
            double maximoDia = getValorMaximoDoDia(cal.getTime());
            if (!Double.isNaN(maximoDia)) {
                registosMaximosSemana.add(maximoDia);
            }
            cal.add(Calendar.DAY_OF_WEEK, 1);
            contador++;
        }
        return registosMaximosSemana;
    }

    public double getMediaRegistosMaxSemanal (int ano, int semana){

        List <Double> registosMaxSemana= this.valoresMaximosSemana(ano, semana);

        int contador=0;
        double somaRegistosMaxSemana=0;
        if (registosMaxSemana.isEmpty()){
            return Double.NaN;
        }
        while (contador<registosMaxSemana.size()){
            somaRegistosMaxSemana += registosMaxSemana.get(contador);
            contador++;
        }
        return somaRegistosMaxSemana/registosMaxSemana.size();
    }
}