package pt.ipp.isep.dei.project.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.model.ReadingMapper;
import pt.ipp.isep.dei.project.model.sensor.SensorDTO;

import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public final class JSONReaderReadings {

    private JSONReaderReadings() {
        // empty
    }

    @SuppressWarnings("unchecked")
    public static List<Object> readJSONReadingFileToList(FileReader reader) {
        List<Object> readingList;
        //JSON parser object to parse read file
        JsonParser jsonParser = new JsonParser();
        //Read JSON file
        JsonElement elem = jsonParser.parse(reader);
        try {
            readingList = parseJsonObjects(elem);
        } catch (NumberFormatException | NullPointerException e) {
            readingList = null;
        }
        return readingList;
    }

    private static List<Object> parseJsonObjects(JsonElement sensorReading) throws NumberFormatException, NullPointerException {
        List<Object> readingList = new ArrayList<>();

        // Get reading within list

        if (sensorReading.isJsonObject()) {
            JsonObject jsonObject = sensorReading.getAsJsonObject();

            JsonArray readingArray = jsonObject.get("readings").getAsJsonArray();


            List<JsonObject> list = new ArrayList<>();

            for (int i = 0; i < readingArray.size(); i++) {
                list.add(readingArray.get(i).getAsJsonObject());
            }
            //Reads reading attributes
            for (JsonObject object : list) {
                // Get objects from reading

                SensorDTO sensorDTO = new SensorDTO();

                String sensorID = object.get("id").getAsString();
                sensorDTO.setId(sensorID);

                if (sensorID.contains("RF")) {
                    LocalDate date = LocalDate.parse(object.get("timestamp/date").getAsString());
                    LocalDateTime dateTime = localDateToLocalDateTime(date);
                    //readingDTO.setDateTime(dateTime);
                    double value = object.get("value").getAsDouble();
                    ReadingDTO readingDTO = ReadingMapper.mapToDTO(dateTime, value);
                    readingList.add(readingDTO);
                } else {
                    ZonedDateTime dateTime = ZonedDateTime.parse(object.get("timestamp/date").getAsString());
                    LocalDateTime dateTimeReading = dateTime.toLocalDateTime();
                    //readingDTO.setDateTime(dateTimeReading);
                    double value = object.get("value").getAsDouble();
                    ReadingDTO readingDTO = ReadingMapper.mapToDTO(dateTimeReading, value);
                    readingList.add(readingDTO);
                }
                //double value = object.get("value").getAsDouble();
                //readingDTO.setValue(value);


                String readingUnit = object.get("unit").getAsString();
                sensorDTO.setUnits(readingUnit);

                readingList.add(sensorDTO);

            }
        }
        return readingList;
    }


    public static LocalDateTime localDateToLocalDateTime(LocalDate localDate) {
        return localDate.atStartOfDay();
    }
}