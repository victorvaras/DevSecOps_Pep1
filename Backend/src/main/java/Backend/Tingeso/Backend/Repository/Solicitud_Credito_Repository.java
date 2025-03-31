package Backend.Tingeso.Backend.Repository;

import Backend.Tingeso.Backend.Entity.Solicitud_Credito_Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Solicitud_Credito_Repository extends JpaRepository<Solicitud_Credito_Entity,Integer> {

    public Solicitud_Credito_Entity findById(int id);
}
