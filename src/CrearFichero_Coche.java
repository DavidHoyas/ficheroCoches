import java.io.*;
import java.util.*;

final class Coche implements Serializable {
    int codigo;
    String nombre;
    String tipo;
    float precio;
    boolean disponible;

    public Coche(int codigo, String nombre, String tipo, float precio, boolean disponible) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Código: " + codigo + ", Nombre: " + nombre + ", Tipo: " + tipo + ", Precio: " + precio + ", Disponible: " + disponible;
    }
}

public class CrearFichero_Coche {
    public static void main(String[] args) {
        List<Coche> coches = new ArrayList<>();
        coches.add(new Coche(101, "BMW X1", "SUV", 35000f, true));
        coches.add(new Coche(102, "Ford Mustang", "Deportivo", 55000f, true));
        coches.add(new Coche(103, "BMW X5", "SUV", 70000f, false));
        coches.add(new Coche(104, "Audi A4", "Sedan", 40000f, true));
        coches.add(new Coche(105, "BMW M8", "Deportivo", 100000f, true));

        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("coches.dat"))) {
            for (Coche c : coches) {
                os.writeObject(c);
            }
            System.out.println("Fichero coches.dat creado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

