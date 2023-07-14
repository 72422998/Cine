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
import pe.com.cine.dto.PeliculaDTO;
import pe.com.cine.services.PeliculaService;

@RestController
@RequestMapping("/api/v1")
public class PeliculaController {
    @Autowired
    private PeliculaService peliculaService;
    @GetMapping("/peliculas")
    public ResponseEntity<Page<PeliculaDTO>>findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize){
    Pageable pageable = PageRequest.of(page, pageSize);
    Page<PeliculaDTO> peliculas = peliculaService.findAll(pageable);
    return ResponseEntity.ok(peliculas);
     }
    @GetMapping("/peliculas/{id}")
    public ResponseEntity<PeliculaDTO>findById(@PathVariable Long id){
        PeliculaDTO pelicula = peliculaService.findById(id);
        if(pelicula != null){
            return ResponseEntity.ok(pelicula);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/peliculas")
    public ResponseEntity<PeliculaDTO>add(@RequestBody @Valid PeliculaDTO peliculaDTO, BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity.badRequest().build();
        }
        PeliculaDTO nuevaPelicula = peliculaService.add(peliculaDTO);
        return ResponseEntity.created(URI.create("/api/v1/"+ nuevaPelicula.getPeliculaId())).body(nuevaPelicula);
    }
    @PutMapping("/peliculas/{id}")
    public ResponseEntity<PeliculaDTO>update(@PathVariable Long id,@RequestBody @Valid PeliculaDTO peliculaDTO, BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity.badRequest().build();
        }
        PeliculaDTO peliculaActualizada = peliculaService.update(id, peliculaDTO);
        if(peliculaActualizada != null){
            return ResponseEntity.ok(peliculaActualizada);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/peliculas/{id}")
    public ResponseEntity<Void>delete(@PathVariable Long id){
        peliculaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
