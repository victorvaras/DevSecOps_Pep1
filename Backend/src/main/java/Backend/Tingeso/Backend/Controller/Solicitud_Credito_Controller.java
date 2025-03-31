package Backend.Tingeso.Backend.Controller;

import Backend.Tingeso.Backend.Entity.Solicitud_Credito_Entity;
import Backend.Tingeso.Backend.Service.Solicitud_Credito_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/solicitud_credito/")
@CrossOrigin
public class Solicitud_Credito_Controller {

    @Autowired
    private Solicitud_Credito_Service solicitud_credito_service;

    @PostMapping("nuevo")
    public ResponseEntity<Solicitud_Credito_Entity> newSolicitud(
            @RequestParam("id_Tipo_Prestamo") int id_Tipo_Prestamo,
            @RequestParam("id_cliente") int id_cliente,
            @RequestParam("monto_deseado") int monto_deseado,
            @RequestParam("plazo_deseado") int plazo_deseado,
            @RequestParam(value= "comprobante_ingresos", required = false) MultipartFile comprobanteIngresos,
            @RequestParam(value="certificado_avaluo", required = false) MultipartFile certificadoAvaluo,
            @RequestParam(value="historial_crediticio", required = false) MultipartFile historialCrediticio,
            @RequestParam(value="escritura_primera_vivienda", required = false) MultipartFile escrituraPrimeraVivienda,
            @RequestParam(value="estado_financiero_negocio", required = false) MultipartFile estadoFinancieroNegocio,
            @RequestParam(value="plan_negocios", required = false) MultipartFile planNegocios,
            @RequestParam(value="presupuesto_remodelacion", required = false) MultipartFile presupuestoRemodelacion,
            @RequestParam(value="certificado_avaluo_actualizado", required = false) MultipartFile certificadoAvaluoActualizado) {

        Solicitud_Credito_Entity solicitud = new Solicitud_Credito_Entity();
        solicitud.setId_Tipo_Prestamo(id_Tipo_Prestamo);
        solicitud.setId_cliente(id_cliente);
        solicitud.setMonto_deseado(monto_deseado);
        solicitud.setPlazo_deseado(plazo_deseado);

        try{
            if(comprobanteIngresos != null && !comprobanteIngresos.isEmpty()){
                solicitud.setComprobante_ingresos(comprobanteIngresos.getBytes());
            }
            if(certificadoAvaluo != null && !certificadoAvaluo.isEmpty()){
                solicitud.setCertificado_avaluo(certificadoAvaluo.getBytes());
            }
            if(historialCrediticio != null && !historialCrediticio.isEmpty()){
                solicitud.setHistorial_crediticio(historialCrediticio.getBytes());
            }
            if(escrituraPrimeraVivienda != null && !escrituraPrimeraVivienda.isEmpty()){
                solicitud.setEscritura_primera_vivienda(escrituraPrimeraVivienda.getBytes());
            }
            if(estadoFinancieroNegocio != null && !estadoFinancieroNegocio.isEmpty()){
                solicitud.setEstado_financiero_negocio(estadoFinancieroNegocio.getBytes());
            }
            if(planNegocios != null && !planNegocios.isEmpty()){
                solicitud.setPlan_negocios(planNegocios.getBytes());
            }
            if(presupuestoRemodelacion != null && !presupuestoRemodelacion.isEmpty()){
                solicitud.setPresupuesto_remodelacion(presupuestoRemodelacion.getBytes());
            }
            if (certificadoAvaluoActualizado != null && !certificadoAvaluoActualizado.isEmpty()) {
                solicitud.setCertificado_avaluo_actualizado(certificadoAvaluoActualizado.getBytes());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        Solicitud_Credito_Entity nuevaSolicitud = solicitud_credito_service.nuevaSolicitud_Credito(solicitud);
        return ResponseEntity.ok(nuevaSolicitud);
    }


    @GetMapping("")
    public ResponseEntity<List<Solicitud_Credito_Entity>> getAllSolicitudes(){
        List<Solicitud_Credito_Entity> solicitudes = solicitud_credito_service.getAllSolicitud_Credito();
        return ResponseEntity.ok(solicitudes);
    }

    @GetMapping("{id}")
    public ResponseEntity<Solicitud_Credito_Entity> getSolicitudeById(@PathVariable int id){
        Solicitud_Credito_Entity solicitud = solicitud_credito_service.getSolicitud_Credito(id);
        return ResponseEntity.ok(solicitud);
    }


    @PutMapping("cambio_seguiminto_solicitud/{id_solicitud}/{id_seguimiento}")
    public ResponseEntity<Solicitud_Credito_Entity> updateSolicitud_Seguimiento(@PathVariable int id_solicitud,@PathVariable int id_seguimiento){
        Solicitud_Credito_Entity update = solicitud_credito_service.updateSolicitud_Credito_SeguimientoSolicitud(id_solicitud, id_seguimiento);
        return ResponseEntity.ok(update);
    }
}
