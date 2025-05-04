package microservicios.cloud_proyecto1.categoria.domain;

import microservicios.cloud_proyecto1.categoria.infrastructure.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import microservicios.cloud_proyecto1.categoria.dto.CategoriaDTO;
import microservicios.cloud_proyecto1.categoria.domain.Categoria;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Obtener todas las categorías
    public List<Categoria> obtenerTodasLasCategorias() {
        return categoriaRepository.findAll();
    }

    // Crear una nueva categoría a partir de un CategoriaDTO
    public Categoria crearCategoria(CategoriaDTO categoriaDTO) {
        Categoria categoria = new Categoria();
        categoria.setCategoria(categoriaDTO.getCategoria());
        return categoriaRepository.save(categoria);
    }

    // Actualizar una categoría existente a partir de un CategoriaDTO
    public Categoria actualizarCategoria(Long id, CategoriaDTO categoriaDTO) {
        Optional<Categoria> categoriaExistente = categoriaRepository.findById(id);
        if (categoriaExistente.isPresent()) {
            Categoria categoria = categoriaExistente.get();
            categoria.setCategoria(categoriaDTO.getCategoria());
            return categoriaRepository.save(categoria);
        }
        throw new RuntimeException("Categoría no encontrada con ID: " + id);
    }

    // Eliminar una categoría
    public void eliminarCategoria(Long id) {
        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Categoría no encontrada con ID: " + id);
        }
    }

    // Obtener una categoría por su ID
    public Categoria obtenerCategoriaPorId(Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isPresent()) {
            return categoria.get();
        } else {
            throw new RuntimeException("Categoría no encontrada con ID: " + id);
        }
    }
}
