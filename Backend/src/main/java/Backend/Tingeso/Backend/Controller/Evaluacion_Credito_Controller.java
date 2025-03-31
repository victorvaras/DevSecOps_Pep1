package Backend.Tingeso.Backend.Controller;

import Backend.Tingeso.Backend.Entity.Evaluacion_Credito_Entity;
import Backend.Tingeso.Backend.Entity.Solicitud_Credito_Entity;
import Backend.Tingeso.Backend.Repository.Solicitud_Credito_Repository;
import Backend.Tingeso.Backend.Service.Evaluacion_Credito_service;
import Backend.Tingeso.Backend.Service.Solicitud_Credito_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evaluacion_credito/")
@CrossOrigin
public class Evaluacion_Credito_Controller {

    @Autowired
    Evaluacion_Credito_service evaluacion_credito_service;

    @Autowired
    Solicitud_Credito_Repository solicitud_credito_repository;

    @GetMapping("{id_solicitud}")
    public ResponseEntity<Evaluacion_Credito_Entity> getEvaluacionCredito(@PathVariable int id_solicitud) {
        Solicitud_Credito_Entity solicitud = solicitud_credito_repository.findById(id_solicitud);
        Evaluacion_Credito_Entity evaluacion = evaluacion_credito_service.getEvaluacion_Credito(solicitud.getId_evaluacion_credito());

        return ResponseEntity.ok(evaluacion);
    }


}
