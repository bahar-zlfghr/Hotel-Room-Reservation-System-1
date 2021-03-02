package com.maktab.java;

public class RoomReservation {
    private String fullName;
    private String nationalCode;
    private Date startDate;
    private Date endDate;
    private int roomCapacity;
    private int roomNumber; // start at 1
    private static int roomNumberValue = 1;
    private int reserveCode; // contain 5 digits
    private static int reserveCodeValue = 10000;

    public RoomReservation(String fullName, String nationalCode, Date startDate, Date endDate, int roomCapacity) {
        this.fullName = fullName;
        this.nationalCode = nationalCode;
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomCapacity = roomCapacity;
        this.roomNumber = roomNumberValue++;
        this.reserveCode = reserveCodeValue++;
    }

    public String getFullName() {
        return fullName;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getRoomCapacity() {
        return roomCapacity;
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
}
