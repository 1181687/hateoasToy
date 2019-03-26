package pt.ipp.isep.dei.project.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.model.ReadingMapper;

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


    public static List<ReadingDTO> readFile(FileReader reader) {
        List<ReadingDTO> readingList;
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

    private static List<ReadingDTO> parseJsonObjects(JsonElement sensorReading) throws NumberFormatException, NullPointerException {
        List<ReadingDTO> readingList = new ArrayList<>();

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

                String sensorID = object.get("id").getAsString();

                String readingUnit = object.get("unit").getAsString();

                if (sensorID.contains("RF")) {
                    LocalDate date = LocalDate.parse(object.get("timestamp/date").getAsString());
                    LocalDateTime dateTime = localDateToLocalDateTime(date);
                    double value = object.get("value").getAsDouble();
                    ReadingDTO readingDTO = ReadingMapper.mapToDTO_id_units(sensorID, dateTime, value, readingUnit);
                    readingList.add(readingDTO);
                } else {
                    ZonedDateTime dateTime = ZonedDateTime.parse(object.get("timestamp/date").getAsString());
                    LocalDateTime dateTimeReading = dateTime.toLocalDateTime();
                    double value = object.get("value").getAsDouble();
                    ReadingDTO readingDTO = ReadingMapper.mapToDTO_id_units(sensorID, dateTimeReading, value, readingUnit);
                    readingList.add(readingDTO);
                }
            }
        }
        return readingList;
    }


    public static LocalDateTime localDateToLocalDateTime(LocalDate localDate) {
        return localDate.atStartOfDay();
    }
}