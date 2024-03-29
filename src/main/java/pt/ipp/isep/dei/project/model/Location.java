package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.roles.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class Location implements ValueObject {
    private double latitude;

    private double longitude;

    private double elevation;

    protected Location() {
        //intentionally empty
    }

    /**
     * constructor of Location that receives a latitude, a longitude and an elevation.
     * @param latitude
     * @param longitude
     * @param elevation
     */
    public Location(double latitude, double longitude, double elevation) {
        setLatitude(latitude);
        setLongitude(longitude);
        this.elevation = elevation;
    }

    /**
     * method that get the latitude.
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * that method set the latitude.
     * @param latitude
     */
    public void setLatitude(double latitude) {
        if (latitude < -90 || latitude > 90) {
            this.latitude = Double.NaN;
        } else this.latitude = latitude;
    }

    /**
     * method that get the longitude.
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * method that set the longitude.
     * @param longitude
     */
    public void setLongitude(double longitude) {
        if (longitude < -180 || longitude > 180) {
            this.longitude = Double.NaN;
        } else this.longitude = longitude;
    }

    /**
     * method that get the elevation.
     */
    public Double getElevation() {
        return elevation;
    }

    public void setElevation(double elevation) {
        this.elevation = elevation;
    }

    /**
     * method that calculate the distance between two locations.
     * @param novoLocal
     * @return the distance calculated.
     */
    public double distanceBetweenTwoLocations(Location novoLocal) {

        final int raioDaTerra = 6371; // raio da Terra
        double lonNovoLocal = novoLocal.getLongitude();
        double altNovoLocal = novoLocal.getElevation();
        double latNovoLocal = novoLocal.getLatitude();

        double distEntreLon = Math.toRadians(lonNovoLocal - this.longitude);

        double distEntreLocais = Math.acos(Math.cos(Math.toRadians(this.latitude)) * Math.cos(Math.toRadians(latNovoLocal))
                * Math.cos(distEntreLon) + Math.sin(Math.toRadians(this.latitude)) * Math.sin(Math.toRadians(latNovoLocal)));


        double distanciaKm = raioDaTerra * distEntreLocais * 1000; // convert to km

        double altura = this.elevation - altNovoLocal;


        return Math.hypot(distanciaKm, altura);
    }

    /**
     * method that creates the hashcode to location
     */
    public int hashCode() {
        return 1;
    }

    /**
     * Equals method to determine if two locations are equals.
     * @param obj
     * @return boolean.
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Location)) {
            return false;
        }

        Location local = (Location) obj;
        Double comparableLatitude = latitude;
        Double comparableLongitude = longitude;
        Double comparableAltitude = elevation;
        Double comparableLocalLatitude = local.latitude;
        Double comparableLocalLongitude = local.longitude;
        Double comparableLocalAltitude = local.elevation;
        return comparableLocalLatitude.equals(comparableLatitude)
                && comparableLocalLongitude.equals(comparableLongitude)
                && comparableLocalAltitude.equals(comparableAltitude);
    }

}