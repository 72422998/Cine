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
    @NotEmpty
    @Size(max=50)
    private String direccion;
    @Column(name="capacidad")
    @NotEmpty
    @Size(max=50)
    private String capacidad;
    @Column(name="turno")
    @NotEmpty
    @Size(max=60)
    private String turno;
    @ManyToOne
    @JoinColumn(name="sede_id")
    private Sede sede;

    public Sala (String nombre, String direccion, String capacidad, String turno, Sede sede){
        this.nombre=nombre;
        this.direccion=direccion;
        this.capacidad=capacidad;
        this.turno=turno;

    }
    public Sala(@NotEmpty @Size(max=50)
    String nombre, @NotEmpty @Size(max=50)
    String direccion, @NotEmpty @Size(max=60)
    String capacidad,@NotEmpty @Size(max=60)
    String turno){
        this.nombre=nombre;
        this.direccion=direccion;
        this.capacidad=capacidad;
        this.turno=turno;
    }
    

    
}
