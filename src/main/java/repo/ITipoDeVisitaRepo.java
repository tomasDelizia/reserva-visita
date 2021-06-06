package repo;

import org.springframework.data.jpa.repository.JpaRepository;
import persistencia.TipoDeVisita;

public interface ITipoDeVisitaRepo extends JpaRepository<TipoDeVisita, Integer> {
}
