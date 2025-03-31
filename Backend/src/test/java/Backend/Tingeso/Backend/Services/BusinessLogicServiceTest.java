package Backend.Tingeso.Backend.Services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import Backend.Tingeso.Backend.Entity.Evaluacion_Credito_Entity;
import Backend.Tingeso.Backend.Entity.Simulacion_Credito_Entity;
import Backend.Tingeso.Backend.Entity.Solicitud_Credito_Entity;
import Backend.Tingeso.Backend.Entity.Tipo_Prestamo_Entity;
import Backend.Tingeso.Backend.Repository.Evaluacion_Credito_Repository;
import Backend.Tingeso.Backend.Repository.Simulacion_Credito_Repository;
import Backend.Tingeso.Backend.Repository.Solicitud_Credito_Repository;
import Backend.Tingeso.Backend.Repository.Tipo_Prestamo_Repository;
import Backend.Tingeso.Backend.Service.Business_Logic_Service;
import Backend.Tingeso.Backend.Service.Tipo_Prestamo_Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

public class BusinessLogicServiceTest {

    @Mock
    private Simulacion_Credito_Repository simulacion_credito_repository;

    @Mock
    private Tipo_Prestamo_Repository tipo_prestamo_repository;

    @Mock
    private Solicitud_Credito_Repository solicitud_credito_repository;

    @Mock
    private Evaluacion_Credito_Repository evaluacion_credito_repository;

    @Mock
    private Tipo_Prestamo_Service tipo_prestamo_service;

    @InjectMocks
    private Business_Logic_Service business_logic_service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testMonthlyFeeCalculation() {
        // Arrange: Configurar la simulación y el tipo de préstamo
        Simulacion_Credito_Entity simulacion = new Simulacion_Credito_Entity();
        simulacion.setId_Tipo_Prestamo(1);
        simulacion.setMonto_deseado(100000);  // Monto deseado de 100,000
        simulacion.setPlazo_deseado(5);  // Plazo deseado de 5 años

        Tipo_Prestamo_Entity tipoPrestamo = new Tipo_Prestamo_Entity();
        tipoPrestamo.setTasa_anual(12);  // Tasa anual del 12%

        when(tipo_prestamo_repository.getById(1)).thenReturn(tipoPrestamo);

        // Act: Llamar al método a probar
        int resultado = business_logic_service.monthly_fee_calculation(simulacion);

        // Assert: Verificar que el resultado sea correcto
        double tasa_mensual = 12.0 / 12 / 100;  // Tasa mensual del 1%
        int plazo = 5 * 12;  // Plazo en meses (5 años)
        double montoEsperado = (100000 * tasa_mensual * Math.pow((1 + tasa_mensual), plazo)) /
                (Math.pow((1 + tasa_mensual), plazo) - 1);

        assertEquals((int) montoEsperado, resultado);

        // Verificar que el método getById del repositorio fue llamado correctamente
        verify(tipo_prestamo_repository, times(1)).getById(1);
    }

    @Test
    public void testValidateR1_RelacionMenorOIgual35() {
        // Arrange: Configurar la simulación de la solicitud y evaluación de crédito
        Solicitud_Credito_Entity solicitud = new Solicitud_Credito_Entity();
        solicitud.setMonto_deseado(100000);  // Monto deseado de 100,000
        solicitud.setPlazo_deseado(12);  // Plazo deseado de 12 meses (1 año)
        solicitud.setId_Tipo_Prestamo(1);
        solicitud.setId_evaluacion_credito(1);

        Tipo_Prestamo_Entity tipoPrestamo = new Tipo_Prestamo_Entity();
        tipoPrestamo.setTasa_anual(12);  // Tasa anual del 12%

        Evaluacion_Credito_Entity evaluacion = new Evaluacion_Credito_Entity();

        when(solicitud_credito_repository.findById(1)).thenReturn(solicitud);
        when(tipo_prestamo_service.getTipo_prestamo(1)).thenReturn(tipoPrestamo);
        when(evaluacion_credito_repository.findById(1)).thenReturn(java.util.Optional.of(evaluacion));

        int ingresos_cliente = 50000;  // Ingresos del cliente

        // Act: Llamar al método a probar
        boolean resultado = business_logic_service.validate_R1(ingresos_cliente, 1);

        // Assert: Verificar que el resultado sea true cuando la relación <= 35%
        assertTrue(resultado);

        // Verificar que se haya guardado la evaluación con R1 = true
        verify(evaluacion_credito_repository, times(1)).save(evaluacion);
        assertTrue(evaluacion.isR1());
    }

    @Test
    public void testValidateR1_RelacionMayor35() {
        // Arrange: Configurar la simulación de la solicitud y evaluación de crédito
        Solicitud_Credito_Entity solicitud = new Solicitud_Credito_Entity();
        solicitud.setMonto_deseado(200000);  // Monto deseado de 200,000
        solicitud.setPlazo_deseado(12);  // Plazo deseado de 12 meses (1 año)
        solicitud.setId_Tipo_Prestamo(1);
        solicitud.setId_evaluacion_credito(2);

        Tipo_Prestamo_Entity tipoPrestamo = new Tipo_Prestamo_Entity();
        tipoPrestamo.setTasa_anual(15);  // Tasa anual del 15%

        Evaluacion_Credito_Entity evaluacion = new Evaluacion_Credito_Entity();

        when(solicitud_credito_repository.findById(2)).thenReturn(solicitud);
        when(tipo_prestamo_service.getTipo_prestamo(1)).thenReturn(tipoPrestamo);
        when(evaluacion_credito_repository.findById(2)).thenReturn(java.util.Optional.of(evaluacion));

        int ingresos_cliente = 30000;  // Ingresos del cliente

        // Act: Llamar al método a probar
        boolean resultado = business_logic_service.validate_R1(ingresos_cliente, 2);

        // Assert: Verificar que el resultado sea false cuando la relación > 35%
        assertFalse(resultado);

        // Verificar que se haya guardado la evaluación con R1 = false
        verify(evaluacion_credito_repository, times(1)).save(evaluacion);
        assertFalse(evaluacion.isR1());
    }

