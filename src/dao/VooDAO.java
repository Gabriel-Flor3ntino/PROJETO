package dao;

import java.sql.SQLException;

import model.Voo;

public class VooDAO extends GenericDAOImpl<Voo, Integer> {
    public VooDAO(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Voo.class);
    }
}