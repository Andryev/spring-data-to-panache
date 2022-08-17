package br.com.letscode.domain.repository;

import br.com.letscode.domain.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    public List<Car> findByColor(String color);

    public List<Car> findByModel(String model);
}