    @Test
    public void testValidateR2_RequisitoTrue() {
        // Arrange: Configurar la solicitud y evaluación de crédito simulada
        Solicitud_Credito_Entity solicitud = new Solicitud_Credito_Entity();
        solicitud.setId_evaluacion_credito(1);

        Evaluacion_Credito_Entity evaluacion = new Evaluacion_Credito_Entity();

        when(solicitud_credito_repository.findById(1)).thenReturn(solicitud);
        when(evaluacion_credito_repository.findById(1)).thenReturn(java.util.Optional.of(evaluacion));

        // Act: Llamar al método a probar con requisito = true
        boolean resultado = business_logic_service.validate_R2(1, true);

        // Assert: Verificar que el resultado sea true y que R2 se haya actualizado correctamente
        assertTrue(resultado);
        verify(evaluacion_credito_repository, times(1)).save(evaluacion);
        assertTrue(evaluacion.isR2());
    }

    @Test
    public void testValidateR2_RequisitoFalse() {
        // Arrange: Configurar la solicitud y evaluación de crédito simulada
        Solicitud_Credito_Entity solicitud = new Solicitud_Credito_Entity();
        solicitud.setId_evaluacion_credito(2);

        Evaluacion_Credito_Entity evaluacion = new Evaluacion_Credito_Entity();

        when(solicitud_credito_repository.findById(2)).thenReturn(solicitud);
        when(evaluacion_credito_repository.findById(2)).thenReturn(java.util.Optional.of(evaluacion));

        // Act: Llamar al método a probar con requisito = false
        boolean resultado = business_logic_service.validate_R2(2, false);

        // Assert: Verificar que el resultado sea false y que R2 se haya actualizado correctamente
        assertFalse(resultado);
        verify(evaluacion_credito_repository, times(1)).save(evaluacion);
        assertFalse(evaluacion.isR2());
    }

    @Test
    public void testValidateR3_RequisitoTrue() {
        // Arrange: Configurar la solicitud y evaluación de crédito simulada
        Solicitud_Credito_Entity solicitud = new Solicitud_Credito_Entity();
        solicitud.setId_evaluacion_credito(3);

        Evaluacion_Credito_Entity evaluacion = new Evaluacion_Credito_Entity();

        when(solicitud_credito_repository.findById(3)).thenReturn(solicitud);
        when(evaluacion_credito_repository.findById(3)).thenReturn(java.util.Optional.of(evaluacion));

        // Act: Llamar al método a probar con requisito = true
        boolean resultado = business_logic_service.validate_R3(3, true);

        // Assert: Verificar que el resultado sea true y que R3 se haya actualizado correctamente
        assertTrue(resultado);
        verify(evaluacion_credito_repository, times(1)).save(evaluacion);
        assertTrue(evaluacion.isR3());
    }

    @Test
    public void testValidateR3_RequisitoFalse() {
        // Arrange: Configurar la solicitud y evaluación de crédito simulada
        Solicitud_Credito_Entity solicitud = new Solicitud_Credito_Entity();
        solicitud.setId_evaluacion_credito(4);

        Evaluacion_Credito_Entity evaluacion = new Evaluacion_Credito_Entity();

        when(solicitud_credito_repository.findById(4)).thenReturn(solicitud);
        when(evaluacion_credito_repository.findById(4)).thenReturn(java.util.Optional.of(evaluacion));

        // Act: Llamar al método a probar con requisito = false
        boolean resultado = business_logic_service.validate_R3(4, false);

        // Assert: Verificar que el resultado sea false y que R3 se haya actualizado correctamente
        assertFalse(resultado);
        verify(evaluacion_credito_repository, times(1)).save(evaluacion);
        assertFalse(evaluacion.isR3());
    }

    @Test
    public void testValidateR4_RelacionMenorOIgual50() {
        // Arrange: Configurar la solicitud y evaluación de crédito simulada
        Solicitud_Credito_Entity solicitud = new Solicitud_Credito_Entity();
        solicitud.setCuota_mensual(10000); // Cuota mensual de 10,000
        solicitud.setId_evaluacion_credito(1);

        Evaluacion_Credito_Entity evaluacion = new Evaluacion_Credito_Entity();

        when(solicitud_credito_repository.findById(1)).thenReturn(solicitud);
        when(evaluacion_credito_repository.findById(1)).thenReturn(java.util.Optional.of(evaluacion));

        int ingresos_cliente = 50000;  // Ingresos del cliente
        int deuda_total = 10000;       // Deuda total

        // Act: Llamar al método a probar
        boolean resultado = business_logic_service.validate_R4(1, ingresos_cliente, deuda_total);

        // Assert: Verificar que el resultado sea true cuando la relación <= 50%
        assertTrue(resultado);

        // Verificar que se haya guardado la evaluación con R4 = true
        verify(evaluacion_credito_repository, times(1)).save(evaluacion);
        assertTrue(evaluacion.isR4());
    }

