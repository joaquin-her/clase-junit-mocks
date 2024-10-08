package org.algo3;



import org.algo3.modelo.*;
import org.algo3.modelo.invitado.Invitado;
import org.algo3.modelo.proveedor.Proveedor;
import org.algo3.modelo.tiempo.Tiempo;
import org.junit.*;
//Let's import Mockito statically so that the code looks clearer
import static org.mockito.Mockito.*;


import java.util.ArrayList;

public class YayoTest {

    @BeforeClass
    public static void beforeAll(){
        System.out.println("BEFORE ALL - Esta funcion se corre antes de correr todos los test");
    }

    // ATENCION - static
    @Before
    public void beforeEach(){
        System.out.println("BEFORE EACH - Esta funcion se corre antes de correr cada uno de los test!");
    }

    // ATENCION - static
    @AfterClass
    public static void afterAll(){
        System.out.println("AFTER ALL - Esta funcion se corre luego de correr todos los test");
    }

    @After
    public void afterEach(){
        System.out.println("AFTER EACH - Esta funcion se corre luego de correr cada uno de los test!");
    }

    @Test
    public void cuandoSeInstanciaYayoNoTieneChistes(){
        // Arrange
        Proveedor proovedorMock = mock(Proveedor.class);
        Invitado invitadoMock = mock(Invitado.class);
        Yayo yayo = new Yayo(proovedorMock, invitadoMock);

        //Act
        ArrayList<Chiste> chistes = yayo.todosLosChistes();

        //Assert
        Assert.assertEquals(0, chistes.size());
    }

    @Test
    public void yayoCuentaUnChisteDeProgramacionEnEspanniolSiElDiaEsPar(){

        // Arrange
        Chiste chisteFalso = new Chiste("Programming","hola pepe","Hola jose");
        Proveedor proovedorStub = mock(Proveedor.class);
        when(proovedorStub.solicitarChiste("Programming", "es")).thenReturn(chisteFalso);
        Invitado invitadoMock = mock(Invitado.class);
        Tiempo tiempoStub = mock(Tiempo.class);
        when(tiempoStub.obtenerDiaDeHoy()).thenReturn(2);

        Yayo yayo = new Yayo(proovedorStub, invitadoMock);


        //Act
        Chiste chiste = yayo.contarChiste(tiempoStub);

        //Assert
        Assert.assertTrue(chiste.tieneCategoria("Programming"));
    }

    @Test
    public void yayoInvocaAlProvedorParaSolicitarChisteDeProgramacion(){

        // Arrange
        Chiste chisteFalso = new Chiste("Programming","hola pepe","Hola jose");
        Proveedor proovedorStub = mock(Proveedor.class);
        when(proovedorStub.solicitarChiste("Programming", "es")).thenReturn(chisteFalso);
        Invitado invitadoMock = mock(Invitado.class);
        Tiempo tiempoStub = mock(Tiempo.class);
        when(tiempoStub.obtenerDiaDeHoy()).thenReturn(2);
        Yayo yayo = new Yayo(proovedorStub, invitadoMock);


        //Act
        Chiste chiste = yayo.contarChiste(tiempoStub);

        //Assert
        verify(proovedorStub, times(1)).solicitarChiste("Programming", "es");
    }

    @Test
    public void yayoCuentaUnChisteDeNavidadEnInglesSiElDiaEsInpar(){

        // Arrange
        Chiste chisteFalso = new Chiste("Christmas","Hello pepe!","Hi Mark");
        Proveedor proovedorStub = mock(Proveedor.class);
        when(proovedorStub.solicitarChiste("Christmas", "en")).thenReturn(chisteFalso);
        Invitado invitadoMock = mock(Invitado.class);
        Tiempo tiempoStub = mock(Tiempo.class);
        when(tiempoStub.obtenerDiaDeHoy()).thenReturn(5);

        Yayo yayo = new Yayo(proovedorStub, invitadoMock);


        //Act
        Chiste chiste = yayo.contarChiste(tiempoStub);

        //Assert
        Assert.assertTrue(chiste.tieneCategoria("Christmas"));
    }

