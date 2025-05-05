package microservicios.cloud_proyecto1.categoria.application;

import microservicios.cloud_proyecto1.categoria.domain.Categoria;
import microservicios.cloud_proyecto1.categoria.domain.CategoriaService;
import microservicios.cloud_proyecto1.categoria.dto.CategoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    // endpoint para obtener todas las categorías
    @GetMapping("/categorias")
    public List<Categoria> obtenerTodasLasCategorias() {
        return categoriaService.obtenerTodasLasCategorias();
    }

    // Crear una categoría
    @PostMapping("/categorias")
    public Categoria crearCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        return categoriaService.crearCategoria(categoriaDTO);
    }



    // Actualizar una categoría
    @PutMapping("/categorias/{id}")
    public Categoria actualizarCategoria(@PathVariable Integer id, @RequestBody CategoriaDTO categoriaDTO) {
        return categoriaService.actualizarCategoria(id, categoriaDTO);
    }

    // Eliminar una categoría
    @DeleteMapping("/categorias/{id}")
    public void eliminarCategoria(@PathVariable Integer id) {
        categoriaService.eliminarCategoria(id);
    }
}
