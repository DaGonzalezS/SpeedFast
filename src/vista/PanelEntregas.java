package vista;
import dao.EntregaDAO;
import model.*;
import javax.swing.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.swing.table.TableRowSorter;
import javax.swing.table.DefaultTableModel;

public class PanelEntregas extends PanelCRUD<Entrega> {
    private final JComboBox<Pedido> pedido = new JComboBox<>();
    private final JComboBox<Repartidor> repartidor = new JComboBox<>();
    private final JTextField fecha = new JTextField();
    private final JTextField hora = new JTextField();
    private final JComboBox<String> filtroPedido = new JComboBox<>();
    private final JComboBox<String> filtroRepartidor = new JComboBox<>();

    public PanelEntregas(Runnable refrescar) {
        super(new EntregaDAO(), refrescar, "ID", "Pedido", "Repartidor", "Fecha", "Hora");
        campo("Pedido:", pedido); campo("Repartidor:", repartidor);
        campo("Fecha (AAAA-MM-DD):", fecha); campo("Hora (HH:mm):", hora);
        campo("Filtrar por pedido:", filtroPedido); campo("Filtrar por repartidor:", filtroRepartidor);
        filtroPedido.addActionListener(e -> filtrar());
        filtroRepartidor.addActionListener(e -> filtrar());
        vaciar();
    }
    public void cargarCombos(List<Pedido> pedidos, List<Repartidor> repartidores) {
        pedido.removeAllItems(); repartidor.removeAllItems();
        filtroPedido.removeAllItems(); filtroRepartidor.removeAllItems();
        filtroPedido.addItem("Todos"); filtroRepartidor.addItem("Todos");
        for (Pedido p : pedidos) { pedido.addItem(p); filtroPedido.addItem(p.toString()); }
        for (Repartidor r : repartidores) { repartidor.addItem(r); filtroRepartidor.addItem(r.toString()); }
        pedido.setSelectedIndex(-1); repartidor.setSelectedIndex(-1);
    }
    private void filtrar() {
        String p = (String) filtroPedido.getSelectedItem();
        String r = (String) filtroRepartidor.getSelectedItem();
        tabla.clearSelection();
        limpiar();
        @SuppressWarnings("unchecked")
        TableRowSorter<DefaultTableModel> ordenador = (TableRowSorter<DefaultTableModel>) tabla.getRowSorter();
        ordenador.setRowFilter(new RowFilter<DefaultTableModel, Integer>() {
            @Override public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> fila) {
                return (p == null || p.equals("Todos") || p.equals(fila.getStringValue(1)))
                        && (r == null || r.equals("Todos") || r.equals(fila.getStringValue(2)));
            }
        });
    }
    @Override protected int id(Entrega e) { return e.getId(); }
    @Override protected Object[] fila(Entrega e) {
        return new Object[]{e.getId(), textoPedido(e.getIdPedido()), textoRepartidor(e.getIdRepartidor()), e.getFecha(), e.getHora()};
    }
    private String textoPedido(int id) {
        for (int i = 0; i < pedido.getItemCount(); i++) if (pedido.getItemAt(i).getIdPedido() == id) return pedido.getItemAt(i).toString();
        return String.valueOf(id);
    }
    private String textoRepartidor(int id) {
        for (int i = 0; i < repartidor.getItemCount(); i++) if (repartidor.getItemAt(i).getId() == id) return repartidor.getItemAt(i).toString();
        return String.valueOf(id);
    }
    @Override protected Entrega leer(int id) {
        Pedido p = (Pedido) pedido.getSelectedItem(); Repartidor r = (Repartidor) repartidor.getSelectedItem();
        if (p == null || r == null) throw new IllegalArgumentException("Seleccione un pedido y un repartidor. Registrelos primero si los combos estan vacios.");
        String dia = fecha.getText().trim(), tiempo = hora.getText().trim();
        if (!dia.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}") || !tiempo.matches("[0-9]{2}:[0-9]{2}(:[0-9]{2})?")) {
            throw new IllegalArgumentException("Use fecha AAAA-MM-DD y hora HH:mm o HH:mm:ss.");
        }
        try {
            return new Entrega(id, p.getIdPedido(), r.getId(), LocalDate.parse(dia), LocalTime.parse(tiempo));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("La fecha u hora no es valida. Ejemplo: 2026-10-04 y 15:30.");
        }
    }
    @Override protected void mostrar(Entrega e) {
        pedido.setSelectedIndex(-1); repartidor.setSelectedIndex(-1);
        for (int i = 0; i < pedido.getItemCount(); i++) if (pedido.getItemAt(i).getIdPedido() == e.getIdPedido()) pedido.setSelectedIndex(i);
        for (int i = 0; i < repartidor.getItemCount(); i++) if (repartidor.getItemAt(i).getId() == e.getIdRepartidor()) repartidor.setSelectedIndex(i);
        fecha.setText(e.getFecha().toString()); hora.setText(e.getHora().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }
    @Override protected void vaciar() {
        pedido.setSelectedIndex(-1); repartidor.setSelectedIndex(-1);
        fecha.setText(LocalDate.now().toString()); hora.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
    }
}