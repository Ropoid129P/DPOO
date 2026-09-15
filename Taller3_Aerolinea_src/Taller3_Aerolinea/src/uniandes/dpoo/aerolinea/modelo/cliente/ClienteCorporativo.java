package uniandes.dpoo.aerolinea.modelo.cliente;

import org.json.JSONObject;

/**
 * Esta clase se usa para representar a los clientes de la aerolínea que son empresas
 */
public class ClienteCorporativo extends Cliente
{
    /**
     * La constante usada para identificar el tipo de cliente
     */
    public static final String CORPORATIVO = "Corporativo";

    /**
     * La constante que identifica a las empresas grandes
     */
    public static final int GRANDE = 1;

    /**
     * La constante que identifica a las empresas medianas
     */
    public static final int MEDIANA = 2;

    /**
     * La constante que identifica a las empresas pequeñas
     */
    public static final int PEQUENA = 3;

    /**
     * El nombre de la empresa
     */
    private String nombreEmpresa;

    /**
     * El tamaño de la empresa: debe ser GRANDE, MEDIANA o PEQUENA
     */
    private int tamanoEmpresa;

    /**
     * Construye un nuevo cliente corporativo con el nombre y el tamaño dados
     * @param nombreEmpresa El nombre de la empresa
     * @param tamano El tamaño de la empresa (GRANDE, MEDIANA o PEQUENA)
     */
    public ClienteCorporativo( String nombreEmpresa, int tamano )
    {
        super( );
        this.nombreEmpresa = nombreEmpresa;
        this.tamanoEmpresa = tamano;
    }

    public String getNombreEmpresa( )
    {
        return nombreEmpresa;
    }

    public int getTamanoEmpresa( )
    {
        return tamanoEmpresa;
    }

    @Override
    public String getTipoCliente( )
    {
        return CORPORATIVO;
    }

    @Override
    public String getIdentificador( )
    {
        return nombreEmpresa;
    }

    /**
     * Crea un nuevo objeto de tipo a partir de un objeto JSON.
     *
     * El objeto JSON debe tener dos atributos: nombreEmpresa (una cadena) y tamanoEmpresa (un número).
     * @param cliente El objeto JSON que contiene la información
     * @return El nuevo objeto inicializado con la información
     */
    public static ClienteCorporativo cargarDesdeJSON( JSONObject cliente )
    {
        String nombreEmpresa = cliente.getString( "nombreEmpresa" );
        int tam = cliente.getInt( "tamanoEmpresa" );
        return new ClienteCorporativo( nombreEmpresa, tam );
    }

    /**
     * Salva este objeto de tipo ClienteCorporativo dentro de un objeto JSONObject para que ese objeto se almacene en un archivo
     * @return El objeto JSON con toda la información del cliente corporativo
     */
    public JSONObject salvarEnJSON( )
    {
        JSONObject jobject = new JSONObject( );
        jobject.put( "nombreEmpresa", this.nombreEmpresa );
        jobject.put( "tamanoEmpresa", this.tamanoEmpresa );
        jobject.put( "tipo", CORPORATIVO );
        return jobject;
    }
}
