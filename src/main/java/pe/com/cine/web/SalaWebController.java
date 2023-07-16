package pe.com.cine.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.com.cine.dto.SalaDTO;
import pe.com.cine.services.SalaService;
import pe.com.cine.services.SedeService;

import java.util.List;

@Controller
@RequestMapping("/sala")
public class SalaWebController {
    @Autowired
    private SalaService salaService;
    @Autowired
    private SedeService sedeService;

    @GetMapping("/")
    public String getAllSalas(Model model){
        List<SalaDTO> salas = salaService.findAll();
        model.addAttribute("salas",salas);
        return "salas/listar";
    }
}
