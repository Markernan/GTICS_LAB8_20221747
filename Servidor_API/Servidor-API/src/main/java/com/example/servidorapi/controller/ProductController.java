package com.example.servidorapi.controller;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.servidorapi.model.entity.Product;
import com.example.servidorapi.repository.ProductRepository;
import java.util.List;

import java.util.Optional;
import java.util.*;

@RestController
@RequestMapping("/product")

@CrossOrigin(origins = "*")
public class ProductController {
    @Autowired

    private ProductRepository productRepository;
    @GetMapping
    public ResponseEntity<List<Product>> obtenerTodosLosProductos() {
        try {

            List<Product> productos = productRepository.findAll();

            return ResponseEntity.ok(productos);

        } catch (Exception excepcion) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerProductoPorId(@PathVariable String id) {
        try {

            Integer idProducto = Integer.parseInt(id);

            Optional<Product> producto = productRepository.findById(idProducto);

            if (producto.isPresent()) {

                return ResponseEntity.ok(producto.get());

            } else {
                Map<String, String> respuestaError = new HashMap<>();

                respuestaError.put("mensaje", "Producto no encontrado con id: " + id);

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaError);

            }
        } catch (NumberFormatException excepcion) {
            Map<String, String> respuestaError = new HashMap<>();

            respuestaError.put("mensaje", "id de producto no válido: " + id);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaError);

        }
    }

    @PostMapping
    public ResponseEntity<?> crearProducto(@RequestBody Product producto) {
        try {
            if (producto.getNombreProducto() == null || producto.getNombreProducto().trim().isEmpty()) {
                Map<String, String> respuestaError = new HashMap<>();
                respuestaError.put("mensaje", "El nombre del producto es requerido");

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaError);
            }

            Product productoCreado = productRepository.save(producto);

            Map<String, String> respuesta = new HashMap<>();

            respuesta.put("mensaje", "producto creado de forma exitosa");

            respuesta.put("id", productoCreado.getIdProducto().toString());

            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);

        } catch (Exception excepcion) {
            Map<String, String> respuestaError = new HashMap<>();

            respuestaError.put("mensaje", "error al crear el producto: " + excepcion.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);

        }
    }

    @PutMapping
    public ResponseEntity<?> actualizarProducto(@RequestBody Product producto) {
        try {
            if (producto.getIdProducto() == null) {
                Map<String, String> respuestaError = new HashMap<>();
                respuestaError.put("mensaje", "id de producto no proporcionado");

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaError);

            }

            Optional<Product> productoExistente = productRepository.findById(producto.getIdProducto());
            if (productoExistente.isPresent()) {

                Product productoActualizado = productRepository.save(producto);
                Map<String, String> respuesta = new HashMap<>();

                respuesta.put("mensaje", "producto actualizado de forma exitosa");

                return ResponseEntity.ok(respuesta);
            } else {
                Map<String, String> respuestaError = new HashMap<>();

                respuestaError.put("mensaje", "producto no encontrado con id: " + producto.getIdProducto());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaError);

            }

        } catch (Exception excepcion) {
            Map<String, String> respuestaError = new HashMap<>();

            respuestaError.put("mensaje", "error al actualizar el producto: " + excepcion.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable String id) {
        try {

            Integer idProducto = Integer.parseInt(id);
            Optional<Product> producto = productRepository.findById(idProducto);

            if (producto.isPresent()) {
                productRepository.deleteById(idProducto);

                Map<String, String> respuesta = new HashMap<>();

                respuesta.put("mensaje", "producto eliminado de forma exitosa");

                return ResponseEntity.ok(respuesta);

            } else {
                Map<String, String> respuestaError = new HashMap<>();

                respuestaError.put("mensaje", "Producto no encontrado con id: " + id);

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaError);
            }

        } catch (NumberFormatException excepcion) {

            Map<String, String> respuestaError = new HashMap<>();
            respuestaError.put("mensaje", "id de producto no válido: " + id);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaError);

        }
    }
}