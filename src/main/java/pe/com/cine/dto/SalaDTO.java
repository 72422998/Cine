package pe.com.cine.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaDTO {
    private Long salaId;
    private String nombre;
    private String direccion;
    private String capacidad;
    private String turno;
    private SedeDTO sede;
}

