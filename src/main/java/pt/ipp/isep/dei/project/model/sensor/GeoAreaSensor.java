package pt.ipp.isep.dei.project.model.sensor;

import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.utils.Utils;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;


@Entity
public class GeoAreaSensor implements Sensor {
    @Id
    private String id;
    private String sensorName;
    private LocalDateTime startingDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn
    private List<Reading> listOfReadings = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn
    private SensorType sensorType;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn
    private Location location;
    private String units;
    private boolean isActive;


    /**
     * Constructor method
     *
     * @param sensorName   name of the sensor (string)
     * @param startingDate starting date of the sensor
     * @param sensorType   Type of sensor
     * @param location     Location of the sensor
     */
    public GeoAreaSensor(String id, String sensorName, LocalDateTime startingDate, SensorType sensorType, Location location, String units) {
        this.id = id;
        this.sensorName = sensorName;
        this.startingDate = startingDate;
        this.sensorType = sensorType;
        this.location = location;
        this.units = units;
        this.isActive = true;
    }

    /**
     * Constructor method
     *
     * @param sensorName name of the sensor (string)
     * @param sensorType Type of sensor
     * @param location   Location of the sensor
     */
    public GeoAreaSensor(String id, String sensorName, SensorType sensorType, Location location, String units) {
        this.id = id;
        this.sensorName = sensorName;
        this.startingDate = LocalDateTime.now();
        this.sensorType = sensorType;
        this.location = location;
        this.units = units;
        this.isActive = true;
    }

    protected GeoAreaSensor() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get method
     *
     * @return sensor Name
     */
    public String getSensorName() {
        return sensorName;
    }

    /**
     * Get method
     *
     * @return Starting date of a sensor
     */
    public LocalDateTime getStartingDate() {
        return startingDate;
    }

    /**
     * Get method
     *
     * @return sensor Type
     */
    public SensorType getSensorType() {
        return sensorType;
    }

