package vista;

import dao.RepartidorDAO;
import model.Repartidor;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaListaRepartidores extends JFrame {

    private final DefaultListModel<String> modelo =
            new DefaultListModel<>();

    public VentanaListaRepartidores() {
        setTitle("Repartidores almacenados");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JList<String> lista = new JList<>(modelo);
        JButton btnActualizar = new JButton("Actualizar");

        btnActualizar.addActionListener(e -> cargar());

        add(new JScrollPane(lista), BorderLayout.CENTER);
        add(btnActualizar, BorderLayout.SOUTH);

        cargar();
        setVisible(true);
    }

    private void cargar() {
        modelo.clear();

        List<Repartidor> repartidores =
                new RepartidorDAO().listarTodos();

        for (Repartidor r : repartidores) {
            modelo.addElement(r.getId() + " - " + r.getNombre());
        }
    }
}