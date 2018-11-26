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

    public TipoAreaGeo getmTipoAreaGeo() {
        return mTipoAreaGeo;
    }

    public void setmTipoAreaGeo(TipoAreaGeo mTipoAreaGeo) {
        this.mTipoAreaGeo = mTipoAreaGeo;
    }

    public Localizacao getmLocalizacao() {
        return mLocalizacao;
    }

    public void setmLocalizacao(Localizacao mLocalizacao) {
        this.mLocalizacao = mLocalizacao;
    }

    public RetanguloArea getmRetanguloArea() {
        return mRetanguloArea;
    }

    public void setmRetanguloArea(RetanguloArea mRetanguloArea) {
        this.mRetanguloArea = mRetanguloArea;
    }
}


