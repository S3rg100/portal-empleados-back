package com.example.retail.employee_backend.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.retail.employee_backend.model.Producto;
import com.example.retail.employee_backend.repository.ProductoRepository;

@RestController
@RequestMapping("/producto")
@CrossOrigin(origins = "*") // 🔁 Asegura que el frontend pueda acceder desde otro dominio
public class ProductoController {

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // === GET ALL ===
    @GetMapping
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    // === CREATE ===
    @PostMapping
    public Producto createProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    // === GET BY ID ===
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable UUID id) {
        return productoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // === UPDATE ===
    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable UUID id, @RequestBody Producto productoDetails) {
        return productoRepository.findById(id)
                .map(producto -> {
                    producto.setNombre(productoDetails.getNombre());
                    producto.setPrecioCompra(productoDetails.getPrecioCompra());
                    producto.setPrecioVenta(productoDetails.getPrecioVenta());
                    producto.setExistencias(productoDetails.getExistencias());
                    producto.setLinkImagen(productoDetails.getLinkImagen());
                    return ResponseEntity.ok(productoRepository.save(producto));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // === DELETE ===
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable UUID id) {
        return productoRepository.findById(id)
                .map(producto -> {
                    productoRepository.delete(producto);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
