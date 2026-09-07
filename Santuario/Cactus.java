/**
 * Planta del desierto: almacena agua y se protege con espinas.
 */
public class Cactus extends Planta {

    private double cantidadDeEspinas;
    private double capacidadAlmacenamientoAgua;

    public Cactus(boolean tieneClorofila, double cantidadDeEspinas,
                  double capacidadAlmacenamientoAgua) {
        super(tieneClorofila);
        this.cantidadDeEspinas = cantidadDeEspinas;
        this.capacidadAlmacenamientoAgua = capacidadAlmacenamientoAgua;
    }

    public double getCantidadDeEspinas() {
        return cantidadDeEspinas;
    }

    public void setCantidadDeEspinas(double cantidadDeEspinas) {
        this.cantidadDeEspinas = cantidadDeEspinas;
    }

    public double getCapacidadAlmacenamientoAgua() {
        return capacidadAlmacenamientoAgua;
    }

    public void setCapacidadAlmacenamientoAgua(double capacidadAlmacenamientoAgua) {
        this.capacidadAlmacenamientoAgua = capacidadAlmacenamientoAgua;
    }

    public void retraerEspinas() {
        System.out.println("Cactus: retraigo mis " + cantidadDeEspinas + " espinas.");
    }

    public void desplegarEspinas() {
        System.out.println("Cactus: despliego mis " + cantidadDeEspinas
                + " espinas para defenderme.");
    }

    @Override
    public void esparcirSemillas() {
        System.out.println("Cactus: mis semillas viajan pegadas a los animales del desierto.");
    }

    @Override
    public void comer() {
        System.out.println("Cactus: uso el agua almacenada ("
                + capacidadAlmacenamientoAgua + " litros) y los pocos nutrientes de la arena.");
    }
}
