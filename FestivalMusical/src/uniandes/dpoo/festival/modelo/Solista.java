package uniandes.dpoo.festival.modelo;

/**
 * Artista que se presenta como una sola persona.
 *
 * <p>
 * En el UML no agrega atributos propios; su unica diferencia frente a
 * {@link Banda} es de comportamiento, y por eso solo se sobrescribe
 * {@link #getCantidadIntegrantes()}.
 * </p>
 */
public class Solista extends Artista
{
    /**
     * Construye un solista.
     *
     * @param nombre          nombre legal del artista.
     * @param nombreArtistico nombre con el que se presenta.
     * @param costo           valor que cobra por presentarse.
     */
    public Solista( String nombre, String nombreArtistico, double costo )
    {
        super( nombre, nombreArtistico, costo );
    }

    /**
     * Un solista siempre esta compuesto por una unica persona.
     */
    @Override
    public int getCantidadIntegrantes( )
    {
        return 1;
    }
}
