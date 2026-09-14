package uniandes.dpoo.aerolinea.modelo.cliente;

import java.util.LinkedList;
import java.util.List;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

/**
 * Esta clase abstracta define e implementa los aspectos que son comunes para todos los tipos de clientes de la Aerolínea
 *
 * Cada cliente, sin importar su tipo, tiene una lista de tiquetes usados y sin usar.
 */
public abstract class Cliente
{
    /**
     * La lista de tiquetes sin usar del cliente
     */
    private List<Tiquete> tiquetesSinUsar;

    /**
     * La lista de tiquetes usados del cliente
     */
    private List<Tiquete> tiquetesUsados;

    /**
     * Inicializa las listas de tiquetes del cliente.
     */
    public Cliente( )
    {
        this.tiquetesSinUsar = new LinkedList<Tiquete>( );
        this.tiquetesUsados = new LinkedList<Tiquete>( );
    }

    /**
     * Retorna el tipo del cliente.
     * @return Una cadena que identifica al tipo de cliente
     */
    public abstract String getTipoCliente( );

    /**
     * Retorna el identificador del cliente
     * @return Identificador del cliente que debería ser único
     */
    public abstract String getIdentificador( );

    /**
     * Agrega un nuevo tiquete a la lista de tiquetes (sin usar) que ha comprado el cliente
     * @param tiquete El nuevo tiquete que se va a agregar
     */
    public void agregarTiquete( Tiquete tiquete )
    {
        this.tiquetesSinUsar.add( tiquete );
    }

    /**
     * Calcula el valor total de los tiquetes que ha comprado un cliente y que todavía no ha usado.
     *
     * Este es el valor que la aerolínea usa como 'saldo pendiente' del cliente.
     * @return La suma de las tarifas de los tiquetes sin usar
     */
    public int calcularValorTotalTiquetes( )
    {
        int total = 0;
        for( Tiquete tiquete : tiquetesSinUsar )
            total = total + tiquete.getTarifa( );

        return total;
    }

    /**
     * Marca como usados todos los tiquetes del cliente que se hayan realizado en el vuelo que llega por parámetro, moviéndolos de la lista de tiquetes sin usar a la lista de
     * tiquetes usados
     * @param vuelo El vuelo del que se usaron los tiquetes
     */
    public void usarTiquetes( Vuelo vuelo )
    {
        List<Tiquete> tiquetesDelVuelo = new LinkedList<Tiquete>( );

        for( Tiquete tiquete : tiquetesSinUsar )
        {
            if( tiquete.getVuelo( ).equals( vuelo ) )
                tiquetesDelVuelo.add( tiquete );
        }

        for( Tiquete tiquete : tiquetesDelVuelo )
        {
            tiquete.marcarComoUsado( );
            tiquetesSinUsar.remove( tiquete );
            tiquetesUsados.add( tiquete );
        }
    }
}
