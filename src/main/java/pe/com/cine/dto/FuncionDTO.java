package pe.com.cine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FuncionDTO {
    private Long funcionId;
  
    private String horaInicio;
  
    private String horaFin;

    private PeliculaDTO pelicula;
   
    private SalaDTO sala;
}
