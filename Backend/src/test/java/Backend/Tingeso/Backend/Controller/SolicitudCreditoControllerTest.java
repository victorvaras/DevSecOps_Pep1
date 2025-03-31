package Backend.Tingeso.Backend.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import Backend.Tingeso.Backend.Controller.Solicitud_Credito_Controller;
import Backend.Tingeso.Backend.Entity.Solicitud_Credito_Entity;
import Backend.Tingeso.Backend.Service.Solicitud_Credito_Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.List;

public class SolicitudCreditoControllerTest {

    @Mock
    private Solicitud_Credito_Service solicitud_credito_service;

    @InjectMocks
    private Solicitud_Credito_Controller solicitud_credito_controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test para crear una nueva solicitud de crédito exitosamente
    @Test
    public void testNewSolicitud_Success() throws Exception {
        // Arrange: Configurar los archivos y la solicitud simulada
        MockMultipartFile comprobanteIngresos = new MockMultipartFile("comprobante_ingresos", "comprobante.pdf", "application/pdf", "comprobante".getBytes());
        Solicitud_Credito_Entity solicitud = new Solicitud_Credito_Entity();
        solicitud.setMonto_deseado(100000);
        solicitud.setPlazo_deseado(12);
        solicitud.setId_Tipo_Prestamo(1);
        solicitud.setId_cliente(123);

        // Simular el comportamiento del servicio para crear la nueva solicitud de crédito
        when(solicitud_credito_service.nuevaSolicitud_Credito(any(Solicitud_Credito_Entity.class))).thenReturn(solicitud);

        // Act: Llamar al método a probar
        ResponseEntity<Solicitud_Credito_Entity> response = solicitud_credito_controller.newSolicitud(
                1, 123, 100000, 12, comprobanteIngresos, null, null, null, null, null, null, null);

        // Assert: Verificar que la respuesta sea correcta
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(solicitud, response.getBody());

        // Verificar que se llamó al servicio para crear la nueva solicitud de crédito
        verify(solicitud_credito_service, times(1)).nuevaSolicitud_Credito(any(Solicitud_Credito_Entity.class));
    }

    // Test para crear una nueva solicitud de crédito con todos los archivos presentes
    @Test
    public void testNewSolicitud_AllFilesPresent() throws Exception {
        // Arrange: Configurar los archivos simulados
        MockMultipartFile comprobanteIngresos = new MockMultipartFile("comprobante_ingresos", "comprobante.pdf", "application/pdf", "comprobante".getBytes());
        MockMultipartFile certificadoAvaluo = new MockMultipartFile("certificado_avaluo", "certificado.pdf", "application/pdf", "certificado".getBytes());

        // Crear más archivos simulados según sea necesario...

        // Simular la entidad de la solicitud de crédito
        Solicitud_Credito_Entity solicitud = new Solicitud_Credito_Entity();

        when(solicitud_credito_service.nuevaSolicitud_Credito(any(Solicitud_Credito_Entity.class))).thenReturn(solicitud);

        // Act: Llamar al método a probar
        ResponseEntity<Solicitud_Credito_Entity> response = solicitud_credito_controller.newSolicitud(
                1, 123, 100000, 12, comprobanteIngresos, certificadoAvaluo,
                null, null, null, null, null, null);

        // Assert: Verificar que la respuesta sea correcta
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verificar que se llamó al servicio para crear la nueva solicitud de crédito
        verify(solicitud_credito_service, times(1)).nuevaSolicitud_Credito(any(Solicitud_Credito_Entity.class));
    }

    @Test
    public void testNewSolicitud_FileProcessingError() throws Exception {
        // Arrange: Configurar un archivo simulado que lanzará una excepción al ser procesado
        MockMultipartFile comprobanteIngresos = mock(MockMultipartFile.class);

        when(comprobanteIngresos.isEmpty()).thenReturn(false);
        when(comprobanteIngresos.getBytes()).thenThrow(new IOException("Error en el procesamiento del archivo"));

        // Act: Llamar al método a probar con un archivo que lanza una excepción
        ResponseEntity<Solicitud_Credito_Entity> response = solicitud_credito_controller.newSolicitud(
                1, 123, 100000, 12, comprobanteIngresos, null,
                null, null, null, null, null, null);

        // Assert: Verificar que la respuesta sea HTTP 500 (error interno)
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

        // Verificar que no se llamó al servicio para crear la nueva solicitud de crédito debido a la excepción
        verify(solicitud_credito_service, never()).nuevaSolicitud_Credito(any(Solicitud_Credito_Entity.class));
    }