    @Test
    public void testValidateR4_RelacionMayor50() {
        // Arrange: Configurar la solicitud y evaluación de crédito simulada
        Solicitud_Credito_Entity solicitud = new Solicitud_Credito_Entity();
        solicitud.setCuota_mensual(30000); // Cuota mensual de 30,000
        solicitud.setId_evaluacion_credito(2);

        Evaluacion_Credito_Entity evaluacion = new Evaluacion_Credito_Entity();

        when(solicitud_credito_repository.findById(2)).thenReturn(solicitud);
        when(evaluacion_credito_repository.findById(2)).thenReturn(java.util.Optional.of(evaluacion));

        int ingresos_cliente = 50000;  // Ingresos del cliente
        int deuda_total = 25000;       // Deuda total

        // Act: Llamar al método a probar
        boolean resultado = business_logic_service.validate_R4(2, ingresos_cliente, deuda_total);

        // Assert: Verificar que el resultado sea false cuando la relación > 50%
        assertFalse(resultado);

        // Verificar que se haya guardado la evaluación con R4 = false
        verify(evaluacion_credito_repository, times(1)).save(evaluacion);
        assertFalse(evaluacion.isR4());
    }

    @Test
    public void testValidateR5_PorcentajeFinanciamientoMenorOIgualMaximo() {
        // Arrange: Configurar la solicitud y evaluación de crédito simulada
        Solicitud_Credito_Entity solicitud = new Solicitud_Credito_Entity();
        solicitud.setMonto_deseado(80000);  // Monto deseado de 80,000
        solicitud.setId_Tipo_Prestamo(1);
        solicitud.setId_evaluacion_credito(3);

        Tipo_Prestamo_Entity prestamo = new Tipo_Prestamo_Entity();
        prestamo.setPorcentaje_maximo_financiamiento(80);  // Máximo financiamiento del 80%

        Evaluacion_Credito_Entity evaluacion = new Evaluacion_Credito_Entity();

        when(solicitud_credito_repository.findById(3)).thenReturn(solicitud);
        when(tipo_prestamo_repository.findById(1)).thenReturn(java.util.Optional.of(prestamo));
        when(evaluacion_credito_repository.findById(3)).thenReturn(java.util.Optional.of(evaluacion));

        int valor_propiedad = 100000;  // Valor de la propiedad

        // Act: Llamar al método a probar con porcentaje de financiamiento menor o igual al máximo permitido
        boolean resultado = business_logic_service.validate_R5(3, valor_propiedad);

        // Assert: Verificar que el resultado sea true y que R5 se haya actualizado correctamente
        assertTrue(resultado);

        verify(evaluacion_credito_repository, times(1)).save(evaluacion);
        assertTrue(evaluacion.isR5());
    }

    @Test
    public void testValidateR5_PorcentajeFinanciamientoMayorMaximo() {
        // Arrange: Configurar la solicitud y evaluación de crédito simulada
        Solicitud_Credito_Entity solicitud = new Solicitud_Credito_Entity();
        solicitud.setMonto_deseado(90000);  // Monto deseado de 90,000
        solicitud.setId_Tipo_Prestamo(2);
        solicitud.setId_evaluacion_credito(4);

        Tipo_Prestamo_Entity prestamo = new Tipo_Prestamo_Entity();
        prestamo.setPorcentaje_maximo_financiamiento(70);  // Máximo financiamiento del 70%

        Evaluacion_Credito_Entity evaluacion = new Evaluacion_Credito_Entity();

        when(solicitud_credito_repository.findById(4)).thenReturn(solicitud);
        when(tipo_prestamo_repository.findById(2)).thenReturn(java.util.Optional.of(prestamo));
        when(evaluacion_credito_repository.findById(4)).thenReturn(java.util.Optional.of(evaluacion));

        int valor_propiedad = 100000;  // Valor de la propiedad

        // Act: Llamar al método a probar con porcentaje de financiamiento mayor al máximo permitido
        boolean resultado = business_logic_service.validate_R5(4, valor_propiedad);

        // Assert: Verificar que el resultado sea false y que R5 se haya actualizado correctamente
        assertFalse(resultado);

        verify(evaluacion_credito_repository, times(1)).save(evaluacion);
        assertFalse(evaluacion.isR5());
    }

    @Test
    public void testValidateR6_EdadFinalMenorOIgual70() {
        // Arrange: Configurar la solicitud y evaluación de crédito simulada
        Solicitud_Credito_Entity solicitud = new Solicitud_Credito_Entity();
        solicitud.setPlazo_deseado(10); // Plazo deseado de 10 años
        solicitud.setId_evaluacion_credito(1);

        Evaluacion_Credito_Entity evaluacion = new Evaluacion_Credito_Entity();

        when(solicitud_credito_repository.findById(1)).thenReturn(solicitud);
        when(evaluacion_credito_repository.findById(1)).thenReturn(java.util.Optional.of(evaluacion));

        int edad_cliente = 60;  // Edad del cliente

        // Act: Llamar al método a probar
        boolean resultado = business_logic_service.validate_R6(1, edad_cliente);

        // Assert: Verificar que el resultado sea true cuando la edad final <= 70
        assertTrue(resultado);

        // Verificar que se haya guardado la evaluación con R6 = true
        verify(evaluacion_credito_repository, times(1)).save(evaluacion);
        assertTrue(evaluacion.isR6());
    }

    @Test
    public void testValidateR6_EdadFinalMayor70() {
        // Arrange: Configurar la solicitud y evaluación de crédito simulada
        Solicitud_Credito_Entity solicitud = new Solicitud_Credito_Entity();
        solicitud.setPlazo_deseado(15); // Plazo deseado de 15 años
        solicitud.setId_evaluacion_credito(2);

        Evaluacion_Credito_Entity evaluacion = new Evaluacion_Credito_Entity();

        when(solicitud_credito_repository.findById(2)).thenReturn(solicitud);
        when(evaluacion_credito_repository.findById(2)).thenReturn(java.util.Optional.of(evaluacion));

        int edad_cliente = 60;  // Edad del cliente

        // Act: Llamar al método a probar
        boolean resultado = business_logic_service.validate_R6(2, edad_cliente);

        // Assert: Verificar que el resultado sea false cuando la edad final > 70
        assertFalse(resultado);

        // Verificar que se haya guardado la evaluación con R6 = false
        verify(evaluacion_credito_repository, times(1)).save(evaluacion);
        assertFalse(evaluacion.isR6());
    }

