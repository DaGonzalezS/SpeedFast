package dao;

import model.*;
import interfaces.CrudDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntregaDAO implements CrudDAO<Entrega> {

    @Override public int create(Entrega entidad) throws SQLException {

        String sql = "INSERT INTO entrega (id_pedido, id_repartidor, fecha, hora) VALUES (?, ?, ?, ?)";

        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, entidad.getIdPedido());
            ps.setInt(2, entidad.getIdRepartidor());
            ps.setDate(3, java.sql.Date.valueOf(entidad.getFecha()));
            ps.setTime(4, java.sql.Time.valueOf(entidad.getHora()));
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

    @Override public List<Entrega> readAll() throws SQLException {

        List<Entrega> lista = new ArrayList<>();

        String sql = "SELECT id, id_pedido, id_repartidor, fecha, hora FROM entrega ORDER BY id";

        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(new Entrega(rs.getInt("id"), rs.getInt("id_pedido"), rs.getInt("id_repartidor"), rs.getDate("fecha").toLocalDate(), rs.getTime("hora").toLocalTime()));
        }

        return lista;
    }

    @Override public boolean update(Entrega entidad) throws SQLException {

        String sql = "UPDATE entrega SET id_pedido = ?, id_repartidor = ?, fecha = ?, hora = ? WHERE id = ?";

        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, entidad.getIdPedido());
            ps.setInt(2, entidad.getIdRepartidor());
            ps.setDate(3, java.sql.Date.valueOf(entidad.getFecha()));
            ps.setTime(4, java.sql.Time.valueOf(entidad.getHora()));
            ps.setInt(5, entidad.getId());

            return ps.executeUpdate() == 1;
        }
    }

    @Override public boolean delete(int id) throws SQLException {

        String sql = "DELETE FROM entrega WHERE id = ?";

        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            return ps.executeUpdate() == 1;
        }
    }
}
