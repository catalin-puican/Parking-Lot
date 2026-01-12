package org.example.parkinglot1.servlets.cars;

import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.parkinglot1.common.UserDto;
import org.example.parkinglot1.ejb.CarsBean;
import org.example.parkinglot1.ejb.UserBean;

import java.io.IOException;
import java.util.List;
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"WRITE_CARS"}))
@WebServlet(name = "AddCar", value = "/AddCar")
public class AddCar extends HttpServlet {
    @Inject
    UserBean usersBean;

    @Inject
    CarsBean carsBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        List<UserDto> users = usersBean.findAllUsers();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/WEB-INF/pages/cars/addCar.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        // Names match addCar.jsp form fields
        String licensePlate = request.getParameter("license_plate");
        String parkingSpot = request.getParameter("parking_spot");

        // This was the cause of the previous crash (changed from user_id to owner_id)
        Long userID = Long.parseLong(request.getParameter("owner_id"));

        carsBean.createCar(licensePlate, parkingSpot, userID);
        response.sendRedirect(request.getContextPath() + "/Cars");
    }
}