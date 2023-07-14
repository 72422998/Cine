package pe.com.cine.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import pe.com.cine.dto.SedeDTO;
import pe.com.cine.dto.ConfiteriaDTO;
import pe.com.cine.dto.ProductoDTO;
import pe.com.cine.services.SedeService;
import pe.com.cine.services.ConfiteriaService;
import pe.com.cine.services.ProductoService;

@Controller
@RequestMapping("/confiteria")
public class ConfiteriaWebController {
    @Autowired
    private ConfiteriaService confiteriaService;
    @Autowired
    private ProductoService productoService;
    @Autowired
    private SedeService sedeService;
    @GetMapping
    public String getAllConfiterias(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int pageSize,Model model) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<ConfiteriaDTO> confiterias = confiteriaService.findAll(pageable);
        model.addAttribute("confiterias", confiterias);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", confiterias.getTotalPages());
        return "confiterias/listar";
    }
    
    @GetMapping("/crear")
    public String createConfiteria(Model model) {
        ConfiteriaDTO confiteriaDTO = new ConfiteriaDTO();
        Page<ProductoDTO> productos = productoService.findAll(null);
        List<SedeDTO> sedes = sedeService.findAll();
        
        model.addAttribute("confiteriaDTO", confiteriaDTO);
        model.addAttribute("productos", productos);
        model.addAttribute("sedes", sedes);
        return "confiterias/guardar";
    }
    
    @PostMapping("/crear")
    public String saveConfiteria(@ModelAttribute("confiteriaDTO") @Valid ConfiteriaDTO confiteriaDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "confiterias/guardar";
        }
        
        confiteriaService.add(confiteriaDTO);
        return "redirect:/confiteria/";
        /* + nuevaConfiteria.getConfiteriaId(); */
    }
    
    @GetMapping("/{id}/editar")
    public String updateConfiteria(@PathVariable Long id, Model model) {
        ConfiteriaDTO confiteria = confiteriaService.findById(id);
        model.addAttribute("confiteriaDTO", confiteria);

        //Obtener la lista de productos y enviarla al modelo
        Page<ProductoDTO> productos = productoService.findAll(null);
        model.addAttribute("productos", productos);

        // Obtener la lista de sedes y enviarla al modelo
        List<SedeDTO> sedes = sedeService.findAll();
        model.addAttribute("sedes", sedes);
        return "confiterias/editar";
    }
    
    @PostMapping("/{id}/editar")
    public String saveUpdatedConfiteria(@PathVariable Long id, @ModelAttribute("confiteriaDTO") @Valid ConfiteriaDTO confiteriaDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "confiterias/editar";
        }

        // Obtener la sede actual de la confiterias y establecerla en el objeto ConfiteriaDTO
        ProductoDTO productoActual = productoService.findById(confiteriaDTO.getProducto().getProductoId());
        confiteriaDTO.setProducto(productoActual);

        // Obtener la sede actual de la confiterias y establecerla en el objeto ConfiteriaDTO
        SedeDTO sedeActual = sedeService.findById(confiteriaDTO.getSede().getSedeId());
        confiteriaDTO.setSede(sedeActual);
        
        confiteriaService.update(id, confiteriaDTO);
        return "redirect:/confiteria/";
    }
    
    @GetMapping("/{id}/eliminar")
    public String deleteConfiteria(@PathVariable Long id) {
    confiteriaService.delete(id);
    return "redirect:/confiteria/";
}

}
