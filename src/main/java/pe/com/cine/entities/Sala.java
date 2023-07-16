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
@Table(name="sala")
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sala_id")
    private Long salaId;
    
    @Column(name="nombre")
    private String nombre;
    @Column(name="direccion")

    private String direccion;
    @Column(name="capacidad")

    private String capacidad;
    @Column(name="turno")

    private String turno;
    @ManyToOne
    @JoinColumn(name="sede_id")
    private Sede sede;

    public Sala (String nombre, String direccion, String capacidad, String turno, Sede sede){
        this.nombre=nombre;
        this.direccion=direccion;
        this.capacidad=capacidad;
        this.turno=turno;
        this.sede = sede;
    }
    
    

    
}
