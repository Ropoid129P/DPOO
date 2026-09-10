package uniandes.dpoo.festival.modelo;

/**
 * Clase abstracta que representa a un aliado comercial del festival.
 *
 * <p>
 * Un aliado es un participante que <b>renta un espacio</b> dentro del festival
 * para operar su negocio. En el UML hereda de {@link Participante} y a su vez
 * es la superclase de {@link Emprendimiento}, {@link Atraccion} y
 * {@link Restaurante}.
 * </p>
 *
 * <p>
 * Es abstracta (nombre en cursiva en el UML) porque siempre se instancia uno de
 * sus tres tipos concretos.
 * </p>
 */
public abstract class Aliado extends Participante
{
    // -------------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------------

    /** Valor que el aliado paga por rentar su espacio. UML: #costoRentaEspacio:double */
    protected double costoRentaEspacio;

    /** Nombre del representante legal del aliado. UML: #representanteLegal:String */
    protected String representanteLegal;

    /** Telefono de contacto del aliado. UML: #telefonoContacto:String */
    protected String telefonoContacto;

    /** Correo electronico de contacto del aliado. UML: #emailContacto:String */
    protected String emailContacto;

    // -------------------------------------------------------------------
    // Constructores
    // -------------------------------------------------------------------

    /**
     * Construye un aliado con toda su informacion comercial y de contacto.
     *
     * <p>
     * El constructor llama primero a <code>super(nombre)</code> porque el
     * nombre pertenece a la superclase {@link Participante}; solo despues
     * inicializa los atributos propios de este nivel de la jerarquia.
     * </p>
     *
     * @param nombre             nombre del aliado.
     * @param costoRentaEspacio  valor pagado por la renta del espacio.
     * @param representanteLegal representante legal.
     * @param telefonoContacto   telefono de contacto.
     * @param emailContacto      correo de contacto.
     */
    public Aliado( String nombre, double costoRentaEspacio, String representanteLegal, String telefonoContacto, String emailContacto )
    {
        super( nombre );
        this.costoRentaEspacio = costoRentaEspacio;
        this.representanteLegal = representanteLegal;
        this.telefonoContacto = telefonoContacto;
        this.emailContacto = emailContacto;
    }

    // -------------------------------------------------------------------
    // Metodos
    // -------------------------------------------------------------------

    public double getCostoRentaEspacio( )
    {
        return costoRentaEspacio;
    }

    public void setCostoRentaEspacio( double costoRentaEspacio )
    {
        this.costoRentaEspacio = costoRentaEspacio;
    }

    public String getRepresentanteLegal( )
    {
        return representanteLegal;
    }

    public void setRepresentanteLegal( String representanteLegal )
    {
        this.representanteLegal = representanteLegal;
    }

    public String getTelefonoContacto( )
    {
        return telefonoContacto;
    }

    public void setTelefonoContacto( String telefonoContacto )
    {
        this.telefonoContacto = telefonoContacto;
    }

    public String getEmailContacto( )
    {
        return emailContacto;
    }

    public void setEmailContacto( String emailContacto )
    {
        this.emailContacto = emailContacto;
    }

    /**
     * Para un aliado, el valor economico es un <b>ingreso</b> para el festival:
     * lo que paga por rentar el espacio. Por eso se devuelve positivo.
     *
     * @return el costo de renta del espacio.
     */
    @Override
    public double calcularValorEconomico( )
    {
        return costoRentaEspacio;
    }

    /**
     * Devuelve el tipo de aliado en texto. Cada subclase lo responde de forma
     * distinta, lo que permite recorrer una lista de aliados de manera
     * polimorfica sin usar <code>instanceof</code>.
     *
     * @return descripcion corta del tipo de aliado.
     */
    public abstract String getTipoAliado( );

    @Override
    public String toString( )
    {
        return nombre + " (" + getTipoAliado( ) + ")";
    }
}
