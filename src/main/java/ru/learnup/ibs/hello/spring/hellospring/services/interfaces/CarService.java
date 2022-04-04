package ru.learnup.ibs.hello.spring.hellospring.services.interfaces;

import ru.learnup.ibs.hello.spring.hellospring.domain.Car;

import java.util.Collection;

/**
 * Description
 *
 * @author bse71
 * Created on 18.03.2022
 * @since
 */
public interface CarService {

    Collection<Car> getAvailableCars();

    Car registerNew(Car car);

    Collection<Car> getAllCarsByFabric(String fabric);

    Collection<Car> getAllForMySelect(String fabric, int borderYear);

    Collection<Car> getAllSort(String fabric, String model);

    Car get(int id);

    void deleteById(int id);

    Car update(Car Car);
}
