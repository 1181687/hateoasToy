package pt.ipp.isep.dei.project.model;

public class RectangleArea {
    private double mComprimento;
    private double mLargura;
    private Location mLocationRetanguloArea;

    public RectangleArea(double largura, double comprimento, Location locationRetangulo) {
        mComprimento = comprimento;
        mLargura = largura;
        mLocationRetanguloArea = locationRetangulo;
    }

    public boolean verificaSeLocalizacaoEstaContidaNumaArea(Location location){
        double latitudeCantoSuperiorEsquerdo = mLocationRetanguloArea.getmLatitude() + (mLargura/2);
        double longitudeCantoSuperiorEsquerdo = mLocationRetanguloArea.getmLongitude() - (mComprimento/2);

        double latitudeCantoInferiorDireito = mLocationRetanguloArea.getmLatitude() - (mLargura/2);
        double longitudeCantoInferiorDireito = mLocationRetanguloArea.getmLongitude() + (mComprimento/2);

        return (location.getmLatitude() >= latitudeCantoInferiorDireito
                && location.getmLatitude() <= latitudeCantoSuperiorEsquerdo
                && location.getmLongitude() <= longitudeCantoInferiorDireito
                && location.getmLongitude() >= longitudeCantoSuperiorEsquerdo);
    }
}