    /**
     * Get method
     *
     * @return sensor Location
     */
    public Location getLocation() {
        return location;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public boolean isActive() {
        return isActive;
    }

    /**
     * Method that deactivates the sensor.
     * If the sensor is active it deactivates it, as expected and returns a positive boolean expression.
     * If the sensor is already deactivated it doesn't change it's state and returns a negative boolean expression.
     *
     * @return
     */
    public boolean deactivateDevice() {
        if (isActive) {
            isActive = false;
            return true;
        }
        return false;
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
        if (!(objeto instanceof GeoAreaSensor)) {
            return false;
        }
        GeoAreaSensor sensor = (GeoAreaSensor) objeto;
        return (this.id.equals(sensor.id) && this.sensorType.equals(sensor.sensorType) && this.location.equals(sensor.location));
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
    public double distanceBetweenTwoLocations(GeoAreaSensor sensor1) {

        return this.location.distanceBetweenTwoLocations(sensor1.location);
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

        for (Reading reading : listOfReadings) {
            if ((reading.getDateTime().toLocalDate().isEqual(startDate) || reading.getDateTime().toLocalDate().isAfter(startDate)) && (reading.getDateTime().toLocalDate().isEqual(endDate) || reading.getDateTime().toLocalDate().isBefore(endDate))) {
                measurementsBetweenDates.add(reading.getValue());
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
            if (Double.compare(smallestMeasurement, measurementsBetweenDates.get(i)) == 1) {
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
    public double getBiggestMeasurementOfMonth(LocalDate date) {
        LocalDate firstDayOfMonth = date.withDayOfMonth(1);
        LocalDate lastDayOfMonth = date.withDayOfMonth(date.lengthOfMonth());

        List<Double> measurementsBetweenDates = getMeasurementValueBetweenDates(firstDayOfMonth, lastDayOfMonth);

        if (measurementsBetweenDates.isEmpty()) {
            return Double.NaN;
        }
        Double biggestMeasurement = measurementsBetweenDates.get(0);

        for (Double measurement : measurementsBetweenDates) {
            if (Double.compare(measurement, biggestMeasurement) == 1) {
                biggestMeasurement = measurement;
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
     * Method that adds a listOfReadings to a list of measurements
     *
     * @param reading listOfReadings of a sensor
     */

    public void addReadingsToList(Reading reading) {
        this.listOfReadings.add(reading);
    }


    public boolean addReading(Reading reading) {
        if (!listOfReadings.contains(reading)){
            return this.listOfReadings.add(reading);
        }
        return false;
    }

    /**
     * Boolean method that checks if a list of measurements is empty
     *
     * @return empty list of measurements
     */
    public boolean isMeasurementListEmpty() {
        return listOfReadings.isEmpty();
    }

    /**
     * Get method for the last measurement of a sensor
     *
     * @return last measurement
     */
    public Reading getLastMeasurement() {
        if (listOfReadings.isEmpty()) {
            return null;
        }
        Reading reading = listOfReadings.get(0);
        for (int i = (listOfReadings.size() - 1); i > 0; i--) {
            if (!(Double.isNaN(listOfReadings.get(i).getValue()))) {
                return listOfReadings.get(i);
            }
        }
        return reading;
    }

    /**
     * Boolean method that compares a sensor Type to another sensor and they are equal
     *
     * @param tipo Type of sensor
     * @return a type of sensor is equal to the sensor type being compared
     */
    public boolean sensorTypeEqualsSensorType(SensorType tipo) {
        String tipoDoSensorPedido = tipo.getType();
        return (this.getSensorType().getType().equals(tipoDoSensorPedido));
    }

    /**
     * Method that gets the measurements of a given day (if they are not invalid (double nan) and belong to the given day)
     *
     * @param date a given day
     * @return the measurements of a given day
     */
    public List<Reading> getDailyMeasurement(LocalDate date) {
        List<Reading> registosDoDia = new ArrayList<>();
        for (Reading registo : listOfReadings) {
            LocalDate secondDate = registo.getDateTime().toLocalDate();

            if (checkIfDaysAreEqual(date, secondDate) && (!Double.isNaN(registo.getValue()))) {
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
            double valorMinimoDoDia = getDailyMeasurement(data).get(0).getValue();
            for (Reading registo : getDailyMeasurement(data)) {
                if (Utils.isFirstDoubleBiggerThanSecondOne(valorMinimoDoDia, registo.getValue())) {
                    valorMinimoDoDia = registo.getValue();
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
        List<Double> lowestMeasurementOfWeek = new ArrayList<>();
        LocalDate firstDayOfWeek = getFirstDayOfWeek(date);
        LocalDate lastDayOfWeek = firstDayOfWeek.plusDays(6);
        for (LocalDate weekDay = firstDayOfWeek; weekDay.isBefore(lastDayOfWeek.plusDays(1)); weekDay = weekDay.plusDays(1)) {
            double minimoDia = getLowestMeasurementOfDay(weekDay);
            if (!Double.isNaN(minimoDia)) {
                lowestMeasurementOfWeek.add(minimoDia);
            }
        }
        return lowestMeasurementOfWeek;
    }

    /**
     * Method that gets the average of the lowest measurements of a week
     *
     * @param date a given date
     * @return average of lowest measurements of a week
     */
    public double getAverageOfLowestMeasurementsWeek(LocalDate date) {

        List<Double> lowestMeasurementOfWeek = this.lowestMeasurementsOfWeek(date);

        int iterator = 0;
        double sumOfLowestMeasurementOfWeek = 0;
        if (lowestMeasurementOfWeek.isEmpty()) {
            return Double.NaN;
        }
        while (iterator < lowestMeasurementOfWeek.size()) {
            sumOfLowestMeasurementOfWeek += lowestMeasurementOfWeek.get(iterator);
            iterator++;
        }
        return sumOfLowestMeasurementOfWeek / lowestMeasurementOfWeek.size();
    }

    /**
     * Method that calculates the max value (measurement) of a given day
     *
     * @param date a given day
     * @return the maximum value of measurements
     */
    public double getMaximumValueOfDay(LocalDate date) {
        if (!getDailyMeasurement(date).isEmpty()) {
            double maximumValueOfDay = getDailyMeasurement(date).get(0).getValue();
            for (Reading reading : getDailyMeasurement(date)) {
                if (!Utils.isFirstDoubleBiggerThanSecondOne(maximumValueOfDay, reading.getValue()) && !Utils.isSameDouble(maximumValueOfDay, reading.getValue())) {
                    maximumValueOfDay = reading.getValue();
                }
            }
            return maximumValueOfDay;
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
        List<Double> biggestWeeklyMeasurements = new ArrayList<>();
        LocalDate firstDayOfWeek = getFirstDayOfWeek(date);
        int iterator = 1;
        while (iterator < 8 && iterator > 0) {
            double maximumValueOfDay = getMaximumValueOfDay(firstDayOfWeek);
            if (!Double.isNaN(maximumValueOfDay)) {
                biggestWeeklyMeasurements.add(maximumValueOfDay);
            }
            firstDayOfWeek = firstDayOfWeek.plusDays(1);
            iterator++;
        }
        return biggestWeeklyMeasurements;
    }

    /**
     * Method that gets the average of the biggest measurements of a week
     *
     * @param date a given date
     * @return average of biggest measurements of a week
     */
    public double getAverageOfBiggestMeasurementsWeek(LocalDate date) {

        List<Double> biggestWeeklyMeasurements = this.biggestWeeklyMeasurements(date);

        int iterator = 0;
        double sumOfBiggestMeasurementOfWeek = 0;
        if (biggestWeeklyMeasurements.isEmpty()) {
            return Double.NaN;
        }
        while (iterator < biggestWeeklyMeasurements.size()) {
            sumOfBiggestMeasurementOfWeek += biggestWeeklyMeasurements.get(iterator);
            iterator++;
        }
        return sumOfBiggestMeasurementOfWeek / biggestWeeklyMeasurements.size();
    }

    /**
     * Method that calculates the distance between a sensor and a location
     *
     * @param location a given location
     * @return distance between a sensor and a location
     */
    public double distanceBetweenSensorAndLocation(Location location) {
        return this.location.distanceBetweenTwoLocations(location);
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
            for (Reading reading : getDailyMeasurement(day)) {
                sum += reading.getValue();
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

    public List<Reading> getReadingsBetweenDates(LocalDate startDate, LocalDate endDate) {
        List<Reading> measurementsBetweenDates = new ArrayList<>();
        for (Reading reading : listOfReadings) {
            if ((reading.getDateTime().toLocalDate().isEqual(startDate) || reading.getDateTime().toLocalDate().isAfter(startDate)) && (reading.getDateTime().toLocalDate().isEqual(endDate) || reading.getDateTime().toLocalDate().isBefore(endDate))) {
                measurementsBetweenDates.add(reading);
            }
        }
        return measurementsBetweenDates;
    }

    /**
     * method to get the first highest reading within a given interval
     *
     * @param startDate initial date of the period the user wants to consider
     * @param endDate   final date of the period the user wants to consider
     * @return a Reading
     */
    public Reading getFirstHighestReading(LocalDate startDate, LocalDate endDate) {
        if (getReadingsBetweenDates(startDate, endDate).isEmpty()) {
            return null;
        }
        Reading highestReading = getReadingsBetweenDates(startDate, endDate).get(0);
        for (Reading reading : getReadingsBetweenDates(startDate, endDate)) {
            if ((!Double.isNaN(reading.getValue())) && reading.getValue() > highestReading.getValue()) {
                highestReading = reading;
            }
        }
        return highestReading;
    }

    /**
     * Returns the Reading with the highest value for a given day.
     * If there are two Readings with the same value, it returns the most recent one.
     * If there are no Readings in the given day, this method returns null.
     *
     * @param day day to check
     * @return Highest, most recent reading of the given day
     */
    public Reading getHighestReadingOfADay(LocalDate day) {
        if (getDailyMeasurementWithDoubleNaN(day).isEmpty()) {
            return null;
        }
        Reading highestReading = getDailyMeasurementWithDoubleNaN(day).get(0);
        for (Reading reading : getDailyMeasurement(day)) {
            if (!Double.isNaN(reading.getValue()) && Utils.isFirstDoubleBiggerOrEqualThanSecondOne(reading.getValue(), highestReading.getValue())) {
                highestReading = reading;
            }
        }
        return highestReading;
    }

    /**
     * Sorts the maximum Readings in a given interval and returns them in a list.
     *
     * @param startDate first day of the interval
     * @param endDate   last day of the interval
     * @return
     */
    public List<Reading> getDailyMaxReadingsInAnInterval(LocalDate startDate, LocalDate endDate) {
        List<Reading> maximumReadings = new ArrayList<>();

        for (LocalDate dateIterator = startDate; dateIterator.isBefore(endDate.plusDays(1)); dateIterator = dateIterator.plusDays(1)) {
            if (getHighestReadingOfADay(dateIterator) != null) {
                maximumReadings.add(getHighestReadingOfADay(dateIterator));
            }
        }
        return maximumReadings;
    }

    /**
     * Method that receives a list of Readings and returns the most recent Reading that has the lowest value.
     * If the given reading list is empty, the method returns null;
     *
     * @param readings list of readings
     * @return the most recent lowest Reading
     */
    public Reading getLastLowestReading(List<Reading> readings) {
        if (readings.isEmpty()) {
            return null;
        }
        Reading lowestReading = readings.get(0);
        for (Reading reading : readings) {
            if (Utils.isFirstDoubleSmallerThanOrEqualToSecondOne(reading.getValue(), lowestReading.getValue())) {
                lowestReading = reading;
            }
        }
        return lowestReading;
    }

    /**
     * Method that returns the list of Readings of a GeoAreaSensor.
     *
     * @return List with Readings.
     */
    public List<Reading> getListOfReadings() {
        return listOfReadings;
    }

    /**
     * Returns a list of the registered readings for a given day, including the invalid ones (Double.Nan).
     *
     * @param date given day
     * @return list of readings registered in given day
     */
    public List<Reading> getDailyMeasurementWithDoubleNaN(LocalDate date) {
        List<Reading> dailyReadings = new ArrayList<>();
        for (Reading registo : listOfReadings) {
            LocalDate secondDate = registo.getDateTime().toLocalDate();

            if (checkIfDaysAreEqual(date, secondDate)) {
                dailyReadings.add(registo);
            }
        }
        return dailyReadings;

    }
}