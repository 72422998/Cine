package pe.com.cine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.cine.dto.SalaDTO;
import pe.com.cine.services.SalaService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/salas")
public class SalaController {
    @Autowired
    private SalaService salaService;
    @GetMapping("/all")
    public ResponseEntity<List<SalaDTO>>findAll(){
        List<SalaDTO> salas = salaService.findAll();
        return ResponseEntity.ok(salas);
    }
}
