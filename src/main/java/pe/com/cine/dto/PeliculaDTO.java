package pe.com.cine.dto;

import lombok.Data;
@Data
public class PeliculaDTO {
    private long id;
    private String titulo;
    private String poster;
    private String sinopsis;
    private String genero;
    private String duracion;
    private String clasificacion;
    private String director;
    private CategoriaDTO categoriaDTO;
}
