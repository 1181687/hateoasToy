package pt.ipp.isep.dei.project.model;

import java.util.*;


public class Sensor {
    private String mNomeSensor;
    private Date mDataFuncionamento;
    private List<Measurement> mRegistos = new ArrayList<>();
    private SensorType mSensorType;
    private Location mLocation;

    public Sensor(String mNomeSensor, Date mDataFuncionamento, SensorType mSensorType, Location mLocation) {
        this.mNomeSensor = mNomeSensor;
        this.mDataFuncionamento = mDataFuncionamento;
        this.mSensorType = mSensorType;
        this.mLocation = mLocation;
    }

    public Sensor(String mNomeSensor, SensorType mSensorType, Location mLocation) {
        this.mNomeSensor = mNomeSensor;
        this.mDataFuncionamento = new GregorianCalendar(Locale.getDefault()).getTime();
        this.mSensorType = mSensorType;
        this.mLocation = mLocation;
    }

    public String getmNomeSensor() {
        return mNomeSensor;
    }

    public Date getmDataFuncionamento() {
        return mDataFuncionamento;
    }

    public SensorType getmSensorType() {
        return mSensorType;
    }

    public Location getmLocation() {
        return mLocation;
    }

    public boolean equals(Object objeto) {
        if (this == objeto) {
            return true;
        }
        if (!(objeto instanceof Sensor)) {
            return false;
        }
        Sensor sensor = (Sensor) objeto;
        return (this.mNomeSensor.equals(sensor.mNomeSensor) && this.mSensorType.equals(sensor.mSensorType) && this.mLocation.equals(sensor.mLocation));
    }

    public int hashCode() {
        return 1;
    }

    public double distanciaLinearEntreDoisSensores(Sensor sensor1) {

        return this.mLocation.distanciaDuasLocalizacoes(sensor1.mLocation);
    }


    public List<Double> getValorRegistosEntreDatas(Date dataInicial, Date dataFinal) {

        List<Double> registosEntreDatas = new ArrayList<>();

        for (Measurement registo : mRegistos) {
            if (registo.getmDateTime().after(dataInicial) && registo.getmDateTime().before(dataFinal)) {
                registosEntreDatas.add(registo.getmValue());
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

    public void addMeasurementToList(Measurement measurement) {
        mRegistos.add(measurement);
    }

    public boolean listaDeRegistosEVazia() {
        return mRegistos.isEmpty();
    }

    public Measurement getUltimoRegisto() {
        for (int i = (mRegistos.size() - 1); i >= 0; i--) {
            if (!(Double.isNaN(mRegistos.get(i).getmValue()))) {
                return mRegistos.get(i);
            }
        }
        return null;
    }

    public boolean umTipoDeSensorEIgualAOutro(SensorType tipo) {
        String tipoDoSensorPedido = tipo.getmTipo();
        return (this.getmSensorType().getmTipo().equals(tipoDoSensorPedido));
    }

    public List<Measurement> getDailyMeasurement(Date data) {
        //passar data para calendario
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);

        List<Measurement> registosDoDia = new ArrayList<>();
        for (Measurement registo : mRegistos) {
            //passar Data do registo para Calendario
            Calendar ca2 = Calendar.getInstance();
            ca2.setTime(registo.getmDateTime());

            if (verificaDiasIguais(ca2, cal) && (!Double.isNaN(registo.getmValue()))) {
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
        if (!getDailyMeasurement(data).isEmpty()) {
            double valorMinimoDoDia = getDailyMeasurement(data).get(0).getmValue();
            for (Measurement registo : getDailyMeasurement(data)) {
                if (valorMinimoDoDia > registo.getmValue()) {
                    valorMinimoDoDia = registo.getmValue();
                }
            }
            return valorMinimoDoDia;
        }
        return Double.NaN;
    }

    public Date getPrimeiroDiaSemana(int ano, int semana) {
        Calendar cal = new GregorianCalendar(Locale.US);
        cal.setWeekDate(ano, semana, cal.getFirstDayOfWeek());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public List<Double> valoresMinimosSemana(int ano, int semana) {
        List<Double> registosMinimosSemana = new ArrayList<>();
        Date primeiroDiaSemana = getPrimeiroDiaSemana(ano, semana);
        Calendar cal = new GregorianCalendar(Locale.US);
        cal.setTime(primeiroDiaSemana);
        int primeiroDiaDaSemana = cal.getFirstDayOfWeek();
        for (int diaDaSemana = primeiroDiaDaSemana; diaDaSemana < primeiroDiaDaSemana + 7; diaDaSemana++) {
            double minimoDia = getValorMinimoDoDia(cal.getTime());
            if (!Double.isNaN(minimoDia)) {
                registosMinimosSemana.add(minimoDia);
            }
            cal.add(Calendar.DAY_OF_WEEK, 1);
        }
        return registosMinimosSemana;
    }

    public double getMediaRegitosMinSemanal(int ano, int semana) {

        List<Double> registosMinSemana = this.valoresMinimosSemana(ano, semana);

        int contador = 0;
        double somaRegistosMinSemana = 0;
        if (registosMinSemana.size() == 0) {
            return Double.NaN;
        }
        while (contador < registosMinSemana.size()) {
            somaRegistosMinSemana += registosMinSemana.get(contador);
            contador++;
        }
        return somaRegistosMinSemana / registosMinSemana.size();

    }

    public double getValorMaximoDoDia(Date data) {
        if (!getDailyMeasurement(data).isEmpty()) {
            double valorMaximoDoDia = getDailyMeasurement(data).get(0).getmValue();
            for (Measurement registo : getDailyMeasurement(data)) {
                if (valorMaximoDoDia < registo.getmValue()) {
                    valorMaximoDoDia = registo.getmValue();
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

    public double getMediaRegistosMaxSemanal(int ano, int semana) {

        List<Double> registosMaxSemana = this.valoresMaximosSemana(ano, semana);

        int contador = 0;
        double somaRegistosMaxSemana = 0;
        if (registosMaxSemana.isEmpty()) {
            return Double.NaN;
        }
        while (contador < registosMaxSemana.size()) {
            somaRegistosMaxSemana += registosMaxSemana.get(contador);
            contador++;
        }
        return somaRegistosMaxSemana / registosMaxSemana.size();
    }

    public double distanceBetweenASensorAndALocation(Location location) {
        return this.mLocation.distanciaDuasLocalizacoes(location);
    }

    /**
     * Method that returns the daily measurement of a given day
     *
     * @param day
     * @return
     */

    public double getTotalDailyMeasurements(Date day) {
        double sum = 0;
        if (!(getDailyMeasurement(day).isEmpty())) {
            for (Measurement measurement : getDailyMeasurement(day)) {
                sum += measurement.getmValue();
            }
        }
        return sum;
    }

    /**
     * Method that returns the daily average on a given date.
     *
     * @param date
     * @return
     */
    public double getDailyAverage(Date date) {
        return getTotalDailyMeasurements(date) / getDailyMeasurement(date).size();
    }


}