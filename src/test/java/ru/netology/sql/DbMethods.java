package ru.netology.sql;

import lombok.Value;
import lombok.val;
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

    public static StatusInfo getStatusForCredit() {
        String dbUrl = System.getProperty("database.url");
        String dbUser = System.getProperty("database.name");
        String dbPassword = System.getProperty("database.password");

        val getCode = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
        val runner = new QueryRunner();

        try (
                val conn = DriverManager.getConnection(
                        dbUrl, dbUser, dbPassword)
        ) {
            val status = runner.query(conn, getCode, new ScalarHandler<>());
            return new StatusInfo(status.toString());
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    public static StatusInfo getStatusForCard() {

        String dbUrl = System.getProperty("database.url");
        String dbUser = System.getProperty("database.name");
        String dbPassword = System.getProperty("database.password");

        val getCode = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1;";
        val runner = new QueryRunner();

        try (
                val conn = DriverManager.getConnection(
                        dbUrl, dbUser, dbPassword)
        ) {
            val status = runner.query(conn, getCode, new ScalarHandler<>());
            return new StatusInfo(status.toString());
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }


    public static int getResultSetRowCountForCredit() {

        String dbUrl = System.getProperty("database.url");
        String dbUser = System.getProperty("database.name");
        String dbPassword = System.getProperty("database.password");

        val getRows = "select * from credit_request_entity";
        try (
                val conn = DriverManager.getConnection(
                        dbUrl, dbUser, dbPassword)
        ) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(getRows);
            rs.last();
            return rs.getRow();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return 0;
    }

    public static int getResultSetRowCountForCard() {
        String dbUrl = System.getProperty("database.url");
        String dbUser = System.getProperty("database.name");
        String dbPassword = System.getProperty("database.password");

        val getRows = "select * from payment_entity";
        try (
                val conn = DriverManager.getConnection(
                        dbUrl, dbUser, dbPassword)
        ) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(getRows);
            rs.last();
            return rs.getRow();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return 0;
    }

}
