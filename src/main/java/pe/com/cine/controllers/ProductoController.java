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
import pe.com.cine.dto.ProductoDTO;
import pe.com.cine.services.ProductoService;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;
    @GetMapping("/all")
    public ResponseEntity<List<ProductoDTO>>findAll(){
        List<ProductoDTO> productos = productoService.findAll();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO>findById(@PathVariable Long id){
        ProductoDTO producto = productoService.findById(id);
        if (producto!=null) {
            return ResponseEntity.ok(producto);
        } else {
            return ResponseEntity.notFound().build();            
        }
    }
    @PostMapping("/add")
    public ResponseEntity<ProductoDTO>add(@RequestBody @Valid ProductoDTO productoDTO, BindingResult result){
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        ProductoDTO nuevoProducto = productoService.add(productoDTO);
        return ResponseEntity.created(URI.create("/api/v1/"+ nuevoProducto.getProductoId())).body(nuevoProducto);
    
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<ProductoDTO>update(@PathVariable Long id, @RequestBody @Valid ProductoDTO productoDTO, BindingResult result){
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        ProductoDTO productoActualizado = productoService.update(id, productoDTO);
        if (productoActualizado!=null) {
            return ResponseEntity.ok(productoActualizado);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void>delete(@PathVariable Long id) {
        productoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
