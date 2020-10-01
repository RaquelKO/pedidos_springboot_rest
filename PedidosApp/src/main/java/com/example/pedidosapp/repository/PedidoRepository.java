package com.example.pedidosapp.repository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import com.example.pedidosapp.model.Pedido;

import org.springframework.stereotype.Component;

@Component
public class PedidoRepository {

    private List<Pedido> pedidos;
    private int nextCode;

    @PostConstruct
    public void cadastrarPedido() {
        Pedido pedidoTeste = new Pedido();
        pedidoTeste.setCodigo(1);
        pedidoTeste.setCliente("Maria");
        pedidoTeste.setData("30/09/2020");
        pedidoTeste.setDescricao("Produto em promoção!");
        pedidoTeste.setValor(99.90);

        pedidos = new ArrayList<Pedido>();
        pedidos.add(pedidoTeste);
        nextCode = pedidos.size() + 1;

    }

    public List<Pedido> getAllPedidos() {
        return pedidos;
    }

    public Pedido getPedidoByCodigo(int codigo) {
        for (Pedido auxiliar : pedidos) {
            if (auxiliar.getCodigo() == codigo) {
                return auxiliar;
            }
        }
        return null;
    }

    public Pedido save(Pedido pedido) {
        pedido.setCodigo(nextCode++);
        pedidos.add(pedido);
        return pedido;
    }

    public void remove(Pedido pedido) {
        pedidos.remove(pedido);
    }

    public Pedido update(Pedido pedido) {

        Pedido auxiliar = getPedidoByCodigo(pedido.getCodigo());

        if (auxiliar != null) {
            auxiliar.setCliente(pedido.getCliente());
            auxiliar.setData(pedido.getData());
            auxiliar.setDescricao(pedido.getDescricao());
            auxiliar.setValor(pedido.getValor());
        }

        return auxiliar;
    }
}
