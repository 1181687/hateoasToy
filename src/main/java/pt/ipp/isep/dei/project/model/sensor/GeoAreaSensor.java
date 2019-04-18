package pt.ipp.isep.dei.project.model.sensor;

import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaId;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.LocalDateTime;

@Entity
public class GeoAreaSensor {
    @EmbeddedId
    private GeoAreaSensorId id;

    private String sensorName;

    private LocalDateTime startingDate;

    @Embedded
    private SensorTypeId sensorTypeId;

    @Transient
    private Location location;

    private String units;

    @Embedded
    private SensorState isActive;

    @Embedded
    private GeoAreaId geoAreaId;


    /**
     * Constructor method
     *
     * @param sensorName   name of the sensor (string)
     * @param startingDate starting date of the sensor
     * @param sensorTypeId   ID of the type of sensor
     * @param location     Location of the sensor
     */
    public GeoAreaSensor(GeoAreaSensorId id, String sensorName, LocalDateTime startingDate, SensorTypeId sensorTypeId, Location location, String units, GeoAreaId geoAreaId) {
        this.id = id;
        this.sensorName = sensorName;
        this.startingDate = startingDate;
        this.sensorTypeId = sensorTypeId;
        this.location = location;
        this.units = units;
        this.isActive = new SensorState();
        this.geoAreaId = geoAreaId;
    }

    /**
     * Constructor method
     *
     * @param sensorName name of the sensor (string)
     * @param sensorTypeId ID of the type of sensor
     * @param location   Location of the sensor
     */

    public GeoAreaSensor(GeoAreaSensorId id, String sensorName, SensorTypeId sensorTypeId, Location location, String units, GeoAreaId geoAreaId) {
        this.id = id;
        this.sensorName = sensorName;
        this.startingDate = LocalDateTime.now();
        this.sensorTypeId = sensorTypeId;
        this.location = location;
        this.units = units;
        this.isActive = new SensorState();
        this.geoAreaId = geoAreaId;
    }


    protected GeoAreaSensor() {
        //empty
    }


    public GeoAreaSensorId getId() {
        return id;
    }

    public void setId(GeoAreaSensorId id) {
        this.id = id;
    }

    /*
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

    public LocalDateTime getStartingDate() {
        return startingDate;
    }
/*
    /**
     * Get method
     *
     * @return sensor Type
     */

    public SensorTypeId getSensorType() {
        return sensorTypeId;
    }

    public LocalDateTime getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDateTime startingDate) {
        this.startingDate = startingDate;
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
        return isActive.isActive();
    }

    /**
     * Method that deactivates the sensor.
     * If the sensor is active it deactivates it, as expected and returns a positive boolean expression.
     * If the sensor is already deactivated it doesn't change it's state and returns a negative boolean expression.
     *
     * @return
     */

    public boolean deactivateDevice() {
        if (isActive.isActive()) {
            isActive.deactivateSensor();
            return true;
        }
        return false;
    }

    /**
     * Equals method to determine if two Sensors are equal. They are equals if all atributtes are equal
     *
     * @param object receives an object
     * @return boolean
     */

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof GeoAreaSensor)) {
            return false;
        }
        GeoAreaSensor sensor = (GeoAreaSensor) object;
        return this.id.getSensorId().equalsIgnoreCase(sensor.id.getSensorId());
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
/*
    public List<Double> getMeasurementValueBetweenDates(LocalDate startDate, LocalDate endDate) {

        List<Double> measurementsBetweenDates = new ArrayList<>();

        for (GeoAreaReading geoAreaReading : listOfReadings) {
            if ((geoAreaReading.getDateTime().toLocalDate().isEqual(startDate) || geoAreaReading.getDateTime().toLocalDate().isAfter(startDate)) && (geoAreaReading.getDateTime().toLocalDate().isEqual(endDate) || geoAreaReading.getDateTime().toLocalDate().isBefore(endDate))) {
                measurementsBetweenDates.add(geoAreaReading.getValue());
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
/*
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
/*
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
/*
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
/*
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
     * @param geoAreaReading listOfReadings of a sensor
     */
/*
    public void addReadingsToList(GeoAreaReading geoAreaReading) {
        this.listOfReadings.add(geoAreaReading);
    }


    public boolean addReading(GeoAreaReading geoAreaReading) {
        if (!this.readingExistsBySensorIdLocalDateTime(geoAreaReading)) {
            return this.listOfReadings.add(geoAreaReading);
        }
        return false;
    }

    public boolean readingExistsBySensorIdLocalDateTime(GeoAreaReading geoAreaReading) {
        if (!listOfReadings.isEmpty()) {
            for (GeoAreaReading geoAreaReading1 : listOfReadings) {
                if (geoAreaReading1.getDateTime().equals(geoAreaReading.getDateTime()))
                    return true;
            }
        }
        return false;
    }


    /**
     * Boolean method that checks if a list of measurements is empty
     *
     * @return empty list of measurements
     */
