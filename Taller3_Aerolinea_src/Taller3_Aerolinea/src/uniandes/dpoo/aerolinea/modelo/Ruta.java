package uniandes.dpoo.aerolinea.modelo;

/**
 * Esta clase tiene la información de una ruta entre dos aeropuertos que cubre una aerolínea.
 */
public class Ruta
{
    /**
     * El aeropuerto del que sale la ruta
     */
    private Aeropuerto origen;

    /**
     * El aeropuerto al que llega la ruta
     */
    private Aeropuerto destino;

    /**
     * La hora de salida, expresada como una cadena donde los dos últimos caracteres son los minutos (ej. '715' son las 7:15)
     */
    private String horaSalida;

    /**
     * La hora de llegada, expresada como una cadena donde los dos últimos caracteres son los minutos (ej. '1250' son las 12:50)
     */
    private String horaLlegada;

    /**
     * El código que identifica la ruta. Debe ser único.
     */
    private String codigoRuta;

    /**
     * Construye una nueva ruta con la información dada
     * @param origen El aeropuerto de origen
     * @param destino El aeropuerto de destino
     * @param horaSalida La hora de salida
     * @param horaLlegada La hora de llegada
     * @param codigoRuta El código de la ruta
     */
    public Ruta( Aeropuerto origen, Aeropuerto destino, String horaSalida, String horaLlegada, String codigoRuta )
    {
        this.origen = origen;
        this.destino = destino;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.codigoRuta = codigoRuta;
    }

    public String getCodigoRuta( )
    {
        return codigoRuta;
    }

    public Aeropuerto getOrigen( )
    {
        return origen;
    }

    public Aeropuerto getDestino( )
    {
        return destino;
    }

    public String getHoraSalida( )
    {
        return horaSalida;
    }

    public String getHoraLlegada( )
    {
        return horaLlegada;
    }

    /**
     * Calcula la duración esperada del vuelo en minutos.
     *
     * Si la hora de llegada es anterior a la hora de salida, se asume que el vuelo llega al día siguiente.
     * @return La duración del vuelo en minutos
     */
    public int getDuracion( )
    {
        int minutosSalida = getHoras( horaSalida ) * 60 + getMinutos( horaSalida );
        int minutosLlegada = getHoras( horaLlegada ) * 60 + getMinutos( horaLlegada );

        int duracion = minutosLlegada - minutosSalida;
        if( duracion < 0 )
            duracion = duracion + 24 * 60;

        return duracion;
    }

    /**
     * Dada una cadena con una hora y minutos, retorna los minutos.
     *
     * Por ejemplo, para la cadena '715' retorna 15.
     * @param horaCompleta Una cadena con una hora, donde los minutos siempre ocupan los dos últimos caracteres
     * @return Una cantidad de minutos entre 0 y 59
     */
    public static int getMinutos( String horaCompleta )
    {
        int minutos = Integer.parseInt( horaCompleta ) % 100;
        return minutos;
    }

    /**
     * Dada una cadena con una hora y minutos, retorna las horas.
     *
     * Por ejemplo, para la cadena '715' retorna 7.
     * @param horaCompleta Una cadena con una hora, donde los minutos siempre ocupan los dos últimos caracteres
     * @return Una cantidad de horas entre 0 y 23
     */
    public static int getHoras( String horaCompleta )
    {
        int horas = Integer.parseInt( horaCompleta ) / 100;
        return horas;
    }


}
