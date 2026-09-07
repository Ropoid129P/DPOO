# Santuario — Proyecto Java basado en el diagrama UML

Implementacion en Java del diagrama de clases del santuario de seres vivos.

## Estructura

| Archivo | Tipo | Descripcion |
|---|---|---|
| `SerVivo.java` | interfaz | `respirar()`, `comer()`, `reproducirse()` |
| `Planta.java` | clase abstracta | `implements SerVivo`. Atributo `tieneClorofila`. `hacerFotosintesis()` concreto y `esparcirSemillas()` abstracto |
| `Cactus.java` | clase | `extends Planta`. Espinas y almacenamiento de agua |
| `Girasol.java` | clase | `extends Planta`. Diametro de flor, produce semillas y busca el sol |
| `VenusCarnivora.java` | clase | `extends Planta`. Atrapa y digiere insectos |
| `Animal.java` | clase abstracta | `implements SerVivo`. Atributo `edad`, metodo abstracto `hacerSonido()` |
| `Mascota.java` | clase abstracta | `extends Animal`. Atributo `nombre` |
| `Perro.java` | clase | `extends Mascota`. Energia, lazarillo, `traerObjeto(String)` |
| `Gato.java` | clase | `extends Mascota`. Vidas restantes, cazador, `ronronear()` |
| `Zorro.java` | clase | `extends Mascota`. Tipo de pelaje, `excavarMadriguera()` |
| `Santuario.java` | clase | Asociacion `0..*` con `SerVivo` (lista `seres`) |
| `Main.java` | clase | Programa de prueba con ejemplos de polimorfismo |

## Como ejecutar

Desde esta carpeta, en una terminal (CMD o PowerShell):

```
javac *.java
java Main
```

En Eclipse: `File > New > Java Project`, desactiva "Use default location" y
apunta a esta carpeta, o copia los `.java` dentro de `src` de un proyecto nuevo.

## Notas de diseno

- Los atributos son `private` con sus `get`/`set`, siguiendo encapsulamiento.
- `Planta` y `Animal` implementan los metodos de `SerVivo`; las subclases
  sobrescriben `comer()` cuando su forma de alimentarse es distinta.
- Los identificadores se escribieron sin tildes (`hacerFotosintesis`,
  `nivelDeEnergia`) para evitar problemas de codificacion al compilar en Windows.
- Las clases estan en el paquete por defecto para que compilen directamente
  con `javac *.java`.
