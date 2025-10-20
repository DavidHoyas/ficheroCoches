import java.io.File;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

public class FicheroXSLT_DHN {

    public static void main(String[] args) {
        try {
            // Crear fábrica de transformadores
            TransformerFactory factory = TransformerFactory.newInstance();

            // Cargar hoja de estilo XSLT
            Source xslt = new StreamSource(new File("coches.xslt"));
            Transformer transformer = factory.newTransformer(xslt);

            // Cargar XML de entrada
            Source text = new StreamSource(new File("coches.xml"));

            // Transformar XML a HTML
            transformer.transform(text, new StreamResult(new File("coches.html")));

            // Mensaje de éxito
            System.out.println("Archivo coches.html creado correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
