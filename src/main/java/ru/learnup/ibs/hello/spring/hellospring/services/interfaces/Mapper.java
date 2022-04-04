package ru.learnup.ibs.hello.spring.hellospring.services.interfaces;

import ru.learnup.ibs.hello.spring.hellospring.controlllers.api.v1.dto.CarDto;
import ru.learnup.ibs.hello.spring.hellospring.domain.Car;
import ru.learnup.ibs.hello.spring.hellospring.model.CarEntity;

/**
 * Description
 *
 * @author bse71
 * Created on 01.04.2022
 * @since
 */
@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {

    Car toDomain(CarEntity carEntity);
    CarEntity toEntity(Car car);

    CarDto toDto(Car carEntity);
    Car toDomain(CarDto car);
}
