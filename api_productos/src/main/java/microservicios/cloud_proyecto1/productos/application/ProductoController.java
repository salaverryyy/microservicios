package microservicios.cloud_proyecto1.productos.application;

import microservicios.cloud_proyecto1.productos.domain.Producto;
import microservicios.cloud_proyecto1.productos.domain.ProductoService;
import microservicios.cloud_proyecto1.productos.dto.ProductoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@RestController
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // endpoint para obtener todos los productos (sin filtrar por categoría)
    @GetMapping("/productos")
    public Page<Producto> obtenerTodosLosProductos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productoService.obtenerTodosLosProductos(pageable);
    }

    // endpoint para obtener productos filtrados por categoría (paginable)
    @GetMapping("/productos/categoria")
    public Page<Producto> obtenerProductosPorCategoria(
            @RequestParam List<Integer> categoriaId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productoService.obtenerProductosPorCategoria(categoriaId, pageable);
    }

    // endpoint para obtener un producto por su ID
    @GetMapping("/productos/{id}")
    public Producto obtenerProductoPorId(@PathVariable Integer id) {
        return productoService.obtenerProductoPorId(id);
    }

    // Crear Producto
    @PostMapping("/productos")
    public Producto crearProducto(@RequestBody ProductoDTO productoDTO) {
        return productoService.crearProducto(productoDTO);
    }

    // Actualizar Producto
    @PutMapping("/productos/{id}")
    public Producto actualizarProducto(@PathVariable Integer id, @RequestBody ProductoDTO productoDTO) {
        return productoService.actualizarProducto(id, productoDTO);
    }

    // Eliminar Producto
    @DeleteMapping("/productos/{id}")
    public void eliminarProducto(@PathVariable Integer id) {
        productoService.eliminarProducto(id);
    }
}
