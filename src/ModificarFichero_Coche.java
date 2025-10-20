import java.io.*;
import java.util.*;

public class ModificarFichero_Coche {

    public static void main(String[] args) {
        List<CrearFichero_Coche> coches = new ArrayList<>();

        // Leer coches desde coches.dat
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream("coches.dat"))) {
            while (true) {
                CrearFichero_Coche c = (CrearFichero_Coche) entrada.readObject();
                coches.add(c);
            }
        } catch (EOFException e) {
            // Fin del fichero
        } catch (FileNotFoundException e) {
            System.out.println("El fichero coches.dat no existe. Crea primero el fichero con CrearFichero_Coche.");
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        // Mostrar todos los coches
        System.out.println("Coches actuales en el fichero:");
        for (CrearFichero_Coche c : coches) {
            System.out.println(c);
        }

        // Comprobar si hay suficientes argumentos para modificar
        if (args.length < 5) {
            System.out.println("\nPara modificar un coche, ejecuta con: <codigo> <nombre> <tipo> <precio> <disponible>");
            return;
        }

        // Leer argumentos
        int codigo = Integer.parseInt(args[0]);
        String nombre = args[1];
        String tipo = args[2];
        float precio = Float.parseFloat(args[3]);
        boolean disponible = Boolean.parseBoolean(args[4]);

        boolean encontrado = false;

        // Buscar y modificar coche
        for (CrearFichero_Coche c : coches) {
            if (c.codigo == codigo) {
                encontrado = true;
                System.out.println("\nDatos antiguos: " + c);

                c.nombre = nombre;
                c.tipo = tipo;
                c.precio = precio;
                c.disponible = disponible;

                System.out.println("Datos modificados: " + c);
                break;
            }
        }

        if (!encontrado) {
            System.out.println("No existe ningún coche con el código " + codigo);
            return;
        }

        // Guardar cambios en coches.dat
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("coches.dat"))) {
            for (CrearFichero_Coche c : coches) {
                salida.writeObject(c);
            }
            System.out.println("\nFichero actualizado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
