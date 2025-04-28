package Backend.Tingeso.Backend.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import Backend.Tingeso.Backend.Entity.Simulacion_Credito_Entity;
import Backend.Tingeso.Backend.Repository.Simulacion_Credito_Repository;
import Backend.Tingeso.Backend.Service.Business_Logic_Service;
import Backend.Tingeso.Backend.Service.Simulacion_Credito_Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class Simulacion_Credito_ServiceTest {

    @Mock
    private Simulacion_Credito_Repository simulacion_credito_repository;

    @Mock
    private Business_Logic_Service business_logic_service;

    @InjectMocks
    private Simulacion_Credito_Service simulacion_credito_service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testNewSimulacionCredito() {
        // Arrange: Configurar la simulación de crédito y el valor de la cuota calculada
        Simulacion_Credito_Entity simulacionCredito = new Simulacion_Credito_Entity();
        simulacionCredito.setMonto_deseado(100000);  // Monto deseado de 100,000
        simulacionCredito.setPlazo_deseado(12);      // Plazo deseado de 12 meses

        int valorCuotaCalculado = 5000;  // Valor de cuota calculado por el servicio de lógica de negocio

        // Simular el comportamiento del método monthly_fee_calculation en Business_Logic_Service
        when(business_logic_service.monthly_fee_calculation(simulacionCredito)).thenReturn(valorCuotaCalculado);

        // Simular el comportamiento del repositorio al guardar la entidad
        when(simulacion_credito_repository.save(simulacionCredito)).thenReturn(simulacionCredito);

        // Act: Llamar al método a probar
        Simulacion_Credito_Entity resultado = simulacion_credito_service.newSimulacionCredito(simulacionCredito);

        // Assert: Verificar que el valor de la cuota fue establecido correctamente y que se guardó la entidad
        assertEquals(valorCuotaCalculado, resultado.getValor_cuota());

        // Verificar que se llamó al método save del repositorio con la entidad modificada
        verify(simulacion_credito_repository, times(1)).save(simulacionCredito);

        // Verificar que se llamó al método monthly_fee_calculation en Business_Logic_Service con la entidad correcta
        verify(business_logic_service, times(1)).monthly_fee_calculation(simulacionCredito);
    }
}