    @Test
    public void yayoInvocaAlProvedorParaSolicitarChisteDeNavidad(){

        // Arrange
        Chiste chisteFalso = new Chiste("Christmas","Hello pepe!","Hi Mark");
        Proveedor proovedorStub = mock(Proveedor.class);
        when(proovedorStub.solicitarChiste("Christmas", "en")).thenReturn(chisteFalso);
        Invitado invitadoMock = mock(Invitado.class);
        Tiempo tiempoStub = mock(Tiempo.class);
        when(tiempoStub.obtenerDiaDeHoy()).thenReturn(5);
        Yayo yayo = new Yayo(proovedorStub, invitadoMock);


        //Act
        Chiste chiste = yayo.contarChiste(tiempoStub);

        //Assert
        verify(proovedorStub, times(1)).solicitarChiste("Christmas", "en");
    }

    @Test
    public void yayoCuentaUnChisteMalisimo(){

        // Arrange
        Chiste chisteFalso = new Chiste("Christmas","Hello pepe!","Hi Mark");
        Proveedor proovedorStub = mock(Proveedor.class);
        when(proovedorStub.solicitarChiste("Christmas", "en")).thenReturn(chisteFalso);
        Invitado invitadoStub = mock(Invitado.class);
        when(invitadoStub.puntuar(chisteFalso)).thenReturn(2);
        Tiempo tiempoStub = mock(Tiempo.class);
        when(tiempoStub.obtenerDiaDeHoy()).thenReturn(5);

        Yayo yayo = new Yayo(proovedorStub, invitadoStub);


        //Act
        Chiste chiste = yayo.contarChiste(tiempoStub);

        //Assert
        Assert.assertTrue(chiste.esMalo());

    }

    @Test
    public void yayoCuentaUnChisteBuenisimo(){

        // Arrange
        Chiste chisteFalso = new Chiste("Christmas","Hello pepe!","Hi Mark");
        Proveedor proovedorStub = mock(Proveedor.class);
        when(proovedorStub.solicitarChiste("Christmas", "en")).thenReturn(chisteFalso);
        Invitado invitadoStub = mock(Invitado.class);
        when(invitadoStub.puntuar(chisteFalso)).thenReturn(10);
        Tiempo tiempoStub = mock(Tiempo.class);
        when(tiempoStub.obtenerDiaDeHoy()).thenReturn(5);

        Yayo yayo = new Yayo(proovedorStub, invitadoStub);


        //Act
        Chiste chiste = yayo.contarChiste(tiempoStub);

        //Assert
        Assert.assertFalse(chiste.esMalo());

    }

    @Ignore("Test es ignorado")
    @Test
    public void testSame() {
        Assert.assertEquals(1, 1);
    }

    @Test 
    public void yayoCuentaUnChisteDesdeUnTxt(){

        //Arrange

        Invitado invitadoStub = mock(Invitado.class);
        BufferedReader brMock = Mockito.mock(BufferedReader.class);
        ProveedorTxt proveedor = spy(new ProveedorTxt());
        Tiempo tiempoStub = mock(Tiempo.class);
        when(tiempoStub.obtenerDiaDeHoy()).thenReturn(2);
        // Usamos Mockito.when().thenReturn() para especificar qué debería devolver el mock cuando se llamen ciertos métodos. 
        // En este caso, simulamos las lecturas de líneas del archivo:
        Mockito.when(brMock.readLine()).thenReturn("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19");

        // Espiar la clase ProveedorTxt
        doReturn(brMock).when(proveedor).crearBufferedReader();

        // Llamar al método a probar
        Yayo yayo = new Yayo(proveedor, invitadoStub);
        Chiste = yayo.contarChiste(tiempoStub);

        // Verificar que readLine se llamó entre 16 y 21 veces
        verify(brMock, atLeast(1)).readLine();
        verify(brMock, atMost(5)).readLine();
    }
}
