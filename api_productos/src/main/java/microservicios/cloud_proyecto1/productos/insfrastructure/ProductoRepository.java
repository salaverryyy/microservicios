package microservicios.cloud_proyecto1.productos.insfrastructure;

import microservicios.cloud_proyecto1.productos.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    Page<Producto> findAll(Pageable pageable);
    List<Producto> findByCategorias_IdIn(List<Integer> categoriaIds);
}