package uniandes.dpoo.festival.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase principal del modelo: representa el festival musical completo.
 *
 * <p>
 * <b>Asociaciones del UML implementadas en esta clase:</b>
 * </p>
 * <ul>
 * <li>FestivalMusical --&gt; Zona con rol <code>zonaGeneral</code>: una sola
 * zona, por eso es un atributo simple y no una lista.</li>
 * <li>FestivalMusical --&gt; Zona con rol <code>zonaVIP</code>: igualmente una
 * sola zona.</li>
 * <li>FestivalMusical --&gt; Participante con rol <code>participantes</code> y
 * multiplicidad <code>1..*</code>: se implementa como
 * <code>List&lt;Participante&gt;</code>. Al ser una lista del tipo padre, ahi
 * caben tanto aliados como artistas.</li>
 * </ul>
 *
 * <p>
 * Esta clase concentra las consultas agregadas del modelo (totales, filtros por
 * tipo) porque es la que conoce a todos los participantes.
 * </p>
 */
public class FestivalMusical
{
    // -------------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------------

    /** Nombre del festival. */
    private String nombre;

    /** Zona de acceso general. UML: rol "zonaGeneral" */
    private Zona zonaGeneral;

    /** Zona de acceso preferencial. UML: rol "zonaVIP" */
    private Zona zonaVIP;

    /** Todos los participantes del festival. UML: rol "participantes", 1..* */
    private List<Participante> participantes;

    // -------------------------------------------------------------------
    // Constructores
    // -------------------------------------------------------------------

    /**
     * Construye el festival con sus dos zonas y sin participantes registrados
     * todavia.
     *
     * @param nombre      nombre del festival.
     * @param zonaGeneral zona general.
     * @param zonaVIP     zona VIP.
     */
    public FestivalMusical( String nombre, Zona zonaGeneral, Zona zonaVIP )
    {
        this.nombre = nombre;
        this.zonaGeneral = zonaGeneral;
        this.zonaVIP = zonaVIP;
        this.participantes = new ArrayList<Participante>( );
    }

    // -------------------------------------------------------------------
    // Metodos
    // -------------------------------------------------------------------

    public String getNombre( )
    {
        return nombre;
    }

    public void setNombre( String nombre )
    {
        this.nombre = nombre;
    }

    public Zona getZonaGeneral( )
    {
        return zonaGeneral;
    }

    public void setZonaGeneral( Zona zonaGeneral )
    {
        this.zonaGeneral = zonaGeneral;
    }

    public Zona getZonaVIP( )
    {
        return zonaVIP;
    }

    public void setZonaVIP( Zona zonaVIP )
    {
        this.zonaVIP = zonaVIP;
    }

    public List<Participante> getParticipantes( )
    {
        return participantes;
    }

    /**
     * Registra un participante en el festival.
     *
     * @param participante puede ser cualquier subclase de
     *                     {@link Participante} (aliado o artista).
     */
    public void agregarParticipante( Participante participante )
    {
        if( participante != null && !participantes.contains( participante ) )
        {
            participantes.add( participante );
        }
    }

    /**
     * Filtra los participantes que son artistas.
     *
     * <p>
     * Aqui si se usa <code>instanceof</code> porque el proposito del metodo es
     * justamente separar por tipo; el patron seguro es verificar y luego hacer
     * el cast.
     * </p>
     *
     * @return lista de artistas registrados.
     */
    public List<Artista> getArtistas( )
    {
        List<Artista> artistas = new ArrayList<Artista>( );
        for( Participante participante : participantes )
        {
            if( participante instanceof Artista )
            {
                artistas.add( ( Artista )participante );
            }
        }
        return artistas;
    }

    /**
     * Filtra los participantes que son aliados comerciales.
     *
     * @return lista de aliados registrados.
     */
    public List<Aliado> getAliados( )
    {
        List<Aliado> aliados = new ArrayList<Aliado>( );
        for( Participante participante : participantes )
        {
            if( participante instanceof Aliado )
            {
                aliados.add( ( Aliado )participante );
            }
        }
        return aliados;
    }

    /**
     * Calcula el balance economico del festival sumando el valor de cada
     * participante.
     *
     * <p>
     * Este es el metodo mas polimorfico del modelo: recorre la lista sin
     * preguntar de que tipo es cada elemento. Los aliados aportan en positivo
     * (pagan renta) y los artistas en negativo (cobran cache), porque cada
     * subclase define su propia version de
     * {@link Participante#calcularValorEconomico()}.
     * </p>
     *
     * @return balance total; positivo si hay utilidad, negativo si hay perdida.
     */
    public double calcularBalanceEconomico( )
    {
        double balance = 0;
        for( Participante participante : participantes )
        {
            balance += participante.calcularValorEconomico( );
        }
        return balance;
    }

    /**
     * Verifica la multiplicidad 1..* de la asociacion "participantes".
     *
     * @return true si hay al menos un participante registrado.
     */
    public boolean cumpleMultiplicidadParticipantes( )
    {
        return !participantes.isEmpty( );
    }

    @Override
    public String toString( )
    {
        return nombre;
    }
}
