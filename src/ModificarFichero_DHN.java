import java.io.*;
import java.util.*;

public class ModificarFichero_DHN {

    public static void main(String[] args) {
        List<CrearFichero_DHN> coches = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        // Leer coches desde coches.dat
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream("coches.dat"))) {
            while (true) {
                CrearFichero_DHN c = (CrearFichero_DHN) entrada.readObject();
                coches.add(c);
            }
        } catch (EOFException e) {
            // Fin del fichero
        } catch (FileNotFoundException e) {
            System.out.println("El fichero coches.dat no existe.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        int opcion = 0;
        while (opcion != 3) {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Ver coches existentes");
            System.out.println("2. Modificar o añadir un coche");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.println("\nCoches actuales en el fichero:");
                    for (CrearFichero_DHN c : coches) {
                        System.out.println(c);
                    }
                    break;

                case 2:
                    System.out.print("Ingresa el código del coche a modificar o 0 para añadir uno nuevo: ");
                    int codigo = sc.nextInt();
                    sc.nextLine();

                    if (codigo == 0) {
                        // Añadir nuevo coche
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();
                        System.out.print("Tipo: ");
                        String tipo = sc.nextLine();
                        System.out.print("Precio: ");
                        float precio = sc.nextFloat();
                        System.out.print("Disponible (true/false): ");
                        boolean disponible = sc.nextBoolean();
                        sc.nextLine();

                        int nuevoCodigo = coches.size() > 0 ? coches.get(coches.size() - 1).codigo + 1 : 1;
                        CrearFichero_DHN nuevoCoche = new CrearFichero_DHN(nuevoCodigo, nombre, tipo, precio,
                                disponible);
                        coches.add(nuevoCoche);
                        System.out.println("Coche añadido correctamente: " + nuevoCoche);

                    } else {
                        // Modificar coche existente
                        boolean encontrado = false;
                        for (CrearFichero_DHN c : coches) {
                            if (c.codigo == codigo) {
                                encontrado = true;
                                System.out.println("\nDatos antiguos: " + c);

                                System.out.print("Nuevo nombre: ");
                                c.nombre = sc.nextLine();
                                System.out.print("Nuevo tipo: ");
                                c.tipo = sc.nextLine();
                                System.out.print("Nuevo precio: ");
                                c.precio = sc.nextFloat();
                                System.out.print("Disponible (true/false): ");
                                c.disponible = sc.nextBoolean();
                                sc.nextLine();

                                System.out.println("Datos modificados: " + c);
                                break;
                            }
                        }
                        if (!encontrado) {
                            System.out.println("No existe ningún coche con el código " + codigo);
                        }
                    }
                    break;

                case 3:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

            // Guardar cambios en coches.dat después de cada operación
            try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("coches.dat"))) {
                for (CrearFichero_DHN c : coches) {
                    salida.writeObject(c);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        sc.close();
    }
}
