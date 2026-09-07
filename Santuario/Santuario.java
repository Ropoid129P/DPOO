import java.util.ArrayList;
import java.util.List;

/**
 * El santuario agrupa a todos los seres vivos (asociacion 0..* con SerVivo).
 */
public class Santuario {

    private List<SerVivo> seres;

    public Santuario() {
        this.seres = new ArrayList<>();
    }

    public List<SerVivo> getSeres() {
        return seres;
    }

    public void agregarSer(SerVivo ser) {
        if (ser != null) {
            seres.add(ser);
        }
    }

    public void eliminarSer(SerVivo ser) {
        seres.remove(ser);
    }

    public int cantidadDeSeres() {
        return seres.size();
    }

    /**
     * Polimorfismo: cada ser vivo respira y come a su manera.
     */
    public void iniciarDia() {
        System.out.println("=== Comienza el dia en el santuario ===");
        for (SerVivo ser : seres) {
            ser.respirar();
            ser.comer();
        }
    }

    /**
     * Solo las plantas hacen fotosintesis.
     */
    public void jornadaDeSol() {
        System.out.println("=== Sale el sol ===");
        for (SerVivo ser : seres) {
            if (ser instanceof Planta) {
                ((Planta) ser).hacerFotosintesis();
            }
            if (ser instanceof Girasol) {
                ((Girasol) ser).buscarSol();
            }
        }
    }

    /**
     * Solo los animales emiten sonidos.
     */
    public void pasarLista() {
        System.out.println("=== Pasando lista ===");
        for (SerVivo ser : seres) {
            if (ser instanceof Animal) {
                ((Animal) ser).hacerSonido();
            }
        }
    }

    public void temporadaDeReproduccion() {
        System.out.println("=== Temporada de reproduccion ===");
        for (SerVivo ser : seres) {
            ser.reproducirse();
        }
    }

    public void listarSeres() {
        System.out.println("=== Seres vivos del santuario (" + seres.size() + ") ===");
        for (SerVivo ser : seres) {
            if (ser instanceof Mascota) {
                System.out.println("- " + ser.toString());
            } else {
                System.out.println("- " + ser.getClass().getSimpleName());
            }
        }
    }
}
