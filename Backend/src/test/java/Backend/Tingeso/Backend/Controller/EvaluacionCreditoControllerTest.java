package Backend.Tingeso.Backend.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import Backend.Tingeso.Backend.Controller.Evaluacion_Credito_Controller;
import Backend.Tingeso.Backend.Entity.Evaluacion_Credito_Entity;
import Backend.Tingeso.Backend.Entity.Solicitud_Credito_Entity;
import Backend.Tingeso.Backend.Repository.Solicitud_Credito_Repository;
import Backend.Tingeso.Backend.Service.Evaluacion_Credito_service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class EvaluacionCreditoControllerTest {

    @Mock
    private Evaluacion_Credito_service evaluacion_credito_service;

    @Mock
    private Solicitud_Credito_Repository solicitud_credito_repository;

    @InjectMocks
    private Evaluacion_Credito_Controller evaluacion_credito_controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test para obtener la evaluación de crédito exitosamente
    @Test
    public void testGetEvaluacionCredito_Success() {
        // Arrange: Configurar la solicitud y evaluación de crédito simuladas
        Solicitud_Credito_Entity solicitud = new Solicitud_Credito_Entity();
        solicitud.setId_evaluacion_credito(1);

        Evaluacion_Credito_Entity evaluacion = new Evaluacion_Credito_Entity();
        evaluacion.setR1(true);

        when(solicitud_credito_repository.findById(1)).thenReturn(solicitud);
        when(evaluacion_credito_service.getEvaluacion_Credito(1)).thenReturn(evaluacion);

        // Act: Llamar al método a probar
        ResponseEntity<Evaluacion_Credito_Entity> response = evaluacion_credito_controller.getEvaluacionCredito(1);

        // Assert: Verificar que la respuesta sea correcta
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(evaluacion, response.getBody());

        // Verificar que se llamó al servicio para obtener la evaluación de crédito por ID
        verify(solicitud_credito_repository, times(1)).findById(1);
        verify(evaluacion_credito_service, times(1)).getEvaluacion_Credito(1);
    }



}