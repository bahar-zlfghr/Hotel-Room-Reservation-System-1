package com.maktab.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.PrintWriter;

import com.maktab.java.Date;

@WebFilter(filterName = "SystemFilter")
public class SystemFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.print("<html><head><meta charset=\"UTF-8\"></head><body>");
        String option = request.getParameter("mainForm");
        String fullName;
        String nationalCode;
        String startDate;
        String endDate;
        int roomCapacity;
        int roomNumber;
        int reserveCode;
        switch (option) {
            case "reserve a room":
                reserveRoom(writer, request, response, chain);
                break;
            case "change reserve info":
                changeRoomInfo(writer, request, response, chain);
                break;
            case "see reserve(s) info":
                break;
            case "cancel reserve":
                break;
        }
        writer.print("</html></body>");
        writer.close();

//        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {
    }

    private void reserveRoom(PrintWriter writer, ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String nationalCode = request.getParameter("nationalCode1");
        String startDate = request.getParameter("startDate1");
        String endDate = request.getParameter("endDate1");
        int roomCapacity = Integer.parseInt(request.getParameter("roomCapacity1"));
        if (validateNationalCode(nationalCode)) {
            if (validateStartAndEndDate(startDate, endDate)){
                if(validateRoomCapacity(roomCapacity)) {
                    chain.doFilter(request, response);
                }
                else {
                    printMessageForRoomCapacity(request, response, writer);
                }
            }
            else {
                printMessageForStartAndEndDate(request, response, writer);
            }
        }
        else {
            printMessageForNationalCode(request, response, writer);
        }
    }

    private void changeRoomInfo(PrintWriter writer, ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        int reserveCode = Integer.parseInt(request.getParameter("reserveCode2"));
        String startDate = request.getParameter("startDate2");
        String endDate = request.getParameter("endDate2");
        System.out.println(reserveCode + " " + startDate + " " + endDate);
        int roomCapacity = Integer.parseInt(request.getParameter("roomCapacity2"));
        if (validateReserveCode(reserveCode)) {
            if (validateStartAndEndDate(startDate, endDate)) {
                if (validateRoomCapacity(roomCapacity)) {
                    chain.doFilter(request, response);
                }
                else {
                    printMessageForRoomCapacity(request, response, writer);
                }
            }
            else {
                printMessageForStartAndEndDate(request, response, writer);
            }
        }
        else {
            printMessageForReserveCode(request, response, writer);
        }
    }

    private boolean validateNationalCode(String nationalCode) {
        if (nationalCode.length() == 10 && nationalCode.matches("[0-9]+")) {
            return true;
        }
        return false;
    }

    private boolean validateRoomCapacity(int roomCapacity) {
        if (roomCapacity > 0 && roomCapacity < 5) {
            return true;
        }
        return false;
    }

    private boolean validateStartAndEndDate(String startDate, String endDate) {
        System.out.println(startDate + " " + endDate);
        if (validateDateFormat(startDate) && validateDateFormat(endDate)) {
            Date sDate = convertStringDateToObjectDate(startDate);
            Date eDate = convertStringDateToObjectDate(endDate);
            if (sDate.compareTo(eDate) == 1) {
                return true;
            }
        }
        return false;
    }

    public static Date convertStringDateToObjectDate(String dateStr) {
        String[] splitDateStr = dateStr.split("/");
        Date date = new Date(Integer.parseInt(splitDateStr[0]), Integer.parseInt(splitDateStr[1]), Integer.parseInt(splitDateStr[2]));
        return date;
    }

    private boolean validateDateFormat(String date) {
        if (date.matches("^[1-4]\\d{3}/((0[1-6]/((3[0-1])|([1-2][0-9])|(0[1-9])))|((1[0-2]|(0[7-9]))/(30|([1-2][0-9])|(0[1-9]))))$")) {
            System.out.println("yes!");
            return true;
        }
        System.out.println("no!");
        return false;
    }

    private boolean validateReserveCode(int reserveCode) {
        String reserveCodeStr = String.valueOf(reserveCode);
        if (reserveCodeStr.length() == 5 && reserveCodeStr.matches("[0-9]+")) {
            return true;
        }
        return false;
    }

    private void printMessageForNationalCode(ServletRequest request, ServletResponse response, PrintWriter writer) throws ServletException, IOException {
        writer.println("<div><h4>!Error: The national code must contain 10 digits...</h4></div>");
        request.getRequestDispatcher("roomReservationSystem.html").include(request, response);
    }

    private void printMessageForStartAndEndDate(ServletRequest request, ServletResponse response, PrintWriter writer) throws ServletException, IOException {
        writer.println("<div><h4> !Error: The start or end date must be in yyyy/mm/dd format...</h4>" +
                        "<h4> And</h4>" +
                        "<h4> The end date must be at least one day after the start date of the stay...</h4>" +
                        "</div>");
        request.getRequestDispatcher("roomReservationSystem.html").include(request, response);
    }

    private void printMessageForRoomCapacity(ServletRequest request, ServletResponse response, PrintWriter writer) throws ServletException, IOException {
        writer.println("<div><h4> !Error: The room capacity should be between 1 and 4...</h4></div>");
        request.getRequestDispatcher("roomReservationSystem.html").include(request, response);
    }

    private void printMessageForReserveCode(ServletRequest request, ServletResponse response, PrintWriter writer) throws ServletException, IOException {
        writer.println("<div><h4> !Error: The reserve code must contain 5 digits...</h4></div>");
        request.getRequestDispatcher("roomReservationSystem.html").include(request, response);
    }
}
