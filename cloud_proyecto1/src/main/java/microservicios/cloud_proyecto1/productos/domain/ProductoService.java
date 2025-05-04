package microservicios.cloud_proyecto1.productos.domain;

import microservicios.cloud_proyecto1.categoria.domain.Categoria;
import microservicios.cloud_proyecto1.categoria.domain.CategoriaService;
import microservicios.cloud_proyecto1.productos.dto.ProductoDTO;
import microservicios.cloud_proyecto1.productos.insfrastructure.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaService categoriaService;

    // Obtener todos los productos (sin filtrar por categoría)
    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }

    // Obtener productos filtrados por categoría
    public List<Producto> obtenerProductosPorCategoria(Long categoriaId) {
        return productoRepository.findByCategoria_id(categoriaId);
    }

    // Obtener un producto por su ID
    public Producto obtenerProductoPorId(Long id) {
        Optional<Producto> producto = productoRepository.findById(id);
        return producto.orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }

    // Crear un nuevo producto a partir de un ProductoDTO
    public Producto crearProducto(ProductoDTO productoDTO) {
        // Convertir el DTO a la entidad Producto
        Producto producto = new Producto();
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setStock(productoDTO.getStock());
        producto.setImagen_url(productoDTO.getImagen_url());
        producto.setFecha_creacion(productoDTO.getFecha_creacion() != null ? productoDTO.getFecha_creacion() : LocalDate.now());

        // Obtener la categoría y asignarla al producto
        Categoria categoria = categoriaService.obtenerCategoriaPorId(productoDTO.getCategoriaId());
        producto.setCategoria(categoria);

        // Guardar el producto en la base de datos
        return productoRepository.save(producto);
    }

    // Actualizar un producto existente
    public Producto actualizarProducto(Long id, ProductoDTO productoDTO) {
        Optional<Producto> productoExistente = productoRepository.findById(id);
        if (productoExistente.isPresent()) {
            Producto producto = productoExistente.get();
            producto.setDescripcion(productoDTO.getDescripcion());
            producto.setPrecio(productoDTO.getPrecio());
            producto.setStock(productoDTO.getStock());
            producto.setImagen_url(productoDTO.getImagen_url());
            producto.setFecha_creacion(productoDTO.getFecha_creacion() != null ? productoDTO.getFecha_creacion() : LocalDate.now());

            // Actualizar la categoría del producto
            Categoria categoria = categoriaService.obtenerCategoriaPorId(productoDTO.getCategoriaId());
            producto.setCategoria(categoria);

            return productoRepository.save(producto);
        }
        throw new RuntimeException("Producto no encontrado con ID: " + id);
    }

    // Eliminar un producto
    public void eliminarProducto(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Producto no encontrado con ID: " + id);
        }
    }
}