    @Test
    public void testNewSolicitud_SomeFilesPresent() throws Exception {
        // Arrange: Configurar algunos archivos simulados
        MockMultipartFile comprobanteIngresos = new MockMultipartFile("comprobante_ingresos", "comprobante.pdf", "application/pdf", "comprobante".getBytes());
        MockMultipartFile certificadoAvaluo = new MockMultipartFile("certificado_avaluo", "avaluo.pdf", "application/pdf", "avaluo".getBytes());

        // Simular la entidad de la solicitud de crédito
        Solicitud_Credito_Entity solicitud = new Solicitud_Credito_Entity();

        when(solicitud_credito_service.nuevaSolicitud_Credito(any(Solicitud_Credito_Entity.class))).thenReturn(solicitud);

        // Act: Llamar al método a probar con algunos archivos presentes
        ResponseEntity<Solicitud_Credito_Entity> response = solicitud_credito_controller.newSolicitud(
                1, 123, 100000, 12, comprobanteIngresos, certificadoAvaluo,
                null, null, null, null, null, null);

        // Assert: Verificar que la respuesta sea correcta
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verificar que se llamó al servicio para crear la nueva solicitud de crédito
        verify(solicitud_credito_service, times(1)).nuevaSolicitud_Credito(any(Solicitud_Credito_Entity.class));
    }

    // Test para obtener todas las solicitudes exitosamente
    @Test
    public void testGetAllSolicitudes_Success() {
        // Arrange: Configurar una lista simulada de solicitudes de crédito
        List<Solicitud_Credito_Entity> solicitudes = List.of(new Solicitud_Credito_Entity(), new Solicitud_Credito_Entity());

        when(solicitud_credito_service.getAllSolicitud_Credito()).thenReturn(solicitudes);

        // Act: Llamar al método a probar
        ResponseEntity<List<Solicitud_Credito_Entity>> response = solicitud_credito_controller.getAllSolicitudes();

        // Assert: Verificar que la respuesta sea correcta
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(solicitudes, response.getBody());

        // Verificar que se llamó al servicio para obtener todas las solicitudes de crédito
        verify(solicitud_credito_service, times(1)).getAllSolicitud_Credito();
    }

    @Test
    public void testGetSolicitudeById_Success() {
        // Arrange: Configurar una solicitud simulada
        Solicitud_Credito_Entity solicitud = new Solicitud_Credito_Entity();
        solicitud.setId_solicitud_credito(1);

        when(solicitud_credito_service.getSolicitud_Credito(1)).thenReturn(solicitud);

        // Act: Llamar al método a probar
        ResponseEntity<Solicitud_Credito_Entity> response = solicitud_credito_controller.getSolicitudeById(1);

        // Assert: Verificar que la respuesta sea correcta
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(solicitud, response.getBody());

        // Verificar que se llamó al servicio para obtener la solicitud por ID
        verify(solicitud_credito_service, times(1)).getSolicitud_Credito(1);
    }

    // Test para actualizar el seguimiento de una solicitud exitosamente
    @Test
    public void testUpdateSolicitudSeguimiento_Success() {
        // Arrange: Configurar una solicitud simulada con seguimiento actualizado
        Solicitud_Credito_Entity updatedSolicitud = new Solicitud_Credito_Entity();
        updatedSolicitud.setId_solicitud_credito(1);
        updatedSolicitud.setId_seguimiento_solicitud(2);  // Simular que el seguimiento fue actualizado

        // Simular el comportamiento del servicio para actualizar la solicitud de crédito
        when(solicitud_credito_service.updateSolicitud_Credito_SeguimientoSolicitud(1, 2)).thenReturn(updatedSolicitud);

        // Act: Llamar al método a probar
        ResponseEntity<Solicitud_Credito_Entity> response = solicitud_credito_controller.updateSolicitud_Seguimiento(1, 2);

        // Assert: Verificar que la respuesta sea correcta
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedSolicitud, response.getBody());

        // Verificar que se llamó al servicio para actualizar la solicitud de crédito con los parámetros correctos
        verify(solicitud_credito_service, times(1)).updateSolicitud_Credito_SeguimientoSolicitud(1, 2);
    }
}