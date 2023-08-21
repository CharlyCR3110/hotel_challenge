package cr.com.charly.dao;

import java.sql.Connection;

public class ReservaDao {
    private final Connection connection;
    ReservaDao(Connection connection) {
        this.connection = connection;
    }
}
