package uniandes.dpoo.festival.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Zona fisica del festival. El {@link FestivalMusical} tiene dos zonas: la
 * general y la VIP.
 *
 * <p>
 * <b>Asociaciones del UML implementadas en esta clase:</b>
 * </p>
 * <ul>
 * <li>Zona --&gt; Amenity: los servicios disponibles en la zona.</li>
 * <li>Zona --&gt; Atraccion, rol <code>atracciones</code>, multiplicidad
 * <code>0..*</code>: una zona puede no tener atracciones.</li>
 * <li>Zona --&gt; Restaurante, rol <code>restaurantes</code>, multiplicidad
 * <code>1..*</code>: toda zona debe tener al menos un restaurante.</li>
 * </ul>
 *
 * <p>
 * Todas las colecciones se inicializan vacias en el constructor; las
 * multiplicidades minimas se validan con {@link #cumpleMultiplicidades()} en
 * lugar de bloquear la construccion, para poder ir armando la zona por pasos.
 * </p>
 */
public class Zona
{
    // -------------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------------

    /** Nombre de la zona (por ejemplo "General" o "VIP"). */
    private String nombre;

    /** Servicios disponibles en la zona. UML: asociacion Zona --> Amenity */
    private List<Amenity> amenities;

    /** Atracciones ubicadas en la zona. UML: rol "atracciones", 0..* */
    private List<Atraccion> atracciones;

    /** Restaurantes ubicados en la zona. UML: rol "restaurantes", 1..* */
    private List<Restaurante> restaurantes;

    // -------------------------------------------------------------------
    // Constructores
    // -------------------------------------------------------------------

    /**
     * Construye una zona vacia, lista para recibir amenities, atracciones y
     * restaurantes.
     *
     * @param nombre nombre de la zona.
     */
    public Zona( String nombre )
    {
        this.nombre = nombre;
        this.amenities = new ArrayList<Amenity>( );
        this.atracciones = new ArrayList<Atraccion>( );
        this.restaurantes = new ArrayList<Restaurante>( );
    }

    // -------------------------------------------------------------------
    // Metodos
    // -------------------------------------------------------------------

    public String getNombre( )
    {
        return nombre;
    }

    public void setNombre( String nombre )
    {
        this.nombre = nombre;
    }

    public List<Amenity> getAmenities( )
    {
        return amenities;
    }

    public List<Atraccion> getAtracciones( )
    {
        return atracciones;
    }

    public List<Restaurante> getRestaurantes( )
    {
        return restaurantes;
    }

    /**
     * Agrega un servicio a la zona.
     *
     * @param amenity servicio a agregar.
     */
    public void agregarAmenity( Amenity amenity )
    {
        if( amenity != null && !amenities.contains( amenity ) )
        {
            amenities.add( amenity );
        }
    }

    /**
     * Agrega una atraccion a la zona.
     *
     * @param atraccion atraccion a agregar.
     */
    public void agregarAtraccion( Atraccion atraccion )
    {
        if( atraccion != null && !atracciones.contains( atraccion ) )
        {
            atracciones.add( atraccion );
        }
    }

    /**
     * Agrega un restaurante a la zona.
     *
     * @param restaurante restaurante a agregar.
     */
    public void agregarRestaurante( Restaurante restaurante )
    {
        if( restaurante != null && !restaurantes.contains( restaurante ) )
        {
            restaurantes.add( restaurante );
        }
    }

    /**
     * Verifica que se cumplan las multiplicidades minimas del diagrama: 1..*
     * restaurantes y 0..* atracciones (esta ultima siempre se cumple).
     *
     * @return true si la zona es valida segun el UML.
     */
    public boolean cumpleMultiplicidades( )
    {
        return !restaurantes.isEmpty( );
    }

    /**
     * Suma el dinero que la zona genera por concepto de renta de espacios de
     * las atracciones y los restaurantes que estan en ella.
     *
     * <p>
     * El recorrido se hace sobre {@link Aliado} para aprovechar el
     * polimorfismo: no importa si el elemento es una atraccion o un
     * restaurante, ambos responden a <code>getCostoRentaEspacio()</code>.
     * </p>
     *
     * @return total recaudado por rentas en la zona.
     */
    public double calcularIngresosPorRentas( )
    {
        double total = 0;
        for( Aliado aliado : atracciones )
        {
            total += aliado.getCostoRentaEspacio( );
        }
        for( Aliado aliado : restaurantes )
        {
            total += aliado.getCostoRentaEspacio( );
        }
        return total;
    }

    @Override
    public String toString( )
    {
        return "Zona " + nombre;
    }
}
