package microservicios.cloud_proyecto1.productos.domain;


import jakarta.persistence.*;
import lombok.Data;
import microservicios.cloud_proyecto1.categoria.domain.Categoria;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id_producto;
    String descripcion;
    BigDecimal precio;
    int stock;
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    Categoria categoria;
    String imagen_url;
    LocalDate fecha_creacion;

    //@Data no genera sus getters y setters por algun motivo
    public void setId_producto(Long id_producto) {
        this.id_producto = id_producto;
    }

    public void setFecha_creacion(LocalDate fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

}
