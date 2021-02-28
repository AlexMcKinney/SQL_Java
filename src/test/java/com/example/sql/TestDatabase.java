package com.example.sql;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
 * Utility class for convenience
 * */
public class TestDatabase {
    Connection conn;

    public TestDatabase(Connection conn){
        this.conn = conn;
    }

    public TestDatabase() throws SQLException {
        this.conn = DriverManager.getConnection ("jdbc:h2:mem:MobileAppsData", "","");
    }

    public void close() throws SQLException {
        conn.close();
    }

    public void execute(String... commands) throws SQLException {
        for(String command: commands) {
            conn.createStatement().execute(command);
        }
    }

    public void runQuery(String query) throws SQLException {
        System.out.println(query);
        ResultSet rs = conn.createStatement().executeQuery(query);
        ResultSetMetaData md = rs.getMetaData();
        while(rs.next()){
            List<Object> list = new ArrayList<Object>();
            for(int i = 0; i < md.getColumnCount(); i++){
                Object value = rs.getObject(i+1);
                list.add(value);
            }
            System.out.println(list);
        }
    }

    public void runFile(String filename) throws IOException, SQLException {
        InputStream in = ClassLoader.getSystemClassLoader().getResource(filename).openStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line;
        while((line = reader.readLine()) != null){
            conn.createStatement().execute(line);
        }
    }
}
