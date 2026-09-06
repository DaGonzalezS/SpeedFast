package model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Repartidor implements Runnable {

    private String nombre;
    private List<Pedido> pedidos;

    public Repartidor(String nombre) {
        this.nombre = nombre;
        this.pedidos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    @Override
    public void run() {

        for (Pedido pedido : pedidos) {

            pedido.asignarRepartidor(nombre);

            System.out.println(
                    "[Repartidor: " + nombre + "] Entregando "
                            + pedido.getClass().getSimpleName()
                            + " #" + pedido.getIdPedido() + "..."
            );

            try {
                int pausa = ThreadLocalRandom.current().nextInt(1000, 3001);
                Thread.sleep(pausa);
            } catch (InterruptedException e) {
                System.out.println(
                        "[Repartidor: " + nombre
                                + "] La entrega fue interrumpida."
                );
                Thread.currentThread().interrupt();
                return;
            }

            pedido.despachar();

            System.out.println(
                    "[Repartidor: " + nombre + "] Pedido #"
                            + pedido.getIdPedido() + " entregado."
            );
        }

        System.out.println(
                "[Repartidor: " + nombre + "] Finalizó todas sus entregas."
        );
    }
}
