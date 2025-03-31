package Backend.Tingeso.Backend.Controller;

import Backend.Tingeso.Backend.Service.Business_Logic_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/business_logic/")
@CrossOrigin("*")
public class business_Logic_Controller {

    @Autowired
    private Business_Logic_Service business_LogicService;

    @GetMapping("validate_r1/{ingreso_cliente}/{id_solicitud_Credito}")
    public boolean validate_R1(@PathVariable int ingreso_cliente, @PathVariable int id_solicitud_Credito){
        boolean R1 = business_LogicService.validate_R1(ingreso_cliente,id_solicitud_Credito);
        return business_LogicService.validate_R1(ingreso_cliente,id_solicitud_Credito);
    }

    @GetMapping("validate_r2/{id_solicitud_Credito}/{requisito}")
    public boolean validate_R2(@PathVariable int id_solicitud_Credito, @PathVariable boolean requisito){
        return business_LogicService.validate_R2(id_solicitud_Credito,requisito);
    }

    @GetMapping("validate_r3/{id_solicitud_Credito}/{requisito}")
    public boolean validate_R3(@PathVariable int id_solicitud_Credito, @PathVariable boolean requisito){
        return business_LogicService.validate_R3(id_solicitud_Credito,requisito);
    }

    @GetMapping("validate_r4/{id_solicitud_Credito}/{ingresos_cliente}/{deuda_total}")
    public boolean validate_R4(@PathVariable int id_solicitud_Credito,@PathVariable int ingresos_cliente ,@PathVariable int deuda_total){
        return business_LogicService.validate_R4(id_solicitud_Credito,ingresos_cliente,deuda_total);
    }

    @GetMapping("validate_r5/{id_solicitud_Credito}/{valor_propiedad}")
    public boolean validate_R5(@PathVariable int id_solicitud_Credito, @PathVariable int valor_propiedad){
        return business_LogicService.validate_R5(id_solicitud_Credito, valor_propiedad);
    }

    @GetMapping("validate_r6/{id_solicitud_Credito}/{edad_cliente}")
    public boolean validate_R6(@PathVariable int id_solicitud_Credito, @PathVariable int edad_cliente){
        return business_LogicService.validate_R6(id_solicitud_Credito,edad_cliente);
    }

    @GetMapping("validate_r7/{id_solicitud_Credito}")
    public String validate_R7(@PathVariable int id_solicitud_Credito){
        return business_LogicService.validate_R7(id_solicitud_Credito);
    }

    @GetMapping("validate_r71/{id_solicitud_Credito}/{saldo_cuenta}")
    public boolean validate_R71(@PathVariable int id_solicitud_Credito, @PathVariable int saldo_cuenta){
        return business_LogicService.validate_R71(id_solicitud_Credito,saldo_cuenta);
    }

    @GetMapping("validate_r72/{id}/{saldo}/{mes_1}/{mes_2}/{mes_3}/{mes_4}/{mes_5}/{mes_6}/{mes_7}/{mes_8}/{mes_9}/{mes_10}/{mes_11}/{mes_12}")
    public boolean validate_R72(
            @PathVariable int id,
            @PathVariable int saldo,
            @PathVariable int mes_1,
            @PathVariable int mes_2,
            @PathVariable int mes_3,
            @PathVariable int mes_4,
            @PathVariable int mes_5,
            @PathVariable int mes_6,
            @PathVariable int mes_7,
            @PathVariable int mes_8,
            @PathVariable int mes_9,
            @PathVariable int mes_10,
            @PathVariable int mes_11,
            @PathVariable int mes_12) {
        return business_LogicService.validate_R72(id, saldo, mes_1, mes_2, mes_3, mes_4, mes_5,
                mes_6, mes_7, mes_8, mes_9, mes_10, mes_11, mes_12);
    }

    @GetMapping("validate_r73/{id}/{ingreso_mensual}/{mes_1}/{mes_2}/{mes_3}/{mes_4}/{mes_5}/{mes_6}/{mes_7}/{mes_8}/{mes_9}/{mes_10}/{mes_11}/{mes_12}")
    public boolean validate_R73(
            @PathVariable int id,
            @PathVariable int ingreso_mensual,
            @PathVariable int mes_1,
            @PathVariable int mes_2,
            @PathVariable int mes_3,
            @PathVariable int mes_4,
            @PathVariable int mes_5,
            @PathVariable int mes_6,
            @PathVariable int mes_7,
            @PathVariable int mes_8,
            @PathVariable int mes_9,
            @PathVariable int mes_10,
            @PathVariable int mes_11,
            @PathVariable int mes_12) {
        return business_LogicService.validate_R73(id, ingreso_mensual, mes_1, mes_2, mes_3, mes_4, mes_5,
                mes_6, mes_7, mes_8, mes_9, mes_10, mes_11, mes_12);
    }


    @GetMapping("validate_r74/{id_solicitud_Credito}/{antiguedad_cliente}/{saldo_cuenta}")
    public boolean validate_R74(@PathVariable int id_solicitud_Credito,@PathVariable int antiguedad_cliente ,@PathVariable int saldo_cuenta){
        return business_LogicService.validate_R74(id_solicitud_Credito,antiguedad_cliente,saldo_cuenta);
    }


    //(int id_solicitud_Credito, int saldo_cuenta
    @GetMapping("validate_r75/{id_solicitud_Credito}/{saldo_cuenta}/{mes_7}/{mes_8}/{mes_9}/{mes_10}/{mes_11}/{mes_12}")
    public boolean validate_R75(
            @PathVariable int id_solicitud_Credito,
            @PathVariable int saldo_cuenta,
            @PathVariable int mes_7,
            @PathVariable int mes_8,
            @PathVariable int mes_9,
            @PathVariable int mes_10,
            @PathVariable int mes_11,
            @PathVariable int mes_12) {
        return business_LogicService.validate_R75(id_solicitud_Credito, saldo_cuenta, mes_7, mes_8, mes_9,
                mes_10, mes_11, mes_12);
    }


    @GetMapping("validarEvaluacionCredito/{id_solicitud_Credito}")
    public String validarEvaluacionCredito(@PathVariable int id_solicitud_Credito){
        return business_LogicService.validarEvaluacionCredito(id_solicitud_Credito);
    }


    @GetMapping("costo_total_credito/{id_solicitud_Credito}")
    public Map<String,Object> costoTotalCredito(@PathVariable int id_solicitud_Credito) {
        return business_LogicService.costoTotalCredito(id_solicitud_Credito);
    }
}
