package pt.ipp.isep.dei.project.utils;

import pt.ipp.isep.dei.project.model.devices.DeviceType;

import java.util.ArrayList;
import java.util.List;

public final class ApplicationConfiguration {

    public static void defineMeteringPeriods(String configFile) {
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile(configFile, "MeteringPeriodGrid"));
        int meteringPeriodDevice = Integer.parseInt(Utils.readConfigFile(configFile, "MeteringPeriodDevice"));
        if (1440 % meteringPeriodGrid != 0) {
            System.out.println("The grid metering period is not valid. Please configure the grid metering period for a valid value.");
            return;
        }

        if (1440 % meteringPeriodDevice != 0 || meteringPeriodDevice % meteringPeriodGrid != 0) {
            System.out.println("The device metering period is not valid. Please configure the device metering period for a valid value.");
        }
    }


    /**
     * This method create device types using a path and a class name.
     *
     */
    public static List<DeviceType> createDeviceTypes(String configFile) {
        List<String> deviceTypeListReader = Utils.readConfigFileToList(configFile, "devicetype.count", "devicetype.name");
        List<DeviceType> deviceTypeList = new ArrayList<>();
        for (String className : deviceTypeListReader) {
            String path = Utils.readConfigFile(configFile, className);
            try {
                DeviceType dt = (DeviceType) Class.forName(path).newInstance();
                deviceTypeList.add(dt);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return deviceTypeList;
    }
}
