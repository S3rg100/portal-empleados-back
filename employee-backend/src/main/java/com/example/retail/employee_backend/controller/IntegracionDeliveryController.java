package com.example.retail.employee_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.retail.employee_backend.dto.DeliveryDataDTO;
import com.example.retail.employee_backend.services.IntegracionDeliveryService;

@RestController
@RequestMapping("/api/integracion_delivery")
@CrossOrigin(origins = "*")
public class IntegracionDeliveryController {

    @Autowired
    private IntegracionDeliveryService integracionDeliveryService;

    @PostMapping("/pedido")
    public ResponseEntity<String> recibirPedido(@RequestBody DeliveryDataDTO deliveryData) {
        // For now, just print it or log it
        System.out.println("Pedido recibido:");
        System.out.println("ID: " + deliveryData.getId());
        System.out.println("Cédula: " + deliveryData.getCedula());
        System.out.println("Productos: " + deliveryData.getProductos().size());

        // Forward the request to the external endpoint
        integracionDeliveryService.enviarPedido(deliveryData);

        return ResponseEntity.ok("Pedido recibido y reenviado correctamente");
    }

}
