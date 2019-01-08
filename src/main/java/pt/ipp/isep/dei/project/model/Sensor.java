package pt.ipp.isep.dei.project.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;


public class Sensor {
    private String mSensorName;
    private LocalDateTime mStartingDate;
    private List<Measurement> mMeasurements = new ArrayList<>();
    private SensorType mSensorType;
    private Location mLocation;

    public Sensor(String mSensorName, LocalDateTime mStartingDate, SensorType mSensorType, Location mLocation) {
        this.mSensorName = mSensorName;
        this.mStartingDate = mStartingDate;
        this.mSensorType = mSensorType;
        this.mLocation = mLocation;
    }

    public Sensor(String mSensorName, SensorType mSensorType, Location mLocation) {
        this.mSensorName = mSensorName;
        this.mStartingDate = LocalDateTime.now();
        this.mSensorType = mSensorType;
        this.mLocation = mLocation;
    }

    public String getmSensorName() {
        return mSensorName;
    }

    public LocalDateTime getmStartingDate() {
        return mStartingDate;
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
        return (this.mSensorName.equals(sensor.mSensorName) && this.mSensorType.equals(sensor.mSensorType) && this.mLocation.equals(sensor.mLocation));
    }

    public int hashCode() {
        return 1;
    }

    public double distanciaLinearEntreDoisSensores(Sensor sensor1) {

        return this.mLocation.distanceBetweenTwoLocations(sensor1.mLocation);
    }


    public List<Double> getValuesOfMeasurementsBetweenDates(LocalDate startDate, LocalDate endDate) {

        List<Double> measurementsBetweenDates = new ArrayList<>();

        for (Measurement measurement : mMeasurements) {
            if (measurement.getmDateTime().toLocalDate().isAfter(startDate) && measurement.getmDateTime().toLocalDate().isBefore(endDate)) {
                measurementsBetweenDates.add(measurement.getmValue());
            }
        }
        return measurementsBetweenDates;
    }

    public boolean hasMeasurementBetweenDates(LocalDate startDate, LocalDate endDate) {
        List<Double> measurementsBetweenDates = getValuesOfMeasurementsBetweenDates(startDate, endDate);

        return !(measurementsBetweenDates.isEmpty());
    }

    public double getMenorRegistoDoMes(LocalDate dayOfMonth) {
        LocalDate firstDayOfMonth = dayOfMonth.withDayOfMonth(1);
        LocalDate lastDayOfMonth = dayOfMonth.withDayOfMonth(dayOfMonth.lengthOfMonth());

        List<Double> measurementsBetweenDates = getValuesOfMeasurementsBetweenDates(firstDayOfMonth, lastDayOfMonth);

        if (measurementsBetweenDates.isEmpty()) {
            return Double.NaN;
        }
        double smallestMeasurement = measurementsBetweenDates.get(0);

        for (int i = 0; i < measurementsBetweenDates.size(); i++) {
            if (smallestMeasurement > measurementsBetweenDates.get(i)) {
                smallestMeasurement = measurementsBetweenDates.get(i);
            }
        }
        return smallestMeasurement;
    }

    public double getMaiorRegisto(LocalDate date) {
        LocalDate firstDayOfMonth = date.withDayOfMonth(1);
        LocalDate lastDayOfMonth = date.withDayOfMonth(date.lengthOfMonth());

        List<Double> measurementsBetweenDates = getValuesOfMeasurementsBetweenDates(firstDayOfMonth, lastDayOfMonth);

        if (measurementsBetweenDates.isEmpty()) {
            return Double.NaN;
        }
        return measurementsBetweenDates.get(0);
    }

    public double getMaiorRegistoDoMes(LocalDate date) {
        LocalDate firstDayOfMonth = date.withDayOfMonth(1);
        LocalDate lastDayOfMonth = date.withDayOfMonth(date.lengthOfMonth());

        //Date primeiroDiaMes = getPrimeiroDiaDoMes(diaDoMes);
        //Date ultimoDiaMes = getUltimoDiaDoMes(diaDoMes);

        List<Double> measurementsBetweenDates = getValuesOfMeasurementsBetweenDates(firstDayOfMonth, lastDayOfMonth);

        double biggestMeasurement = getMaiorRegisto(date);

        for (int i = 0; i < measurementsBetweenDates.size(); i++) {
            if (biggestMeasurement < measurementsBetweenDates.get(i)) {
                biggestMeasurement = measurementsBetweenDates.get(i);
            }
        }
        return biggestMeasurement;
    }

    public double getRegistoMediaMes(LocalDate date) {
        LocalDate firstDayOfMonth = date.withDayOfMonth(1);
        LocalDate lastDayOfMonth = date.withDayOfMonth(date.lengthOfMonth());

        //Date primeiroDiaMes = getPrimeiroDiaDoMes(diaDoMes);
        //Date ultimoDiaMes = getUltimoDiaDoMes(diaDoMes);

        List<Double> measurementsBetweenDates = getValuesOfMeasurementsBetweenDates(firstDayOfMonth, lastDayOfMonth);

        int numberOfMeasurements = measurementsBetweenDates.size();
        double sumOfMeasurements = 0;

        if (numberOfMeasurements == 0) {
            return Double.NaN;
        }

        for (Double measurements : measurementsBetweenDates) {

            sumOfMeasurements += measurements;
        }
        return sumOfMeasurements / numberOfMeasurements;
    }

//    public Date getPrimeiroDiaDoMes(Date data) {
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(data);
//        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
//        return cal.getTime();
//    }
//
//    public Date getUltimoDiaDoMes(Date data) {
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(data);
//        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
//        cal.set(Calendar.HOUR_OF_DAY, cal.getActualMaximum(Calendar.HOUR_OF_DAY));
//
//        return cal.getTime();
//    }

