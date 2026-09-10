package uniandes.dpoo.festival.consola;

import java.util.List;

import uniandes.dpoo.festival.modelo.Aliado;
import uniandes.dpoo.festival.modelo.Amenity;
import uniandes.dpoo.festival.modelo.Artista;
import uniandes.dpoo.festival.modelo.Atraccion;
import uniandes.dpoo.festival.modelo.Banda;
import uniandes.dpoo.festival.modelo.Emprendimiento;
import uniandes.dpoo.festival.modelo.FestivalMusical;
import uniandes.dpoo.festival.modelo.Restaurante;
import uniandes.dpoo.festival.modelo.Solista;
import uniandes.dpoo.festival.modelo.Zona;

/**
 * Programa de prueba que arma un festival de ejemplo y lo imprime en consola.
 *
 * <p>
 * Su objetivo es doble: demostrar que todas las clases del diagrama compilan y
 * se conectan entre si, y servir de ejemplo de uso del modelo. Se ubica en un
 * paquete distinto (<code>consola</code>) para separar la logica del modelo de
 * la interfaz, que es la convencion tipica en los proyectos de DPOO.
 * </p>
 *
 * <p>
 * Para ejecutarlo en Eclipse: clic derecho sobre este archivo &gt; Run As &gt;
 * Java Application.
 * </p>
 */
public class Main
{
    /**
     * Punto de entrada de la aplicacion.
     *
     * @param args no se utilizan.
     */
    public static void main( String[] args )
    {
        // ---------------------------------------------------------------
        // Paso 1: crear las zonas del festival
        // ---------------------------------------------------------------
        Zona zonaGeneral = new Zona( "General" );
        Zona zonaVIP = new Zona( "VIP" );

        // ---------------------------------------------------------------
        // Paso 2: agregar amenities a cada zona (asociacion Zona --> Amenity)
        // ---------------------------------------------------------------
        zonaGeneral.agregarAmenity( new Amenity( "Banos publicos", "Costado norte" ) );
        zonaGeneral.agregarAmenity( new Amenity( "Punto de hidratacion", "Entrada principal" ) );
        zonaVIP.agregarAmenity( new Amenity( "Lounge climatizado", "Segundo piso" ) );

        // ---------------------------------------------------------------
        // Paso 3: crear los aliados comerciales
        // ---------------------------------------------------------------
        Restaurante burgers = new Restaurante( "Burgers del Parque S.A.S.", 4500000, "Ana Rojas", "3101112233", "contacto@burgers.co", "PS-2026-001" );
        Restaurante veggie = new Restaurante( "Veggie Fest Ltda.", 3800000, "Luis Pena", "3104445566", "hola@veggiefest.co", "PS-2026-002" );
        Atraccion rueda = new Atraccion( "Rueda Panoramica S.A.", 9000000, "Marta Diaz", "3117778899", "info@rueda.co", "Seguros Andinos" );
        Emprendimiento artesanias = new Emprendimiento( "Manos de Barro", 1200000, "Pedro Gil", "3123334455", "ventas@manosdebarro.co", "Artesanias" );

        // ---------------------------------------------------------------
        // Paso 4: ubicar aliados en las zonas
        // (Zona --> Restaurante 1..* y Zona --> Atraccion 0..*)
        // ---------------------------------------------------------------
        zonaGeneral.agregarRestaurante( burgers );
        zonaGeneral.agregarAtraccion( rueda );
        zonaVIP.agregarRestaurante( veggie );

        // ---------------------------------------------------------------
        // Paso 5: crear los artistas y asignarles proveedores de alimentacion
        // (Artista --> Restaurante 1..*)
        // ---------------------------------------------------------------
        Banda banda = new Banda( "Los Del Valle", "VALLE", 25000000, new String[]{ "Camilo", "Sara", "Nicolas", "Juliana" } );
        banda.agregarProveedorAlimentacion( veggie );

        Solista solista = new Solista( "Andrea Mora", "ANDMOR", 18000000 );
        solista.agregarProveedorAlimentacion( burgers );
        solista.agregarProveedorAlimentacion( veggie );

        // ---------------------------------------------------------------
        // Paso 6: armar el festival y registrar todos los participantes
        // (FestivalMusical --> Participante 1..*)
        // ---------------------------------------------------------------
        FestivalMusical festival = new FestivalMusical( "Festival Andino 2026", zonaGeneral, zonaVIP );
        festival.agregarParticipante( burgers );
        festival.agregarParticipante( veggie );
        festival.agregarParticipante( rueda );
        festival.agregarParticipante( artesanias );
        festival.agregarParticipante( banda );
        festival.agregarParticipante( solista );

        // ---------------------------------------------------------------
        // Paso 7: imprimir el resumen usando los metodos del modelo
        // ---------------------------------------------------------------
        System.out.println( "=== " + festival.getNombre( ) + " ===" );
        System.out.println( );

        System.out.println( "Aliados registrados:" );
        List<Aliado> aliados = festival.getAliados( );
        for( Aliado aliado : aliados )
        {
            // getTipoAliado() se resuelve de forma polimorfica en cada subclase
            System.out.println( "  - " + aliado + " | renta: $" + aliado.getCostoRentaEspacio( ) );
        }
        System.out.println( );

        System.out.println( "Artistas registrados:" );
        List<Artista> artistas = festival.getArtistas( );
        for( Artista artista : artistas )
        {
            System.out.println( "  - " + artista + " | integrantes: " + artista.getCantidadIntegrantes( ) + " | cache: $" + artista.getCosto( ) );
            System.out.println( "      proveedores de alimentacion: " + artista.getProveedoresAlimentacion( ) );
            System.out.println( "      cumple multiplicidad 1..*: " + artista.cumpleMultiplicidadProveedores( ) );
        }
        System.out.println( );

        System.out.println( "Zonas:" );
        imprimirZona( festival.getZonaGeneral( ) );
        imprimirZona( festival.getZonaVIP( ) );
        System.out.println( );

        System.out.println( "Balance economico del festival: $" + festival.calcularBalanceEconomico( ) );
        System.out.println( "(positivo = utilidad, negativo = perdida)" );
    }

    /**
     * Imprime el detalle de una zona: sus amenities, atracciones y
     * restaurantes.
     *
     * @param zona zona a imprimir.
     */
    private static void imprimirZona( Zona zona )
    {
        System.out.println( "  " + zona );
        System.out.println( "     amenities:    " + zona.getAmenities( ) );
        System.out.println( "     atracciones:  " + zona.getAtracciones( ) );
        System.out.println( "     restaurantes: " + zona.getRestaurantes( ) );
        System.out.println( "     ingresos por rentas: $" + zona.calcularIngresosPorRentas( ) );
        System.out.println( "     cumple multiplicidades: " + zona.cumpleMultiplicidades( ) );
    }
}
