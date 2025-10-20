import java.io.*;
import java.util.*;

public class CrearFichero_DHN implements Serializable {

    // Campos del coche
    int codigo;
    String nombre;
    String tipo;
    float precio;
    boolean disponible;

    // Constructor
    public CrearFichero_DHN(int codigo, String nombre, String tipo, float precio, boolean disponible) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.disponible = disponible;
    }

    // toString para mostrar datos correctamente
    @Override
    public String toString() {
        return "Código: " + codigo + ", Nombre: " + nombre + ", Tipo: " + tipo +
                ", Precio: " + precio + ", Disponible: " + disponible;
    }

    // Programa principal
    public static void main(String[] args) {
        List<CrearFichero_DHN> coches = new ArrayList<>();

        // Crear coches de ejemplo
        coches.add(new CrearFichero_DHN(101, "BMW X1", "SUV", 35000f, true));
        coches.add(new CrearFichero_DHN(102, "Ford Mustang", "Deportivo", 55000f, true));
        coches.add(new CrearFichero_DHN(103, "BMW X5", "SUV", 70000f, false));
        coches.add(new CrearFichero_DHN(104, "Audi A4", "Sedan", 40000f, true));
        coches.add(new CrearFichero_DHN(105, "BMW M8", "Deportivo", 100000f, true));

        // Guardar la lista en coches.dat
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("coches.dat"))) {
            for (CrearFichero_DHN c : coches) {
                os.writeObject(c);
            }
            System.out.println("Fichero coches.dat creado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
