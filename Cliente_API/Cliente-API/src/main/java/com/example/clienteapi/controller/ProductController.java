package com.example.clienteapi.controller;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import com.example.clienteapi.service.ApiService;

import com.example.clienteapi.model.entity.Product;

import java.util.List;

@Controller
public class ProductController {

    @Autowired

    private ApiService apiService;

    @GetMapping("/")
    public String mostrarProductos(Model model) {

        try {

            List<Product> productos = apiService.obtenerTodosLosProductos();

            model.addAttribute("productos", productos);

        } catch (Exception excepcion) {

            model.addAttribute("error", "error al cargar productos: " + excepcion.getMessage());
        }
        return "products";
    }

    @PostMapping("/buscar")
    public String buscarProducto(@RequestParam("idProducto") String idProducto, Model model) {
        try {

            List<Product> productos = apiService.obtenerTodosLosProductos();

            model.addAttribute("productos", productos);


            Object resultado = apiService.obtenerProductoPorId(idProducto);

            if (resultado instanceof Product) {

                model.addAttribute("productoBuscado", resultado);

                model.addAttribute("mensajeBusqueda", "Producto encontrado:");

            } else {
                model.addAttribute("errorBusqueda", resultado.toString());
            }

        } catch (Exception excepcion) {
            model.addAttribute("error", "error al cargar productos: " + excepcion.getMessage());
        }

        return "products";
    }
}