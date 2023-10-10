package org.project;

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
             //per un contesto più ampio
            throw new IOException("Impossible to load db.properties");
        }
            String url = pros.getProperty("url");
            String user = pros.getProperty("user");
            String password = pros.getProperty("password");

        //Verifico se url, user o psw sono null
        if (url == null || user == null || password == null) {
            throw new SQLException("The db.properties are not completed");
        }
        //crea la connessione
        return DriverManager.getConnection(url, user, password);
    }
}
