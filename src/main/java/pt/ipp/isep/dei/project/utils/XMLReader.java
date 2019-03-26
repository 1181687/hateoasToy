package pt.ipp.isep.dei.project.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import pt.ipp.isep.dei.project.io.ui.InputValidator;
import pt.ipp.isep.dei.project.model.FileReader;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.AreaShape;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.sensor.Sensor;
import pt.ipp.isep.dei.project.model.sensor.SensorType;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class XMLReader implements FileReader {
    String typeName;

    public XMLReader() {
        this.typeName = "xml";
    }

    public static void main(String[] args) {
        List<GeographicalArea> GA = null;

        try {
            String path = InputValidator.getString("path");
            File file = new File(path);
            GA = readXMLFileToList(file);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(GA);
    }


    @SuppressWarnings("unchecked")
    public static List<GeographicalArea> readXMLFileToList(File file) {

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        dbFactory.setIgnoringElementContentWhitespace(true);
        dbFactory.setNamespaceAware(true);
        DocumentBuilder dBuilder;
        List<GeographicalArea> geographicalAreaList = new ArrayList<>();

        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();


            NodeList nodeList = doc.getElementsByTagName("geographical_area_list");

            for (int i = 0; i < nodeList.getLength(); i++) {
                geographicalAreaList.add(getGeoArea(nodeList.item(i)));
            }
        } catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();

        }
        return geographicalAreaList;
    }


    private static GeographicalArea getGeoArea(Node node) {

        GeographicalArea geographicalArea = null;
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            String id = getTagValue("id", element);
            String description = getTagValue("description", element);
            GeographicalAreaType type = new GeographicalAreaType(getTagValue("type", element));
            Double width = Double.parseDouble(getTagValue("width", element));
            Double length = Double.parseDouble(getTagValue("length", element));
            Location location = getLocation(getTag("location", element));
            AreaShape areaShape = new AreaShape(width, length, location);
            geographicalArea = new GeographicalArea(id, description, type, location, areaShape);
            addSensorsToGeoArea(geographicalArea, element.getLastChild());
        }

        return geographicalArea;
    }


    private static Location getLocation(Node node) {
        Element element = (Element) node;
        Double latitude = Double.parseDouble(getTagValue("latitude", element));
        Double longitude = Double.parseDouble(getTagValue("longitude", element));
        Double altitude = Double.parseDouble(getTagValue("altitude", element));
        Location location = new Location(latitude, longitude, altitude);
        return location;
    }


    private static void addSensorsToGeoArea(GeographicalArea geographicalArea, Node node) {

        Element element = (Element) node;
        NodeList sensors = element.getChildNodes();
        for (int i = 0; i < sensors.getLength(); i++) {
            Element sensor = (Element) sensors.item(i);
            String id = getTagValue("id", sensor);
            String name = getTagValue("name", sensor);
            LocalDateTime startDate = LocalDateTime.parse(getTagValue("start_date", sensor));
            SensorType type = new SensorType(getTagValue("type", sensor));
            String units = getTagValue("units", sensor);
            Location location = getLocation(element.getFirstChild());
            Sensor sensorObject = new Sensor(id, name, startDate, type, location, units);
            geographicalArea.addSensor(sensorObject);
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
    public String getTypeName() {
        return this.typeName;
    }

    @Override
    public List<List<String>> readFile() {
        return null;
    }
}

