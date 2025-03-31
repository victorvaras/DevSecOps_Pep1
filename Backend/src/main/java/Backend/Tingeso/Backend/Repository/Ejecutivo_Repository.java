package Backend.Tingeso.Backend.Repository;

import Backend.Tingeso.Backend.Entity.Ejecutivo_Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Ejecutivo_Repository extends JpaRepository<Ejecutivo_Entity, Integer> {

    public Ejecutivo_Entity findByRut(int Rut);
}
