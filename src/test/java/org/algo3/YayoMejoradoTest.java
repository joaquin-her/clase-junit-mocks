package org.algo3;


import org.algo3.modelo.Chiste;
import org.algo3.modelo.Yayo;
import org.algo3.modelo.invitado.Invitado;
import org.algo3.modelo.proveedor.Proveedor;
import org.algo3.modelo.tiempo.Tiempo;
import org.junit.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class YayoMejoradoTest {

    @Mock private Proveedor proveedorMock;
    @Mock private Invitado invitadoMock;
    @Mock private Tiempo tiempoMock;

    private AutoCloseable closeable;


    // Inicializamos cada mock
    @Before
    public void beforeEach(){
        closeable = MockitoAnnotations.openMocks(this);
        Mockito.reset(proveedorMock, invitadoMock, tiempoMock);
    }

    @After
    public void releaseMocks() throws Exception {
        closeable.close();
    }

    @Test
    public void cuandoSeInstanciaYayoNoTieneChistes(){
        // Arrange
        Yayo yayo = new Yayo(proveedorMock, invitadoMock);

        //Act
        ArrayList<Chiste> chistes = yayo.todosLosChistes();

        //Assert
        Assert.assertEquals(0, chistes.size());
    }

    @Test
    public void yayoCuentaUnChisteDeProgramacionEnEspanniolSiElDiaEsPar(){

        // Arrange
        Chiste chisteFalso = new Chiste("Programming","hola pepe","Hola jose");
        when(proveedorMock.solicitarChiste("Programming", "es")).thenReturn(chisteFalso);
        when(tiempoMock.obtenerDiaDeHoy()).thenReturn(2);

        Yayo yayo = new Yayo(proveedorMock, invitadoMock);

        //Act
        Chiste chiste = yayo.contarChiste(tiempoMock);

        //Assert
        Assert.assertTrue(chiste.tieneCategoria("Programming"));
    }

    @Test
    public void yayoInvocaAlProvedorParaSolicitarChisteDeProgramacion(){

        // Arrange
        Chiste chisteFalso = new Chiste("Programming","hola pepe","Hola jose");
        when(YayoMejoradoTest.this.proveedorMock.solicitarChiste("Programming", "es")).thenReturn(chisteFalso);
        when(tiempoMock.obtenerDiaDeHoy()).thenReturn(2);
        Yayo yayo = new Yayo(proveedorMock, invitadoMock);

        //Act
        Chiste chiste = yayo.contarChiste(tiempoMock);

        //Assert
        verify(YayoMejoradoTest.this.proveedorMock, times(1)).solicitarChiste("Programming", "es");
    }

    @Test
    public void yayoCuentaUnChisteDeNavidadEnInglesSiElDiaEsInpar(){

        // Arrange
        Chiste chisteFalso = new Chiste("Christmas","Hello pepe!","Hi Mark");
        when(YayoMejoradoTest.this.proveedorMock.solicitarChiste("Christmas", "en")).thenReturn(chisteFalso);
        when(tiempoMock.obtenerDiaDeHoy()).thenReturn(5);

        Yayo yayo = new Yayo(proveedorMock, invitadoMock);

        //Act
        Chiste chiste = yayo.contarChiste(tiempoMock);

        //Assert
        Assert.assertTrue(chiste.tieneCategoria("Christmas"));
    }

    @Test
    public void yayoInvocaAlProvedorParaSolicitarChisteDeNavidad(){

        // Arrange
        Chiste chisteFalso = new Chiste("Christmas","Hello pepe!","Hi Mark");
        when(YayoMejoradoTest.this.proveedorMock.solicitarChiste("Christmas", "en")).thenReturn(chisteFalso);
        when(tiempoMock.obtenerDiaDeHoy()).thenReturn(5);
        Yayo yayo = new Yayo(YayoMejoradoTest.this.proveedorMock, invitadoMock);

        //Act
        Chiste chiste = yayo.contarChiste(tiempoMock);

        //Assert
        verify(YayoMejoradoTest.this.proveedorMock, times(1)).solicitarChiste("Christmas", "en");
    }

    @Test
    public void yayoCuentaUnChisteMalisimo(){

        // Arrange
        Chiste chisteFalso = new Chiste("Christmas","Hello pepe!","Hi Mark");
        when(YayoMejoradoTest.this.proveedorMock.solicitarChiste("Christmas", "en")).thenReturn(chisteFalso);
        when(invitadoMock.puntuar(chisteFalso)).thenReturn(2);
        when(tiempoMock.obtenerDiaDeHoy()).thenReturn(5);
        Yayo yayo = new Yayo(YayoMejoradoTest.this.proveedorMock, invitadoMock);

        //Act
        Chiste chiste = yayo.contarChiste(tiempoMock);

        //Assert
        Assert.assertTrue(chiste.esMalo());

    }

    @Test
    public void yayoCuentaUnChisteBuenisimo(){

        // Arrange
        Chiste chisteFalso = new Chiste("Christmas","Hello pepe!","Hi Mark");
        when(YayoMejoradoTest.this.proveedorMock.solicitarChiste("Christmas", "en")).thenReturn(chisteFalso);
        when(invitadoMock.puntuar(chisteFalso)).thenReturn(10);
        when(tiempoMock.obtenerDiaDeHoy()).thenReturn(5);
        Yayo yayo = new Yayo(YayoMejoradoTest.this.proveedorMock, invitadoMock);


        //Act
        Chiste chiste = yayo.contarChiste(tiempoMock);

        //Assert
        Assert.assertFalse(chiste.esMalo());

    }

    @Ignore("Test es ignorado")
    @Test
    public void testSame() {
        Assert.assertEquals(1, 1);
    }

    @Test
    public void yayoDevuelve3DeSusMejoresChistes(){
        //Arrange
        Chiste[] chistes = new Chiste[]{new Chiste("Christmas", "Hello pepe!", "Hi Mark"),
        new Chiste("Christmas", "Hello pepe!", "Hi Clark"),
        new Chiste("Christmas", "Hello pepe!", "Hi Kim"),
        new Chiste("Christmas", "Hello pepe!", "Hi Ben"),
        new Chiste("Christmas", "Hello pepe!", "Hi Ken")
        };
        when(tiempoMock.obtenerDiaDeHoy()).thenReturn(5);
        Yayo yayo = new Yayo(YayoMejoradoTest.this.proveedorMock, invitadoMock);
        int puntaje = 10;
        for (Chiste chiste : chistes) {
            when(YayoMejoradoTest.this.proveedorMock.solicitarChiste("Christmas", "en")).thenReturn(chiste);
            when(invitadoMock.puntuar(chiste)).thenReturn(puntaje);
            Chiste ch = yayo.contarChiste(tiempoMock);
            puntaje -= 1;
        };
        //Act
        ArrayList<Chiste> mejoresChistes = yayo.mejoresChistes(3);

        //Assert
        for (int i = 0; i < chistes.length; i++) {
            if (i < 3) {
                assert (mejoresChistes.contains(chistes[i]));
            } else assert (!mejoresChistes.contains(chistes[i]));
        }

    }
}
