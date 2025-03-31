package Backend.Tingeso.Backend.Repository;

import Backend.Tingeso.Backend.Entity.Evaluacion_Credito_Entity;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Evaluacion_Credito_Repository extends JpaRepository<Evaluacion_Credito_Entity, Integer> {


}