    @Test
    public void testValidateR7_Solida() {
        // Arrange: Configurar la solicitud y evaluación de crédito simulada
        Solicitud_Credito_Entity solicitud = new Solicitud_Credito_Entity();
        solicitud.setId_evaluacion_credito(3);

        Evaluacion_Credito_Entity evaluacion = new Evaluacion_Credito_Entity();
        evaluacion.setR1(true);
        evaluacion.setR2(true);
        evaluacion.setR3(true);
        evaluacion.setR4(true);
        evaluacion.setR5(true);

        when(solicitud_credito_repository.findById(3)).thenReturn(solicitud);
        when(evaluacion_credito_repository.findById(3)).thenReturn(java.util.Optional.of(evaluacion));

        // Act: Llamar al método a probar cuando todos los requisitos son true (solida)
        String resultado = business_logic_service.validate_R7(3);

        // Assert: Verificar que el resultado sea "solida"
        assertEquals("solida", resultado);

        verify(evaluacion_credito_repository, times(1)).save(evaluacion);
    }

    @Test
    public void testValidateR7_Moderada() {
        // Arrange: Configurar la solicitud y evaluación de crédito simulada
        Solicitud_Credito_Entity solicitud = new Solicitud_Credito_Entity();
        solicitud.setId_evaluacion_credito(4);

        Evaluacion_Credito_Entity evaluacion = new Evaluacion_Credito_Entity();
        evaluacion.setR1(true);
        evaluacion.setR2(true);
        evaluacion.setR3(true);
        evaluacion.setR4(false);  // Uno de los requisitos es false

        when(solicitud_credito_repository.findById(4)).thenReturn(solicitud);
        when(evaluacion_credito_repository.findById(4)).thenReturn(java.util.Optional.of(evaluacion));

        // Act: Llamar al método a probar cuando solo algunos requisitos son true (moderada)
        String resultado = business_logic_service.validate_R7(4);

        // Assert: Verificar que el resultado sea "moderada"
        assertEquals("moderada", resultado);

        verify(evaluacion_credito_repository, times(1)).save(evaluacion);
    }

    @Test
    public void testValidateR7_Insuficiente() {
        // Arrange: Configurar la solicitud y evaluación de crédito simulada
        Solicitud_Credito_Entity solicitud = new Solicitud_Credito_Entity();
        solicitud.setId_evaluacion_credito(5);

        Evaluacion_Credito_Entity evaluacion = new Evaluacion_Credito_Entity();
        evaluacion.setR1(false);  // Todos los requisitos son false
        evaluacion.setR2(false);
        evaluacion.setR3(false);
        evaluacion.setR4(false);
        evaluacion.setR5(false);

        when(solicitud_credito_repository.findById(5)).thenReturn(solicitud);
        when(evaluacion_credito_repository.findById(5)).thenReturn(java.util.Optional.of(evaluacion));

        // Act: Llamar al método a probar cuando ningún requisito es true (insuficiente)
        String resultado = business_logic_service.validate_R7(5);

        // Assert: Verificar que el resultado sea "insuficiente"
        assertEquals("insuficiente", resultado);

        verify(evaluacion_credito_repository, times(1)).save(evaluacion);
    }

    @Test
    public void testValidateR71_RelacionMayorOIgual10Porciento() {
        // Arrange: Configurar la solicitud y evaluación de crédito simulada
        Solicitud_Credito_Entity solicitud = new Solicitud_Credito_Entity();
        solicitud.setMonto_deseado(100000);  // Monto deseado de 100,000
        solicitud.setId_evaluacion_credito(1);

        Evaluacion_Credito_Entity evaluacion = new Evaluacion_Credito_Entity();

        when(solicitud_credito_repository.findById(1)).thenReturn(solicitud);
        when(evaluacion_credito_repository.findById(1)).thenReturn(java.util.Optional.of(evaluacion));

        int saldo_cuenta = 15000;  // Saldo de la cuenta

        // Act: Llamar al método a probar con relación >= 10%
        boolean resultado = business_logic_service.validate_R71(1, saldo_cuenta);

        // Assert: Verificar que el resultado sea true cuando la relación >= 10%
        assertTrue(resultado);

        // Verificar que se haya guardado la evaluación con R71 = true
        verify(evaluacion_credito_repository, times(1)).save(evaluacion);
        assertTrue(evaluacion.isR71());
    }

    @Test
    public void testValidateR71_RelacionMenor10Porciento() {
        // Arrange: Configurar la solicitud y evaluación de crédito simulada
        Solicitud_Credito_Entity solicitud = new Solicitud_Credito_Entity();
        solicitud.setMonto_deseado(100000);  // Monto deseado de 100,000
        solicitud.setId_evaluacion_credito(2);

        Evaluacion_Credito_Entity evaluacion = new Evaluacion_Credito_Entity();

        when(solicitud_credito_repository.findById(2)).thenReturn(solicitud);
        when(evaluacion_credito_repository.findById(2)).thenReturn(java.util.Optional.of(evaluacion));

        int saldo_cuenta = 5000;  // Saldo de la cuenta

        // Act: Llamar al método a probar con relación < 10%
        boolean resultado = business_logic_service.validate_R71(2, saldo_cuenta);

        // Assert: Verificar que el resultado sea false cuando la relación < 10%
        assertFalse(resultado);

        // Verificar que se haya guardado la evaluación con R71 = false
        verify(evaluacion_credito_repository, times(1)).save(evaluacion);
        assertFalse(evaluacion.isR71());
    }

