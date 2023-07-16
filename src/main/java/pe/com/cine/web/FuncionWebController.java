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
import pe.com.cine.dto.FuncionDTO;
import pe.com.cine.dto.PeliculaDTO;
import pe.com.cine.dto.SalaDTO;
import pe.com.cine.services.FuncionService;
import pe.com.cine.services.PeliculaService;
import pe.com.cine.services.SalaService;

@Controller
@RequestMapping("/funcion")
public class FuncionWebController {
    @Autowired
    private FuncionService funcionService;
    @Autowired
    private SalaService salaService; 
    @Autowired
    private PeliculaService peliculaService;

    @GetMapping("/")
    public String getAllFunciones(Model model){
        List<FuncionDTO> funciones=funcionService.findAll();
        model.addAttribute("funciones",funciones);
        return "funciones/listar";
    }
    @GetMapping("/crear")
    public String createFuncion(Model model){
        FuncionDTO funcionDTO= new FuncionDTO();
        List<SalaDTO> salas = salaService.findAll();
        List<PeliculaDTO> peliculas = peliculaService.findAll();
        model.addAttribute("funcionDTO",funcionDTO);
        model.addAttribute("peliculas",peliculas);
        model.addAttribute("salas", salas);
        return "funciones/guardar";
    }
    @PostMapping("/crear")
    public String saveFuncion(@ModelAttribute("funcionDTO")@Valid FuncionDTO funcionDTO, BindingResult result){
        if (result.hasErrors()){
            return "funciones/guardar";
        }
        funcionService.add(funcionDTO);
        return "redirect:/funcion/";
    }
    @GetMapping("/{id}/editar")
    public String updateFuncion(@PathVariable Long id,
    Model model){
        FuncionDTO funcion=funcionService.findById(id);
        model.addAttribute("funcionDTO", funcion);
        List<SalaDTO>salas = salaService.findAll();
        model.addAttribute("salas",salas);
        return "funcion/editar";
        
    }
    @PostMapping("/{id}/editar")
    public String savaUpdateFuncion(@PathVariable Long id,
    @ModelAttribute("funcionDTO")@Valid FuncionDTO funcionDTO, BindingResult result){
        if(result.hasErrors()){
            return "funciones/editar";
        }
        SalaDTO salaActual = salaService.findById(funcionDTO.getSala().getSalaId());
        funcionDTO.setSala(salaActual);
        funcionService.update(id,funcionDTO);
        return "redirect:/sala/";

    }
    @GetMapping("/{id}/eliminar")
    public String deleteSala(@PathVariable Long id){
        funcionService.delete(id);
        return "redirect:/funcion/";
    }
    
}
