package ru.learnup.ibs.hello.spring.hellospring.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.learnup.ibs.hello.spring.hellospring.model.CarEntity;
import ru.learnup.ibs.hello.spring.hellospring.repository.interfaces.CarRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Description
 *
 * @author bse71
 * Created on 18.03.2022
 * @since
 */
@Repository
@Profile("in-memory")
public class InMemoryCarRepository implements CarRepository {

    private final List<CarEntity> storage = new ArrayList<>();

    @Override
    public List<CarEntity> getAll() {
        return new ArrayList<>(storage);
    }

    @Override
    public void addCar(CarEntity car) {
        storage.add(car);
    }
}
