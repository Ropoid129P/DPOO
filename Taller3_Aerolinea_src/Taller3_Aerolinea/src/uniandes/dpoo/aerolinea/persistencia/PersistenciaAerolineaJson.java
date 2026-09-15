package uniandes.dpoo.aerolinea.persistencia;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import uniandes.dpoo.aerolinea.exceptions.AeropuertoDuplicadoException;
import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;
import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Avion;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;

/**
 * Esta clase se encarga de cargar y salvar, usando archivos JSON, la información de los aviones, aeropuertos, rutas y vuelos de una aerolínea.
 *
 * El formato del archivo es el siguiente:
 *
 * <pre>
 * {
 *   "aviones": [ { "nombre": "...", "capacidad": 0 } ],
 *   "aeropuertos": [ { "nombre": "...", "codigo": "...", "nombreCiudad": "...", "latitud": 0.0, "longitud": 0.0 } ],
 *   "rutas": [ { "codigoRuta": "...", "horaSalida": "...", "horaLlegada": "...", "origen": "&lt;codigo&gt;", "destino": "&lt;codigo&gt;" } ],
 *   "vuelos": [ { "fecha": "YYYY-MM-DD", "codigoRuta": "...", "avion": "&lt;nombre&gt;" } ]
 * }
 * </pre>
 */
public class PersistenciaAerolineaJson implements IPersistenciaAerolinea
{
    private static final String AVIONES = "aviones";
    private static final String AEROPUERTOS = "aeropuertos";
    private static final String RUTAS = "rutas";
    private static final String VUELOS = "vuelos";

    private static final String NOMBRE = "nombre";
    private static final String CAPACIDAD = "capacidad";
    private static final String CODIGO = "codigo";
    private static final String NOMBRE_CIUDAD = "nombreCiudad";
    private static final String LATITUD = "latitud";
    private static final String LONGITUD = "longitud";
    private static final String CODIGO_RUTA = "codigoRuta";
    private static final String HORA_SALIDA = "horaSalida";
    private static final String HORA_LLEGADA = "horaLlegada";
    private static final String ORIGEN = "origen";
    private static final String DESTINO = "destino";
    private static final String FECHA = "fecha";
    private static final String AVION = "avion";

    @Override
    public void cargarAerolinea( String archivo, Aerolinea aerolinea ) throws IOException, InformacionInconsistenteException
    {
        String jsonCompleto = new String( Files.readAllBytes( new File( archivo ).toPath( ) ) );
        JSONObject raiz = new JSONObject( jsonCompleto );

        cargarAviones( aerolinea, raiz.getJSONArray( AVIONES ) );
        Map<String, Aeropuerto> aeropuertos = cargarAeropuertos( raiz.getJSONArray( AEROPUERTOS ) );
        cargarRutas( aerolinea, raiz.getJSONArray( RUTAS ), aeropuertos );
        cargarVuelos( aerolinea, raiz.getJSONArray( VUELOS ) );
    }

    @Override
    public void salvarAerolinea( String archivo, Aerolinea aerolinea ) throws IOException
    {
        JSONObject jobject = new JSONObject( );

        salvarAviones( aerolinea, jobject );
        salvarAeropuertos( aerolinea, jobject );
        salvarRutas( aerolinea, jobject );
        salvarVuelos( aerolinea, jobject );

        PrintWriter pw = new PrintWriter( archivo );
        jobject.write( pw, 2, 0 );
        pw.close( );
    }

    // ************************************************************************************
    // Métodos para cargar la información
    // ************************************************************************************

    /**
     * Carga los aviones de la aerolínea a partir de un arreglo JSON
     * @param aerolinea La aerolínea donde deben quedar los aviones
     * @param jAviones El arreglo JSON con la información de los aviones
     */
    private void cargarAviones( Aerolinea aerolinea, JSONArray jAviones )
    {
        for( int i = 0; i < jAviones.length( ); i++ )
        {
            JSONObject jAvion = jAviones.getJSONObject( i );
            String nombre = jAvion.getString( NOMBRE );
            int capacidad = jAvion.getInt( CAPACIDAD );
            aerolinea.agregarAvion( new Avion( nombre, capacidad ) );
        }
    }

