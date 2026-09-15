import java.io.PrintWriter;

import uniandes.dpoo.aerolinea.modelo.Aerolinea;
import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteNatural;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifas;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifasTemporadaAlta;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifasTemporadaBaja;
import uniandes.dpoo.aerolinea.persistencia.CentralPersistencia;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public class PruebaTaller3
{
    private static int fallas = 0;

    private static void check( String nombre, boolean condicion, Object obtenido )
    {
        if( condicion )
            System.out.println( "  OK   " + nombre + "  ->  " + obtenido );
        else
        {
            System.out.println( "  FALLA " + nombre + "  ->  " + obtenido );
            fallas++;
        }
    }

    public static void main( String[] args ) throws Exception
    {
        // ---------- Ruta.getDuracion ----------
        Aeropuerto bog = new Aeropuerto( "El Dorado", "BOG", "Bogota", 4.7016, -74.1469 );
        Aeropuerto mde = new Aeropuerto( "Jose Maria Cordova", "MDE", "Medellin", 6.1645, -75.4231 );
        Aeropuerto nrt = new Aeropuerto( "Narita", "NRT", "Tokyo", 35.7647, 140.3863 );

        Ruta r1 = new Ruta( bog, mde, "715", "815", "100" );
        Ruta r2 = new Ruta( bog, mde, "2330", "35", "101" );
        check( "getDuracion normal (7:15 -> 8:15)", r1.getDuracion( ) == 60, r1.getDuracion( ) );
        check( "getDuracion cruzando medianoche (23:30 -> 0:35)", r2.getDuracion( ) == 65, r2.getDuracion( ) );
        check( "getHoras('715')", Ruta.getHoras( "715" ) == 7, Ruta.getHoras( "715" ) );
        check( "getMinutos('715')", Ruta.getMinutos( "715" ) == 15, Ruta.getMinutos( "715" ) );

        // ---------- Aeropuerto duplicado ----------
        boolean lanzo = false;
        try
        {
            new Aeropuerto( "Otro", "BOG", "Bogota", 1, 1 );
        }
        catch( Exception e )
        {
            lanzo = true;
        }
        check( "Aeropuerto duplicado lanza excepcion", lanzo, lanzo );

        int distancia = Aeropuerto.calcularDistancia( bog, mde );
        check( "Distancia BOG-MDE razonable (200-260 km)", distancia > 200 && distancia < 260, distancia + " km" );
        check( "Distancia BOG-NRT razonable", Aeropuerto.calcularDistancia( bog, nrt ) > 10000, Aeropuerto.calcularDistancia( bog, nrt ) + " km" );

        // ---------- Tarifas ----------
        Aerolinea aerolinea = new Aerolinea( );
        aerolinea.agregarRuta( r1 );
        aerolinea.agregarAvion( new uniandes.dpoo.aerolinea.modelo.Avion( "Avion1", 3 ) );
        aerolinea.programarVuelo( "2024-03-10", "100", "Avion1" );

        ClienteNatural alice = new ClienteNatural( "Alice" );
        ClienteCorporativo apple = new ClienteCorporativo( "Apple", ClienteCorporativo.PEQUENA );
        ClienteCorporativo boeing = new ClienteCorporativo( "Boeing", ClienteCorporativo.MEDIANA );
        ClienteCorporativo shell = new ClienteCorporativo( "Shell", ClienteCorporativo.GRANDE );
        aerolinea.agregarCliente( alice );
        aerolinea.agregarCliente( apple );
        aerolinea.agregarCliente( boeing );
        aerolinea.agregarCliente( shell );

        CalculadoraTarifas baja = new CalculadoraTarifasTemporadaBaja( );
        CalculadoraTarifas alta = new CalculadoraTarifasTemporadaAlta( );
        uniandes.dpoo.aerolinea.modelo.Vuelo vuelo = aerolinea.getVuelo( "100", "2024-03-10" );

        int esperadoNaturalBaja = ( int ) ( distancia * 600 * ( 1 - 0 ) );
        esperadoNaturalBaja = esperadoNaturalBaja + ( int ) ( esperadoNaturalBaja * CalculadoraTarifas.IMPUESTO );
        check( "Tarifa natural temporada baja", baja.calcularTarifa( vuelo, alice ) == esperadoNaturalBaja, baja.calcularTarifa( vuelo, alice ) );

        int baseCorp = distancia * 900;
        int conDesc = ( int ) ( baseCorp * ( 1 - 0.2 ) );
        int esperadoGrandeBaja = conDesc + ( int ) ( conDesc * CalculadoraTarifas.IMPUESTO );
        check( "Tarifa corporativo GRANDE temporada baja", baja.calcularTarifa( vuelo, shell ) == esperadoGrandeBaja, baja.calcularTarifa( vuelo, shell ) );

        check( "Descuento pequena < mediana < grande",
                baja.calcularTarifa( vuelo, apple ) > baja.calcularTarifa( vuelo, boeing ) && baja.calcularTarifa( vuelo, boeing ) > baja.calcularTarifa( vuelo, shell ),
                baja.calcularTarifa( vuelo, apple ) + " > " + baja.calcularTarifa( vuelo, boeing ) + " > " + baja.calcularTarifa( vuelo, shell ) );

        int baseAlta = distancia * 1000;
        int esperadoAlta = baseAlta + ( int ) ( baseAlta * CalculadoraTarifas.IMPUESTO );
        check( "Tarifa temporada alta igual para todos", alta.calcularTarifa( vuelo, alice ) == esperadoAlta && alta.calcularTarifa( vuelo, shell ) == esperadoAlta,
                alta.calcularTarifa( vuelo, alice ) );

        // ---------- Venta de tiquetes ----------
        int total = aerolinea.venderTiquetes( "Alice", "2024-03-10", "100", 2 );
        check( "venderTiquetes retorna el total", total == 2 * esperadoNaturalBaja, total );
        check( "El vuelo tiene 2 tiquetes", vuelo.getTiquetes( ).size( ) == 2, vuelo.getTiquetes( ).size( ) );
        check( "La aerolinea reporta 2 tiquetes", aerolinea.getTiquetes( ).size( ) == 2, aerolinea.getTiquetes( ).size( ) );
        check( "Saldo pendiente de Alice", aerolinea.consultarSaldoPendienteCliente( "Alice" ).equals( "" + total ), aerolinea.consultarSaldoPendienteCliente( "Alice" ) );

        boolean sobrevendido = false;
        try
        {
            aerolinea.venderTiquetes( "Apple", "2024-03-10", "100", 5 );
        }
        catch( uniandes.dpoo.aerolinea.exceptions.VueloSobrevendidoException e )
        {
            sobrevendido = true;
        }
        check( "Vuelo sobrevendido lanza VueloSobrevendidoException", sobrevendido, sobrevendido );

        // Codigos de tiquete unicos y de 7 digitos
        boolean codigosOk = true;
        for( Tiquete t : aerolinea.getTiquetes( ) )
        {
            if( t.getCodigo( ).length( ) < 7 || !uniandes.dpoo.aerolinea.tiquetes.GeneradorTiquetes.validarTiquete( t.getCodigo( ) ) )
                codigosOk = false;
        }
        check( "Codigos de tiquete registrados en el generador", codigosOk, codigosOk );

        // ---------- Vuelo realizado ----------
        aerolinea.registrarVueloRealizado( "2024-03-10", "100" );
        boolean todosUsados = true;
        for( Tiquete t : aerolinea.getTiquetes( ) )
            if( !t.esUsado( ) )
                todosUsados = false;
        check( "Tiquetes marcados como usados", todosUsados, todosUsados );
        check( "Saldo pendiente de Alice queda en 0", aerolinea.consultarSaldoPendienteCliente( "Alice" ).equals( "0" ), aerolinea.consultarSaldoPendienteCliente( "Alice" ) );

        // ---------- Avion ocupado ----------
        Ruta r3 = new Ruta( mde, bog, "745", "845", "102" );
        aerolinea.agregarRuta( r3 );
        boolean ocupado = false;
        try
        {
            aerolinea.programarVuelo( "2024-03-10", "102", "Avion1" );
        }
        catch( Exception e )
        {
            ocupado = true;
        }
        check( "No se puede usar un avion ocupado en el mismo horario", ocupado, ocupado );

        Ruta r4 = new Ruta( mde, bog, "1200", "1300", "103" );
        aerolinea.agregarRuta( r4 );
        aerolinea.programarVuelo( "2024-03-10", "103", "Avion1" );
        check( "Si se puede usar el avion en otro horario", aerolinea.getVuelos( ).size( ) == 2, aerolinea.getVuelos( ).size( ) );

        // ---------- Persistencia de la aerolinea ----------
        new java.io.File( "./datos" ).mkdirs( );
        aerolinea.salvarAerolinea( "./datos/aerolinea_salvada.json", CentralPersistencia.JSON );
        check( "Se creo el archivo de la aerolinea", new java.io.File( "./datos/aerolinea_salvada.json" ).exists( ), true );

        boolean tipoInvalido = false;
        try
        {
            aerolinea.salvarAerolinea( "./datos/x.json", "XML" );
        }
        catch( uniandes.dpoo.aerolinea.persistencia.TipoInvalidoException e )
        {
            tipoInvalido = true;
        }
        check( "Tipo de archivo invalido lanza TipoInvalidoException", tipoInvalido, tipoInvalido );

        // ---------- Carga completa desde archivo ----------
        String jsonAerolinea = "{\n" + "  \"aviones\": [ {\"nombre\": \"Boeing737\", \"capacidad\": 150} ],\n"
                + "  \"aeropuertos\": [ {\"nombre\": \"Alfonso Bonilla\", \"codigo\": \"CLO\", \"nombreCiudad\": \"Cali\", \"latitud\": 3.5432, \"longitud\": -76.3816},\n"
                + "                     {\"nombre\": \"Rafael Nunez\", \"codigo\": \"CTG\", \"nombreCiudad\": \"Cartagena\", \"latitud\": 10.4424, \"longitud\": -75.513} ],\n"
                + "  \"rutas\": [ {\"codigoRuta\": \"4558\", \"horaSalida\": \"900\", \"horaLlegada\": \"1030\", \"origen\": \"CLO\", \"destino\": \"CTG\"} ],\n"
                + "  \"vuelos\": [ {\"fecha\": \"2024-11-05\", \"codigoRuta\": \"4558\", \"avion\": \"Boeing737\"} ]\n" + "}\n";
        PrintWriter pw = new PrintWriter( "./datos/aerolinea_prueba.json" );
        pw.print( jsonAerolinea );
        pw.close( );

        Aerolinea otra = new Aerolinea( );
        otra.cargarAerolinea( "./datos/aerolinea_prueba.json", CentralPersistencia.JSON );
        check( "Cargo 1 avion", otra.getAviones( ).size( ) == 1, otra.getAviones( ).size( ) );
        check( "Cargo 1 ruta", otra.getRutas( ).size( ) == 1, otra.getRutas( ).size( ) );
        check( "Cargo 1 vuelo", otra.getVuelos( ).size( ) == 1, otra.getVuelos( ).size( ) );
        check( "El vuelo cargado se encuentra", otra.getVuelo( "4558", "2024-11-05" ) != null, otra.getVuelo( "4558", "2024-11-05" ) );

        // ---------- Carga de tiquetes (archivo original del esqueleto) ----------
        otra.cargarTiquetes( "./datos/tiquetes.json", CentralPersistencia.JSON );
        check( "Cargo 4 clientes", otra.getClientes( ).size( ) == 4, otra.getClientes( ).size( ) );
        check( "Cargo 1 tiquete y quedo asociado al vuelo", otra.getTiquetes( ).size( ) == 1, otra.getTiquetes( ).size( ) );
        check( "El tiquete cargado quedo en el cliente Bob", otra.consultarSaldoPendienteCliente( "Bob" ).equals( "450000" ), otra.consultarSaldoPendienteCliente( "Bob" ) );

        System.out.println( "\n==================================" );
        System.out.println( fallas == 0 ? "TODAS LAS PRUEBAS PASARON" : ( fallas + " PRUEBAS FALLARON" ) );
        System.out.println( "==================================" );
    }
}
