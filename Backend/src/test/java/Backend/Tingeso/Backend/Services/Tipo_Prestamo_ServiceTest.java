package Backend.Tingeso.Backend.Services;

import Backend.Tingeso.Backend.Entity.Tipo_Prestamo_Entity;
import Backend.Tingeso.Backend.Repository.Tipo_Prestamo_Repository;
import Backend.Tingeso.Backend.Service.Tipo_Prestamo_Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class Tipo_Prestamo_ServiceTest {

    @Mock
    private Tipo_Prestamo_Repository tipo_prestamo_repository;

    @InjectMocks
    private Tipo_Prestamo_Service tipo_prestamo_service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetTipoPrestamo_EntityFound() {
        // Crear un objeto simulado (mock) de Tipo_Prestamo_Entity
        Tipo_Prestamo_Entity mockTipoPrestamo = new Tipo_Prestamo_Entity();
        mockTipoPrestamo.setId_Tipo_Prestamo(1);
        mockTipoPrestamo.setNombreTipo_Prestamo("Préstamo Hipotecario");

        // Simular comportamiento del repositorio
        when(tipo_prestamo_repository.findById(1)).thenReturn(Optional.of(mockTipoPrestamo));

        // Ejecutar el método del servicio
        Tipo_Prestamo_Entity result = tipo_prestamo_service.getTipo_prestamo(1);

        // Verificar que el resultado no sea nulo y que los valores sean correctos
        assertNotNull(result);
        assertEquals(1, result.getId_Tipo_Prestamo());
        assertEquals("Préstamo Hipotecario", result.getNombreTipo_Prestamo());

        // Verificar que el repositorio fue llamado correctamente
        verify(tipo_prestamo_repository, times(1)).findById(1);
    }

    @Test
    public void testGetTipoPrestamo_EntityNotFound() {
        // Simular que el repositorio no encuentra el ID solicitado
        when(tipo_prestamo_repository.findById(2)).thenReturn(Optional.empty());

        // Ejecutar el método y capturar la excepción lanzada
        Exception exception = assertThrows(RuntimeException.class, () -> {
            tipo_prestamo_service.getTipo_prestamo(2);
        });

        // Verificar que se lanzó una excepción de tipo NoSuchElementException (o cualquier otra esperada)
        assertTrue(exception instanceof NoSuchElementException);

        // Verificar que el repositorio fue llamado correctamente
        verify(tipo_prestamo_repository, times(1)).findById(2);
    }
}
