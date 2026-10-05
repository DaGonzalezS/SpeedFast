package model;

public class Repartidor {
    private final int id;
    private final String nombre;
    public Repartidor(int id, String nombre) {

        if (id < 0 || nombre == null || nombre.trim().isEmpty() || nombre.trim().length() > 100) {
            throw new IllegalArgumentException("Ingrese un nombre de 1 a 100 caracteres.");
        }
        this.id = id;
        this.nombre = nombre.trim();
    }
    public Repartidor(String nombre) { this(0, nombre); }
    public int getId() { return id; }
    public String getNombre() { return nombre; }

    @Override public String toString() { return id + " - " + nombre; }
}