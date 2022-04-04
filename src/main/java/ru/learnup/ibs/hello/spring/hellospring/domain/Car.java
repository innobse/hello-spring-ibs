package ru.learnup.ibs.hello.spring.hellospring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Description
 *
 * @author bse71
 * Created on 18.03.2022
 * @since
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    private Integer id;
    private String vin;
    private String fabric;
    private String model;
    private int createYear;

    public void paint(String color) {
        System.out.println("Перекрасили машину в цвет " + color);
    }
}
