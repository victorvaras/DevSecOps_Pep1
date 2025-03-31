package Backend.Tingeso.Backend.Service;

import Backend.Tingeso.Backend.Entity.Evaluacion_Credito_Entity;
import Backend.Tingeso.Backend.Repository.Evaluacion_Credito_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Evaluacion_Credito_service {

    @Autowired
    Evaluacion_Credito_Repository evaluacion_credito_repository;

    public Evaluacion_Credito_Entity getEvaluacion_Credito(int id){
        return evaluacion_credito_repository.findById(id).get();
    }

    public Evaluacion_Credito_Entity createEvaluacion_Credito(){
        Evaluacion_Credito_Entity nuevo = new Evaluacion_Credito_Entity();
        return evaluacion_credito_repository.save(nuevo);
    }

}
