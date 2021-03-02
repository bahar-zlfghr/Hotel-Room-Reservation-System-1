package com.maktab.java;

public class Date implements Comparable<Date> {
    private final int year;
    private final int month;
    private final int day;

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public int compareTo(Date date) {
        if (this.year < date.year) {
            return 1;
        }
        else if (this.year == date.year) {
            if (this.month < date.month) {
                return 1;
            }
            else if (this.month == date.month) {
                if (this.day < date.day) {
                    return 1;
                }
                return -1;

            }
            return -1;
        }
        return -1;
    }
}
