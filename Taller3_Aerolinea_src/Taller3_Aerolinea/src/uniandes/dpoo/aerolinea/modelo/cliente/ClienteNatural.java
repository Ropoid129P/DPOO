package uniandes.dpoo.aerolinea.modelo.cliente;

/**
 * Esta clase se usa para representar a los clientes de la aerolínea que son personas naturales
 */
public class ClienteNatural extends Cliente
{
    /**
     * La constante usada para identificar el tipo de cliente
     */
    public static final String NATURAL = "Natural";

    /**
     * El nombre del cliente
     */
    private String nombre;

    /**
     * Construye un nuevo cliente natural dado su nombre
     * @param nombre
     */
    public ClienteNatural( String nombre )
    {
        super( );
        this.nombre = nombre;
    }

    @Override
    public String getIdentificador( )
    {
        return nombre;
    }

    @Override
    public String getTipoCliente( )
    {
        return NATURAL;
    }
}
