package pt.ipp.isep.dei.project.model;

public class RetanguloArea {
    private double mComprimento;
    private double mLargura;
    private Localizacao mLocalizacaoRetanguloArea;

    public RetanguloArea(double largura, double comprimento, Localizacao localizacaoRetangulo) {
        mComprimento = comprimento;
        mLargura = largura;
        mLocalizacaoRetanguloArea = localizacaoRetangulo;
    }

    public boolean verificaSeLocalizacaoEstaContidaNumaArea(Localizacao localizacao){
        double latitudeCantoSuperiorEsquerdo = mLocalizacaoRetanguloArea.getmLatitude() + (mLargura/2);
        double longitudeCantoSuperiorEsquerdo = mLocalizacaoRetanguloArea.getmLongitude() - (mComprimento/2);

        double latitudeCantoInferiorDireito = mLocalizacaoRetanguloArea.getmLatitude() - (mLargura/2);
        double longitudeCantoInferiorDireito = mLocalizacaoRetanguloArea.getmLongitude() + (mComprimento/2);

        return (localizacao.getmLatitude() >= latitudeCantoInferiorDireito
                && localizacao.getmLatitude() <= latitudeCantoSuperiorEsquerdo
                && localizacao.getmLongitude() <= longitudeCantoInferiorDireito
                && localizacao.getmLongitude() >= longitudeCantoSuperiorEsquerdo);
    }

    public int hashCode() {
        return 1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RetanguloArea)) {
            return false;
        }

        RetanguloArea local = (RetanguloArea) obj;
        final double delta = 0.0001;
        return mLocalizacaoRetanguloArea.equals(local.mLocalizacaoRetanguloArea)&& Math.abs((mComprimento-local.mComprimento))<delta && Math.abs((mLargura-local.mLargura))<delta;
    }
}