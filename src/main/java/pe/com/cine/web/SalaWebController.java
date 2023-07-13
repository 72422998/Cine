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
import pe.com.cine.dto.SalaDTO;
import pe.com.cine.dto.SedeDTO;
import pe.com.cine.services.SalaService;
import pe.com.cine.services.SedeService;

@Controller
@RequestMapping("/sala")
public class SalaWebController {
    @Autowired
    private SalaService salaService;
    @Autowired
    private SedeService sedeService;

    @GetMapping("/")
    public String getAllSalas(Model model){
        List<SalaDTO> salas=salaService.findAll();
        model.addAttribute("salas",salas);
        return "salas/listar";
    }

    @GetMapping("/crear")
    public String createSala(Model model){
        SalaDTO salaDTO = new SalaDTO();
        List<SedeDTO> sedes = sedeService.
        findAll();

        model.addAttribute("salaDTO",salaDTO);
        model.addAttribute("sedes",sedes);
        return "salas/guardar";
    }

    @PostMapping("/crear")
    public String saveSala(@ModelAttribute("salaDTO")@Valid SalaDTO salaDTO, BindingResult result){
        if (result.hasErrors()){
            return "salas/guardar";
        }
        salaService.add(salaDTO);
        return "redirect:/sala/";
    }

    @GetMapping("/{id}/editar")
    public String updateSala(@PathVariable Long id, Model model){
        SalaDTO sala = salaService.findById(id);
        model.addAttribute("salaDTO", sala);
        List<SedeDTO> sedes = sedeService.findAll();
        model.addAttribute("sedes", sedes);
        return "salas/editar";
    }

    @PostMapping("/{id}/editar")
    public String saveUpdatedSala(@PathVariable Long id,
    @ModelAttribute("salaDTO")@Valid SalaDTO salaDTO, BindingResult result){
        if(result.hasErrors()){
            return "salas/editar";
        }
        SedeDTO sedeActual = sedeService.
        findById(salaDTO.getSede().getSedeId());
        salaDTO.setSede(sedeActual);
        salaService.update(id,salaDTO);
        return "redirect:/sala/";
    }

    @GetMapping("/{id}/eliminar")
    public String deleteSede(@PathVariable Long id){
        salaService.delete(id);
        return "redirect:/sala/";
    } 
}
