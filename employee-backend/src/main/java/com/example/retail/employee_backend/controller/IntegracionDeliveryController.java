package com.example.retail.employee_backend.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.retail.employee_backend.dto.DeliveryDataDTO;
import com.example.retail.employee_backend.dto.RetailDataDTO;
import com.example.retail.employee_backend.model.Producto;
import com.example.retail.employee_backend.repository.ProductoRepository;
import com.example.retail.employee_backend.services.IntegracionDeliveryService;

@RestController
@RequestMapping("/api/integracion_delivery")
@CrossOrigin(origins = "*")
public class IntegracionDeliveryController {

    private final ProductoRepository productoRepository;

    @Autowired
    public IntegracionDeliveryController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Autowired
    private IntegracionDeliveryService integracionDeliveryService;

    @PostMapping("/pedido")
    public ResponseEntity<String> recibirPedido(@RequestBody DeliveryDataDTO deliveryData) {
        // For now, just print it or log it
        System.out.println("Pedido recibido:");
        System.out.println("ID: " + deliveryData.getId());
        System.out.println("Cédula: " + deliveryData.getCedula());
        System.out.println("Productos: " + deliveryData.getProductos().size());

        // Redireccion a delivery

        integracionDeliveryService.enviarPedido(deliveryData);

        return ResponseEntity.ok("Pedido recibido y reenviado correctamente");
    }

    @GetMapping("/pedido-estado/{id}")
    public ResponseEntity<RetailDataDTO> getEstadoPedido(@PathVariable UUID id) {
        return integracionDeliveryService.getPedido(id.toString());
    }

}
