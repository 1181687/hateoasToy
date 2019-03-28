package pt.ipp.isep.dei.project.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import pt.ipp.isep.dei.project.model.LocationDTO;
import pt.ipp.isep.dei.project.model.ProjectFileReader;
import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.model.ReadingMapper;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaMapper;
import pt.ipp.isep.dei.project.model.sensor.SensorDTO;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class XMLReader implements ProjectFileReader {

    private String readerName = "xml";

    public XMLReader() {
        // empty
    }

    private static List<Object> readXMLFileToList(Document doc) {

        List<Object> geographicalAreaDTOList = new ArrayList<>();

        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("geographical_area");

        for (int i = 0; i < nodeList.getLength(); i++) {
            geographicalAreaDTOList.add(getGeoArea(nodeList.item(i)));
        }
        return geographicalAreaDTOList;
    }

    @SuppressWarnings("unchecked")
    private static List<Object> readXMLFileToListReadings(Document doc) {

        List<Object> readingDTOList = new ArrayList<>();

        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("reading");

        for (int i = 0; i < nodeList.getLength(); i++) {
            readingDTOList.add(getReadingDTO(nodeList.item(i)));
        }
        return readingDTOList;
    }

    private static ReadingDTO getReadingDTO(Node node) {

        ReadingDTO readingDTO = null;
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            String id = getTagValue("id", element);
            LocalDateTime dateTime;
            if (id.contains("RF")) {
                LocalDate date = LocalDate.parse(getTagValue("timestamp_date", element));
                dateTime = date.atStartOfDay();
            } else {
                ZonedDateTime zonedDateTime = ZonedDateTime.parse(getTagValue("timestamp_date", element));
                dateTime = zonedDateTime.toLocalDateTime();
            }
            Double value = Double.parseDouble(getTagValue("value", element));
            String unit = getTagValue("unit", element);
            readingDTO = ReadingMapper.mapToDTOwithIDandUnits(id, dateTime, value, unit);
        }
        return readingDTO;
    }

    @Override
    public String getTypeName() {
        return this.readerName;
    }

    private static LocationDTO getLocation(Node node) {

        Element element = (Element) node;
        Double latitude = Double.parseDouble(getTagValue("latitude", element));
        Double longitude = Double.parseDouble(getTagValue("longitude", element));
        Double altitude = Double.parseDouble(getTagValue("altitude", element));

        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setLatitude(latitude);
        locationDTO.setLongitude(longitude);
        locationDTO.setElevation(altitude);
        return locationDTO;
    }


    private static GeographicalAreaDTO getGeoArea(Node node) {

        GeographicalAreaDTO geographicalAreaDTO = null;
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            String id = getTagValue("id", element);
            String description = getTagValue("description", element);
            String type = getTagValue("type", element);
            Double width = Double.parseDouble(getTagValue("width", element));
            Double length = Double.parseDouble(getTagValue("length", element));

            LocationDTO location = getLocation(getTag("location", element));

            geographicalAreaDTO = GeographicalAreaMapper.mapToDTO(id, description, type, width, length, location.getLatitude(), location.getLongitude(), location.getElevation());
            addSensorsToGeoArea(geographicalAreaDTO, getTag("area_sensors", element));
        }

        return geographicalAreaDTO;
    }


    private static void addSensorsToGeoArea(GeographicalAreaDTO geographicalAreaDTO, Node node) {

        Element areaSensors = (Element) node;
        NodeList sensors = areaSensors.getChildNodes();
        for (int i = 0; i < sensors.getLength(); i++) {
            if (sensors.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element sensor = (Element) sensors.item(i);
                String id = getTagValue("id", sensor);
                String name = getTagValue("name", sensor);
                LocalDate startDate = LocalDate.parse(getTagValue("start_date", sensor));
                String type = getTagValue("type", sensor);
                String units = getTagValue("units", sensor);
                LocationDTO location = getLocation(getTag("location", sensor));
                SensorDTO sensorObject = new SensorDTO();
                sensorObject.setId(id);
                sensorObject.setName(name);
                sensorObject.setSensorType(type);
                sensorObject.setLocation(location);
                sensorObject.setStartingDate(startDate);
                sensorObject.setUnits(units);
                geographicalAreaDTO.addSensor(sensorObject);

            }
        }
    }


    /**
     * get method
     *
     * @param tag     identifying string of each element
     * @param element part of the xml document
     * @return
     */

    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }


    private static Node getTag(String tag, Element element) {
        Node node = element.getElementsByTagName(tag).item(0);
        return node;
    }

    @Override
    public List<Object> readFile(File file) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        dbFactory.setIgnoringElementContentWhitespace(true);
        dbFactory.setNamespaceAware(true);
        DocumentBuilder dBuilder;
        List<Object> objectList = new ArrayList<>();
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            String firstTag = doc.getDocumentElement().getTagName();

            if (firstTag.equals("geographical_area_list")) {
                objectList = readXMLFileToList(doc);
            }
            if (firstTag.equals("readings_list")) {
                objectList = readXMLFileToListReadings(doc);
            }

        } catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();

        }
        return objectList;
    }
}

