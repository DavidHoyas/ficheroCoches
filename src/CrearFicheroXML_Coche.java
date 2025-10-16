import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

// Clase Coche serializable
class Coche implements Serializable {
    int codigo;
    String nombre;
    String tipo;
    float precio;
    boolean disponible;

    Coche(int codigo, String nombre, String tipo, float precio, boolean disponible) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.disponible = disponible;
    }
}

public class CrearFicheroXML_Coche {

    public static void main(String[] args) {
        try {
            // Crear documento XML vacío
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            // Crear nodo raíz <Coches>
            Element root = doc.createElement("Coches");
            doc.appendChild(root);

            // Leer objetos Coche desde coches.dat
            List<Coche> coches = new ArrayList<>();

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("coches.dat"))) {
                while (true) {
                    coches.add((Coche) ois.readObject());
                }
            } catch (EOFException eof) {
                // Fin del fichero: normal
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Crear nodos XML para cada coche
            for (Coche c : coches) {
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

            // Guardar el documento XML en un fichero coches.xml
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // para sangría
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("coches.xml"));
            transformer.transform(source, result);

            System.out.println("Fichero coches.xml creado correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
