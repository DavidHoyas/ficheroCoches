import java.io.*;
import java.util.*;

public class ModificarFichero_Coche {
    public static void main(String[] args) {
        if (args.length < 5) {
            System.out.println("Modificar: codigo, nombre, tipo, precio, disponible");
            return;
        }

        int codigo = Integer.parseInt(args[0]);
        String nombre = args[1];
        String tipo = args[2];
        float precio = Float.parseFloat(args[3]);
        boolean disponible = Boolean.parseBoolean(args[4]);

        List<Coche> coches = new ArrayList<>();

        try (ObjectInputStream oos = new ObjectInputStream(new FileInputStream("coches.dat"))) {
            while (true) {
                coches.add((Coche) oos.readObject());
            }
        } catch (EOFException e) {

        } catch (Exception e) {
            e.printStackTrace();
        }

        boolean encontrado = false;
        for (Coche c : coches) {
            if (c.codigo == codigo) {
                System.out.println("Datos antiguos: " + c);
                c.nombre = nombre;
                c.tipo = tipo;
                c.precio = precio;
                c.disponible = disponible;
                encontrado = true;
                System.out.println("Datos modificados: " + c);
                break;
            }
        }

        if (!encontrado) {
            System.out.println("No existe ningún coche con el código " + codigo);
        } else {
            try (ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream("coches.dat"))) {
                for (Coche c : coches) {
                    oss.writeObject(c);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
