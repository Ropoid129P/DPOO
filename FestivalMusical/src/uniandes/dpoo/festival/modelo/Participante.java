package uniandes.dpoo.festival.modelo;

/**
 * Clase abstracta que representa a cualquier entidad que participa dentro del
 * festival musical.
 *
 * <p>
 * En el diagrama UML esta es la raiz de toda la jerarquia: tanto los
 * {@link Aliado aliados comerciales} como los {@link Artista artistas} son
 * participantes. Por eso el unico atributo comun que se modela aqui es el
 * nombre.
 * </p>
 *
 * <p>
 * Se declara <b>abstracta</b> porque en el UML el nombre de la clase aparece en
 * cursiva: no tiene sentido crear un "Participante" generico, siempre debe ser
 * un aliado o un artista concreto.
 * </p>
 *
 * <p>
 * El atributo se declara <code>protected</code> (simbolo <code>#</code> en el
 * UML) para que las subclases puedan usarlo directamente.
 * </p>
 */
public abstract class Participante
{
    // -------------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------------

    /** Nombre legal o de registro del participante. UML: #nombre:String */
    protected String nombre;

    // -------------------------------------------------------------------
    // Constructores
    // -------------------------------------------------------------------

    /**
     * Construye un participante con su nombre.
     *
     * @param nombre nombre del participante. No debe ser null.
     */
    public Participante( String nombre )
    {
        this.nombre = nombre;
    }

    // -------------------------------------------------------------------
    // Metodos
    // -------------------------------------------------------------------

    /**
     * @return el nombre del participante.
     */
    public String getNombre( )
    {
        return nombre;
    }

    /**
     * Cambia el nombre del participante.
     *
     * @param nombre nuevo nombre.
     */
    public void setNombre( String nombre )
    {
        this.nombre = nombre;
    }

    /**
     * Calcula el valor economico que el participante representa para el
     * festival.
     *
     * <p>
     * Es un metodo <b>abstracto</b> porque el calculo es completamente
     * diferente segun el tipo de participante: un {@link Aliado} le
     * <i>paga</i> al festival por rentar un espacio, mientras que un
     * {@link Artista} le <i>cobra</i> al festival por presentarse. Cada
     * subclase concreta define el signo y la formula.
     * </p>
     *
     * @return valor economico asociado al participante.
     */
    public abstract double calcularValorEconomico( );

    /**
     * Representacion textual usada para imprimir en consola.
     */
    @Override
    public String toString( )
    {
        return nombre;
    }
}
