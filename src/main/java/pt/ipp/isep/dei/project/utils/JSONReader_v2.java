/* package pt.ipp.isep.dei.project.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
*/
/*public class JSONReader_v2 implements pt.ipp.isep.dei.project.model.FileReader {

    private JSONReader_v2() {
        // empty
    }

    @Override
    public String getTypeName() {
        return "";
    }

    @Override
    public List<List<String>> readFile() {
        return null;
    }

    public void parse(String json) throws IOException {
        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        JsonNode rootNode = mapper.readTree(json);
        Iterator<Map.Entry<String, JsonNode>> fieldsIterator = rootNode.fields();
        while (fieldsIterator.hasNext()) {
            Map.Entry<String, JsonNode> field = fieldsIterator.next();
            System.out.println("Key: " + field.getKey() + "\tValue:" + field.getValue());
        }
    }
}
*/