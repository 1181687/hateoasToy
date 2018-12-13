package sprint0.Controllers;

import sprint0.Model.*;

public class US6Controller {
    private ListaTiposSensores mListaTiposSensores;
    private ListaAG mListaAreaGeografica;

    public US6Controller(ListaTiposSensores listaTiposSensores, ListaAG listaAG) {
        this.mListaTiposSensores = listaTiposSensores;
        this.mListaAreaGeografica = listaAG;
    }


    public TipoSensor getTipoSensorPorPosicao (int posicao) {
        return mListaTiposSensores.getTipoSensorPorPosicao(posicao);
    }

    public String getNomeAreaGeograficaPorIndice (int posicao) {
        return mListaAreaGeografica.getNomeAreaGeograficaPorIndice(posicao);
    }

    public AreaGeografica getAreaGeografica (int posicao) {
        return mListaAreaGeografica.getmListaAG().get(posicao);
    }

    public int numeroElementosDaListaAreaGeografica () {
        return mListaAreaGeografica.getmListaAG().size();
    }

    public int numeroElementosDaListaTipoDeSensor () {
        return mListaTiposSensores.getmListaTiposSensores().size();
    }

    public String getNomeTipoSensorPorIndice (int posicao) {
        return mListaTiposSensores.getTipoSensorPorPosicao(posicao).getmTipo();
    }

    public boolean adicionarSensorAAreaGeografica(Sensor sensor, AreaGeografica areaGeografica) {
        if ((!(this.mListaAreaGeografica.getAreaGeografica(areaGeografica).getmListaSensor().contains(sensor)))) {
            mListaAreaGeografica.getAreaGeografica(areaGeografica).adicionarSensorAListaDeSensores(sensor);
            return true;
        }
        return false;
    }
}
