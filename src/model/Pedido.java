package model;

import interfaces.Cancelable;
import interfaces.Despachable;
import interfaces.Rastreable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Pedido implements Despachable, Cancelable, Rastreable {
    private int id;
    private String direccionEntrega;
    private double distanciaKm;
    private String repartidor;
    private EstadoPedido estado;
    private boolean cancelado;

    private static final List<String> historial =
            Collections.synchronizedList(new ArrayList<>());

    public Pedido(int id, String direccionEntrega, double distanciaKm) {
        this.id = id;
        this.direccionEntrega = direccionEntrega;
        this.distanciaKm = distanciaKm;
        this.repartidor = "Sin asignar";
        this.estado = EstadoPedido.PENDIENTE;
        this.cancelado = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public String getRepartidor() {
        return repartidor;
    }

    public void setRepartidor(String repartidor) {
        this.repartidor = repartidor;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public boolean estaDisponible() {
        return !cancelado && estado == EstadoPedido.PENDIENTE;
    }

    public void mostrarResumen() {
        System.out.println("Pedido #" + id);
        System.out.println("Direccion: " + direccionEntrega);
        System.out.println("Distancia: " + distanciaKm + " km");
        System.out.println("Repartidor asignado: " + repartidor);
        System.out.println("Estado: " + estado);
    }

    public abstract void asignarRepartidor();

    public void asignarRepartidor(String nombre) {
        this.repartidor = nombre;
    }

    public abstract int calcularTiempoEntrega();

    @Override
    public void despachar() {
        if (cancelado) {
            System.out.println("El pedido #" + id
                    + " esta cancelado y no puede ser despachado.");
            return;
        }

        estado = EstadoPedido.ENTREGADO;
        historial.add(getClass().getSimpleName() + " #" + id
                + " - entregado por " + repartidor);
    }

    @Override
    public void cancelar() {
        if (estado == EstadoPedido.EN_REPARTO
                || estado == EstadoPedido.ENTREGADO) {
            System.out.println("El pedido #" + id
                    + " ya esta en proceso o fue entregado.");
            return;
        }

        cancelado = true;
        System.out.println("Pedido #" + id + " cancelado exitosamente.");
    }

    @Override
    public void verHistorial() {
        System.out.println("Historial:");

        if (historial.isEmpty()) {
            System.out.println("No existen entregas registradas.");
            return;
        }

        synchronized (historial) {
            for (String registro : historial) {
                System.out.println("- " + registro);
            }
        }
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", direccionEntrega='" + direccionEntrega + '\'' +
                ", distanciaKm=" + distanciaKm +
                ", repartidor='" + repartidor + '\'' +
                ", estado=" + estado +
                '}';
    }
}