    @Test
    public void testValidateR72_NoMesExcedeLimite() {
        // Arrange: Configurar la solicitud y evaluación de crédito simulada
        Solicitud_Credito_Entity solicitud = new Solicitud_Credito_Entity();
        solicitud.setId_evaluacion_credito(1);

        Evaluacion_Credito_Entity evaluacion = new Evaluacion_Credito_Entity();

        when(solicitud_credito_repository.findById(1)).thenReturn(solicitud);
        when(evaluacion_credito_repository.findById(1)).thenReturn(java.util.Optional.of(evaluacion));

        int saldo = 10000;  // Saldo de la cuenta
        int limite = saldo / 2;  // Limite es el 50% del saldo (5000)

        // Act: Llamar al método a probar con todos los meses dentro del límite
        boolean resultado = business_logic_service.validate_R72(1, saldo,
                limite - 100, limite - 200, limite - 300, limite - 400,
                limite - 500, limite - 600, limite - 700, limite - 800,
                limite - 900, limite - 1000, limite - 1100, limite - 1200);

        // Assert: Verificar que el resultado sea true cuando ningún mes excede el límite
        assertTrue(resultado);

        // Verificar que se haya guardado la evaluación con R72 = true
        verify(evaluacion_credito_repository, times(1)).save(evaluacion);
        assertTrue(evaluacion.isR72());
    }

    @Test
    public void testValidateR72_UnMesExcedeLimite() {
        // Arrange: Configurar la solicitud y evaluación de crédito simulada
        Solicitud_Credito_Entity solicitud = new Solicitud_Credito_Entity();
        solicitud.setId_evaluacion_credito(2);

        Evaluacion_Credito_Entity evaluacion = new Evaluacion_Credito_Entity();

        when(solicitud_credito_repository.findById(2)).thenReturn(solicitud);
        when(evaluacion_credito_repository.findById(2)).thenReturn(java.util.Optional.of(evaluacion));

        int saldo = 10000;  // Saldo de la cuenta
        int limite = saldo / 2;  // Limite es el 50% del saldo (5000)

        // Act: Llamar al método a probar con un mes que excede el límite
        boolean resultado = business_logic_service.validate_R72(2, saldo,
                limite + 100, limite - 200, limite - 300, limite - 400,
                limite - 500, limite - 600, limite - 700, limite - 800,
                limite - 900, limite - 1000, limite - 1100, limite - 1200);

        // Assert: Verificar que el resultado sea false cuando un mes excede el límite
        assertFalse(resultado);

        // Verificar que se haya guardado la evaluación con R72 = false
        verify(evaluacion_credito_repository, times(1)).save(evaluacion);
        assertFalse(evaluacion.isR72());
    }

    @Test
    public void testValidateR73_TodosMesesCumplen() {
        // Arrange: Configurar la solicitud y evaluación de crédito simulada
        Solicitud_Credito_Entity solicitud = new Solicitud_Credito_Entity();
        solicitud.setId_evaluacion_credito(1);

        Evaluacion_Credito_Entity evaluacion = new Evaluacion_Credito_Entity();

        when(solicitud_credito_repository.findById(1)).thenReturn(solicitud);
        when(evaluacion_credito_repository.findById(1)).thenReturn(java.util.Optional.of(evaluacion));

        int ingreso_mensual = 10000;  // Ingreso mensual del cliente
        double montoMinimo = ingreso_mensual * 0.05;  // Monto mínimo es el 5% del ingreso mensual (500)

        // Act: Llamar al método a probar con todos los meses cumpliendo con el monto mínimo
        boolean resultado = business_logic_service.validate_R73(1, ingreso_mensual,
                (int) montoMinimo + 100, (int) montoMinimo + 100, (int) montoMinimo + 100,
                (int) montoMinimo + 100, (int) montoMinimo + 100, (int) montoMinimo + 100,
                (int) montoMinimo + 100, (int) montoMinimo + 100, (int) montoMinimo + 100,
                (int) montoMinimo + 100, (int) montoMinimo + 100, (int) montoMinimo + 100);

        // Assert: Verificar que el resultado sea true cuando todos los meses cumplen con el mínimo
        assertTrue(resultado);

        // Verificar que se haya guardado la evaluación con R73 = true
        verify(evaluacion_credito_repository, times(1)).save(evaluacion);
        assertTrue(evaluacion.isR73());
    }

    @Test
    public void testValidateR73_TrimestresCumplen() {
        // Arrange: Configurar la solicitud y evaluación de crédito simulada
        Solicitud_Credito_Entity solicitud = new Solicitud_Credito_Entity();
        solicitud.setId_evaluacion_credito(2);

        Evaluacion_Credito_Entity evaluacion = new Evaluacion_Credito_Entity();

        when(solicitud_credito_repository.findById(2)).thenReturn(solicitud);
        when(evaluacion_credito_repository.findById(2)).thenReturn(java.util.Optional.of(evaluacion));

        int ingreso_mensual = 10000;  // Ingreso mensual del cliente
        double montoMinimo = ingreso_mensual * 0.05;  // Monto mínimo es el 5% del ingreso mensual (500)

        // Act: Llamar al método a probar con trimestres cumpliendo con el mínimo pero no todos los meses individualmente
        boolean resultado = business_logic_service.validate_R73(2, ingreso_mensual,
                (int) montoMinimo - 200, (int) montoMinimo - 200, (int) montoMinimo + 400,
                (int) montoMinimo - 200, (int) montoMinimo - 200, (int) montoMinimo + 400,
                (int) montoMinimo - 200, (int) montoMinimo - 200, (int) montoMinimo + 400,
                (int) montoMinimo - 200, (int) montoMinimo - 200, (int) montoMinimo + 400);

        // Assert: Verificar que el resultado sea true cuando los trimestres cumplen con el mínimo
        assertTrue(resultado);

        // Verificar que se haya guardado la evaluación con R73 = true
        verify(evaluacion_credito_repository, times(1)).save(evaluacion);
        assertTrue(evaluacion.isR73());
    }

