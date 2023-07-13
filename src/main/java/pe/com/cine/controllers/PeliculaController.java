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
import pe.com.cine.dto.PeliculaDTO;
import pe.com.cine.services.PeliculaService;

@RestController
@RequestMapping("/api/v1")
public class PeliculaController {
    @Autowired
    private PeliculaService peliculaService;
    // @GetMapping("/all")
    // public ResponseEntity<List<PeliculaDTO>>findAll(){
    //     List<PeliculaDTO> peliculas = peliculaService.findAll();
    //     return ResponseEntity.ok(peliculas);
    // }
    @GetMapping("/{id}")
    public ResponseEntity<PeliculaDTO>findById(@PathVariable Long id){
        PeliculaDTO pelicula = peliculaService.findById(id);
        if(pelicula != null){
            return ResponseEntity.ok(pelicula);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/add")
    public ResponseEntity<PeliculaDTO>add(@RequestBody @Valid PeliculaDTO peliculaDTO, BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity.badRequest().build();
        }
        PeliculaDTO nuevaPelicula = peliculaService.add(peliculaDTO);
        return ResponseEntity.created(URI.create("/api/v1/"+ nuevaPelicula.getPeliculaId())).body(nuevaPelicula);
    }
    @PutMapping("/{id}/edit")
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
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void>delete(@PathVariable Long id){
        peliculaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
