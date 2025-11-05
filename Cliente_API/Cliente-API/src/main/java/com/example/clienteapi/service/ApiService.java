package com.example.clienteapi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.example.clienteapi.model.entity.Product;

import java.util.Arrays;
import java.util.List;
@Service
public class ApiService {

    @Value("${app.api.url}")

    private String apiUrl;

    private final RestTemplate restTemplate;

    public ApiService() {

        this.restTemplate = new RestTemplate();
    }

    public List<Product> obtenerTodosLosProductos() {

        try {

            String url = apiUrl + "/product";

            ResponseEntity<Product[]> respuesta = restTemplate.getForEntity(url, Product[].class);

            return Arrays.asList(respuesta.getBody());

        } catch (Exception excepcion) {

            throw new RuntimeException("error al obtener productos: " + excepcion.getMessage());
        }
    }


    public Object obtenerProductoPorId(String id) {
        try {
            String url = apiUrl + "/product/" + id;

            ResponseEntity<Product> respuesta = restTemplate.getForEntity(url, Product.class);

            return respuesta.getBody();

        } catch (HttpClientErrorException.BadRequest excepcion) {

            return "error: producto no encontrado o id no válido";

        } catch (Exception excepcion) {
            return "error: " + excepcion.getMessage();
        }
    }
}
