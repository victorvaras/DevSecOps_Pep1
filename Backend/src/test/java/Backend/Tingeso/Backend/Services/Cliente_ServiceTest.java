package Backend.Tingeso.Backend.Services;

import Backend.Tingeso.Backend.Entity.Cliente_Entity;
import Backend.Tingeso.Backend.Repository.Cliente_Repository;
import Backend.Tingeso.Backend.Service.Cliente_service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class Cliente_ServiceTest {

    @Mock
    private Cliente_Repository cliente_repository;

    @InjectMocks
    private Cliente_service cliente_service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test para getClienteById
    @Test
    public void testGetClienteById_EntityFound() {
        // Crear un objeto simulado (mock) de Cliente_Entity
        Cliente_Entity mockCliente = new Cliente_Entity();
        mockCliente.setId_cliente(1);
        mockCliente.setRut(12345678);
        mockCliente.setNombre("Juan");

        // Simular comportamiento del repositorio
        when(cliente_repository.findById(1)).thenReturn(mockCliente);

        // Ejecutar el método del servicio
        Cliente_Entity result = cliente_service.getClienteById(1);

        // Verificar que el resultado no sea nulo y que los valores sean correctos
        assertNotNull(result);
        assertEquals(1, result.getId_cliente());
        assertEquals("Juan", result.getNombre());

        // Verificar que el repositorio fue llamado correctamente
        verify(cliente_repository, times(1)).findById(1);
    }

    // Test para getClienteByRut
    @Test
    public void testGetClienteByRut_EntityFound() {
        // Crear un objeto simulado (mock) de Cliente_Entity
        Cliente_Entity mockCliente = new Cliente_Entity();
        mockCliente.setRut(12345678);

        // Simular comportamiento del repositorio
        when(cliente_repository.findByRut(12345678)).thenReturn(mockCliente);

        // Ejecutar el método del servicio
        Cliente_Entity result = cliente_service.getClienteByRut(12345678);

        // Verificar que el resultado no sea nulo y que los valores sean correctos
        assertNotNull(result);
        assertEquals(12345678, result.getRut());

        // Verificar que el repositorio fue llamado correctamente
        verify(cliente_repository, times(1)).findByRut(12345678);
    }

    // Test para createCliente
    @Test
    public void testCreateCliente_Success() {
        // Crear un objeto simulado (mock) de Cliente_Entity a guardar
        Cliente_Entity mockCliente = new Cliente_Entity();

        // Simular comportamiento del repositorio al guardar el cliente
        when(cliente_repository.save(mockCliente)).thenReturn(mockCliente);

        // Ejecutar el método del servicio
        Cliente_Entity result = cliente_service.createCliente(mockCliente);

        // Verificar que el resultado no sea nulo y que se guardó correctamente
        assertNotNull(result);

        // Verificar que el repositorio fue llamado correctamente para guardar la entidad
        verify(cliente_repository, times(1)).save(mockCliente);
    }

    // Test para LoginCliente - éxito en autenticación
    @Test
    public void testLoginCliente_Success() {
        // Crear un objeto simulado (mock) de Cliente_Entity con credenciales correctas
        Cliente_Entity mockCliente = new Cliente_Entity();
        mockCliente.setRut(12345678);
        mockCliente.setContrasenia("password123");

        // Simular comportamiento del repositorio al buscar por RUT
        when(cliente_repository.findByRut(12345678)).thenReturn(mockCliente);

        // Ejecutar el método del servicio con credenciales correctas
        int result = cliente_service.LoginCliente(12345678, "password123");

        // Verificar que se devuelve el ID correcto del cliente autenticado
        assertEquals(mockCliente.getId_cliente(), result);

        // Verificar que el repositorio fue llamado correctamente para buscar por RUT
        verify(cliente_repository, times(1)).findByRut(12345678);
    }

    // Test para LoginCliente - fallo en autenticación (contraseña incorrecta)
    @Test
    public void testLoginCliente_Failure_WrongPassword() {
        // Crear un objeto simulado (mock) de Cliente_Entity con credenciales incorrectas
        Cliente_Entity mockCliente = new Cliente_Entity();
        mockCliente.setRut(12345678);
        mockCliente.setContrasenia("password123");

        // Simular comportamiento del repositorio al buscar por RUT
        when(cliente_repository.findByRut(12345678)).thenReturn(mockCliente);

        // Ejecutar el método del servicio con una contraseña incorrecta
        int result = cliente_service.LoginCliente(12345678, "wrongpassword");

        // Verificar que se devuelve 0 cuando la autenticación falla por contraseña incorrecta
        assertEquals(0, result);

        // Verificar que el repositorio fue llamado correctamente para buscar por RUT
        verify(cliente_repository, times(1)).findByRut(12345678);
    }
}