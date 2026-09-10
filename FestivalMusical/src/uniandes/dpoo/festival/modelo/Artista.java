package uniandes.dpoo.festival.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase abstracta que representa a un artista que se presenta en el festival.
 *
 * <p>
 * Hereda de {@link Participante} y es la superclase de {@link Banda} y
 * {@link Solista}. Es abstracta porque todo artista es o una banda o un
 * solista.
 * </p>
 *
 * <p>
 * <b>Asociacion del UML:</b> Artista --&gt; Restaurante con rol
 * <code>proveedoresAlimentacion</code> y multiplicidad <code>1..*</code>. Como
 * la multiplicidad es "muchos", se implementa con una lista
 * (<code>List&lt;Restaurante&gt;</code>). La navegabilidad de la flecha va de
 * Artista hacia Restaurante, asi que solo el artista guarda la referencia.
 * </p>
 */
public abstract class Artista extends Participante
{
    // -------------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------------

    /** Nombre con el que el artista se presenta al publico. UML: #nombreArtistico:String */
    protected String nombreArtistico;

    /** Cache o costo que el festival le paga al artista. UML: #costo:double */
    protected double costo;

    /**
     * Restaurantes encargados de la alimentacion del artista.
     * UML: asociacion "proveedoresAlimentacion" con multiplicidad 1..*
     */
    protected List<Restaurante> proveedoresAlimentacion;

    // -------------------------------------------------------------------
    // Constructores
    // -------------------------------------------------------------------

    /**
     * Construye un artista.
     *
     * <p>
     * La lista de proveedores se inicializa vacia en el constructor (nunca en
     * null) para evitar <code>NullPointerException</code> al agregar elementos
     * mas adelante. La multiplicidad minima 1 del UML se verifica con
     * {@link #cumpleMultiplicidadProveedores()}.
     * </p>
     *
     * @param nombre          nombre legal del artista.
     * @param nombreArtistico nombre con el que se presenta.
     * @param costo           valor que cobra por presentarse.
     */
    public Artista( String nombre, String nombreArtistico, double costo )
    {
        super( nombre );
        this.nombreArtistico = nombreArtistico;
        this.costo = costo;
        this.proveedoresAlimentacion = new ArrayList<Restaurante>( );
    }

    // -------------------------------------------------------------------
    // Metodos
    // -------------------------------------------------------------------

    public String getNombreArtistico( )
    {
        return nombreArtistico;
    }

    public void setNombreArtistico( String nombreArtistico )
    {
        this.nombreArtistico = nombreArtistico;
    }

    public double getCosto( )
    {
        return costo;
    }

    public void setCosto( double costo )
    {
        this.costo = costo;
    }

    /**
     * @return la lista de restaurantes que alimentan al artista.
     */
    public List<Restaurante> getProveedoresAlimentacion( )
    {
        return proveedoresAlimentacion;
    }

    /**
     * Agrega un restaurante como proveedor de alimentacion del artista,
     * evitando duplicados.
     *
     * @param restaurante restaurante a agregar.
     */
    public void agregarProveedorAlimentacion( Restaurante restaurante )
    {
        if( restaurante != null && !proveedoresAlimentacion.contains( restaurante ) )
        {
            proveedoresAlimentacion.add( restaurante );
        }
    }

    /**
     * Verifica la multiplicidad 1..* exigida por el diagrama.
     *
     * @return true si el artista tiene al menos un proveedor de alimentacion.
     */
    public boolean cumpleMultiplicidadProveedores( )
    {
        return !proveedoresAlimentacion.isEmpty( );
    }

    /**
     * Para un artista el valor economico es un <b>egreso</b> del festival, por
     * eso se devuelve negativo: es dinero que el festival paga.
     *
     * @return el costo del artista, en negativo.
     */
    @Override
    public double calcularValorEconomico( )
    {
        return -costo;
    }

    /**
     * Numero de personas que componen al artista. Es abstracto porque una
     * {@link Banda} tiene varios integrantes y un {@link Solista} siempre uno.
     *
     * @return cantidad de integrantes.
     */
    public abstract int getCantidadIntegrantes( );

    @Override
    public String toString( )
    {
        return nombreArtistico + " [" + nombre + "]";
    }
}
