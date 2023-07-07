package pe.com.cine.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String getAllPeliculas(Model model) {
        List<PeliculaDTO> peliculas = peliculaService.findAll();
        model.addAttribute("peliculas", peliculas);
        return "peliculas/listar";
    }
    
    @GetMapping("/crear")
    public String createPelicula(Model model) {
        PeliculaDTO peliculaDTO = new PeliculaDTO();
        List<CategoriaDTO> categorias = categoriaService.findAll();
        
        model.addAttribute("peliculaDTO", peliculaDTO);
        model.addAttribute("categorias", categorias);
        return "peliculas/guardar";
    }
    
    @PostMapping("/crear")
    public String savePelicula(@ModelAttribute("peliculaDTO") @Valid PeliculaDTO peliculaDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "peliculas/guardar";
        }
        
        peliculaService.add(peliculaDTO);
        return "redirect:/pelicula/";
        /* + nuevaPelicula.getPeliculaId(); */
    }
    
    @GetMapping("/{id}/editar")
    public String updatePelicula(@PathVariable Long id, Model model) {
        PeliculaDTO pelicula = peliculaService.findById(id);
        model.addAttribute("peliculaDTO", pelicula);

        // Obtener la lista de categorias y enviarla al modelo
        List<CategoriaDTO> categorias = categoriaService.findAll();
        model.addAttribute("categorias", categorias);
        return "peliculas/editar";
    }
    
    @PostMapping("/{id}/editar")
    public String saveUpdatedPelicula(@PathVariable Long id, @ModelAttribute("peliculaDTO") @Valid PeliculaDTO peliculaDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "peliculas/editar";
        }

        // Obtener la catagoria actual de la peliculas y establecerla en el objeto PeliculaDTO
        CategoriaDTO categoriaActual = categoriaService.findById(peliculaDTO.getCategoria().getCategoriaId());
        peliculaDTO.setCategoria(categoriaActual);
        
        peliculaService.update(id, peliculaDTO);
        return "redirect:/pelicula/";
    }
    
    @GetMapping("/{id}/eliminar")
    public String deletePelicula(@PathVariable Long id) {
    peliculaService.delete(id);
    return "redirect:/pelicula/";
}

}