    /**
     * Construye los aeropuertos que se van a usar dentro de las rutas.
     *
     * Los aeropuertos no se almacenan directamente dentro de la aerolínea: sólo se puede llegar a ellos a través de las rutas.
     * @param jAeropuertos El arreglo JSON con la información de los aeropuertos
     * @return Un mapa donde las llaves son los códigos de los aeropuertos y los valores son los aeropuertos
     * @throws InformacionInconsistenteException Se lanza esta excepción si en el archivo hay dos aeropuertos con el mismo código
     */
    private Map<String, Aeropuerto> cargarAeropuertos( JSONArray jAeropuertos ) throws InformacionInconsistenteException
    {
        Map<String, Aeropuerto> aeropuertos = new HashMap<String, Aeropuerto>( );

        for( int i = 0; i < jAeropuertos.length( ); i++ )
        {
            JSONObject jAeropuerto = jAeropuertos.getJSONObject( i );
            String nombre = jAeropuerto.getString( NOMBRE );
            String codigo = jAeropuerto.getString( CODIGO );
            String nombreCiudad = jAeropuerto.getString( NOMBRE_CIUDAD );
            double latitud = jAeropuerto.getDouble( LATITUD );
            double longitud = jAeropuerto.getDouble( LONGITUD );

            try
            {
                Aeropuerto nuevoAeropuerto = new Aeropuerto( nombre, codigo, nombreCiudad, latitud, longitud );
                aeropuertos.put( codigo, nuevoAeropuerto );
            }
            catch( AeropuertoDuplicadoException e )
            {
                throw new InformacionInconsistenteException( e.getMessage( ) );
            }
        }

        return aeropuertos;
    }

    /**
     * Carga las rutas de la aerolínea a partir de un arreglo JSON
     * @param aerolinea La aerolínea donde deben quedar las rutas
     * @param jRutas El arreglo JSON con la información de las rutas
     * @param aeropuertos Los aeropuertos que ya fueron construidos, indexados por su código
     * @throws InformacionInconsistenteException Se lanza esta excepción si una ruta hace referencia a un aeropuerto que no existe
     */
    private void cargarRutas( Aerolinea aerolinea, JSONArray jRutas, Map<String, Aeropuerto> aeropuertos ) throws InformacionInconsistenteException
    {
        for( int i = 0; i < jRutas.length( ); i++ )
        {
            JSONObject jRuta = jRutas.getJSONObject( i );
            String codigoRuta = jRuta.getString( CODIGO_RUTA );
            String horaSalida = jRuta.getString( HORA_SALIDA );
            String horaLlegada = jRuta.getString( HORA_LLEGADA );
            String codigoOrigen = jRuta.getString( ORIGEN );
            String codigoDestino = jRuta.getString( DESTINO );

            Aeropuerto origen = aeropuertos.get( codigoOrigen );
            Aeropuerto destino = aeropuertos.get( codigoDestino );

            if( origen == null )
                throw new InformacionInconsistenteException( "No existe el aeropuerto de origen con código " + codigoOrigen );
            if( destino == null )
                throw new InformacionInconsistenteException( "No existe el aeropuerto de destino con código " + codigoDestino );

            aerolinea.agregarRuta( new Ruta( origen, destino, horaSalida, horaLlegada, codigoRuta ) );
        }
    }

    /**
     * Carga los vuelos programados de la aerolínea a partir de un arreglo JSON
     * @param aerolinea La aerolínea donde deben quedar los vuelos
     * @param jVuelos El arreglo JSON con la información de los vuelos
     * @throws InformacionInconsistenteException Se lanza esta excepción si un vuelo hace referencia a una ruta o a un avión que no existen, o si el avión ya estaba ocupado
     */
    private void cargarVuelos( Aerolinea aerolinea, JSONArray jVuelos ) throws InformacionInconsistenteException
    {
        for( int i = 0; i < jVuelos.length( ); i++ )
        {
            JSONObject jVuelo = jVuelos.getJSONObject( i );
            String fecha = jVuelo.getString( FECHA );
            String codigoRuta = jVuelo.getString( CODIGO_RUTA );
            String nombreAvion = jVuelo.getString( AVION );

            try
            {
                aerolinea.programarVuelo( fecha, codigoRuta, nombreAvion );
            }
            catch( Exception e )
            {
                throw new InformacionInconsistenteException( "No fue posible programar el vuelo de la ruta " + codigoRuta + " en la fecha " + fecha + ": " + e.getMessage( ) );
            }
        }
    }

