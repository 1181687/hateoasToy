package pt.ipp.isep.dei.project.utils;

/*
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import pt.ipp.isep.dei.project.model.FileReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class XMLReader implements FileReader {
    String typeName;

    public XMLReader() {
        this.typeName = "xml";
    }

    public void read() throws IOException, ParserConfigurationException, NullPointerException, SAXException {

        //path to the file
        File xmlFile = new File("XMLfile_GA.xml");

        //get document builder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);

        //Normalize the XML Structure;
        doc.getDocumentElement().normalize();

        //root node
        Element root = doc.getDocumentElement();

        //get all geographical areas
        NodeList listOfNodes = doc.getElementsByTagName("geographical_area");

        for (int i = 0; i < listOfNodes.getLength(); i++) {
            Node mainNode = listOfNodes.item(i);
            if (mainNode.getNodeType() == Node.ELEMENT_NODE) {
                Element firstElement = (Element) mainNode;
                NodeList geoArea = firstElement.getElementsByTagName("geographical_area");

                for (int j = 0; j < geoArea.getLength(); ++j) {
                    Element value = (Element) geoArea.item(j);

                    NodeList conditionList = value.getElementsByTagName("description");
                    for (int k = 0; k < conditionList.getLength(); ++k) {
                        Element description = (Element) conditionList.item(k);
                        if (description.getParentNode().getNodeName().equals("geographical_area")) {
                            String conditionText = description.getFirstChild().getTextContent();
                        }
                    }
                    NodeList conditionList1 = value.getElementsByTagName("id");
                    for (int k = 0; k < conditionList1.getLength(); ++k) {
                        Element id = (Element) conditionList1.item(k);
                        String conditionText = id.getFirstChild().getTextContent();
                    }

                    NodeList conditionList2 = value.getElementsByTagName("type");
                    for (int k = 0; k < conditionList2.getLength(); ++k) {
                        Element type = (Element) conditionList2.item(k);
                        String conditionText = type.getFirstChild().getTextContent();
                    }

                    NodeList conditionList3 = value.getElementsByTagName("width");
                    for (int k = 0; k < conditionList3.getLength(); ++k) {
                        Element width = (Element) conditionList3.item(k);
                        String conditionText = width.getFirstChild().getNodeValue();
                    }

                    NodeList conditionList4 = value.getElementsByTagName("length");
                    for (int k = 0; k < conditionList4.getLength(); ++k) {
                        Element length = (Element) conditionList4.item(k);
                        String conditionText = length.getFirstChild().getNodeValue();
                    }


                    NodeList conditionList6 = value.getElementsByTagName("altitude");
                    for (int k = 0; k < conditionList6.getLength(); ++k) {
                        Element altitude = (Element) conditionList6.item(k);
                        if (altitude.getParentNode().getNodeName().equals("location")) {
                            String conditionText = altitude.getFirstChild().getNodeValue();
                        }
                    }

                    NodeList conditionList7 = value.getElementsByTagName("latitude");
                    for (int k = 0; k < conditionList7.getLength(); ++k) {
                        Element latitude = (Element) conditionList7.item(k);
                        String conditionText = latitude.getFirstChild().getNodeValue();

                    }

                    NodeList conditionList8 = value.getElementsByTagName("longitude");
                    for (int k = 0; k < conditionList8.getLength(); ++k) {
                        Element longitude = (Element) conditionList8.item(k);
                        String conditionText = longitude.getFirstChild().getNodeValue();

                    }

                    NodeList conditionList10 = value.getElementsByTagName("sensor");
                    for (int k = 0; k < conditionList10.getLength(); ++k) {
                        Element sensor = (Element) conditionList10.item(k);
                        if (sensor.getParentNode().getNodeName().equals("area_sensors")) {
                            String conditionText = sensor.getFirstChild().getTextContent();
                        }
                    }

                    NodeList conditionList11 = value.getElementsByTagName("id");
                    for (int k = 0; k < conditionList11.getLength(); ++k) {
                        Element id = (Element) conditionList10.item(k);
                        if (id.getParentNode().getNodeName().equals("sensor")) {
                            String conditionText = id.getFirstChild().getTextContent();
                        }
                    }

                    NodeList conditionList12 = value.getElementsByTagName("name");
                    for (int k = 0; k < conditionList12.getLength(); ++k) {
                        Element name = (Element) conditionList12.item(k);
                        String conditionText = name.getFirstChild().getTextContent();

                    }

                    NodeList conditionList13 = value.getElementsByTagName("start_date");
                    for (int k = 0; k < conditionList13.getLength(); ++k) {
                        Element startDate = (Element) conditionList13.item(k);
                        String conditionText = startDate.getFirstChild().getTextContent();

                    }

                    NodeList conditionList14 = value.getElementsByTagName("type");
                    for (int k = 0; k < conditionList14.getLength(); ++k) {
                        Element type = (Element) conditionList14.item(k);
                        String conditionText = type.getFirstChild().getTextContent();

                    }

                    NodeList conditionList15 = value.getElementsByTagName("units");
                    for (int k = 0; k < conditionList15.getLength(); ++k) {
                        Element units = (Element) conditionList15.item(k);
                        String conditionText = units.getFirstChild().getTextContent();

                    }

                    NodeList conditionList16 = value.getElementsByTagName("location");
                    for (int k = 0; k < conditionList16.getLength(); ++k) {
                        Element location = (Element) conditionList16.item(k);
                        if (location.getParentNode().getNodeName().equals("sensor")) {
                            String conditionText = location.getFirstChild().getTextContent();
                        }
                    }

                    NodeList conditionList17 = value.getElementsByTagName("altitude");
                    for (int k = 0; k < conditionList17.getLength(); ++k) {
                        Element altitude = (Element) conditionList17.item(k);
                        if (altitude.getParentNode().getNodeName().equals("location")) {
                            String conditionText = altitude.getFirstChild().getNodeValue();
                        }
                    }

                    NodeList conditionList18 = value.getElementsByTagName("latitude");
                    for (int k = 0; k < conditionList18.getLength(); ++k) {
                        Element latitude = (Element) conditionList18.item(k);
                        String conditionText = latitude.getFirstChild().getNodeValue();

                    }

                    NodeList conditionList19 = value.getElementsByTagName("longitude");
                    for (int k = 0; k < conditionList19.getLength(); ++k) {
                        Element longitude = (Element) conditionList19.item(k);
                        String conditionText = longitude.getFirstChild().getNodeValue();

                    }
                }
            }
        }
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

*/