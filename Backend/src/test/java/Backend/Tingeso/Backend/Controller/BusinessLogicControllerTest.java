package Backend.Tingeso.Backend.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import Backend.Tingeso.Backend.Controller.business_Logic_Controller;
import Backend.Tingeso.Backend.Service.Business_Logic_Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class BusinessLogicControllerTest {

    @Mock
    private Business_Logic_Service business_LogicService;

    @InjectMocks
    private business_Logic_Controller business_logic_controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test para validar R1 exitosamente
    @Test
    public void testValidateR1_Success() {
        // Arrange: Configurar el comportamiento simulado del servicio
        when(business_LogicService.validate_R1(50000, 1)).thenReturn(true);

        // Act: Llamar al método a probar
        boolean result = business_logic_controller.validate_R1(50000, 1);

        // Assert: Verificar que el resultado sea true
        assertTrue(result);

        // Verificar que se llamó al servicio para validar R1
        verify(business_LogicService, times(2)).validate_R1(50000, 1);  // Se llama dos veces en el controlador
    }

    @Test
    public void testValidateR2_Success() {
        // Arrange: Configurar el comportamiento simulado del servicio
        when(business_LogicService.validate_R2(1, true)).thenReturn(true);

        // Act: Llamar al método a probar
        boolean result = business_logic_controller.validate_R2(1, true);

        // Assert: Verificar que el resultado sea true
        assertTrue(result);

        // Verificar que se llamó al servicio para validar R2
        verify(business_LogicService, times(1)).validate_R2(1, true);
    }

    @Test
    public void testValidateR3_Success() {
        // Arrange: Configurar el comportamiento simulado del servicio
        when(business_LogicService.validate_R3(1, true)).thenReturn(true);

        // Act: Llamar al método a probar
        boolean result = business_logic_controller.validate_R3(1, true);

        // Assert: Verificar que el resultado sea true
        assertTrue(result);

        // Verificar que se llamó al servicio para validar R3
        verify(business_LogicService, times(1)).validate_R3(1, true);
    }

    @Test
    public void testValidateR4_Success() {
        // Arrange: Configurar el comportamiento simulado del servicio
        when(business_LogicService.validate_R4(1, 50000, 20000)).thenReturn(true);

        // Act: Llamar al método a probar
        boolean result = business_logic_controller.validate_R4(1, 50000, 20000);

        // Assert: Verificar que el resultado sea true
        assertTrue(result);

        // Verificar que se llamó al servicio para validar R4
        verify(business_LogicService, times(1)).validate_R4(1, 50000, 20000);
    }

    // Test para validar R5 exitosamente
    @Test
    public void testValidateR5_Success() {
        // Arrange: Configurar el comportamiento simulado del servicio
        when(business_LogicService.validate_R5(1, 200000)).thenReturn(true);

        // Act: Llamar al método a probar
        boolean result = business_logic_controller.validate_R5(1, 200000);

        // Assert: Verificar que el resultado sea true
        assertTrue(result);

        // Verificar que se llamó al servicio para validar R5
        verify(business_LogicService, times(1)).validate_R5(1, 200000);
    }

    @Test
    public void testValidateR6_Success() {
        // Arrange: Configurar el comportamiento simulado del servicio
        when(business_LogicService.validate_R6(1, 30)).thenReturn(true);

        // Act: Llamar al método a probar
        boolean result = business_logic_controller.validate_R6(1, 30);

        // Assert: Verificar que el resultado sea true
        assertTrue(result);

        // Verificar que se llamó al servicio para validar R6
        verify(business_LogicService, times(1)).validate_R6(1, 30);
    }

    @Test
    public void testValidateR7_Success() {
        // Arrange: Configurar el comportamiento simulado del servicio
        when(business_LogicService.validate_R7(1)).thenReturn("solida");

        // Act: Llamar al método a probar
        String result = business_logic_controller.validate_R7(1);

        // Assert: Verificar que el resultado sea "solida"
        assertEquals("solida", result);

        // Verificar que se llamó al servicio para validar R7
        verify(business_LogicService, times(1)).validate_R7(1);
    }

    // Test para validar R71 exitosamente
    @Test
    public void testValidateR71_Success() {
        // Arrange: Configurar el comportamiento simulado del servicio
        when(business_LogicService.validate_R71(1, 50000)).thenReturn(true);

        // Act: Llamar al método a probar
        boolean result = business_logic_controller.validate_R71(1, 50000);

        // Assert: Verificar que el resultado sea true
        assertTrue(result);

        // Verificar que se llamó al servicio para validar R71
        verify(business_LogicService, times(1)).validate_R71(1, 50000);
    }

    @Test
    public void testValidateR72_Success() {
        // Arrange: Configurar el comportamiento simulado del servicio
        when(business_LogicService.validate_R72(1, 50000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000)).thenReturn(true);

        // Act: Llamar al método a probar
        boolean result = business_logic_controller.validate_R72(1, 50000, 1000, 1000, 1000, 1000, 1000, 1000,
                1000, 1000, 1000, 1000, 1000, 1000);

        // Assert: Verificar que el resultado sea true
        assertTrue(result);

        // Verificar que se llamó al servicio para validar R72
        verify(business_LogicService, times(1)).validate_R72(1, 50000, 1000, 1000,
                1000, 1000, 1000,
                1000, 1000,
                1000,
                1000,
                1000,
                1000,1000);
    }

    // Test para validar R73 exitosamente
    @Test
    public void testValidateR73_Success() {
        // Arrange: Configurar el comportamiento simulado del servicio
        when(business_LogicService.validate_R73(1, 50000,
                5000, 5000, 5000,
                5000, 5000, 5000,
                5000, 5000, 5000,
                5000, 5000, 5000)).thenReturn(true);

        // Act: Llamar al método a probar
        boolean result = business_logic_controller.validate_R73(1,
                50000,
                5000, 5000, 5000,
                5000, 5000, 5000,
                5000, 5000, 5000,
                5000, 5000, 5000);

        // Assert: Verificar que el resultado sea true
        assertTrue(result);

        // Verificar que se llamó al servicio para validar R73
        verify(business_LogicService).validate_R73(1,
                50000,
                // Meses simulados
                5000,
                5000,
                5000,
                5000,
                5000,
                5000,
                5000,5000,5000,5000,5000,5000

        );
    }

    // Test para validar R74 exitosamente
    @Test
    public void testValidateR74_Success() {
        // Arrange: Configurar el comportamiento simulado del servicio
        when(business_LogicService.validate_R74(1, 3, 50000)).thenReturn(true);

        // Act: Llamar al método a probar
        boolean result = business_logic_controller.validate_R74(1, 3, 50000);

        // Assert: Verificar que el resultado sea true
        assertTrue(result);

        // Verificar que se llamó al servicio para validar R74
        verify(business_LogicService, times(1)).validate_R74(1, 3, 50000);
    }

    @Test
    public void testValidateR75_Success() {
        // Arrange: Configurar el comportamiento simulado del servicio
        when(business_LogicService.validate_R75(1, 50000, 1000, 1000, 1000, 1000, 1000, 1000)).thenReturn(true);

        // Act: Llamar al método a probar
        boolean result = business_logic_controller.validate_R75(1,
                50000,
                1000,
                1000,
                1000,
                1000,
                1000,
                1000);

        // Assert: Verificar que el resultado sea true
        assertTrue(result);

        // Verificar que se llamó al servicio para validar R75
        verify(business_LogicService, times(1)).validate_R75(1,
                50000,
                1000,
                1000,
                1000,
                1000,
                1000,
                1000);
    }

    // Test para validar la evaluación de crédito exitosamente
    @Test
    public void testValidarEvaluacionCredito_Success() {
        // Arrange: Configurar el comportamiento simulado del servicio
        when(business_LogicService.validarEvaluacionCredito(1)).thenReturn("solida");

        // Act: Llamar al método a probar
        String result = business_logic_controller.validarEvaluacionCredito(1);

        // Assert: Verificar que el resultado sea "solida"
        assertEquals("solida", result);

        // Verificar que se llamó al servicio para validar la evaluación de crédito
        verify(business_LogicService, times(1)).validarEvaluacionCredito(1);
    }



}