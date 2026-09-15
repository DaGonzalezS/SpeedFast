package model;

import data.ZonaDeCarga;
import java.util.concurrent.ThreadLocalRandom;

public class Repartidor implements Runnable {
    private final String nombre;
    private final ZonaDeCarga zonaDeCarga;

    public Repartidor(String nombre, ZonaDeCarga zonaDeCarga) {
        this.nombre = nombre;
        this.zonaDeCarga = zonaDeCarga;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {
        Pedido pedido;

        while ((pedido = zonaDeCarga.retirarPedido()) != null) {
            pedido.asignarRepartidor(nombre);
            pedido.setEstado(EstadoPedido.EN_REPARTO);

            System.out.println("[Repartidor - " + nombre + "] Retirando "
                    + pedido.getClass().getSimpleName()
                    + " #" + pedido.getId() + "...");
            System.out.println("[Repartidor - " + nombre + "] Estado: "
                    + pedido.getEstado());
            System.out.println("[Repartidor - " + nombre + "] Entregando pedido #"
                    + pedido.getId() + " en "
                    + pedido.getDireccionEntrega() + "...");

            try {
                int pausa = ThreadLocalRandom.current().nextInt(1000, 3001);
                Thread.sleep(pausa);
            } catch (InterruptedException e) {
                System.out.println("[Repartidor - " + nombre
                        + "] La entrega fue interrumpida.");
                Thread.currentThread().interrupt();
                return;
            }

            pedido.despachar();
            System.out.println("[Repartidor - " + nombre + "] Pedido #"
                    + pedido.getId() + " - Estado: " + pedido.getEstado());
            System.out.println();
        }

        System.out.println("[Repartidor - " + nombre + "] No quedan pedidos.");
    }
}