package br.com.letscode.resource;


import br.com.letscode.domain.model.Car;
import br.com.letscode.domain.service.CarService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/car")
@Produces(MediaType.APPLICATION_JSON)
public class CarResource {

    @Inject
    CarService carService;

    @GET
    public Iterable<Car> findAll() {
         return this.carService.findAll();
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        this.carService.delete(id);
    }

    @POST
    @Path("/color/{color}/model/{model}")
    public Car save(@PathParam("color") String color, @PathParam("model") String model) {
         return this.carService.save(color, model);
    }


    @GET
    @Path("/color/{color}")
    public List<Car> findByColor(@PathParam("color") String color) {
        return this.carService.findByCor(color);
    }


    @GET
    @Path("/model/{model}")
    public List<Car> findByModel(@PathParam("model") String model) {
        return this.carService.findByModel(model);
    }

    @GET
    @Path("/{id}")
    public Car findById(@PathParam("id") Long id) {
        return this.carService.findById(id);
    }

}
