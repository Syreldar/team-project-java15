package org.project.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLJDBCUtil {

    public static Connection getConnection() throws SQLException, IOException {

        Properties pros = new Properties();
        try (FileInputStream f = new FileInputStream("db.properties")) {
            pros.load(f);
        } catch (IOException e) {
            throw new IOException("Impossible to load db.properties");
        }

        String url = pros.getProperty("url");
        String user = pros.getProperty("user");
        String password = pros.getProperty("password");

        if (url == null || user == null || password == null) {
            throw new SQLException("The db.properties are not completed");
        }

        return DriverManager.getConnection(url, user, password);
    }
}
