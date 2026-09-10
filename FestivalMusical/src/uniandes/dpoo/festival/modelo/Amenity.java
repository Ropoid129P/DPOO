package uniandes.dpoo.festival.modelo;

/**
 * Servicio o comodidad disponible dentro de una {@link Zona} del festival:
 * banos, puntos de hidratacion, enfermeria, lockers, etc.
 *
 * <p>
 * Es una clase simple, sin herencia, tal como aparece aislada en el extremo
 * derecho del diagrama UML.
 * </p>
 */
public class Amenity
{
    // -------------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------------

    /** Descripcion del servicio. UML: -descripcion:String */
    private String descripcion;

    /** Ubicacion fisica del servicio dentro de la zona. UML: -ubicacion:String */
    private String ubicacion;

    // -------------------------------------------------------------------
    // Constructores
    // -------------------------------------------------------------------

    /**
     * Construye un amenity.
     *
     * @param descripcion descripcion del servicio.
     * @param ubicacion   ubicacion dentro de la zona.
     */
    public Amenity( String descripcion, String ubicacion )
    {
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
    }

    // -------------------------------------------------------------------
    // Metodos
    // -------------------------------------------------------------------

    public String getDescripcion( )
    {
        return descripcion;
    }

    public void setDescripcion( String descripcion )
    {
        this.descripcion = descripcion;
    }

    public String getUbicacion( )
    {
        return ubicacion;
    }

    public void setUbicacion( String ubicacion )
    {
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString( )
    {
        return descripcion + " (" + ubicacion + ")";
    }
}
