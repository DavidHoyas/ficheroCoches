import java.io.File;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

public class LeerFicheroXML_DHN {

    public static void main(String[] args) {
        try {

            System.out.println("=== DOM ===");

            // Archivo XML a leer
            File archivoXML = new File("coches.xml");

            // Se crea un objeto DocumentBuilder para poder parsear el XML
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructor = fabrica.newDocumentBuilder();
            Document documento = constructor.parse(archivoXML);

            // Normaliza el documento XML (elimina espacios vacíos y nodos innecesarios)
            documento.getDocumentElement().normalize();

            // Se obtiene el elemento raíz (en este caso "Coches")
            Element raiz = documento.getDocumentElement();

            Element cocheElemento;
            int indice = 0;

            // Recorremos todos los elementos "Coche" hasta que no haya más
            while (true) {
                try {
                    cocheElemento = (Element) raiz.getElementsByTagName("Coche").item(indice);
                    if (cocheElemento == null)
                        break;

                    // Obtenemos el contenido de cada etiqueta dentro de <Coche>
                    String codigo = cocheElemento.getElementsByTagName("Codigo").item(0).getTextContent();
                    String nombre = cocheElemento.getElementsByTagName("Nombre").item(0).getTextContent();
                    String tipo = cocheElemento.getElementsByTagName("Tipo").item(0).getTextContent();
                    String precio = cocheElemento.getElementsByTagName("Precio").item(0).getTextContent();
                    String disponible = cocheElemento.getElementsByTagName("Disponible").item(0).getTextContent();

                    // Mostramos la información en consola
                    System.out.println("Código: " + codigo + ", Nombre: " + nombre +
                            ", Tipo: " + tipo + ", Precio: " + precio + ", Disponible: " + disponible);

                    indice++;
                } catch (Exception e) {
                    break; // Sale del bucle si hay un error (fin de elementos)
                }
            }

            System.out.println("\n=== SAX ===");

            // Se crea un parser SAX para leer el XML secuencialmente
            SAXParserFactory fabricaSAX = SAXParserFactory.newInstance();
            SAXParser parserSAX = fabricaSAX.newSAXParser();

            // Se define un manejador de eventos SAX
            DefaultHandler manejador = new DefaultHandler() {
                // Flags para saber qué elemento se está leyendo
                boolean esCodigo = false;
                boolean esNombre = false;
                boolean esTipo = false;
                boolean esPrecio = false;
                boolean esDisponible = false;

                // Método que se ejecuta al encontrar una etiqueta de apertura
                public void startElement(String uri, String localName, String qName, Attributes atributos) {
                    if (qName.equalsIgnoreCase("Codigo"))
                        esCodigo = true;
                    else if (qName.equalsIgnoreCase("Nombre"))
                        esNombre = true;
                    else if (qName.equalsIgnoreCase("Tipo"))
                        esTipo = true;
                    else if (qName.equalsIgnoreCase("Precio"))
                        esPrecio = true;
                    else if (qName.equalsIgnoreCase("Disponible"))
                        esDisponible = true;
                }

                // Método que se ejecuta al encontrar el contenido de una etiqueta
                public void characters(char[] ch, int start, int length) {
                    String texto = new String(ch, start, length).trim();
                    if (texto.isEmpty())
                        return;

                    // Dependiendo de qué flag esté activo, mostramos la información
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

                // Método que se ejecuta al cerrar una etiqueta (aquí no lo usamos)
                public void endElement(String uri, String localName, String qName) {
                }
            };

            // Se inicia el parseo del archivo XML con el manejador definido
            parserSAX.parse(archivoXML, manejador);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
