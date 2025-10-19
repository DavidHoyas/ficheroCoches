import java.io.File;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

public class LeerFicheroXML_Coche {

    public static void main(String[] args) {
        try {
            System.out.println("=== DOM ===");
            File archivoXML = new File("coches.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(archivoXML);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("Coche");

            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) node;
                    System.out.println(
                            "Código: " + e.getElementsByTagName("Codigo").item(0).getTextContent() +
                                    ", Nombre: " + e.getElementsByTagName("Nombre").item(0).getTextContent() +
                                    ", Tipo: " + e.getElementsByTagName("Tipo").item(0).getTextContent() +
                                    ", Precio: " + e.getElementsByTagName("Precio").item(0).getTextContent() +
                                    ", Disponible: " + e.getElementsByTagName("Disponible").item(0).getTextContent());
                }
            }

            System.out.println("=== SAX ===");
            SAXParserFactory saxFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxFactory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {
                boolean codigo = false, nombre = false, tipo = false, precio = false, disponible = false;

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) {
                    switch (qName) {
                        case "Codigo" -> codigo = true;
                        case "Nombre" -> nombre = true;
                        case "Tipo" -> tipo = true;
                        case "Precio" -> precio = true;
                        case "Disponible" -> disponible = true;
                    }
                }

                @Override
                public void characters(char[] ch, int start, int length) {
                    String value = new String(ch, start, length).trim();
                    if (value.isEmpty())
                        return;

                    if (codigo) {
                        System.out.print("Código: " + value + ", ");
                        codigo = false;
                    } else if (nombre) {
                        System.out.print("Nombre: " + value + ", ");
                        nombre = false;
                    } else if (tipo) {
                        System.out.print("Tipo: " + value + ", ");
                        tipo = false;
                    } else if (precio) {
                        System.out.print("Precio: " + value + ", ");
                        precio = false;
                    } else if (disponible) {
                        System.out.println("Disponible: " + value);
                        disponible = false;
                    }
                }

                @Override
                public void endElement(String uri, String localName, String qName) {

                }
            };

            saxParser.parse(archivoXML, handler);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
