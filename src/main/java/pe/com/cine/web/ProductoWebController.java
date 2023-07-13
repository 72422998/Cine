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
import pe.com.cine.dto.ProductoDTO;
import pe.com.cine.services.ProductoService;

@Controller
@RequestMapping("/producto")
public class ProductoWebController {
    @Autowired
    private ProductoService productoService;

    @GetMapping("/")
    public String getAllProductos(Model model) {
        List<ProductoDTO> productos = productoService.findAll();
        model.addAttribute("productos", productos);
        return "productos/listar";
    }

    @GetMapping("/crear")
    public String createProducto(Model model) {
        ProductoDTO productoDTO = new ProductoDTO();

        model.addAttribute("productoDTO", productoDTO);
        return "productos/guardar";
    }

    @PostMapping("/crear")
    public String saveProducto(@ModelAttribute("productoDTO") @Valid ProductoDTO productoDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "productos/guardar";
        }

        productoService.add(productoDTO);
        return "redirect:/producto/";
        /* + nuevaProducto.getProductoId(); */
    }

    @GetMapping("/{id}/editar")
    public String updateProducto(@PathVariable Long id, Model model) {
        ProductoDTO producto = productoService.findById(id);
        model.addAttribute("productoDTO", producto);

        return "productos/editar";

    }

    @PostMapping("/{id}/editar")
    public String saveUpdatedProducto(@PathVariable Long id,
            @ModelAttribute("productoDTO") @Valid ProductoDTO productoDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "productos/editar";
        }

        productoService.update(id, productoDTO);
        return "redirect:/producto/";
    }

    @GetMapping("/{id}/eliminar")
    public String deleteProducto(@PathVariable Long id) {
        productoService.delete(id);
        return "redirect:/producto/";
    }
}
