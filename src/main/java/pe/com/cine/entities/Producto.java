package pe.com.cine.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producto_id")
    private Long productoId;

    @Column(name = "nombre")
    @NotEmpty
    @Size(max = 50)
    private String nombre;
    @Column(name = "precio")
    @NotEmpty
    @Size(max = 20)
    private Double precio;

    
    public Producto(@NotEmpty @Size(max = 50) String nombre, @NotEmpty @Size(max = 20) Double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

}
