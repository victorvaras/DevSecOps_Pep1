package Backend.Tingeso.Backend.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import Backend.Tingeso.Backend.Controller.Seguimiento_Solicitud_Controller;
import Backend.Tingeso.Backend.Entity.Seguimiento_Solicitud_Entity;
import Backend.Tingeso.Backend.Service.Seguimiento_Solicitud_Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class SeguimientoSolicitudControllerTest {

    @Mock
    private Seguimiento_Solicitud_Service seguimiento_solicitud_service;

    @InjectMocks
    private Seguimiento_Solicitud_Controller seguimiento_solicitud_controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test para obtener el seguimiento de una solicitud exitosamente
    @Test
    public void testObtenerSeguimientoSolicitud_Success() {
        // Arrange: Configurar el seguimiento de solicitud simulado
        Seguimiento_Solicitud_Entity seguimiento = new Seguimiento_Solicitud_Entity();
        seguimiento.setId_seguimiento_solicitud(1);
        seguimiento.setNombre_seguimiento_solicitud("En proceso");

        when(seguimiento_solicitud_service.getSeguimiento_Solicitud(1)).thenReturn(seguimiento);

        // Act: Llamar al método a probar
        ResponseEntity<Seguimiento_Solicitud_Entity> response = seguimiento_solicitud_controller.obtenerSeguimiento_Solicitud(1);

        // Assert: Verificar que la respuesta sea correcta
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(seguimiento, response.getBody());

        // Verificar que se llamó al servicio para obtener el seguimiento de la solicitud por ID
        verify(seguimiento_solicitud_service, times(1)).getSeguimiento_Solicitud(1);
    }

}