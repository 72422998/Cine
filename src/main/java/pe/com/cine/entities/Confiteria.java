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
    @Column(name = "producto")
    @NotEmpty
    @Size(max = 20)
    private Producto producto;
    @ManyToOne
    @JoinColumn(name = "sede_id")
    private Sede sede;

    public Confiteria(String nombre, Producto producto, Sede sede) {
        this.nombre = nombre;
        this.producto = producto;
        this.sede = sede;
    }

    public Confiteria(@NotEmpty @Size(max = 50) String nombre, @NotEmpty @Size(max = 20) Producto producto) {
        this.nombre = nombre;
        this.producto = producto;
    }

}
