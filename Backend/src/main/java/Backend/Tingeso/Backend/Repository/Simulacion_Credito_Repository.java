package Backend.Tingeso.Backend.Repository;

import Backend.Tingeso.Backend.Entity.Simulacion_Credito_Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Simulacion_Credito_Repository extends JpaRepository<Simulacion_Credito_Entity,Integer> {
}
