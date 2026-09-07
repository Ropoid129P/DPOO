/**
 * Planta que sigue la trayectoria del sol.
 */
public class Girasol extends Planta {

    private double diametroFlor;

    public Girasol(boolean tieneClorofila, double diametroFlor) {
        super(tieneClorofila);
        this.diametroFlor = diametroFlor;
    }

    public double getDiametroFlor() {
        return diametroFlor;
    }

    public void setDiametroFlor(double diametroFlor) {
        this.diametroFlor = diametroFlor;
    }

    public void producirSemillas() {
        System.out.println("Girasol: produzco semillas en mi flor de "
                + diametroFlor + " cm de diametro.");
    }

    public void buscarSol() {
        System.out.println("Girasol: giro mi flor siguiendo la luz del sol.");
    }

    @Override
    public void esparcirSemillas() {
        producirSemillas();
        System.out.println("Girasol: el viento y las aves dispersan mis semillas.");
    }
}
