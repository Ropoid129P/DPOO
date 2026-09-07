/**
 * Animal que convive con las personas y por lo tanto tiene nombre propio.
 */
public abstract class Mascota extends Animal {

    private String nombre;

    public Mascota(String edad, String nombre) {
        super(edad);
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + nombre + " (" + getEdad() + ")";
    }
}
