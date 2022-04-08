package ru.learnup.ibs.hello.spring.hellospring.annotations;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description
 *
 * @author bse71
 * Created on 08.04.2022
 * @since
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasRole(\"ADMIN\")")
public @interface AdminRole {
}
