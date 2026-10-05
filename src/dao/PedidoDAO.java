package dao;

import model.*;
import interfaces.CrudDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO implements CrudDAO<Pedido> {

    @Override public int create(Pedido entidad) throws SQLException {

        String sql = "INSERT INTO pedido (direccion, tipo, estado) VALUES (?, ?, ?)";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, entidad.getDireccionEntrega());
            ps.setString(2, entidad.getTipo().name());
            ps.setString(3, entidad.getEstado().name());
            con.setAutoCommit(false);

            try {
                if (ps.executeUpdate() != 1) throw new SQLException("No se pudo crear el registro.");
                int idGenerado;

                try (ResultSet claves = ps.getGeneratedKeys()) {
                    if (!claves.next()) throw new SQLException("MySQL no devolvio el ID generado.");
                    idGenerado = claves.getInt(1);
                    if (idGenerado <= 0) throw new SQLException("MySQL devolvio un ID invalido.");
                }
                con.commit();

                return idGenerado;

            } catch (SQLException e) {
                try { con.rollback(); } catch (SQLException falloRollback) { e.addSuppressed(falloRollback); }
                throw e;
            }
        }
    }

    @Override public List<Pedido> readAll() throws SQLException {

        List<Pedido> lista = new ArrayList<>();
        String sql = "SELECT id, direccion, tipo, estado FROM pedido ORDER BY id";

        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(Pedido.crear(rs.getInt("id"), rs.getString("direccion"), TipoPedido.valueOf(rs.getString("tipo")), EstadoPedido.valueOf(rs.getString("estado"))));
        }

        return lista;
    }

    @Override public boolean update(Pedido entidad) throws SQLException {

        String sql = "UPDATE pedido SET direccion = ?, tipo = ?, estado = ? WHERE id = ?";

        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, entidad.getDireccionEntrega());
            ps.setString(2, entidad.getTipo().name());
            ps.setString(3, entidad.getEstado().name());
            ps.setInt(4, entidad.getIdPedido());

            return ps.executeUpdate() == 1;
        }
    }

    @Override public boolean delete(int id) throws SQLException {

        String sql = "DELETE FROM pedido WHERE id = ?";

        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            return ps.executeUpdate() == 1;
        }
    }
}