package pe.com.cine.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import pe.com.cine.dto.ConfiteriaDTO;
import pe.com.cine.services.ConfiteriaService;

@RestController
@RequestMapping("/api/v1")
public class ConfiteriaController {
    @Autowired
    private ConfiteriaService confiteriaService;
    @GetMapping("/confiterias")
    public ResponseEntity<Page<ConfiteriaDTO>>findAll(@RequestParam(defaultValue = "0")int page,@RequestParam(defaultValue = "10") int pageSize){
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<ConfiteriaDTO> confiterias = confiteriaService.findAll(pageable);
        return ResponseEntity.ok(confiterias);
    }

    @GetMapping("/confiterias/{id}")
    public ResponseEntity<ConfiteriaDTO>findById(@PathVariable Long id){
        ConfiteriaDTO confiteria = confiteriaService.findById(id);
        if (confiteria!=null) {
            return ResponseEntity.ok(confiteria);
        } else {
            return ResponseEntity.notFound().build();            
        }
    }
    @PostMapping("/confiterias")
    public ResponseEntity<ConfiteriaDTO>add(@RequestBody @Valid ConfiteriaDTO confiteriaDTO, BindingResult result){
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        ConfiteriaDTO nuevaConfiteria = confiteriaService.add(confiteriaDTO);
        return ResponseEntity.created(URI.create("/api/v1/"+ nuevaConfiteria.getConfiteriaId())).body(nuevaConfiteria);
    
    }

    @PutMapping("/confiterias/{id}")
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

    @DeleteMapping("/confiterias/{id}")
    public ResponseEntity<Void>delete(@PathVariable Long id) {
        confiteriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
