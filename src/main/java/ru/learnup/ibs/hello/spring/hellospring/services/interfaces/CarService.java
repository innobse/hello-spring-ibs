package ru.learnup.ibs.hello.spring.hellospring.services.interfaces;

import ru.learnup.ibs.hello.spring.hellospring.model.CarEntity;

import java.net.SocketException;
import java.util.Collection;

/**
 * Description
 *
 * @author bse71
 * Created on 18.03.2022
 * @since
 */
public interface CarService {

    Collection<CarEntity> getAvailableCars();

    void registerNew(CarEntity car);

    void updateVin(String vin);

    Collection<CarEntity> getAllCarsByFabric(String fabric);

    Collection<CarEntity> getAllForMySelect(String fabric, int borderYear);

    Collection<CarEntity> getAllSort(String fabric, String model);
}
