package Sprint_0;
import java.util.List;

// Atributos da classe AreaGeografica
public class AreaGeografica {
    private String mNomeAreaGeo;
    private TipoAreaGeo mTipoAreaGeo;
    private AreaGeografica mAreaInserida;
    private Localizacao mLocalizacao;
    private RetanguloArea mRetanguloArea;
    private List<Sensor> mListaSensor;


    // Construtor com todos os atributos
    public AreaGeografica(String mNomeAreaGeo, TipoAreaGeo mTipoAreaGeo, AreaGeografica mAreaInserida, Localizacao mLocalizacao, RetanguloArea mRetanguloArea, List<Sensor> mListaSensor) {
        this.mNomeAreaGeo = mNomeAreaGeo;
        this.mTipoAreaGeo = mTipoAreaGeo;
        this.mAreaInserida = mAreaInserida;
        this.mLocalizacao = mLocalizacao;
        this.mRetanguloArea = mRetanguloArea;
        this.mListaSensor = mListaSensor;
    }

}
