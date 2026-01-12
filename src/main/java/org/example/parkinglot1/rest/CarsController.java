package org.example.parkinglot1.rest;

import org.example.parkinglot1.common.CarDto;
import org.example.parkinglot1.ejb.CarsBean;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/Cars")
@RolesAllowed("READ_CARS")
public class CarsController {
    @Inject
    CarsBean carsBean;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CarDto> findAllCars() {
        return carsBean.findAllCars();
    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public CarDto findCar(@PathParam("id") Long id) {
        return carsBean.findById(id);
    }
}