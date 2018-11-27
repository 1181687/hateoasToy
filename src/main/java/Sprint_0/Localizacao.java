package Sprint_0;

public class Localizacao {
    private double mLatitude;
    private double mLongitude;
    private double mAltitude;

    public Localizacao(double mLatitude, double mLongitude, double mAltitude) {
        this.mLatitude = mLatitude;
        this.mLongitude = mLongitude;
        this.mAltitude = mAltitude;
    }

    public double getmLatitude() {
        return mLatitude;
    }

    public void setmLatitude(double mLatitude) {
        this.mLatitude = mLatitude;
    }

    public double getmLongitude() {
        return mLongitude;
    }

    public void setmLongitude(double mLongitude) {
        this.mLongitude = mLongitude;
    }

    public double getmAltitude() {
        return mAltitude;
    }

    public void setmAltitude(double mAltitude) {
        this.mAltitude = mAltitude;
    }


    public double distanciaDuasLocalizacoes(Localizacao novoLocal) {
        return Math.hypot((this.mLatitude - novoLocal.mLatitude), (this.mLongitude - novoLocal.mLongitude));

    }
}
