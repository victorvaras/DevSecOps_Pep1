package Backend.Tingeso.Backend.Repository;

import Backend.Tingeso.Backend.Entity.Tipo_Prestamo_Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Tipo_Prestamo_Repository extends JpaRepository<Tipo_Prestamo_Entity,Integer> {
}
