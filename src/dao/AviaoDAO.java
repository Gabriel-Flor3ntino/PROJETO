package dao;

import java.sql.SQLException;

import model.Aviao;

public class AviaoDAO extends GenericDAOImpl<Aviao, Integer> {
    public AviaoDAO(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Aviao.class);
    }
}