package pt.ipp.isep.dei.project.model;


public class Location {
    private double mLatitude;
    private double mLongitude;
    private double mAltitude;

    /**
     * constructor of Location that receives a latitude, a longitude and an altitude.
     * @param mLatitude
     * @param mLongitude
     * @param mAltitude
     */
    public Location(double mLatitude, double mLongitude, double mAltitude) {
        setmLatitude(mLatitude);
        setmLongitude(mLongitude);
        this.mAltitude = mAltitude;
    }

    /**
     * method that get the latitude.
     */
    public double getmLatitude() {
        return mLatitude;
    }

    /**
     * that method set the latitude.
     * @param latitude
     */
    public void setmLatitude(double latitude) {
        if (latitude < -90 || latitude > 90) {
            this.mLatitude = Double.NaN;
        } else this.mLatitude = latitude;
    }

    /**
     * method that get the longitude.
     */
    public double getmLongitude() {
        return mLongitude;
    }

    /**
     * method that set the longitude.
     * @param mLongitude
     */
    public void setmLongitude(double mLongitude) {
        if (mLongitude < -180 || mLongitude > 180) {
            this.mLongitude = Double.NaN;
        } else this.mLongitude = mLongitude;
    }

    /**
     * method that get the altitude.
     */
    public double getmAltitude() {
        return mAltitude;
    }

    /**
     * method that calculate the distance between two locations.
     * @param novoLocal
     * @return the distance calculated.
     */
    public double distanceBetweenTwoLocations(Location novoLocal) {

        final int raioDaTerra = 6371; // raio da Terra
        double lonNovoLocal = novoLocal.getmLongitude();
        double altNovoLocal = novoLocal.getmAltitude();
        double latNovoLocal = novoLocal.getmLatitude();

        double distEntreLon = Math.toRadians(lonNovoLocal - this.mLongitude);

        double distEntreLocais = Math.acos(Math.cos(Math.toRadians(this.mLatitude)) * Math.cos(Math.toRadians(latNovoLocal))
                * Math.cos(distEntreLon) + Math.sin(Math.toRadians(this.mLatitude)) * Math.sin(Math.toRadians(latNovoLocal)));


        double distanciaKm = raioDaTerra * distEntreLocais * 1000; // convert to km

        double altura = this.mAltitude - altNovoLocal;


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
        Double comparablemLatitude = mLatitude;
        Double comparablemLongitude = mLongitude;
        Double comparablemAltitude = mAltitude;
        Double comparableLocalLatitude = local.mLatitude;
        Double comparableLocalLongitude = local.mLongitude;
        Double comparableLocalAltitude = local.mAltitude;
        return comparableLocalLatitude.equals(comparablemLatitude)
                && comparableLocalLongitude.equals(comparablemLongitude)
                && comparableLocalAltitude.equals(comparablemAltitude);
    }

}