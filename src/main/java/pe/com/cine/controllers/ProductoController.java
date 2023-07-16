package pe.com.cine.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import pe.com.cine.dto.ProductoDTO;
import pe.com.cine.services.ProductoService;

@RestController
@RequestMapping("/api/v1/")
public class ProductoController {
    @Autowired
    private ProductoService productoService;
    @GetMapping("/productos")
    public ResponseEntity<Page<ProductoDTO>>findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize){
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<ProductoDTO> productos = productoService.findAll(pageable);
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<ProductoDTO>findById(@PathVariable Long id){
        ProductoDTO producto = productoService.findById(id);
        if (producto!=null) {
            return ResponseEntity.ok(producto);
        } else {
            return ResponseEntity.notFound().build();            
        }
    }
    @PostMapping("/productos/save")
    public ResponseEntity<ProductoDTO>add(@RequestBody @Valid ProductoDTO productoDTO, BindingResult result){
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        ProductoDTO nuevoProducto = productoService.add(productoDTO);
        return ResponseEntity.created(URI.create("/api/v1/"+ nuevoProducto.getProductoId())).body(nuevoProducto);
    
    }

    @PutMapping("/productos/{id}")
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

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Void>delete(@PathVariable Long id) {
        productoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
