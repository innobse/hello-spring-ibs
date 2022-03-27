package ru.learnup.ibs.hello.spring.hellospring.services;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.learnup.ibs.hello.spring.hellospring.model.CarEntity;
import ru.learnup.ibs.hello.spring.hellospring.events.CarRegistrationEvent;
import ru.learnup.ibs.hello.spring.hellospring.repository.CarRepository;
import ru.learnup.ibs.hello.spring.hellospring.services.interfaces.CarService;
import ru.learnup.ibs.hello.spring.hellospring.services.interfaces.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;
import java.util.Collection;
import java.util.Optional;

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

    @Autowired
    public CarServiceImpl(CarRepository repository, Logger logger) {
        this.repository = repository;
        this.logger = logger;
    }

    @Override
    public Collection<CarEntity> getAvailableCars() {
        return repository.findAll();
    }

    @Override
    public void registerNew(CarEntity car) {
        repository.save(car);
        ctx.publishEvent(
                new CarRegistrationEvent(
                        new CarRegistrationEvent.Info(car.getVin())));
    }

    @Override
    @Transactional
    public void updateVin(String vin) {
        System.out.println("Старт транзакции " + Thread.currentThread().getName());
        final CarEntity targetCar = repository.lockAndGetById(1);
        System.out.println("Получение объекта");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        targetCar.setVin(vin);
        repository.save(targetCar);
        System.out.println("Конец транзакции " + Thread.currentThread().getName());
    }

    @Override
    public Collection<CarEntity> getAllCarsByFabric(String fabric) {
        return repository.findAllByFabric(fabric);
    }

    @Override
    public Collection<CarEntity> getAllForMySelect(String fabric, int borderYear) {
        return repository.findAllMySelect(fabric, borderYear);
    }

    @Override
    public Collection<CarEntity> getAllSort(String fabric, String model) {
        return repository.getAllSort(fabric, model);
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