    @Test
    public void testValidateR73_NingunaCondicionCumple() {
        // Arrange: Configurar la solicitud y evaluación de crédito simulada
        Solicitud_Credito_Entity solicitud = new Solicitud_Credito_Entity();
        solicitud.setId_evaluacion_credito(3);

        Evaluacion_Credito_Entity evaluacion = new Evaluacion_Credito_Entity();

        when(solicitud_credito_repository.findById(3)).thenReturn(solicitud);
        when(evaluacion_credito_repository.findById(3)).thenReturn(java.util.Optional.of(evaluacion));

        int ingreso_mensual = 10000;  // Ingreso mensual del cliente
        double montoMinimo = ingreso_mensual * 0.05;  // Monto mínimo es el 5% del ingreso mensual (500)

        // Act: Llamar al método a probar cuando ninguna condición se cumple
        // Todos los meses tienen depósitos de solo 100, lo cual es menor al monto mínimo requerido (500)
        boolean resultado = business_logic_service.validate_R73(3, ingreso_mensual,
                100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100);

        // Assert: Verificar que el resultado sea false cuando ninguna condición se cumple
        assertFalse(resultado);

        // Verificar que se haya guardado la evaluación con R73 = false
        verify(evaluacion_credito_repository, times(1)).save(evaluacion);
        assertFalse(evaluacion.isR73());
    }

    @Test
    public void testValidateR74_AntiguedadMenorOIgual2_SaldoMayorOIgual20Porciento() {
        // Arrange: Configurar la solicitud y evaluación de crédito simulada
        Solicitud_Credito_Entity solicitud = new Solicitud_Credito_Entity();
        solicitud.setMonto_deseado(100000);  // Monto deseado de 100,000
        solicitud.setId_evaluacion_credito(1);

        Evaluacion_Credito_Entity evaluacion = new Evaluacion_Credito_Entity();

        when(solicitud_credito_repository.findById(1)).thenReturn(solicitud);
        when(evaluacion_credito_repository.findById(1)).thenReturn(java.util.Optional.of(evaluacion));

        int antiguedad_cliente = 2;  // Antigüedad de 2 años
        int saldo_cuenta = 25000;    // Saldo de la cuenta

        // Act: Llamar al método a probar con antigüedad <= 2 y saldo >= 20%
        boolean resultado = business_logic_service.validate_R74(1, antiguedad_cliente, saldo_cuenta);

        // Assert: Verificar que el resultado sea true cuando se cumple la condición
        assertTrue(resultado);

        // Verificar que se haya guardado la evaluación con R74 = true
        verify(evaluacion_credito_repository, times(1)).save(evaluacion);
        assertTrue(evaluacion.isR74());
    }

    @Test
    public void testValidateR74_AntiguedadMenorOIgual2_SaldoMenor20Porciento() {
        // Arrange: Configurar la solicitud y evaluación de crédito simulada
        Solicitud_Credito_Entity solicitud = new Solicitud_Credito_Entity();
        solicitud.setMonto_deseado(100000);  // Monto deseado de 100,000
        solicitud.setId_evaluacion_credito(2);

        Evaluacion_Credito_Entity evaluacion = new Evaluacion_Credito_Entity();

        when(solicitud_credito_repository.findById(2)).thenReturn(solicitud);
        when(evaluacion_credito_repository.findById(2)).thenReturn(java.util.Optional.of(evaluacion));

        int antiguedad_cliente = 2;  // Antigüedad de 2 años
        int saldo_cuenta = 15000;    // Saldo de la cuenta

        // Act: Llamar al método a probar con antigüedad <= 2 y saldo < 20%
        boolean resultado = business_logic_service.validate_R74(2, antiguedad_cliente, saldo_cuenta);

        // Assert: Verificar que el resultado sea false cuando no se cumple la condición
        assertFalse(resultado);

        // Verificar que se haya guardado la evaluación con R74 = false
        verify(evaluacion_credito_repository, times(1)).save(evaluacion);
        assertFalse(evaluacion.isR74());
    }

    @Test
    public void testValidateR74_AntiguedadMayor2_SaldoMayorOIgual10Porciento() {
        // Arrange: Configurar la solicitud y evaluación de crédito simulada
        Solicitud_Credito_Entity solicitud = new Solicitud_Credito_Entity();
        solicitud.setMonto_deseado(100000);  // Monto deseado de 100,000
        solicitud.setId_evaluacion_credito(3);

        Evaluacion_Credito_Entity evaluacion = new Evaluacion_Credito_Entity();

        when(solicitud_credito_repository.findById(3)).thenReturn(solicitud);
        when(evaluacion_credito_repository.findById(3)).thenReturn(java.util.Optional.of(evaluacion));

        int antiguedad_cliente = 3;  // Antigüedad mayor a 2 años (3 años)
        int saldo_cuenta = 15000;    // Saldo de la cuenta

        // Act: Llamar al método a probar con antigüedad > 2 y saldo >= 10%
        boolean resultado = business_logic_service.validate_R74(3, antiguedad_cliente, saldo_cuenta);

        // Assert: Verificar que el resultado sea true cuando se cumple la condición
        assertTrue(resultado);

        // Verificar que se haya guardado la evaluación con R74 = true
        verify(evaluacion_credito_repository, times(1)).save(evaluacion);
        assertTrue(evaluacion.isR74());
    }

