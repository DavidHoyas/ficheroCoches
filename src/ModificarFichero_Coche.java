import java.io.*;
import java.util.*;

public class ModificarFichero_Coche {

    public static void main(String[] args) {
        if (args.length < 5) {
            System.out.println("Uso correcto: java ModificarFichero_Coche <codigo> <nombre> <tipo> <precio> <disponible>");
            return;
        }

        int codigo = Integer.parseInt(args[0]);
        String nombre = args[1];
        String tipo = args[2];
        float precio = Float.parseFloat(args[3]);
        boolean disponible = Boolean.parseBoolean(args[4]);

        List<Coche> coches = new ArrayList<>();

        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream("coches.dat"))) {
            while (true) {
                Coche c = (Coche) entrada.readObject();
                coches.add(c);
            }
        } catch (EOFException e) {
        } catch (FileNotFoundException e) {
            System.out.println("El fichero coches.dat no existe.");
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        boolean encontrado = false;

        for (Coche c : coches) {
            if (c.codigo == codigo) {
                encontrado = true;

                System.out.println("Datos antiguos: " + c);

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

        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("coches.dat"))) {
            for (Coche c : coches) {
                salida.writeObject(c);
            }
            System.out.println("Fichero actualizado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
