package microservicios.cloud_proyecto1.productos.domain;

import microservicios.cloud_proyecto1.categoria.domain.Categoria;
import microservicios.cloud_proyecto1.categoria.domain.CategoriaService;
import microservicios.cloud_proyecto1.productos.dto.ProductoDTO;
import microservicios.cloud_proyecto1.productos.insfrastructure.ProductoRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaService categoriaService;

    // Obtener todos los productos (sin filtrar por categoría)
    public Page<Producto> obtenerTodosLosProductos(Pageable pageable) {
        return productoRepository.findAll(pageable);
    }

    // Obtener productos filtrados por categoría
    public Page<Producto> obtenerProductosPorCategoria(List<Integer> categoriaIds, Pageable pageable) {
        return productoRepository.findByCategorias_IdIn(categoriaIds, pageable);
    }

    // Obtener un producto por su ID
    public Producto obtenerProductoPorId(Integer id) {
        Optional<Producto> producto = productoRepository.findById(id);
        return producto.orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }

    // Crear un nuevo producto a partir de un ProductoDTO
    public Producto crearProducto(ProductoDTO productoDTO) {
        Producto producto = new Producto();
        producto.setNombre(productoDTO.getNombre());
        producto.setDireccion(productoDTO.getDireccion());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setStock(productoDTO.getStock());
        producto.setImagen_url(productoDTO.getImagen_url());
        producto.setFecha_creacion(productoDTO.getFecha_creacion());
        producto.setProveedor(productoDTO.getProveedor());

        // Asignar las categorías
        Set<Categoria> categorias = new HashSet<>();
        for (Integer categoriaId : productoDTO.getCategoriaIds()) {
            Categoria categoria = categoriaService.obtenerCategoriaPorId(categoriaId);
            categorias.add(categoria);
        }
        producto.setCategorias(categorias);

        return productoRepository.save(producto);
    }

    // Actualizar un producto existente
    public Producto actualizarProducto(Integer id, ProductoDTO productoDTO) {
        Optional<Producto> productoExistente = productoRepository.findById(id);
        if (productoExistente.isPresent()) {
            Producto producto = productoExistente.get();
            producto.setNombre(productoDTO.getNombre());
            producto.setDireccion(productoDTO.getDireccion());
            producto.setPrecio(productoDTO.getPrecio());
            producto.setStock(productoDTO.getStock());
            producto.setImagen_url(productoDTO.getImagen_url());
            producto.setFecha_creacion(productoDTO.getFecha_creacion());
            producto.setProveedor(productoDTO.getProveedor());

            // Actualizar las categorías
            Set<Categoria> categorias = new HashSet<>();
            for (Integer categoriaId : productoDTO.getCategoriaIds()) {
                Categoria categoria = categoriaService.obtenerCategoriaPorId(categoriaId);
                categorias.add(categoria);
            }
            producto.setCategorias(categorias);

            return productoRepository.save(producto);
        }
        throw new RuntimeException("Producto no encontrado con ID: " + id);
    }

    // Eliminar un producto
    public void eliminarProducto(Integer id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Producto no encontrado con ID: " + id);
        }
    }
}