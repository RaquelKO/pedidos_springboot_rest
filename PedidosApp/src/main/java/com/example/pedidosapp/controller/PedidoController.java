package com.example.pedidosapp.controller;

import java.net.URI;
import java.util.List;

import com.example.pedidosapp.model.Pedido;
import com.example.pedidosapp.repository.PedidoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository repository;

    @GetMapping()
    public List<Pedido> getPedidos() {
        return repository.getAllPedidos();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Pedido> getPedido(@PathVariable int codigo) {
        Pedido pedido = repository.getPedidoByCodigo(codigo);

        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<Void> salvar(@RequestBody Pedido pedido) {
        pedido = repository.save(pedido);
        URI uri = URI.create("http://localhost:8080/pedidos/" + pedido.getCodigo());
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> remover(@PathVariable int codigo) {
        Pedido pedido = repository.getPedidoByCodigo(codigo);

        if (pedido != null) {
            repository.remove(pedido);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Pedido> atualizar(@PathVariable int codigo, @RequestBody Pedido pedido) {

        if (repository.getPedidoByCodigo(codigo) != null) {
            pedido.setCodigo(codigo);
            pedido = repository.update(pedido);
            return ResponseEntity.ok(pedido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
