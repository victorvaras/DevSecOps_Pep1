package Backend.Tingeso.Backend.Controller;

import Backend.Tingeso.Backend.Entity.Seguimiento_Solicitud_Entity;
import Backend.Tingeso.Backend.Service.Seguimiento_Solicitud_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seguimiento_solicitud/")
@CrossOrigin("*")
public class Seguimiento_Solicitud_Controller {

    @Autowired
    private Seguimiento_Solicitud_Service seguimiento_Solicitud_Service;

    @GetMapping("{id}")
    public ResponseEntity<Seguimiento_Solicitud_Entity> obtenerSeguimiento_Solicitud(@PathVariable int id) {
        Seguimiento_Solicitud_Entity seguimieto = seguimiento_Solicitud_Service.getSeguimiento_Solicitud(id);
        return ResponseEntity.ok(seguimieto);
    }

}
