import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

public class CrearFicheroXML_DHN {

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element root = doc.createElement("Coches");
            doc.appendChild(root);

            List<CrearFichero_DHN> coches = new ArrayList<>();

            // Leer objetos desde coches.dat correctamente
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("coches.dat"))) {
                while (true) {
                    coches.add((CrearFichero_DHN) ois.readObject()); // ✅ clase correcta
                }
            } catch (EOFException eof) {
                // Fin del fichero
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Crear XML
            for (CrearFichero_DHN c : coches) {
                Element cocheElem = doc.createElement("Coche");

                Element codigo = doc.createElement("Codigo");
                codigo.appendChild(doc.createTextNode(String.valueOf(c.codigo)));
                cocheElem.appendChild(codigo);

                Element nombre = doc.createElement("Nombre");
                nombre.appendChild(doc.createTextNode(c.nombre));
                cocheElem.appendChild(nombre);

                Element tipo = doc.createElement("Tipo");
                tipo.appendChild(doc.createTextNode(c.tipo));
                cocheElem.appendChild(tipo);

                Element precio = doc.createElement("Precio");
                precio.appendChild(doc.createTextNode(String.valueOf(c.precio)));
                cocheElem.appendChild(precio);

                Element disponible = doc.createElement("Disponible");
                disponible.appendChild(doc.createTextNode(String.valueOf(c.disponible)));
                cocheElem.appendChild(disponible);

                root.appendChild(cocheElem);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // sangría
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("coches.xml"));
            transformer.transform(source, result);

            System.out.println("Fichero coches.xml creado correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
