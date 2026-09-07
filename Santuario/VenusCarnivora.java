/**
 * Planta carnivora que atrapa insectos con sus trampas.
 */
public class VenusCarnivora extends Planta {

    private int insectosAtrapados;

    public VenusCarnivora(boolean tieneClorofila, int insectosAtrapados) {
        super(tieneClorofila);
        this.insectosAtrapados = insectosAtrapados;
    }

    public int getInsectosAtrapados() {
        return insectosAtrapados;
    }

    public void setInsectosAtrapados(int insectosAtrapados) {
        this.insectosAtrapados = insectosAtrapados;
    }

    public void cerrarTrampa() {
        insectosAtrapados++;
        System.out.println("VenusCarnivora: cierro la trampa. Insectos atrapados: "
                + insectosAtrapados + ".");
    }

    public void digerirPresa() {
        if (insectosAtrapados > 0) {
            insectosAtrapados--;
            System.out.println("VenusCarnivora: digiero una presa. Quedan "
                    + insectosAtrapados + " en mis trampas.");
        } else {
            System.out.println("VenusCarnivora: no tengo presas para digerir.");
        }
    }

    @Override
    public void comer() {
        cerrarTrampa();
        digerirPresa();
    }

    @Override
    public void esparcirSemillas() {
        System.out.println("VenusCarnivora: libero mis semillas cerca de suelos humedos.");
    }
}
