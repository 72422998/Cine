package pe.com.cine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import pe.com.cine.dto.SalaDTO;
import pe.com.cine.services.SalaService;

import java.net.URI;
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
    @PostMapping("/add")
    public ResponseEntity<SalaDTO>add(@RequestBody @Valid SalaDTO salaDTO,BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity.badRequest().build();
        }
        SalaDTO nuevaSala = salaService.add(salaDTO);
        return ResponseEntity.created(URI.create("/api/v1/"+nuevaSala.getSalaId())).body(nuevaSala);
    }
}
