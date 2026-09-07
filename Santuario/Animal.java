/**
 * Clase abstracta que representa un animal del santuario.
 */
public abstract class Animal implements SerVivo {

    private String edad;

    public Animal(String edad) {
        this.edad = edad;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    /**
     * Cada especie emite un sonido diferente.
     */
    public abstract void hacerSonido();

    // ---- Implementacion de SerVivo ----

    @Override
    public void respirar() {
        System.out.println(getClass().getSimpleName()
                + ": inhalo oxigeno y exhalo dioxido de carbono.");
    }

    @Override
    public void comer() {
        System.out.println(getClass().getSimpleName() + ": busco alimento y como.");
    }

    @Override
    public void reproducirse() {
        System.out.println(getClass().getSimpleName()
                + ": busco pareja para tener crias.");
    }
}
