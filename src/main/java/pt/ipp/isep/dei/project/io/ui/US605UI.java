package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.US605Controller;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Measurement;
import pt.ipp.isep.dei.project.model.SensorTypeList;

import java.util.Objects;

public class US605UI {

    private US605Controller mctrl;

    public US605UI(House house, SensorTypeList listatiposSens) {
        this.mctrl = new US605Controller(house, listatiposSens.novoTipoSensor("temperature"));
    }

    public void run() {

        System.out.println(mctrl.getDisplayRoomList());
        if (mctrl.getDisplayRoomList().isEmpty()) {
            System.out.println("There are no rooms available\n");
        } else {
            String label0 = "Choose the room you want to get the current temperature";
            int option = InputValidator.getIntRange(label0, 1, mctrl.lengthOfRoomList());
            String roomName = mctrl.getNameOfTheChosenRoomInSpecificPos(option - 1);
            Measurement temp = mctrl.getLatestTemperatureRoom(roomName);
            if (Objects.isNull(temp)) {
                System.out.println("There are no temperature values available");
            } else {
                StringBuilder content = new StringBuilder();
                content.append("The latest temperature of the room ");
                content.append(roomName);
                content.append(" is ");
                content.append(temp.getmValue());
                content.append(" and was regitered at ");
                content.append(temp.getmDateTime().toString());
                content.append(".\n");
                System.out.println(content.toString());

                //acrescentar a hora e dia a que se conseguiu obter a temperatura pk o sensor pode estar avariadp
                // e a ultima medição ser de outro dia ou hora etc


                //mudar o metodo getultimoregisto para retornar um objecto mediçao e nao um valor. para assim podermos saber a que data
                //esta associado aquele valor. Fazendo depois .getData.ToString. e para obter o valor fazer getmValor. (este ultimo
                //acrescentar nos testes para conseguir obter valor e funcionarem
            }
        }
    }


}
