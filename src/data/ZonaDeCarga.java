package data;

import model.Pedido;

import java.util.ArrayList;
import java.util.List;

public class ZonaDeCarga {
    private final List<Pedido> pedidos;

    public ZonaDeCarga() {
        pedidos = new ArrayList<>();
        System.out.println("[Zona de carga inicializada]");
    }

    public synchronized void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
        System.out.println("Pedido #" + pedido.getId()
                + " agregado. Destino: " + pedido.getDireccionEntrega());
    }

    public synchronized Pedido retirarPedido() {
        for (int i = 0; i < pedidos.size(); i++) {
            Pedido pedido = pedidos.get(i);

            if (pedido.estaDisponible()) {
                pedidos.remove(i);
                return pedido;
            }
        }

        return null;
    }

    public synchronized boolean estaVacia() {
        return pedidos.isEmpty();
    }
}
