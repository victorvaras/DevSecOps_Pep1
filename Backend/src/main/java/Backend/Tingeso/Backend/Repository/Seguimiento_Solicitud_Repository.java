package Backend.Tingeso.Backend.Repository;

import Backend.Tingeso.Backend.Entity.Seguimiento_Solicitud_Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Seguimiento_Solicitud_Repository extends JpaRepository<Seguimiento_Solicitud_Entity,Integer> {
}
