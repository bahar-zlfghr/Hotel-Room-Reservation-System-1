package com.maktab.service;

import com.maktab.java.RoomReservation;
import com.maktab.servlet.RoomReservationServlet;

import java.sql.*;

public class RoomReservationDao {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/hotel_room_reservation_system";
    static final String USER = "root";
    static final String PASS = "79682435@Bahar";

    public static void getReservesInfo() {
        Connection connection;
        PreparedStatement statement;
        try {
            connection = getConnection();
            String insertQuery = "select *" +
                    " from reserve_info";
            statement = connection.prepareStatement(insertQuery);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                RoomReservationServlet.addRoom(
                        new RoomReservation(
                                result.getString(1),
                                result.getString(2),
                                result.getString(3),
                                result.getString(4),
                                result.getInt(5),
                                result.getInt(6),
                                result.getInt(7)
                        )
                );
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertReserve(RoomReservation room) {
        Connection connection;
        PreparedStatement statement;
        try {
            connection = getConnection();
            String insertQuery = "insert into reserve_info values(?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(insertQuery);
            statement.setString(1, room.getFullName());
            statement.setString(2, room.getNationalCode());
            statement.setString(3, room.getStartDate());
            statement.setString(4, room.getEndDate());
            statement.setInt(5, room.getRoomCapacity());
            statement.setInt(6, room.getRoomNumber());
            statement.setInt(7, room.getReserveCode());
            statement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateReserve(RoomReservation room) {
        Connection connection;
        PreparedStatement statement;
        try {
            connection = getConnection();
            String updateQuery = "update reserve_info" +
                    " set START_DATE = ?, END_DATE = ?, ROOM_CAPACITY = ?" +
                    " where RESERVE_CODE = ?";
            statement = connection.prepareStatement(updateQuery);
            statement.setString(1, room.getStartDate());
            statement.setString(2, room.getEndDate());
            statement.setInt(3, room.getRoomCapacity());
            statement.setInt(4, room.getReserveCode());
            statement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteReserve(RoomReservation room) {
        Connection connection;
        PreparedStatement statement;
        try {
            connection = getConnection();
            String deleteQuery = "delete from reserve_info" +
                    " where RESERVE_CODE = ?";
            statement = connection.prepareStatement(deleteQuery);
            statement.setInt(1, room.getReserveCode());
            statement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int getLastReserveCode() {
        Connection connection;
        PreparedStatement statement;
        int lastReserveCode = 9999;
        try {
            connection = getConnection();
            String lastReserveCodeQuery = "select max(RESERVE_CODE)" +
                    " from reserve_info";
            statement = connection.prepareStatement(lastReserveCodeQuery);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                if (result.getInt(1) != 0) {
                    lastReserveCode = result.getInt(1);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return lastReserveCode;
    }

    public static int getLastRoomNumber() {
        Connection connection;
        PreparedStatement statement;
        int lastRoomNumber = 0;
        try {
            connection = getConnection();
            String lastRoomNumberQuery = "select max(ROOM_NUMBER)" +
                    " from reserve_info";
            statement = connection.prepareStatement(lastRoomNumberQuery);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                if (result.getInt(1) != 0) {
                    lastRoomNumber = result.getInt(1);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return lastRoomNumber;
    }

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
