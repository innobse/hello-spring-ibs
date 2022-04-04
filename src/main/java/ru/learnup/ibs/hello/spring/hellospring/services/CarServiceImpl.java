package ru.learnup.ibs.hello.spring.hellospring.services;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.learnup.ibs.hello.spring.hellospring.domain.Car;
import ru.learnup.ibs.hello.spring.hellospring.events.CarRegistrationEvent;
import ru.learnup.ibs.hello.spring.hellospring.repository.CarRepository;
import ru.learnup.ibs.hello.spring.hellospring.services.interfaces.CarService;
import ru.learnup.ibs.hello.spring.hellospring.services.interfaces.Logger;
import ru.learnup.ibs.hello.spring.hellospring.services.interfaces.Mapper;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Description
 *
 * @author bse71
 * Created on 18.03.2022
 * @since
 */
@Service("carService")
@Scope("prototype")
public class CarServiceImpl implements CarService, BeanNameAware, ApplicationContextAware, DisposableBean {

    private CarRepository repository;
    private Logger logger;
    private String myName;
    private ApplicationContext ctx;
    private Mapper mapper;

    @Autowired
    public CarServiceImpl(CarRepository repository, Logger logger, Mapper mapper) {
        this.repository = repository;
        this.logger = logger;
        this.mapper = mapper;
    }

    @Override
    public Collection<Car> getAvailableCars() {
        return repository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
//        throw new RuntimeException("Что-то стращное случилось!");
    }

    @Override
    public Car registerNew(Car car) {
        final Car savedCar = mapper.toDomain(
                repository.save(
                        mapper.toEntity(car)));
        ctx.publishEvent(
                new CarRegistrationEvent(
                        new CarRegistrationEvent.Info(car.getVin())));

        return savedCar;
    }

    @Override
    public Collection<Car> getAllCarsByFabric(String fabric) {
        return repository.findAllByFabric(fabric).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Car> getAllForMySelect(String fabric, int borderYear) {
        return repository.findAllMySelect(fabric, borderYear).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Car> getAllSort(String fabric, String model) {
        return repository.getAllSort(fabric, model).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Car get(int id) {
        return mapper.toDomain(
                repository.findById(id).orElse(null));
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
//        throw new RuntimeException();
    }

    @Override
    public Car update(Car car) {
        return mapper.toDomain(
                repository.save(
                        mapper.toEntity(car)));
    }

    @Override
    public void setBeanName(String name) {
        myName = name;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }

    @PostConstruct
    public void myInit() {
        logger.log("Заполняю другие поля в " + myName + "...");
    }

    @Override
    public void destroy() throws Exception {
        logger.log("spring destroy");
    }

    @PreDestroy
    public void ustroyDestroy() {
        logger.log("ustroyDestroy");
    }
}
