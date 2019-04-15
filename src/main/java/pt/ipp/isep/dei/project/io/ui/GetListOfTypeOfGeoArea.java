package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetListOfTypeOfGeoAreaController;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaService;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaTypeDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaTypeList;
import pt.ipp.isep.dei.project.services.GeoAreaTypeService;
import pt.ipp.isep.dei.project.services.SensorTypeService;

import java.util.List;
import java.util.Scanner;

public class GetListOfTypeOfGeoArea {

    /** US004 As an Administrator, I want to get a list of existing geographical areas of a given type. */
    private GetListOfTypeOfGeoAreaController controller;


    public GetListOfTypeOfGeoArea(GeoAreaTypeService geoAreaTypeService) {
        this.controller = new GetListOfTypeOfGeoAreaController(geoAreaTypeService);
    }

    public void run() {
        System.out.println("Choose the Geographical Area type you wish to see.");
        List<GeographicalAreaTypeDTO> geographicalAreaTypeDTOS = controller.getListOfGeoAreaTypes();
        int listIterator = 1;
        for (GeographicalAreaTypeDTO geographicalAreaTypeDTO : geographicalAreaTypeDTOS) {
            System.out.println(listIterator+" - "+ geographicalAreaTypeDTO.getGeoAreaType());
        }

        Scanner ler = new Scanner(System.in);
        int opcaoEscolhida = -1;
        do {
            for (int i = 1; i <= geographicalAreaTypeDTOS.size(); i++) {
                System.out.println(i + "-" + geographicalAreaTypeDTOS.get(i - 1));
            }
            opcaoEscolhida = ler.nextInt();
        }
        while (opcaoEscolhida < 1 || opcaoEscolhida > geographicalAreaTypeDTOS.size());

        String tipoEscolhido = geographicalAreaTypeDTOS.get(opcaoEscolhida - 1);

        List<String> listaDeAGPorTipo = controller.getListaAGPorTipo(tipoEscolhido);

        if (listaDeAGPorTipo.isEmpty()) {
            System.out.println("No Geographical Areas of the chosen type were found.");
        } else {
            if (listaDeAGPorTipo.size() == 1) {
                System.out.println("The Geographical Area of the type " + tipoEscolhido + " is:");
            }
            if (listaDeAGPorTipo.size() > 1) {
                System.out.println("The Geographical Areas of the type " + tipoEscolhido + " are:");
            }
            for (int i = 0; i < listaDeAGPorTipo.size(); i++) {
                System.out.println(listaDeAGPorTipo.get(i));
            }
            System.out.println();
        }
    }
}
