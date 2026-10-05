package vista;

import dao.RepartidorDAO;
import model.Repartidor;
import javax.swing.JTextField;

public class PanelRepartidores extends PanelCRUD<Repartidor> {
    private final JTextField nombre = new JTextField();

    public PanelRepartidores(Runnable refrescar) {
        super(new RepartidorDAO(), refrescar, "ID", "Nombre");
        campo("Nombre:", nombre);
    }

    @Override protected int id(Repartidor r) { return r.getId(); }

    @Override protected Object[] fila(Repartidor r) { return new Object[]{r.getId(), r.getNombre()}; }

    @Override protected Repartidor leer(int id) { return new Repartidor(id, nombre.getText()); }

    @Override protected void mostrar(Repartidor r) { nombre.setText(r.getNombre()); }

    @Override protected void vaciar() { nombre.setText(""); }
}
