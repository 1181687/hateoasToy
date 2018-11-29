
package Sprint_0;

public class Localizacao {      //graus decimais
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

        final int R = 6371; // raio da Terra
        double lonNovoLocal = novoLocal.getmLongitude();
        double altNovoLocal = novoLocal.getmAltitude();

        double distEntreLon = Math.toRadians(lonNovoLocal - this.mLongitude);

        double distEntreLocais = Math.acos(Math.cos(Math.toRadians(this.mLatitude))*Math.cos(Math.toRadians(novoLocal.getmLatitude()))
                *Math.cos(distEntreLon)+Math.sin(Math.toRadians(this.mLatitude))*Math.sin(Math.toRadians(novoLocal.getmLatitude())));


        double distancia = R * distEntreLocais*1000; // convert to km

        double altura = this.mAltitude - altNovoLocal;


        return Math.hypot(distancia, altura);

    }
}