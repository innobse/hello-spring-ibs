package ru.learnup.ibs.hello.spring.hellospring.controlllers.api.v1.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@JsonIgnoreProperties(value = "unknown")
public class ErrorDto {

    @JsonProperty("code")
    private Integer code;

    @JsonProperty("message")
    private String message;
}
