package pe.com.cine.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import pe.com.cine.dto.ConfiteriaDTO;
import pe.com.cine.services.ConfiteriaService;

@RestController
@RequestMapping
public class ConfiteriaController {
    @Autowired
    private ConfiteriaService confiteriaService;
    @GetMapping("/all")
    public ResponseEntity<List<ConfiteriaDTO>>findAll(){
        List<ConfiteriaDTO> confiterias = confiteriaService.findAll();
        return ResponseEntity.ok(confiterias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConfiteriaDTO>findById(@PathVariable Long id){
        ConfiteriaDTO confiteria = confiteriaService.findById(id);
        if (confiteria!=null) {
            return ResponseEntity.ok(confiteria);
        } else {
            return ResponseEntity.notFound().build();            
        }
    }
    @PostMapping("/add")
    public ResponseEntity<ConfiteriaDTO>add(@RequestBody @Valid ConfiteriaDTO confiteriaDTO, BindingResult result){
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        ConfiteriaDTO nuevaConfiteria = confiteriaService.add(confiteriaDTO);
        return ResponseEntity.created(URI.create("/api/v1/"+ nuevaConfiteria.getConfiteriaId())).body(nuevaConfiteria);
    
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<ConfiteriaDTO>update(@PathVariable Long id, @RequestBody @Valid ConfiteriaDTO confiteriaDTO, BindingResult result){
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        ConfiteriaDTO confiteriaActualizada = confiteriaService.update(id, confiteriaDTO);
        if (confiteriaActualizada!=null) {
            return ResponseEntity.ok(confiteriaActualizada);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void>delete(@PathVariable Long id) {
        confiteriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
