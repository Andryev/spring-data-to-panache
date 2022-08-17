package br.com.letscode.domain.service;


import br.com.letscode.domain.model.Car;
import br.com.letscode.domain.repository.CarRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Transactional
public class CarService {

    private final CarRepository carRepository;

    @Inject
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Iterable<Car> findAll() {
        return  this.carRepository.listAll();
    }

    public void delete(Long id) {
        this.carRepository.deleteById(id);
    }

    public Car save(String color, String model) {
        Car car = new Car();
        car.setColor(color);
        car.setModel(model);
        this.carRepository.persist(car);
        return car;
    }

    public List<Car> findByCor(String color) {
        return this.carRepository.findByColor(color);
    }

    public List<Car> findByModel(String model) {
        return this.carRepository.findByModel(model);
    }

    public Car findById(Long id) {
        return this.carRepository.findById(id);
    }

}
