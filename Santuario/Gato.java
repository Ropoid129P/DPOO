/**
 * Mascota independiente, con fama de tener siete vidas.
 */
public class Gato extends Mascota {

    private int vidasRestantes;
    private boolean esCazador;

    public Gato(String edad, String nombre, int vidasRestantes, boolean esCazador) {
        super(edad, nombre);
        this.vidasRestantes = vidasRestantes;
        this.esCazador = esCazador;
    }

    public int getVidasRestantes() {
        return vidasRestantes;
    }

    public void setVidasRestantes(int vidasRestantes) {
        this.vidasRestantes = vidasRestantes;
    }

    public boolean isEsCazador() {
        return esCazador;
    }

    public void setEsCazador(boolean esCazador) {
        this.esCazador = esCazador;
    }

    public void ronronear() {
        System.out.println(getNombre() + ": rrrrrrr... (ronroneo)");
    }

    @Override
    public void hacerSonido() {
        System.out.println(getNombre() + ": Miau!");
    }

    @Override
    public void comer() {
        if (esCazador) {
            System.out.println(getNombre() + ": cazo un raton y me lo como.");
        } else {
            System.out.println(getNombre() + ": como el alimento de mi plato.");
        }
    }
}
