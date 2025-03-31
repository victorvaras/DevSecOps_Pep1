package Backend.Tingeso.Backend.Services;

import Backend.Tingeso.Backend.Entity.Evaluacion_Credito_Entity;
import Backend.Tingeso.Backend.Repository.Evaluacion_Credito_Repository;
import Backend.Tingeso.Backend.Service.Evaluacion_Credito_service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class Evaluacion_Credito_ServiceTest {

    @Mock
    private Evaluacion_Credito_Repository evaluacion_credito_repository;

    @InjectMocks
    private Evaluacion_Credito_service evaluacion_credito_service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test para getEvaluacion_Credito cuando la entidad es encontrada
    @Test
    public void testGetEvaluacionCredito_EntityFound() {
        // Crear un objeto simulado (mock) de Evaluacion_Credito_Entity
        Evaluacion_Credito_Entity mockEvaluacion = new Evaluacion_Credito_Entity();
        mockEvaluacion.setId_evaluacion_credito(1);
        mockEvaluacion.setCredito_aceptado(true);

        // Simular comportamiento del repositorio
        when(evaluacion_credito_repository.findById(1)).thenReturn(Optional.of(mockEvaluacion));

        // Ejecutar el método del servicio
        Evaluacion_Credito_Entity result = evaluacion_credito_service.getEvaluacion_Credito(1);

        // Verificar que el resultado no sea nulo y que los valores sean correctos
        assertNotNull(result);
        assertEquals(1, result.getId_evaluacion_credito());
        assertTrue(result.isCredito_aceptado());

        // Verificar que el repositorio fue llamado correctamente
        verify(evaluacion_credito_repository, times(1)).findById(1);
    }

    // Test para getEvaluacion_Credito cuando la entidad no es encontrada (lanzar excepción)
    @Test
    public void testGetEvaluacionCredito_EntityNotFound() {
        // Simular que el repositorio no encuentra el ID solicitado
        when(evaluacion_credito_repository.findById(2)).thenReturn(Optional.empty());

        // Ejecutar el método y capturar la excepción lanzada
        Exception exception = assertThrows(RuntimeException.class, () -> {
            evaluacion_credito_service.getEvaluacion_Credito(2);
        });

        // Verificar que se lanzó una excepción de tipo NoSuchElementException (u otra esperada)
        assertTrue(exception instanceof NoSuchElementException);

        // Verificar que el repositorio fue llamado correctamente
        verify(evaluacion_credito_repository, times(1)).findById(2);
    }

    // Test para createEvaluacion_Credito (creación exitosa)
    @Test
    public void testCreateEvaluacionCredito_Success() {
        // Crear un objeto simulado (mock) de Evaluacion_Credito_Entity a guardar
        Evaluacion_Credito_Entity mockNuevo = new Evaluacion_Credito_Entity();

        // Simular comportamiento del repositorio al guardar la nueva evaluación de crédito
        when(evaluacion_credito_repository.save(any(Evaluacion_Credito_Entity.class))).thenReturn(mockNuevo);

        // Ejecutar el método del servicio para crear una nueva evaluación de crédito
        Evaluacion_Credito_Entity result = evaluacion_credito_service.createEvaluacion_Credito();

        // Verificar que el resultado no sea nulo y que se guardó correctamente
        assertNotNull(result);

        // Verificar que el repositorio fue llamado correctamente para guardar la nueva entidad
        verify(evaluacion_credito_repository, times(1)).save(any(Evaluacion_Credito_Entity.class));
    }
}