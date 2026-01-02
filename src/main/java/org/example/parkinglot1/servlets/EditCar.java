package org.example.parkinglot1.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.parkinglot1.common.CarDto;
import org.example.parkinglot1.common.UserDto;
import org.example.parkinglot1.ejb.CarsBean;
import org.example.parkinglot1.ejb.UsersBean;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "EditCar", value = "/EditCar")
public class EditCar extends HttpServlet {

    @Inject
    UsersBean usersBean;

    @Inject
    CarsBean carsBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        List<UserDto> users = usersBean.findAllUsers();
        request.setAttribute("users", users);

        Long carId = Long.parseLong(request.getParameter("id")); // Usually passed as ?id=... in URL
        CarDto car = carsBean.findById(carId);
        request.setAttribute("car", car);

        request.getRequestDispatcher("/WEB-INF/pages/editCar.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        // Names match editCar.jsp form fields EXACTLY
        String licensePlate = request.getParameter("licensePlate"); // camelCase in JSP
        String parkingSpot = request.getParameter("parking_spot");  // snake_case in JSP
        String userIdStr = request.getParameter("user_id");         // snake_case in JSP
        String carIdStr = request.getParameter("car_id");           // snake_case in JSP

        Long userID = Long.parseLong(userIdStr);
        Long carID = Long.parseLong(carIdStr);

        carsBean.updateCar(carID, licensePlate, parkingSpot, userID);
        response.sendRedirect(request.getContextPath() + "/Cars");
    }
}