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


    public double distanciaDuasLocalizacoes(Localizacao novoLocal) {
        return Math.hypot((this.mLatitude - novoLocal.mLatitude), (this.mLongitude - novoLocal.mLongitude));

    }
}
