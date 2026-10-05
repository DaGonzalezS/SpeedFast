package dao;

import model.*;
import interfaces.CrudDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepartidorDAO implements CrudDAO<Repartidor> {

    @Override public int create(Repartidor entidad) throws SQLException {

        String sql = "INSERT INTO repartidor (nombre) VALUES (?)";

        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, entidad.getNombre());
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

    @Override public List<Repartidor> readAll() throws SQLException {

        List<Repartidor> lista = new ArrayList<>();
        String sql = "SELECT id, nombre FROM repartidor ORDER BY id";

        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(new Repartidor(rs.getInt("id"), rs.getString("nombre")));
        }

        return lista;
    }

    @Override public boolean update(Repartidor entidad) throws SQLException {

        String sql = "UPDATE repartidor SET nombre = ? WHERE id = ?";

        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, entidad.getNombre());
            ps.setInt(2, entidad.getId());
            return ps.executeUpdate() == 1;
        }
    }

    @Override public boolean delete(int id) throws SQLException {

        String sql = "DELETE FROM repartidor WHERE id = ?";

        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            return ps.executeUpdate() == 1;
        }
    }
}