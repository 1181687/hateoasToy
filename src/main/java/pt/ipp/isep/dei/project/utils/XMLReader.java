//package pt.ipp.isep.dei.project.utils;

/*public class XMLReader implements FileReader {
    String typeName;

    public XMLReader() {
        this.typeName = "xml";
    }

    public static void main(String[] argv) throws SAXException, IOException, ParserConfigurationException {

        File xmlFile = new File("XMLfile_GA.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);

        doc.getDocumentElement().normalize();

        NodeList feeds = doc.getElementsByTagName("geographical_area_list");

        for (int i = 0; i < feeds.getLength(); i++) {
            Node mainNode = feeds.item(i);
            if (mainNode.getNodeType() == Node.ELEMENT_NODE) {
                Element firstElement = (Element) mainNode;
                NodeList geoArea = firstElement.getElementsByTagName("geographical_area");

                for (int j = 0; j < geoArea.getLength(); ++j) {
                    Element value = (Element) geoArea.item(j);

                    NodeList conditionList = value.getElementsByTagName("description");
                    for (int k = 0; k < conditionList.getLength(); ++k) {
                        Element condition = (Element) conditionList.item(k);
                        if (condition.getParentNode().getNodeName().equals("geographical_area")) {
                            String conditionText = condition.getFirstChild().getNodeValue();
                        }
                    }
                    NodeList conditionList1 = value.getElementsByTagName("id");
                    for (int k = 0; k < conditionList1.getLength(); ++k) {
                        Element condition = (Element) conditionList1.item(k);
                        String conditionText = condition.getFirstChild().getNodeValue();
                    }

                    NodeList conditionList2 = value.getElementsByTagName("type");
                    for (int k = 0; k < conditionList2.getLength(); ++k) {
                        Element condition = (Element) conditionList2.item(k);
                        String conditionText = condition.getFirstChild().getNodeValue();
                    }

                    NodeList conditionList3 = value.getElementsByTagName("width");
                    for (int k = 0; k < conditionList3.getLength(); ++k) {
                        Element condition = (Element) conditionList3.item(k);
                        String conditionText = condition.getFirstChild().getNodeValue();
                    }

                    NodeList conditionList4 = value.getElementsByTagName("length");
                    for (int k = 0; k < conditionList4.getLength(); ++k) {
                        Element condition = (Element) conditionList4.item(k);
                        String conditionText = condition.getFirstChild().getNodeValue();
                    }

                    NodeList conditionList5 = value.getElementsByTagName("location");
                    for (int k = 0; k < conditionList5.getLength(); ++k) {
                        Element condition = (Element) conditionList5.item(k);
                        for (int l = 0; l < condition.getChildNodes().getLength(); ++l) {
                            Element condition5 = (Element) condition.getChildNodes().item(l);
                            String conditionText = condition5.getFirstChild().getNodeValue();
                        }
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