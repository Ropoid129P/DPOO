package uniandes.dpoo.festival.modelo;

/**
 * Aliado concreto que corresponde a un emprendimiento (por ejemplo artesanias,
 * ropa, accesorios o merchandising) instalado dentro del festival.
 *
 * <p>
 * En el UML es una de las tres especializaciones de {@link Aliado} y agrega un
 * unico atributo propio: el tipo de emprendimiento.
 * </p>
 */
public class Emprendimiento extends Aliado
{
    // -------------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------------

    /** Categoria del emprendimiento. UML: -tipoEmprendimiento:String */
    private String tipoEmprendimiento;

    // -------------------------------------------------------------------
    // Constructores
    // -------------------------------------------------------------------

    /**
     * Construye un emprendimiento.
     *
     * @param nombre             nombre comercial.
     * @param costoRentaEspacio  valor pagado por la renta del espacio.
     * @param representanteLegal representante legal.
     * @param telefonoContacto   telefono de contacto.
     * @param emailContacto      correo de contacto.
     * @param tipoEmprendimiento categoria del emprendimiento.
     */
    public Emprendimiento( String nombre, double costoRentaEspacio, String representanteLegal, String telefonoContacto, String emailContacto,
            String tipoEmprendimiento )
    {
        super( nombre, costoRentaEspacio, representanteLegal, telefonoContacto, emailContacto );
        this.tipoEmprendimiento = tipoEmprendimiento;
    }

    // -------------------------------------------------------------------
    // Metodos
    // -------------------------------------------------------------------

    public String getTipoEmprendimiento( )
    {
        return tipoEmprendimiento;
    }

    public void setTipoEmprendimiento( String tipoEmprendimiento )
    {
        this.tipoEmprendimiento = tipoEmprendimiento;
    }

    @Override
    public String getTipoAliado( )
    {
        return "Emprendimiento de " + tipoEmprendimiento;
    }
}