/*
    public boolean isMeasurementListEmpty() {
        return listOfReadings.isEmpty();
    }

    /**
     * Get method for the last measurement of a sensor
     *
     * @return last measurement
     */
/*
    public GeoAreaReading getLastMeasurement() {
        if (listOfReadings.isEmpty()) {
            return null;
        }
        GeoAreaReading geoAreaReading = listOfReadings.get(0);
        for (int i = (listOfReadings.size() - 1); i > 0; i--) {
            if (!(Double.isNaN(listOfReadings.get(i).getValue()))) {
                return listOfReadings.get(i);
            }
        }
        return geoAreaReading;
    }

    /**
     * Boolean method that compares a sensor Type to another sensor and they are equal
     *
     * @param tipo Type of sensor
     * @return a type of sensor is equal to the sensor type being compared
     */
/*
    public boolean sensorTypeEqualsSensorType(SensorType tipo) {
        String tipoDoSensorPedido = tipo.getSensorType();
        return (this.getSensorType().getSensorType().equals(tipoDoSensorPedido));
    }

    /**
     * Method that gets the measurements of a given day (if they are not invalid (double nan) and belong to the given day)
     *
     * @param date a given day
     * @return the measurements of a given day
     */

/*
    public List<GeoAreaReading> getDailyMeasurement(LocalDate date) {
        List<GeoAreaReading> registosDoDia = new ArrayList<>();
        for (GeoAreaReading registo : listOfReadings) {
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

/*
    public boolean checkIfDaysAreEqual(LocalDate firstDate, LocalDate secondDate) {
        return (firstDate.isEqual(secondDate));
    }

    /**
     * Method that goes through the list of measurements of the day and gets the lowest measurement
     *
     * @param data a given day
     * @return Lowest measurement of a day
     */

/*
    public double getLowestMeasurementOfDay(LocalDate data) {
        if (!getDailyMeasurement(data).isEmpty()) {
            double valorMinimoDoDia = getDailyMeasurement(data).get(0).getValue();
            for (GeoAreaReading registo : getDailyMeasurement(data)) {
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

/*
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

/*
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

/*
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

/*
    public double getMaximumValueOfDay(LocalDate date) {
        if (!getDailyMeasurement(date).isEmpty()) {
            double maximumValueOfDay = getDailyMeasurement(date).get(0).getValue();
            for (GeoAreaReading geoAreaReading : getDailyMeasurement(date)) {
                if (!Utils.isFirstDoubleBiggerThanSecondOne(maximumValueOfDay, geoAreaReading.getValue()) && !Utils.isSameDouble(maximumValueOfDay, geoAreaReading.getValue())) {
                    maximumValueOfDay = geoAreaReading.getValue();
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

/*
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

/*
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

/*
    public double distanceBetweenSensorAndLocation(Location location) {
        return this.location.distanceBetweenTwoLocations(location);
    }

    /**
     * Method that returns the daily measurements of a given day
     *
     * @param day a given day
     * @return sum of daily measurements (total)
     */
/*
    public double getTotalDailyMeasurements(LocalDate day) {
        double sum = 0;
        if (!(getDailyMeasurement(day).isEmpty())) {
            for (GeoAreaReading geoAreaReading : getDailyMeasurement(day)) {
                sum += geoAreaReading.getValue();
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

/*
    public double getDailyAverage(LocalDate date) {
        return getTotalDailyMeasurements(date) / getDailyMeasurement(date).size();
    }

    public List<GeoAreaReading> getReadingsBetweenDates(LocalDate startDate, LocalDate endDate) {
        List<GeoAreaReading> measurementsBetweenDates = new ArrayList<>();
        for (GeoAreaReading geoAreaReading : listOfReadings) {
            if ((geoAreaReading.getDateTime().toLocalDate().isEqual(startDate) || geoAreaReading.getDateTime().toLocalDate().isAfter(startDate)) && (geoAreaReading.getDateTime().toLocalDate().isEqual(endDate) || geoAreaReading.getDateTime().toLocalDate().isBefore(endDate))) {
                measurementsBetweenDates.add(geoAreaReading);
            }
        }
        return measurementsBetweenDates;
    }

    /**
     * method to get the first highest reading within a given interval
     *
     * @param startDate initial date of the period the user wants to consider
     * @param endDate   final date of the period the user wants to consider
     * @return a GeoAreaReading
     */

