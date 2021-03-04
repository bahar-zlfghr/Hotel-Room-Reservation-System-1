package com.maktab.java;

import com.maktab.service.RoomReservationDao;

public class RoomReservation {
    private String fullName;
    private String nationalCode;
    private String startDate;
    private String endDate;
    private int roomCapacity;
    private int roomNumber; // start at 1
    private static int roomNumberValue;
    private int reserveCode; // contain 5 digits
    private static int reserveCodeValue;

    public RoomReservation(String fullName, String nationalCode, String startDate, String endDate, int roomCapacity) {
        this.fullName = fullName;
        this.nationalCode = nationalCode;
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomCapacity = roomCapacity;
        this.roomNumber = RoomReservationDao.getLastRoomNumber() + 1;
        this.reserveCode = RoomReservationDao.getLastReserveCode() + 1;
    }

    public RoomReservation(String fullName, String nationalCode, String startDate, String endDate, int roomCapacity, int roomNumber, int reserveCode) {
        this.fullName = fullName;
        this.nationalCode = nationalCode;
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomCapacity = roomCapacity;
        this.roomNumber = roomNumber;
        this.reserveCode = reserveCode;
    }

    public String getFullName() {
        return fullName;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getRoomCapacity() {
        return roomCapacity;
    }

    public void setRoomCapacity(int roomCapacity) {
        this.roomCapacity = roomCapacity;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public static int getRoomNumberValue() {
        return roomNumberValue;
    }

    public int getReserveCode() {
        return reserveCode;
    }

    public static int getReserveCodeValue() {
        return reserveCodeValue;
    }

    public static void setRoomNumberValue(int roomNumberValue) {
        RoomReservation.roomNumberValue = roomNumberValue;
    }

    public static void setReserveCodeValue(int reserveCodeValue) {
        RoomReservation.reserveCodeValue = reserveCodeValue;
    }
}
