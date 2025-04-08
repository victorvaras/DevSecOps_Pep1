package Backend.Tingeso.Backend.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import Backend.Tingeso.Backend.Controller.Simulacion_Credito_Controller;
import Backend.Tingeso.Backend.Entity.Simulacion_Credito_Entity;
import Backend.Tingeso.Backend.Service.Simulacion_Credito_Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class SimulacionCreditoControllerTest {

    @Mock
    private Simulacion_Credito_Service simulacion_credito_service;

    @InjectMocks
    private Simulacion_Credito_Controller simulacion_credito_controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test para crear una nueva simulación de crédito exitosamente
    @Test
    public void testNewSimulacionCredito_Success() {
        // Arrange: Configurar la simulación de crédito simulada
        Simulacion_Credito_Entity simulacionCredito = new Simulacion_Credito_Entity();
        simulacionCredito.setMonto_deseado(100000);
        simulacionCredito.setPlazo_deseado(12);

        // Simular el comportamiento del servicio para crear la nueva simulación de crédito
        when(simulacion_credito_service.newSimulacionCredito(any(Simulacion_Credito_Entity.class))).thenReturn(simulacionCredito);

        // Act: Llamar al método a probar
        ResponseEntity<Simulacion_Credito_Entity> response = simulacion_credito_controller.newSimulacionCredito(simulacionCredito);

        // Assert: Verificar que la respuesta sea correcta
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(simulacionCredito, response.getBody());

        // Verificar que se llamó al servicio para crear la nueva simulación de crédito
        verify(simulacion_credito_service, times(1)).newSimulacionCredito(any(Simulacion_Credito_Entity.class));
    }
}