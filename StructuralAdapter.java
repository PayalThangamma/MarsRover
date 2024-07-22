//Use case: Adapting an XML data source to a JSON data consumer.
class XMLData {
    public String getXml() {
        return "<data><item>item1</item></data>";
    }
}
interface JSONData {
    String getJson();
}
class Adapter implements JSONData {
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
class StructuralAdapter {
    public static void main(String[] args) {
        XMLData xmlData = new XMLData();
        Adapter adapter = new Adapter(xmlData);
        System.out.println(adapter.getJson());
    }
}
