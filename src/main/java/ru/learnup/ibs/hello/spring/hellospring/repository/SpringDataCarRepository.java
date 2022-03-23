package ru.learnup.ibs.hello.spring.hellospring.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.learnup.ibs.hello.spring.hellospring.model.CarEntity;
import ru.learnup.ibs.hello.spring.hellospring.repository.interfaces.CarRepository;
import ru.learnup.ibs.hello.spring.hellospring.repository.springdata.CarJpaRepository;

import java.util.List;

/**
 * Description
 *
 * @author bse71
 * Created on 18.03.2022
 * @since
 */
@Repository
@Profile("spring-data")
public class SpringDataCarRepository implements CarRepository {

    private CarJpaRepository carJpaRepository;

    @Autowired
    public SpringDataCarRepository(CarJpaRepository carJpaRepository) {
        this.carJpaRepository = carJpaRepository;
    }

    @Override
    public List<CarEntity> getAll() {
        return carJpaRepository.findAll();
    }

    @Override
    public void addCar(CarEntity car) {
        carJpaRepository.save(car);
    }
}
