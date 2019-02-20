package pt.ipp.isep.dei.project.model;


public class Location {
    private double latitude;
    private double longitude;
    private double altitude;

    /**
     * constructor of Location that receives a latitude, a longitude and an altitude.
     * @param latitude
     * @param longitude
     * @param altitude
     */
    public Location(double latitude, double longitude, double altitude) {
        setLatitude(latitude);
        setLongitude(longitude);
        this.altitude = altitude;
    }

    /**
     * method that get the latitude.
     */
    public double getLatitude() {
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
    public double getLongitude() {
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
     * method that get the altitude.
     */
    public double getAltitude() {
        return altitude;
    }

    /**
     * method that calculate the distance between two locations.
     * @param novoLocal
     * @return the distance calculated.
     */
    public double distanceBetweenTwoLocations(Location novoLocal) {

        final int raioDaTerra = 6371; // raio da Terra
        double lonNovoLocal = novoLocal.getLongitude();
        double altNovoLocal = novoLocal.getAltitude();
        double latNovoLocal = novoLocal.getLatitude();

        double distEntreLon = Math.toRadians(lonNovoLocal - this.longitude);

        double distEntreLocais = Math.acos(Math.cos(Math.toRadians(this.latitude)) * Math.cos(Math.toRadians(latNovoLocal))
                * Math.cos(distEntreLon) + Math.sin(Math.toRadians(this.latitude)) * Math.sin(Math.toRadians(latNovoLocal)));


        double distanciaKm = raioDaTerra * distEntreLocais * 1000; // convert to km

        double altura = this.altitude - altNovoLocal;


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
        Double comparableAltitude = altitude;
        Double comparableLocalLatitude = local.latitude;
        Double comparableLocalLongitude = local.longitude;
        Double comparableLocalAltitude = local.altitude;
        return comparableLocalLatitude.equals(comparableLatitude)
                && comparableLocalLongitude.equals(comparableLongitude)
                && comparableLocalAltitude.equals(comparableAltitude);
    }

}