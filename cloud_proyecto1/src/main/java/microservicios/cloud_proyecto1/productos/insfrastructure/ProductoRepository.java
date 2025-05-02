package microservicios.cloud_proyecto1.productos.insfrastructure;

import microservicios.cloud_proyecto1.productos.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // Cambiar a findByCategoria.id para que Spring Data JPA busque el campo 'id' en la entidad Categoria
    List<Producto> findByCategoria_id(Long categoriaId);
}
