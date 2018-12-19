
package pt.ipp.isep.dei.project.model;

public class Location {      //graus decimais
    private double mLatitude;
    private double mLongitude;
    private double mAltitude;

    public Location(double mLatitude, double mLongitude, double mAltitude) {
        setmLatitude(mLatitude);
        setmLongitude(mLongitude);
        this.mAltitude=mAltitude;
    }

    public double getmLatitude() {
        return mLatitude;
    }

    public void setmLatitude(double mLatitude) {
        if (mLatitude<-90||mLatitude>90){
            this.mLatitude=Double.NaN;
        }
        else this.mLatitude = mLatitude;
    }

    public double getmLongitude() {
        return mLongitude;
    }

    public void setmLongitude(double mLongitude) {
        if (mLongitude<-180||mLongitude>180){
            this.mLongitude=Double.NaN;
        }
        else this.mLongitude = mLongitude;
    }

    public double getmAltitude() {
        return mAltitude;
    }


    public double distanciaDuasLocalizacoes(Location novoLocal) {

        final int raioDaTerra = 6371; // raio da Terra
        double lonNovoLocal = novoLocal.getmLongitude();
        double altNovoLocal = novoLocal.getmAltitude();
        double latNovoLocal = novoLocal.getmLatitude();

        double distEntreLon = Math.toRadians(lonNovoLocal - this.mLongitude);

        double distEntreLocais = Math.acos(Math.cos(Math.toRadians(this.mLatitude))*Math.cos(Math.toRadians(latNovoLocal))
                *Math.cos(distEntreLon)+Math.sin(Math.toRadians(this.mLatitude))*Math.sin(Math.toRadians(latNovoLocal)));


        double distanciaKm = raioDaTerra* distEntreLocais*1000; // convert to km

        double altura = this.mAltitude - altNovoLocal;


        return Math.hypot(distanciaKm, altura);
    }

    public int hashCode() {
        return 1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Location)) {
            return false;
        }

        Location local = (Location) obj;
        final double delta = 0.0001;
        return Math.abs((this.mLatitude - local.mLatitude)) < delta && Math.abs((this.mLongitude - local.mLongitude)) < delta && Math.abs((this.mAltitude - local.mAltitude)) < delta;
    }

}