    // ************************************************************************************
    // Métodos para salvar la información
    // ************************************************************************************

    /**
     * Salva la información de los aviones de la aerolínea dentro de la llave 'aviones'
     * @param aerolinea La aerolínea que tiene la información
     * @param jobject El objeto JSON donde debe quedar la información
     */
    private void salvarAviones( Aerolinea aerolinea, JSONObject jobject )
    {
        JSONArray jAviones = new JSONArray( );
        for( Avion avion : aerolinea.getAviones( ) )
        {
            JSONObject jAvion = new JSONObject( );
            jAvion.put( NOMBRE, avion.getNombre( ) );
            jAvion.put( CAPACIDAD, avion.getCapacidad( ) );
            jAviones.put( jAvion );
        }
        jobject.put( AVIONES, jAviones );
    }

    /**
     * Salva la información de los aeropuertos que aparecen en las rutas de la aerolínea, dentro de la llave 'aeropuertos'
     * @param aerolinea La aerolínea que tiene la información
     * @param jobject El objeto JSON donde debe quedar la información
     */
    private void salvarAeropuertos( Aerolinea aerolinea, JSONObject jobject )
    {
        Map<String, Aeropuerto> aeropuertos = new HashMap<String, Aeropuerto>( );
        for( Ruta ruta : aerolinea.getRutas( ) )
        {
            aeropuertos.put( ruta.getOrigen( ).getCodigo( ), ruta.getOrigen( ) );
            aeropuertos.put( ruta.getDestino( ).getCodigo( ), ruta.getDestino( ) );
        }

        JSONArray jAeropuertos = new JSONArray( );
        for( Aeropuerto aeropuerto : aeropuertos.values( ) )
        {
            JSONObject jAeropuerto = new JSONObject( );
            jAeropuerto.put( NOMBRE, aeropuerto.getNombre( ) );
            jAeropuerto.put( CODIGO, aeropuerto.getCodigo( ) );
            jAeropuerto.put( NOMBRE_CIUDAD, aeropuerto.getNombreCiudad( ) );
            jAeropuerto.put( LATITUD, aeropuerto.getLatitud( ) );
            jAeropuerto.put( LONGITUD, aeropuerto.getLongitud( ) );
            jAeropuertos.put( jAeropuerto );
        }
        jobject.put( AEROPUERTOS, jAeropuertos );
    }

    /**
     * Salva la información de las rutas de la aerolínea dentro de la llave 'rutas'
     * @param aerolinea La aerolínea que tiene la información
     * @param jobject El objeto JSON donde debe quedar la información
     */
    private void salvarRutas( Aerolinea aerolinea, JSONObject jobject )
    {
        JSONArray jRutas = new JSONArray( );
        for( Ruta ruta : aerolinea.getRutas( ) )
        {
            JSONObject jRuta = new JSONObject( );
            jRuta.put( CODIGO_RUTA, ruta.getCodigoRuta( ) );
            jRuta.put( HORA_SALIDA, ruta.getHoraSalida( ) );
            jRuta.put( HORA_LLEGADA, ruta.getHoraLlegada( ) );
            jRuta.put( ORIGEN, ruta.getOrigen( ).getCodigo( ) );
            jRuta.put( DESTINO, ruta.getDestino( ).getCodigo( ) );
            jRutas.put( jRuta );
        }
        jobject.put( RUTAS, jRutas );
    }

    /**
     * Salva la información de los vuelos de la aerolínea dentro de la llave 'vuelos'
     * @param aerolinea La aerolínea que tiene la información
     * @param jobject El objeto JSON donde debe quedar la información
     */
    private void salvarVuelos( Aerolinea aerolinea, JSONObject jobject )
    {
        JSONArray jVuelos = new JSONArray( );
        for( Vuelo vuelo : aerolinea.getVuelos( ) )
        {
            JSONObject jVuelo = new JSONObject( );
            jVuelo.put( FECHA, vuelo.getFecha( ) );
            jVuelo.put( CODIGO_RUTA, vuelo.getRuta( ).getCodigoRuta( ) );
            jVuelo.put( AVION, vuelo.getAvion( ).getNombre( ) );
            jVuelos.put( jVuelo );
        }
        jobject.put( VUELOS, jVuelos );
    }
}
