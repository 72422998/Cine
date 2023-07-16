package pe.com.cine.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import pe.com.cine.dto.PeliculaDTO;
import pe.com.cine.services.PeliculaService;
@Controller
@RequestMapping("/home")
public class HomeWebController {
    @Autowired
    PeliculaService peliculaService;
    @GetMapping("/")
    public String getAllPeliculas(@RequestParam(defaultValue = "0")int page,@RequestParam(defaultValue = "10")int pageSize,Model model) {
        Pageable pageable = PageRequest.of(page,pageSize);
        Page<PeliculaDTO> peliculas = peliculaService.findAll(pageable);
        model.addAttribute("peliculas", peliculas);
        return "home/index";
    }
    @GetMapping("/detalle/{id}")
    public String mostrarDetalle(@PathVariable("id") Long id, Model model) {
        PeliculaDTO pelicula = peliculaService.findById(id);
        if (pelicula != null) {
            model.addAttribute("pelicula", pelicula);
            return "home/detalle"; // Devuelve la vista HTML correspondiente al detalle de la película
        } else {
            return "error"; // Devuelve una vista de error o maneja el caso de película no encontrada según tus necesidades
        }
    }
    
}
