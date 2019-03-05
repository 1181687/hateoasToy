package pt.ipp.isep.dei.project.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;

public class ReadJSONfile {

    @SuppressWarnings("unchecked")
    public static void readJSONFileToList(String[] args) {

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("ReadJSONfile")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray geoAreaList = (JSONArray) obj;
            System.out.println(geoAreaList);

            //Iterate over employee array
            geoAreaList.forEach(areaGeo -> parseAreaGeoObject((JSONObject) areaGeo));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void parseAreaGeoObject(JSONObject areGeo) {

        // Get area geo object within list
        JSONObject areaGeoObject = (JSONObject) areGeo.get("geographical_area");

        // Get objects from geo area
        String id = (String) areaGeoObject.get("id");
        System.out.println("Id: " + id);

        String description = (String) areaGeoObject.get("description");
        System.out.println("Description: " + description);

        String type = (String) areaGeoObject.get("type");
        System.out.println("Type: " + type);

        double width = (double) areaGeoObject.get("width");
        System.out.println("Width: " + width);

        double length = (double) areaGeoObject.get("length");
        System.out.println("Length: " + length);

        JSONObject location = (JSONObject) areaGeoObject.get("location");

        double latitude = (double) location.get("latitude");
        System.out.println("Latitude: " + latitude);

        double longitude = (double) location.get("longitude");
        System.out.println("Longitude: " + longitude);

        double altitude = (double) location.get("altitude");
        System.out.println("Altitude: " + altitude);

        // JSONArray sensors = areaGeoObject
        JSONObject areaSensor = (JSONObject) areaGeoObject.get("area_sensor");

        // SENSOR 1
        String nameSensor1 = (String) areaSensor.get("name");
        System.out.println("Name: " + nameSensor1);

        LocalDateTime startDateSensor1 = (LocalDateTime) areaSensor.get("start_date");
        System.out.println("Start date: " + startDateSensor1);

        String areaSensorType1 = (String) areaSensor.get("type");
        System.out.println("Type: " + areaSensorType1);

        String unitsSensor1 = (String) areaSensor.get("units");
        System.out.println("Units: " + unitsSensor1);

        // Readings sensor 1
        JSONArray readingsSensor1 = (JSONArray) areaSensor.get("readings");

        for (int i = 0; i < readingsSensor1.size(); i++) {
            areaSensor = (JSONObject) readingsSensor1.get(i);

            System.out.println(areaSensor.toString());
        }

        JSONObject sensorLocation1 = (JSONObject) areaSensor.get("location");

        double sensorLatitude1 = (double) sensorLocation1.get("latitude");
        System.out.println("Latitude: " + sensorLatitude1);

        double sensorLongitude1 = (double) sensorLocation1.get("longitude");
        System.out.println("Longitude: " + sensorLongitude1);

        double sensorAltitude1 = (double) sensorLocation1.get("altitude");
        System.out.println("Altitude: " + sensorAltitude1);


        // SENSOR 2
        String nameSensor2 = (String) areaSensor.get("name");
        System.out.println("Name: " + nameSensor2);

        LocalDateTime startDateSensor2 = (LocalDateTime) areaSensor.get("start_date");
        System.out.println("Start date: " + startDateSensor2);

        String areaSensorType2 = (String) areaSensor.get("type");
        System.out.println("Type: " + areaSensorType2);

        String unitsSensor2 = (String) areaSensor.get("units");
        System.out.println("Units: " + unitsSensor2);

        // Readings sensor 2
        JSONArray readingsSensor2 = (JSONArray) areaSensor.get("readings");

        for (int i = 0; i < readingsSensor2.size(); i++) {
            areaSensor = (JSONObject) readingsSensor2.get(i);

            System.out.println(areaSensor.toString());
        }

        JSONObject sensorLocation2 = (JSONObject) areaSensor.get("location");

        double sensorLatitude2 = (double) sensorLocation2.get("latitude");
        System.out.println("Latitude: " + sensorLatitude2);

        double sensorLongitude2 = (double) sensorLocation2.get("longitude");
        System.out.println("Longitude: " + sensorLongitude2);

        double sensorAltitude2 = (double) sensorLocation2.get("altitude");
        System.out.println("Altitude: " + sensorAltitude2);

    }
}