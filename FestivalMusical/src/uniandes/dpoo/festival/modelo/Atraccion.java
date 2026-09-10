package uniandes.dpoo.festival.modelo;

/**
 * Aliado concreto que corresponde a una atraccion del festival (por ejemplo una
 * rueda, un simulador o una zona de juegos).
 *
 * <p>
 * El nombre de la clase en el UML aparece con tilde ("Atraccion"), pero en Java
 * se escribe sin tilde para que el nombre del archivo y el identificador sean
 * portables entre sistemas operativos y codificaciones.
 * </p>
 *
 * <p>
 * Ademas de lo heredado de {@link Aliado}, una atraccion debe declarar la
 * aseguradora que respalda su operacion.
 * </p>
 */
public class Atraccion extends Aliado
{
    // -------------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------------

    /** Compania aseguradora que cubre la atraccion. UML: -aseguradora:String */
    private String aseguradora;

    // -------------------------------------------------------------------
    // Constructores
    // -------------------------------------------------------------------

    /**
     * Construye una atraccion.
     *
     * @param nombre             nombre de la atraccion.
     * @param costoRentaEspacio  valor pagado por la renta del espacio.
     * @param representanteLegal representante legal.
     * @param telefonoContacto   telefono de contacto.
     * @param emailContacto      correo de contacto.
     * @param aseguradora        aseguradora que respalda la atraccion.
     */
    public Atraccion( String nombre, double costoRentaEspacio, String representanteLegal, String telefonoContacto, String emailContacto,
            String aseguradora )
    {
        super( nombre, costoRentaEspacio, representanteLegal, telefonoContacto, emailContacto );
        this.aseguradora = aseguradora;
    }

    // -------------------------------------------------------------------
    // Metodos
    // -------------------------------------------------------------------

    public String getAseguradora( )
    {
        return aseguradora;
    }

    public void setAseguradora( String aseguradora )
    {
        this.aseguradora = aseguradora;
    }

    @Override
    public String getTipoAliado( )
    {
        return "Atraccion";
    }
}
