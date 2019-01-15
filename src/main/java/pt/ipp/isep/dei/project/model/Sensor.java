package pt.ipp.isep.dei.project.model;

import java.time.DayOfWeek;
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


    /**
     * Constructor method
     *
     * @param mSensorName   name of the sensor (string)
     * @param mStartingDate starting date of the sensor
     * @param mSensorType   Type of Sensor
     * @param mLocation     Locatiom of the Sensor
     */
    public Sensor(String mSensorName, LocalDateTime mStartingDate, SensorType mSensorType, Location mLocation) {
        this.mSensorName = mSensorName;
        this.mStartingDate = mStartingDate;
        this.mSensorType = mSensorType;
        this.mLocation = mLocation;
    }

    /**
     * Constructor method
     *
     * @param mSensorName name of the sensor (string)
     * @param mSensorType Type of Sensor
     * @param mLocation   Locatiom of the Sensor
     */
    public Sensor(String mSensorName, SensorType mSensorType, Location mLocation) {
        this.mSensorName = mSensorName;
        this.mStartingDate = LocalDateTime.now();
        this.mSensorType = mSensorType;
        this.mLocation = mLocation;
    }

    /**
     * Get method
     *
     * @return Sensor Name
     */
    public String getmSensorName() {
        return mSensorName;
    }

    /**
     * Get method
     *
     * @return Starting date of a sensor
     */
    public LocalDateTime getmStartingDate() {
        return mStartingDate;
    }

    /**
     * Get method
     *
     * @return Sensor Type
     */
    public SensorType getmSensorType() {
        return mSensorType;
    }

    /**
     * Get method
     *
     * @return Sensor Location
     */
    public Location getmLocation() {
        return mLocation;
    }

    /**
     * Equals method to determine if two Sensors are equal. They are equals if all atributtes are equal
     *
     * @param objeto receives an object
     * @return boolean
     */
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

    /**
     * method that creates the same hashcode to Sensors with the same attribute
     *
     * @return the hashcode created
     */
    public int hashCode() {
        return 1;
    }

    /**
     * Method that calculates the distance between two locations
     *
     * @param sensor1 a sensor
     * @return distance between two locations
     */
    public double distanceBetweenTwoLocations(Sensor sensor1) {

        return this.mLocation.distanceBetweenTwoLocations(sensor1.mLocation);
    }

    /**
     * Method that goes through a list of measurements and adds the value of the measurements to a list if they are equal or after the starting date and equal or before the end date
     *
     * @param startDate starting date of measurements
     * @param endDate   end date of measurements
     * @return measurements between two dates
     */
    public List<Double> getMeasurementValueBetweenDates(LocalDate startDate, LocalDate endDate) {

        List<Double> measurementsBetweenDates = new ArrayList<>();

        for (Measurement measurement : mMeasurements) {
            if ((measurement.getmDateTime().toLocalDate().isEqual(startDate) || measurement.getmDateTime().toLocalDate().isAfter(startDate)) && (measurement.getmDateTime().toLocalDate().isEqual(endDate) || measurement.getmDateTime().toLocalDate().isBefore(endDate))) {
                measurementsBetweenDates.add(measurement.getmValue());
            }
        }
        return measurementsBetweenDates;
    }

    /**
     * Boolean method that checks if a list of measurements between two dates (start and end) is not empty
     *
     * @param startDate starting date of measurements
     * @param endDate   end date of measurements
     * @return existence of measurement between two dates
     */
    public boolean checkMeasurementExistenceBetweenDates(LocalDate startDate, LocalDate endDate) {
        List<Double> measurementsBetweenDates = getMeasurementValueBetweenDates(startDate, endDate);

        return !(measurementsBetweenDates.isEmpty());
    }

    /**
     * Method that goes through the list of measurements between two dates and, if it is not empty, finds the smallest measure
     *
     * @param dayOfMonth specific day of the month
     * @return smallest measurement of a month
     */
    public double getSmallestMeasurementOfMonth(LocalDate dayOfMonth) {
        LocalDate firstDayOfMonth = dayOfMonth.withDayOfMonth(1);
        LocalDate lastDayOfMonth = dayOfMonth.withDayOfMonth(dayOfMonth.lengthOfMonth());

        List<Double> measurementsBetweenDates = getMeasurementValueBetweenDates(firstDayOfMonth, lastDayOfMonth);

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

    /**
     * Method that returns the biggest measurement
     *
     * @param date a date
     * @return
     */
    public double getBiggestMeasurement(LocalDate date) {
        LocalDate firstDayOfMonth = date.withDayOfMonth(1);
        LocalDate lastDayOfMonth = date.withDayOfMonth(date.lengthOfMonth());

        List<Double> measurementsBetweenDates = getMeasurementValueBetweenDates(firstDayOfMonth, lastDayOfMonth);

        if (measurementsBetweenDates.isEmpty()) {
            return Double.NaN;
        }
        double biggestMeasurement = measurementsBetweenDates.get(0);

        for (Double measurement : measurementsBetweenDates) {
            if (measurement > biggestMeasurement) {
                biggestMeasurement = measurement;
            }
        }
        return biggestMeasurement;
    }

    /**
     * Method that goes through the list of measurements between two dates and gets the biggest measure
     *
     * @param date a date
     * @return biggest measurement of a month
     */
    public double getBiggestMeasurementOfMonth(LocalDate date) {
        LocalDate firstDayOfMonth = date.withDayOfMonth(1);
        LocalDate lastDayOfMonth = date.withDayOfMonth(date.lengthOfMonth());

        List<Double> measurementsBetweenDates = getMeasurementValueBetweenDates(firstDayOfMonth, lastDayOfMonth);

        double biggestMeasurement = getBiggestMeasurement(date);

        for (int i = 0; i < measurementsBetweenDates.size(); i++) {
            if (biggestMeasurement < measurementsBetweenDates.get(i)) {
                biggestMeasurement = measurementsBetweenDates.get(i);
            }
        }
        return biggestMeasurement;
    }

    /**
     * Method that calculates the monthly average measurement going trough all the measurements between two dates (first and last day of the month),
     *
     * @param date a date
     * @return Monthly Average measurement
     */
    public double getMonthlyAverageMeasurement(LocalDate date) {
        LocalDate firstDayOfMonth = date.withDayOfMonth(1);
        LocalDate lastDayOfMonth = date.withDayOfMonth(date.lengthOfMonth());

        List<Double> measurementsBetweenDates = getMeasurementValueBetweenDates(firstDayOfMonth, lastDayOfMonth);

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

    /**
     * Method that adds a measurement to a list of measurements
     *
     * @param measurement measurement of a sensor
     */
    public void addMeasurementToList(Measurement measurement) {
        mMeasurements.add(measurement);
    }

    /**
     * Boolean method that checks if a list of measurements is empty
     *
     * @return empty list of measurements
     */
    public boolean measurementListIsEmpty() {
        return mMeasurements.isEmpty();
    }

    /**
     * Get method for the last measurement of a sensor
     *
     * @return last measurement
     */
    public Measurement getLastMeasurement() {
        for (int i = (mMeasurements.size() - 1); i >= 0; i--) {
            if (!(Double.isNaN(mMeasurements.get(i).getmValue()))) {
                return mMeasurements.get(i);
            }
        }
        return null;
    }

    /**
     * Boolean method that compares a Sensor Type to another Sensor and they are equal
     *
     * @param tipo Type of Sensor
     * @return a type of sensor is equal to the sensor type being compared
     */
    public boolean sensorTypeEqualsSensorType(SensorType tipo) {
        String tipoDoSensorPedido = tipo.getmType();
        return (this.getmSensorType().getmType().equals(tipoDoSensorPedido));
    }

    /**
     * Method that gets the measurements of a given day (if they are not invalid (double nan) and belong to the given day)
     *
     * @param date a given day
     * @return the measurements of a given day
     */
    public List<Measurement> getDailyMeasurement(LocalDate date) {
        List<Measurement> registosDoDia = new ArrayList<>();
        for (Measurement registo : mMeasurements) {
            LocalDate secondDate = registo.getmDateTime().toLocalDate();

            if (checkIfDaysAreEqual(date, secondDate) && (!Double.isNaN(registo.getmValue()))) {
                registosDoDia.add(registo);
            }
        }
        return registosDoDia;

    }

    /**
     * Boolean method that compares days (two days are equal)
     *
     * @param firstDate  first day
     * @param secondDate second day
     * @return the first day is equal to the second day
     */
    public boolean checkIfDaysAreEqual(LocalDate firstDate, LocalDate secondDate) {
        return (firstDate.isEqual(secondDate));
    }

    /**
     * Method that goes through the list of measurements of the day and gets the lowest measurement
     *
     * @param data a given day
     * @return Lowest measurement of a day
     */
    public double getLowestMeasurementOfDay(LocalDate data) {
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

    /**
     * Method that gets the first day of the week
     *
     * @param date a specific date
     * @return
     */
    public LocalDate getFirstDayOfWeek(LocalDate date) {
        if (date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            return date;
        }
        return (date.with(ChronoField.DAY_OF_WEEK, 1)).minusDays(1);
    }

    /**
     * Method that gets the lowest measurements of the week (after defining first and last day of a week and if the lowest values of a day are not double nan (invalid))
     *
     * @param date a given date
     * @return lowest measurements of the week
     */
    public List<Double> lowestMeasurementsOfWeek(LocalDate date) {
        List<Double> registosMinimosSemana = new ArrayList<>();
        LocalDate primeiroDiaSemana = getFirstDayOfWeek(date);
        LocalDate ultimoDiaSemana = primeiroDiaSemana.plusDays(6);
        for (LocalDate diaDaSemana = primeiroDiaSemana; diaDaSemana.isBefore(ultimoDiaSemana.plusDays(1)); diaDaSemana.plusDays(1)) {
            double minimoDia = getLowestMeasurementOfDay(diaDaSemana);
            if (!Double.isNaN(minimoDia)) {
                registosMinimosSemana.add(minimoDia);
            }
            diaDaSemana = diaDaSemana.plusDays(1);
        }
        return registosMinimosSemana;
    }

    /**
     * Method that gets the average of the lowest measurements of a week
     *
     * @param date a given date
     * @return average of lowest measurements of a week
     */
    public double getAverageOfLowestMeasurementsWeek(LocalDate date) {

        List<Double> registosMinSemana = this.lowestMeasurementsOfWeek(date);

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
     * Method that calculates the max value (measurement) of a given day
     *
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

    /**
     * Method that gets the biggest measurements of the week (after defining first and last day of a week and if the biggest values of a day are not double nan (invalid))
     *
     * @param date a given date
     * @return lowest measurements of the week
     */
    public List<Double> biggestWeeklyMeasurements(LocalDate date) {
        List<Double> registosMaximosSemana = new ArrayList<>();
        LocalDate primeiroDiaSemana = getFirstDayOfWeek(date);
        int contador = 1;
        while (contador < 8) {
            double maximoDia = getMaximumValueOfDay(primeiroDiaSemana);
            if (!Double.isNaN(maximoDia)) {
                registosMaximosSemana.add(maximoDia);
            }
            primeiroDiaSemana = primeiroDiaSemana.plusDays(1);
            contador++;
        }
        return registosMaximosSemana;
    }

    /**
     * Method that gets the average of the biggest measurements of a week
     *
     * @param date a given date
     * @return average of biggest measurements of a week
     */
    public double getAverageOfBiggestMeasurementsWeek(LocalDate date) {

        List<Double> registosMaxSemana = this.biggestWeeklyMeasurements(date);

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

    /**
     * Method that calculates the distance between a sensor and a location
     *
     * @param location a given location
     * @return distance between a sensor and a location
     */
    public double distanceBetweenSensorAndLocation(Location location) {
        return this.mLocation.distanceBetweenTwoLocations(location);
    }

    /**
     * Method that returns the daily measurements of a given day
     *
     * @param day a given day
     * @return sum of daily measurements (total)
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
     * Method that returns the daily average on a given day
     *
     * @param date a given day
     * @return daily average
     */
    public double getDailyAverage(LocalDate date) {
        return getTotalDailyMeasurements(date) / getDailyMeasurement(date).size();
    }
}