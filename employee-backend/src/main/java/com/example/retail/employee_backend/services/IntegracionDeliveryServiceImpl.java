package com.example.retail.employee_backend.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.retail.employee_backend.dto.DeliveryDataDTO;
import com.example.retail.employee_backend.dto.RetailDataDTO;

@Service
public class IntegracionDeliveryServiceImpl implements IntegracionDeliveryService {

    private final RestTemplate restTemplate;

    public IntegracionDeliveryServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void enviarPedido(DeliveryDataDTO dataDTO) {
        String url = "http://10.43.103.202:5125/api/pedido";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<DeliveryDataDTO> request = new HttpEntity<>(dataDTO, headers);
        restTemplate.postForEntity(url, request, Void.class);
    }

    @Override
    public ResponseEntity<RetailDataDTO> getPedido(String id) {
        String url = "http://10.43.103.202:5125/api/Pedido/codigo/{codigo}";
        return restTemplate.getForEntity(url, RetailDataDTO.class, id);
    }

}
