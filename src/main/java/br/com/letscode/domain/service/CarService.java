package br.com.letscode.domain.service;


import br.com.letscode.domain.model.Car;
import br.com.letscode.domain.repository.CarRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Transactional
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Iterable<Car> findAll() {
        return  this.carRepository.findAll();
    }

    public void delete(Long id) {
        this.carRepository.deleteById(id);
    }

    public Car save(String color, String model) {
        Car car = new Car();
        car.setColor(color);
        car.setModel(model);
        return this.carRepository.save(car);
    }

    public List<Car> findByCor(String color) {
        return this.carRepository.findByColor(color);
    }

    public List<Car> findByModel(String model) {
        return this.carRepository.findByModel(model);
    }

    public Car findById(Long id) {
        return this.carRepository.findById(id).get();
    }

}
