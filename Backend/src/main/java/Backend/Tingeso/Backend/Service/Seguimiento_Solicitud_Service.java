package Backend.Tingeso.Backend.Service;

import Backend.Tingeso.Backend.Entity.Seguimiento_Solicitud_Entity;
import Backend.Tingeso.Backend.Repository.Seguimiento_Solicitud_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Seguimiento_Solicitud_Service {

    @Autowired
    Seguimiento_Solicitud_Repository seguimiento_Solicitud_Repository;

    public Seguimiento_Solicitud_Entity getSeguimiento_Solicitud(int id){
        return seguimiento_Solicitud_Repository.findById(id).get();
    }
}
