package model;

import interfaces.Cancelable;
import interfaces.Despachable;
import interfaces.Rastreable;

public abstract class Pedido implements Despachable, Cancelable, Rastreable {
    private int idPedido;
    private String direccionEntrega;
    private double distanciaKm;
    private String repartidor;
    private EstadoPedido estado;

    public Pedido(int idPedido, String direccionEntrega, double distanciaKm) {
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.distanciaKm = distanciaKm;
        this.estado = EstadoPedido.PENDIENTE;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public double getDistanciaKm() {
        return distanciaKm;
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

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public abstract void asignarRepartidor();

    public void asignarRepartidor(String nombreRepartidor) {
        setRepartidor(nombreRepartidor);
    }

    public abstract int calcularTiempoEntrega();

    public void mostrarResumen() {
        System.out.println("Pedido #" + idPedido);
        System.out.println("Direccion: " + direccionEntrega);
        System.out.println("Distancia: " + distanciaKm + " km");
        System.out.println("Estado: " + estado);
        System.out.println("Repartidor: " +
                (repartidor == null ? "Sin asignar" : repartidor));
    }

    @Override
    public void despachar() {
        estado = EstadoPedido.EN_REPARTO;
        System.out.println("Pedido #" + idPedido + " despachado.");
    }

    @Override
    public void cancelar() {
        System.out.println("Pedido #" + idPedido + " cancelado.");
    }

    @Override
    public void verHistorial() {
        System.out.println("Historial del pedido #" + idPedido +
                ": estado actual = " + estado);
    }
}