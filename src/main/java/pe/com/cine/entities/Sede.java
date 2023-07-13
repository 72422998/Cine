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
@Table(name="sede")
public class Sede {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="sede_id")
    private Long sedeId;

    @Column(name="nombre")
    @NotEmpty
    @Size(max=23)
    private String nombre;

    
    @Column(name="direccion")
    @NotEmpty
    @Size(max=23)
    private String direccion;
    
    public Sede(String nombre){
        this.nombre=nombre;
        
    }
    public Sede(@NotEmpty @Size(max=23) String nombre,
    @NotEmpty @Size(max=23) String direccion){
        this.nombre = nombre;

    }
    
    
}
