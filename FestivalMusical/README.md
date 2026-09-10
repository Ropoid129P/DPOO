# FestivalMusical — Implementación del diagrama UML

Proyecto Java listo para abrirse en Eclipse. Implementa exactamente las clases,
atributos, herencias y asociaciones del diagrama UML del festival musical.

## Cómo abrirlo en Eclipse

1. Descomprime `FestivalMusical.zip` en tu workspace (o en cualquier carpeta).
2. En Eclipse: `File > Import... > General > Existing Projects into Workspace`.
3. En "Select root directory" elige la carpeta `FestivalMusical` y pulsa `Finish`.
4. Para probarlo: clic derecho sobre `Main.java` > `Run As > Java Application`.

El proyecto ya incluye `.project`, `.classpath` y `.settings`, configurados para
`JavaSE-17`. Si tu Eclipse usa otra versión de Java, basta con clic derecho sobre
el proyecto > `Properties > Java Build Path > Libraries` y seleccionar el JRE que
tengas instalado.

## Estructura

    src/uniandes/dpoo/festival/modelo/    -> las 11 clases del diagrama
    src/uniandes/dpoo/festival/consola/   -> Main.java, programa de prueba

## Cómo se tradujo el UML a código

**Herencia (triángulo hueco).** Cada flecha con punta de triángulo se convirtió
en un `extends`:

    Participante (abstracta)
     +-- Aliado (abstracta)
     |    +-- Emprendimiento
     |    +-- Atraccion
     |    +-- Restaurante
     +-- Artista (abstracta)
          +-- Banda
          +-- Solista

Las clases cuyo nombre aparece en cursiva en el diagrama (`Participante`,
`Aliado`, `Artista`) se declararon `abstract`, porque nunca se instancian
directamente.

**Visibilidad.** El símbolo `#` del UML es `protected` en Java (se usó en los
atributos de las clases abstractas, para que las subclases los hereden y los
usen). El símbolo `-` es `private` (se usó en las clases concretas).

**Asociaciones con multiplicidad "muchos" (`0..*` y `1..*`).** Se implementaron
como `List<...>` inicializada vacía en el constructor, más un método
`agregarXxx(...)` que evita duplicados:

| Asociación del UML | Atributo en Java |
|---|---|
| `FestivalMusical --> Participante` (participantes, 1..*) | `List<Participante> participantes` |
| `Zona --> Atraccion` (atracciones, 0..*) | `List<Atraccion> atracciones` |
| `Zona --> Restaurante` (restaurantes, 1..*) | `List<Restaurante> restaurantes` |
| `Zona --> Amenity` | `List<Amenity> amenities` |
| `Artista --> Restaurante` (proveedoresAlimentacion, 1..*) | `List<Restaurante> proveedoresAlimentacion` |

Las multiplicidades mínimas de 1 no se imponen en el constructor (eso obligaría
a tener todo listo antes de crear el objeto), sino que se verifican con métodos
`cumpleMultiplicidad...()`, para poder ir armando el festival por pasos.

**Asociaciones "uno a uno".** `zonaGeneral` y `zonaVIP` son dos flechas
separadas de `FestivalMusical` hacia `Zona`, cada una con un rol distinto; por
eso son dos atributos simples de tipo `Zona` y no una lista.

**Navegabilidad.** Todas las flechas del diagrama son unidireccionales, así que
solo el lado del origen guarda la referencia. Por ejemplo, un `Artista` conoce
sus restaurantes proveedores, pero un `Restaurante` no sabe qué artistas
alimenta.

**Tipos.** `integrantes` en `Banda` se dejó como `String[]` porque así aparece
en el diagrama, aunque una `List<String>` sería más cómoda.

**Nombres.** `Atracción` se escribió sin tilde (`Atraccion`) para que el nombre
del archivo y el identificador sean portables entre sistemas operativos.

## Métodos que se agregaron

Los compartimentos de métodos del diagrama están vacíos, así que además de
constructores, getters y setters se añadieron algunos métodos que le dan sentido
al modelo y demuestran polimorfismo:

- `Participante.calcularValorEconomico()` — abstracto. Un `Aliado` lo devuelve
  positivo (paga renta al festival) y un `Artista` negativo (cobra caché).
  Gracias a esto, `FestivalMusical.calcularBalanceEconomico()` recorre la lista
  de participantes sin preguntar de qué tipo es cada uno.
- `Aliado.getTipoAliado()` — abstracto, cada subclase describe su tipo.
- `Artista.getCantidadIntegrantes()` — abstracto; `Solista` siempre devuelve 1 y
  `Banda` el tamaño de su arreglo.
- `Zona.calcularIngresosPorRentas()` — suma las rentas de atracciones y
  restaurantes tratándolos a ambos como `Aliado`.
- `FestivalMusical.getArtistas()` y `getAliados()` — filtran la lista de
  participantes por tipo.

## Verificación

El proyecto se compiló con `javac` y se ejecutó `Main` con éxito: imprime el
festival de ejemplo, las dos zonas con sus amenities, atracciones y
restaurantes, los artistas con sus proveedores, y el balance económico.
