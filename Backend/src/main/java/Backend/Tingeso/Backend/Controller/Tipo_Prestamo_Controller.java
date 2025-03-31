package Backend.Tingeso.Backend.Controller;

import Backend.Tingeso.Backend.Entity.Tipo_Prestamo_Entity;
import Backend.Tingeso.Backend.Service.Tipo_Prestamo_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tipo_prestamo/")
@CrossOrigin
public class Tipo_Prestamo_Controller {

    @Autowired
    private Tipo_Prestamo_Service tipo_prestamo_service;


    @GetMapping("{id}")
    public ResponseEntity<Tipo_Prestamo_Entity> getTipoPrestamoById(@PathVariable int id) {
        Tipo_Prestamo_Entity tipo = tipo_prestamo_service.getTipo_prestamo(id);
        System.out.println("prueba");
        return ResponseEntity.ok(tipo);
    }

}
