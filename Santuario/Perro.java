/**
 * Mascota fiel, puede ser perro lazarillo.
 */
public class Perro extends Mascota {

    private int nivelDeEnergia;
    private boolean esLazarillo;

    public Perro(String edad, String nombre, int nivelDeEnergia, boolean esLazarillo) {
        super(edad, nombre);
        this.nivelDeEnergia = nivelDeEnergia;
        this.esLazarillo = esLazarillo;
    }

    public int getNivelDeEnergia() {
        return nivelDeEnergia;
    }

    public void setNivelDeEnergia(int nivelDeEnergia) {
        this.nivelDeEnergia = nivelDeEnergia;
    }

    public boolean isEsLazarillo() {
        return esLazarillo;
    }

    public void setEsLazarillo(boolean esLazarillo) {
        this.esLazarillo = esLazarillo;
    }

    public void traerObjeto(String objeto) {
        if (nivelDeEnergia <= 0) {
            System.out.println(getNombre() + ": estoy muy cansado para traer " + objeto + ".");
            return;
        }
        nivelDeEnergia -= 10;
        System.out.println(getNombre() + ": traigo " + objeto
                + ". Energia restante: " + nivelDeEnergia + ".");
    }

    @Override
    public void hacerSonido() {
        System.out.println(getNombre() + ": Guau guau!");
    }

    @Override
    public void comer() {
        nivelDeEnergia += 20;
        System.out.println(getNombre() + ": como mi racion. Energia: " + nivelDeEnergia + ".");
    }
}
