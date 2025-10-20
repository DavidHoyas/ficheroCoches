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

            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructor = fabrica.newDocumentBuilder();
            Document documento = constructor.parse(archivoXML);

            documento.getDocumentElement().normalize();

            Element raiz = documento.getDocumentElement();

            Element cocheElemento;
            int indice = 0;

            while (true) {
                try {
                    cocheElemento = (Element) raiz.getElementsByTagName("Coche").item(indice);
                    if (cocheElemento == null) break;

                    String codigo = cocheElemento.getElementsByTagName("Codigo").item(0).getTextContent();
                    String nombre = cocheElemento.getElementsByTagName("Nombre").item(0).getTextContent();
                    String tipo = cocheElemento.getElementsByTagName("Tipo").item(0).getTextContent();
                    String precio = cocheElemento.getElementsByTagName("Precio").item(0).getTextContent();
                    String disponible = cocheElemento.getElementsByTagName("Disponible").item(0).getTextContent();

                    System.out.println("Código: " + codigo + ", Nombre: " + nombre +
                            ", Tipo: " + tipo + ", Precio: " + precio + ", Disponible: " + disponible);

                    indice++;
                } catch (Exception e) {
                    break;
                }
            }

            System.out.println("\n=== SAX ===");

            SAXParserFactory fabricaSAX = SAXParserFactory.newInstance();
            SAXParser parserSAX = fabricaSAX.newSAXParser();

            DefaultHandler manejador = new DefaultHandler() {
                boolean esCodigo = false;
                boolean esNombre = false;
                boolean esTipo = false;
                boolean esPrecio = false;
                boolean esDisponible = false;

                public void startElement(String uri, String localName, String qName, Attributes atributos) {
                    if (qName.equalsIgnoreCase("Codigo")) esCodigo = true;
                    else if (qName.equalsIgnoreCase("Nombre")) esNombre = true;
                    else if (qName.equalsIgnoreCase("Tipo")) esTipo = true;
                    else if (qName.equalsIgnoreCase("Precio")) esPrecio = true;
                    else if (qName.equalsIgnoreCase("Disponible")) esDisponible = true;
                }

                public void characters(char[] ch, int start, int length) {
                    String texto = new String(ch, start, length).trim();
                    if (texto.isEmpty()) return;

                    if (esCodigo) {
                        System.out.print("Código: " + texto + ", ");
                        esCodigo = false;
                    } else if (esNombre) {
                        System.out.print("Nombre: " + texto + ", ");
                        esNombre = false;
                    } else if (esTipo) {
                        System.out.print("Tipo: " + texto + ", ");
                        esTipo = false;
                    } else if (esPrecio) {
                        System.out.print("Precio: " + texto + ", ");
                        esPrecio = false;
                    } else if (esDisponible) {
                        System.out.println("Disponible: " + texto);
                        esDisponible = false;
                    }
                }

                public void endElement(String uri, String localName, String qName) {
                }
            };

            parserSAX.parse(archivoXML, manejador);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
