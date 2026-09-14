package uniandes.dpoo.aerolinea.modelo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import uniandes.dpoo.aerolinea.exceptions.VueloSobrevendidoException;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifas;
import uniandes.dpoo.aerolinea.tiquetes.GeneradorTiquetes;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

/**
 * Esta clase tiene la información de un vuelo particular que cubre una ruta y se lleva a cabo en una cierta fecha.
 *
 * Los vuelos son las unidades a las cuales están asociadas las ventas de tiquetes
 */
public class Vuelo
{
    /**
     * La ruta que cubre el vuelo
     */
    private Ruta ruta;

    /**
     * La fecha para el vuelo, expresada como una cadena de la forma YYYY-MM-DD
     */
    private String fecha;

    /**
     * El avión utilizado para realizar el vuelo
     */
    private Avion avion;

    /**
     * Los tiquetes que ya fueron vendidos para el vuelo.
     *
     * Las llaves del mapa son los códigos de los tiquetes.
     */
    private Map<String, Tiquete> tiquetes;

    /**
     * Crea un nuevo vuelo con los parámetros dados.
     *
     * El constructor inicializa también el mapa de tiquetes vendidos, pero lo deja vacío
     * @param ruta La ruta que cubrirá el vuelo
     * @param fecha La fecha para el vuelo, expresada como una cadena de la forma YYYY-MM-DD
     * @param avion El avion que realizará el vuelo
     */
    public Vuelo( Ruta ruta, String fecha, Avion avion )
    {
        this.ruta = ruta;
        this.fecha = fecha;
        this.avion = avion;
        this.tiquetes = new HashMap<String, Tiquete>( );
    }

    public Ruta getRuta( )
    {
        return ruta;
    }

    public String getFecha( )
    {
        return fecha;
    }

    public Avion getAvion( )
    {
        return avion;
    }

    public Collection<Tiquete> getTiquetes( )
    {
        return tiquetes.values( );
    }

    /**
     * Registra un tiquete dentro del mapa de tiquetes del vuelo.
     *
     * Este método lo usa la clase Tiquete cuando se construye un tiquete nuevo, para que el tiquete quede asociado al vuelo tanto si se vendió durante la ejecución como si
     * se cargó desde un archivo.
     * @param tiquete El tiquete que se quiere asociar al vuelo
     */
    public void agregarTiquete( Tiquete tiquete )
    {
        this.tiquetes.put( tiquete.getCodigo( ), tiquete );
    }

    /**
     * Vende una determinada cantidad de tiquetes para el vuelo y los deja registrados en el mapa de tiquetes
     * @param cliente El cliente al cual se le venden los tiquetes
     * @param calculadora La calculadora de tarifas que debe usarse para saber el precio por tiquete
     * @param cantidad La cantidad de tiquetes que se quieren comprar
     * @return El valor total de los tiquetes vendidos
     * @throws VueloSobrevendidoException Se lanza esta excepción si no hay suficiente espacio en el vuelo para todos los pasajeros
     */
    public int venderTiquetes( Cliente cliente, CalculadoraTarifas calculadora, int cantidad ) throws VueloSobrevendidoException
    {
        int sillasDisponibles = avion.getCapacidad( ) - tiquetes.size( );
        if( cantidad > sillasDisponibles )
            throw new VueloSobrevendidoException( this );

        int tarifa = calculadora.calcularTarifa( this, cliente );

        for( int i = 0; i < cantidad; i++ )
        {
            Tiquete nuevoTiquete = GeneradorTiquetes.generarTiquete( this, cliente, tarifa );
            GeneradorTiquetes.registrarTiquete( nuevoTiquete );
        }

        return tarifa * cantidad;
    }

    @Override
    public boolean equals( Object obj )
    {
        if( this == obj )
            return true;
        if( !( obj instanceof Vuelo ) )
            return false;

        Vuelo otroVuelo = ( Vuelo )obj;
        return this.fecha.equals( otroVuelo.getFecha( ) ) && this.ruta.getCodigoRuta( ).equals( otroVuelo.getRuta( ).getCodigoRuta( ) );
    }

    @Override
    public int hashCode( )
    {
        return ( fecha + "-" + ruta.getCodigoRuta( ) ).hashCode( );
    }

    @Override
    public String toString( )
    {
        return "Vuelo " + ruta.getCodigoRuta( ) + " del " + fecha;
    }
}
