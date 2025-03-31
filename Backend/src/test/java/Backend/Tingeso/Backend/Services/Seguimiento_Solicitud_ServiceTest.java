package Backend.Tingeso.Backend.Services;

import Backend.Tingeso.Backend.Entity.Seguimiento_Solicitud_Entity;
import Backend.Tingeso.Backend.Repository.Seguimiento_Solicitud_Repository;
import Backend.Tingeso.Backend.Service.Seguimiento_Solicitud_Service;
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
public class Seguimiento_Solicitud_ServiceTest {

    @Mock
    private Seguimiento_Solicitud_Repository seguimiento_Solicitud_Repository;

    @InjectMocks
    private Seguimiento_Solicitud_Service seguimiento_Solicitud_Service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test para getSeguimiento_Solicitud cuando la entidad es encontrada
    @Test
    public void testGetSeguimientoSolicitud_EntityFound() {
        // Crear un objeto simulado (mock) de Seguimiento_Solicitud_Entity
        Seguimiento_Solicitud_Entity mockSolicitud = new Seguimiento_Solicitud_Entity();
        mockSolicitud.setId_seguimiento_solicitud(1);
        mockSolicitud.setNombre_seguimiento_solicitud("Solicitud Aprobada");

        // Simular comportamiento del repositorio
        when(seguimiento_Solicitud_Repository.findById(1)).thenReturn(Optional.of(mockSolicitud));

        // Ejecutar el método del servicio
        Seguimiento_Solicitud_Entity result = seguimiento_Solicitud_Service.getSeguimiento_Solicitud(1);

        // Verificar que el resultado no sea nulo y que los valores sean correctos
        assertNotNull(result);
        assertEquals(1, result.getId_seguimiento_solicitud());
        assertEquals("Solicitud Aprobada", result.getNombre_seguimiento_solicitud());

        // Verificar que el repositorio fue llamado correctamente
        verify(seguimiento_Solicitud_Repository, times(1)).findById(1);
    }

    // Test para getSeguimiento_Solicitud cuando la entidad no es encontrada (lanzar excepción)
    @Test
    public void testGetSeguimientoSolicitud_EntityNotFound() {
        // Simular que el repositorio no encuentra el ID solicitado
        when(seguimiento_Solicitud_Repository.findById(2)).thenReturn(Optional.empty());

        // Ejecutar el método y capturar la excepción lanzada
        Exception exception = assertThrows(RuntimeException.class, () -> {
            seguimiento_Solicitud_Service.getSeguimiento_Solicitud(2);
        });

        // Verificar que se lanzó una excepción de tipo NoSuchElementException (u otra esperada)
        assertTrue(exception instanceof NoSuchElementException);

        // Verificar que el repositorio fue llamado correctamente
        verify(seguimiento_Solicitud_Repository, times(1)).findById(2);
    }
}