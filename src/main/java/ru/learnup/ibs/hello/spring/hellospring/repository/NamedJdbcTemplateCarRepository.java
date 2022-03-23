//package ru.learnup.ibs.hello.spring.hellospring.repository;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Profile;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.jdbc.support.rowset.SqlRowSet;
//import org.springframework.stereotype.Repository;
//import ru.learnup.ibs.hello.spring.hellospring.domain.Car;
//import ru.learnup.ibs.hello.spring.hellospring.repository.interfaces.CarRepository;
//
//import java.util.*;
//
///**
// * Description
// *
// * @author bse71
// * Created on 18.03.2022
// * @since
// */
//@Repository
//@Profile("jdbc-named")
//public class NamedJdbcTemplateCarRepository implements CarRepository {
//
//    private NamedParameterJdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public NamedJdbcTemplateCarRepository(NamedParameterJdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    @Override
//    public List<Car> getAll() {
//        final SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("SELECT * FROM cars;", Collections.emptyMap());
//        List<Car> result = new ArrayList<>();
//        while (sqlRowSet.next()) {
//            result.add(
//                    parseCar(sqlRowSet));
//        }
//        return result;
//    }
//
//    @Override
//    public void addCar(Car car) {
//        Map<String, Object> params = new HashMap<String, Object>() {{
//            put("vin", car.getVin());
//            put("fabric", car.getFabric());
//            put("model", car.getModel());
//            put("year", car.getCreateYear());
//        }};
//
//        jdbcTemplate.update(
//                "INSERT INTO cars(id, vin, fabric, model, year)" +
//                        " VALUES (nextval('cars_id_seq'), :vin, :fabric, :model, :year);", params);
//    }
//
//    private Car parseCar(SqlRowSet sqlRowSet) {
//        final int id = sqlRowSet.getInt("id");
//        final String vin = sqlRowSet.getString("vin");
//        final String fabric = sqlRowSet.getString("fabric");
//        final String model = sqlRowSet.getString("model");
//        final int year = sqlRowSet.getInt("year");
//
//        return new Car(id, vin, fabric, model, year);
//    }
//}
