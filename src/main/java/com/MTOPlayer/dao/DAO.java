package com.MTOPlayer.dao;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.stream.Stream;

public abstract class DAO {
    private String getPassword() throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get("src/main/dataBasePassword/password.txt"), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("PASSWORD TO DataBase NOT FOUND");
        }
        return contentBuilder.toString();
    }

    protected Connection openDataBase() throws SQLException, IOException {
        Connection c = null;
        String password = getPassword();
        try {
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/MTOplayer", "player", password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("DB ERROR OPEN DB");
        }
        System.out.println("Opened database successfully");
        return c;
    }

    protected void closeStatementAndConnection(Connection connection, Statement statement) throws SQLException {
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("DB ERROR CLOSE STATEMENT AND CONNECTION");
        }
    }

    protected PreparedStatement getStatement(Connection connection) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = (PreparedStatement) connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("DB ERROR getStatement");
        }
        return statement;
    }

    protected ResultSet askDataBaseForData(String query, PreparedStatement statement) throws SQLException {
        ResultSet result = null;
        try {
            result = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("DB ERROR askDataBaseForData");
        }
        return result;
    }


    protected void executeQuery(String query) throws SQLException, IOException {
        Connection connection = openDataBase();

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
            closeStatementAndConnection(connection, statement);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("DB error executing query");
        }
    }

    protected ResultSet fillResult(String query) throws SQLException, IOException {
        Connection connection = this.openDataBase();
        PreparedStatement statement = null;
        ResultSet result;

        try {
            statement = connection.prepareStatement(query);
            result = askDataBaseForData(query, statement);
        } catch (SQLException e) {
            e.printStackTrace();
            closeStatementAndConnection(connection, statement);
            throw new SQLException("something went wrong with connecting");
        }
        return result;
    }
}
