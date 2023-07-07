package pe.com.cine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfiteriaDTO {
    private Long confiteriaId;
    private String nombre;
    private ProductoDTO producto;
    // private SedeDTO sede;
}