    @Test
    public void testValidateR74_AntiguedadMayor2_SaldoMenor10Porciento() {
        // Arrange: Configurar la solicitud y evaluación de crédito simulada
        Solicitud_Credito_Entity solicitud = new Solicitud_Credito_Entity();
        solicitud.setMonto_deseado(100000);  // Monto deseado de 100,000
        solicitud.setId_evaluacion_credito(4);

        Evaluacion_Credito_Entity evaluacion = new Evaluacion_Credito_Entity();

        when(solicitud_credito_repository.findById(4)).thenReturn(solicitud);
        when(evaluacion_credito_repository.findById(4)).thenReturn(java.util.Optional.of(evaluacion));

        int antiguedad_cliente = 3;   // Antigüedad mayor a 2 años (3 años)
        int saldo_cuenta = 5000;      // Saldo de la cuenta

        // Act: Llamar al método a probar con antigüedad > 2 y saldo < 10%
        boolean resultado = business_logic_service.validate_R74(4, antiguedad_cliente, saldo_cuenta);

        // Assert: Verificar que el resultado sea false cuando no se cumple la condición
        assertFalse(resultado);

        // Verificar que se haya guardado la evaluación con R74 = false
        verify(evaluacion_credito_repository, times(1)).save(evaluacion);
        assertFalse(evaluacion.isR74());
    }

    @Test
    public void testValidateR75_TodosMesesCumplenConElMaximo() {
        // Arrange: Configurar la solicitud y evaluación de crédito simulada
        Solicitud_Credito_Entity solicitud = new Solicitud_Credito_Entity();
        solicitud.setId_evaluacion_credito(1);

        Evaluacion_Credito_Entity evaluacion = new Evaluacion_Credito_Entity();

        when(solicitud_credito_repository.findById(1)).thenReturn(solicitud);
        when(evaluacion_credito_repository.findById(1)).thenReturn(java.util.Optional.of(evaluacion));

        int saldo_cuenta = 10000;  // Saldo de la cuenta
        double max_retiro = saldo_cuenta * 0.3;  // Máximo retiro permitido es el 30% del saldo (3000)

        // Act: Llamar al método a probar con todos los meses cumpliendo con el máximo retiro permitido
        boolean resultado = business_logic_service.validate_R75(1, saldo_cuenta,
                (int) max_retiro - 100, (int) max_retiro - 200, (int) max_retiro - 300,
                (int) max_retiro - 400, (int) max_retiro - 500, (int) max_retiro - 600);

        // Assert: Verificar que el resultado sea true cuando todos los meses cumplen con el máximo retiro
        assertTrue(resultado);

        // Verificar que se haya guardado la evaluación con R75 = true
        verify(evaluacion_credito_repository, times(1)).save(evaluacion);
        assertTrue(evaluacion.isR75());
    }

    @Test
    public void testValidateR75_UnMesExcedeElMaximo() {
        // Arrange: Configurar la solicitud y evaluación de crédito simulada
        Solicitud_Credito_Entity solicitud = new Solicitud_Credito_Entity();
        solicitud.setId_evaluacion_credito(2);

        Evaluacion_Credito_Entity evaluacion = new Evaluacion_Credito_Entity();

        when(solicitud_credito_repository.findById(2)).thenReturn(solicitud);
        when(evaluacion_credito_repository.findById(2)).thenReturn(java.util.Optional.of(evaluacion));

        int saldo_cuenta = 10000;  // Saldo de la cuenta
        double max_retiro = saldo_cuenta * 0.3;  // Máximo retiro permitido es el 30% del saldo (3000)

        // Act: Llamar al método a probar con un mes excediendo el máximo retiro permitido
        boolean resultado = business_logic_service.validate_R75(2, saldo_cuenta,
                (int) max_retiro + 100, (int) max_retiro - 200, (int) max_retiro - 300,
                (int) max_retiro - 400, (int) max_retiro - 500, (int) max_retiro - 600);

        // Assert: Verificar que el resultado sea false cuando un mes excede el máximo retiro
        assertFalse(resultado);

        // Verificar que se haya guardado la evaluación con R75 = false
        verify(evaluacion_credito_repository, times(1)).save(evaluacion);
        assertFalse(evaluacion.isR75());
    }

    @Test
    public void testValidarEvaluacionCredito_Moderada() {
        // Arrange: Configurar la solicitud y evaluación de crédito simulada
        Solicitud_Credito_Entity solicitud = new Solicitud_Credito_Entity();
        solicitud.setId_evaluacion_credito(1);

        Evaluacion_Credito_Entity evaluacion = new Evaluacion_Credito_Entity();
        evaluacion.setR7("moderada");

        when(solicitud_credito_repository.findById(1)).thenReturn(solicitud);
        when(evaluacion_credito_repository.findById(1)).thenReturn(java.util.Optional.of(evaluacion));

        // Act: Llamar al método a probar
        String resultado = business_logic_service.validarEvaluacionCredito(1);

        // Assert: Verificar que el resultado sea "Realizar revision adicional"
        assertEquals("Realizar revision adicional", resultado);

        // Verificar que se haya guardado la evaluación y la solicitud actualizadas
        verify(evaluacion_credito_repository, times(1)).save(evaluacion);
        verify(solicitud_credito_repository, times(1)).save(solicitud);
        assertFalse(evaluacion.isCredito_aceptado());
        assertEquals(7, solicitud.getId_seguimiento_solicitud());
    }

