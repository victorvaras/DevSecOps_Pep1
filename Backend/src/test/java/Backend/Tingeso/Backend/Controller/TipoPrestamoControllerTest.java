package Backend.Tingeso.Backend.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import Backend.Tingeso.Backend.Controller.Tipo_Prestamo_Controller;
import Backend.Tingeso.Backend.Entity.Tipo_Prestamo_Entity;
import Backend.Tingeso.Backend.Service.Tipo_Prestamo_Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class TipoPrestamoControllerTest {

    @Mock
    private Tipo_Prestamo_Service tipo_prestamo_service;

    @InjectMocks
    private Tipo_Prestamo_Controller tipo_prestamo_controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test para obtener un tipo de préstamo por ID exitosamente
    @Test
    public void testGetTipoPrestamoById_Success() {
        // Arrange: Configurar el tipo de préstamo simulado
        Tipo_Prestamo_Entity tipoPrestamo = new Tipo_Prestamo_Entity();
        tipoPrestamo.setId_Tipo_Prestamo(1);
        tipoPrestamo.setNombreTipo_Prestamo("Préstamo Hipotecario");

        when(tipo_prestamo_service.getTipo_prestamo(1)).thenReturn(tipoPrestamo);

        // Act: Llamar al método a probar
        ResponseEntity<Tipo_Prestamo_Entity> response = tipo_prestamo_controller.getTipoPrestamoById(1);

        // Assert: Verificar que la respuesta sea correcta
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tipoPrestamo, response.getBody());

        // Verificar que se llamó al servicio para obtener el tipo de préstamo por ID
        verify(tipo_prestamo_service, times(1)).getTipo_prestamo(1);
    }
}