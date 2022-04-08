package ru.learnup.ibs.hello.spring.hellospring.controlllers.api.v1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.learnup.ibs.hello.spring.hellospring.controlllers.api.v1.dto.ErrorDto;

import java.sql.SQLException;

/**
 * Description
 *
 * @author bse71
 * Created on 01.04.2022
 * @since
 */
@RestControllerAdvice
@Slf4j
public class ErrorController {

    @ExceptionHandler(value = {
            DataAccessException.class,
            SQLException.class
    })
    public ErrorDto dataBaseException(Exception err) {
        return new ErrorDto(1, "У нас временные трудности, попробуйте завтра");
    }

//    @ExceptionHandler(value = {
//            Exception.class
//    })
//    public ErrorDto exception(Exception err) {
//        log.error("Error: " + err);
//        return new ErrorDto(2, "Упс, что-то пошло не так, попробуйте позже");
//    }
}
