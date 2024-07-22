//Use case: Adapting an XML data source to a JSON data consumer.
// XMLData.java
public class XMLData {
    public String getXml() {
        return "<data><item>item1</item></data>";
    }
}

// JSONData.java
public interface JSONData {
    String getJson();
}

// Adapter.java
public class Adapter implements JSONData {
    private XMLData xmlData;

    public Adapter(XMLData xmlData) {
        this.xmlData = xmlData;
    }

    @Override
    public String getJson() {
        String xml = xmlData.getXml();
        // Convert XML to JSON (pseudo-code)
        String jsonData = "{\"data\": {\"item\": \"item1\"}}";
        return jsonData;
    }
}

// Main.java
public class Main {
    public static void main(String[] args) {
        XMLData xmlData = new XMLData();
        Adapter adapter = new Adapter(xmlData);
        System.out.println(adapter.getJson());
    }
}
