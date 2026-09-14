package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

/**
 * Esta clase se utiliza para calcular las tarifas en temporada alta.
 *
 * En temporada alta todos los clientes pagan el mismo costo por kilómetro y ninguno recibe descuentos.
 */
public class CalculadoraTarifasTemporadaAlta extends CalculadoraTarifas
{
    /**
     * El costo por kilómetro en temporada alta
     */
    protected final int COSTO_POR_KM = 1000;

    @Override
    public int calcularCostoBase( Vuelo vuelo, Cliente cliente )
    {
        int distancia = calcularDistanciaVuelo( vuelo.getRuta( ) );
        return distancia * COSTO_POR_KM;
    }

    @Override
    public double calcularPorcentajeDescuento( Cliente cliente )
    {
        // En temporada alta no hay descuentos para ningún tipo de cliente
        return 0;
    }
}
