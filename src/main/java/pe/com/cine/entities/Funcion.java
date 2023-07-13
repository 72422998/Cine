package pe.com.cine.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "funciones")
public class Funcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funcion_id")
    private Long funcionId;
    @Column(name = "hora_inicio")
    private String horaInicio;
    @Column(name = "hora_fin")
    private String horaFin;
    @ManyToOne
    @JoinColumn(name = "pelicula_id")
    private Pelicula pelicula;
    @ManyToOne
    @JoinColumn(name = "sala_id")
    private Sala sala;
}
