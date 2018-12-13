package sprint0.Model;

public class TipoAreaGeo {
    private String mTipoAreaGeo;

    public TipoAreaGeo(String mTipoAreaGeo) {
        this.mTipoAreaGeo = mTipoAreaGeo;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TipoAreaGeo)) {
            return false;
        }
        TipoAreaGeo tag = (TipoAreaGeo) obj;
        return this.mTipoAreaGeo.equals(tag.mTipoAreaGeo);

    }

    public String getmTipoAreaGeo() {
        return mTipoAreaGeo;
    }

    public boolean umTipoAreaGeoEIgualAOutra(String tipo) {
        return (this.getmTipoAreaGeo().equals(tipo));
    }

}
