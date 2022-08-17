package br.com.letscode.domain.repository;

import br.com.letscode.domain.model.Car;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class CarRepository implements PanacheRepository<Car> {

    public List<Car> findByColor(String color) {
        return list("color", color);
    }

    public List<Car> findByModel(String model) {
        return list("model" , model);
    }
}
