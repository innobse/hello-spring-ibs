package ru.learnup.ibs.hello.spring.hellospring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.learnup.ibs.hello.spring.hellospring.model.CarEntity;

import javax.persistence.LockModeType;
import java.util.List;

/**
 * Description
 *
 * @author bse71
 * Created on 23.03.2022
 * @since
 */
public interface CarRepository extends JpaRepository<CarEntity, Integer> {

    List<CarEntity> findAllByCreateYear(int year);
    List<CarEntity> findAllByCreateYearAfter(int afterYear);
    List<CarEntity> findAllByFabric(String fabric);
    List<CarEntity> findAllByFabricAndModelOrderByCreateYearDesc(String fabric, String model);

    @Query("select car from CarEntity car where car.fabric = :fabric and car.createYear > :borderYear")
    List<CarEntity> findAllMySelect(String fabric, int borderYear);

    @Modifying
    @Query("update CarEntity car set car.vin = :vin")
    void updateAllVins(String vin);

    @Query(name = "car.byFabricAndModelOrder")
    List<CarEntity> getAllSort(String fabric, String model);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("select car from CarEntity car where car.id = :id")
    CarEntity lockAndGetById(int id);
}
