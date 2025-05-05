package com.example.retail.employee_backend.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.retail.employee_backend.model.Producto;
import com.example.retail.employee_backend.repository.ProductoRepository;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private final ProductoRepository productoRepository;

    public ProductoController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @GetMapping("/findAll")
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    @PostMapping("/add")
    public Producto createProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    //Get product by Id
   @GetMapping("/find/{id}")
   public ResponseEntity<Producto> getProductoById(@PathVariable UUID id) {
       return productoRepository.findById(id)
       .map(ResponseEntity::ok)
       .orElse(ResponseEntity.notFound().build());
   }

   //Update
  @PutMapping("/update/{id}")
  public ResponseEntity<Producto> updateProducto(@PathVariable UUID id, @RequestBody Producto productoDetails) {
    return productoRepository.findById(id)
    .map(producto -> {
        producto.setNombre(productoDetails.getNombre());
        producto.setPrecioCompra(productoDetails.getPrecioCompra());
        producto.setPrecioVenta(productoDetails.getPrecioVenta());
        producto.setExistencias(productoDetails.getExistencias());
        Producto updatedProducto = productoRepository.save(producto);
        return ResponseEntity.ok(updatedProducto);
    })
    .orElse(ResponseEntity.notFound().build());
  } 

  //Delete
 @DeleteMapping("/delete/{id}")
 public ResponseEntity<Void> deleteProducto(@PathVariable UUID id) {
    return productoRepository.findById(id)
            .map(producto -> {
                productoRepository.delete(producto);
                return ResponseEntity.noContent().<Void>build();
            })
            .orElse(ResponseEntity.notFound().build());
}

  
    

}
