package Backend.Tingeso.Backend.Service;

import Backend.Tingeso.Backend.Entity.Tipo_Prestamo_Entity;
import Backend.Tingeso.Backend.Repository.Tipo_Prestamo_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Tipo_Prestamo_Service {

    @Autowired
    Tipo_Prestamo_Repository tipo_prestamo_repository;

    public Tipo_Prestamo_Entity getTipo_prestamo(int id) {
        return tipo_prestamo_repository.findById(id).get();
    }

}
