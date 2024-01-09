package com.testbots;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.sql.*;
import java.util.ArrayList;

public class ConnectDatabase {
    public static final String DB_USERNAME = "postgres";
    public static final String DB_PASSWORD = "1234";
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/translator";

    public static void connectDB(Long id, String name) throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        String SQLAddUser = "insert into users (chatId, username)\n" +
                "values (?, ?)\n" +
                "ON CONFLICT (chatId) DO NOTHING;";
        PreparedStatement ps = connection.prepareStatement(SQLAddUser);
        ps.setInt(1, Math.toIntExact(id));
        ps.setString(2, name);
        ps.executeUpdate();
    }

    public void setLastMessage(String messageText) throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        String SQLAddMessage = "update last_bot_message set last_message = ?";
        PreparedStatement ps = connection.prepareStatement(SQLAddMessage);
        ps.setString(1, messageText);
        ps.executeUpdate();
    }

    public String getLastMessage() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        String SQLSelectMessage = "select last_message from last_bot_message order by id";
        PreparedStatement ps = connection.prepareStatement(SQLSelectMessage);
        ps.execute();
        ResultSet result = ps.getResultSet();
        String message = "";
        while (result.next()) {
            message = result.getString("last_message");
        }
        return message;
    }

    public String getSourceLanguage(Long id) throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        String SQLSelectSource = "select source from users where chatid = ?";
        PreparedStatement ps = connection.prepareStatement(SQLSelectSource);
        ps.setInt(1, Math.toIntExact(id));
        ps.execute();
        ResultSet result = ps.getResultSet();
        String source = "";
        while (result.next()) {
            source = result.getString("source");
        }
        return source;
    }

    public String getTargetLanguage(Long id) throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        String SQLSelectSource = "select target from users where chatid = ?";
        PreparedStatement ps = connection.prepareStatement(SQLSelectSource);
        ps.setInt(1, Math.toIntExact(id));
        ps.execute();
        ResultSet result = ps.getResultSet();
        String target = "";
        while (result.next()) {
            target = result.getString("target");
        }
        return target;
    }

    public void setSourceLanguage(Long id, String sourceLanguage) throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        String SQLAddSource = "update users set source = ? where chatid = ?;";
        PreparedStatement ps = connection.prepareStatement(SQLAddSource);
        ps.setString(1, sourceLanguage);
        ps.setInt(2, Math.toIntExact(id));
        ps.executeUpdate();
    }

    public void setTargetLanguage(Long id, String targetLanguage) throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        String SQLAddSource = "update users set target = ? where chatid = ?";
        PreparedStatement ps = connection.prepareStatement(SQLAddSource);
        ps.setString(1, targetLanguage);
        ps.setInt(2, Math.toIntExact(id));
        ps.executeUpdate();
    }


    public ArrayList<Long> selectChatId() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        String SQLSelectChatId = "select chatId from users order by id";
        ResultSet result = statement.executeQuery(SQLSelectChatId);
        ArrayList<Long> chatIds = new ArrayList<Long>();
        while (result.next()) {
            chatIds.add(Long.valueOf(result.getInt("chatId")));
        }
        return chatIds;
    }

    public String selectAds() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        String SQLAddAds = "select bodyAds from ads order by id";
        ResultSet resultAds = statement.executeQuery(SQLAddAds);
        String ads = "";
        while (resultAds.next()) {
             ads = resultAds.getString("bodyAds");
        }
        return ads;
    }

    public String selectPhoto() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        String SQLAddAds = "select photo from ads order by id";
        ResultSet resultAds = statement.executeQuery(SQLAddAds);
        String photo = "";
        while (resultAds.next()) {
            photo = resultAds.getString("photo");
        }
        return photo;
    }

    public ResultSet selectKeys() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        String SQLAddKeys = "select keys, nameKey from keyboard order by id";
        ResultSet resultKeys = statement.executeQuery(SQLAddKeys);
        return resultKeys;
    }

}
