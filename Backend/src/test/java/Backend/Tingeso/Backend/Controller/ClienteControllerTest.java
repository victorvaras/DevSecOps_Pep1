package Backend.Tingeso.Backend.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import Backend.Tingeso.Backend.Entity.Cliente_Entity;
import Backend.Tingeso.Backend.Service.Cliente_service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

public class ClienteControllerTest {

    @Mock
    private Cliente_service cliente_service;

    @InjectMocks
    private Cliente_Controller cliente_controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test para crear un nuevo cliente con archivos válidos
    @Test
    public void testNewCliente_Success() throws Exception {
        // Arrange: Configurar los archivos y el cliente simulado
        MockMultipartFile identificacion = new MockMultipartFile("identificacion", "identificacion.pdf", "application/pdf", "identificacion".getBytes());
        MockMultipartFile comprobanteIngresos = new MockMultipartFile("comprobante_ingresos", "comprobante.pdf", "application/pdf", "comprobante".getBytes());

        Cliente_Entity nuevoCliente = new Cliente_Entity();
        nuevoCliente.setRut(12345678);
        nuevoCliente.setNombre("Juan");
        nuevoCliente.setApellido("Perez");
        nuevoCliente.setContrasenia("password");

        when(cliente_service.createCliente(any(Cliente_Entity.class))).thenReturn(nuevoCliente);

        // Act: Llamar al método a probar
        ResponseEntity<Cliente_Entity> response = cliente_controller.newCliente(
                12345678, "Juan", "Perez", "password", identificacion, comprobanteIngresos);

        // Assert: Verificar que la respuesta sea correcta
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(nuevoCliente, response.getBody());

        // Verificar que se llamó al servicio para crear el cliente
        verify(cliente_service, times(1)).createCliente(any(Cliente_Entity.class));
    }

    // Test para manejar excepción al cargar archivos vacíos
    @Test
    public void testNewCliente_FailEmptyFiles() throws Exception {
        // Arrange: Configurar archivos vacíos y el cliente simulado
        MockMultipartFile identificacion = new MockMultipartFile("identificacion", "", "application/pdf", new byte[0]);
        MockMultipartFile comprobanteIngresos = new MockMultipartFile("comprobante_ingresos", "", "application/pdf", new byte[0]);

        // Act: Llamar al método a probar con archivos vacíos
        ResponseEntity<Cliente_Entity> response = cliente_controller.newCliente(
                12345678, "Juan", "Perez", "password", identificacion, comprobanteIngresos);

        // Assert: Verificar que la respuesta sea HTTP 500 (error interno)
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

        // Verificar que no se llamó al servicio para crear el cliente
        verify(cliente_service, never()).createCliente(any(Cliente_Entity.class));
    }

    // Test para manejar el caso en que el archivo comprobante de ingresos está vacío
    @Test
    public void testNewCliente_ComprobanteIngresosEmpty() throws Exception {
        // Arrange: Configurar los archivos simulados
        MockMultipartFile identificacion = new MockMultipartFile("identificacion", "identificacion.pdf", "application/pdf", "identificacion".getBytes());
        MockMultipartFile comprobanteIngresos = new MockMultipartFile("comprobante_ingresos", "", "application/pdf", new byte[0]);  // Archivo vacío

        // Act: Llamar al método a probar con un archivo vacío para comprobanteIngresos
        ResponseEntity<Cliente_Entity> response = cliente_controller.newCliente(
                12345678, "Juan", "Perez", "password", identificacion, comprobanteIngresos);

        // Assert: Verificar que la respuesta sea HTTP 500 (error interno)
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

        // Verificar que no se llamó al servicio para crear el cliente debido al error en los archivos
        verify(cliente_service, never()).createCliente(any(Cliente_Entity.class));
    }


    // Test para obtener un cliente por ID exitosamente
    @Test
    public void testGetClienteById_Success() {
        // Arrange: Configurar el cliente simulado
        Cliente_Entity cliente = new Cliente_Entity();
        cliente.setRut(12345678);
        when(cliente_service.getClienteById(1)).thenReturn(cliente);

        // Act: Llamar al método a probar
        ResponseEntity<Cliente_Entity> response = cliente_controller.getClienteById(1);

        // Assert: Verificar que la respuesta sea correcta
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cliente, response.getBody());

        // Verificar que se llamó al servicio para obtener el cliente por ID
        verify(cliente_service, times(1)).getClienteById(1);
    }

    // Test para login de un cliente exitosamente
    @Test
    public void testGetClienteByLogin_Success() {
        // Arrange: Configurar el login simulado
        Cliente_Entity cliente = new Cliente_Entity();
        cliente.setRut(12345678);
        cliente.setContrasenia("password");

        when(cliente_service.LoginCliente(12345678, "password")).thenReturn(1);

        // Act: Llamar al método a probar
        ResponseEntity<Integer> response = cliente_controller.getClienteByLogin(cliente);

        // Assert: Verificar que la respuesta sea correcta (login exitoso)
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody());

        // Verificar que se llamó al servicio para realizar el login del cliente
        verify(cliente_service, times(1)).LoginCliente(12345678, "password");
    }


}