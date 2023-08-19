package cr.com.charly.dao;

import java.sql.Connection;

public class HuespedDao {
    private Connection connection;
    public HuespedDao(Connection connection) {
        this.connection = connection;
    }
}
