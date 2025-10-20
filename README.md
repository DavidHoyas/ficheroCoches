# Ficheros  

## Descripción

El objetivo del proyecto es **manejar información persistente mediante ficheros en Java** utilizando diferentes tipos de acceso:  
- Ficheros binarios `.dat`  
- Transformación a formato XML  
- Lectura mediante DOM y SAX  
- Conversión a HTML mediante XSLT  

El proyecto está centrado en la **gestión de coches**.

---

## Información a guardar

Cada **coche** almacenado en el sistema contiene los siguientes campos:

| Campo         | Tipo de dato | Descripción                             |
|---------------|--------------|------------------------------------------|
| `codigo`      | `int`        | Identificador único del coche            |
| `nombre`      | `String`     | Marca y/o modelo                         |
| `tipo`        | `String`     | Tipo de vehículo (SUV, Berlina, etc.)    |
| `precio`      | `float`      | Precio en euros                          |
| `disponible`  | `boolean`    | Indica si está disponible o no           |

Los datos se guardan en un **fichero binario** (`coches.dat`) usando `ObjectOutputStream` y se leen con `ObjectInputStream`.

---

## Estructura y programas desarrollados

### 1. `CrearFichero_DHN.java`
- **Función:** Crea el fichero binario `coches.dat` con una lista inicial de coches.
- **Acciones:**  
  - Define objetos de tipo `CrearFichero_DHN` (serializable).  
  - Almacena varios coches de ejemplo en el fichero.  
- **Formato:** `.dat` (binario, objetos serializados).

---

### 2. `ModificarFichero_DHN.java`
- **Función:** Permite consultar, modificar o añadir coches desde consola.  
- **Acciones:**  
  - Muestra todos los coches actuales.  
  - Menú interactivo mediante `switch-case` con opciones:  
    1. Ver coches existentes  
    2. Modificar un coche existente o añadir uno nuevo  
    3. Salir  
  - Actualiza el fichero `coches.dat` tras cada operación.  
- **Interfaz:** Consola (`System.out.println` y `Scanner`).

---

### 3. `CrearFicheroXML_DHN.java`
- **Función:** Convierte el fichero binario `coches.dat` a un fichero XML estructurado (`coches.xml`).  
- **Acciones:**  
  - Lee todos los coches serializados.  
  - Genera un documento XML con DOM.  
  - Guarda la información en un fichero `coches.xml`.  
- **Formato de salida:** XML estructurado con nodos `<Coche>`.

---

### 4. `LeerFicheroXML_DHN.java`
- **Función:** Lee y muestra el contenido de `coches.xml` usando:
  - **DOM** → lectura estructurada del árbol.  
  - **SAX** → lectura basada en eventos.  
- **Acciones:**  
  - Muestra por consola los datos de todos los coches.  
  - Sirve para verificar que la conversión se ha hecho correctamente.

---

### 5. `FicheroXSLT_DHN.java`
- **Función:** Transforma el fichero XML a HTML utilizando XSLT.
- **Acciones:**  
  - Genera una tabla HTML que muestra al menos el nombre, tipo y precio de los coches.
  - Permite visualizar la información desde un navegador web.
- **Formatos:**  
  - Entrada: `coches.xml`  
  - Transformación: `FicheroXSLT_DHN.java`  
  - Salida: `coches.html`

---

## Estructura del proyecto

```
FICHEROCOCHES/
- .vscode/
	- (configuración de VS Code)

- bin/
	- CrearFichero_DHN.class
	- CrearFicheroXML_DHN.class
	- FicheroXSLT_DHN.class
	- LeerFicheroXML_DHN$1.class
	- LeerFicheroXML_DHN.class
	- ModificarFichero_DHN.class

- src/
	- CrearFichero_Coche.class
	- CrearFichero_DHN.java
	- CrearFicheroXML_DHN.java
	- FicheroXSLT_DHN.java
	- LeerFicheroXML_DHN.java
	- ModificarFichero_Coche.class
	- ModificarFichero_DHN.java

- coches.dat          (fichero binario con los objetos serializados)
- coches.xml          (XML generado desde coches.dat)
- coches.xslt         (hoja de estilo XSLT)
- coches.html         (HTML generado con la transformación XSLT)
- README.md           (documentación del proyecto)
```


