package dao;

import model.Pedido;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    public boolean guardar(Pedido pedido) {
        String sql = "INSERT INTO pedido (id, direccion, tipo, estado) VALUES (?, ?, ?, ?)";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, pedido.getIdPedido());
            ps.setString(2, pedido.getDireccionEntrega());
            ps.setString(3, obtenerTipo(pedido));
            ps.setString(4, pedido.getEstado().name());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al guardar pedido: " + e.getMessage());
            return false;
        }
    }

    public List<PedidoRegistro> listarTodos() {
        List<PedidoRegistro> lista = new ArrayList<>();
        String sql = "SELECT id, direccion, tipo, estado FROM pedido ORDER BY id";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new PedidoRegistro(
                        rs.getInt("id"),
                        rs.getString("direccion"),
                        rs.getString("tipo"),
                        rs.getString("estado")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar pedidos: " + e.getMessage());
        }

        return lista;
    }

    private String obtenerTipo(Pedido pedido) {
        if (pedido.getClass().getSimpleName().equals("PedidoComida")) {
            return "COMIDA";
        }
        if (pedido.getClass().getSimpleName().equals("PedidoEncomienda")) {
            return "ENCOMIENDA";
        }
        return "EXPRESS";
    }

    public static class PedidoRegistro {
        private final int id;
        private final String direccion;
        private final String tipo;
        private final String estado;

        public PedidoRegistro(int id, String direccion, String tipo, String estado) {
            this.id = id;
            this.direccion = direccion;
            this.tipo = tipo;
            this.estado = estado;
        }

        public int getId() { return id; }
        public String getDireccion() { return direccion; }
        public String getTipo() { return tipo; }
        public String getEstado() { return estado; }
    }
}