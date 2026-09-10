package uniandes.dpoo.festival.modelo;

/**
 * Artista conformado por varios musicos.
 *
 * <p>
 * En el UML el atributo <code>integrantes</code> aparece como
 * <code>String[]</code>, por lo que aqui se respeta el arreglo en lugar de
 * usar una lista.
 * </p>
 */
public class Banda extends Artista
{
    // -------------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------------

    /** Nombres de los integrantes de la banda. UML: -integrantes:String[] */
    private String[] integrantes;

    // -------------------------------------------------------------------
    // Constructores
    // -------------------------------------------------------------------

    /**
     * Construye una banda.
     *
     * @param nombre          nombre legal o de registro de la agrupacion.
     * @param nombreArtistico nombre con el que se presenta.
     * @param costo           valor que cobra por presentarse.
     * @param integrantes     nombres de los integrantes.
     */
    public Banda( String nombre, String nombreArtistico, double costo, String[] integrantes )
    {
        super( nombre, nombreArtistico, costo );
        this.integrantes = integrantes != null ? integrantes : new String[0];
    }

    // -------------------------------------------------------------------
    // Metodos
    // -------------------------------------------------------------------

    public String[] getIntegrantes( )
    {
        return integrantes;
    }

    public void setIntegrantes( String[] integrantes )
    {
        this.integrantes = integrantes != null ? integrantes : new String[0];
    }

    /**
     * @return el numero de integrantes registrados en el arreglo.
     */
    @Override
    public int getCantidadIntegrantes( )
    {
        return integrantes.length;
    }
}
