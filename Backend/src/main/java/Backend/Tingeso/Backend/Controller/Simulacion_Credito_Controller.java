package Backend.Tingeso.Backend.Controller;

import Backend.Tingeso.Backend.Entity.Simulacion_Credito_Entity;
import Backend.Tingeso.Backend.Service.Simulacion_Credito_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/simulacion_credito/")
@CrossOrigin
public class Simulacion_Credito_Controller {

    @Autowired
    Simulacion_Credito_Service simulacion_credito_service;

    @PostMapping("nuevo")
    public ResponseEntity<Simulacion_Credito_Entity> newSimulacionCredito(@RequestBody Simulacion_Credito_Entity simulacion_credito) {
        Simulacion_Credito_Entity nuevo = simulacion_credito_service.newSimulacionCredito(simulacion_credito);
        return ResponseEntity.ok(nuevo);
    }


}
