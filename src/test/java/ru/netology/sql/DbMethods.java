package ru.netology.sql;

import lombok.Value;
import lombok.SneakyThrows;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.*;

public class DbMethods {
    public DbMethods() {
    }

    @Value
    public static class StatusInfo {
        String status;
    }

    @SneakyThrows
    public static Connection getConnection()
    {
        String dbUrl = System.getProperty("database.url");
        String dbUser = System.getProperty("database.name");
        String dbPassword = System.getProperty("database.password");
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    @SneakyThrows
    public static String getStatusForCredit() {
        Connection conn = getConnection();

        var getCode = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
        var runner = new QueryRunner();
        return runner.query(conn, getCode, new ScalarHandler<>());
    }

    @SneakyThrows
    public static String getStatusForCard() {

        Connection conn = getConnection();

        var getCode = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1;";
        var runner = new QueryRunner();

        return runner.query(conn, getCode, new ScalarHandler<>());
    }

    @SneakyThrows
    public static int getResultSetRowCount(String tableName) {
        Connection conn = getConnection();
        return countForTable(conn, tableName);
    }

    private static int countForTable(Connection conn, String tableName) throws SQLException {
        var getRows = "select count(*) C from " + tableName;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(getRows);
        if(rs.next())
            return rs.getInt("C");

        throw new IllegalStateException();
    }

}
