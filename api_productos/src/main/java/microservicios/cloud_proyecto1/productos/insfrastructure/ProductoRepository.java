package microservicios.cloud_proyecto1.productos.insfrastructure;

import microservicios.cloud_proyecto1.productos.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    List<Producto> findByCategorias_IdIn(List<Integer> categoriaIds);
}