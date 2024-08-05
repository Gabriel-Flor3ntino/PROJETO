package database;

import java.io.IOException;
import java.sql.SQLException;

import model.Aviao;
import model.Passageiro;
import model.Voo;

public class DatabaseManager {
    private static final String DATABASE_URL = "jdbc:h2:mem:airline_db";
    private ConnectionSource connectionSource;

    private Dao<Aviao, Integer> aviaoDao;
    private Dao<Voo, Integer> vooDao;
    private Dao<Passageiro, Integer> passageiroDao;

    public DatabaseManager() throws SQLException {
        connectionSource = new JdbcConnectionSource(DATABASE_URL);
        setupDatabase();
    }

    private void setupDatabase() throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, Aviao.class);
        TableUtils.createTableIfNotExists(connectionSource, Voo.class);
        TableUtils.createTableIfNotExists(connectionSource, Passageiro.class);

        aviaoDao = DaoManager.createDao(connectionSource, Aviao.class);
        vooDao = DaoManager.createDao(connectionSource, Voo.class);
        passageiroDao = DaoManager.createDao(connectionSource, Passageiro.class);
    }

    public Dao<Aviao, Integer> getAviaoDao() {
        return aviaoDao;
    }

    public Dao<Voo, Integer> getVooDao() {
        return vooDao;
    }

    public Dao<Passageiro, Integer> getPassageiroDao() {
        return passageiroDao;
    }

    public void close() throws IOException {
        connectionSource.close();
    }
}