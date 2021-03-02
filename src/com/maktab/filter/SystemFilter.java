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
        switch (option) {
            case "reserve a room":
                String nationalCode = request.getParameter("nationalCode");
                String startDate = request.getParameter("startDate");
                String endDate = request.getParameter("endDate");
                int roomCapacity = Integer.parseInt(request.getParameter("roomCapacity"));
                if (validateNationalCode(nationalCode)) {
                    if (validateStartAndEndDate(startDate, endDate)){
                        if(validateRoomCapacity(roomCapacity)) {
                            chain.doFilter(request, response);
                        }
                        else {
                            printMessage("<div><h4>!Error: Room capacity is invalid...</h4></div>", request, response, writer);
                        }
                    }
                    else {
                        printMessage("<div><h4>!Error: Start and end date is invalid...</h4></div>", request, response, writer);
                    }
                }
                else {
                    printMessage("<div><h4>!Error: National code is invalid...</h4></div>", request, response, writer);
                }
                break;
            case "change reserve info":
                break;
            case "see reserve(s) info":
                break;
            case "cancel reserve":
                break;
            default:
                printMessage("<div><h4>!Warning: Please select the option you want...</div></h4>", request, response, writer);
                break;
        }
        writer.print("</html></body>");
        writer.close();

//        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {
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
            String[] splitStartDate = startDate.split("/");
            String[] splitEndDate = endDate.split("/");
            Date sDate = new Date(Integer.parseInt(splitStartDate[0]), Integer.parseInt(splitStartDate[1]), Integer.parseInt(splitStartDate[2]));
            Date eDate = new Date(Integer.parseInt(splitEndDate[0]), Integer.parseInt(splitEndDate[1]), Integer.parseInt(splitEndDate[2]));
            if (sDate.compareTo(eDate) == 1) {
                return true;
            }
            return false;
        }
        else {
            return false;
        }
    }

    private boolean validateDateFormat(String date) {
        if (date.matches("^[1-4]\\d{3}/((0[1-6]/((3[0-1])|([1-2][0-9])|(0[1-9])))|((1[0-2]|(0[7-9]))/(30|([1-2][0-9])|(0[1-9]))))$")) {
            System.out.println("yes!");
            return true;
        }
        System.out.println("no!");
        return false;
    }

    private void printMessage(String message, ServletRequest request, ServletResponse response, PrintWriter writer) throws ServletException, IOException {
        writer.println(message);
        request.getRequestDispatcher("roomReservationSystem.html").include(request, response);
    }
}
