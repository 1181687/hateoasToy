package pt.ipp.isep.dei.project.model;

public class RectangleArea {
    private double mLength;
    private double mWidth;
    private Location mLocationRectangleArea;


    public RectangleArea(double largura, double comprimento, Location locationRetangulo) {
        mLength = comprimento;
        mWidth = largura;
        mLocationRectangleArea = locationRetangulo;
    }

    public boolean verificaSeLocalizacaoEstaContidaNumaArea(Location location){
        double latitudeCantoSuperiorEsquerdo = mLocationRectangleArea.getmLatitude() + (mWidth /2);
        double longitudeCantoSuperiorEsquerdo = mLocationRectangleArea.getmLongitude() - (mLength /2);

        double latitudeCantoInferiorDireito = mLocationRectangleArea.getmLatitude() - (mWidth /2);
        double longitudeCantoInferiorDireito = mLocationRectangleArea.getmLongitude() + (mLength /2);

        return (location.getmLatitude() >= latitudeCantoInferiorDireito
                && location.getmLatitude() <= latitudeCantoSuperiorEsquerdo
                && location.getmLongitude() <= longitudeCantoInferiorDireito
                && location.getmLongitude() >= longitudeCantoSuperiorEsquerdo);
    }
    public int hashCode() {
        return 1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RectangleArea)) {
            return false;
        }

        RectangleArea local = (RectangleArea) obj;
        final double delta = 0.0001;
        return mLocationRectangleArea.equals(local.mLocationRectangleArea)&& Math.abs((mLength -local.mLength))<delta && Math.abs((mWidth -local.mWidth))<delta;
    }

}