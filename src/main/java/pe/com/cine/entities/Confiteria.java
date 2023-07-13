package pe.com.cine.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Table(name = "confiterias")
public class Confiteria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "confiteria_id")
    private Long confiteriaId;

    @Column(name = "nombre")
    @NotEmpty
    @Size(max = 50)
    private String nombre;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "confiteria_productos",
        joinColumns = @JoinColumn(name="confiteria_id"),
        inverseJoinColumns = @JoinColumn(name="producto_id"))
        private List<Producto> productos = new ArrayList<>();
    
    @ManyToOne
    @JoinColumn(name = "sede_id")
    private Sede sede;




   

}
