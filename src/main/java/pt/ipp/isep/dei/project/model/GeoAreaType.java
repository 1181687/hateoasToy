package pt.ipp.isep.dei.project.model;

public class GeoAreaType {
    private String mTipoAreaGeo;

    public GeoAreaType(String mTipoAreaGeo) {
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
        if (!(obj instanceof GeoAreaType)) {
            return false;
        }
        GeoAreaType tag = (GeoAreaType) obj;
        return this.mTipoAreaGeo.equals(tag.mTipoAreaGeo);

    }

    public String getNomeDoTipoAreaGeo() {
        return mTipoAreaGeo;
    }

    public boolean umTipoAreaGeoEIgualAOutra(String tipo) {
        return (this.getNomeDoTipoAreaGeo().equals(tipo));
    }

}
