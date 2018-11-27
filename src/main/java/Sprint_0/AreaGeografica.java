package Sprint_0;

import java.util.List;

public class AreaGeografica {
    private String mNomeAreaGeo;
    private TipoAreaGeo mTipoAreaGeo;
    private AreaGeografica mAreaInserida;
    private Localizacao mLocalizacao;
    private RetanguloArea mRetanguloArea;
    private List<Sensor> mListaSensor;

    public AreaGeografica(String mNomeAreaGeo, TipoAreaGeo mTipoAreaGeo, Localizacao mLocalizacao, RetanguloArea mRetanguloArea) {
        this.mNomeAreaGeo = mNomeAreaGeo;
        this.mTipoAreaGeo = mTipoAreaGeo;
        this.mLocalizacao = mLocalizacao;
        this.mRetanguloArea = mRetanguloArea;
    }

    public String getmNomeAreaGeo() {
        return mNomeAreaGeo;
    }

    public void setmNomeAreaGeo(String mNomeAreaGeo) {
        this.mNomeAreaGeo = mNomeAreaGeo;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals (Object obj){
        if (this == obj){
            return true;
        }
        if (!(obj instanceof AreaGeografica)){
            return false;
        }
        AreaGeografica ag = (AreaGeografica) obj;
        if (this.mNomeAreaGeo.equals(ag.mNomeAreaGeo)&&this.mTipoAreaGeo.equals(ag.mTipoAreaGeo)&&this.mLocalizacao.equals(ag.mLocalizacao)&&this.mRetanguloArea.equals(ag.mRetanguloArea)){
            return true;
        }
        return false;
    }

    public double distanciaLinearDuasAreas(AreaGeografica novoAg) {
        return this.mLocalizacao.distanciaDuasLocalizacoes(novoAg.mLocalizacao);
    }

}


