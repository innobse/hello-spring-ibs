package ru.learnup.ibs.hello.spring.hellospring.model;

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
@Entity
@Table(name = "cars")
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cars_id_seq")
    @SequenceGenerator(name = "cars_id_seq", sequenceName = "cars_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "vin")
    private String vin;

    @Column(name = "fabric")
    private String fabric;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private int createYear;
}
