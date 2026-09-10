package uniandes.dpoo.festival.modelo;

/**
 * Aliado concreto que vende alimentos dentro del festival.
 *
 * <p>
 * Adicional a lo heredado de {@link Aliado}, un restaurante requiere un permiso
 * sanitario de funcionamiento.
 * </p>
 *
 * <p>
 * Esta clase es tambien el destino de dos asociaciones del UML: cada
 * {@link Zona} tiene 1..* restaurantes, y cada {@link Artista} tiene 1..*
 * restaurantes como proveedores de alimentacion.
 * </p>
 */
public class Restaurante extends Aliado
{
    // -------------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------------

    /** Numero del permiso sanitario. UML: -permisoFuncionamiento:String */
    private String permisoFuncionamiento;

    // -------------------------------------------------------------------
    // Constructores
    // -------------------------------------------------------------------

    /**
     * Construye un restaurante.
     *
     * @param nombre                nombre del restaurante.
     * @param costoRentaEspacio     valor pagado por la renta del espacio.
     * @param representanteLegal    representante legal.
     * @param telefonoContacto      telefono de contacto.
     * @param emailContacto         correo de contacto.
     * @param permisoFuncionamiento numero del permiso de funcionamiento.
     */
    public Restaurante( String nombre, double costoRentaEspacio, String representanteLegal, String telefonoContacto, String emailContacto,
            String permisoFuncionamiento )
    {
        super( nombre, costoRentaEspacio, representanteLegal, telefonoContacto, emailContacto );
        this.permisoFuncionamiento = permisoFuncionamiento;
    }

    // -------------------------------------------------------------------
    // Metodos
    // -------------------------------------------------------------------

    public String getPermisoFuncionamiento( )
    {
        return permisoFuncionamiento;
    }

    public void setPermisoFuncionamiento( String permisoFuncionamiento )
    {
        this.permisoFuncionamiento = permisoFuncionamiento;
    }

    @Override
    public String getTipoAliado( )
    {
        return "Restaurante";
    }
}
