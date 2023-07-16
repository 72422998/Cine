package pe.com.cine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {
    private Long productoId;
    private String nombre;
    private String imagen;
    private Double precio;
    private ConfiteriaDTO confiteria;
}
