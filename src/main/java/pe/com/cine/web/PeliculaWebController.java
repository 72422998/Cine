package pe.com.cine.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import pe.com.cine.dto.CategoriaDTO;
import pe.com.cine.dto.PeliculaDTO;
import pe.com.cine.services.CategoriaService;
import pe.com.cine.services.PeliculaService;

@Controller
@RequestMapping("/pelicula")
public class PeliculaWebController {
    @Autowired
    private PeliculaService peliculaService;
    @Autowired
    private CategoriaService categoriaService;
    @GetMapping("/")
    public String findAll(Model model){
        List<PeliculaDTO> peliculas = peliculaService.findAll();
        model.addAttribute("peliculas", peliculas);
        return "peliculas/lista";
    }
    @GetMapping("/crear")
    public String add(Model model){
        PeliculaDTO peliculaDTO = new PeliculaDTO();
        
        model.addAttribute("peliculaDTO", peliculaDTO);
        model.addAttribute("categorias",categoriaService.findAll());
        return "peliculas/guardar";
    }
    @PostMapping("/crear")
    public String add(@ModelAttribute("peliculaDTO")@Valid PeliculaDTO peliculaDTO,BindingResult result){
        if(result.hasErrors()){
            return "peliculas/guardar";
        }
        PeliculaDTO nuevaPelicula = peliculaService.add(peliculaDTO);
        return "redirect:/pelicula/" ;
    }
    @GetMapping("/{id}/editar")
    public String update(@PathVariable int id, Model model){
        PeliculaDTO pelicula = peliculaService.findById(id);
        model.addAttribute("peliculaDTO", pelicula);
        return "peliculas/guardar";
    }
    @PostMapping("/{id}/editar")
    public String update(@PathVariable int id,@ModelAttribute("peliculaDTO")@Valid PeliculaDTO peliculaDTO, BindingResult result){
        if(result.hasErrors()){
            return "peliculas/guardar";
        }
        PeliculaDTO peliculaActualizada = peliculaService.update(id, peliculaDTO);
        return "redirect:/pelicula/" + peliculaActualizada.getId();
    }
    @DeleteMapping("/{id}/eliminar")
    public String delete(@PathVariable int id){
        peliculaService.delete(id);
        return "redirect:/pelicula";
    }
}
