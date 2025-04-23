package microservicios.cloud_proyecto1.productos;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.beans.factory.annotation.Lookup;

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
    String categoria;
    String imagen_url;
    LocalDate fecha_creacion;
}
