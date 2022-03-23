package ru.learnup.ibs.hello.spring.hellospring.repository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.learnup.ibs.hello.spring.hellospring.model.CarEntity;

/**
 * Description
 *
 * @author bse71
 * Created on 23.03.2022
 * @since
 */
public interface CarJpaRepository extends JpaRepository<CarEntity, Integer> {

}
