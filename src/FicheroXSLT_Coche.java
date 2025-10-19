import java.io.File;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

public class FicheroXSLT_Coche {
    public static void main(String[] args) {
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Source xslt = new StreamSource(new File("coches.xslt"));
            Transformer transformer = factory.newTransformer(xslt);

            Source text = new StreamSource(new File("coches.xml"));
            transformer.transform(text, new StreamResult(new File("coches.html")));

            System.out.println("Archivo coches.html creado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
