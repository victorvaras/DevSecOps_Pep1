package Backend.Tingeso.Backend.Services;

import Backend.Tingeso.Backend.Entity.Solicitud_Credito_Entity;
import Backend.Tingeso.Backend.Entity.Evaluacion_Credito_Entity;
import Backend.Tingeso.Backend.Repository.Solicitud_Credito_Repository;
import Backend.Tingeso.Backend.Service.Evaluacion_Credito_service;
import Backend.Tingeso.Backend.Service.Solicitud_Credito_Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class Solicitud_Credito_ServiceTest {

    @Mock
    private Solicitud_Credito_Repository solicitud_Credito_Repository;

    @Mock
    private Evaluacion_Credito_service evaluacion_credito_service;

    @InjectMocks
    private Solicitud_Credito_Service solicitud_Credito_Service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test para getAllSolicitud_Credito
    @Test
    public void testGetAllSolicitudCredito() {
        // Crear una lista simulada (mock) de Solicitud_Credito_Entity
        Solicitud_Credito_Entity solicitud1 = new Solicitud_Credito_Entity();
        solicitud1.setId_solicitud_credito(1);
        Solicitud_Credito_Entity solicitud2 = new Solicitud_Credito_Entity();
        solicitud2.setId_solicitud_credito(2);

        List<Solicitud_Credito_Entity> mockSolicitudes = Arrays.asList(solicitud1, solicitud2);

        // Simular comportamiento del repositorio
        when(solicitud_Credito_Repository.findAll()).thenReturn(mockSolicitudes);

        // Ejecutar el método del servicio
        List<Solicitud_Credito_Entity> result = solicitud_Credito_Service.getAllSolicitud_Credito();

        // Verificar que el resultado no sea nulo y que contenga las entidades correctas
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId_solicitud_credito());
        assertEquals(2, result.get(1).getId_solicitud_credito());

        // Verificar que el repositorio fue llamado correctamente
        verify(solicitud_Credito_Repository, times(1)).findAll();
    }

    // Test para getSolicitud_Credito cuando la entidad es encontrada
    @Test
    public void testGetSolicitudCredito_EntityFound() {
        // Crear un objeto simulado (mock) de Solicitud_Credito_Entity
        Solicitud_Credito_Entity mockSolicitud = new Solicitud_Credito_Entity();
        mockSolicitud.setId_solicitud_credito(1);

        // Simular comportamiento del repositorio
        when(solicitud_Credito_Repository.findById(1)).thenReturn(mockSolicitud);

        // Ejecutar el método del servicio
        Solicitud_Credito_Entity result = solicitud_Credito_Service.getSolicitud_Credito(1);

        // Verificar que el resultado no sea nulo y que los valores sean correctos
        assertNotNull(result);
        assertEquals(1, result.getId_solicitud_credito());

        // Verificar que el repositorio fue llamado correctamente
        verify(solicitud_Credito_Repository, times(1)).findById(1);
    }

    // Test para getSolicitudCredito cuando la entidad no es encontrada (retorna null)
    @Test
    public void testGetSolicitudCredito_EntityNotFound() {
        // Simular que el repositorio no encuentra el ID solicitado (retorna null)
        when(solicitud_Credito_Repository.findById(2)).thenReturn(null);

        // Ejecutar el método del servicio y verificar que retorna null
        Solicitud_Credito_Entity result = solicitud_Credito_Service.getSolicitud_Credito(2);

        assertNull(result);

        // Verificar que el repositorio fue llamado correctamente
        verify(solicitud_Credito_Repository, times(1)).findById(2);
    }

    // Test para nuevaSolicitudCredito (creación de una nueva solicitud con evaluación)
    @Test
    public void testNuevaSolicitudCredito() {
        // Crear un objeto simulado (mock) de Evaluacion_Credito y Solicitud_Credito
        Evaluacion_Credito_Entity mockEvaluacion = new Evaluacion_Credito_Entity();
        mockEvaluacion.setId_evaluacion_credito(10);

        Solicitud_Credito_Entity mockSolicitud = new Solicitud_Credito_Entity();

        // Simular comportamiento del servicio de evaluación y repositorio
        when(evaluacion_credito_service.createEvaluacion_Credito()).thenReturn(mockEvaluacion);

        when(solicitud_Credito_Repository.save(mockSolicitud)).thenReturn(mockSolicitud);

        // Ejecutar el método del servicio
        Solicitud_Credito_Entity result = solicitud_Credito_Service.nuevaSolicitud_Credito(mockSolicitud);

        // Verificar que la evaluación fue asignada correctamente a la solicitud y se guardó correctamente en el repositorio.
        assertNotNull(result);
        assertEquals(10, result.getId_evaluacion_credito());

        verify(evaluacion_credito_service, times(1)).createEvaluacion_Credito();

        verify(solicitud_Credito_Repository, times(1)).save(mockSolicitud);
    }



    // Test para updateSolicitudCreditoSeguimiento cuando la entidad no es encontrada.
    @Test
    public void testUpdateSolicitudCreditoSeguimiento_NotFound() {
        // Simular comportamiento del repositorio (no encontrar por ID)
        when(solicitud_Credito_Repository.findById(anyInt())).thenReturn(null);

        // Ejecutar el método con un ID inexistente y verificar que retorna null.
        Solicitud_Credito_Entity result = solicitud_Credito_Service.updateSolicitud_Credito_SeguimientoSolicitud(999, 2);

        assertNull(result);

        verify(solicitud_Credito_Repository, times(1)).findById(anyInt());
        verify(solicitud_Credito_Repository, times(0)).save(any(Solicitud_Credito_Entity.class));
    }
}