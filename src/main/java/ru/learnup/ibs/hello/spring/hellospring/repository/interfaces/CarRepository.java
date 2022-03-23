package ru.learnup.ibs.hello.spring.hellospring.repository.interfaces;

import ru.learnup.ibs.hello.spring.hellospring.model.CarEntity;

import java.util.Collection;

/**
 * Description
 *
 * @author bse71
 * Created on 18.03.2022
 * @since
 */
public interface CarRepository {

    Collection<CarEntity> getAll();
    void addCar(CarEntity car);
}
