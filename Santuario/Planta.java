/**
 * Clase abstracta que representa una planta.
 * Implementa SerVivo y agrega el comportamiento propio del reino vegetal.
 */
public abstract class Planta implements SerVivo {

    private boolean tieneClorofila;

    public Planta(boolean tieneClorofila) {
        this.tieneClorofila = tieneClorofila;
    }

    public boolean isTieneClorofila() {
        return tieneClorofila;
    }

    public void setTieneClorofila(boolean tieneClorofila) {
        this.tieneClorofila = tieneClorofila;
    }

    /**
     * Comportamiento comun a todas las plantas con clorofila.
     */
    public void hacerFotosintesis() {
        if (tieneClorofila) {
            System.out.println(getClass().getSimpleName()
                    + ": transformo la luz solar en energia (fotosintesis).");
        } else {
            System.out.println(getClass().getSimpleName()
                    + ": no tengo clorofila, no puedo hacer fotosintesis.");
        }
    }

    /**
     * Cada tipo de planta esparce sus semillas de una forma distinta.
     */
    public abstract void esparcirSemillas();

    // ---- Implementacion de SerVivo ----

    @Override
    public void respirar() {
        System.out.println(getClass().getSimpleName()
                + ": absorbo dioxido de carbono y libero oxigeno.");
    }

    @Override
    public void comer() {
        System.out.println(getClass().getSimpleName()
                + ": tomo agua y nutrientes del suelo por mis raices.");
    }

    @Override
    public void reproducirse() {
        System.out.println(getClass().getSimpleName() + ": me reproduzco...");
        esparcirSemillas();
    }
}