    @Test
    public void testValidarEvaluacionCredito_Insuficiente() {
        // Arrange: Configurar la solicitud y evaluación de crédito simulada
        Solicitud_Credito_Entity solicitud = new Solicitud_Credito_Entity();
        solicitud.setId_evaluacion_credito(2);

        Evaluacion_Credito_Entity evaluacion = new Evaluacion_Credito_Entity();
        evaluacion.setR7("insuficiente");

        when(solicitud_credito_repository.findById(2)).thenReturn(solicitud);
        when(evaluacion_credito_repository.findById(2)).thenReturn(java.util.Optional.of(evaluacion));

        // Act: Llamar al método a probar
        String resultado = business_logic_service.validarEvaluacionCredito(2);

        // Assert: Verificar que el resultado sea "Solicitud rechazada"
        assertEquals("Solicitud rechazada", resultado);

        // Verificar que se haya guardado la evaluación y la solicitud actualizadas
        verify(evaluacion_credito_repository, times(1)).save(evaluacion);
        verify(solicitud_credito_repository, times(1)).save(solicitud);
        assertFalse(evaluacion.isCredito_aceptado());
        assertEquals(7, solicitud.getId_seguimiento_solicitud());
    }

    @Test
    public void testValidarEvaluacionCredito_Solida_Aprobada() {
        // Arrange: Configurar la solicitud y evaluación de crédito simulada
        Solicitud_Credito_Entity solicitud = new Solicitud_Credito_Entity();
        solicitud.setId_evaluacion_credito(3);

        Evaluacion_Credito_Entity evaluacion = new Evaluacion_Credito_Entity();
        evaluacion.setR7("solida");
        evaluacion.setR1(true);
        evaluacion.setR2(true);
        evaluacion.setR3(true);
        evaluacion.setR4(true);
        evaluacion.setR5(true);
        evaluacion.setR6(true);

        when(solicitud_credito_repository.findById(3)).thenReturn(solicitud);
        when(evaluacion_credito_repository.findById(3)).thenReturn(java.util.Optional.of(evaluacion));

        // Act: Llamar al método a probar
        String resultado = business_logic_service.validarEvaluacionCredito(3);

        // Assert: Verificar que el resultado sea "Solicitud aprobada"
        assertEquals("Solicitud aprobada", resultado);

        // Verificar que se haya guardado la evaluación y la solicitud actualizadas
        verify(evaluacion_credito_repository, times(1)).save(evaluacion);
        verify(solicitud_credito_repository, times(1)).save(solicitud);
        assertTrue(evaluacion.isCredito_aceptado());
        assertEquals(4, solicitud.getId_seguimiento_solicitud());
    }

    @Test
    public void testValidarEvaluacionCredito_Solida_Rechazada() {
        // Arrange: Configurar la solicitud y evaluación de crédito simulada
        Solicitud_Credito_Entity solicitud = new Solicitud_Credito_Entity();
        solicitud.setId_evaluacion_credito(4);

        Evaluacion_Credito_Entity evaluacion = new Evaluacion_Credito_Entity();
        evaluacion.setR7("solida");

        // Una condición es false
        evaluacion.setR1(false);

        when(solicitud_credito_repository.findById(4)).thenReturn(solicitud);
        when(evaluacion_credito_repository.findById(4)).thenReturn(java.util.Optional.of(evaluacion));

        // Act: Llamar al método a probar
        String resultado = business_logic_service.validarEvaluacionCredito(4);

        // Assert: Verificar que el resultado sea "Solicitud rechazada"
        assertEquals("Solicitud rechazada", resultado);

        // Verificar que se haya guardado la evaluación y la solicitud actualizadas
        verify(evaluacion_credito_repository, times(1)).save(evaluacion);
        verify(solicitud_credito_repository, times(1)).save(solicitud);
        assertFalse(evaluacion.isCredito_aceptado());
        assertEquals(7, solicitud.getId_seguimiento_solicitud());
    }

    @Test
    public void testCostoTotalCredito() {
        // Arrange: Configurar la solicitud simulada con datos específicos.
        Solicitud_Credito_Entity solicitud = new Solicitud_Credito_Entity();
        solicitud.setCuota_mensual(5000);  // Cuota mensual de 5000
        solicitud.setPlazo_deseado(5);     // Plazo deseado de 5 años (60 meses)
        solicitud.setMonto_deseado(100000);  // Monto deseado de 100000

        when(solicitud_credito_repository.findById(1)).thenReturn(solicitud);

        // Act: Llamar al método a probar.
        Map<String, Object> respuesta = business_logic_service.costoTotalCredito(1);

        // Assert: Verificar los cálculos.

        int seguro_desgravamen = (int) (100000 * 0.0003);  // Seguro desgravamen calculado.
        int seguro_incendio = 20000;                       // Seguro incendio fijo.

        int comision_administrativa = (int) (100000 * 0.01);  // Comisión administrativa calculada.

        int costo_mensual_real = 5000 + seguro_desgravamen + seguro_incendio;  // Costo mensual real.

        int costo_total = costo_mensual_real * 5 * 12 + comision_administrativa;  // Costo total.

        assertEquals(costo_total, respuesta.get("costo_total"));
        assertEquals(costo_mensual_real, respuesta.get("costo_mensual_real"));
        assertEquals(comision_administrativa, respuesta.get("comision_administracion"));
        assertEquals(seguro_desgravamen, respuesta.get("seguro_desgravamen"));
        assertEquals(seguro_incendio, respuesta.get("seguro_incendio"));
    }

}



