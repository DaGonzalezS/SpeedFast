package vista;

import interfaces.CrudDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class PanelCRUD<T> extends JPanel {

    protected final JTable tabla;
    protected final DefaultTableModel modelo;
    protected final JPanel formulario =
            new JPanel(new GridLayout(0, 2, 8, 8));

    protected int idSeleccionado;
    protected List<T> registros = new ArrayList<>();

    private final CrudDAO<T> dao;
    private final Runnable refrescar;

    protected PanelCRUD(
            CrudDAO<T> dao,
            Runnable refrescar,
            String... columnas
    ) {
        this.dao = dao;
        this.refrescar = refrescar;

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tabla = new JTable(modelo);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setAutoCreateRowSorter(true);

        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tabla.getSelectedRow() >= 0) {
                int fila = tabla.convertRowIndexToModel(
                        tabla.getSelectedRow()
                );

                T entidad = registros.get(fila);
                idSeleccionado = id(entidad);
                mostrar(entidad);
            }
        });

        add(formulario, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel botones = new JPanel(
                new FlowLayout(FlowLayout.CENTER)
        );

        boton(botones, "Crear", () -> guardar(false));
        boton(botones, "Guardar cambios", () -> guardar(true));
        boton(botones, "Eliminar", this::eliminar);
        boton(botones, "Limpiar", this::limpiar);
        boton(botones, "Actualizar tablas", refrescar);

        add(botones, BorderLayout.SOUTH);
    }

    protected void campo(String etiqueta, JComponent componente) {
        formulario.add(new JLabel(etiqueta));
        formulario.add(componente);
    }

    private void boton(JPanel panel, String texto, Runnable accion) {
        JButton boton = new JButton(texto);
        boton.addActionListener(e -> accion.run());
        panel.add(boton);
    }

    public void cargar() throws SQLException {
        List<T> nuevos = dao.readAll();

        tabla.clearSelection();
        registros = nuevos;
        modelo.setRowCount(0);

        for (T entidad : registros) {
            modelo.addRow(fila(entidad));
        }

        limpiar();
    }

    protected abstract int id(T entidad);

    protected abstract Object[] fila(T entidad);

    protected abstract T leer(int id);

    protected abstract void mostrar(T entidad);

    protected abstract void vaciar();

    public void limpiar() {
        idSeleccionado = 0;
        tabla.clearSelection();
        vaciar();
    }

    private boolean seleccionado() {
        if (idSeleccionado > 0) {
            return true;
        }

        JOptionPane.showMessageDialog(
                this,
                "Seleccione un registro en la tabla."
        );

        return false;
    }

    private void guardar(boolean editar) {
        if (editar && !seleccionado()) {
            return;
        }

        if (!editar && idSeleccionado > 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Pulse Limpiar antes de crear un registro nuevo."
            );
            return;
        }

        try {
            T entidad = leer(editar ? idSeleccionado : 0);

            if (editar) {
                boolean cambiado = dao.update(entidad);

                JOptionPane.showMessageDialog(
                        this,
                        cambiado
                                ? "Registro actualizado correctamente."
                                : "No se cambio ningun registro. Actualice la tabla."
                );
            } else {
                int idGenerado = dao.create(entidad);

                JOptionPane.showMessageDialog(
                        this,
                        "Registro creado correctamente. ID: " + idGenerado
                );
            }

            refrescar.run();

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Datos invalidos",
                    JOptionPane.WARNING_MESSAGE
            );
        } catch (SQLException e) {
            Mensajes.error(this, e);
        }
    }

    private void eliminar() {
        if (!seleccionado()) {
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "Eliminar el registro " + idSeleccionado + "?",
                "Confirmar eliminacion",
                JOptionPane.YES_NO_OPTION
        );

        if (respuesta != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            boolean eliminado = dao.delete(idSeleccionado);

            JOptionPane.showMessageDialog(
                    this,
                    eliminado
                            ? "Registro eliminado."
                            : "El registro ya no existe. Actualice la tabla."
            );

            refrescar.run();

        } catch (SQLException e) {
            Mensajes.error(this, e);
        }
    }
}