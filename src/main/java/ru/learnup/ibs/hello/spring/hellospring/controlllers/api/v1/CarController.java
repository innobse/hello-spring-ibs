package ru.learnup.ibs.hello.spring.hellospring.controlllers.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.learnup.ibs.hello.spring.hellospring.controlllers.api.v1.dto.CarDto;
import ru.learnup.ibs.hello.spring.hellospring.controlllers.api.v1.dto.ErrorDto;
import ru.learnup.ibs.hello.spring.hellospring.services.interfaces.CarService;
import ru.learnup.ibs.hello.spring.hellospring.services.interfaces.Mapper;

import java.sql.SQLException;
import java.util.Collection;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Description
 *
 * @author bse71
 * Created on 31.03.2022
 * @since
 */
@RestController
@RequestMapping("/api/v1/cars")
public class CarController {

    private CarService service;
    private Mapper mapper;

    @Autowired
    public CarController(CarService service, Mapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public Collection<CarDto> getAll() {
        return service.getAvailableCars().stream()
                .map(mapper::toDto)
                .map(e -> {
                    e.add(
                            linkTo(methodOn(CarController.class).get(e.getId())).withSelfRel());
                    e.add(
                            Link.of("http://yandex.ru/"));
                    e.add(
                            linkTo(CarController.class).slash("ololo").slash(e.getId()).withRel("ololo"));
                    return e;
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/byFabric")
    public Collection<CarDto> getAll(@RequestParam("fabric") String fabric) {
        return service.getAllCarsByFabric(fabric).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CarDto get(@PathVariable("id") int id) {
        return mapper.toDto(
                service.get(id));
    }

    @PostMapping
    public CarDto create(@RequestBody CarDto carDto) {
        return mapper.toDto(
                service.registerNew(
                    mapper.toDomain(carDto)));
    }

    @PutMapping("/{id}")
    public CarDto update(
            @PathVariable("id") int id,
            @RequestBody CarDto carDto) {
        carDto.setId(id);
        return mapper.toDto(
                service.update(
                    mapper.toDomain(carDto)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        service.deleteById(id);
    }
}
