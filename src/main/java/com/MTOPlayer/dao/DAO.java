package com.MTOPlayer.dao;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.stream.Stream;

public abstract class DAO {
    private String getPassword(){
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines( Paths.get("src/main/dataBasePassword/password.txt"), StandardCharsets.UTF_8))
        {
            stream.forEach(s -> contentBuilder.append(s));
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("PASSWORD TO DataBase NOT FOUND");
        }
        return contentBuilder.toString();
    }

    protected Connection openDataBase() {
        Connection c = null;
        String password = getPassword();
        try{
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/MTOplayer","player",password);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
        return c;
    }

    protected void closeStatementAndConnection(Connection connection, Statement statement) {
        try{
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected PreparedStatement getStatement(Connection connection) {
        PreparedStatement statement = null;
        try{
            statement = (PreparedStatement) connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    protected ResultSet askDataBaseForData(String query, PreparedStatement statement) {
        ResultSet result = null;
        try{
            result = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    protected void editDataBase(Connection connection, String query) {
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
            closeStatementAndConnection(connection, statement);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Data base error - check Your internet connection or try later!");
        }
    }

    protected void executeQuery(String query){
        Connection connection = openDataBase();

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
            closeStatementAndConnection(connection, statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
