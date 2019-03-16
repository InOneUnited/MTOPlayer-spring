package com.MTOPlayer.testDataBase.scripts;

import java.sql.*;

public class ApiKeyDao {

    public String getApiName(int id){
        Connection connection = this.openDataBase();
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT api_name FROM apikey WHERE id = "+ id+ ";";
        try {
            statement = connection.prepareStatement(query);

            result = askDataBaseForData(query, statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String api_name = "";
        try {
            while (result.next()) {
                api_name = result.getString(1);
            }
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Data base error - check Your internet connection or try later!");

        }
        closeStatementAndConnection(connection,statement);
        return api_name;
    }

    public Connection openDataBase() {
        Connection c = null;
        try {
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/MTOplayer",
                            "player", "player");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
        return c;
    }

    private ResultSet askDataBaseForData(String query, PreparedStatement statement) {

        ResultSet result = null;
        try {
            result = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    protected void closeStatementAndConnection(Connection connection, Statement statement) {
        try{
            connection.close();
            statement.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
