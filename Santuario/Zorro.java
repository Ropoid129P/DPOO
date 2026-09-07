/**
 * Mascota silvestre del santuario, excava madrigueras.
 */
public class Zorro extends Mascota {

    private String tipoPelaje;

    public Zorro(String edad, String nombre, String tipoPelaje) {
        super(edad, nombre);
        this.tipoPelaje = tipoPelaje;
    }

    public String getTipoPelaje() {
        return tipoPelaje;
    }

    public void setTipoPelaje(String tipoPelaje) {
        this.tipoPelaje = tipoPelaje;
    }

    public void excavarMadriguera() {
        System.out.println(getNombre() + ": excavo una madriguera para refugiarme.");
    }

    @Override
    public void hacerSonido() {
        System.out.println(getNombre() + ": Ring-ding-ding!");
    }

    @Override
    public void comer() {
        System.out.println(getNombre() + ": como frutos, insectos y pequenos roedores.");
    }
}
