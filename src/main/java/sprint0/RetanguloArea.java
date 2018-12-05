package sprint0;

public class RetanguloArea {
    private Localizacao mCantoSuperiorEsquerdo;
    private Localizacao mCantoInferiorDireito;

    public RetanguloArea(Localizacao cantoSuperiorEsquerdo, Localizacao cantoInferiorDireito) {
        mCantoSuperiorEsquerdo = cantoSuperiorEsquerdo;
        mCantoInferiorDireito = cantoInferiorDireito;
    }

    public boolean verificaSeLocalizacaoEstaContidaNumaArea(Localizacao localizacao){
        return (localizacao.getmLatitude() >= mCantoInferiorDireito.getmLatitude()
                && localizacao.getmLatitude() <= mCantoSuperiorEsquerdo.getmLatitude()
                && localizacao.getmLongitude() <= mCantoInferiorDireito.getmLongitude()
                && localizacao.getmLongitude() >= mCantoSuperiorEsquerdo.getmLongitude());
    }
}