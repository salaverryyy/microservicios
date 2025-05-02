package microservicios.cloud_proyecto1.productos.application;

import microservicios.cloud_proyecto1.productos.domain.Producto;
import microservicios.cloud_proyecto1.productos.domain.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // endpoint para obtener todos los productos (sin filtrar por categoría)
    @GetMapping("/productos")
    public List<Producto> obtenerTodosLosProductos() {
        return productoService.obtenerTodosLosProductos();
    }

    // endpoint para obtener productos filtrados por categoría (utilizando query params)
    @GetMapping("/productos/categoria")
    public List<Producto> obtenerProductosPorCategoria(@RequestParam Long categoriaId) {
        return productoService.obtenerProductosPorCategoria(categoriaId);
    }

    // endpoint para obtener un producto por su ID
    @GetMapping("/productos/{id}")
    public Producto obtenerProductoPorId(@PathVariable Long id) {
        return productoService.obtenerProductoPorId(id);
    }
    //Todo endpoints CRUD

    // Crear Producto
    @PostMapping("/productos")
    public Producto crearProducto(@RequestBody Producto producto) {
        return productoService.crearProducto(producto);
    }

    // Actualizar Producto
    @PutMapping("/productos/{id}")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        return productoService.actualizarProducto(id, producto);
    }

    // Eliminar Producto
    @DeleteMapping("/productos/{id}")
    public void eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
    }
}
