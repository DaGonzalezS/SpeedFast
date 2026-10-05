package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Entrega {

    private final int id;
    private final int idPedido;
    private final int idRepartidor;
    private final LocalDate fecha;
    private final LocalTime hora;

    public Entrega(int id, int idPedido, int idRepartidor, LocalDate fecha, LocalTime hora) {

        if (id < 0 || idPedido <= 0 || idRepartidor <= 0 || fecha == null || hora == null) {
            throw new IllegalArgumentException("Seleccione un pedido, un repartidor y una fecha y hora validas.");
        }
        if (fecha.getYear() < 1000 || fecha.getYear() > 9999) {
            throw new IllegalArgumentException("La fecha debe estar entre los anos 1000 y 9999.");
        }
        this.id = id; this.idPedido = idPedido; this.idRepartidor = idRepartidor;
        this.fecha = fecha; this.hora = hora.withNano(0);
    }

    public int getId() { return id; }
    public int getIdPedido() { return idPedido; }
    public int getIdRepartidor() { return idRepartidor; }
    public LocalDate getFecha() { return fecha; }
    public LocalTime getHora() { return hora; }
}
