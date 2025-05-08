package microservicios.cloud_proyecto1.productos.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class ProductoDTO {
    private String nombre;
    private String direccion; // Agregado
    private BigDecimal precio;
    private int stock;
    private String imagen_url;
    private LocalDate fecha_creacion;
    private String proveedor;
    private List<Integer> categoriaIds;
}