    public void addMeasurementToList(Measurement measurement) {
        mMeasurements.add(measurement);
    }

    public boolean measurementListIsEmpty() {
        return mMeasurements.isEmpty();
    }

    public Measurement getUltimoRegisto() {
        for (int i = (mMeasurements.size() - 1); i >= 0; i--) {
            if (!(Double.isNaN(mMeasurements.get(i).getmValue()))) {
                return mMeasurements.get(i);
            }
        }
        return null;
    }

    public boolean umTipoDeSensorEIgualAOutro(SensorType tipo) {
        String tipoDoSensorPedido = tipo.getmTipo();
        return (this.getmSensorType().getmTipo().equals(tipoDoSensorPedido));
    }

    public List<Measurement> getDailyMeasurement(LocalDate date) {
        //passar data para calendario
        //Calendar cal = Calendar.getInstance();
        //cal.setTime(data);

        List<Measurement> registosDoDia = new ArrayList<>();
        for (Measurement registo : mMeasurements) {
            LocalDate secondDate = registo.getmDateTime().toLocalDate();
            //passar Data do registo para Calendario
            //Calendar ca2 = Calendar.getInstance();
            //ca2.setTime(registo.getmDateTime());

            if (checkIfSameDay(date, secondDate) && (!Double.isNaN(registo.getmValue()))) {
                registosDoDia.add(registo);
            }
        }
        return registosDoDia;

    }

    public boolean checkIfSameDay(LocalDate firstDate, LocalDate secondDate) {
        return (firstDate.isEqual(secondDate));
    }

    public double getValorMinimoDoDia(LocalDate data) {
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

    public LocalDate getFirstDayOfTheWeek(LocalDate date) {
        return (date.with(ChronoField.DAY_OF_WEEK, 1));
        //return date.getDayOfWeek().

//        Calendar cal = new GregorianCalendar(Locale.US);
//        cal.setWeekDate(ano, week, cal.getFirstDayOfWeek());
//        cal.set(Calendar.HOUR_OF_DAY, 0);
//        cal.set(Calendar.MINUTE, 0);
//        cal.set(Calendar.SECOND, 0);
//        cal.set(Calendar.MILLISECOND, 0);
//        return cal.getTime();
    }

    public List<Double> valoresMinimosSemana(LocalDate date) {
        List<Double> registosMinimosSemana = new ArrayList<>();
        LocalDate primeiroDiaSemana = getFirstDayOfTheWeek(date);
        for (LocalDate diaDaSemana = primeiroDiaSemana; diaDaSemana.isBefore(primeiroDiaSemana.plusDays(7)); diaDaSemana.plusDays(1)) {
            double minimoDia = getValorMinimoDoDia(diaDaSemana);
            if (!Double.isNaN(minimoDia)) {
                registosMinimosSemana.add(minimoDia);
            }
        }
        return registosMinimosSemana;
    }

    public double getMediaRegitosMinSemanal(LocalDate date) {

        List<Double> registosMinSemana = this.valoresMinimosSemana(date);

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

    /**
     * @param date a given day
     * @return the maximum value of measurements
     */
    public double getMaximumValueOfDay(LocalDate date) {
        if (!getDailyMeasurement(date).isEmpty()) {
            double valorMaximoDoDia = getDailyMeasurement(date).get(0).getmValue();
            for (Measurement registo : getDailyMeasurement(date)) {
                if (valorMaximoDoDia < registo.getmValue()) {
                    valorMaximoDoDia = registo.getmValue();
                }
            }
            return valorMaximoDoDia;
        }
        return Double.NaN;
    }

    public List<Double> valoresMaximosSemana(LocalDate date) {
        List<Double> registosMaximosSemana = new ArrayList<>();
        LocalDate primeiroDiaSemana = getFirstDayOfTheWeek(date);
        int contador = 1;
        while (contador < 8) {
            double maximoDia = getMaximumValueOfDay(primeiroDiaSemana);
            if (!Double.isNaN(maximoDia)) {
                registosMaximosSemana.add(maximoDia);
            }
            primeiroDiaSemana.plusDays(1);
            contador++;
        }
        return registosMaximosSemana;
    }

    public double getMediaRegistosMaxSemanal(LocalDate date) {

        List<Double> registosMaxSemana = this.valoresMaximosSemana(date);

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
        return this.mLocation.distanceBetweenTwoLocations(location);
    }

    /**
     * Method that returns the daily measurement of a given day
     *
     * @param day
     * @return
     */

    public double getTotalDailyMeasurements(LocalDate day) {
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
    public double getDailyAverage(LocalDate date) {
        return getTotalDailyMeasurements(date) / getDailyMeasurement(date).size();
    }


}