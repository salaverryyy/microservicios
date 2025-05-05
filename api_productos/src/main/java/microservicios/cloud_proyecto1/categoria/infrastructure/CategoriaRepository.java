package microservicios.cloud_proyecto1.categoria.infrastructure;

import microservicios.cloud_proyecto1.categoria.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}