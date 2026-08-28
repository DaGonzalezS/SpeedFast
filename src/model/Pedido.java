package model;

import interfaces.Cancelable;
import interfaces.Despachable;
import interfaces.Rastreable;

import java.util.ArrayList;

public abstract class Pedido implements Despachable, Cancelable, Rastreable {

    private int idPedido;
    private String direccionEntrega;
    private double distanciaKm;
    private String repartidor;
    private boolean cancelado;
    private boolean despachado;

    private static ArrayList<String> historial = new ArrayList<>();

    public Pedido(int idPedido, String direccionEntrega, double distanciaKm) {
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.distanciaKm = distanciaKm;
        this.repartidor = "Sin asignar";
        this.cancelado = false;
        this.despachado = false;
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

    protected void setRepartidor(String repartidor) {
        this.repartidor = repartidor;
    }

    public void mostrarResumen() {
        System.out.println("Pedido #" + idPedido);
        System.out.println("Dirección: " + direccionEntrega);
        System.out.println("Distancia: " + distanciaKm + " km");
        System.out.println("Repartidor asignado: " + repartidor);
    }

    public abstract void asignarRepartidor();

    public void asignarRepartidor(String nombre) {
        this.repartidor = nombre;
    }

    public abstract int calcularTiempoEntrega();

    @Override
    public void despachar() {

        if (cancelado) {
            System.out.println("El pedido #" + idPedido
                    + " está cancelado y no puede ser despachado.");
            return;
        }

        despachado = true;

        System.out.println("Pedido #" + idPedido
                + " despachado correctamente.");

        historial.add(
                getClass().getSimpleName()
                        + " #" + idPedido
                        + " - entregado por " + repartidor
        );
    }

    @Override
    public void cancelar() {

        if (despachado) {
            System.out.println("El pedido #" + idPedido
                    + " ya fue despachado y no puede cancelarse.");
            return;
        }

        cancelado = true;

        System.out.println("Pedido #" + idPedido
                + " cancelado exitosamente.");
    }

    @Override
    public void verHistorial() {

        System.out.println("Historial:");

        if (historial.isEmpty()) {
            System.out.println("No existen entregas registradas.");
            return;
        }

        for (String registro : historial) {
            System.out.println("- " + registro);
        }
    }
}