/*
    public GeoAreaReading getFirstHighestReading(LocalDate startDate, LocalDate endDate) {
        if (getReadingsBetweenDates(startDate, endDate).isEmpty()) {
            return null;
        }
        GeoAreaReading highestGeoAreaReading = getReadingsBetweenDates(startDate, endDate).get(0);
        for (GeoAreaReading geoAreaReading : getReadingsBetweenDates(startDate, endDate)) {
            if ((!Double.isNaN(geoAreaReading.getValue())) && geoAreaReading.getValue() > highestGeoAreaReading.getValue()) {
                highestGeoAreaReading = geoAreaReading;
            }
        }
        return highestGeoAreaReading;
    }

    /**
     * Returns the GeoAreaReading with the highest value for a given day.
     * If there are two Readings with the same value, it returns the most recent one.
     * If there are no Readings in the given day, this method returns null.
     *
     * @param day day to check
     * @return Highest, most recent reading of the given day
     */

/*
    public GeoAreaReading getHighestReadingOfADay(LocalDate day) {
        if (getDailyMeasurementWithDoubleNaN(day).isEmpty()) {
            return null;
        }
        GeoAreaReading highestGeoAreaReading = getDailyMeasurementWithDoubleNaN(day).get(0);
        for (GeoAreaReading geoAreaReading : getDailyMeasurement(day)) {
            if (!Double.isNaN(geoAreaReading.getValue()) && Utils.isFirstDoubleBiggerOrEqualThanSecondOne(geoAreaReading.getValue(), highestGeoAreaReading.getValue())) {
                highestGeoAreaReading = geoAreaReading;
            }
        }
        return highestGeoAreaReading;
    }

    /**
     * Sorts the maximum Readings in a given interval and returns them in a list.
     *
     * @param startDate first day of the interval
     * @param endDate   last day of the interval
     * @return
     */

/*
    public List<GeoAreaReading> getDailyMaxReadingsInAnInterval(LocalDate startDate, LocalDate endDate) {
        List<GeoAreaReading> maximumGeoAreaReadings = new ArrayList<>();

        for (LocalDate dateIterator = startDate; dateIterator.isBefore(endDate.plusDays(1)); dateIterator = dateIterator.plusDays(1)) {
            if (getHighestReadingOfADay(dateIterator) != null) {
                maximumGeoAreaReadings.add(getHighestReadingOfADay(dateIterator));
            }
        }
        return maximumGeoAreaReadings;
    }

    /**
     * Method that receives a list of Readings and returns the most recent GeoAreaReading that has the lowest value.
     * If the given reading list is empty, the method returns null;
     *
     * @param geoAreaReadings list of geoAreaReadings
     * @return the most recent lowest GeoAreaReading
     */

/*
    public GeoAreaReading getLastLowestReading(List<GeoAreaReading> geoAreaReadings) {
        if (geoAreaReadings.isEmpty()) {
            return null;
        }
        GeoAreaReading lowestGeoAreaReading = geoAreaReadings.get(0);
        for (GeoAreaReading geoAreaReading : geoAreaReadings) {
            if (Utils.isFirstDoubleSmallerThanOrEqualToSecondOne(geoAreaReading.getValue(), lowestGeoAreaReading.getValue())) {
                lowestGeoAreaReading = geoAreaReading;
            }
        }
        return lowestGeoAreaReading;
    }

    /**
     * Method that returns the list of Readings of a GeoAreaSensor.
     *
     * @return List with Readings.
     */

/*
    public List<GeoAreaReading> getListOfReadings() {
        return listOfReadings;
    }

    /**
     * Returns a list of the registered readings for a given day, including the invalid ones (Double.Nan).
     *
     * @param date given day
     * @return list of readings registered in given day
     */

/*
    public List<GeoAreaReading> getDailyMeasurementWithDoubleNaN(LocalDate date) {
        List<GeoAreaReading> dailyGeoAreaReadings = new ArrayList<>();
        for (GeoAreaReading registo : listOfReadings) {
            LocalDate secondDate = registo.getDateTime().toLocalDate();

            if (checkIfDaysAreEqual(date, secondDate)) {
                dailyGeoAreaReadings.add(registo);
            }
        }
        return dailyGeoAreaReadings;

    }
    */
}