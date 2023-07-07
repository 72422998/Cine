package pe.com.cine.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "peliculas")
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pelicula_id")
    private Long peliculaId;

    @Column(name = "titulo")
    @NotEmpty
    @Size(max = 50)
    private String titulo;
    @Column(name = "poster")
    @NotEmpty
    @Size(max = 255)
    private String poster;
    @Column(name="sinopsis")
    @NotEmpty
    @Size(max = 500)
    private String sinopsis;
    
    @Column(name="duracion")
    @NotEmpty
    @Size(max = 50)
    private String duracion;
    @Column(name="clasificacion")
    @NotEmpty
    @Size(max = 50)
    private String clasificacion;
    @Column(name="director")
    @NotEmpty
    @Size(max = 50)
    private String director;
    @ManyToOne
    @JoinColumn(name ="categoria_id")
    private Categoria categoria;

    public Pelicula(String titulo, String poster, String sinopsis, String duracion, String clasificacion,
            String director, Categoria categoria) {
        this.titulo = titulo;
        this.poster = poster;
        this.sinopsis = sinopsis;
        this.duracion = duracion;
        this.clasificacion = clasificacion;
        this.director = director;
        this.categoria = categoria;
    }

    public Pelicula(@NotEmpty @Size(max = 50) String titulo, @NotEmpty @Size(max = 255) String poster,
            @NotEmpty @Size(max = 500) String sinopsis,
            @NotEmpty @Size(max = 50) String duracion, @NotEmpty @Size(max = 50) String clasificacion,
            @NotEmpty @Size(max = 50) String director) {
        this.titulo = titulo;
        this.poster = poster;
        this.sinopsis = sinopsis;
        this.duracion = duracion;
        this.clasificacion = clasificacion;
        this.director = director;
    }
    
    
}
