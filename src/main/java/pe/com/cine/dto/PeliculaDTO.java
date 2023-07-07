package pe.com.cine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeliculaDTO {
    private Long peliculaId;
    private String titulo;
    private String poster;
    private String sinopsis;
    private String duracion;
    private String clasificacion;
    private String director;
    private CategoriaDTO categoria;
}
