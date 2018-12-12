package sprint0.Model;

import java.util.ArrayList;
import java.util.List;

public class ListaTiposSensores {

    private List<TipoSensor> mListaTiposSensores = new ArrayList<>();

    public ListaTiposSensores() {
    }

    public List<TipoSensor> getmListaTiposSensores() {
        return mListaTiposSensores;
    }

    public ListaTiposSensores(List<TipoSensor> mListaTiposSensores) {
        this.mListaTiposSensores = mListaTiposSensores;
    }

    public TipoSensor novoTipoSensor (String novoTipo){
        return new TipoSensor(novoTipo);
    }

    public boolean adicionarTipoSensorALista(TipoSensor novoTipoSensor){
        if(!(this.mListaTiposSensores.contains(novoTipoSensor))){
            this.mListaTiposSensores.add(novoTipoSensor);
            return true;
        }
        return false;
    }
}
