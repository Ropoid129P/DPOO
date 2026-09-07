/**
 * Clase de prueba: crea el santuario, agrega seres vivos y demuestra
 * herencia, abstraccion y polimorfismo.
 */
public class Main {

    public static void main(String[] args) {
        Santuario santuario = new Santuario();

        // Plantas
        Cactus cactus = new Cactus(true, 250.5, 12.0);
        Girasol girasol = new Girasol(true, 30.0);
        VenusCarnivora venus = new VenusCarnivora(true, 0);

        // Animales (mascotas)
        Perro perro = new Perro("3 anios", "Firulais", 50, true);
        Gato gato = new Gato("2 anios", "Michi", 7, true);
        Zorro zorro = new Zorro("1 anio", "Rojo", "Pelaje rojizo");

        santuario.agregarSer(cactus);
        santuario.agregarSer(girasol);
        santuario.agregarSer(venus);
        santuario.agregarSer(perro);
        santuario.agregarSer(gato);
        santuario.agregarSer(zorro);

        santuario.listarSeres();
        System.out.println();

        santuario.iniciarDia();
        System.out.println();

        santuario.jornadaDeSol();
        System.out.println();

        santuario.pasarLista();
        System.out.println();

        // Comportamientos propios de cada clase
        System.out.println("=== Comportamientos especificos ===");
        cactus.desplegarEspinas();
        cactus.retraerEspinas();
        girasol.producirSemillas();
        venus.cerrarTrampa();
        venus.digerirPresa();
        perro.traerObjeto("el periodico");
        gato.ronronear();
        zorro.excavarMadriguera();
        System.out.println();

        santuario.temporadaDeReproduccion();
    }
}
