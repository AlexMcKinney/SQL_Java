package com.example.sql;

import org.junit.Test;

import java.io.*;
import java.sql.*;

public class TestSQL {

    //to set up the database in the code
    @Test
    public void testCodeSetup() throws SQLException {
        TestDatabase database = new TestDatabase();

        database.execute(
                "CREATE SCHEMA BUSINESS",
                "CREATE TABLE BUSINESS.PEOPLE (ID BIGINT IDENTITY(1,1), NAME VARCHAR(255), AGE INT, PRIMARY KEY(ID))",
                "INSERT INTO BUSINESS.PEOPLE (NAME, AGE) VALUES ('Alex', 11)",
                "INSERT INTO BUSINESS.PEOPLE (NAME, AGE) VALUES ('Bob', 22)",
                "INSERT INTO BUSINESS.PEOPLE (NAME, AGE) VALUES ('Carl', 33)"
        );

        //prints query and results
        database.runQuery("SELECT ID, NAME, AGE FROM BUSINESS.PEOPLE WHERE AGE >= 22");

        database.close();
    }

    //to set up the database in a file
    @Test
    public void testFileSetup() throws SQLException, IOException {
        TestDatabase database = new TestDatabase();

        //runs the SQL commands from a file in the "resources" folder
        database.runFile("init.sql");

        //prints query and results
        database.runQuery("SELECT ID, NAME, AGE FROM BUSINESS.PEOPLE WHERE AGE >= 22");

        database.close();
    }
}
